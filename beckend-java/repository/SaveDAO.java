package repository;

import model.Save;

import java.sql.*;

public class SaveDAO {
    public static Save novoJogo() throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "INSERT INTO save(id_cena_atual) VALUES (1)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Corrigi a ordem dos argumentos
        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        Save save = new Save();

        if (generatedKeys.next()) {
            save.setId_save(generatedKeys.getInt(1));
            save.setCenaAtual(CenaDAO.findCenaById(1));
        }
        stmt.close();
        connection.close();
        return save;
    }

    public static Save salvarJogo(Save save) throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "UPDATE save SET id_cena_atual = ? WHERE id_save = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, save.getCenaAtual().getId_cena());
        stmt.setInt(2, save.getId_save());
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Progresso do jogo salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar o progresso do jogo.");
        }

        stmt.close();
        connection.close();
        return save;
    }

    public static Save carregarJogo(int id_save) throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "SELECT * FROM save WHERE id_save = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id_save);
        ResultSet rs = stmt.executeQuery();

        Save save = new Save();
        if (rs.next()) {
            save.setId_save(rs.getInt("id_save"));
            save.setCenaAtual(CenaDAO.findCenaById(rs.getInt("id_cena_atual")));
        }

        rs.close();
        stmt.close();
        connection.close();

        return save;
    }
}
