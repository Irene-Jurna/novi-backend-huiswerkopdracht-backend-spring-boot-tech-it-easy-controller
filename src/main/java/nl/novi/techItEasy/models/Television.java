package nl.novi.techItEasy.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key
    private Long id;

    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;

    // Cascade komt uit EdHub H14, volgens mij zorgt dat ervoor dat je bij een delete ook de gerelateerde info delete
    // In huiswerkles werd private voor de variabele gezet. In H14 niks (public?)
    @OneToOne(cascade = CascadeType.ALL)
    private RemoteController remote;

    @OneToMany(mappedBy = "tv")
    private List<CIModule> ciModules;

    @OneToMany(mappedBy = "tv")
    private List<TelevisionsWallbrackets> tvWbList;

    @ManyToMany
    @JoinTable(
            name = "tussentabel_televisions_wallbrackets",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "wb_id")
    )
    private List<WallBracket> wallBrackets;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getAvailableSize() {
        return availableSize;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public Boolean getSmartTv() {
        return smartTv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public Boolean getVoiceControl() {
        return voiceControl;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public Boolean getAmbiLight() {
        return ambiLight;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public Integer getSold() {
        return sold;
    }

    public RemoteController getRemote() {
        return remote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailableSize(Double availableSize) {
        this.availableSize = availableSize;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public void setSmartTv(Boolean smartTv) {
        this.smartTv = smartTv;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public void setVoiceControl(Boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setAmbiLight(Boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public void setRemote(RemoteController remote) {
        this.remote = remote;
    }

}
