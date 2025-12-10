package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;

public class DisciplinaDAO {

    public boolean salvar(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (id, nome_disciplina) VALUES (?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, disciplina.getId());
            stmt.setString(2, disciplina.getNomeDisciplina());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DisciplinaDAO - salvar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar disciplina: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET nome_disciplina = ? WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getNomeDisciplina());
            stmt.setInt(2, disciplina.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DisciplinaDAO - alterar: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar disciplina: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println("DisciplinaDAO - excluir: " + ok);
            return ok;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir disciplina: " + e.getMessage());
            return false;
        }
    }

    public Disciplina pesquisarPorId(int id) {
        String sql = "SELECT id, nome_disciplina FROM disciplina WHERE id = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Disciplina d = new Disciplina();
                d.setId(rs.getInt("id"));
                d.setNomeDisciplina(rs.getString("nome_disciplina"));
                System.out.println("DisciplinaDAO - pesquisarPorId: encontrada");
                return d;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar disciplina: " + e.getMessage());
        }
        System.out.println("DisciplinaDAO - pesquisarPorId: não encontrada");
        return null;
    }

    // NOVO: usado para preencher combo de disciplinas
    public List<Disciplina> listarTodos() {
        List<Disciplina> lista = new ArrayList<>();

        String sql = "SELECT id, nome_disciplina FROM disciplina";

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.setId(rs.getInt("id"));
                d.setNomeDisciplina(rs.getString("nome_disciplina"));
                lista.add(d);
            }

            System.out.println("DisciplinaDAO - listarTodos: " + lista.size() + " registros");

        } catch (SQLException e) {
            System.out.println("Erro ao listar disciplinas: " + e.getMessage());
        }

        return lista;
    }
}
