/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.controllers;

import devcaotics.entities.Professor;
import devcaotics.repository.ProfessorRepository;
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
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("codigo") != null) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));

            if (request.getParameter("op") != null) {

                if (request.getParameter("op").equals("edit")) {

                    Professor pEdit = ProfessorRepository.read(codigo);

                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Editar Professor</h1>");
                        out.println("<a href='ProfessorController'>Professores cadastrados</a><br/>");
                        out.println("<form method='post' action='ProfessorController?up=update'>");
                        out.println("Codigo: <input type='hidden' name='codigo' value='" + pEdit.getCodigo() + "'/>" + pEdit.getCodigo() + "</br>");
                        out.println("Nome: <input type='text' name='nome' value='" + pEdit.getNome() + "'/></br>");
                        out.println("e-mail: <input type='text' name='email' value='" + pEdit.getEmail() + "'/></br>");
                        out.println("<input type='submit' value='editar'/>");
                        out.println("</form>");
                        out.println("</body>");
                        out.println("</html>");

                        return;

                    }

                }
                if (request.getParameter("op").equals("delete")) {

                    ProfessorRepository.delete(codigo);

                    response.setContentType("text/html;charset=UTF-8");

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<a href='ProfessorController'>Professores Cadastrados</a>");
                        out.println("<h1>O Professor foi deletado com sucesso</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            Professor profe = ProfessorRepository.read(codigo);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Professores Cadastrados no Sistema</h1>");
                out.println("<a href='ProfessorController'>VOLTAR</a><br/>");

                out.println("<h5>Código:" + profe.getCodigo() + "</h5>");
                out.println("<h5>Nome:" + profe.getNome() + "</h5>");
                out.println("<h5>E-mail:" + profe.getEmail() + "</h5>");
                out.println("</tr>");

                out.println("</body>");
                out.println("</html>");

            }
            return;
        }

        List<Professor> professores = ProfessorRepository.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgiotaController</title>");
            out.println("<style>");
            out.println("body {");
            out.println("font-family: Arial, sans-serif;");
            out.println("margin: 20px;");
            out.println("}");
            out.println("h1 {");
            out.println("color: #007bff;");
            out.println("}");
            out.println("table {");
            out.println("border-collapse: collapse;");
            out.println("width: 80%;");
            out.println("margin: 20px auto;");
            out.println("}");
            out.println("th, td {");
            out.println("border: 1px solid #ccc;");
            out.println("padding: 8px;");
            out.println("text-align: left;");
            out.println("}");
            out.println("th {");
            out.println("background-color: #007bff;");
            out.println("color: white;");
            out.println("}");
            out.println("a {");
            out.println("text-decoration: none;");
            out.println("color: #007bff;");
            out.println("margin-right: 10px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Prfoessores cadastrados no sistema</h1>");
            out.println("<a href='index.html'>Home</a><br/>");
            out.println("<table>");
            out.println("<tr><th>Código</th><th>Nome</th><th>E-mail</th></tr>");

            for (Professor prof : professores) {
                out.println("<tr>");
                out.println("<td>" + prof.getCodigo() + "</td>");
                out.println("<td>" + prof.getNome() + "</td>");
                out.println("<td>" + prof.getEmail() + "</td>");
                out.println("<td><a href='ProfessorController?codigo=" + prof.getCodigo() + "'>Detalhar</a>"
                        + " <a href='ProfessorController?codigo=" + prof.getCodigo() + "&op=edit'>Editar</a>"
                        + " <a href='ProfessorController?codigo=" + prof.getCodigo() + "&op=delete'>Deletar</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        
        //Cria e preenche o objeto com os parametro recuperados acima
        Professor prof = new Professor();
        
        prof.setCodigo(codigo);
        prof.setNome(nome);
        prof.setEmail(email);
        prof.setSenha(senha);

        //Adiciona o objeto criado acima no repositorio
        if(request.getParameter("up") == null){
            ProfessorRepository.create(prof);
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
                out.println("<h1>O Professor " + nome + " foi cadastrado com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");
                
                return;
            }
        } else {
            
            Professor profCadastrado = ProfessorRepository.read(codigo);
            prof.setSenha(profCadastrado.getSenha());

            ProfessorRepository.update(prof);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Estudante</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='ProfessorController'>Professores Cadastrados</a>");
                out.println("<h1>O Estudante " + nome + " foi alterado</h1>");
                out.println("</body>");
                out.println("</html>");
            }     
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
