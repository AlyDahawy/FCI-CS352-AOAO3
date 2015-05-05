<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="/social/search" method="post">
  Name : <input type="text" name="n" /> <br>
  <input type="submit" value="search">
  </form>
  <br><br>
  <form action="/social/accept" method="post">
  username : <input type="text" name="nn" /> <br>
  <input type="submit" value="Add">
  </form>
<form action="/social/showmsg" method="post">
  
  <input type="submit" value="show messages">
  </form>
  <a href="/social/msgGroup/">send message in group  </a> <br>
  <a href="/social/createGroup/">create group conversation </a> <br>
    <a href="/social/sendmsg/">send message</a> <br>
  <a href="/social">Sign out</a> <br>
  <a href="/social/showrequest">show friend requests</a> <br>

</body>
</html>