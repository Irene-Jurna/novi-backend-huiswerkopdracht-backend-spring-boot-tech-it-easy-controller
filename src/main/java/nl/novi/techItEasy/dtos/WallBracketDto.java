package nl.novi.techItEasy.dtos;

public class WallBracketDto {
    public Long id;
    public String size;
    public Boolean ajustable;
    public String name;
    public Double price;

    public WallBracketDto() {
    }

    public WallBracketDto(Long id, String size, Boolean ajustable, String name, Double price) {
        this.id = id;
        this.size = size;
        this.ajustable = ajustable;
        this.name = name;
        this.price = price;
    }

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
}
