<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>仓库管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function delList() {
		//获取勾选的条数
		var checked = new Array;
		var rows = document.getElementsByName("zj");
		var j = 0;
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].checked) {
				checked[j] = rows[i].value;
				j++;
			}
		}
		if (checked.length >= 1) {
			confirmx_tree('确认要删除所选的仓库吗？', "${ctx}/cangku/ck/deleteList?ids="
					+ checked, checked);
		} else {
			confirmx('请选择至少一行数据', window.location.href);
			return;
		}
	}

	// 确认对话框
	function confirmx_tree(mess, href, checked) {
		top.$.jBox.confirm(mess, '系统提示', function(v, h, f) {
			if (v == 'ok') {
				hasChildOrSb(checked, href);
			}
		}, {
			buttonsFocus : 1
		});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
		return false;
	}
	//有子节点或者有设备
	function hasChildOrSb(checked, href) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "${ctx}/cangku/ck/checkHasChild?ids=" + checked,
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data, textStatus) {
				if (data.flag == 0) {
					loading('正在提交，请稍等...');
					location = href;
				} else if (data.flag == 1) {
					//confirmx('您选择的仓库<'+data.mc+'>下面有子节点，请先删除子节点！', href);
					confirmx('您选择的仓库<'+data.mc+'>下面有子节点，请先删除子节点！',
							window.location.href);
				}
			}
		});
	}
	//将信息导出到excel表格中
	$(document).ready(function() {
		$("#btnExport").click(function() {
			/*submit: function (v, h, f) { return true; }, 
			点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，
			v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗
			口内容里的form表单键值 */
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "仓库数据", '${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
	});
	$(document).ready(function() {
		$("#printID").click(function() {
			$("#contentTable").printArea();
		});
	});

	function addf() {
		window.location.href = "${ctx}/cangku/ck/form?parentId=${parentId}";
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cangku/ck/list"}>仓库列表</a></li>
		<%-- <shiro:hasPermission name="cangku:ck:edit"><li><a href="${ctx}/cangku/ck/form?parentId=${parentId}">仓库添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="ck"
		action="${ctx}/cangku/ck/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="name" htmlEscape="false" maxlength="50" class="input-small" />
		<label>编号 ：</label>
		<form:input path="bh" htmlEscape="false" maxlength="50" class="input-small" />
		<label>职能 ：</label>
		<form:select path="zn" class="input-small">
			<form:option value="" label=""/>
            <form:option value="0" label="中转仓库"/>
            <form:option value="1" label="流通加工仓库"/>
            <form:option value="2" label="存储仓库"/>
        </form:select>
		<label>存储性质分类 ：</label>
		<form:select path="ccxzfl" class="input-small">
            <form:option value="" label=""/>
            <form:option value="0" label="自用仓库"/>
            <form:option value="1" label="公用仓库"/>
            <form:option value="2" label="保税仓库"/>
            <form:option value="3" label="出口监管仓库"/>
        </form:select>
		<label>建筑：</label>
		<form:select path="jz" class="input-small">
            <form:option value="" label=""/>
            <form:option value="0" label="平房库"/>
            <form:option value="1" label="楼房库"/>
            <form:option value="2" label="立体仓库"/>
            <form:option value="3" label="底下仓库"/>
        </form:select>
		<!-- <label>名称 ：</label> -->
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<div style="overflow: auto;">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th width="5%">选择</th>
					<th>仓库编码</th>
					<th>仓库名称</th>
					<th>仓库状态</th>
					<th>联系电话</th>
					<th>值班移动电话</th>
					<th>是否全局性单位</th>
					<th>是否实体单位</th>
					<th>仓库职能</th>
					<th>存储性质分类</th>
					<th>仓库建筑</th>
					<th>仓库自动化程度</th>
					<th>面积</th>
					<th>体积</th>
					<th>网站地址</th>
					<th>邮箱</th>
					<th>地址</th>
					<th>经度</th>
					<th>纬度</th>
					<th>行政主管领导名称</th>
					<th>行政副管领导名称</th>
					<th>简介</th>
					<%-- <shiro:hasPermission name="cangku:ck:edit">
						<th>操作</th>
					</shiro:hasPermission> --%>
				</tr>
			</thead>
			<tbody>
				</div>
				<c:forEach items="${page.list}" var="ck">
					<tr>
						<th><input type="checkbox" name="zj" value="${ck.id}" /></th>
						<th>${ck.ckbh}</th>
						<th><a href="${ctx}/cangku/ck/form?id=${ck.id}">${ck.mc}</a></th>
						<th>${fns:getDictLabel(ck.sfqy, 'ckgl_ckgl_sfqy', '无')}</th>
						<th>${ck.lxdh}</th>
						<th>${ck.zbyddh}</th>
						<th>${fns:getDictLabel(ck.sfqjxdw, 'ckgl_ckcx_sfqjxdw', '无')}</th>
						<th>${fns:getDictLabel(ck.sfstdw, 'ckgl_ckcx_sfstxdw', '无')}</th>
						<th>${fns:getDictLabel(ck.ckzn, 'ckgl_ckcx_ckzn', '无')}</th>
						<th>${fns:getDictLabel(ck.ckxz, 'ckgl_ckcx_ckxz', '无')}</th>
						<th>${fns:getDictLabel(ck.ckjz, 'ckgl_ckcx_ckjz', '无')}</th>
						<th>${fns:getDictLabel(ck.zdhcd, 'ckgl_ckcx_ckzdhcd', '无')}</th>
						<th>${ck.mj}</th>
						<th>${ck.tj}</th>
						<th>${ck.wz}</th>
						<th>${ck.yx}</th>
						<th>${ck.dz}</th>
						<th>${ck.jd}</th>
						<th>${ck.wd}</th>
						<th>${ck.xzzgldmc}</th>
						<th>${ck.xzfgldmc}</th>
						<th>${ck.jj}</th>
						<%-- <shiro:hasPermission name="cangku:ck:edit">
							<th><a href="${ctx}/cangku/ck/form?id=${ck.id}">修改</a> <a
								href="${ctx}/cangku/ck/delete?id=${ck.id}&parentId=${ck.sjjd.id}"
								onclick="return confirmx('确认要删除该仓库吗？', this.href)">删除</a></th>
						</shiro:hasPermission> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<button class="btn btn-primary" onclick="editList('zj','${ctx}/cangku/ck/form')">编辑</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
			<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/cangku/ck/updateStateList?ids=','0');">启用</button>
			<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/cangku/ck/updateStateList?ids=','1');">禁用</button>
		</div>
		<div class="pagination">${page}</div>
</body>
</html>
