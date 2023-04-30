package nl.novi.techItEasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techItEasy.dtos.IdInputDto;
import nl.novi.techItEasy.dtos.TelevisionDto;
import nl.novi.techItEasy.models.Television;
import nl.novi.techItEasy.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("televisions")
public class TelevisionController {

    // We importeren de Service
    private final TelevisionService service;

    public TelevisionController(TelevisionService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        // Een andere return kan zijn: ResponseEntity<>(televisionList, HttpStatus.OK);
        List<TelevisionDto> televisionList;
        televisionList = service.getTelevisions();
        return ResponseEntity.ok().body(televisionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@Valid @PathVariable Long id) {
        TelevisionDto tv = service.getTelevisionById(id);
        return new ResponseEntity<>(tv, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionDto tvDto) {
        Long tv = service.createTelevision(tvDto);
        return new ResponseEntity<>(tvDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@Valid @PathVariable Long id, @RequestBody TelevisionDto tv) {
        TelevisionDto tvDto = service.updateTelevision(id, tv);
        return new ResponseEntity<>(tvDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> removeTelevisionBasedOnId(@PathVariable Long id) {
        service.deleteTelevision(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // Onderstaande 2 methodes zijn endpoints om andere entiteiten toe te voegen aan de Television.
    // Dit is één manier om dit te doen, met één PathVariable en één RequestBody.
//    @PutMapping("/televisions/{id}/remotecontroller")
//    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id,@Valid @RequestBody IdInputDto input) {
//        televisionService.assignRemoteControllerToTelevision(id, input.id);
//        return ResponseEntity.noContent().build();
//    }

    //Dit is een andere manier om het te doen, met twee Pathvariables, maar het kan uiteraard ook anders.
    @PutMapping("{id}/remote")
    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id, @Valid @RequestBody IdInputDto remoteId) {
        service.assignRemoteControllerToTelevision(id, remoteId.id);
        return ResponseEntity.noContent().build();
    }

    // Deze methode is om alle wallbrackets op te halen die aan een bepaalde television gekoppeld zijn.
    // Deze methode maakt gebruik van de televisionWallBracketService.
//    @GetMapping("/televisions/wallBrackets/{televisionId}")
//    public ResponseEntity<Collection<WallBracketDto>> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
//        return ResponseEntity.ok(televisionWallBracketService.getWallBracketsByTelevisionId(televisionId));
//    }
}
