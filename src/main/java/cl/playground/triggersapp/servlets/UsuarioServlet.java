package cl.playground.triggersapp.servlets;

import cl.playground.triggersapp.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UsuarioServlet", value = "/users")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("232141", "andres", "sepulveda", "9 2312 2411"));
        listaUsuarios.add(new Usuario("5636346", "alejandro", "valenzuela", "9 4112 6444"));
        listaUsuarios.add(new Usuario("65755434", "felipe", "kessi", "9 3222 1222"));
        listaUsuarios.add(new Usuario("86776", "nelson", "nelcarca", "9 2333 4555"));
        listaUsuarios.add(new Usuario("42352", "rigoberto", "allirue", "9 1555 4333"));

        // Llamamos a la sesion que se genera por defecto cuando el usuario entra a la web.
        HttpSession miSesion = req.getSession();
        miSesion.setAttribute("listaUsuarios", listaUsuarios);

        resp.sendRedirect("mostrarUsuarios.jsp");
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
