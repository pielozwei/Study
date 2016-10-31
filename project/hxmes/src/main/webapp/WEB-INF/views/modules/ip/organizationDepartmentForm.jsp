<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织机构部门管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
		
		function displaytable(tablename)
		{
			var jbxx_traget=document.getElementById("jbxx");
			var bmxx_traget=document.getElementById("bmxx");
			var ywsx_traget=document.getElementById("ywsx");
			
			var li_jbxx_traget=document.getElementById("li_jbxx");
			var li_bmxx_traget=document.getElementById("li_bmxx");
			var li_ywsx_traget=document.getElementById("li_ywsx");
			
			if(tablename == "jbxx"){
				jbxx_traget.style.display="";
				bmxx_traget.style.display="none";
				ywsx_traget.style.display="none";
				li_jbxx_traget.className="active";
				li_bmxx_traget.className="";
				li_ywsx_traget.className="";
			}else if(tablename == "bmxx"){
				jbxx_traget.style.display="none";
				bmxx_traget.style.display="";
				ywsx_traget.style.display="none";
				li_jbxx_traget.className="";
				li_bmxx_traget.className="active";
				li_ywsx_traget.className="";
			}else if(tablename == "ywsx"){
				jbxx_traget.style.display="none";
				bmxx_traget.style.display="none";
				ywsx_traget.style.display="";
				li_jbxx_traget.className="";
				li_bmxx_traget.className="";
				li_ywsx_traget.className="active";
			}
			
		}
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/organizationDepartment/">组织机构部门列表</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>

	

	<ul class="nav nav-tabs">
		<li id="li_jbxx" class="active"><a onclick="displaytable('jbxx')">基本信息</a></li>
		<!-- <li id="li_bmxx"><a onclick="displaytable('bmxx')">编码信息</a></li> -->
		<li id="li_ywsx"><a onclick="displaytable('ywsx')">业务属性</a></li>
	</ul><br/>
	
	
	
	<form:form id="inputForm" modelAttribute="organizationDepartment" action="${ctx}/ip/organizationDepartment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<table id="jbxx">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="bmmc">部门名称:</label>
						<div class="controls">
							<form:input path="bmmc" htmlEscape="false" maxlength="200" class="required"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xzzgldmc">行政主管领导名称:</label>
						<div class="controls">
							<form:input path="xzzgldmc" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="bmbm">部门编码:</label>
						<div class="controls">
							<form:input path="bmbm" htmlEscape="false" maxlength="200" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xzfgldmc">行政副管领导名称:</label>
						<div class="controls">
							<form:input path="xzfgldmc" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="bmjc">部门简称:</label>
						<div class="controls">
							<form:input path="bmjc" htmlEscape="false" maxlength="200" class="required"/>
						</div>
						
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="dwzgldmc">党务主管领导名称:</label>
						<div class="controls">
							<form:input path="dwzgldmc" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="wz">网址:</label>
						<div class="controls">
							<form:input path="wz" htmlEscape="false" maxlength="200"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="dwfgldmc">党务副管领导名称:</label>
						<div class="controls">
							<form:input path="dwfgldmc" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="yx">邮箱:</label>
						<div class="controls">
							<form:input path="yx" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="lxdh">联系电话:</label>
						<div class="controls">
							<form:input path="lxdh" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="dz">地址:</label>
						<div class="controls">
							<form:input path="dz" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xssx">顺序排序:</label>
						<div class="controls">
							<form:input path="xssx" htmlEscape="false" maxlength="200" class="required"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="yb">邮编:</label>
						<div class="controls">
							<form:input path="yb" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="sfqy">是否启用:</label>
						<div class="controls">
							<form:select path="sfstxjg" class="input-xlarge required">
							  <option value ="0">否</option>  
							  <option value ="1" selected>是</option>    
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sjbmid">上级部门:</label>
						<div class="controls">
							<form:select path="sjbmid" class="input-xlarge">
								<option value="0">--请选择--</option>
								<c:forEach var="DeparmentList" items="${DeparmentList}" varStatus="s">
                                      <option value="${DeparmentList.sjbmid}" <c:if test="${DeparmentList.sjbmid eq organizationDepartment.sjbmid}">selected="selected"</c:if>>${DeparmentList.bmmc}</option>
                                </c:forEach>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="organization">所属机构:</label>
						<div class="controls">
							<form:select path="organization.id" class="input-xlarge">
								<option value="0">--请选择--</option>
								<c:forEach var="SjjgList" items="${SjjgList}" varStatus="s">
                                      <option value="${SjjgList.id}" <c:if test="${SjjgList.id eq organizationDepartment.organization.id}">selected="selected"</c:if>>${SjjgList.jgmc}</option>
                                </c:forEach>
                            </form:select>
						</div>
						
					</div>
				</td>
			</tr>
		</table>
		
		<table id="bmxx" style="display:none">
			
		</table>
		<table id="ywsx" style="display:none">
			<tr><td>
				<div class="control-group">
						<label class="control-label" for="sfqjxjg">是否全局性机构:</label>
						<div class="controls">
							<form:select path="sfqjxjg" class="input-xlarge">
							  <option value ="0">否</option>  
							  <option value ="1">是</option>    
							</form:select>
						</div>
					</div>
			</td></tr>
			<tr><td>
				<div class="control-group">
						<label class="control-label" for="sfstxjg">是否实体性机构:</label>
						<div class="controls">
							<form:select path="sfstxjg" class="input-xlarge">
							  <option value ="0">否</option>  
							  <option value ="1">是</option>    
							</form:select>
						</div>
					</div>
			</td></tr>
			<tr><td>
				<div class="control-group">
						<label class="control-label" for="ywlx">业务类型:</label>
						<div class="controls">
							<form:select path="ywlx">
								<form:options items="${fns:getDictList('d_ywlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td></tr>
			<tr><td>
				<div class="control-group">
						<label class="control-label" for="bmlx">部门类型:</label>
						<div class="controls">
							<form:select path="bmlx">
								<form:options items="${fns:getDictList('d_czlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
			</td></tr>
			
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="ip:organizationDepartment:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
