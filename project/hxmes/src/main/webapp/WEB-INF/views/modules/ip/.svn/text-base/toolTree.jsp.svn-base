<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工器具分类列表</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
		.accordion-inner{padding:2px;}
	</style>
	<script type="text/javascript">
		$(function(){
			var setting = {view:{selectedMulti:false},data:{simpleData:{enable:true}}};
			var zNodes=[
		            <c:forEach items="${toolCategoryList}" var="tpl">{id:'${tpl.id}', pId:'${tpl.pId}', name:"${tpl.name}",
		            	url:"${ctx}/ip/${not empty module?module:'none'}/list?nodeId=${tpl.id}&_module=${_module}", target:"shebeiMainFrame"},
		            </c:forEach>];
			for(var i=0; i<zNodes.length; i++) {
				// 移除父节点
				if (zNodes[i] && zNodes[i].id == 0){
					zNodes.splice(i, 1);
			}
		}
			// 初始化树结构
			var tree = $.fn.zTree.init($("#tree"), setting, zNodes);
			// 展开第一级节点
			var nodes = tree.getNodesByParam("level", 0);
			for(var i=0; i<nodes.length; i++) {
				tree.expandNode(nodes[i], true, true, false);
			}
			// 展开第二级节点
			nodes = tree.getNodesByParam("level", 1);
			for(var i=0; i<nodes.length; i++) {
				tree.expandNode(nodes[i], true, true, false);
			}
			wSize();
		});
		$(window).resize(function(){
			wSize();
		});
		function wSize(){
			$(".ztree").width($(window).width()-16).height($(window).height()-62);
			$(".ztree").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
		}
	</script>
</head>
<body>
	<div class="accordion-group">
	    <div class="accordion-heading">
	    	<a class="accordion-toggle">工器具分类列表</a>
	    </div>
	    <div class="accordion-body">
			<div class="accordion-inner">
				<div id="tree" class="ztree"></div>
				<script type="text/javascript">
				</script>
			</div>
	    </div>
	</div>
</body>
</html>