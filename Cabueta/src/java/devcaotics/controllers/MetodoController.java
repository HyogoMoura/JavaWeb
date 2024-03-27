/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.controllers;

import devcaotics.entities.MetodoFila;
import devcaotics.repository.MetodoFilaRepository;
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
public class MetodoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("codigo") != null) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));

            if (request.getParameter("op") != null) {

                if (request.getParameter("op").equals("edit")) {

                    MetodoFila mEdit = MetodoFilaRepository.read(codigo);

                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Editar Metodo de Fila</h1>");
                        out.println("<a href='MetodoController'>Metodos de Fila cadastrados</a><br/>");
                        out.println("<form method='post' action='MetodoController?up=update'>");
                        out.println("Codigo: <input type='hidden' name='codigo' value='" + mEdit.getCodigo() + "'/>" + mEdit.getCodigo() + "</br>");
                        out.println("Descricao: <input type='text' name='nome' value='" + mEdit.getDescricaoCurta() + "'/></br>");
                        out.println("Detalhes: <input type='text' name='email' value='" + mEdit.getDescricaoLonga() + "'/></br>");
                        out.println("<input type='submit' value='editar'/>");
                        out.println("</form>");
                        out.println("</body>");
                        out.println("</html>");

                        return;

                    }

                }
                if (request.getParameter("op").equals("delete")) {

                    MetodoFilaRepository.delete(codigo);

                    response.setContentType("text/html;charset=UTF-8");

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<a href='MetodoController'>Metodo de Fila Cadastrados</a>");
                        out.println("<h1>O Metodo de fila foi deletado com sucesso</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            MetodoFila metd = MetodoFilaRepository.read(codigo);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Metodos de fila Cadastrados no Sistema</h1>");
                out.println("<a href='MetodoController'>VOLTAR</a><br/>");

                out.println("<h5>Código:" + metd.getCodigo() + "</h5>");
                out.println("<h5>Descricao:" + metd.getDescricaoCurta() + "</h5>");
                out.println("<h5>Detalhes:" + metd.getDescricaoLonga() + "</h5>");

                out.println("</tr>");

                out.println("</body>");
                out.println("</html>");

            }
            return;
        }

        List<MetodoFila> metodos = MetodoFilaRepository.readAll();

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
            out.println("<h1>Metodos cadastrados no sistema</h1>");
            out.println("<a href='index.html'>Home</a><br/>");
            out.println("<table>");
            out.println("<tr><th>Código</th><th>Nome</th><th>Descricao</th><th>Detalhes</th></tr>");

            for (MetodoFila mtd : metodos) {
                out.println("<tr>");
                out.println("<td>" + mtd.getCodigo() + "</td>");
                out.println("<td>" + mtd.getDescricaoCurta() + "</td>");
                out.println("<td>" + mtd.getDescricaoLonga() + "</td>");
                out.println("<td><a href='MetodoController?codigo=" + mtd.getCodigo() + "'>Detalhar</a>"
                        + " <a href='MetodoController?codigo=" + mtd.getCodigo() + "&op=edit'>Editar</a>"
                        + " <a href='MetodoController?codigo=" + mtd.getCodigo() + "&op=delete'>Deletar</a></td>");
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
        String descricaoCurta = request.getParameter("descricaoCurta");
        String descricaoLonga = request.getParameter("descricaoLonga");

        //Cria e preenche o objeto com os parametro recuperados acima
        MetodoFila met = new MetodoFila();

        met.setCodigo(codigo);
        met.setDescricaoCurta(descricaoCurta);
        met.setDescricaoLonga(descricaoLonga);

        //Adiciona o objeto criado acima no repositorio
        if (request.getParameter("up") == null) {
            MetodoFilaRepository.create(met);
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
                out.println("<h1>O Metodo de fila " + descricaoCurta + " foi cadastrado com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");

                return;
            }
        } else {

//            MetodoFila metCadastrado = MetodoFilaRepository.read(codigo);
//            est.setSenha(estCadastrado.getSenha());

            MetodoFilaRepository.update(met);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='MetodoRepository'>Metodos de Fila Cadastrados</a>");
                out.println("<h1>O Metodo " + descricaoCurta + " foi alterado</h1>");
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
