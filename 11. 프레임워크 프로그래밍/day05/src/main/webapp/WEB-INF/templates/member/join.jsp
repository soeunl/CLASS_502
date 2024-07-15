<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="actionUrl" value="/member/join" />
<h1>
    <spring:message code="❤회원가입❤" />
</h1>
<form:form method="POST" action="${actionUrl}" autocomplete="off" modelAttribute="requestJoin">
    <dl>
    <dt><spring:message code="이메일" /></dt>
    <dd>
    <form:input path="email" cssClass="input-txt" cssStyle="background: plum" />
        <form:errors path="email"/>
<%--        element: 오류 메시지를 감싸는 HTML 요소의 종류를 지정합니다. 여기서는 오류 메시지가 <div> 요소로 감싸집니다.--%>
<%--        delimiter: 여러 오류 메시지를 표시할 때 각 메시지를 구분하는 문자열을 지정합니다. 이 경우, 빈 문자열("")로 설정되어 있어 메시지 사이에 구분자가 없습니다.--%>
<%--        이 코드는 email 필드에서 발생한 오류를 <div> 태그로 감싸서 출력하며, 오류 메시지 사이에는 구분자가 없음을 의미합니다.--%>
<%--        해당하는 필드의 오류를 바로 출력할 수 있음--%>
    </dd>
    </dl>
    <dl>
    <dt><spring:message code="비밀번호" /></dt>
    <dd>
    <form:password path="password" cssClass="input-txt" cssStyle="background: orange" />
        <form:errors path="password" />
            <%--        해당하는 필드의 오류를 바로 출력할 수 있음--%>
    </dd>
    </dl>
    <dl>
    <dt><spring:message code="비밀번호_확인" /></dt>
    <dd>
    <form:password path="confirmPassword" cssClass="input-txt" cssStyle="background: yellowgreen"/>
        <form:errors path="confirmPassword" />
    </dd>
    </dl>
    <dl>
    <dt><spring:message code="회원명" /></dt>
    <dd>
    <form:input path="userName" cssClass="input-txt" cssStyle="background: lightblue"/>
        <form:errors path="userName" />
    </dd>
    </dl>
    <dl>
        <dt><spring:message code="약관동의" /></dt>
        <dd>
            <form:checkbox path="agree" value="true" label="회원가입 약관에 동의합니다" />
            <form:errors path="agree" element="div" delimiter=""/>
        </dd>
    </dl>
    <button type="submit"><spring:message code="가입하기" /></button>
</form:form>