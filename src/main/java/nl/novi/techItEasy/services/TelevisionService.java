package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.TelevisionDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.RemoteController;
import nl.novi.techItEasy.models.Television;
import nl.novi.techItEasy.repositories.RemoteControllerRepository;
import nl.novi.techItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;
    private final RemoteControllerRepository remoteRepos;

    public TelevisionService(TelevisionRepository repos, RemoteControllerRepository remoteRepos) {
        this.repos = repos;
        this.remoteRepos = remoteRepos;
    }

    public Long createTelevision(TelevisionDto tvDto) {
        Television tv = dtoToTelevision(tvDto);
        if (tvDto.remoteId != null) {
            Optional<RemoteController> r = remoteRepos.findById(tvDto.remoteId);
            // Deze if-statement kan ook andersom met t.isPresent
            if (r.isPresent()) {
                RemoteController remote = r.get();
                tv.setRemote(remote);
            }
        }
        repos.save(tv);
        // Alternatieve oplossing: tvToDto aanroepen met return televisionToDto(tv); --> dan hoef ik geen Long te returnen, maar een DTO (maar Long is prima).
        return tv.getId();
    }

    // Moet dit List zijn of List<TelevisionDto>? En Iterable<Television> of List<Television>?
    public List<TelevisionDto> getTelevisions() {
        List<Television> tvList = repos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        for (Television tv : tvList) {
            TelevisionDto tvDto = televisionToDto(tv);
            tvDtoList.add(tvDto);
        }
        return tvDtoList;
    }

    // Toegevoegd bij nakijken, overgenomen uit uitwerkingen (inclusief extra regel in Repository)
    public List<TelevisionDto> getTelevisionByBrand(String brand) {
        List<Television> tvList = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        for (Television tv : tvList) {
            TelevisionDto tvDto = televisionToDto(tv);
            tvDtoList.add(tvDto);
        }
        return tvDtoList;
    }

    // Hier straks nog check aan toevoegen of er CiModules en RemoteControllers bij de tv horen
    public TelevisionDto getTelevisionById(Long id) {
        // Alternatieve manier uit les Robert-Jan
//        Television tv = repos.findById(id).orElseThrow(() -> new RecordNotFoundException("Tv id not found"));
        Optional<Television> t = repos.findById(id);
        // Deze if-statement kan ook andersom met t.isPresent
        if (t.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television tv = t.get();
            return televisionToDto(tv);
        }
    }

    public TelevisionDto updateTelevision(Long id, TelevisionDto tvForUpdate) {
        Optional<Television> t = repos.findById(id);
        if (t.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television tv = t.get();
            Television tv1 = dtoToTelevision(tvForUpdate);
            Television returnTelevision = repos.save(tv1);
            return televisionToDto(returnTelevision);
        }
    }

    public void deleteTelevision(@RequestBody Long id) {
        repos.deleteById(id);
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> tv = repos.findById(id);
        Optional<RemoteController> remote = remoteRepos.findById(remoteControllerId);

        if (tv.isPresent() && remote.isPresent()) {
            Television t = tv.get();
            RemoteController r = remote.get();
            t.setRemote(r);
            repos.save(t);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public TelevisionDto televisionToDto(Television tv) {
        TelevisionDto tvDto = new TelevisionDto();
        tvDto.id = tv.getId();
        tvDto.type = tv.getType();
        tvDto.brand = tv.getBrand();
        tvDto.name = tv.getName();
        tvDto.price = tv.getPrice();
        tvDto.availableSize = tv.getAvailableSize();
        tvDto.refreshRate = tv.getRefreshRate();
        tvDto.screenType = tv.getScreenType();
        tvDto.smartTv = tv.getSmartTv();
        tvDto.wifi = tv.getWifi();
        tvDto.voiceControl = tv.getVoiceControl();
        tvDto.hdr = tv.getHdr();
        tvDto.bluetooth = tv.getBluetooth();
        tvDto.ambiLight = tv.getAmbiLight();
        tvDto.originalStock = tv.getOriginalStock();
        tvDto.sold = tv.getSold();
        return tvDto;
    }

    public Television dtoToTelevision(TelevisionDto tvDto) {
        Television tv = new Television();

        if (tvDto.type != null) {
            tv.setType(tvDto.type);
        }

        if (tvDto.brand != null) {
            tv.setBrand(tvDto.brand);
        }

        if (tvDto.name != null) {
            tv.setName(tvDto.name);
        }

        if (tvDto.price != null) {
            tv.setPrice(tvDto.price);
        }

        if (tvDto.availableSize != null) {
            tv.setAvailableSize(tvDto.availableSize);
        }

        if (tvDto.refreshRate != null) {
            tv.setRefreshRate(tvDto.refreshRate);
        }

        if (tvDto.screenType != null) {
            tv.setScreenType(tvDto.screenType);
        }

        if (tvDto.screenQuality != null) {
            tv.setScreenQuality(tvDto.screenQuality);
        }

        if (tvDto.smartTv != null) {
            tv.setSmartTv(tvDto.smartTv);
        }

        if (tvDto.wifi != null) {
            tv.setWifi(tvDto.wifi);
        }

        if (tvDto.voiceControl != null) {
            tv.setVoiceControl(tvDto.voiceControl);
        }

        if (tvDto.hdr != null) {
            tv.setHdr(tvDto.hdr);
        }

        if (tvDto.bluetooth != null) {
            tv.setBluetooth(tvDto.bluetooth);
        }

        if (tvDto.ambiLight != null) {
            tv.setAmbiLight(tvDto.ambiLight);
        }

        if (tvDto.originalStock != null) {
            tv.setOriginalStock(tvDto.originalStock);
        }

        if (tvDto.sold != null) {
            tv.setSold(tvDto.sold);
        }
        return tv;
    }
}
