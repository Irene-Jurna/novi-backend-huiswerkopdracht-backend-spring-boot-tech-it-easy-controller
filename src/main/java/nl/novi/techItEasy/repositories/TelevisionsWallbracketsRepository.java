package nl.novi.techItEasy.repositories;

import nl.novi.techItEasy.models.TelevisionsWallbrackets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionsWallbracketsRepository extends JpaRepository<TelevisionsWallbrackets, Long> {
    // Uit eennalaatste huiswerkles
//    List<TelevisionsWallbrackets> findAllById(Long id);
//    List<TelevisionsWallbrackets> findAllByWallBracketId(Long id);

}
