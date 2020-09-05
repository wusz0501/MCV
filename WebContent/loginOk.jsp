<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
     <!-- 
                     在jsp界面中,可以使用 <% %>,在其中key编写java代码
      -->
     <%
         String name =  (String)session.getAttribute("name");
         // 说明没有经过正常的登录界面,
         if(name == null){        	 
        	 request.getRequestDispatcher("index.jsp").forward(request, response);
         }else{
     %>
           <h1>欢迎欢迎,热烈欢迎</h1> 	 
     <%    	 
         }
     %>	
</body>
</html>