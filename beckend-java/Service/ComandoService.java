package Service;

import com.sun.net.httpserver.Request;
import model.Console;
import model.Save;
import repository.CenaDAO;
import repository.SaveDAO;

import java.sql.SQLException;
import java.util.Arrays;

public class ComandoService {
    private String[] comando;
    private Console console;
    private Request request;

    public ComandoService (String comandoBruto){
        Console console = new Console();
        this.console = new Console();
        this.comando = comandoBruto.split(" ");

    }

    public Console help() {
        Console console = new Console();
        console.setMensagem("Este aqui é o texto de ajuda");
        return console;
    }

    public Console start() {
        try {
            Save save = SaveDAO.novoJogo();
            this.console.setMensagem(save.getCenaAtual().getDescricao());
            this.console.setId_save(save.getId_save());
            return console;

        } catch (Exception e) {
            e.printStackTrace();
            console.setMensagem("Erro ao tenatr iniciar o jogo");
            return console;
        }
    }

    public Console useItem() {
        if (comando.length < 2) {
            console.setMensagem("Você precisa especificar o item que deseja usar");
            return console;
        }

        String item = comando[1];
        console.setMensagem("Você usou o item:" + item);
        return console;
    }

    public Console getItem() {
        if (comando.length < 2) {
            console.setMensagem("Você precisa especificar o item qu deseja pegar.");
            return console;
        }

        String item = comando[1];
        console.setMensagem("Você pegou o item:");
        return console;
    }

    public Console checkItem() {
        if (comando.length < 2) {
            console.setMensagem("Você precisa especifiacar o item que deseja verificar");
            return console;
        }

        String item = comando[1];
        if (item.equalsIgnoreCase("portal")) {
            console.setMensagem("O portal começa a se formar");
        } else {
            console.setMensagem("Você examina o " + item + ", mas nada parece acontecer");
        }
        return console;
    }

    public Console inventario() {
        console.setMensagem("Itens no seu inventario:\n- Lanterna\n- Chave\n- Medalhão");
        return console;
    }

    public Console restartGame() {
        try {
            Save save = SaveDAO.novoJogo();
            if (save != null && save.getCenaAtual() != null) {
                console.setMensagem("Jogo reiniciado. " + save.getCenaAtual().getDescricao());
            } else {
                console.setMensagem("Erro ao reiniciar o jogo, cena atual não encontrada.");
            }
            return console;
        } catch (SQLException e) {
            console.setMensagem("Erro ao reiniciar o jogo: " + e.getMessage());
            return console;
        }
    }

    public Console saveGame() {
        try {
            int idSave = Integer.parseInt(String.valueOf(request.equals("idSave")));
            int idCenaAtual = Integer.parseInt(String.valueOf(request.equals("idCenaAtual")));

            Save save = new Save();
            save.setId_save(idSave);
            save.setCenaAtual(CenaDAO.findCenaById(idCenaAtual));

            SaveDAO.salvarJogo(save);
            console.setMensagem("Progresso salvo com sucesso! Salve antes de sair da ilha");
            console.setId_save(save.getId_save());
            return console;
        } catch (SQLException e) {
            console.setMensagem("Erro ao salvar o jogo: " + e.getMessage());
            return console;
        } catch (NumberFormatException e) {
            console.setMensagem("Erro ao converter IDs: " + e.getMessage());
            return console;
        }
    }

    public Console loadGame() {
        try {
            if (comando.length < 2) {
                console.setMensagem("Comando incompleto. Use: load [ID do save]");
                return console;
            }

            System.out.println("Comando recebido: " + Arrays.toString(comando));
            int id_save = Integer.parseInt(comando[1]);
            Save save = SaveDAO.carregarJogo(id_save);
            console.setMensagem("Jogo carregado: " + save.getCenaAtual().getDescricao());
            console.setId_save(save.getId_save());
            return console;

        } catch (SQLException e) {
            console.setMensagem("Erro ao carregar o jogo.");
            return console;

        } catch (NumberFormatException e) {
            console.setMensagem("ID do save inválido. Use: load [ID numérico]");
            return console;
        }
    }

    public Console getResultadoConsole(){
        try {
            switch (comando[0]) {
                case "help":
                    return help();
                case "start":
                    return start();
                case "useItem":
                    return useItem();
                case "getItem":
                    return getItem();
                case "checkItem":
                    return checkItem();
                case "inventario":
                    return inventario();
                case " restartGame":
                    return restartGame();
                case "saveGAme":
                    return saveGame();
                case "loadGame":
                    return loadGame();
                default:
                    return console;
            }
        } catch (Exception e) {
            console.setMensagem("Comando Invalido");
            e.printStackTrace();
            return console;
        }
    }
}
