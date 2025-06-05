package repository;

import model.Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    public static List<Inventario> findByPlayerId(Integer JogadorId) throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "SELECT * FROM inventario WHERE Id_jogador = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, JogadorId);
        ResultSet rs = stmt.executeQuery();
        Inventario inventario = new Inventario();

        List<Inventario> inventarioList = new ArrayList<>();
        while (rs.next()) {
            inventario.setId_inventario(rs.getInt("Id_inventario"));
            inventario.setId_item(rs.getInt("Id_item"));
            inventario.setId_jogador(rs.getInt("Id_jogador"));

            inventarioList.add(inventario);
        }
        return inventarioList;
    }
}
