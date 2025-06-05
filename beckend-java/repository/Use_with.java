package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Use_with {
    public static model.Use_with findById(Integer id) throws SQLException {
        Connection connection = Mysql.getConnection();
        String sql = "SELECT * FROM use_with WHERE Id_use_with = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        model.Use_with use_with = new model.Use_with();

        if (rs.next()) {
            use_with.setId_use_with(rs.getInt("id_use_with"));
            use_with.setId_cena(rs.getInt("Id_cena"));
            use_with.setId_item(rs.getInt("Id_item"));
        }
        return use_with;
    }
}
