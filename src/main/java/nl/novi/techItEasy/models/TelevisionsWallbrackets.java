package nl.novi.techItEasy.models;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

@Entity
public class TelevisionsWallbrackets {
    // Compound key kun je hier maken (?)
    @Id
    @GeneratedValue
    private Long id;

    // Googlen: Cascade types en hoe dat werkt
    // At column nullable is false, dan moet de relatie minimaal 1 zijn (mag geen nul zijn). Is gebruikelijk bij een tussentabel, want als deze wordt aangemaakt, is er een relatie (en die is dan niet 0)
    @ManyToOne
//    @Column(nullable = false)
    private WallBracket wallBracket;

    @ManyToOne
    private Television tv;
}
