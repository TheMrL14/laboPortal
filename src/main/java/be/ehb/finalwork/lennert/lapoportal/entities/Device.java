package be.ehb.finalwork.lennert.lapoportal.entities;


import be.ehb.finalwork.lennert.lapoportal.dto.DeviceDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

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
    @ToString.Exclude private SOP sop;

    @Column(name="device_image_name", nullable=true)
    @ToString.Exclude private String imageName;

    @Lob
    @JsonIgnore
    @Column(name="device_image", nullable=true, columnDefinition="mediumblob")
    @ToString.Exclude private byte[] image;


    public void setFromDevice(DeviceDTO device){
        this.name = device.getName();
        this.description = device.getDescription();
        this.metaInfo = device.getMetaInfo();
        this.sop = device.getSop();
        this.image = device.getImage();
        this.imageName = device.getImageName();
    }
    public  Device(DeviceDTO device){
        setFromDevice(device);
    }

}
