package nl.novi.techItEasy.models;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
public class TelevisionsWallbrackets {
    // Compound key kun je hier maken (?). Is dat de WallBracketTelevisionKey in de uitwerkingen?
    @Id
    @GeneratedValue
    private Long id;

    // Googlen: Cascade types en hoe dat werkt
    // At column nullable is false, dan moet de relatie minimaal 1 zijn (mag geen nul zijn). Is gebruikelijk bij een tussentabel, want als deze wordt aangemaakt, is er een relatie (en die is dan niet 0)

    // FetchType.LAZY?
    @ManyToOne
//    @Column(nullable = false)
    private WallBracket wallBracket;

    @ManyToOne
    private Television tv;

    public Long getId() {
        return id;
    }

    public WallBracket getWallBracket() {
        return wallBracket;
    }

    public Television getTv() {
        return tv;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWallBracket(WallBracket wallBracket) {
        this.wallBracket = wallBracket;
    }

    public void setTv(Television tv) {
        this.tv = tv;
    }
}
