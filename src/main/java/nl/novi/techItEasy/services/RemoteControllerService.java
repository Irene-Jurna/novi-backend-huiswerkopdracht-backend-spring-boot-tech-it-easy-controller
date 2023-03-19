package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.RemoteControllerDto;
import nl.novi.techItEasy.models.RemoteController;
import nl.novi.techItEasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }

    public Long createRemoteController(RemoteControllerDto rDto) {
        RemoteController remote = new RemoteController();
        remote.setCompatibleWith(rDto.compatibleWith);
        remote.setBatteryType(rDto.batteryType);
        remote.setName(rDto.name);
        remote.setBrand(rDto.brand);
        remote.setPrice(rDto.price);
        remote.setOriginalStock(rDto.originalStock);
        repos.save(remote);
        return rDto.getId();
    }
}
