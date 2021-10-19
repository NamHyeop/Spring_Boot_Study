<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: namhyeob
  Date: 2021/10/18
  Time: 11:23 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //1."<$"영역은 자바 코드를 선언하는 영역을 의미하는 문법이다.
    //2.requset와 response는 자동으로 등록을 해준다.

    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getAge()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
