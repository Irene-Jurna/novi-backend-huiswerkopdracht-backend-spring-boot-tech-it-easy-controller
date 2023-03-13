package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.TelevisionDto;
import nl.novi.techItEasy.dtos.TelevisionInputDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.Television;
import nl.novi.techItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    public Long createTelevision(TelevisionDto tvDto) {
        Television tv = new Television();
        tv.setType(tvDto.type);
        tv.setBrand(tvDto.brand);
        tv.setName(tvDto.name);
        repos.save(tv);
        return tvDto.getId();
    }

    // Moet dit List zijn of List<TelevisionDto> ?
    public List<TelevisionDto> getTelevisions() {
        Iterable<Television> tvList = repos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        for (Television tv : tvList) {
            TelevisionDto tvDto = televisionToDto(tv);
            tvDtoList.add(tvDto);
        }
        return tvDtoList;
    }

    public TelevisionDto getTelevisionById(Long id) {
        // Alternatieve manier uit les Robert-Jan
//        Television tv = repos.findById(id).orElseThrow(() -> new RecordNotFoundException("Tv id not found"));

        Optional<Television> t = repos.findById(id);
        if (t.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television tv = t.get();
            TelevisionDto tvDto = televisionToDto(tv);
            return tvDto;
        }
    }

    public TelevisionDto televisionToDto(Television tv) {
        TelevisionDto tvDto = new TelevisionDto();
        tvDto.id = tv.getId();
        tvDto.type = tv.getType();
        tvDto.brand = tv.getBrand();
        tvDto.name = tv.getName();
        return tvDto;
    }

    public void deleteTelevision(@RequestBody Long id) {
        repos.deleteById(id);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionDto tvForUpdate) {
        Optional<Television> t = repos.findById(id);
        if (t.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television tv = t.get();
            tv.setType(tvForUpdate.name);
            tv.setBrand(tvForUpdate.brand);
            tv.setName(tvForUpdate.name);
            Television returnTelevision = repos.save(tv);
            TelevisionDto tvDto = televisionToDto(returnTelevision);
            return tvDto;
        }
    }

}
