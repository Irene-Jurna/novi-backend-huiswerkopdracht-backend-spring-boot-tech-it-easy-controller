package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.CIModuleDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.CIModule;
import nl.novi.techItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
    private final CIModuleRepository repos;

    public CIModuleService(CIModuleRepository repos) {
        this.repos = repos;
    }

    public CIModuleDto createCIModule(CIModuleDto cimDto) {
        CIModule cim = dtoToCIModule(cimDto);
        repos.save(cim);
        return cimDto;
    }

    public List<CIModuleDto> getCiModules(){
        List<CIModule> ciModuleList = repos.findAll();
        List<CIModuleDto> ciModuleDtoList = new ArrayList<>();
        for (CIModule cim : ciModuleList) {
            CIModuleDto cimDto = ciModuleToDto(cim);
            ciModuleDtoList.add(cimDto);
        }
        return ciModuleDtoList;
    }

    public CIModuleDto getCiModuleById(Long id) {
        Optional<CIModule> c= repos.findById(id);
        if (c.isEmpty()) {
            throw new RecordNotFoundException("CiModule not found");
        } else {
            CIModule cim = c.get();
            return ciModuleToDto(cim);
        }
    }

    public CIModuleDto updateCIModule(Long id, CIModuleDto cimForUpdate) {
        Optional<CIModule> c = repos.findById(id);
        if (c.isEmpty()) {
            throw new RecordNotFoundException("CIModule not found");
        } else {
            CIModule cim = c.get();
            CIModule cim1 = dtoToCIModule(cimForUpdate);
            CIModule returnCim = repos.save(cim1);
            return ciModuleToDto(returnCim);
        }
    }

    public void deleteCIModule(@RequestBody Long id) {
        repos.deleteById(id);
    }

    public CIModule dtoToCIModule(CIModuleDto cimDto) {
        CIModule cim = new CIModule();
        cim.setName(cimDto.name);
        cim.setPrice(cimDto.price);
        cim.setType(cimDto.type);
        return cim;
    }

    public CIModuleDto ciModuleToDto(CIModule cim) {
        CIModuleDto cimDto = new CIModuleDto();
        cimDto.id = cim.getId();
        cimDto.name = cim.getName();
        cimDto.price = cim.getPrice();
        cimDto.type = cim.getType();
        return cimDto;
    }

}
