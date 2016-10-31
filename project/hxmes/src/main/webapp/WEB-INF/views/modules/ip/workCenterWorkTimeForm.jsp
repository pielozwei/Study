<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心班次配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
		//异步验证格式
		function validationFormat(){
			var val1=$('#kssj').val();
			var val2=$('#jssj').val();
			if(val1 !== null && val1 !==''){
				var pattern = /^(?:(?:0?|1)\d|2[0-3]):[0-5]\d$/;
				if(!pattern.test(val1)){
					var span=document.getElementById("span1");
					span.innerHTML="<font color='red'>  格式不正确(格式：**：** 如19:56)</font>";
				}else{
					var span=document.getElementById("span1");
					span.innerHTML="<font></font>";
				}
			}
			if(val2 !== null && val2 !==''){
				var pattern = /^(?:(?:0?|1)\d|2[0-3]):[0-5]\d$/;
				if(!pattern.test(val2)){
					var span2=document.getElementById("span2");
					span2.innerHTML="<font color='red'>  格式不正确(格式：**：** 如19:56)</font>";
				}else{
					var span2=document.getElementById("span2");
					span2.innerHTML="<font></font>";
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/gzzx/gzzxbc/">工作中心班次配置列表</a></li>
		<li class="active"><a href="${ctx}/gzzx/gzzxbc/form?gzbzId=${gzzxbc.gzbzId.id}">工作中心班次配置<shiro:hasPermission name="gzzx:gzzxbc:edit">${not empty gzzxbc.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzzx:gzzxbc:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="gzzxbc" action="${ctx}/gzzx/gzzxbc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<form:hidden path="gzbzId.id"/>
		<div class="control-group">
			<label class="control-label" for="bzmc">班制名:</label>
			<div class="controls">
				<form:input path="" htmlEscape="false" maxlength="200" class="required" value="${workShift.bzmc }" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">班次:</label>
			<div class="controls">
				<form:input path="bc" htmlEscape="false" maxlength="200" class="required input-mini"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">班名:</label>
			<div class="controls">
				<form:input path="bm" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">性质：</label>
			<div class="controls">
				<form:select path="xz" class="input-small">
                    <form:option value="0" label="正常班"/>
                    <form:option value="1" label="加班"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">班时:</label>
			<div class="controls">
				<form:input path="bs" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
				<label class="control-label">开始时间:</label>
				<div class="controls">
					<%-- <input id="weightDate" name="kssj" type="text" readonly="readonly" maxlength="20" class="required input-small Wdate"
					value="<fmt:formatDate value="${gzzxbc.kssj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> --%>
					<form:input path="kssj" id="kssj" htmlEscape="false" maxlength="200" class="required" onblur="validationFormat();"/><span id="span1"></span>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label">结束时间:</label>
				<div class="controls">
					<%-- <input id="jssj" name="jssj" type="text" readonly="readonly" maxlength="20" class="required input-small Wdate"
					value="<fmt:formatDate value="${gzzxbc.jssj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> --%>
					<form:input path="jssj" id="jssj" htmlEscape="false" maxlength="200" class="required" onblur="validationFormat();"/><span id="span2"></span>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ktbs">跨天标识:</label>
			<div class="controls">
				<form:input path="ktbs" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200" class="input-mini"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用：</label>
			<div class="controls">
				<form:select path="sfqy" class="input-small">
                    <form:option value="0" label="启用"/>
                    <form:option value="1" label="禁用"/>
                </form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="weight">工作中心ID：</label>
			<div class="controls">
				 <tags:treeselect id="ip" name="ipGzzxId" value="${gzzxbc.ipGzzxId.gzzxmc}" labelName="company.name" labelValue="${gzzxbc.ipGzzxId.gzzxmc}"
					title="公司" url="/gzzx/gzzxwh/treeData?type=1" cssClass="required input-small"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="bz">备注：</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="gzzx:gzzxbc:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
