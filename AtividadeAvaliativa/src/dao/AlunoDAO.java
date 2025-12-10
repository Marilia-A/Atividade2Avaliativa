package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Aluno;

public class AlunoDAO {

    public boolean salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (id, matricula, nome, endereco, telefone, email, nome_pai, nome_mae) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getId());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getNome());
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getTelefone());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getNomePai());
            stmt.setString(8, aluno.getNomeMae());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Aluno aluno) {
        String sql = "UPDATE aluno SET matricula = ?, nome = ?, endereco = ?, telefone = ?, "
                   + "email = ?, nome_pai = ?, nome_mae = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getEndereco());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setString(6, aluno.getNomePai());
            stmt.setString(7, aluno.getNomeMae());
            stmt.setInt(8, aluno.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar aluno: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
            return false;
        }
    }

    public Aluno pesquisarPorId(int id) {
        String sql = "SELECT id, matricula, nome, endereco, telefone, email, nome_pai, nome_mae "
                   + "FROM aluno WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setNomePai(rs.getString("nome_pai"));
                aluno.setNomeMae(rs.getString("nome_mae"));
                return aluno;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar aluno: " + e.getMessage());
        }
        return null;
    }
}
