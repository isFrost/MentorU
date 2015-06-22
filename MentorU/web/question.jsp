<%-- 
    Document   : student
    Created on : Jun 16, 2015, 8:34:40 AM
    Author     : isend_000
--%>

<%@page import="entities.Subject"%>
<%@page import="entities.Video"%>
<%@page import="java.util.Iterator"%>
<%@page import="entities.Doc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="entities.Student"%>
<%@page import="utils.EntityManager"%>
<%@page import="controllers.StudentController"%>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="site.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Задать вопрос</title>
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
        <a href="answers.jsp" title="Ответы"> Ответ</a>  |
        <a href="about.jsp" title="About"> О программе</a>  |
        <a href="index.html" title="quit">Выйти</a>
    </div>
<form action="QuestionController" method="post">              
    
    <div class="profile-div">
        <br/><p align="center">Студент</p> 
        <img align="center" src="./img/prof_img.png" alt="Студент" height="128" width="128"/>
        
        <%
            
            //String id = request.getSession().getAttribute("id").toString();
            int i = 3;//Integer.parseInt(id);
            try{
                Student s = EntityManager.getStudent(i);
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
        <h3 aligin="center">Оставить вопрос</h3>   
        <br/>
        <hr/>
        <br/>
        <select name="subj" onselect="<% session.setAttribute("subj",request.getParameter("subj")); %>">
            <% 
                ArrayList<Subject> list = EntityManager.getSubjects();
                for (Subject s : list){
                    out.println("<option>" + s.getSubj_Name() + "</option>");
                }                                     
            %>
        </select>
        <br/>
        <br/>
        <textarea align="center" cols="30" rows="10" name="quest" id="quest"></textarea><br/><br/>
        <input type="submit" name="askButton" value="Оставить вопрос" onclick="QuestionController"/>
        <br/>
        <hr/>
        <br/>
        <br/>
        <br/>
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
