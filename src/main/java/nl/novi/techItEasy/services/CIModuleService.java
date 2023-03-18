package nl.novi.techItEasy.services;

import nl.novi.techItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CIModuleService {
    private final CIModuleRepository repos;

    public CIModuleService(CIModuleRepository repos) {
        this.repos = repos;
    }
}
