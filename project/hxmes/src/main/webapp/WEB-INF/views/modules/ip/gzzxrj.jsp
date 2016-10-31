<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作中心机构管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="gzzxjg"
		action="${ctx}/gzzx/gzzxjg/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<table>
			<tr>
				<td rowspan="2"><select multiple="multiple" id="sel1">
						<option>111a</option>
						<option>111c</option>
						<option>111d</option>
						<option>111f</option>
						<option>111s</option>
						<option>111h</option>
						<option>111g</option>
				</select></td>
				<td><table>
						<tr>
							<td><input type="button" value=">>" onclick="move(this);" />
							</td>
						</tr>
						<tr>
							<td><input type="button" value="<<" onclick=" move(this);" /></td>
						</tr>
					</table></td>
				<td><select multiple="multiple" id="sel2">
						<option>111</option>
						<option>111</option>
						<option>111</option>
						<option>111</option>
						<option>111</option>
						<option>111</option>
						<option>111</option>
				</select></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
