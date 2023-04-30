package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.RemoteControllerDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.RemoteController;
import nl.novi.techItEasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }

    public RemoteControllerDto createRemoteController(RemoteControllerDto rDto) {
        RemoteController remote = dtoToRemoteController(rDto);
        repos.save(remote);
    }

    public List<RemoteControllerDto> getRemotes() {
        List<RemoteController> rcList = repos.findAll();
        List<RemoteControllerDto> rcDtoList = new ArrayList<>();
        for (RemoteController rc : rcList) {
            RemoteControllerDto rcDto = remoteControllerToDto(rc);
            rcDtoList.add(rcDto);
        }
        return rcDtoList;
    }

    public RemoteControllerDto getRemotesById(Long id) {
        Optional<RemoteController> r = repos.findById(id);
        if (r.isEmpty()) {
            throw new RecordNotFoundException("Remote controller not found");
        } else {
            RemoteController rc = r.get();
            return remoteControllerToDto(rc);
        }
    }

    public RemoteControllerDto updateRemote(Long id, RemoteControllerDto rcForUpdate) {
        Optional<RemoteController> r = repos.findById(id);
        if (r.isEmpty()) {
            throw new RecordNotFoundException("Remote controller not found");
        } else {
            RemoteController rc = r.get();
            RemoteController rc1 = dtoToRemoteController(rcForUpdate);
            RemoteController returnRemote = repos.save(rc1);
            return remoteControllerToDto(returnRemote);
        }
    }

//    public void deleteRemote(@RequestBody Long id) {
//        repos.deleteById(id);
//    }

    public Boolean deleteRemote(Long id) {
        if(repos.existsById(id)) {
            repos.deleteById(id);
            //return true als het deleten is geslaagd
            return true;
        }
        //return false als het id niet in de DB staat en het deleten dus niet is geslaagd.
        return false;
    }

    public RemoteControllerDto remoteControllerToDto(RemoteController rc) {
        RemoteControllerDto rcDto = new RemoteControllerDto();
        rcDto.id = rc.getId();
        rcDto.compatibleWith = rc.getCompatibleWith();
        rcDto.batteryType = rc.getBatteryType();
        rcDto.name = rc.getName();
        rcDto.brand = rc.getBrand();
        rcDto.price = rc.getPrice();
        rcDto.originalStock = rc.getOriginalStock();
        return rcDto;
    }

    public RemoteController dtoToRemoteController(RemoteControllerDto rcDto) {
        RemoteController rc = new RemoteController();
        rc.setCompatibleWith(rcDto.compatibleWith);
        rc.setBatteryType(rcDto.batteryType);
        rc.setName(rcDto.name);
        rc.setBrand(rcDto.brand);
        rc.setPrice(rcDto.price);
        rc.setOriginalStock(rcDto.originalStock);
        return rc;
    }
}

