/**
 * 公共函数调用
 * @param operateId
 * @param href
 * @param state
 */

/*================启用和禁用=================*/
function stopStateList(operateId,href,state) {
	// 获取勾选的条数
	var checked = new Array;
	var rows = document.getElementsByName(operateId);
	var j = 0;
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].checked) {
			checked[j] = rows[i].value;
			j++;
		}
	}
	if (checked.length >= 1) {
		confirmx_tree1('确认要禁用所选的选项吗？', href
				+ checked+"&state="+state, checked);
	} else {
		confirmx('请选择至少一行数据', window.location.href);
		return;
	}
}

// 确认对话框
function confirmx_tree1(mess, href, checked) {
	top.$.jBox.confirm(mess, '系统提示', function(v, h, f) {
		if (v == 'ok') {
			loading('正在提交，请稍等...');
			location = href;
		}
	}, {
		buttonsFocus : 1
	});
	top.$('.jbox-body .jbox-icon').css('top', '55px');
	return false;
}

function editList(operateId,href) {
	//获取勾选的条数
	var checked = new Array;
	var rows = document.getElementsByName(operateId);
	var j = 0;
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].checked) {
			checked[j] = rows[i].value;
			j++;
		}
	}
	if (checked.length == 1) {
		editConfirm('确认要编辑所选的选项吗？', href
				+'?id='+checked[0], checked);
	} else if(checked.length == 0) {
		confirmx('请选择至少一行数据', window.location.href);
		return;
	} else{
		confirmx("只能选择一条进行编辑",window.location.href);
		return;
	}
}


function editConfirm(mess, href, checked) {
	top.$.jBox.confirm(mess, '系统提示', function(v, h, f) {
		if (v == 'ok') {
			location = href;
		}
	}, {
		buttonsFocus : 1
	});
	top.$('.jbox-body .jbox-icon').css('top', '55px');
	return false;
}

/**
 * 批量操作时，调用action
 * msg：提示信息
 * url：访问地址-->ps："${ctx}/ip/equipmentCategory/delete"
 * boxId：checkbox的id值
 * paramStr:访问地址所需参数，参数字符串以&开头,没有参数设置空字符串-->ps："&state=0&code=1"
 */
function batchFnc(msg, url,boxId,paramStr) {
	var checked = new Array;
	var rows = document.getElementsByName(boxId);
	var j = 0;
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].checked) {
			checked[j] = rows[i].value;
			j++;
		}
	}
	if (checked.length >= 1) {
		confirmx(msg, url +"?ids=" +checked+paramStr);
	} else {
		confirmx('请选择至少一行数据', window.location.href);
		return;
	}
}

