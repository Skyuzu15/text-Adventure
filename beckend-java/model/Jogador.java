package model;

public class Jogador {
    private Integer Id_jogador;
    private String Nome;

    public Integer getId_jogador() {
        return Id_jogador;
    }

    public void setId_jogador(Integer id_jogador) {
        Id_jogador = id_jogador;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "Id_jogador=" + Id_jogador +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
