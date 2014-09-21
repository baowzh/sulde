<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>上传图片</title>
<script type="text/javascript">  
        function checkFormat(filePath){   
            var i = filePath.lastIndexOf(".");   
            var len = filePath.length;   
            var str = filePath.substring(i+1,len);   
            var extName = "JPG,GIF,PNG,BMP";            
            if(extName.indexOf(str.toUpperCase()) < 0){   
                alert("图片格式不正确");   
                return false;   
            }   
            return true;                
        }   
           
        /*   
        图标预览，兼容ie，firefox   
        */   
        function fileChange(o){   
            if(checkFormat(o.value)){   
                if(window.ActiveXObject){                    
                   //  document.getElementById("uploadImg").width = 100;   
                   //  document.getElementById("uploadImg").height = 100; 
                    alert(o.value);  
                    document.getElementById("uploadImg").src = o.value;                    
                }else{   
                   //  document.getElementById("uploadImg").width = 100;   
                   //  document.getElementById("uploadImg").height = 100;   
                    document.getElementById("uploadImg").src = o.files[0].getAsDataURL();   
                     alert("eee");  
                }                     
            }   
        }   
  
       /* 给图片text附上图片地址*/   
       function finish(generatedId){                      
             window.returnValue="<c:out value="${imgurl}" />";
             window.close();  
     }
     function checkmess(){   
            //获得图片text的id 
	      <c:if test="${imgurl!=null}">
           finish("<c:out value="${imgurl}" />");
         </c:if>            
     }             
    </script>

</head>
<body onLoad="checkmess()">
<form id="uploadForm" name="uploadForm" method="post"
	action="imgupload.do" theme="simple" enctype="multipart/form-data"
	target="frame">
	<input type="hidden" id="infotypeid"
	name="infotypeid"> 
	<input type="hidden" id="txtUrlId"
	name="txtUrlId">
<table align="center">
	<tr>
		<td colspan="2"><input type="file" id="image" name="image"
			onchange="return fileChange(this)" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="上传" /></td>
		<td><input type="button" value="取消"
			onclick="javascript:window.close();" /></td>
	</tr>
</table>
<div align="center"><img src="" width="579" height="338"
	id="uploadImg" /></div>
</form>
<iframe name="frame" style="display: none" src="_self"> </iframe>
</body>
</html>
