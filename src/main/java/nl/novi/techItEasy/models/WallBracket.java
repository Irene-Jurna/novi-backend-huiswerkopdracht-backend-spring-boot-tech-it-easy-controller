package nl.novi.techItEasy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wallbrackets")
public class WallBracket {
    @Id
    @GeneratedValue
    private Long id;

    private String size;
    private Boolean ajustable;
    private String name;
    private Double price;

    // Dit is de makkelijke versie. Zit niet in de uitwerkingen
    @ManyToMany(mappedBy = "wallBrackets")
    private List<Television> tvs;

    @OneToMany(mappedBy = "wallBracket")
    private List<TelevisionsWallbrackets> tvWbList;

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getAjustable() {
        return ajustable;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<Television> getTvs() {
        return tvs;
    }

    public List<TelevisionsWallbrackets> getTvWbList() {
        return tvWbList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAjustable(Boolean ajustable) {
        this.ajustable = ajustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTvs(List<Television> tvs) {
        this.tvs = tvs;
    }

    public void setTvWbList(List<TelevisionsWallbrackets> tvWbList) {
        this.tvWbList = tvWbList;
    }
}
