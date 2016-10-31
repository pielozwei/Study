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
	var form="<form action='"
	+url+"' method='post'><input type='hidden' name='currentPageUrl' value='"
	+currentPageUrl+"'/><input type='hidden' name='returnPageUrl' value='"
	+returnPageUrl+"'/><input type='hidden' name='filename' value='"
	+filename+"'/><input type='hidden' name='tableText' value='"
	+tableText+"'/></form>";
	$(document.body).append(form);
	$(form).submit();
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
	var form="<form action='"
	+url+"' method='post'><input type='hidden' name='currentPageUrl' value='"
	+currentPageUrl+"'/><input type='hidden' name='returnPageUrl' value='"
	+returnPageUrl+"'/><input type='hidden' name='filename' value='"
	+filename+"'/><input type='hidden' name='tableText' value='"
	+tableText+"'/></form>";
	$(document.body).append(form);
	$(form).submit();
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
function  printTable(obj){
	if($(obj).css('display')=='none'){
		$(obj).show();
	   	$(obj).jqprint();
	   	$(obj).hide();
	}else{
	   	$(obj).jqprint();
	}
}