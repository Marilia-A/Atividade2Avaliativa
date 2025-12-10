package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Diario;

public class DiarioDAO {

    public boolean salvar(Diario diario) {
        String sql = "INSERT INTO diario (id, status) VALUES (?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, diario.getId());
            stmt.setBoolean(2, diario.isStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar diário: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Diario diario) {
        String sql = "UPDATE diario SET status = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setBoolean(1, diario.isStatus());
            stmt.setInt(2, diario.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar diário: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM diario WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir diário: " + e.getMessage());
            return false;
        }
    }

    public Diario pesquisarPorId(int id) {
        String sql = "SELECT id, status FROM diario WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Diario d = new Diario();
                d.setId(rs.getInt("id"));
                d.setStatus(rs.getBoolean("status"));
                return d;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar diário: " + e.getMessage());
        }
        return null;
    }
}
