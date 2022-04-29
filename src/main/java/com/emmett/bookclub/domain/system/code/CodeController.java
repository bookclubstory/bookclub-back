package com.emmett.bookclub.domain.system.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/code")
public class CodeController {
    private final CodeService codeService;

    @GetMapping()
    public List<Map<String, Object>> getCodeList(@RequestParam String code) {
        return codeService.getCodeList(code);
    }
}
