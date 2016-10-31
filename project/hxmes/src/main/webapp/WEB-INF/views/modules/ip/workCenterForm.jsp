<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心维护管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var flag=0;
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			
			$(".flag_font1").remove();
		});
		
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/gzzx/gzzxwh/validation?val1='+val1,
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
		<li><a href="${ctx}/gzzx/gzzxwh/">工作中心维护列表</a></li>
		<li class="active"><a href="${ctx}/gzzx/gzzxwh/form?id=${gzzxwh.id}">工作中心维护<shiro:hasPermission name="gzzx:gzzxwh:edit">${not empty gzzxwh.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzzx:gzzxwh:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod();">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="gzzxwh" action="${ctx}/gzzx/gzzxwh/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<%-- <tags:message content="${message}"/> --%>
		<div class="control-group">
			<label class="control-label" for="weight">工作中心编码：</label>
			<div class="controls">
				<form:input id="bm" path="gzzxbm" htmlEscape="false" maxlength="200" class="required"  oninput="validation();"/>
				<font style="color: red;font-size: 150%">*</font>
				<span id="span1"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;状态：
				<form:select path="gzzxzt" class="input-small">
                    <form:option value="0" label="启用"/>
                    <form:option value="1" label="停用"/>
                    <form:option value="2" label="检修"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">工作中心名称：</label>
			<div class="controls">
				<form:input path="gzzxmc" htmlEscape="false" maxlength="200" class="required"/>
				<font style="color: red;font-size: 150%">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;行政主管领导岗位名称：
				<form:input path="xzzgldgwmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">工作中心简称：</label>
			<div class="controls">
				<form:input path="gzzxjc" htmlEscape="false" maxlength="200" class="required"/>
				<font style="color: red;font-size: 150%">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;党务主管领导岗位名称：
				<form:input path="dwzgldgwmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">联系电话：</label>
			<div class="controls">
				<form:input path="lxdh" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;保密主管领导岗位名称：
				<form:input path="bmzgldgwmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">值班电话：</label>
			<div class="controls">
				<form:input path="zbyddh" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;安全主管领导岗位名称：
				<form:input path="aqzgldgwmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">网址：</label>
			<div class="controls">
				<form:input path="wz" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;是否有分址：
				<form:select path="sfyfz" class="input-small">
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">邮箱：</label>
			<div class="controls">
				<form:input path="yx" htmlEscape="false" maxlength="200"/>
				&nbsp;&nbsp;&nbsp;&nbsp;上级工作中心：
				 <tags:treeselect id="company" name="sjgzzxid.id" value="${gzzxwh.sjgzzxid.id}" labelName="company.name" labelValue="${gzzxwh.sjgzzxid.gzzxmc}"
					title="上级工作中心" url="/gzzx/gzzxwh/treeData?type=1&extLcl01=${gzzxwh.id}" cssClass="required" />
				<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">地址：</label>
			<div class="controls">
				<form:input path="dz" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;是否是全局性单位：
				<form:select path="sfqjxdw" class="input-small" >
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
                <font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">经度：</label>
			<div class="controls">
				<form:input path="jd" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;纬度：
				<form:input path="wd" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >是否是实体单位：</label>
			<div class="controls">
				<form:select path="sfsstdw" class="input" >
                   <%--  <form:option value="" label="请选择"/> --%>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
                <font style="color: red;font-size: 150%">*</font>
                &nbsp;&nbsp;&nbsp;&nbsp;班制：
               <%--  <tags:treeselect id="gzbz" name="gzbzId.id" value="${gzzxwh.gzbzId.id}" labelName="gzbz.name" labelValue="${gzzxwh.gzbzId.bzmc}"
					title="工作班制" url="/gzzx/gzzxwh/treeData1?type=1" cssClass="required"/> --%>
				 <form:select path="gzbzId" cssClass="required">
					<form:options items="" itemLabel="bzmc" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="">支持的排成方法：</label>
			<div class="controls">
				<form:checkbox path="sfzczxpc" value="0"/>正向排程&nbsp;&nbsp;
				<form:checkbox path="sfzcnxpc" value="1"/>逆向排程&nbsp;&nbsp;
				<form:checkbox path="sfzczhpc" value="2"/>综合排程&nbsp;&nbsp;
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="jj">简介：</label>
			<div class="controls">
				<form:textarea path="jj" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注：</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		
		<%-- <div class="form-actions">
			<shiro:hasPermission name="gzzx:gzzxwh:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
