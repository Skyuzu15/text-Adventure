package Controller;

import Service.ComandoService;
import com.google.gson.Gson;
import spark.Response;
import spark.Route;

public class AntesDoJogoController implements Route {
    @Override
    public Object handle(spark.Request request, Response response) throws Exception {
        String comandoBruto = request.params(":comando");

        ComandoService comandoService = new ComandoService(comandoBruto);
        Gson gson = new Gson();

        return gson.toJson(comandoService.getResultadoConsole());
    }
}
