/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.model.entities.Agiota;
import br.edu.ifpe.recife.model.repositories.AgiotaRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ALUNO
 */
@WebServlet(name = "CadastroAgiotaServlet", 
        urlPatterns = {"/CadastroAgiotaServlet"})
public class CadastroAgiotaServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String codnome = request.getParameter("codnome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String descricao = request.getParameter("descricao");
        
        Agiota agi = new Agiota();
        
        agi.setCodigo(codigo);
        agi.setCodNome(codnome);
        agi.setSenha(senha);
        agi.setEmail(email);
        agi.setDescricao(descricao);
        
        AgiotaRepository.create(agi);
        
        response.setContentType("text/html;charset=UTF-8");
       
       try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroAgiotaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>home</a>");
            out.println("<h1>O Agiota "+codnome+" foi cadastrado com sucesso</h1>");
            out.println("</body>");
            out.println("</html>");
        }
       
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
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
