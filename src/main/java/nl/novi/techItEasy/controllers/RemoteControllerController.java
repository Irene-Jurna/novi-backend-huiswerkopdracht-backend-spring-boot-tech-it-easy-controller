package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.dtos.RemoteControllerDto;
import nl.novi.techItEasy.services.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remotes")
public class RemoteControllerController {
    private final RemoteControllerService service;

    public RemoteControllerController(RemoteControllerService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<RemoteControllerDto> addRemote(@RequestBody RemoteControllerDto rDto) {
        Long remoteController = service.createRemoteController(rDto);
        return new ResponseEntity<>(rDto, HttpStatus.CREATED);
    }
}
