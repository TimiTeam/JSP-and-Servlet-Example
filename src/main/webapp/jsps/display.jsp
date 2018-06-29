
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>All phones</title>
</head>
<body>

<h1 align="center"> Thank for registration </h1>
<h2 align="center"> Hello
<c:forEach items="${fn:split(str,' ')}" var="s">
 ${s}
</c:forEach>
</h2>
<% String str = (String)request.getAttribute("str");
%>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
url="jdbc:mysql://localhost:3306/timur_base"
user="root" password="root"/>
<sql:query var="rs" dataSource="${db}" >
SELECT * FROM phones
</sql:query>

<h3 align="center"></h3><b>
<c:forEach items="${rs.rows}" var="list">
    <br/>
    position - <c:out value="${list.id}"/> - model **<c:out value="${list.name}"/>** price: <c:out value="${list.price}"/>
    <br/>
</c:forEach>
</b>
</h3>

</body>
</html>
