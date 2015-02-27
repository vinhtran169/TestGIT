<%@ page contentType="text/html;charset=Shift_JIS"
          import="javax.sql.*,javax.naming.*,java.sql.*" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World - JSP tutorial</title>
<title>Insert title here</title>
</head>
<body>
	<%= "Hello World!" %>
	<%
		 Context objCtx=new InitialContext();
		 DataSource objDs=(DataSource)objCtx.lookup("java:comp/env/jdbc/TestDB");
		 Connection db=objDs.getConnection();
		 Statement objSql=db.createStatement();

		db.close();
 %>
</body>
</html>