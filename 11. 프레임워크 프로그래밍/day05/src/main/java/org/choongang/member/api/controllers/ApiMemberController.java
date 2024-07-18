package org.choongang.member.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.Utils;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.global.exceptions.CommonException;
import org.choongang.global.rests.JSONData;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.services.JoinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberMapper mapper;
    private final JoinService joinService;
    private final Utils utils;

    @PostMapping // POST /api/member
    public ResponseEntity join(@RequestBody @Valid RequestJoin form, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessage(errors));
        }

//        if (errors.hasErrors()) { // errors 객체는 에러 정보를 확인 하는 것이 주 목적
//            errors.getFieldErrors() // JSON은 errors 객체에 있는 정보는 가지고 에러 정보를 직접 가공해야 한다
//                    .forEach(System.out::println);
//            errors.getGlobalErrors().forEach(System.out::println);
//
//            return ResponseEntity.badRequest().build();
//        }

//        boolean result = false;
//        if(!result) {
//            throw new BadRequestException("예외 테스트");
//        }

        joinService.process(form);
        // 응답 코드 201, 출력 바디 X
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info/{email}")
    public JSONData info(@PathVariable("email") String email) {
        // Content-Type: application/json
        // 반환값이 자바객체이다
        Member member = mapper.get(email);

        return new JSONData(member);
    }
    
    @GetMapping("/list")
    public ResponseEntity<JSONData> list() {
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .regDt(LocalDateTime.now())
                        .build())
                .toList(); // 반환값이 List 형태이면 List도 알아서 JSON 형태로 변환된다

        HttpHeaders headers = new HttpHeaders(); // 헤더
        headers.add("t1", "v1");
        headers.add("t2", "v2");

        return new ResponseEntity<>(new JSONData(members), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/test", produces = "text/html; charset=UTF-8")
    public String test() {
        // Cont-Type: text/plain
        return "안녕하세요!";
    }

    @GetMapping("/test2")
    public void test2() {
        log.info("test2");
    }

    // 응답 JSON은 형식이 거의 달라질 일이 없다
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<JSONData> errorHandler(Exception e) {
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
//        if (e instanceof CommonException commonException) {
//            status = commonException.getStatus();
//        }
//        JSONData data = new JSONData(); // JSON 데이터 형태로 추가
//        data.setSuccess(false);
//        data.setMessage(e.getMessage());
//        data.setStatus(status); // 상태 코드 넣기
//
//        e.printStackTrace();
//
//        return ResponseEntity.status(status).body(data);
//    }
}
