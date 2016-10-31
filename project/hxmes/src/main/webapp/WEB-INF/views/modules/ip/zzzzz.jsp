<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
    <html>  
        <head>  
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
            <title>RunJS</title>  
        <script id="jquery_183" type="text/javascript" class="library" src="/js/sandbox/jquery/jquery-1.8.3.min.js"></script>  
	  <script>
    $(function(){  
      
    $('#username').bind('input propertychange', function() {  
        $('#result').html($(this).val().length + ' characters');  
    });  
      
    })  
</script>
        </head>  
        <body>  
            <h1 >  
                实时监测input中值的变化  
            </h1>  
            <input type="text" id="username" autoComplete='off'>  
            <div id="result"></div>  
        </body>  
    </html>  