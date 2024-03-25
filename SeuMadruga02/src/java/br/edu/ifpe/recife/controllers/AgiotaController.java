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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ALUNO
 */
public class AgiotaController extends HttpServlet {

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

        /*
            dentro deste if estão os codigos de detalhar, alterar e deletar
        */
        if (request.getParameter("codigo") != null) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            
            /*
                 dentro deste if está o código que apresenta o 
                 formulário de alteração
                */
            if (request.getParameter("op") != null) {

                if (request.getParameter("op").equals("edit")) {

                    Agiota aEdit = AgiotaRepository.read(codigo);

                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet AgiotaController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Editar Agiota</h1>");
                        out.println("<a href='AgiotaController'>ver agiotas cadastrados</a><br/>");
                        out.println("<form method='post' action='AgiotaController'>");
                        out.println("Codigo: <input type='hidden' name='codigo' value='" + aEdit.getCodigo() + "'/>" + aEdit.getCodigo() + "</br>");
                        out.println("Nome de Guerra: <input type='text' name='codNome' value='" + aEdit.getCodNome() + "'/></br>");
                        out.println("e-mail: <input type='text' name='email' value='" + aEdit.getEmail() + "'/></br>");
                        out.println("Descrição: <textarea name='descricao'>" + aEdit.getDescricao() + "</textarea></br>");
                        out.println("<input type='submit' value='editar'/>");
                        out.println("</form>");

                        out.println("</body>");
                        out.println("</html>");

                        return;

                    }

                }

                /*
                 dentro deste if está o código para realizar o delete de agiota
                */
                
                if (request.getParameter("op").equals("delete")) {

                    AgiotaRepository.delete(codigo);

                    response.setContentType("text/html;charset=UTF-8");

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet CadastroAgiotaServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<a href='AgiotaController'>Agiotas Cadastrados</a>");
                        out.println("<h1>O Agiota foi deletado com sucesso</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                }

            }
            
            /*
                O código a baixo apresenta a tabela com todos os 
                agiotas cadastrados. Este código é executado quando
                o AgiotaServlet é chamado sem parametros
            */

            Agiota agiot = AgiotaRepository.read(codigo);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AgiotaController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Agiota cadastrado no sistema</h1>");
                out.println("<a href='AgiotaController'>ver agiotas cadastrados</a><br/>");

                out.println("<h5>código:" + agiot.getCodigo() + "</h5>");
                out.println("<h5>Nome de Guerra:" + agiot.getCodNome() + "</h5>");
                out.println("<h5>e-mail:" + agiot.getEmail() + "</h5>");
                out.println("<h5>Descrição:" + agiot.getDescricao() + "</h5>");

                out.println("</tr>");

                out.println("</body>");
                out.println("</html>");

            }
            return;
        }

        List<Agiota> agiotas = AgiotaRepository.readAll();

        /*response.setContentType("application/pdf");
        response.getOutputStream().write(bytes);*/
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgiotaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agiotas cadastrados no sistema</h1>");
            out.println("<a href='index.html'>home</a><br/>");
            out.println("<table border=\"2\">");
            out.println("<tr><th>Código</th><th>"
                    + "Nome de Guerra</th><th>e-mail</th><th>operações</th>"
                    + "</tr>");

            for (Agiota agi : agiotas) {
                out.println("<tr>");
                out.println("<td>" + agi.getCodigo() + "</td>");
                out.println("<td>" + agi.getCodNome() + "</td>");
                out.println("<td>" + agi.getEmail() + "</td>");
                out.println("<td><a href='AgiotaController?codigo=" + agi.getCodigo() + "'>detalhar</a>"
                        + "     <a href='AgiotaController?codigo=" + agi.getCodigo() + "&op=edit'>editar</a>"
                        + " <a href='AgiotaController?codigo=" + agi.getCodigo() + "&op=delete'>deletar</a></td>");
                out.println("</tr>");
            }

            out.println("</body>");
            out.println("</html>");
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

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String codNome = request.getParameter("codNome");
        String email = request.getParameter("email");
        String descricao = request.getParameter("descricao");

        Agiota ag = new Agiota();

        ag.setCodigo(codigo);
        ag.setCodNome(codNome);
        ag.setEmail(email);
        ag.setDescricao(descricao);

        Agiota agCadastrado = AgiotaRepository.read(codigo);
        ag.setSenha(agCadastrado.getSenha());

        AgiotaRepository.update(ag);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroAgiotaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='AgiotaController'>Agiotas Cadastrados</a>");
            out.println("<h1>O Agiota " + codNome + " foi alterado com sucesso</h1>");
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
