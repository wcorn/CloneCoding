package com.base.project.tdd.domain.test;

import com.base.project.tdd.global.common.api.ApiResponse;
import com.base.project.tdd.global.error.ErrorCode;
import com.base.project.tdd.global.common.api.ResponseCode;
import com.base.project.tdd.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    @PostMapping("/success")
    public ResponseEntity<ApiResponse<String>> testSuccess() {

        String data = "test api success";
        log.info("test controller {}",data);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.TEST));
    }

    @PostMapping("/fail")
    public ResponseEntity<ApiResponse<String>> testFail() {
        String data = "test api fail";
        throw new CustomException(ErrorCode.TEST);
    }
}