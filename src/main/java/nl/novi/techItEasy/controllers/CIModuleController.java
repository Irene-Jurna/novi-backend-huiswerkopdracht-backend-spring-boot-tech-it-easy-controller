package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.services.CIModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cimodules")
public class CIModuleController {
    private final CIModuleService service;

    public CIModuleController(CIModuleService service) {
        this.service = service;
    }
}
