package nl.novi.techItEasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TelevisionInputDto {
    public Long id;

    @NotBlank
    public String type;

    @NotBlank
    public String brand;

    @NotBlank
    public String name;

    @Min(value=0)
    public Double price;

    @Size(min=1, max=1000)
    public Double availableSize;

    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;

    @NotNull
    public Integer originalStock;

    public Integer sold;
}
