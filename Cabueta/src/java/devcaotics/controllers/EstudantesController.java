/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.controllers;

import devcaotics.entities.Estudante;
import devcaotics.repository.EstudanteRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diogo
 */
public class EstudantesController extends HttpServlet {


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
        if (request.getParameter("codigo") != null){
            
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            
            List<Estudante> estudantes = EstudanteRepository.readAll();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>EstudanteServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Agiotas cadastrados no sistema</h1>");
                out.println("<a href='index.html'>home</a><br/>");
                out.println("<table border=\"2\">");
                out.println("<tr><th>Código</th><th>"
                        + "nome</th><th>e-mail</th><th>anoEntrada</th>"
                        + "</tr>");

                for (Estudante est : estudantes) {
                    out.println("<tr>");
                    out.println("<td>" + est.getCodigo() + "</td>");
                    out.println("<td>" + est.getNome() + "</td>");
                    out.println("<td>" + est.getEmail() + "</td>");
                    out.println("<td>" + est.getAnoEntrada() + "</td>");
                    out.println("<td><a href='EstudanteController?codigo=" + est.getCodigo() + "'>detalhar</a>"
                            + "     <a href='EstudanteController?codigo=" + est.getCodigo() + "&op=edit'>editar</a>"
                            + "     <a href='EstudanteController?codigo=" + est.getCodigo() + "&op=delete'>deletar</a></td>");
                    out.println("</tr>");
                }

                out.println("</body>");
                out.println("</html>");
            }
            
            
        }
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
        //Cadastro de Estudante
        //Esquerda sao os parametros definidos no model
        //Direita sao parametros do HTML
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        int anoEntrada = Integer.parseInt(request.getParameter("anoEntrada"));

        
        //Cria e preenche o objeto com os parametro recuperados acima
        Estudante est = new Estudante();
        
        est.setCodigo(codigo);
        est.setNome(nome);
        est.setSenha(senha);
        est.setEmail(email);
        est.setAnoEntrada(anoEntrada);

        //Adiciona o objeto criado acima no repositorio
        EstudanteRepository.create(est);
        
        //Menssagem nao entendi bem como essa mensagem e retornada
        //Obs:.O return do metodo deve ser colocado no html no action do form
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Estudante</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>home</a>");
            out.println("<h1>O Estudante " + nome + " foi cadastrado com sucesso</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
