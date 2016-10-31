<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<script  src="${ctxStatic}/echarts/echarts.js"></script>
	<title>设备层次管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var flag=0;
		$(document).ready(function() {
			$("#ccmc").focus();
			$("#inputForm").validate();
			/* $("#ccbm").attr("readonly",true); */
		});
		//编码唯一校验
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/ip/equipmentField/validation?val1='+val1,
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
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = ec.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/equipmentField/?nodeId=${nodeId}">设备层次列表</a></li>
		<li class="active"><a href="${ctx}/ip/equipmentField/form?id=${equipmentField.id}&nodeId=${nodeId}">设备层次
		<shiro:hasPermission name="ip:equipmentField:edit">${not empty equipmentField.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:equipmentField:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod();">保存</button>&nbsp;
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="equipmentField" action="${ctx}/ip/equipmentField/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="ccbm">层次编码:</label>
			<div class="controls">
				<form:input path="ccbm" id="bm" htmlEscape="false" maxlength="200" class="required" oninput="validation();"/><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ccmc">层次名称:</label>
			<div class="controls">
				<form:input path="ccmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="SJCCID">父节点:</label>
			<div class="controls">
				<tags:treepreserve id="SJCCID" name="parent.id" cssClass="required" value="${equipmentField.parent.id}" labelName="parent.ccmc" labelValue="${equipmentField.parent.ccmc}"
					title="父节点" url="/ip/equipmentField/treeData" module="equipmentField" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" />&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cclx">层次类型:</label>
			<div class="controls">
				<form:select path="cclx" class="required">
					<form:options items="${fns:getDictList('d_sbcclx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<label class="control-label" for="cclx">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy" class="required">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:equipmentField:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
