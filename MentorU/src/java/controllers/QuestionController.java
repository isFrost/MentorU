/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
public class QuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            String quest = request.getParameter("quest");
            String select = request.getParameter("select");
            
            if (quest != null)
            {   
                HttpSession session = request.getSession();
                String Subj_Name= new String(request.getParameter("subj").getBytes("iso-8859-1"), "UTF-8");
                int S_Id = 0;
                int Subj_Id = EntityManager.findSubjectByName(Subj_Name).getSubj_Id();               
                Cookie[] cookies = request.getCookies();
                for(int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals("user"))
                    S_Id = Integer.parseInt(cookies[i].getValue());
            }
                if (quest != null){
                    EntityManager.updateQuestions(quest, S_Id, Subj_Id );
                    RequestDispatcher rd = request.getRequestDispatcher("/question.jsp");                
                    rd.include(request, response);
                }
                else{                    
                    RequestDispatcher rd = request.getRequestDispatcher("/question.jsp");                
                    rd.include(request, response);
                }
            }            
            
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
