<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
성공
<ul>
  <li>id=${member.id}</li>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
<%--  이런것도 가능함, 그러나 더 불편--%>
<%--  <li<%=((member)request.getAttribute("member")).getAge()%></li>--%>
</ul>
<a href="/index.html">메인</a>
</body>
</html>