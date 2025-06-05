package model;

public class Item {
    private Integer Id_item;
    private String nome;
    private String descricao;
    private Integer id_proxima_cena;
    private Integer id_cena_atual;
    private String uso;

    public Integer getId_item() {
        return Id_item;
    }

    public void setId_item(Integer id_item) {
        Id_item = id_item;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId_proxima_cena() {
        return id_proxima_cena;
    }

    public void setId_proxima_cena(Integer id_proxima_cena) {
        this.id_proxima_cena = id_proxima_cena;
    }

    public Integer getId_cena_atual() {
        return id_cena_atual;
    }

    public void setId_cena_atual(Integer id_cena_atual) {
        this.id_cena_atual = id_cena_atual;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Id_item=" + Id_item +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", id_proxima_cena=" + id_proxima_cena +
                ", id_cena_atual=" + id_cena_atual +
                ", uso='" + uso + '\'' +
                '}';
    }
}
