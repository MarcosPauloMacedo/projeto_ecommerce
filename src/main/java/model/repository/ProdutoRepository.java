package model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.database.Database;
import model.entity.Produto;

public class ProdutoRepository {

    public Boolean create(Produto produto) {

        String query = "INSERT INTO produtos (nome, descricao, quantidade, preco, precoFinal, desconto, parcelas, imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = Database.prepareStatement(query);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getQuantidade());
            stmt.setString(4, produto.getPreco());
            stmt.setString(5, produto.getPrecoFinal());
            stmt.setString(6, produto.getDesconto());
            stmt.setString(7, produto.getParcelas());
            stmt.setBytes(8, produto.getImagem());

            stmt.execute();
            stmt.close();

            return true;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void delete(long Id) {

        String query = "DELETE FROM produtos WHERE id = " + Id;
        Database.executeUpdate(query);
    }

    public Boolean update(Produto produto) {

        String query = "UPDATE produtos SET nome = ?, descricao = ?, quantidade = ?, preco = ?, precoFinal = ?, desconto = ?, quantidade = ?, imagem = ? WHERE id = ?";

        try {
            PreparedStatement stmt = Database.prepareStatement(query);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getQuantidade());
            stmt.setString(4, produto.getPreco());
            stmt.setString(5, produto.getPrecoFinal());
            stmt.setString(6, produto.getDesconto());
            stmt.setString(7, produto.getQuantidade());
            stmt.setBytes(8, produto.getImagem());
            stmt.setLong(9, produto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public ResultSet getById(long Id) {

        String query = "SELECT * FROM produtos WHERE id = " + Id;
        return Database.executeQuery(query);
    }

    public ResultSet getAll() {

        String query = "SELECT * FROM produtos";
        return Database.executeQuery(query);
    }
}
