<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>供应商基本信息管理</title>
<meta name="decorator" content="default" />

<script>
	$(function() {
		$("[data-toggle='popover']").popover();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		//	$("#gysbm").focus();
		var code = '${provider.gysbm}';
		$("#inputForm").validate({
		rules : {
			gysbm : {
				remote : "${ctx}/ip/provider/checkGysbm?oldGysbm=" + code
			}
		},
		messages : {
			gysbm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li id="li_bmxx" class="active">
			<a onclick="displaytable('bmxx')">基本信息</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="provider" action="${ctx}/ip/provider/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />

		<table id="jbxx">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gysmc">供应商名称:</label>
						<div class="controls">
							<form:input path="gysmc" htmlEscape="false" maxlength="200" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xydj">信用等级:</label>
						<div class="controls">
							<form:select path="xydj">
								<form:options items="${fns:getDictList('d_xydj')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gysbm">供应商编码:</label>
						<div class="controls">
							<form:input path="gysbm" htmlEscape="false" maxlength="200" class="required" />
						</div>
					</div>

				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="zczj">注册资金:</label>
						<div class="controls">
							<form:input path="zczj" htmlEscape="false" maxlength="200" class="number" />
						</div>
					</div>

				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="cz">传真:</label>
						<div class="controls">
							<form:input path="cz" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="lxr">联系人:</label>
						<div class="controls">
							<form:input path="lxr" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="lxdh">联系电话:</label>
						<div class="controls">
							<form:input path="lxdh" htmlEscape="false" maxlength="200"/>
						</div>
					</div>

				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="jrsj">加入时间:</label>
						<div class="controls">
							<input id="jrsj" name="jrsj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
								value="<fmt:formatDate value="${provider.jrsj}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
						</div>
					</div>

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
				<td rowspan="4">
					<div class="control-group">
						<label class="control-label" for="bz">备注:</label>
						<div class="controls">
							<form:textarea path="bz" htmlEscape="false" rows="10" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>

					<div class="control-group">
						<label class="control-label" for="yzbm">邮政编码:</label>
						<div class="controls">
							<form:input path="yzbm" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="yhxx">供应商银行信息:</label>
						<div class="controls">
							<a data-toggle="modal" href="#yh" class="btn">登记</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for=xssx>显示顺序:</label>
						<div class="controls">
							<form:input path="xssx" htmlEscape="false" maxlength="30" class="required" />
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
								<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td rowspan="4">
					<div class="control-group">
						<label class="control-label" for="gysjs">供应商介绍:</label>
						<div class="controls">
							<form:textarea path="gysjs" htmlEscape="false" rows="10" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="frsb">法人代表:</label>
						<div class="controls">
							<form:input path="frdb" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="dlr">代理人:</label>
						<div class="controls">
							<form:input path="dlr" htmlEscape="false" maxlength="200"  />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="wzdz">网站地址:</label>
						<div class="controls">
							<form:input path="wzdz" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gyszt">状态:</label>
						<div class="controls">
							<form:select path="gyszt">
								<form:options items="${fns:getDictList('d_shiyong')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div id="yh" class="modal hide fade in" style="display: none; height: 70%" title="银行信息">
			<div class="control-group">
				<div style="text-align: center;">
					<h3>供应商银行信息</h3>
					<hr>
				</div>

				<label class="control-label" for="khyx">开户银行:</label>
				<div class="controls">
					<form:input path="khyx" htmlEscape="false" maxlength="200" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="yxzh">帐号:</label>
				<div class="controls">
					<form:input path="yxzh" htmlEscape="false" maxlength="200" class="digits" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="sh">税号:</label>
				<div class="controls">
					<form:input path="sh" htmlEscape="false" maxlength="200" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="yxlxr">联系人:</label>
				<div class="controls">
					<form:input path="yxlxr" htmlEscape="false" maxlength="200"  />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="yxdh">银行电话:</label>
				<div class="controls">
					<form:input path="yxdh" htmlEscape="false" maxlength="200" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="yxcz">传真:</label>
				<div class="controls">
					<form:input path="yxcz" htmlEscape="false" maxlength="200" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="khyxlx">银行类型:</label>
				<div class="controls">
					<form:input path="khyxlx" htmlEscape="false" maxlength="200" />
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">保存</a>
			</div>
		</div>

	</form:form>
</body>
</html>
