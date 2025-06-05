package repository;

import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    // Método para buscar os itens de uma cena específica
    public static List<Item> findItensByScene(Integer cenaId) throws SQLException {
        String sql = "SELECT * FROM Item WHERE id_cena_atual = ?";
        List<Item> itens = new ArrayList<>();

        try (Connection connection = Mysql.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Definir o id da cena no PreparedStatement
            ps.setInt(1, cenaId);

            try (ResultSet rs = ps.executeQuery()) {
                // Iterar sobre o ResultSet para adicionar os itens à lista
                while (rs.next()) {
                    Item item = new Item();
                    item.setId_item(rs.getInt("Id_item"));
                    item.setNome(rs.getString("nome"));
                    item.setDescricao(rs.getString("descricao"));
                    item.setId_cena_atual(cenaId); // Associando ao id da cena atual

                    itens.add(item);
                }
            }
        }
        return itens; // Retorna a lista de itens ou uma lista vazia se não houver itens
    }
}


