package Controller;

import static spark.Spark.get;

import Erros.StandardErrorResponse;
import com.google.gson.Gson;
import model.Cena;
import repository.CenaDAO;

import java.sql.SQLException;
import java.util.List;

public class CenaController {

    // Declarando Gson como uma constante para reutilizar
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        get("/cenas-itens", (req, res) -> {
            try {
                // Definindo o tipo de resposta como JSON
                res.type("application/json");

                // Buscar todas as cenas com seus itens
                List<Cena> cenas = CenaDAO.findAllWithItems();

                // Converter a lista de cenas (com seus itens) para JSON
                String jsonResponse = gson.toJson(cenas);

                // Log para verificar a resposta JSON
                System.out.println("JSON gerado para cenas com itens: " + jsonResponse);

                // Retornar a resposta JSON
                return jsonResponse;

            } catch (SQLException e) {
                // Capturar erros SQL específicos
                System.err.println("Erro de SQL: " + e.getMessage());
                e.printStackTrace();
                res.status(500); // Internal Server Error
                return gson.toJson(new StandardErrorResponse("Erro de banco de dados", e.getMessage()));

            } catch (Exception e) {
                // Capturar outros tipos de erro
                System.err.println("Erro geral: " + e.getMessage());
                e.printStackTrace();
                res.status(500); // Internal Server Error
                return gson.toJson(new StandardErrorResponse("Erro interno no servidor", e.getMessage()));
            }
        });
    }
}

