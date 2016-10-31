<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate();
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/testMain/">主子表列表</a></li>
		<li class="active"><a href="${ctx}/test/testMain/form?id=${testMain.id}">主子表<shiro:hasPermission name="test:testMain:edit">${not empty testMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="test:testMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="testMain" action="${ctx}/test/testMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:radiobuttons path="sex" items="${fns:getDictList('d_xb')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加入日期：</label>
			<div class="controls">
				<input name="inDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${testMain.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务数据子表：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="hide"></th>
							<th>备注信息</th>
							<th>业务主表ID</th>
							<th>名称</th>
							<shiro:hasPermission name="test:testMain:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
						</tr>
					</thead>
					<tbody id="testChildList">
					</tbody>
					<shiro:hasPermission name="test:testMain:edit"><tfoot>
						<tr><td colspan="5"><a href="javascript:" onclick="addRow('#testChildList', testChildRowIdx, testChildTpl);testChildRowIdx = testChildRowIdx + 1;" class="btn">新增</a></td></tr>
					</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="testChildTpl">//<!--
					<tr id="testChildList{{idx}}">
						<td class="hide">
							<input id="testChildList{{idx}}_id" name="testChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
							<input id="testChildList{{idx}}_delFlag" name="testChildList[{{idx}}].delFlag" type="hidden" value="0"/>
						</td>
						<td>
							<textarea id="testChildList{{idx}}_remarks" name="testChildList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
						</td>
						<td>
							<input id="testChildList{{idx}}_testMainId" name="testChildList[{{idx}}].testMainId" type="text" value="{{row.testMainId}}" maxlength="64" class="input-small "/>
						</td>
						<td>
							<input id="testChildList{{idx}}_name" name="testChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>
						</td>
						<shiro:hasPermission name="test:testMain:edit"><td class="text-center" width="10">
							{{#delBtn}}<span class="close" onclick="delRow(this, '#testChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
						</td></shiro:hasPermission>
					</tr>//-->
				</script>
				<script type="text/javascript">
					var testChildRowIdx = 0, testChildTpl = $("#testChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
					$(document).ready(function() {
						var data = ${fns:toJson(testMain.testChildList)};
						for (var i=0; i<data.length; i++){
							addRow('#testChildList', testChildRowIdx, testChildTpl, data[i]);
							testChildRowIdx = testChildRowIdx + 1;
						}
					});
				</script>
			</div>
		</div>		
		<div class="form-actions">
			<shiro:hasPermission name="test:testMain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
