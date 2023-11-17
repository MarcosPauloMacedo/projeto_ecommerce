package model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import model.entity.Usuario;
import model.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();
    
    public Boolean create(String nome, String email, String senha, String adm) {

        if(!usuarioRepository.emailExists(email)){
            
            String hashSenha = generatePasswordHash(senha);
    
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(hashSenha);
            usuario.setAdm(Long.parseLong(adm));
    
            return usuarioRepository.create(usuario);
        }

        return false;
    }

    public List<Usuario> getAll() {

        ResultSet usuarios = usuarioRepository.getAll();
        List<Usuario> listUsuarios = new ArrayList<Usuario>();

        try {
            while(usuarios.next()) {

                Usuario usuario = new Usuario(
                    usuarios.getLong("id"),
                    usuarios.getString("nome"),
                    usuarios.getString("email"),
                    usuarios.getString("senha"),
                    usuarios.getLong("adm")
                );

                listUsuarios.add(usuario);
            }

            return listUsuarios;

        } catch (SQLException e) {   

            throw new RuntimeException(e);
        }
    }

    public Usuario getById(long id) {

        ResultSet usuario = usuarioRepository.getById(id);

        try {
            usuario.next();
            Usuario userFound = new Usuario(
                usuario.getLong("id"),
                usuario.getString("nome"),
                usuario.getString("email"),
                usuario.getString("senha"),
                usuario.getLong("adm")
            );

            return userFound;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public Boolean update(Long id, String nome, String email, String senha, Long adm) {
        
        Usuario usuario = new Usuario( id, nome, email, generatePasswordHash(senha), adm);
        return usuarioRepository.update(usuario);
    }

    public Boolean delete(Long id) {

        return usuarioRepository.delete(id);
    }

    public Usuario login(String email, String senha, HttpSession session) throws SQLException {

        Usuario loggedInUser = new Usuario();
        ResultSet userFound = usuarioRepository.login(email);
        userFound.next();

        if(checkPassword(senha, userFound.getString("senha"))) {

            setDataInUsuario(loggedInUser, userFound);
            createSession(session, loggedInUser);
        }

        return loggedInUser;
    }

    private String generatePasswordHash(String senha) {

        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    private Boolean checkPassword(String senha, String hashSenha) {

        return BCrypt.checkpw(senha, hashSenha);
    }

    private void setDataInUsuario(Usuario usuario, ResultSet usuarioFound) throws SQLException {

        usuario.setId(usuarioFound.getLong("id"));
        usuario.setNome(usuarioFound.getString("nome"));
        usuario.setEmail(usuarioFound.getString("email"));
        usuario.setSenha(usuarioFound.getString("senha"));
        usuario.setAdm(usuarioFound.getLong("adm"));
    }

    private void createSession(HttpSession session, Usuario usuario) {

        session.setAttribute("id", usuario.getId());
        session.setAttribute("nome", usuario.getNome());
        session.setAttribute("email", usuario.getEmail());
        session.setAttribute("adm", usuario.getAdm());
    }
}
