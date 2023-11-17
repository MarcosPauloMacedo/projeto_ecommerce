package model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.database.Database;
import model.entity.Usuario;

public class UsuarioRepository {
    
    public Boolean create(Usuario usuario) {
        
        String query = "INSERT INTO usuarios (nome, email, senha, adm) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = Database.prepareStatement(query);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setLong(4, usuario.getAdm());

            stmt.execute();
            stmt.close();

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean delete(long Id) {

        String query = "DELETE FROM usuarios WHERE id = " + Id;
        return Database.executeUpdate(query);
    }

    public Boolean update(Usuario usuario) {

        String query = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, adm = ? WHERE id = ?";
        try {
            PreparedStatement stmt = Database.prepareStatement(query);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setLong(4, usuario.getAdm());
            stmt.setLong(5, usuario.getId());

            stmt.execute();
            stmt.close();

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getById(long Id) {

        String query = "SELECT * FROM usuarios WHERE id = " + Id;
        return Database.executeQuery(query);
    }

    public ResultSet getAll() {

        String query = "SELECT * FROM usuarios";
        return Database.executeQuery(query);
    }

    public ResultSet login(String email) {

        String query = "SELECT * FROM usuarios WHERE email = '" + email + "'";
        return Database.executeQuery(query);
    }

    public Boolean emailExists(String email) {

        String query = "SELECT * FROM usuarios WHERE email = '" + email + "'";
        ResultSet usuario = Database.executeQuery(query);

        try {
            return usuario.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
