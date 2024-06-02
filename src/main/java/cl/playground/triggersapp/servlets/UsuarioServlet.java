package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.database.DatabaseConnection;
import cl.playground.triggersapp.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UsuarioServlet", value = "/users")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono")
                );
                listaUsuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error al conectar a la base de datos</h3>");
        }

        for (Usuario usuario : listaUsuarios) {
            out.println("<p>" + usuario + "</p>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");

        System.out.println("dni:" + dni);
        System.out.println("nombre:" + nombre);
        System.out.println("apellido:" + apellido);
        System.out.println("telefono:" + telefono);

    }
}
