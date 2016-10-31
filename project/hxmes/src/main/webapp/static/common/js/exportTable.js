$(function(){
	var form2="<form id='form_export' action='#' method='post'><input type='hidden' name='currentPageUrl'/><input type='hidden' name='returnPageUrl' value='"
	+"'/><input type='hidden' name='filename' value='"
	+"'/><input type='hidden' name='tableText' value='"
	+"'/></form>";
	$(document.body).append(form2);
});
/**
 * 导出表--不要第一列
 * */
function exportTable(id,currentPageUrl,returnPageUrl,filename,basePath){
	 var objTable2=document.getElementById(id);
	 var objTable=$(objTable2).clone()[0];
	for(var i=0;i<objTable.rows.length;i++){
       for(var j=0;j<objTable.rows[i].cells.length;j++){
    	   if(j==0)$(objTable.rows[i].cells[j]).remove();
    	   $(objTable.rows[i].cells[j]).width("auto");
       }
    }  
	tableText = objTable.innerHTML;
	var url=basePath+"/common/export/";
	$("#form_export").attr("action",url);
	$("#form_export input[name='currentPageUrl']").val(currentPageUrl);
	$("#form_export input[name='returnPageUrl']").val(returnPageUrl);
	$("#form_export input[name='filename']").val(filename);
	$("#form_export input[name='tableText']").val(tableText);
	$("#form_export").submit();
}

/**
 * 导出表--不要第一列 和 最后一列
 * */
function exportTable2(id,currentPageUrl,returnPageUrl,filename,basePath){
	 var objTable2=document.getElementById(id);
	 var objTable=$(objTable2).clone()[0];
	for(var i=0;i<objTable.rows.length;i++){
      for(var j=0;j<objTable.rows[i].cells.length;j++){
   	   if(j==0)$(objTable.rows[i].cells[j]).remove();
   	   if(j==objTable.rows[i].cells.length-1)$(objTable.rows[i].cells[j]).remove();
   	   $(objTable.rows[i].cells[j]).width("auto");
      }
   }  
	tableText = objTable.innerHTML;
	var url=basePath+"/common/export/";
	$("#form_export").attr("action",url);
	$("#form_export input[name='currentPageUrl']").val(currentPageUrl);
	$("#form_export input[name='returnPageUrl']").val(returnPageUrl);
	$("#form_export input[name='filename']").val(filename);
	$("#form_export input[name='tableText']").val(tableText);
	$("#form_export").submit();
}

/**
 * 打印表格table
 * @param id table的id
 */
function  printTable(id){
	if($("#"+id).css('display')=='none'){
		$("#"+id).show();
	   	$("#"+id).jqprint();
	   	$("#"+id).hide();
	}else{
	   	$("#"+id).jqprint();
	}
}

/**
 * 打印表格table 可以跟上边的方法合写
 * @param obj dom元素table
 */
function  printTable(obj){
	if($(obj).css('display')=='none'){
		$(obj).show();
	   	$(obj).jqprint();
	   	$(obj).hide();
	}else{
	   	$(obj).jqprint();
	}
}