package org.choongang.survey.controllers;

import lombok.Data;

@Data // 여기는 커맨드 객체이다
public class RequestSurvey {
    private String q1;
    private String q2;
    private String q3;
    private String q4;
}
