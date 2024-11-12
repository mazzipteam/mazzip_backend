package com.project.api.test;

import com.project.api.test.dto.TestAccessDTO;
import com.project.api.test.dto.TestCreateDTO;
import com.project.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final PasswordEncoder passwordEncoder;

    public Test access(TestAccessDTO testAccessDTO) {
        // testParam2는 Unique이기 때문에 접근가능
        var testParam2 = testAccessDTO.getTestParam2();

        // 1. id를 통해 해당 엔티티 불러오기
        var test = testRepository.findByTestParam2(testParam2)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return test;
    }

    public Test create(TestCreateDTO testCreateDTO) {
        var test = Test.builder()
                .testParam1(testCreateDTO.getTestParam1())
                .testParam2(testCreateDTO.getTestParam2())
                .testParam3(testCreateDTO.getTestParam3())
                .build();
        testRepository.save(test);
        return test;
    }

    public String getEncodedPwd(String password) {
        return passwordEncoder.encode(password);
    }
}
