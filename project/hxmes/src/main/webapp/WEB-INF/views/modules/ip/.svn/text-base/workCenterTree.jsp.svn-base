<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备层次列表</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}<%--
		.ztree li span.button.level0, .ztree li a.level0 {display:none;height:0;}
		.ztree li ul.level0 {padding:0;background:none;}--%>
		.accordion-inner{padding:2px;}
	</style>
	<script type="text/javascript">
		$(function(){
			var setting = {view:{selectedMulti:false},data:{simpleData:{enable:true}},callback:{beforeAsync: function(){alert(22);return true;},onAsyncSuccess:function(){alert(11);},onAsyncError:function(){alert(33);}}};
			var zNodes=[
		            <c:forEach items="${gzzxList}" var="tpl">{id:'${tpl.id}', pId:'${not empty tpl.sjgzzxid?tpl.sjgzzxid.id:0}', name:"${tpl.gzzxmc}",
		            	url:"${ctx}/gzzx/${not empty module?module:'none'}${module=='gzzxbc'||module=='gzzxcn'?'/list?ipGzzxId':(module=='gzzxjg'?'/form?ipGzzxId':'/form?id')}=${tpl.id}", target:"gzzxMainFrame"},
		            </c:forEach>];
			for(var i=0; i<zNodes.length; i++) {
				// 移除父节点
				if (zNodes[i] && zNodes[i].id == 0){
					zNodes.splice(i, 1);
				/* // 并将没有关联关系的父节点，改为父节点
				var isExistParent = false;
				for(var j=0; j<zNodes.length; j++) {
					if (zNodes[i].pId == zNodes[j].id){
						isExistParent = true;
						break;
					}
				}
				if (!isExistParent){
					zNodes[i].pId = 0;
				} */
			}
		}
			// 初始化树结构
			var tree = $.fn.zTree.init($("#tree"), setting, zNodes);
			//ztree.selectNode(ztree.getNodeByParam("id",$("#cur_sbccid",parent.document).val(), null));
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
		   setTimeout(function(){
			   $(document).find("#tree a").each(function(i,e){
				var l=this.href.length;
				var start=(this.href+"").indexOf('?')+4;
				var id=this.href.substring(start,l);
				var pId=$("#cur_sbccid",parent.document).val();
				if(id==pId){$(this).attr("class",$(this).attr("class")+" curSelectedNode");}
				});
			  },1050);
		});
		$(window).resize(function(){
			wSize();
		});
		function wSize(){
			$(".ztree").width($(window).width()-16).height($(window).height()-62);
			$(".ztree").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
		}
		 $(document).ready(function () {
	        });
	</script>
</head>
<body>
	<div class="accordion-group">
	    <div class="accordion-heading">
	    	<a class="accordion-toggle">工作中心层次列表</a>
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