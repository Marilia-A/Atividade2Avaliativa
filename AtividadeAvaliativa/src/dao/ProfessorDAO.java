package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Professor;

public class ProfessorDAO {

    public boolean salvar(Professor professor) {
        String sql = "INSERT INTO professor (id, matricula, nome, endereco, telefone, email) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, professor.getId());
            stmt.setString(2, professor.getMatricula());
            stmt.setString(3, professor.getNome());
            stmt.setString(4, professor.getEndereco());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getEmail());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Professor professor) {
        String sql = "UPDATE professor SET matricula = ?, nome = ?, endereco = ?, "
                   + "telefone = ?, email = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, professor.getMatricula());
            stmt.setString(2, professor.getNome());
            stmt.setString(3, professor.getEndereco());
            stmt.setString(4, professor.getTelefone());
            stmt.setString(5, professor.getEmail());
            stmt.setInt(6, professor.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar professor: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
            return false;
        }
    }

    public Professor pesquisarPorId(int id) {
        String sql = "SELECT id, matricula, nome, endereco, telefone, email "
                   + "FROM professor WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setMatricula(rs.getString("matricula"));
                professor.setNome(rs.getString("nome"));
                professor.setEndereco(rs.getString("endereco"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                return professor;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar professor: " + e.getMessage());
        }
        return null;
    }
}
