<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共模型</title>
	<meta name="decorator" content="default"/>
<c:if test="${not empty message}">
<script type="text/javascript">
$(function(){
	var shebeiMenuFrame=$("#shebeiMenuFrame",parent.document);
	var src=shebeiMenuFrame.attr("src");
	shebeiMenuFrame.attr("src",src);
});
</script>
</c:if>
</head>
<body>
<tags:message content="${message}"/>
	请在左侧“工器具分类列表”中选择工器具分类。
</body>
</html>