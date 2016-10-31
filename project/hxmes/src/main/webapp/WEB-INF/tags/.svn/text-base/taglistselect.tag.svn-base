<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据来源地址"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="选择行的展示名称字段"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="itemNames" type="java.lang.String" required="true" description="所需字段名称"%>
<%@ attribute name="itemCodes" type="java.lang.String" required="true" description="所需字段值"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="pageSize" type="java.lang.String" required="false" description="每页显示条数"%>
<%@ attribute name="width" type="java.lang.String" required="false" description="弹框的宽带"%>
<%@ attribute name="height" type="java.lang.String" required="false" description="弹框的高度"%>
<div class="input-append">
	<input id="${id}Id" name="${name}" type="hidden" value="${value}" />
	<input id="${labelName}" readonly="readonly" type="text" value="${labelValue}" maxlength="50" />
	<a id="${id}Button" href="javascript:" class="btn">
		<i class="icon-search"></i>
	</a>
</div>
<script type="text/javascript">
	$("#${id}Button").click(
			function() {
				 var pageSize = ${pageSize eq null ? "4" : pageSize};
				 var width = ${width eq null ? "500" : width};
				 var height = ${height eq null ? "300" : height};
				// 正常打开	
				top.$.jBox.open("iframe:${ctx}${url}?url="+'${url}'+"&itemNames=" + '${itemNames}' + "&itemCodes=" + '${itemCodes}' +"&pageSize=" + pageSize,
						"${title}查询选择", width, height, {
						buttons : {
						"确定" : "ok",
						"关闭" : true
						},
						submit : function(v, h, f) {
							console.log(h.find("iframe").contents().find("input[name='zj']:checked").parent().parent().find('td'));
							if (v == "ok") {
								debugger;
								//获取选中的行
								var checkedObj = h.find("iframe").contents().find("input[name='zj']:checked");
								//获取行的ID
								var checkedId = checkedObj.val();
								//获取行的数据
								var checkedData = checkedObj.parent().parent().find('td')
								var tdCodeStr = '${itemCodes}';
								var tdCodes = tdCodeStr.split(",");
								//id赋值
								$("#${id}Id").val(checkedId);
								//对应tdCodeStr循序赋值
								for (var i = 0; i < tdCodes.length; i++) {
									$("#" + tdCodes[i]).val($(checkedData[i+1]).text());
								}
							} else if (v == "clear") {
								$("#${id}Id").val('');
								$("#${labelName}").val('');
							}
						},
						loaded : function(h) {
							$(".jbox-content", top.document).css("overflow-y", "hidden");
						}
						});
			});
</script>
