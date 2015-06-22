<%-- 
    Document   : studentsignup
    Created on : May 30, 2015, 7:13:04 AM
    Author     : Vladimir Smolko
--%>

<%@page import="entities.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Question"%>
<%@page import="entities.Question"%>
<%@page import="utils.EntityManager"%>
<%@page import="entities.Tutor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <link href="site.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Преподаватель</title>
</head>
<body> 
    <div id="logo">
        Mentor
    </div>
    <div id="main-menu">    
        <a href="tutupdate.jsp" title="Редактировать профиль">Профиль</a>  |
        <a href="adddoc.jsp" title="Новый документ">Добавить материал</a>  |
        <a href="addvideos.jsp" title="Добавить видео"> Добавить видео </a>  |        
        <a href="tutor.jsp" title="Ответить"> Ответить</a>  |
        <a href="about.jsp" title="About"> О программе</a>  |
        <a href="index.html" title="quit">Выйти</a>
    </div>
<form action="AnswerController"  method="post" >    
    <div class="profile-div">
        <br/><p align="center">Преподаватель</p>          
        <img src="${pageContext.servletContext.contextPath}/img/tutor.jpg" alt="Преподаватель" height="128" width="128"/>
        
        <%                        
            int id = 0;
            Cookie[] cookies = request.getCookies();
            for(int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals("user"))
                    id = Integer.parseInt(cookies[i].getValue());
            }
            //int i = 5;//Integer.parseInt(id);
            try{
                Tutor t = EntityManager.getTutor(id);
                out.println("<p>" + t.getT_Name() +"<br/>");
                out.println("Кафедра: " + t.getT_Kaf() + "<br/>");
                out.println("<a href>" + t.getT_Mail() + "</a></p>");
            }
            catch (SQLException e){
            
            }
        %>
        
    </div>    
    <div class="feed">
        <h3 aligin="center">Вопросы</h3>   
        <select name="subj" onselect="<% session.setAttribute("subj",request.getParameter("subj")); %>">
            <% 
                ArrayList<Subject> list = EntityManager.getSubjects();
                for (Subject s : list){
                    out.println("<option name=\"" + s.getSubj_Id() + "\">" + s.getSubj_Name() + "</option>");                    
                }                                     
            %>
        </select>
        <br/><hr/>
        <%    
            int Subj_Id = 8;
            //String Subj_Name = "Английский язык";
            //Subj_Name = new String(request.getParameter("subj").getBytes("iso-8859-1"), "UTF-8");
            //if (Subj_Name != null)
            //{
                //Subj_Id = EntityManager.findSubjectByName(Subj_Name).getSubj_Id();
                ArrayList<Question> qList = EntityManager.getQuestionsBySubj(8);
            
                    //EntityManager.findSubjectByName(request.getParameter("subj")).getSubj_Id());
            for (Question q : qList){
                //out.println("<div class=\"message\">");
                out.println("<p class=\"message\">");
                out.println(q.getQ_Text() + "<br/>");
                out.println("<input type=\"text\" name=\"answer\"></>");
                out.println("<input type=\"submit\" name=\"button\" value=\"Отправить\"></>");
                out.println("<br/>");
                out.println("</p>");                
                out.println("<hr/>");
                //out.println("<div>");                
            //}
            }
            
        %>
    </div>
    <p id="quotation">
        <em>"We are made wise not by the recollection of our past but by the responsibility for our future."<br/>
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
    
</form>
</body>
</html>
