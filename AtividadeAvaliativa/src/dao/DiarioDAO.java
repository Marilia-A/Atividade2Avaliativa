package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Diario;

public class DiarioDAO {
    //estudar mais pq ta esquisito
    public boolean salvar(Diario diario) {
        String sql = "INSERT INTO diario (id, status, idaluno, iddisciplina, idturma, idperiodo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, diario.getId());
            stmt.setBoolean(2, diario.isStatus());
            stmt.setInt(3, diario.getIdAluno());
            stmt.setInt(4, diario.getIdDisciplina());
            stmt.setInt(5, diario.getIdTurma());
            stmt.setInt(6, diario.getIdPeriodo());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DiarioDAO - salvar: " + (ok ? "sucesso" : "falha"));
            return ok;

        } catch (SQLException e) {
            System.out.println("DiarioDAO - Erro ao salvar diário: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Diario diario) {
        String sql = "UPDATE diario SET status = ?, idaluno = ?, iddisciplina = ?, " +
                     "idturma = ?, idperiodo = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setBoolean(1, diario.isStatus());
            stmt.setInt(2, diario.getIdAluno());
            stmt.setInt(3, diario.getIdDisciplina());
            stmt.setInt(4, diario.getIdTurma());
            stmt.setInt(5, diario.getIdPeriodo());
            stmt.setInt(6, diario.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DiarioDAO - alterar: " + (ok ? "sucesso" : "falha"));
            return ok;

        } catch (SQLException e) {
            System.out.println("DiarioDAO - Erro ao alterar diário: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM diario WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DiarioDAO - excluir: " + (ok ? "sucesso" : "falha"));
            return ok;

        } catch (SQLException e) {
            System.out.println("DiarioDAO - Erro ao excluir diário: " + e.getMessage());
            return false;
        }
    }

    // a tela puxa os combos daqui 
    public Diario pesquisarPorId(int id) {
        String sql = "SELECT id, status, idaluno, iddisciplina, idturma, idperiodo " +
                     "FROM diario WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Diario d = new Diario();
                d.setId(rs.getInt("id"));
                d.setStatus(rs.getBoolean("status"));
                d.setIdAluno(rs.getInt("idaluno"));
                d.setIdDisciplina(rs.getInt("iddisciplina"));
                d.setIdTurma(rs.getInt("idturma"));
                d.setIdPeriodo(rs.getInt("idperiodo"));

                System.out.println("DiarioDAO - pesquisarPorId: encontrado ID " + id);
                return d;
            } else {
                System.out.println("DiarioDAO - pesquisarPorId: não encontrou ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("DiarioDAO - Erro ao pesquisar diário: " + e.getMessage());
        }
        return null;
    }
}
