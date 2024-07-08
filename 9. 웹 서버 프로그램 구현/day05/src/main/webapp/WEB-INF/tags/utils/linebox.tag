<%@ tag body-content="scriptless" pageEncoding="UTF-8"
trimDirectiveWhitespaces="true" %>
<%@ attribute name="color" %>
<div style="border: 2px double ${color}; margin: 10px; padding: 30px;">
<jsp:doBody />
</div>
