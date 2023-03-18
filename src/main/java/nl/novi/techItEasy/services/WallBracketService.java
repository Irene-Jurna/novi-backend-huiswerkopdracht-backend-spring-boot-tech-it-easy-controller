package nl.novi.techItEasy.services;

import nl.novi.techItEasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {
    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }
}
