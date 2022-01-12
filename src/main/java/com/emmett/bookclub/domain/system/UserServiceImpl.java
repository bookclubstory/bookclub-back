package com.emmett.bookclub.domain.system;

import com.emmett.bookclub.domain.system.sequence.SequenceService;
import com.emmett.bookclub.global.security.AuthToken;
import com.emmett.bookclub.global.security.role.Role;
import com.emmett.bookclub.global.security.role.RoleRepository;
import com.emmett.bookclub.global.security.role.UserRole;
import com.emmett.bookclub.global.security.role.UserRoleRepository;
import com.emmett.bookclub.global.security.user.*;
import com.emmett.bookclub.global.util.Util;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final Util util;
    private final SequenceService sequenceService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    @Modifying
    public ResponseEntity<UserDto> signUp(UserDto userDto) {
        userRepository.findByUsername(userDto.getUsername()).ifPresent(c -> {
            throw new RuntimeException("User Name(" + userDto.getUsername() + ") is already registered...");
        });

        try {
            // 1st save User without userRoles
            User user = new User();
            user.signUp(
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    userDto.getEmail(),
                    "system",
                    LocalDateTime.now());
            userRepository.save(user);

            // 2nd save UserRole
            UserRole userRole = new UserRole();
            userRole.newUserRole(
                    userDto.getUsername(),
                    "USER",
                    "system",
                    LocalDateTime.now()
            );
            userRoleRepository.save(userRole);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        String loginId = util.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            loginId = "system";
        }
        userRepository.findByUsername(userDto.getUsername()).ifPresent(c -> {
            throw new RuntimeException("User Name(" + userDto.getUsername() + ") is already registered...");
        });
        try {
            List<UserRole> userRoles = new ArrayList<>();
            for (String role :
                    userDto.getRoles()) {
                UserRole userRole = new UserRole();
                userRole.newUserRole(userDto.getUsername(),
                        role,
                        loginId,
                        LocalDateTime.now());
                userRoleRepository.save(userRole);

                userRoles.add(userRole);
            }

            User user = new User();
            user.newUser(
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getTel(),
                    userDto.getEmail(),
                    userRoles,
                    userDto.getAddress1(),
                    userDto.getAddress2(),
                    userDto.getIsValid(),
                    loginId,
                    LocalDateTime.now());
            userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public List<UserDto> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        "******",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getTel(),
                        user.getEmail(),
                        user.getAddress1(),
                        user.getAddress2(),
                        user.getRoles().stream()
                                .map(userRoles -> userRoles.getRole()
                                ).collect(toList())
                )).collect(toList());
    }

    @Override
    public ResponseEntity<AuthToken> login(UserDto userDto, HttpSession session, HttpServletRequest request) {
        try {
            String loginId = userDto.getUsername();
            String loginPw = userDto.getPassword();
            String loginToken = userDto.getToken();

            Optional<User> user = userRepository.findByUsername(loginId);

            Optional<AuthToken> result =
                    user.map(obj -> {
                        // 1. username, password를 조합하여 UsernamePasswordAuthenticationToken 생성
                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginId, loginPw);
                        if (loginToken != null) {
                            // Create Granted Authority Rules
                            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                            token = new UsernamePasswordAuthenticationToken(loginId, null, grantedAuthorities);
                        } else {
                            // Form login
                            // 2. Form 로그인 검증을 위해 UsernamePasswordAuthenticationToken 을 authenticationManager 의 인스턴스로 전달
                            authenticationManager.authenticate(token);// 3. 인증에 성공하면 Authentication 인스턴스 리턴
                        }

                        /*
                         * added on 26.08.2019
                         * Login history
                         * */
                        String clientIp = this.getClientIp(request);

                        String logId = sequenceService.getSequenceBySeqPrefix("Log");
                        /* login success */
                        LoginHistory loginHistory = new LoginHistory(
                                logId,
                                loginId,
                                clientIp,
                                userDto.getConnectMethod(),
                                "",
                                request.getRequestURI(),
                                LocalDateTime.now()
                        );
                        loginHistoryRepository.save(loginHistory);

                        if (!StringUtils.isEmpty(userDto.getAttribute1())) {
                            /* from mobile */
                            userRepository.findByUsername(loginId).ifPresent(c -> {
                                c.updateAttribute1(userDto.getAttribute1());

                                // save Mobile token (String value)
                                userRepository.save(c);
                            });
                        }
                        return getAuthToken(session, loginId, obj, token);
                    });

            return result.map(authToken -> new ResponseEntity<>(authToken, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new AuthToken(
                            null
                            , null
                            , null
                            , null
                            , null
                            , null
                            , null
                            , null
                            , null
                            , 0), HttpStatus.UNAUTHORIZED));
        } catch (AuthenticationException e) {
            String logId = sequenceService.getSequenceBySeqPrefix("Log");
            LoginHistory loginHistory = new LoginHistory(
                    logId,
                    userDto.getUsername(),
                    this.getClientIp(request),
                    userDto.getConnectMethod(),
                    e.getMessage(),
                    request.getRequestURI(),
                    LocalDateTime.now()
            );
            loginHistoryRepository.save(loginHistory);

            return new ResponseEntity<>(new AuthToken(
                    null
                    , null
                    , null
                    , null
                    , null
                    , null
                    , null
                    , null
                    , null
                    , 0), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Override
    public ResponseEntity<String> resetPassword(UserDto userDto) {
        userRepository.findByUsername(util.getLoginId()).ifPresent(c -> {
            c.resetPassword(passwordEncoder.encode(userDto.getPassword()));

            userRepository.save(c);
        });

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<UserDto> updateUser(UserDto userDto) {
        String loginId = util.getLoginId();

        userRepository.findByUsername(loginId).ifPresent(c -> {
            c.updatePersonalUserInfo(userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getTel(),
                    userDto.getEmail(),
                    userDto.getAddress1(),
                    userDto.getAddress2());

            userRepository.save(c);
        });

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public List<Map<String, Object>> searchUserList(String keyword) {
        List<Map<String, Object>> result = new ArrayList<>();

        userRepository.findAll()
                .stream()
                .forEach(user -> {
                    String modifiedDate = "";
                    if (user.getModifiedDate() != null) {
                        modifiedDate = user.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    } else {
                        modifiedDate = user.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }

                    AtomicReference<String> userRoles = new AtomicReference<>("");
                    userRoles.set("[");
                    user.getRoles().forEach(role -> {
                        if (userRoles.get().length() > 1) {
                            userRoles.set(userRoles.get() + ", " + role.getRole());
                        } else {
                            userRoles.set(userRoles.get() + role.getRole());
                        }
                    });
                    userRoles.set(userRoles.get() + "]");

                    Map<String, Object> map = new HashMap<>();
                    map.put("action", "");
                    map.put("firstName", user.getFirstName());
                    map.put("lastName", user.getLastName());
                    map.put("username", user.getUsername());
                    map.put("email", user.getEmail());
                    map.put("isValid", user.getIsValid() == 1 ? "True" : "False");
                    map.put("modifiedDate", modifiedDate);
                    map.put("role", userRoles.toString());

                    result.add(map);
                });

        // Sorting result list by modifiedDate descending
        util.getCollectionSortByModifiedDate(result, "d");

        return result;
    }

    @Override
    @Modifying
    public boolean deleteUser(String username) {
        userRepository.deleteById(username);
        return true;
    }

    @Override
    public ResponseEntity<UserDto> getUserDetail(String username) {
        UserDto userDto = new UserDto();
        userRepository.findByUsername(username).ifPresent(c -> {
            userDto.setUsername(username);
            userDto.setFirstName(c.getFirstName());
            userDto.setLastName(c.getLastName());
            userDto.setIsValid(c.getIsValid());
            userDto.setTel(c.getTel());
            userDto.setEmail(c.getEmail());
            userDto.setAddress1(c.getAddress1());
            userDto.setAddress2(c.getAddress2());

            List<String> roles = new ArrayList<>();
            userRoleRepository.findRolesByUser_username(username).forEach(role -> {
                roles.add(role.getRole());
            });
            userDto.setRoles(roles);
        });

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    @Modifying
    public ResponseEntity<UserDto> editUser(UserDto userDto) {
        String loginId = util.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            loginId = "system";
        }

        try {
            String finalLoginId = loginId;
            userRepository.findByUsername(userDto.getUsername()).ifPresent(c -> {
                //1. process user data (edit User)
                c.editUser(userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getTel(),
                        userDto.getEmail(),
                        userDto.getAddress1(),
                        userDto.getAddress2(),
                        userDto.getIsValid(),
                        finalLoginId,
                        LocalDateTime.now());
                userRepository.save(c);

                //2. process Roles
                userRoleRepository.deleteByUsername(c.getUsername());
                List<UserRole> newRoles = new ArrayList<>();
                for (String role :
                        userDto.getRoles()) {
                    UserRole userRole = new UserRole();
                    userRole.newUserRole(c.getUsername(),
                            role,
                            finalLoginId,
                            LocalDateTime.now());
                    userRoleRepository.save(userRole);

                    newRoles.add(userRole);
                }
            });

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> registerUser(UserDto userDto) {
        String loginId = util.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            loginId = "system";
        }

        String email = userDto.getEmail();
        String username = email.substring(0, email.indexOf("@"));
        userRepository.findByUsername(username).ifPresent(c -> {
            throw new RuntimeException("User Name(" + username + ") is already registered...");
        });

        try {
            UserRole userRole = new UserRole();
            userRole.newUserRole(username,
                    "USER",
                    loginId,
                    LocalDateTime.now());
            userRoleRepository.save(userRole);

            List<UserRole> newRoles = new ArrayList<>();
            newRoles.add(userRole);

            User newUser = new User();
            newUser.newUser(
                    username,
                    passwordEncoder.encode(userDto.getPassword()),
                    userDto.getName(),
                    "",
                    "",
                    userDto.getEmail(),
                    newRoles,
                    "",
                    "",
                    1,
                    loginId,
                    LocalDateTime.now());
            userRepository.save(newUser);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @NotNull
    private AuthToken getAuthToken(final HttpSession session, final String loginId, final User user, final UsernamePasswordAuthenticationToken token/*, final Authentication authentication*/) {
        // 4. Authentication 인스턴스를 SecurityContextHolder의 SecurityContext에 설정
        SecurityContextHolder.getContext().setAuthentication(token);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        List<Role> authList = roleRepository.findByRoleIn(user
                .getRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList()));

        return new AuthToken(user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getTel(),
                user.getEmail(),
                user.getAddress1(),
                user.getAddress2(),
                session.getId(),
                authList,
                user.getIsValid());
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
