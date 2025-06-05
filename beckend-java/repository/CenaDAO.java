package repository;

import model.Cena;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CenaDAO {

    // Método para buscar uma Cena pelo ID, incluindo os itens associados
    public static Cena findCenaById(Integer id) throws SQLException {
        String sql = "SELECT * FROM cena WHERE id_cena = ?";
        Cena cena = null;

        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cena = new Cena();
                    cena.setId_cena(rs.getInt("Id_cena"));
                    cena.setDescricao(rs.getString("descricao"));

                    // Adicionando os itens da cena
                    List<Item> itens = ItemDAO.findItensByScene(cena.getId_cena());
                    cena.setItens(itens != null ? itens : new ArrayList<>());
                }
            }
        }
        return cena;
    }

    // Método para inserir uma nova Cena
    public static void insertCena(Cena cena) throws SQLException {
        String insert = "INSERT INTO cena(descricao) VALUES (?);";

        try (Connection connection = Mysql.getConnection();
             PreparedStatement ps = connection.prepareStatement(insert)) {

            ps.setString(1, cena.getDescricao());
            ps.execute();
        }
    }

    // Método para buscar todas as Cenas, incluindo os itens de cada uma
    public static List<Cena> findAll() throws SQLException {
        String sql = "SELECT * FROM cena;";
        List<Cena> cenas = new ArrayList<>();

        try (Connection connection = Mysql.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                Cena cena = new Cena();
                cena.setId_cena(resultSet.getInt("Id_cena"));
                cena.setDescricao(resultSet.getString("descricao"));

                // Buscando e setando os itens de cada cena
                List<Item> itens = ItemDAO.findItensByScene(cena.getId_cena());
                cena.setItens(itens != null ? itens : new ArrayList<>());

                cenas.add(cena);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar findAll: " + e.getMessage());
            throw e;
        }
        return cenas;
    }
    public static List<Cena> findAllWithItems() throws SQLException {
        String sql = "SELECT c.id_cena, c.descricao, i.id_item, i.nome, i.descricao AS item_descricao " +
                "FROM cena c " +
                "LEFT JOIN item i ON c.id_cena = i.id_cena_atual";
        List<Cena> cenas = new ArrayList<>();
        Map<Integer, Cena> cenaMap = new HashMap<>();

        try (Connection connection = Mysql.getConnection()) {
            if (connection == null) {
                throw new SQLException("Conexão com o banco de dados falhou.");
            }

            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int idCena = rs.getInt("id_cena");
                    Cena cena = cenaMap.get(idCena);

                    if (cena == null) {
                        cena = new Cena();
                        cena.setId_cena(idCena);
                        cena.setDescricao(rs.getString("descricao"));
                        cena.setItens(new ArrayList<>()); // Inicializa a lista de itens vazia
                        cenas.add(cena); // Adiciona a cena à lista de resultados
                        cenaMap.put(idCena, cena); // Mapeia a cena pelo ID
                    }

                    int idItem = rs.getInt("id_item");
                    if (idItem > 0) { // Se o id_item for válido
                        Item item = new Item();
                        item.setId_item(idItem);
                        item.setNome(rs.getString("nome"));
                        item.setDescricao(rs.getString("item_descricao"));
                        item.setId_cena_atual(idCena);
                        cena.getItens().add(item); // Adiciona o item à lista de itens da cena
                    }
                }
            }

        }return cenas;
    }
}




