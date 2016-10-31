<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var flag=0;
		$(document).ready(function() {
			$("#mc").focus();
			$("#inputForm").validate();
			/* $("#bm").attr("readonly",true); */
		});
		//编码唯一校验
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/ip/tool/validation?val1='+val1,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data){
					var span=document.getElementById("span1");
					span.innerHTML="<font color='red'>"+data.msg+"</font>";
					flag=data.flag;
				}
			})
		}
		function submitMethod(){
			//	alert(flag);
				if(flag==0){
					$('#inputForm').submit();
				}
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/tool/list?nodeId=${nodeId}">基本信息列表</a></li>
		<li class="active"><a href="${ctx}/ip/tool/form?id=${tool.id}&nodeId=${nodeId}">基本信息
		<shiro:hasPermission name="ip:tool:edit">${not empty tool.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:tool:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod()">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="tool" action="${ctx}/ip/tool/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="gzqjlbId.id" value="${nodeId}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="gzqjbm">工器具编码:</label>
			<div class="controls">
				<form:input path="gzqjbm" htmlEscape="false" maxlength="100" class="required" id="bm" oninput="validation()"/><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gzqjmc">工器具名称:</label>
			<div class="controls">
				<form:input path="gzqjmc" htmlEscape="false" maxlength="100" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gzqjjc">工器具简称:</label>
			<div class="controls">
				<form:input path="gzqjjc" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gg">工器具规格:</label>
			<div class="controls">
				<form:input path="gg" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xh">工器具型号:</label>
			<div class="controls">
				<form:input path="xh" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jldw">计量单位:</label>
			<div class="controls">
				<form:input path="" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="smzqzt">生命周期状态:</label>
			<div class="controls">
				<%-- <form:select path="smzqzt" class="required">
					<form:options items="${fns:getDictList('d_glzt')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
				<form:select path="smzqzt" class="required">
					<form:option value="0" label="在用" />
					<form:option value="1" label="封存" />
					<form:option value="2" label="报废" />
					<form:option value="3" label="送检" />
					<form:option value="4" label="备用" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="glzt">管理状态:</label>
			<div class="controls">
				<%-- <form:select path="glzt" class="required">
					<form:options items="${fns:getDictList('d_glzt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<form:select path="glzt" class="required">
					<form:option value="0" label="在用" />
					<form:option value="1" label="封存" />
					<form:option value="2" label="报废" />
					<form:option value="3" label="送检" />
					<form:option value="4" label="备用" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="tzlb">台账类别:</label>
			<div class="controls">
				<%-- <form:select path="tzlb" class="required">
					<form:options items="${fns:getDictList('d_glzt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<form:select path="tzlb" class="required">
					<form:option value="0" label="标准量具" />
					<form:option value="1" label="非标量具" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jllb">计量类别:</label>
			<div class="controls">
				<%-- <form:select path="jllb" class="required">
					<form:options items="${fns:getDictList('d_jllb')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<form:select path="jllb" class="required">
					<form:option value="0" label="长度" />
					<form:option value="1" label="压力" />
					<form:option value="2" label="温度" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="qrjg">确认间隔:</label>
			<div class="controls">
				<form:input path="qrjg" htmlEscape="false" maxlength="10" class="digits"/>天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gsbm">归属部门:</label>
			<div class="controls">
				<form:input path="gsbm" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="syr">使用人:</label>
			<div class="controls">
				<form:input path="syr" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zqd">准确度:</label>
			<div class="controls">
				<form:input path="zqd" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cld">测量点:</label>
			<div class="controls">
				<form:input path="cld" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="clfw">测量范围:</label>
			<div class="controls">
				<form:input path="clfw" htmlEscape="false" maxlength="30"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jdrq">检定日期:</label>
			<div class="controls">
				<input id="jdrq" name="jdrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${tool.jdrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jddw">检定单位:</label>
			<div class="controls">
				<form:input path="jddw" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="yxrq">有效日期:</label>
			<div class="controls">
				<input id="yxrq" name="yxrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${tool.yxrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jdjg">检定结果:</label>
			<div class="controls">
				<form:input path="jdjg" htmlEscape="false" maxlength="20" class="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ccbh">出厂编号:</label>
			<div class="controls" >
				<form:input path="ccbh" htmlEscape="false" maxlength="20" class="number required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sccj">生产厂家:</label>
			<div class="controls">
				<form:input path="sccj" htmlEscape="false" maxlength="20" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls" >
				<form:input path="xssx" htmlEscape="false" maxlength="20" class="number required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<%-- <form:select path="sfqy">
					<form:options items="${fns:getDictList('d_glzt')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				</form:select> --%>
				<form:select path="sfqy" class="required">
					<form:option value="0" label="是" />
					<form:option value="1" label="否" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:tool:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
