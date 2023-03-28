package nl.novi.techItEasy.dtos;

import jakarta.validation.constraints.*;

public class TelevisionInputDto {
    public Long id;

    // In uitwerkingen wordt @NotNull gebruikt
    @NotBlank(message = "Type is required")
    public String type;

    @NotNull(message = "Brand is required")
    public String brand;

    @Size(max = 20, message = "Name must be between 0-20 characters")
    public String name;

    @Positive
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

    @PositiveOrZero
    public Integer originalStock;

    @Min(value=0)
    public Integer sold;
}
