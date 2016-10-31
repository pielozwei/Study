<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺路线基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			sel=document.getElementById("jdlx");
			
		});
		
		function setbm()
		{
			var value=$("#zcpwl_idId").val();
			document.getElementById("bm").value = value;
		}
		
		function changejdlx(i){
			var $example = $("#jdlx").select2();
			$example.val(i).trigger("change");
			
		

			//document.getElementById("jdlx").value = i;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/technique/form?id=${technique.id}">新增基本信息</a>
		</li>
		<li class="pull-right">
				<input type='button' class="btn btn-primary" value='新增工序组' onClick="changejdlx(1);" />
				<input type='button' class="btn btn-primary" value='新增工序' onClick="changejdlx(3);" />
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#inputForm').submit();" />
				<input type='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
				<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />		
		</li>
	</ul>
	<form:form id="inputForm" modelAttribute="technique" action="${ctx}/ip/technique/editsave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:input path="sjgylxid" value="${id}" type="hidden"/>
		<tags:message content="${message}"/>
		<br/>
		<p style="font-weight:bold">基本信息</p>
		<hr align="left" width="200">
		<table id="jbxx" >
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="bm">工艺路线编码:</label>
					<div class="controls">
						<form:input path="bm" htmlEscape="false" maxlength="100" class="required"/>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="xssx">显示顺序:</label>
					<div class="controls">
						<form:input path="xssx" htmlEscape="false"  maxlength="100" class="required"/>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="mc">名称:</label>
					<div class="controls">
						<form:input path="mc" htmlEscape="false" maxlength="100" class="required"/>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="jc">简称:</label>
					<div class="controls">
						<form:input path="jc" htmlEscape="false"  maxlength="100" class="required"/>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="material.id">主产品:</label>
						<div class="controls">
							<tags:treeselect id="zcpid" name="material.id" value="${technique.material.id}" labelName="material.wlmc"
								labelValue="${technique.material.wlmc}" title="产品编码" url="/ip/technique/wlbmtreeData" cssClass="required"/>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="">产品编码:</label>
						<div class="controls">
			                <form:input path="" id="gylxtag" name="gylxtag" htmlEscape="false" readonly="true" maxlength="100" class="required" />
						</div>
					</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="gylx">工艺类型:</label>
						<div class="controls">
							<form:select path="gylx">
								<form:options items="${fns:getDictList('d_gylx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="sfzgy">是否主工艺:</label>
						<div class="controls">
							<form:select path="sfzgy" class="required">
								<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="sfqy">是否启用:</label>
						<div class="controls">
							<form:select path="sfqy" class="required">
								<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="sftsgx">是否特殊工序:</label>
						<div class="controls">
							<form:select path="sftsgx">
								<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>   
						</div>
					</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="jdlx">节点类型:</label>
						<div class="controls">
							<form:select path="jdlx" id="jdlx" name="jdlx" class="required"  >
								<form:options items="${fns:getDictList('d_gyjdlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="gysylx">工艺使用类型:</label>
						<div class="controls">
							<form:select path="gysylx">
								<form:options items="${fns:getDictList('d_gysylx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td>
		</tr>
		</table>
		<br/>
		<p style="font-weight:bold">质量要求</p>
		<hr align="left" width="200">
		<table id="jbxx2" >
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="sfzlkzd">是否控制点:</label>
						<div class="controls">
							<form:select path="sfzlkzd">
								<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>    
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="yhkzdlx">用户控制点类型:</label>
						<div class="controls">
							<form:select path="yhkzdlx">
								<form:options items="${fns:getDictList('d_yhkzdlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>  
						</div>
					</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="cnkzdlx">厂内控制点类型:</label>
						<div class="controls">
							<form:select path="cnkzdlx">
								<form:options items="${fns:getDictList('d_cnkzdlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select> 
						</div>
					</div>
			</td>
		</tr>
				</table>

				<div class="control-group">
						<label class="control-label"  for="bz">备注:</label>
						<div class="controls">
							<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
						</div>
					</div>
	</form:form>
<script type="text/javascript">
var cpvalue = document.getElementById("zcpwl_idName");
cpvalue.onchange = function(){
	alert(1);
}
</script>
</body>
</html>
