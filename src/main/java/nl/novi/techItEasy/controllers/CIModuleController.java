package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.dtos.CIModuleDto;
import nl.novi.techItEasy.models.CIModule;
import nl.novi.techItEasy.services.CIModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cimodules")
public class CIModuleController {
    private final CIModuleService service;

    public CIModuleController(CIModuleService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> ciModuleList = service.getCiModules();
        // Andere return kan zijn: ResponseEntity.ok(ciModuleList);
        return ResponseEntity.ok().body(ciModuleList);
    }

    @GetMapping("{/id}")
    public ResponseEntity<CIModuleDto> getCiModuleById(Long id) {
        CIModuleDto cim = service.getCiModuleById(id);
        return new ResponseEntity<>(cim, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleDto cimDto) {
        CIModuleDto cim = service.createCIModule(cimDto);
        return new ResponseEntity<>(cim, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable("id") Long id, @RequestBody CIModuleDto cimDto) {
        service.updateCIModule(id, cimDto);
        return ResponseEntity.ok(cimDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CIModule> removeCIModuleBasedOnId(@PathVariable Long id) {
        service.deleteCIModule(id);
        // Alternatief: return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
    }

}
