package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Nota;

public class NotaDAO {

    public boolean salvar(Nota nota) {
        String sql = "INSERT INTO nota (id, nota) VALUES (?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, nota.getId());
            stmt.setDouble(2, nota.getNota());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("NotaDAO - salvar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Nota nota) {
        String sql = "UPDATE nota SET nota = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDouble(1, nota.getNota());
            stmt.setInt(2, nota.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("NotaDAO - alterar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar nota: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM nota WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("NotaDAO - excluir: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir nota: " + e.getMessage());
            return false;
        }
    }

    public Nota pesquisarPorId(int id) {
        String sql = "SELECT id, nota FROM nota WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Nota n = new Nota();
                n.setId(rs.getInt("id"));
                n.setNota(rs.getDouble("nota"));
                System.out.println("NotaDAO - pesquisarPorId: encontrada");
                return n;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar nota: " + e.getMessage());
        }
        System.out.println("NotaDAO - pesquisarPorId: não encontrada");
        return null;
    }
}
