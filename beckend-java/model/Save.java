package model;

public class Save {
    private Integer Id_save;
    private Cena cenaAtual;

    public Integer getId_save() {
        return Id_save;
    }

    public void setId_save(Integer id_save) {
        Id_save = id_save;
    }

    public Cena getCenaAtual() {
        return cenaAtual;
    }

    public void setCenaAtual(Cena cenaAtual) {
        this.cenaAtual = cenaAtual;
    }

    @Override
    public String toString() {
        return "Save{" +
                "Id_save=" + Id_save +
                ", cenaAtual=" + cenaAtual +
                '}';
    }
}
