package model;

public class Use_with {
    private Integer Id_use_with;
    private Integer Id_cena;
    private Integer Id_item;

    public Integer getId_use_with() {
        return Id_use_with;
    }

    public void setId_use_with(Integer id_use_with) {
        Id_use_with = id_use_with;
    }

    public Integer getId_cena() {
        return Id_cena;
    }

    public void setId_cena(Integer id_cena) {
        Id_cena = id_cena;
    }

    public Integer getId_item() {
        return Id_item;
    }

    public void setId_item(Integer id_item) {
        Id_item = id_item;
    }

    @Override
    public String toString() {
        return "Use_with{" +
                "Id_use_with=" + Id_use_with +
                ", Id_cena=" + Id_cena +
                ", Id_item=" + Id_item +
                '}';
    }
}
