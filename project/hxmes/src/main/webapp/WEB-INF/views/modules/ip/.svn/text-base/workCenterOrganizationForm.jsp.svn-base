<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>Droid</title>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<style type="text/css">
select {
	width: 150px;
	height: 210px;
	border: 3px solid;
	font-size: 20px;
	color: #909993;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var info = '${message}';
		//alert(info);
		if (info != null && info != '') {
			//alert(info);
			top.$.jBox.tip(info,'success');
		}
	}
	//得到指定数组指定元的位置
	function searchKeys(needle, haystack) {
		var result = [];
		for (i in haystack) {
			if (haystack[i] == needle) {
				result.push(i);
			}
		}
		return result;
	}

	//申明一个数组用来存放本次添加的设备id
	var a1 = new Array();
	//申明一个数组用来存放本次删除的设备id
	var a2 = new Array();
	//申明一个数组用来存放本次添加机构的id
	var a3 = new Array();
	//申明一个数组用来存放本次删除机构的id
	var a4 = new Array();
	var move = function(obj) {
		var sel1 = document.getElementById('sel1');
		var sel2 = document.getElementById('sel2');
		if (obj.value == '>>') {
			for (var i = 0; i < sel1.children.length; i++) {
				var x = sel1.children[i];
				if (x.selected) {
					sel2.appendChild(x);
					//alert(a2.indexOf(x.value));
					//a2.toString().indexOf(x.value);
					if (a2.indexOf(x.value) > -1) {
						var result = searchKeys(x.value, a2);
						a2.splice(result, 1);
					} else {
						a1.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		} else if (obj.value == '<<') {
			for (var i = 0; i < sel2.children.length; i++) {
				var x = sel2.children[i];
				if (x.selected) {
					sel1.appendChild(x);

					if (a1.indexOf(x.value) > -1) {
						var result = searchKeys(x.value, a1);
						a1.splice(result, 1);
					} else {
						a2.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		}
		/* alert(a1.length);
		alert(a2.length); */
	}

	var moveNew = function(obj) {
		//alert(1);
		var sel1New = document.getElementById('sel1New');
		var sel2New = document.getElementById('sel2New');
		if (obj.value == '>>') {
			for (var i = 0; i < sel1New.children.length; i++) {
				var x = sel1New.children[i];
				if (x.selected) {
					sel2New.appendChild(x);
					if (a4.indexOf(x.value) > -1) {
						var result = searchKeys(x.value, a4);
						a4.splice(result, 1);
					} else {
						a3.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		} else if (obj.value == '<<') {
			for (var i = 0; i < sel2New.children.length; i++) {
				var x = sel2New.children[i];
				if (x.selected) {
					sel1New.appendChild(x);
					if (a3.indexOf(x.value) > -1) {
						var result = searchKeys(x.value, a3);
						a3.splice(result, 1);
					} else {
						a4.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		}
	}
	function subm() {
		//将a1和a2数组转换为字符串
		var str1 = "";
		var str2 = "";
		var str3 = "";
		var str4 = "";
		for (var i = 0; i < a1.length; i++) {
			if (i == 0) {
				str1 = str1 + "" + a1[i];
			} else {
				str1 = str1 + "," + a1[i];
			}
		}
		for (var i = 0; i < a2.length; i++) {
			if (i == 0) {
				str2 = str2 + "" + a2[i];
			} else {
				str2 = str2 + "," + a2[i];
			}
		}
		for (var i = 0; i < a3.length; i++) {
			if (i == 0) {
				str3 = str3 + "" + a3[i];
			} else {
				str3 = str3 + "," + a3[i];
			}
		}
		for (var i = 0; i < a4.length; i++) {
			if (i == 0) {
				str4 = str4 + "" + a4[i];
			} else {
				str4 = str4 + "," + a4[i];
			}
		}
		//alert(str1);
		window.location.href = "${ctx}/gzzx/gzzxjg/saveSB?ids=" + str1
				+ "&ipGzzxId=${ipGzzxId}" + "&ids1=" + str2 + "&ids2=" + str3
				+ "&ids3=" + str4;
	}
</script>
</head>
<body>
	<div style="height: 60px;margin-top: 20px;">
			 <font>工作中心执行机构</font>
			<input id="btnJs" class="btn pull-right btn-primary" type="button" value="提交" onclick="subm();" style="width: 70px; height: 30px;float:right;margin-right: 50;margin-bottom: 10px;"/>
			<HR width="100%" color=#404040 SIZE=4 />
	</div>
	<div style="margin-left: 80px; margin-bottom: 20px; margin-top: 25px;">
		<label>工作中心编码:</label> <input type="text" value="${gzzx.gzzxbm }"
			style="width: 140px" readonly="true" /> &nbsp;&nbsp; <label>工作中心名称:</label>
		<input type="text" value="${gzzx.gzzxmc }" style="width: 140px" readonly="true" />
	</div>
	<div style="margin-left: 180px">
		<form:form id="inputForm" modelAttribute="gzzxjg"
			action="${ctx}/gzzx/gzzxjg/save" method="post"
			class="form-horizontal" path="id">
			<form:hidden path="id" />
			<table>
				<tr>
					<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
						size="3" color="white" style="margin-left: 4px;">所有机构:</font></td>
					<td></td>
					<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
						size="3" color="white" style="margin-left: 4px;">工作中心机构:</font></td>
				</tr>
				<tr>
					<td><select multiple="multiple" id="sel1New">
							<c:forEach items="${organizations }" var="each">
								<option value="${each.id }">${each.jgmc }</option>
							</c:forEach>
					</select></td>
					<td>
						<table>
							<tr>
								<td><input type="button" value=">>"
									onclick="moveNew(this);" /></td>
							</tr>
							<tr>
								<td><input type="button" value="<<" onclick=" moveNew(this);" /></td>
							</tr>
						</table>
					</td>
					<td><select multiple="multiple" id="sel2New">
							<c:forEach items="${gzzxjgs }" var="each">
								<option value="${each.id }">${each.zzjgbm.jgmc }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>

			<div style="margin-top: 45px;">
				<table>
					<tr>
						<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
							size="3" color="white" style="margin-left: 4px;">所有设备域:</font></td>
						<td></td>
						<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
							size="3" color="white" style="margin-left: 4px;">工作中心使用设备:</font></td>
					</tr>
					<tr>
						<td><select multiple="multiple" id="sel1">
								<c:forEach items="${sb }" var="each">
									<option value="${each.id }">${each.sbbm }</option>
								</c:forEach>
						</select></td>
						<td>
							<table>
								<tr>
									<td><input type="button" value=">>" onclick="move(this);" />
									</td>
								</tr>
								<tr>
									<td><input type="button" value="<<" onclick=" move(this);" /></td>
								</tr>
							</table>
						</td>
						<td><select multiple="multiple" id="sel2">
								<c:forEach items="${gzzxsbs }" var="each">
									<option value="${each.id }">${each.sbybm.sbbm }</option>
								</c:forEach>
						</select></td>
					</tr>
				</table>
				<%-- <div class="form-actions">
					<div style="height: 30px;"></div>
					<shiro:hasPermission name="gzzx:gzzxwh:edit">
						<input id="btnSubmit" type="button" value="保 存" onclick="subm();"
							style="width: 70px; height: 30px;" />&nbsp;
			</shiro:hasPermission>
					<input id="btnCancel" type="button" value="返 回"
						onclick="history.go(-1)" style="width: 70px; height: 30px;" />
				</div> --%>
		</form:form>


	</div>
</body>
</html>
