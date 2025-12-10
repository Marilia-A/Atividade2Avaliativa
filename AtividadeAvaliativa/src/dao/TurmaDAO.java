package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Turma;

public class TurmaDAO {

    public boolean salvar(Turma turma) {
        String sql = "INSERT INTO turma (id, nome_turma) VALUES (?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, turma.getId());
            stmt.setString(2, turma.getNomeTurma());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("TurmaDAO - salvar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar turma: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Turma turma) {
        String sql = "UPDATE turma SET nome_turma = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, turma.getNomeTurma());
            stmt.setInt(2, turma.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("TurmaDAO - alterar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar turma: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM turma WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("TurmaDAO - excluir: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir turma: " + e.getMessage());
            return false;
        }
    }

    public Turma pesquisarPorId(int id) {
        String sql = "SELECT id, nome_turma FROM turma WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Turma t = new Turma();
                t.setId(rs.getInt("id"));
                t.setNomeTurma(rs.getString("nome_turma"));
                System.out.println("TurmaDAO - pesquisarPorId: encontrada");
                return t;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar turma: " + e.getMessage());
        }
        System.out.println("TurmaDAO - pesquisarPorId: não encontrada");
        return null;
    }

    public List<Turma> listarTodos() {
        List<Turma> lista = new ArrayList<>();

        String sql = "SELECT id, nome_turma FROM turma";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma t = new Turma();
                t.setId(rs.getInt("id"));
                t.setNomeTurma(rs.getString("nome_turma"));
                lista.add(t);
            }

            System.out.println("TurmaDAO - listarTodos: " + lista.size() + " registros");

        } catch (SQLException e) {
            System.out.println("Erro ao listar turmas: " + e.getMessage());
        }

        return lista;
    }
}
