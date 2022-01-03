package com.emmett.bookclub.global.security.user;

import com.emmett.bookclub.domain.system.UserService;
import com.emmett.bookclub.global.security.role.UserRole;
import com.emmett.bookclub.global.security.role.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class UserRepositoryTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void getUser() {
        List<UserRole> roles = userRoleRepository.findAll();

        assertNotNull(roles);
    }
}
