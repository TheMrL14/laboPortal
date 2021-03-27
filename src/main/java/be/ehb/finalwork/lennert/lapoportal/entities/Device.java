package be.ehb.finalwork.lennert.lapoportal.entities;


import be.ehb.finalwork.lennert.lapoportal.dto.DeviceDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "devices")

@NoArgsConstructor
public @Data
class Device extends BaseEntity{

    private String name;

    @Lob
    @Column(length = 100000)
    private String description;

    @Column(name = "meta_info")
    private String metaInfo;

    @ManyToOne
    @JoinColumn(name = "sop_fk")
    private SOP sop;

    @Lob
    @Column(name="device_image", nullable=true, columnDefinition="mediumblob")
    private byte[] image;


    public void setFromDevice(DeviceDTO device){
        this.name = device.getName();
        this.description = device.getDescription();
        this.metaInfo = device.getMetaInfo();
        this.sop = device.getSop();
    }
    public  Device(DeviceDTO device){
        setFromDevice(device);
    }

}
