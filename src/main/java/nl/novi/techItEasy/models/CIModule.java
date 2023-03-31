package nl.novi.techItEasy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ci-modules")
public class CIModule {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private Double price;

    // Deze relatie staat verkeerdom. Moet @OneToMany zijn
//    @OneToMany(mappedBy = "ciModule")
//    @JsonIgnore
//    List<Television> televisions;
    @ManyToOne
    private Television tv;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
