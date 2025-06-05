package repository;

import model.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JogadorDAO {
    public static Jogador findById(Integer jogadorId) throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "SELECT * FROM jogador WHERE Id_jogador = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, jogadorId);
        ResultSet rs = stmt.executeQuery();

        Jogador jogador = null;
        if (rs.next()) {
            jogador = new Jogador();
            jogador.setId_jogador(rs.getInt("Id_jogador"));
        }
        return jogador;
    }
}
