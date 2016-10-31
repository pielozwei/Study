<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>bom管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<div id="content" class="row-fluid">
		<input type="hidden" id="cur_wlbm" />
		<div id="left">
			<iframe id="bomMainFrame" name="bomMainFrame" src="${ctx}/ip/productBOMSheet/tree/?id=${productBOMSheet.id}" style="overflow: visible;" scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="bomforMainFrame" name="bomforMainFrame" src="${ctx}/ip/productBOMSheet/none" style="overflow: visible;border: none;"   width="100%"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		var leftWidth = "160"; // 左侧窗口大小
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			$("#bomMainFrame, #bomforMainFrame, #openClose").height(
					strs[0] - 5);
			$("#right").width(
					$("body").width() - $("#left").width()
							- $("#openClose").width() - 5);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>