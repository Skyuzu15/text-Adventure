package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    private static final String URL = "jdbc:mysql://localhost:3306/text_adventure?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao tentar conectar com o banco: " + e.getMessage());
            e.printStackTrace(); // Detalhes do erro
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao tentar importar o driver MySQL: " + e.getMessage());
            e.printStackTrace(); // Detalhes do erro
        }

        return connection; // Pode retornar null se a conexão falhar
    }
}



