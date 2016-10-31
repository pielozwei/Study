<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
	function edit_confirm() {
		var herf = "${ctx}/ip/techniqueWorkcenter/delete?id=" + result;
		confirmx('当前页面未保存，是否保存？', herf);
		if (confirm("当前页面未保存，是否保存？")) {

		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/techniqueWorkcenter/">新增工艺工作中心</a>
		</li>
		<li class="pull-right">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#inputForm').submit();" />
				<input type='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />		
		</li>
	</ul>
	
	<br />

	<form:form id="inputForm" modelAttribute="techniqueWorkcenter"
		action="${ctx}/ip/techniqueWorkcenter/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div>
			基本信息
			<HR align=left width=200>

			<table>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label" for="gylx">工艺路线:</label>
							<div class="controls">
								<tags:treeselect id="gylx" name="technique.id" value="${techniqueWorkcenter.technique.id}" labelName="technique.mc"
								labelValue="${techniqueWorkcenter.technique.mc}" title="工序名称" url="/ip/technique/treeData" cssClass="required input-large"/>
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label" for="">工艺路线编码:</label>
							<div class="controls">
								<form:input id="gylxtag" path="" htmlEscape="false" maxlength="200"
									readOnly="readonly" class="required"/>
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<div class="control-group">
							<label class="control-label" for="">工作中心:</label>
							<div class="controls">
								<tags:treeselect id="ip1" name="workcenter.id" value="${techniqueWorkcenter.workcenter.id}" labelName="workcenter.gzzxmc" labelValue="${techniqueWorkcenter.workcenter.gzzxmc}"
								title="上级工作中心" url="/gzzx/gzzxwh/treeData?type=1" cssClass="required input-large"/>
							</div>
						</div>
					</td>

					<td>
						<div class="control-group">
							<label class="control-label" for="">工作中心编码:</label>
							<div class="controls">
								<form:input path="" htmlEscape="false" maxlength="200"
									class="required"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label" for="sfmrgzzx">是否默认工作中心:</label>
							<div class="controls">
								<form:select path="sfmrgzzx">
									<form:options items="${fns:getDictList('yes_no')}"
										itemLabel="label" itemValue="value" htmlEscape="false"
										cssClass="required" />
								</form:select>
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label" for="xssx">显示顺序:</label>
							<div class="controls">
								<form:input path="xssx" htmlEscape="false" maxlength="200"
									cssClass="required" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label" for="sfqy">是否启用:</label>
							<div class="controls">
								<form:select path="sfqy">
									<form:options items="${fns:getDictList('yes_no')}" 
									itemLabel="label"
										itemValue="value" htmlEscape="false" class="required" />
								</form:select>
							</div>
						</div>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="control-group">
							<label class="control-label" for="bz">备注:</label>
							<div class="controls">
								<form:textarea path="bz" htmlEscape="false" maxlength="300"
									class="" style="margin: 0px; width: 515px; height: 149px"/>
							</div>
						</div>
					</td>
				</tr>
			</table>

			
		</div>
		<HR align=center width=1140>
		<div>
		
			产能信息
			<HR align=left width=200>

			<table>
				<tr>
					<td>
						<div class="control-group">
							<%-- 数据字典中没有时间单位 ，添加了d_sjdd到数据字典--%>
							<label class="control-label" for="sjdw">时间单位:</label>
							<div class="controls">
								<form:select path="sjdw">
									<form:options items="${fns:getDictList('gzzx_cn_bzscsjdw')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label" for="bzzbsj">标准时间:</label>
							<div class="controls">
							 	<form:input path="bzzbsj" htmlEscape="false" maxlength="200" />
							</div>
							
						</div>
					</td>
				</tr>

				<tr>
					<td>
						<!-- <label class="control-label"></label> -->
						<div class="control-group">
							<label class="control-label" for="bzzbsj">准备时间:</label>
							<div class="controls">
								<form:input path="bzzbsj" htmlEscape="false" maxlength="200" />
							</div>
						</div>
					</td>
					<td><div class="control-group">
							<label class="control-label" for="bzpdsj">排队时间:</label>
							<div class="controls">
								<form:input path="bzpdsj" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td width="150"><div class="control-group">
							<label class="control-label" for="bzjgsj">生产时间:</label>
							<div class="controls">
								<form:input path="bzjgsj" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
					<td width="150"><div class="control-group">
							<label class="control-label" for="bzwlbysj">搬运时间:</label>
							<div class="controls">
								<form:input path="bzwlbysj" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<div class="control-group">
			<label class="control-label" for="cpqhsj">产品切换时间:</label>
			<div class="controls">
				<form:input path="cpqhsj" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div>
			<table>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="material.id">主产品:</label>
							<div class="controls">
								<tags:treeselect id="id" name="material.id" value="${techniqueWorkcenter.material.id}" labelName="material.wlmc"
									labelValue="${technique.material.wlmc}" title="产品编码" url="/ip/technique/wlbmtreeData"/>
							</div>
						</div></td>

					<td><div class="control-group">
							<label class="control-label" for="">主产品编号:</label>
							<div class="controls">
								<form:input path="" htmlEscape="false" maxlength="200"
									readOnly="readOnly" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="cpzll">产品质量率:</label>
							<div class="controls">
								<form:input path="cpzll" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
					<td><div class="control-group">
							<label class="control-label" for="">计量单位:</label>
							<!-- 没有这个字段-->
							<div class="controls">
								<form:select path="">
									<form:options items="${fns:getDictList('d_jldw')}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</div>
						</div></td>
				</tr>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="plzl">批量增量:</label>
							<div class="controls">
								<form:input path="plzl" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>

					<td><div class="control-group">
							<label class="control-label" for="pljgsj">批量间隔时间:</label>
							<div class="controls">
								<form:input path="pljgsj" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="zxscpl">最小批量:</label>
							<div class="controls">
								<form:input path="zxscpl" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
					<td><div class="control-group">
							<label class="control-label" for="zdscpl">最大批量:</label>
							<div class="controls">
								<form:input path="zdscpl" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="zjjscpl"> 经济批量 :</label>
							<div class="controls">
								<form:input path="zjjscpl" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
					<td><div class="control-group">
							<label class="control-label" for="ydpl"> 移动批量 :</label>
							<div class="controls">
								<form:input path="ydpl" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td><div class="control-group">
							<label class="control-label" for="dwcb">单位成本:</label>
							<div class="controls">
								<form:input path="dwcb" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
					<td><div class="control-group">
							<label class="control-label" for="dwjjgz">单位计件工资:</label>
							<div class="controls">
								<form:input path="dwjjgz" htmlEscape="false" maxlength="200" />
							</div>
						</div></td>
				</tr>
				
			</table>
		</div>
		

		
	</form:form>
</body>
</html>
