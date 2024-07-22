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
        // 요청 본문 (body)에 담긴 RequestJoin form 객체를 파라미터로 받음
        // @Valid 애노테이션은 해당 객체에 정의된 데이터 검증 (validation) 로직을 수행
        // Errors errors: 데이터 검증 과정에서 발생한 에러 정보를 담는 객체

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessage(errors)); // 공통적으로 동일한 형식으로 처리하기 위해 던졌다
        } // 데이터 검증 과정에서 에러가 발생한 경우 BadRequestException 예외를 발생시키고 에러 메시지를 클라이언트에게 반환

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

        joinService.process(form); // 데이터 검증에 통과한 경우 회원 가입 처리를 실제 수행
        // 응답 코드 201, 출력 바디 X
        return ResponseEntity.status(HttpStatus.CREATED).build();
        // 서비스 로직 수행이 완료되면 정상 처리 결과를 나타내는 ResponseEntity 객체를 클라이언트에게 반환
        // 반환되는 객체의 상태 코드는 HttpStatus.CREATED (201) 로 설정되어 회원 가입이 성공적으로 완료되었음을 알림
    }

    @GetMapping("/info/{email}") // 이메일 주소에 해당하는 회원 조회
    public JSONData info(@PathVariable("email") String email) {
        // @PathVariable: URL 경로의 {email} 부분을 email 파라미터로 매핑
        // String email: 매핑된 파라미터 값을 email 변수에 담음
        // Content-Type: application/json
        // 반환값이 자바객체이다
        Member member = mapper.get(email); // 이메일 주소에 해당하는 회원 정보를 조회

        return new JSONData(member); // 사용자 정보를 JSON 형태로 반환
    }


    // ResponseEntity<JSONData> 사용 시점
    // JSON 형태의 데이터를 클라이언트에게 반환할 때: REST API에서 자주 사용
    // HTTP 응답 상태 코드 및 헤더 정보를 함께 전달해야 할 때: 예를 들어, 캐싱 정보나 인증 관련 정보를 헤더에 담아 전달할 수 있음

    @GetMapping("/list") // 회원 목록 조회
    public ResponseEntity<JSONData> list() {
        // ResponseEntity : HTTP 응답 상태 코드, 헤더 정보, 본문 데이터를 담아 클라이언트에게 전달하는 역할
        List<Member> members = IntStream.rangeClosed(1, 10) //  1부터 10까지의 정수 범위를 생성
                .mapToObj(i -> Member.builder() // 스트림의 각 요소를 Member 객체 목록으로 변환
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .regDt(LocalDateTime.now())
                        .build())
                .toList(); // 반환값이 List 형태이면 List도 알아서 JSON 형태로 변환된다
        // 이메일 주소, 비밀번호, 사용자 이름, 등록날짜 정보를 가진 10개의 가짜 회원 정보가 생성(JSON 형태)

        HttpHeaders headers = new HttpHeaders(); // 헤더, HTTP 헤더 정보를 담는 객체를 생성
        headers.add("t1", "v1");
        headers.add("t2", "v2");

        return new ResponseEntity<>(new JSONData(members), headers, HttpStatus.OK);
        // 회원 정보 목록을 JSONData 클래스에 담아 JSON 형태로 변환
        // 생성된 HTTP 헤더 정보와 응답 상태 코드도 함께 가짐
        // 위와 같은 정보를 모두 포함하는 ResponseEntity 객체를 생성
        // 생성된 ResponseEntity 객체를 클라이언트에게 반환
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
