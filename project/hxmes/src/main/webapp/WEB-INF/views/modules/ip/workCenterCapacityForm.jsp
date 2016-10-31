<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作中心产能配置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
		
		$(".flag_font1").remove();
	});
	function calculate(){
		//设计年生产量
		var cjbzsjnscl=$("#cjbzsjnscl").val();
		//实际年生产量
		var cjbzsjnscl2=$("#cjbzsjnscl2").val();
		//审定年生产量
		var cjbzsdnscl=$("#cjbzsdnscl").val();
		/*************计算季度**************/
		var sjjdscla=(cjbzsjnscl/4).toFixed(4);
		var sjjdscl2a=(cjbzsjnscl2/4).toFixed(4);
		var sdjdscla=(cjbzsdnscl/4).toFixed(4);
		$("#a1").val(sjjdscla);
		$("#a2").val(sjjdscl2a);
		$("#a3").val(sdjdscla);
		/*************计算月**************/
		var sjjdsclb=(cjbzsjnscl/12).toFixed(4);
		var sjjdscl2b=(cjbzsjnscl2/12).toFixed(4);
		var sdjdsclb=(cjbzsdnscl/12).toFixed(4);
		$("#a4").val(sjjdsclb);
		$("#b1").val(sjjdscl2b);
		$("#b2").val(sdjdsclb);
		/*************计算周**************/
		var sjjdsclc=(cjbzsjnscl/52).toFixed(4);
		var sjjdscl2c=(cjbzsjnscl2/52).toFixed(4);
		var sdjdsclc=(cjbzsdnscl/52).toFixed(4);
		$("#b3").val(sjjdsclc);
		$("#b4").val(sjjdscl2c);
		$("#c1").val(sdjdsclc);
		/*************计算季度**************/
		var sjjdscld=(cjbzsjnscl/365).toFixed(4);
		var sjjdscl2d=(cjbzsjnscl2/365).toFixed(4);
		var sdjdscld=(cjbzsdnscl/365).toFixed(4);
		$("#c2").val(sjjdscld);
		$("#c3").val(sjjdscl2d);
		$("#c4").val(sdjdscld);
		/******厂级设计与实际生成量比例*********/
		var cjpercent=((cjbzsjnscl/cjbzsjnscl2).toFixed(4)*100).toFixed(2)+"%";
		$('#cjbznsclbfb').val(cjpercent);
		/******中心设计与实际生成量比例*********/
		//设计年生成量
		var sjnscl=$('#sjnscl').val();
		//实际年生成量
		var sjnscl2=$('#sjnscl2').val();
		var zxpercent=((sjnscl/sjnscl2).toFixed(4)*100).toFixed(2)+"%";
	//	$('#nsclbfb').val(zxpercent);
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/gzzx/gzzxcn/">工作中心产能配置列表</a></li>
		<li class="active"><a
			href="${ctx}/gzzx/gzzxcn/form?id=${gzzxcn.id}">工作中心产能配置<shiro:hasPermission
					name="gzzx:gzzxcn:edit">${not empty gzzxcn.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="gzzx:gzzxcn:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保 存</button>
	</ul>
	<br />
	<div style="overflow: auto;">
		<form:form id="inputForm" modelAttribute="gzzxcn" action="${ctx}/gzzx/gzzxcn/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="gzzxId">工作中心编码:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input path="ipGzzxId.gzzxbm" value="${gzzxcn.ipGzzxId.gzzxbm }" htmlEscape="false" maxlength="200" class="input-medium" readonly="true"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >工作中心名称:&nbsp;&nbsp;</label><%-- <form:input path="ipGzzxId.gzzxbm" htmlEscape="false" maxlength="200" class="input-medium" /> --%>
			<tags:treeselect id="ip1" name="ipGzzxId.id" value="${gzzxcn.ipGzzxId.id}" labelName="" labelValue="${gzzxcn.ipGzzxId.gzzxmc}"
					title="上级工作中心" url="/gzzx/gzzxwh/treeData?type=1" cssClass="required input-small"/>
			<font style="color: red;font-size: 150%">*</font>
			</div>
			<%-- <form:hidden path="ipGzzxId.id"/> --%>
		</div>
		<div style="height: 50px;">
			<h5  style="margin-left:10px;">中心标准产品信息</h5>
			<HR width="80%" color=#987cb9 SIZE=4 />
		</div>
		<div class="control-group">
			<label class="control-label" for="bzcpId">标准产品编码:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input path="bzcpId" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%-- <label >工作中心ID:&nbsp;&nbsp;</label><tags:treeselect id="ip" name="gzzxId" value="${gzzxcn.gzzxId.gzzxmc}" labelName="company.name" labelValue="${gzzxcn.gzzxId.gzzxmc}"
					title="公司" url="/gzzx/gzzxwh/treeData?type=1" cssClass="required input-small"/> --%>
			<label >标准生产时间单位:&nbsp;&nbsp;</label>
					<form:select path="bzscsjdw" class="input-small">
                    	<form:option value="0" label="年"/>
                   	 	<form:option value="1" label="季"/>
                   	 	<form:option value="2" label="月"/>
                   	 	<form:option value="3" label="周"/>
                   	 	<form:option value="4" label="日"/>
                   	 	<form:option value="5" label="班"/>
                   	 	<form:option value="6" label="小时"/>
                   	 	<form:option value="7" label="分"/>
                   	 	
                	</form:select>
               </div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzscsj">标准生产时间:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input path="bzscsj" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >设计年生产量:&nbsp;&nbsp;</label><form:input id="sjnscl" path="sjnscl" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzscsj">审定年生产量:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input id="sdnscl" path="sdnscl" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >实际年生产量:&nbsp;&nbsp;</label><form:input id="sjnscl2" path="sjnscl2" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="nsclbfb">年生产量百分比:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input path="nsclbfb" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >生产效率因子:&nbsp;&nbsp;</label><form:input id="sjnscl2" path="" htmlEscape="false" maxlength="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhsjzq">计划时间周期:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input path="jhsjzq" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >计划时间单位:&nbsp;&nbsp;</label>
					<form:select path="jhsjdw" class="input-small">
                    	<form:option value="" label="时间单位"/>
                    	<form:option value="0" label="年"/>
                   	 	<form:option value="1" label="季"/>
                   	 	<form:option value="2" label="月"/>
                   	 	<form:option value="3" label="周"/>
                   	 	<form:option value="4" label="日"/>
                   	 	<form:option value="5" label="班"/>
                   	 	<form:option value="6" label="小时"/>
                   	 	<form:option value="7" label="分"/>
                   	 	
                	</form:select>
                </div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zxsq">执行时区:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input id="zxsq" path="zxsq" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >需求时区:&nbsp;&nbsp;</label><form:input id="xqsq" path="xqsq" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ghsq">规划时区:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input id="ghsq" path="ghsq" htmlEscape="false" maxlength="200" class="required input-medium" />
			<font style="color: red;font-size: 150%">*</font>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label >预测时区:&nbsp;&nbsp;</label><form:input id="ycsq" path="ycsq" htmlEscape="false" maxlength="200" class="required input-medium"/>
			<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sctqq">生产提前期:&nbsp;&nbsp;</label>
			<div class="controls">
			<form:input id="sctqq" path="sctqq" htmlEscape="false" maxlength="200" class="required input-medium" />
			<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<div style="height: 60px;margin-top: 20px;">
			<h5  style="margin-left: 10px;">厂级标准产品产能折算</h5>
			<!-- <button id="btnJs" class="btn pull-right btn-primary" onclick="calculate();" style="margin-right: 440px">产能折算</button> -->
			<input id="btnJs" class="btn pull-right btn-primary" type="button" value="产能折算" onclick="calculate();" style="margin-right: 440px"/>
			<HR width="80%" color=#987cb9 SIZE=4 />
		</div>
		
		<div class="control-group" style="margin-left: 50px">
			<tr>
				<th>
					<label >设计年生产量:&nbsp;&nbsp;</label><form:input id="cjbzsjnscl" path="cbpsjnscl" htmlEscape="false" maxlength="200" class=" input-mini"/>
					<label >实际年生产量:&nbsp;&nbsp;</label><form:input id="cjbzsjnscl2" path="cbpsjnscl2" htmlEscape="false" maxlength="200" class=" input-mini"/>
					<label >审定年生产量:&nbsp;&nbsp;</label><form:input id="cjbzsdnscl" path="cbpsdnscl" htmlEscape="false" maxlength="200" class=" input-mini"/>
					<label >生产量百分比:&nbsp;&nbsp;</label><form:input id="cjbznsclbfb" path="cbpclbfb" htmlEscape="false" maxlength="200" class=" input-mini" readonly="true"/>
				</th>
			</tr>
		</div>
		<div class="control-group" style="margin-left: 50px">
			<tr>
				<th>
					<label >设计季生产量:&nbsp;&nbsp;</label><form:input id="a1" path="cbpsjjscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >审定季生产量:&nbsp;&nbsp;</label><form:input id="a2" path="cbpsdjscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >实际季生产量:&nbsp;&nbsp;</label><form:input id="a3" path="cbpsjjscl2" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >设计月生产量:&nbsp;&nbsp;</label><form:input id="a4" path="cbpsjyscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
				</th>
			</tr>
		</div>
		<div class="control-group" style="margin-left: 50px">
			<tr>
				<th>
					<label >审定月生产量:&nbsp;&nbsp;</label><form:input id="b1" path="cbpsdyscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >实际月生产量:&nbsp;&nbsp;</label><form:input id="b2" path="cbpsjyscl2" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >设计周生产量:&nbsp;&nbsp;</label><form:input id="b3" path="cbpsjzscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >实际周生产量:&nbsp;&nbsp;</label><form:input id="b4" path="cbpsjzscl2" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
				</th>
			</tr>
		</div>
		<div class="control-group" style="margin-left: 50px">
			<tr>
				<th>
					<label >审定周生产量:&nbsp;&nbsp;</label><form:input id="c1" path="cbpsdzscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >设计日生产量:&nbsp;&nbsp;</label><form:input id="c2" path="cbpsjrscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >审定日生产量:&nbsp;&nbsp;</label><form:input id="c3" path="cbpsdrscl" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
					<label >实际日生产量:&nbsp;&nbsp;</label><form:input id="c4" path="cbpsjrscl2" htmlEscape="false" maxlength="200" class="required input-mini" disabled="true"/>
				</th>
			</tr>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="gzzx:gzzxcn:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			<!-- <input id="btnJs" class="btn" type="button" value="计算" onclick="calculate();"/> -->
		</div> --%>
	</form:form>
	</div>
</body>
</html>
