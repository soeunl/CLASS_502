package org.choongang.global.validators;

public interface RequiredValidator { // 필수 입력값 검증을 위한 메서드를 제공

    default void checkRequired(String str, RuntimeException e) { // 유효하지 않은 입력값을 체크하는 역할을 수행
        if(str == null || str.isBlank()) { // null이거나 공백 (isBlank())인 경우 파라미터로 받은 e 예외를 발생
            throw e;
        }
    }

    default void checkTrue(boolean result, RuntimeException e) { // 조건 검증을 위한 메서드를 제공
        if (!result) { // result 값이 false인 경우 파라미터로 받은 e 예외를 발생
            throw e; // 특정 조건이 참이어야 하는 상황을 검증하는 역할을 수행
        }
    }
}
