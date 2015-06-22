<%-- 
    Document   : student
    Created on : Jun 16, 2015, 8:34:40 AM
    Author     : isend_000
--%>

<%@page import="java.util.Iterator"%>
<%@page import="entities.Doc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="entities.Student"%>
<%@page import="utils.EntityManager"%>
<%@page import="controllers.StudentController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="site.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Студент</title>
</head>
<body>    
    <div id="logo">
        Mentor
    </div>
    <div id="main-menu">    
        <a href="studupdate.jsp" title="Редактировать профиль">Профиль</a>  |      
        <a href="student.jsp" title="Материалы"> Материалы</a>  |         
        <a href="video.jsp" title="Видео"> Видео </a>  |        
        <a href="question.jsp" title="Вопросы"> Задать вопрос </a>  |
        <a href="answers.jsp" title="Ответы"> Ответы</a>  |
        <a href="about.jsp" title="About"> О программе</a>  |
        <a href="index.html" title="quit">Выйти</a>
    </div>
<form action="student.jsp" method="post">              
    
    <div class="profile-div">
        <br/><p align="center">Студент</p> 
        <img src="./img/prof_img.png" alt="" width="128px" height="128px"/>
        
        <%
            int id = 0;
            Cookie[] cookies = request.getCookies();
            for(int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals("user"))
                    id = Integer.parseInt(cookies[i].getValue());
            }
            //int i = 3;//Integer.parseInt(id);
            try{
                Student s = EntityManager.getStudent(id);
                out.println("<p>" + s.getS_Name() +"<br/>");
                out.println( EntityManager.getGroup(s.getGr_Id()).getGr_Name() + "<br/>");
                out.println(s.getYear() + "<br/>");
                out.println("<a href>" + s.getS_Mail() + "</a></p>");
            }
            catch (SQLException e){
            
            }
        %>
        
        <p name="Group"><% out.println(""); %></p>
        <p name="Year"><% out.println(""); %></p>
        <p name="Mail"><% out.println(""); %></p>
    </div>    
    <div class="feed">
        <h3 aligin="center">Материалы</h3>     
        <%
            ArrayList<Doc> list = EntityManager.getDocs();
            for (Doc d : list){
                //out.println("<div class=\"message\">");
                out.println("<p class=\"message\">");
                out.println(d.getDoc_Name() + "<br/>");
                out.println("<a href=\"" + d.getDoc_Path() + "\"> Ссылка на документ</a>");
                out.println("<br/>");
                out.println("</p>");                
                out.println("<hr/>");
                //out.println("<div>");                
            }
            out.println("Новое сообщение<bt/> <a href=\"\">ссылка</a>");
        %>
    </div>
    <div id="q">
        <p id="quotation">
            <em>"We are made wise not by the recollection of our past but<br/> by the responsibility for our future."<br/>
            George Bernard Shaw</em>
        </p>
        <br/>
        <br/>
        <br/>
        <br/>
        <video width="320" height="240" controls>
            <source src="movie.mp4" type="video/mp4">
            <source src="movie.ogg" type="video/ogg">
            Your browser does not support the video tag.
        </video> 
    <br/>  
    </div>
</form>
</body>
</html>
