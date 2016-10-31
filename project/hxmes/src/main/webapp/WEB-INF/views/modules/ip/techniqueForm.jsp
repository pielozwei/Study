<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺路线基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var flag=0;
	var be1=null,be2=null,be3=null;
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
		$("#wlbmtag").val("${technique.material.id}");
		 be1=$("#bm").val();
		 be2=$("#mc").val();
		 be3=$("#jc").val();
		 be4=$("#wlbmtag").val();
		 be5=$("#sfzgy").val();
		
		 if(be2!=''&&be2!=null){
			
		 }
	});
	
	
	function setbm()
	{	
		var value=$("#zcpwl_idId").val();
		document.getElementById("bm").value = value; 
	}
	

	function validationid(id){
	
		var val=document.getElementById(id);
		var val1=val.value;
		
		var bbid=val.id;
		if(bbid=='wlbmtag'){
			bbid='IP_WLBM_ID';
		}
	
		if(val.value==be4&&$("#sfzgy").val()==be5){
			
			val1='';
		}
		if($("#sfzgy").val()=='0'&&val.id=='wlbmtag'){
			val1='';
		}
		
		val1 = encodeURI(val1); 
		val1 = encodeURI(val1); 
			
		$.ajax({
			type : "post",
			dataType : "json",
			url : '${ctx}/ip/technique/validation?val1='+bbid+'&val2='+val1,
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data){
				var span=document.getElementById(id+'1');
		
				if(data.flag==1){
				
				span.innerHTML="<font color='red'>"+val.alt+"</font>";
				}
				if(flag==0){
				 flag=data.flag;
				}
				 if(bbid=='IP_WLBM_ID'&&flag==0){
					 $("#jdlx").removeAttr("disabled");
				 $('#inputForm').submit();
				 }
				
			}
		})
	}
	
	function submitMethod(){
		
		
		if(be1!=$("#bm").val()){
		validationid('bm');}
		if(be2!=$("#mc").val()){
		validationid('mc');}
		if(be3!=$("#jc").val()){
		validationid('jc');}
		validationid('wlbmtag');
		flag=0;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/technique/form?id=${technique.id}">新增基本信息</a>
		</li>
		<li class="pull-right">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="submitMethod();" />
				<input type='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />		
		</li>
	</ul>
	<form:form id="inputForm" modelAttribute="technique" action="${ctx}/ip/technique/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:input path="sjgylxid" value="0" type="hidden"/>
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
						<form:input path="bm" htmlEscape="false" maxlength="100" class="required" alt="该工艺路线编码已存在"/><span id="bm1"></span>
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
						<form:input path="mc" id="mc" htmlEscape="false" maxlength="100" class="required" alt="该名称已存在"/><span id="mc1"></span>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="jc">简称:</label>
					<div class="controls">
						<form:input path="jc" htmlEscape="false"  maxlength="100" class="required" alt="该简称已存在"/><span id="jc1"></span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
						<label class="control-label" for="material.id">主产品:</label>
						<div class="controls">
							<tags:treeselect id="id" name="material.id" value="${technique.material.id}" labelName="material.wlmc"
								labelValue="${technique.material.wlmc}" title="产品编码" url="/ip/technique/wlbmtreeData" cssClass="required"/>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group">
						<label class="control-label" for="">产品编码:</label>
						<div class="controls">
			                <form:input path="" id="wlbmtag" name="IP_WLBM_ID" htmlEscape="false" readonly="true" maxlength="100"  alt="主产品唯一性错误" /><span id="wlbmtag1"></span>
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
							<form:select path="jdlx" disabled="true" class="required" >
								<form:options items="${fns:getDictList('d_gyjdlx')}" itemValue="value" itemLabel="label" htmlEscape="false"   />
							</form:select>
						</div>
					</div>
			</td>
			<td>
				<div class="control-group" >
						<label class="control-label" for="gysylx">工艺使用类型:</label>
						<div class="controls">
							<form:select path="gysylx" >
								<form:options items="${fns:getDictList('d_gysylx')}" itemValue="value" itemLabel="label" htmlEscape="false" />
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
