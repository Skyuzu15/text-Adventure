package model;

import java.util.List;

public class CenasComItem {
    private Cena cena;
    private List<Item> itens;

    public CenasComItem(Cena cena, List<Item> itens) {
    }

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
