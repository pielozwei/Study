<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
			//alert('${parentId}');
			menuThree(0);
			
			$(".flag_font1").remove();
		});
		function menuThree(mk){
			if(mk==1){
				$('#jbxx').show();
				$("#ywxx").hide();
				$("#fjxx").hide();
			}else if(mk==2){
				$('#ywxx').show();
				//$('#an').show();
				$("#jbxx").hide();
				$("#fjxx").hide();
			}else if(mk==3){
				$('#fjxx').show();
				//$('#an').show();
				$("#jbxx").hide();
				$("#ywxx").hide();
			}else{
				$("#jbxx").show();
				$("#ywxx").hide();
				$("#fjxx").hide();
				//$('#an').hide();
			}
			//$('#an').show();
		}
		
		 /* function confirm(){
			var t = $('#bm').val();
			if(null==t||t==""){
				alert("请输入仓库编码！");
				return false;
			}else{
				return true;
			}
		} */
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/cangku/ck/validation?val1='+val1,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data){
					var span=document.getElementById("span1");
					span.innerHTML="<font color='red'>"+data.msg+"</font>";
				}
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cangku/ck/list?id=${parentId}">仓库列表</a></li>
		<li class="active"><a href="${ctx}/cangku/ck/form?id=${ck.id}">仓库<shiro:hasPermission name="cangku:ck:edit">${not empty ck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cangku:ck:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保存</button>&nbsp;
	</ul><br/>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/cangku/ck/jbxx" target="ckbmdFrame">基本信息</a></li>
		<li><a href="${ctx}/cangku/ck/ywxx" target="ckbmdFrame">业务信息</a></li>
		<li><a href="${ctx}/cangku/ck/fjxx" target="ckbmdFrame">附加信息</a></li> --%>
		<li><a href="javascript:menuThree(1);"  target="">基本信息</a></li>
		<li><a href="javaScript:menuThree(2);"  target="">业务信息</a></li>
		<li><a href="javaScript:menuThree(3);"  target="">附加信息</a></li>
	</ul><br/>
	<%-- <div >
			<iframe id="ckbmdFrame" name="ckbmdFrame" src="${ctx}/cangku/ck/jbxx" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%" height="600px"></iframe>
	</div> --%>
	
	<form:form id="inputForm" modelAttribute="ck" action="${ctx}/cangku/ck/save" method="post" class="form-horizontal">
	<div id="jbxx" style="display:none;">
		<%-- <c:if test="${empty type&&type!='add'}"><form:hidden path="id"/></c:if>
		<input type="hidden" name="pId" value="${pId}" />
		<input type="hidden" name="type" value="${type}" />--%>
		<tags:message content="${message}"/> 
		<form:hidden path="id"/>
		<form:hidden path="sjjd" value="${parentId }"/>
		<div class="control-group">
			<label class="control-label" for="weight">编码：</label>
			<div class="controls">
				<form:input id="bm" path="ckbh" htmlEscape="false" maxlength="200" class="required" oninput="validation()"/>
				<font style="color: red;font-size: 150%">*</font>
				<span id="span1"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;联系人1：
				<form:input path="lxr1" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">名称：</label>
			<div class="controls">
				<form:input path="mc" htmlEscape="false" maxlength="200" class="required"/>
				<font style="color: red;font-size: 150%">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;联系电话1：
				<form:input path="lxdh1" htmlEscape="false" maxlength="200" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">简称：</label>
			<div class="controls">
				<form:input path="wllbjc" htmlEscape="false" maxlength="200" class="required"/>
				<font style="color: red;font-size: 150%">*</font>
				&nbsp;&nbsp;&nbsp;&nbsp;联系人2：
				<form:input path="lxr2" htmlEscape="false" maxlength="200" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">仓库状态：</label>
			<div class="controls">
				<form:select path="ckzt">
                    <form:option value="0" label="启用"/>
                    <form:option value="1" label="停用"/>
                    <form:option value="2" label="检修"/>
                </form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;联系电话2：
				<form:input path="lxdh2" htmlEscape="false" maxlength="200" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">值班移动电话：</label>
			<div class="controls">
				<form:input path="zbyddh" htmlEscape="false" maxlength="200" class="digits"/>
				&nbsp;&nbsp;&nbsp;&nbsp;联系人3：
				<form:input path="lxr3" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">邮编：</label>
			<div class="controls">
				<form:input path="yzbm" htmlEscape="false" maxlength="200" class="digits"/>
				&nbsp;&nbsp;&nbsp;&nbsp;联系电话3：
				<form:input path="lxdh3" htmlEscape="false" maxlength="200" class="digits"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" >邮编:</label>
			<div class="controls">
				<form:input path="yzbm" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="">是否全局性单位:</label>
			<div class="controls">
				<form:select path="sfqjxdw" class="required">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
                <font style="color: red;font-size: 150%">*</font>
                &nbsp;&nbsp;&nbsp;&nbsp;是否实体性单位
				<form:select path="sfstdw" class="required">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
                <font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="">是否实体性单位:</label>
			<div class="controls">
				<form:select path="sfstdw">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
			</div>
		</div> --%>
		
		<div class="control-group">
			<label class="control-label" for="">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
                &nbsp;&nbsp;&nbsp;&nbsp;显示顺序：
				<form:input path="xssx" htmlEscape="false" maxlength="200" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="company">上级仓库编码:</label>
			<div class="controls">
                <tags:treeselect id="company" name="sjjd.id" value="${ck.sjjd.id}" labelName="company.name" labelValue="${ck.sjjd.mc}"
					title="仓库编码" url="/cangku/ckbmd/treeData?type=1&id=${id}" cssClass="required"/>
				<font style="color: red;font-size: 150%">*</font>
			</div>
		</div>
		
	</div>
	<div id="ywxx" style="display:none;">
		<%-- <c:if test="${empty type&&type!='add'}"><form:hidden path="id"/></c:if>
		<input type="hidden" name="pId" value="${pId}" />
		<input type="hidden" name="type" value="${type}" />
		<tags:message content="${message}"/> --%>
		<div class="control-group">
			<label class="control-label" >仓库职能:</label>
			<div class="controls">
				<form:select path="ckzn">
                    <form:option value="0" label="中转仓库"/>
                    <form:option value="1" label="流通加工仓库"/>
                    <form:option value="2" label="存储仓库"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >仓库类型:</label>
			<div class="controls">
			    <form:select path="cklx">
                    <form:option value="0" label="库"/>
                    <form:option value="1" label="库区"/>
                    <form:option value="2" label="库层"/>
                    <form:option value="3" label="库位"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >仓库性质:</label>
			<div class="controls">
				<form:select path="ckxz">
                    <form:option value="0" label="自用仓库"/>
                    <form:option value="1" label="公用仓库"/>
                    <form:option value="2" label="保税仓库"/>
                    <form:option value="3" label="出口监管仓库"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >仓库建筑:</label>
			<div class="controls">
				<form:select path="ckjz">
                    <form:option value="0" label="平房库"/>
                    <form:option value="1" label="楼房库"/>
                    <form:option value="2" label="立体仓库"/>
                    <form:option value="3" label="底下仓库"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >仓库自动化程度:</label>
			<div class="controls">
				<form:select path="zdhcd">
                    <form:option value="0" label="普通仓库"/>
                    <form:option value="1" label="自动化仓库"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >面积:</label>
			<div class="controls">
				<form:input path="mj" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >体积:</label>
			<div class="controls">
				<form:input path="tj" htmlEscape="false" maxlength="200" />
			</div>
		</div>
	</div>
	<div id="fjxx" style="display:none;">
		<%-- <c:if test="${empty type&&type!='add'}"><form:hidden path="id"/></c:if>
		<input type="hidden" name="pId" value="${pId}" />
		<input type="hidden" name="type" value="${type}" />
		<tags:message content="${message}"/> --%>
		<div class="control-group">
			<label class="control-label" for="weight">网址：</label>
			<div class="controls">
				<form:input path="wz" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;行政主管领导名称：
				<form:input path="xzzgldmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">邮箱：</label>
			<div class="controls">
				<form:input path="yx" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;行政副管领导名称：
				<form:input path="xzfgldmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">地址：</label>
			<div class="controls">
				<form:input path="dz" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;党务主管领导名称：
				<form:input path="dwzgldmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">经度：</label>
			<div class="controls">
				<form:input path="jd" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;党务副管领导名称：
				<form:input path="dwfgldmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">纬度：</label>
			<div class="controls">
				<form:input path="wd" htmlEscape="false" maxlength="200" />
				&nbsp;&nbsp;&nbsp;&nbsp;是否有分址：
				<form:select path="sfyfz" class="input-small">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="">简介:</label>
			<div class="controls">
				<form:textarea path="jj" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
	</div>
		<%-- <div class="form-actions" id="an" style="display: none;">
			<shiro:hasPermission name="cangku:ck:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnSubmit" class="btn btn-primary" type="reset" value="清空"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</div> --%>
	</form:form>
	
</body>
</html>
