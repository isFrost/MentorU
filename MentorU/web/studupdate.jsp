<%-- 
    Document   : facsingup
    Created on : May 30, 2015, 7:20:58 AM
    Author     : isend_000
--%>


<!DOCTYPE html>
<%@page import="com.mysql.jdbc.jdbc2.optional.MysqlDataSource"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.sql.*" buffer="100kb"%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/site.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Редактировать Профиль</title>
</head>
<body>
    <div id="logo">
        Mentor
    </div>
    <div id="main-menu">
        <a href="student.jsp" title="Назад">Назад</a>  | 
        <a href="index.html" title="Выйти">Выйти</a>  | 
    </div>

<script language="javascript">
        function fu(form)
        {
            alert('Ваш запрос отправлен администратору');
        }
	function fun1(form)
	{   
            var l = form.pass.value.length;

            for(var i=0; i < l ; i++)
            {
		if(form.pass1.value.substr(i,1) != form.pass.value.substr(i,1) )
		{
			form.pass.value="";
			form.pass1.value="";
			alert('passwords Not Equal');
			break;
		}
            }
	}

        function fun2(form)
	{
            var l=form.phno.value.length;
            for(var i=0; i < l ; i++)
            {
		if( (form.phno.value.substr(i,1)>= 'a' && form.phno.value.substr(i,1)>= 'z'  ) ||(form.phno.value.substr(i,1)>= 'A' && form.phno.value.substr(i,1)>= 'Z'  ) )
		{
			form.pass.value="";
			form.pass1.value="";
			alert('invalid phone no.');
			break;
		}
            }
	}		
</script>

<form action="studupdate.jsp" method="post" >

    

<div id="admin-login">
    <br/>
    <table aling="center">    
	<tr>  
            <td>ФИO</td> 
            <td><input type="text" name="name" value=""></td>
        </tr>	        
	<tr>  
            <td>e-mail</td> 
            <td><input type="text" name="phno" value="" onblur="fun1(this.form)"></td>
        </tr>	
	<tr>
            <td>Группа</td>
            <td><select name="group"> type="text" name="subject" value="" onblur="fun2(this.form)">
                    <option name="test">Тестовая группа</option>>
                </select>
            </td>
        </tr>
	
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить" onclick="fu(this.form)" ></td>
        </tr>
    </table>
</div>
    <%
        
    %>
</form>
</body>
</html>

