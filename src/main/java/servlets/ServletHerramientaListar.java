/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HerramientaController;

/**
 *
 * @author ANDRES
 */
/**
 * Servket implementation class ServerletUsuarioLogin
 *
 */
@WebServlet("/ServletHerramientaListar")
public class ServletHerramientaListar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see httpservlet#HttpServlet()
     */
    public ServletHerramientaListar() {
        super();
//TODO Auto-generated constructor stub
    }

    /**
     * @see Httpservlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HerramientaController herramienta = new HerramientaController();

        boolean ordenar = Boolean.parseBoolean(request.getParameter("ordenar"));
        String orden = request.getParameter("orden");

        String herramientaStr = herramienta.listar(ordenar, orden);
        response.setContentType("text/html:charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(herramientaStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
