package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Usuario;
import model.enuns.Perfil;
import model.service.UsuarioService;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet implements Controller {

    private UsuarioService usuarioService = new UsuarioService();
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private boolean profileAdm;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.req = req;
        this.resp = resp;

        Long profile = verifyProfile();
        profileAdm = Perfil.acesso(profile) == Perfil.ADMINISTRADOR;
        
        if(req.getParameter("id") != null && profileAdm) view();
        else if(req.getParameter("delete") != null && profileAdm) delete();
        else if(req.getParameter("edit") != null && profileAdm) formEdit();
        else if(req.getParameter("create") != null && profileAdm) formCreate();
        else if(req.getParameter("logout") != null) logout();
        else if(profileAdm) index();
        else{
            resp.sendRedirect("/ecommerce");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.req = req;
        this.resp = resp;

        try {
            if (req.getParameter("login") != null) login();
            if (req.getParameter("register") != null) create("/ecommerce");
            if (req.getParameter("id").isEmpty()) create("/ecommerce/usuarios");
            else update();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void index() throws ServletException, IOException {
        
        List<Usuario> usuarios = usuarioService.getAll();
        req.setAttribute("usuarios", usuarios);
        req.getRequestDispatcher("/usuarios/index.jsp").forward(req, resp);
    }

    private void view() throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Usuario usuario = usuarioService.getById(id);
        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("/usuarios/view.jsp").forward(req, resp);
    }

    private void create(String redirect) throws ServletException, IOException {
            
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String adm = req.getParameter("adm");

        usuarioService.create(nome, email, senha, adm);
        resp.sendRedirect(redirect);
    }

    private void update() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        Long adm = Long.parseLong(req.getParameter("adm"));

        usuarioService.update(id, nome, email, senha, adm);
        resp.sendRedirect("/ecommerce/usuarios");
    }

    private void delete() throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("delete"));
        usuarioService.delete(id);
        resp.sendRedirect("/ecommerce/usuarios");
    }

    private void login() throws ServletException, IOException, SQLException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        HttpSession session = req.getSession();
        Usuario usuario = usuarioService.login(email, senha, session);

        directUser(usuario.getAdm());
    }

    private void formEdit() throws ServletException, IOException {
        
        int id = Integer.parseInt(req.getParameter("edit"));
        Usuario editarUsuario = usuarioService.getById(id);
        req.setAttribute("editarUsuario", editarUsuario);
        req.getRequestDispatcher("/usuarios/form.jsp").forward(req, resp);
    }

    private void formCreate() throws ServletException, IOException {

        req.getRequestDispatcher("/usuarios/form.jsp").forward(req, resp);
    }

    private void directUser(Long adm) throws ServletException, IOException{

        if(Perfil.acesso(adm) == Perfil.ADMINISTRADOR) {
            resp.sendRedirect("/ecommerce/usuarios");
        }
        else if(Perfil.acesso(adm) == Perfil.CLIENTE) {
            resp.sendRedirect("/ecommerce/produtos/loja.jsp");
        }
        else{
            resp.sendRedirect("/ecommerce");
        }
    }

    private void logout() throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect("/ecommerce");
    }

    @Override
    public Long verifyProfile() {

        if(req.getSession().getAttribute("adm") != null) {
            return (long) req.getSession().getAttribute("adm");
        }
        else{
            return null;
        }
    }
}
