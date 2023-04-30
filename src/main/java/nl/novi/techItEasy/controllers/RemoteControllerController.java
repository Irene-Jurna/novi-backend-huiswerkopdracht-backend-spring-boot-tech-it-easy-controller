package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.dtos.RemoteControllerDto;
import nl.novi.techItEasy.services.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("remotes")
public class RemoteControllerController {
    private final RemoteControllerService service;

    public RemoteControllerController(RemoteControllerService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<RemoteControllerDto> addRemote(@RequestBody RemoteControllerDto rDto) {
        RemoteControllerDto remoteController = service.createRemoteController(rDto);
        return new ResponseEntity<>(rDto, HttpStatus.CREATED);
    }

    @GetMapping("/remotecontrollers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemotecontrollers() {

        List<RemoteControllerDto> dtos = service.getRemotes();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/remotecontrollers/{id}")
    public ResponseEntity<RemoteControllerDto> getRemotecontroller(@PathVariable("id") Long id) {

        RemoteControllerDto dto = service.getRemotesById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/remotecontrollers")
    public ResponseEntity<RemoteControllerDto> addRemoteController(@RequestBody RemoteControllerDto dto) {
        RemoteControllerDto dto1 = service.createRemoteController(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @DeleteMapping("/remotecontrollers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") Long id) {
        Boolean check = service.deleteRemote(id);
        // Als de service methode een true returned (succesvolle delete), returnen we een noContent, anders returnen we een badRequest
        if(check) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Het id dat je probeert te verwijderen bestaat niet.");
        }
    }

    @PutMapping("/remotecontrollers/{id}")
    public ResponseEntity<RemoteControllerDto> updateTelevision(@PathVariable("id") Long id, @RequestBody RemoteControllerDto dto) {
        service.updateRemote(id, dto);
        return ResponseEntity.ok(dto);
    }

}
