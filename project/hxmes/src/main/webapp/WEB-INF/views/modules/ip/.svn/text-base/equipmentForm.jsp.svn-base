<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var flag=0;
	$(document).ready(function() {
		$("#ccbh").focus();
		$("#inputForm").validate();
	//	$("#sbbm").attr("readonly",true);
		
		$("#sbggxhBtn").click(function(){//弹出蒙版
			$("#sbggxhList").show();
		});
		$("#close").click(function(){//弹出蒙版
			$("#sbggxhList").hide();
		});
		$("#sbggxhList #sure").click(function(){//弹出蒙版
			var ck=$("#sbggxhList .zj:checked");
			if(ck.length<1){
				top.$.jBox.tip("必须选中一行数据");
				return;
			}
			var tds=ck.parent().parent().find("td");
			$("#bzpj").text("编码:"+$(tds[1]).text()+";名称:"+$(tds[2]).text()+";规格:"+$(tds[4]).text()+":型号:"+$(tds[5]).text());
			$("#equipmentModel").val(ck.val());
			$("#lbjmc").val($(tds[2]).text());
			$("#close").click();
		});
	});
	function validation(){
		var val1=$('#bm').val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : '${ctx}/ip/equipment/validation?val1='+val1,
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
		<li><a href="${ctx}/ip/equipment/list?nodeId=${nodeId}">基本信息列表</a></li>
		<li class="active"><a href="${ctx}/ip/equipment/form?id=${equipment.id}&nodeId=${nodeId}">基本信息
		<shiro:hasPermission name="ip:equipment:edit">${not empty equipment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:equipment:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod();">保存</button>&nbsp;
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="equipment" action="${ctx}/ip/equipment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipmentField.id" value="${nodeId}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="sbbm">设备编号:</label>
			<div class="controls">
				<form:input path="sbbm" id="bm" htmlEscape="false" maxlength="200" class="required" oninput="validation();"/><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="IP_SBGGXH_ID">设备规格:</label>
			<div class="controls">
				<span id="bzpj"><c:if test="${not empty equipment.id}">
					编码:${equipment.equipmentModel.sbggxhbm};名称:${equipment.equipmentModel.sbggxhmc};规格:${equipment.equipmentModel.sbgg};型号:${equipment.equipmentModel.sbxh}
				</c:if></span>
				<input id="sbggxhBtn" class="btn" type="button" value="选择对应的信息"/>
				<form:input path="equipmentModel" htmlEscape="false" maxlength="200" class="required" value="${equipment.equipmentModel.id}" style="width:0;margin:0;padding:0;border:0;"/>
			</div>
		</div>
		<div id="sbggxhList" style="overflow:auto;top:10%;display: none;border:1px solid #ddd;padding:2px;margin-bottom:10px;">
			<input id="close" class="btn" type="button" value="关闭" />
			<input id="sure" class="btn" type="button" value="确定" />
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead><tr>
					<th>选择</th>
					<th>设备规格型号编号</th>
					<th>设备规格型号名称</th>
					<th>简称</th>
					<th>规格</th>
					<th>型号</th>
					<th>功率</th>
					<th>技术性能描述</th>
					<th>所属分类</th>
					<th>备注</th>
				</tr></thead>
				<tbody>
				<c:forEach items="${listSbggxh}" var="sbggxh">
					<tr>
						<td><input type="radio" class="zj" name="zj" value="${sbggxh.id}" /></td>
						<td>${sbggxh.sbggxhbm}</td>
						<td>${sbggxh.sbggxhmc}</td>
						<td>${sbggxh.sbggxhjc}</td>
						<td>${sbggxh.sbgg}</td>
						<td>${sbggxh.sbxh}</td>
						<td>${sbggxh.gl}</td>
						<td>${sbggxh.jsxnms}</td>
						<td>${sbggxh.equipmentCategory.sblbmc}</td>
						<td>${sbggxh.remarks}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="name">计量单位:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="ccbh">出厂编号:</label>
			<div class="controls">
				<form:input path="ccbh" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ccbh">归属部门:</label>
			<div class="controls">
				<form:input path="sybm" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csccrq">出厂日期:</label>
			<div class="controls">
				<input id="csccrq" name="csccrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required"
			value="<fmt:formatDate value="${equipment.csccrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbsclx">设备生产类型:</label>
			<div class="controls">
                <form:select path="sbsclx" cssClass="required">
					<form:options items="${fns:getDictList('d_sbsclx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jzabcfl">价值ABC分类:</label>
			<div class="controls">
                <form:select path="jzabcfl" cssClass="required">
                    <form:options items="${fns:getDictList('d_abc')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbdj">设备等级:</label>
			<div class="controls">
                <form:select path="sbdj" cssClass="required">
                    <form:options items="${fns:getDictList('d_sbdj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		
		<%-- <div class="control-group">
			<label class="control-label" for="dqyxzt">当前运行状态:</label>
			<div class="controls">
                <form:select path="dqyxzt" cssClass="required">
                    <form:options items="${fns:getDictList('d_dqyxzt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dqjszt">当前技术状态:</label>
			<div class="controls">
                <form:select path="dqjszt" cssClass="required">
                    <form:options items="${fns:getDictList('d_dqjszt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dqjdzt">当前鉴定状态:</label>
			<div class="controls">
                <form:select path="dqjdzt" cssClass="required">
                    <form:options items="${fns:getDictList('d_dqjdzt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="hgxjdzt">合格性鉴定状态:</label>
			<div class="controls">
                <form:select path="hgxjdzt" >
                    <form:option value="0" label="合格"/>
                    <form:option value="1" label="不合格"/>
                </form:select>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label" for="smzqzt">生命周期状态:</label>
			<div class="controls">
               <form:select path="smzqzt">
                    <form:option value="0" label="采购"/>
                    <form:option value="1" label="生产"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="whxjdzt">完好性鉴定状态:</label>
			<div class="controls">
                <form:select path="whxjdzt" >
                    <form:option value="0" label="完好"/>
                    <form:option value="1" label="不完好"/>
                </form:select>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
                <form:select path="sfqy" cssClass="required">
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="azqywz">安装区域位置（层次）:</label>
			<div class="controls">
				<tags:treepreserve id="azqywz" name="" value="${equipmentField.parent.id}" labelName="azqywz" labelValue="${equipmentField.parent.ccmc}"
					title="父节点" url="/ip/equipmentField/treeData" module="equipmentField" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" />&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ly">来源:</label>
			<div class="controls">
                <form:select path="ly">
                    <form:options items="${fns:getDictList('d_sbly')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbyz">设备原值:</label>
			<div class="controls">
				<form:input path="sbyz" htmlEscape="false" maxlength="200" />&nbsp; 万元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="nzjl">折旧率:</label>
			<div class="controls">
				<form:input path="nzjl" htmlEscape="false" maxlength="200" />（%）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="synx">使用年限:</label>
			<div class="controls">
				<form:input path="synx" htmlEscape="false" maxlength="200" />（年）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lkrq">立卡日期:</label>
			<div class="controls">
				<input id="lkrq" name="lkrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${equipment.lkrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jbcrq">进厂日期:</label>
			<div class="controls">
				<input id="jbcrq" name="jbcrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${equipment.jbcrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="azrq">安装日期:</label>
			<div class="controls">
				<input id="azrq" name="azrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${equipment.azrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="tcrq">投产日期:</label>
			<div class="controls">
				<input id="tcrq" name="tcrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${equipment.tcrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bfrq">报废日期:</label>
			<div class="controls">
				<input id="bfrq" name="bfrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
			value="<fmt:formatDate value="${equipment.bfrq}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:equipment:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
