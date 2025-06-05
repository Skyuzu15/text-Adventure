package model;

public class Inventario {
    private Integer Id_inventario;
    private Integer Id_item;
    private Integer Id_jogador;

    public Integer getId_inventario() {
        return Id_inventario;
    }

    public void setId_inventario(Integer id_inventario) {
        Id_inventario = id_inventario;
    }

    public Integer getId_item() {
        return Id_item;
    }

    public void setId_item(Integer id_item) {
        Id_item = id_item;
    }

    public Integer getId_jogador() {
        return Id_jogador;
    }

    public void setId_jogador(Integer id_jogador) {
        Id_jogador = id_jogador;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "Id_inventario=" + Id_inventario +
                ", Id_item=" + Id_item +
                ", Id_jogador=" + Id_jogador +
                '}';
    }
}
