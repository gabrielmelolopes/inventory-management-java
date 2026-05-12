package repository;

import exception.NegocioException;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorySQL implements ProdutoRepository {
    private final Connection conn;

    public ProdutoRepositorySQL(Connection conn){
        this.conn = conn;
    }
    @Override
    public void salvar(Produto p) {
        String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }
    }

    @Override
    public void removerPorId(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Produto buscarPorId(int id) {
        String sql = "SELECT id, nome, preco, quantidade FROM produto WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new Produto(rs.getString(
                        "nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Produto prod = new Produto(
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"));
            lista.add(prod);
        }
        }catch (SQLException e){
            throw new RuntimeException("Erro: " + e.getMessage());
        }
        return lista;
    }
}