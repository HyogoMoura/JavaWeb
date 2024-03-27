/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devcaotics.controllers;

import devcaotics.entities.Cadeira;
import devcaotics.repository.CadeiraRepository;
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
public class CadeirasController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("codigo") != null) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));

            if (request.getParameter("op") != null) {

                if (request.getParameter("op").equals("edit")) {

                    Cadeira cEdit = CadeiraRepository.read(codigo);

                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet CadeiraController</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Editar Cadeira</h1>");
                        out.println("<a href='CadeirasController'>Estudantes cadastrados</a><br/>");
                        out.println("<form method='post' action='CadeirasController?up=update'>");
                        out.println("Codigo: <input type='hidden' name='codigo' value='" + cEdit.getCodigo() + "'/>" + cEdit.getCodigo() + "</br>");
                        out.println("Nome: <input type='text' name='nome' value='" + cEdit.getNome() + "'/></br>");
                        out.println("Ano: <input type='text' name='ano' value='" + cEdit.getAno() + "'/></br>");
                        out.println("Semestre: <input type='text' name='semestre' value='" + cEdit.getSemestre() + "'/></br>");
                        out.println("Descricao: <input type='text' name='descricao' value='" + cEdit.getDescricao() + "'/></br>");
                        out.println("<input type='submit' value='editar'/>");
                        out.println("</form>");
                        out.println("</body>");
                        out.println("</html>");

                        return;

                    }

                }
                if (request.getParameter("op").equals("delete")) {

                    CadeiraRepository.delete(codigo);

                    response.setContentType("text/html;charset=UTF-8");

                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<a href='CadeirasController'>Cadeiras Cadastrados</a>");
                        out.println("<h1>A Cadeira foi deletada com sucesso</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            Cadeira cade = CadeiraRepository.read(codigo);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Cadeiras Cadastrados no Sistema</h1>");
                out.println("<a href='CadeirasController'>VOLTAR</a><br/>");
                out.println("<h5>Código:" + cade.getCodigo() + "</h5>");
                out.println("<h5>Nome da Cadeira:" + cade.getNome() + "</h5>");
                out.println("<h5>Ano:" + cade.getAno() + "</h5>");
                out.println("<h5>Semestre:" + cade.getSemestre() + "</h5>");
                out.println("<h5>Descricao:" + cade.getDescricao() + "</h5>");
                out.println("</tr>");

                out.println("</body>");
                out.println("</html>");

            }
            return;
        }

        List<Cadeira> cadeiras = CadeiraRepository.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadeirasController</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("h1 { text-align: center; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println("a { text-decoration: none; color: #007bff; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cadeiras cadastradas no sistema</h1>");
            out.println("<a href='index.html'>home</a><br/>");
            out.println("<table>");
            out.println("<tr><th>Código</th><th>Nome</th><th>Ano</th><th>Semestre</th><th>Ações</th></tr>");

            for (Cadeira cad : cadeiras) {
                out.println("<tr>");
                out.println("<td>" + cad.getCodigo() + "</td>");
                out.println("<td>" + cad.getNome() + "</td>");
                out.println("<td>" + cad.getAno() + "</td>");
                out.println("<td>" + cad.getSemestre() + "</td>");
                out.println("<td><a href='CadeirasController?codigo=" + cad.getCodigo() + "'>detalhar</a>"
                        + "     <a href='CadeirasController?codigo=" + cad.getCodigo() + "&op=edit'>editar</a>"
                        + " <a href='CadeirasController?codigo=" + cad.getCodigo() + "&op=delete'>deletar</a></td>");
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
        int ano = Integer.parseInt(request.getParameter("ano"));
        int semestre = Integer.parseInt(request.getParameter("semestre"));
        String descricao = request.getParameter("descricao");

        //Cria e preenche o objeto com os parametro recuperados acima
        Cadeira cad = new Cadeira();

        cad.setCodigo(codigo);
        cad.setNome(nome);
        cad.setAno(ano);
        cad.setSemestre(semestre);
        cad.setDescricao(descricao);

        //Adiciona o objeto criado acima no repositorio
        if (request.getParameter("up") == null) {
            CadeiraRepository.create(cad);

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Estudante</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='index.html'>HOME</a>");
                out.println("<h1>A CAdeira: " + nome + " foi cadastrado com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");

                return;
            }
        } else {

            Cadeira cadCadastrado = CadeiraRepository.read(codigo);

            CadeiraRepository.update(cad);
            cad.setCodigo(cad.getCodigo());

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Estudante</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='CadeirasController'>Cadeira Cadastradas</a>");
                out.println("<h1>A Cadeira " + nome + " foi alterado</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
