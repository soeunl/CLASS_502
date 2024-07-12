<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="actionUrl" value="/member/join" />
${command}

<%--<spring:message code="LOGIN_MSG" arguments="이소은,user01" />--%>

<h3><spring:message code="LOGIN_MSG">
    <spring:argument value="이소은01" />
    <spring:argument value="user01" />
</spring:message></h3>

    <h1><spring:message code="회원가입" /></h1>
<h2>${commonValue}</h2>
<form:form method="POST" action="${actionUrl}" autocomplete="off">
    <dl>
        <dt><spring:message code="이메일" /></dt>
        <dd>
            <form:input path="email" cssClass="input-txt" cssStyle="background: plum" />
        </dd>
    </dl>
    <dl>
        <dt><spring:message code="비밀번호" /></dt>
        <dd>
            <form:password path="password" cssClass="input-txt" cssStyle="background: orange" />
        </dd>
    </dl>
    <dl>
        <dt><spring:message code="비밀번호_확인" /></dt>
        <dd>
            <form:password path="confirmPassword" cssClass="input-txt" cssStyle="background: yellowgreen"/>
        </dd>
    </dl>
    <dl>
        <dt><spring:message code="회원명" /></dt>
        <dd>
            <form:input path="userName" cssClass="input-txt" cssStyle="background: lightblue"/>
        </dd>
    </dl>
<%--    <dl>--%>
<%--        <dt>❤ 취미</dt>--%>
<%--        <dd>--%>
<%--            <option value=''>- 선택하세요 -</option>--%>
<%--            <form:radiobuttons path="hobby" items="${hobbies}" />--%>
<%--            <form:select path="hobby">--%>
<%--                <option value=''>- 선택하세요 -</option>--%>
<%--                <form:option value="hobby0" label="취미0" />--%>
<%--                <form:options items="${hobbies2}" itemLabel="code" itemValue="value" />--%>
<%--            </form:select>--%>
<%--            <form:select path="hobby">--%>
<%--                <option value=''>- 선택하세요 -</option>--%>
<%--                <form:options items="${hobbies}" />--%>
<%--            </form:select>--%>
<%--            <form:select path="hobby" items="${hobbies}" />--%>
<%--            ${requestJoin.hobby[0]}--%>
<%--            <form:checkboxes path="hobby" items="${hobbies}" />--%>
<%--        </dd>--%>
<%--    </dl>--%>
<%--    <dl>--%>
<%--        <dt>❤ 주소</dt>--%>
<%--        <dd>--%>
<%--            <form:input path="addr.zipCode" placeholder="우편번호" cssClass="input-txt" cssStyle="background: lightsalmon"/>--%>
<%--            <form:input path="addr.address" placeholder="주소" cssClass="input-txt" cssStyle="background: lightgreen"/>--%>
<%--            <form:input path="addr.addressSub" placeholder="나머지 주소" cssClass="input-txt" cssStyle="background: lightpink"/>--%>
<%--        </dd>--%>
<%--    </dl>--%>
    <dl>
        <dt><spring:message code="약관동의" /></dt>
        <dd>
            <form:checkbox path="agree" value="true" label="회원가입 약관에 동의합니다" />
        </dd>
    </dl>
    <button type="submit"><spring:message code="가입하기" /></button>
</form:form>