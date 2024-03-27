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
public class EstudanteController extends HttpServlet {


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
        
        if (request.getParameter("codigo") != null) {
            
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            
            if (request.getParameter("op") != null) {
                
                if (request.getParameter("op").equals("edit")) {
                    
                    Estudante eEdit = EstudanteRepository.read(codigo);
                    
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet AgiotaController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Editar Estudante</h1>");
                        out.println("<a href='EstudantesServlet'>Estudantes cadastrados</a><br/>");
                        out.println("<form method='post' action='EstudantesServlet?up=update'>");
                        out.println("Codigo: <input type='hidden' name='codigo' value='" + eEdit.getCodigo() + "'/>" + eEdit.getCodigo() + "</br>");
                        out.println("Nome: <input type='text' name='nome' value='" + eEdit.getNome() + "'/></br>");
                        out.println("e-mail: <input type='text' name='email' value='" + eEdit.getEmail() + "'/></br>");
                        out.println("Ano: <input type='text' name='anoEntrada' value='" + eEdit.getAnoEntrada() + "'/></br>");
                        out.println("<input type='submit' value='editar'/>");
                        out.println("</form>");
                        out.println("</body>");
                        out.println("</html>");

                        return;

                    }
                    
                }
                if (request.getParameter("op").equals("delete")) {
                    
                    EstudanteRepository.delete(codigo);

                    response.setContentType("text/html;charset=UTF-8");

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<a href='EstudantesServlet'>Estudantes Cadastrados</a>");
                        out.println("<h1>O Estudante foi deletado com sucesso</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            Estudante estu = EstudanteRepository.read(codigo);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Estudantes Cadastrados no Sistema</h1>");
                out.println("<a href='EstudantesServlet'>VOLTAR</a><br/>");

                out.println("<h5>Código:" + estu.getCodigo() + "</h5>");
                out.println("<h5>Nome do Cabueta:" + estu.getNome() + "</h5>");
                out.println("<h5>E-mail:" + estu.getEmail() + "</h5>");
                out.println("<h5>Ano:" + estu.getAnoEntrada() + "</h5>");

                out.println("</tr>");

                out.println("</body>");
                out.println("</html>");

            }
            return;
        }

        List<Estudante> estudantes = EstudanteRepository.readAll();

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
            out.println("<h1>Agiotas cadastrados no sistema</h1>");
            out.println("<a href='index.html'>Home</a><br/>");
            out.println("<table>");
            out.println("<tr><th>Código</th><th>Nome</th><th>E-mail</th><th>Ano de Entrada</th></tr>");

            for (Estudante est : estudantes) {
                out.println("<tr>");
                out.println("<td>" + est.getCodigo() + "</td>");
                out.println("<td>" + est.getNome() + "</td>");
                out.println("<td>" + est.getEmail() + "</td>");
                out.println("<td>" + est.getAnoEntrada() + "</td>");
                out.println("<td><a href='EstudantesServlet?codigo=" + est.getCodigo() + "'>Detalhar</a>"
                        + " <a href='EstudantesServlet?codigo=" + est.getCodigo() + "&op=edit'>Editar</a>"
                        + " <a href='EstudantesServlet?codigo=" + est.getCodigo() + "&op=delete'>Deletar</a></td>");
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
        if(request.getParameter("up") == null){
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
                
                return;
            }
        } else {
            
            Estudante estCadastrado = EstudanteRepository.read(codigo);
            est.setSenha(estCadastrado.getSenha());

            EstudanteRepository.update(est);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Estudante</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='EstudantesServlet'>Estudantes Cadastrados</a>");
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
