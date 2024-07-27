package org.choongang.statistic.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MemberStatisticService {
    // 스프링 스케줄링 기능을 사용하는 애노테이션
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    // fixedRate: 작업을 실행하는 간격을 설정. 이 경우 5초마다 실행
    // timeUnit: 간격의 단위를 설정. TimeUnit.SECONDS는 초를 의미
    public void makeData() { // 주기적으로 지정한 스케줄 대로 실행된다.
        log.info("실행");
    }
}
