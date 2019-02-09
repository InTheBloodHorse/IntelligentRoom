package cn.lsu.chicken.room.domain;

public class MeetingRoom {
    private Integer id;

    private String name;

    private Integer buildingId;

    private String address;

    private Byte volume;

    private Byte isUsing;

    private Short price;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getVolume() {
        return volume;
    }

    public void setVolume(Byte volume) {
        this.volume = volume;
    }

    public Byte getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Byte isUsing) {
        this.isUsing = isUsing;
    }

    public Short getPrice() {
        return price;
    }

    public void setPrice(Short price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}