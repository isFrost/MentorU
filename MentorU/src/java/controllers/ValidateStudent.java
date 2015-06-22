/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.S_User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.EntityManager;

/**
 *
 * @author Vladimir Smolko
 */
@WebServlet(name = "ValidateStudent", urlPatterns = {"/ValidateStudent"})
public class ValidateStudent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String login = request.getParameter("login");
            String pass = request.getParameter("password"); 
            
            try {
                S_User user = EntityManager.getUser(login, pass);
                //id = user.getU_Id();
                if (user != null){
                HttpSession session = request.getSession();
                session.setAttribute("id", String.valueOf(user.getU_Id()));
                Cookie loginCookie = new Cookie("user",String.valueOf(user.getU_Id()));
                response.addCookie(loginCookie);
                response.sendRedirect("student.jsp");
                //RequestDispatcher rd = request.getRequestDispatcher("/student.jsp");
                //rd.forward(request, response);                
            }
            else{
                out.println("<font color='red'><b>Введен не верный пароль.</b></font>");
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
            }
            catch (Exception e){
                
            }          
            
        }
        catch (Exception e){
            
        }
    }
}
