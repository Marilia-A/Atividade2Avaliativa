package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Periodo;

public class PeriodoDAO {

    public boolean salvar(Periodo periodo) {
        String sql = "INSERT INTO periodo (id, nome_periodo) VALUES (?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, periodo.getId());
            stmt.setString(2, periodo.getNomePeriodo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar período: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Periodo periodo) {
        String sql = "UPDATE periodo SET nome_periodo = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, periodo.getNomePeriodo());
            stmt.setInt(2, periodo.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar período: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM periodo WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir período: " + e.getMessage());
            return false;
        }
    }

    public Periodo pesquisarPorId(int id) {
        String sql = "SELECT id, nome_periodo FROM periodo WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Periodo p = new Periodo();
                p.setId(rs.getInt("id"));
                p.setNomePeriodo(rs.getString("nome_periodo"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar período: " + e.getMessage());
        }
        return null;
    }
}
