import Controller.AntesDoJogoController;
import com.google.gson.Gson;
import model.Save;
import repository.SaveDAO;
import spark.Spark;
import java.sql.SQLException;


public class Main {
    private static final Gson Gson = new Gson();
    public static void main(String[] args) {
        try {
            Save save = SaveDAO.novoJogo();
            String saveJson = Gson.toJson(save);
            Spark.get("/", (req, res) -> saveJson);

            Spark.get("/:comando", new AntesDoJogoController());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}