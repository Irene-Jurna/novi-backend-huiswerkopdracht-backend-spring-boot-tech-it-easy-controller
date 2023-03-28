package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.TelevisionDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.RemoteController;
import nl.novi.techItEasy.models.Television;
import nl.novi.techItEasy.repositories.RemoteControllerRepository;
import nl.novi.techItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.rmi.Remote;
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
        Television tv = new Television();
        tv.setType(tvDto.type);
        tv.setBrand(tvDto.brand);
        tv.setName(tvDto.name);
        tv.setPrice(tvDto.price);
        tv.setAvailableSize(tvDto.availableSize);
        tv.setRefreshRate(tvDto.refreshRate);
        tv.setScreenType(tvDto.screenType);
        tv.setScreenQuality(tvDto.screenQuality);
        tv.setSmartTv(tvDto.smartTv);
        tv.setWifi(tvDto.wifi);
        tv.setVoiceControl(tvDto.voiceControl);
        tv.setHdr(tvDto.hdr);
        tv.setBluetooth(tvDto.bluetooth);
        tv.setAmbiLight(tvDto.ambiLight);
        tv.setOriginalStock(tvDto.originalStock);
        tv.setSold(tvDto.sold);

        if (tvDto.remoteId != null) {
            Optional<RemoteController> r = remoteRepos.findById(tvDto.remoteId);
            // Deze if-statement kan ook andersom met t.isPresent
            if (r.isPresent()) {
                RemoteController remote = r.get();
                tv.setRemote(remote);
            }
        }
        repos.save(tv);
        // tvToDto aanroepen --> dan hoef ik geen Long te returnen, maar een DTO (maar Long is prima)
        return tv.getId();
    }

    // Moet dit List zijn of List<TelevisionDto>? En Iterable<Television> of List<Television>?
    public List<TelevisionDto> getTelevisions() {
        List<Television> tvList = (List<Television>) repos.findAll();
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

    public void deleteTelevision(@RequestBody Long id) {
        repos.deleteById(id);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionDto tvForUpdate) {
        Optional<Television> t = repos.findById(id);
        if (t.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television tv = t.get();
            // Null check eromheen zetten, zodat je een enkele waarde kunt updaten
            if(tvForUpdate.type != null) {
                tv.setType(tvForUpdate.type);
            }
            tv.setBrand(tvForUpdate.brand);
            tv.setName(tvForUpdate.name);
            tv.setPrice(tvForUpdate.price);
            tv.setAvailableSize(tvForUpdate.availableSize);
            tv.setRefreshRate(tvForUpdate.refreshRate);
            tv.setScreenType(tvForUpdate.screenType);
            tv.setScreenQuality(tvForUpdate.screenQuality);
            tv.setSmartTv(tvForUpdate.smartTv);
            tv.setWifi(tvForUpdate.wifi);
            tv.setVoiceControl(tvForUpdate.voiceControl);
            tv.setHdr(tvForUpdate.hdr);
            tv.setBluetooth(tvForUpdate.bluetooth);
            tv.setAmbiLight(tvForUpdate.ambiLight);
            tv.setOriginalStock(tvForUpdate.originalStock);
            tv.setSold(tvForUpdate.sold);
            Television returnTelevision = repos.save(tv);
            // Onderste twee stappen kunnen worden samengevoegd in return televisionToDto(returnTelevision);
            TelevisionDto tvDto = televisionToDto(returnTelevision);
            return tvDto;
        }
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> tv = repos.findById(id);
        Optional<RemoteController> remote = remoteRepos.findById(remoteControllerId);

        if(tv.isPresent() && remote.isPresent()) {
            Television t = tv.get();
            RemoteController r = remote.get();
            t.setRemote(r);
            repos.save(t);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
