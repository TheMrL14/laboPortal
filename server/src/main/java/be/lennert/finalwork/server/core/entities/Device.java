package be.lennert.finalwork.server.core.entities;


import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Device extends BaseEntity {

    private String name;

    @Lob
    @Column(length = 100000)
    private String description;

    @Column(name = "meta_info")
    private String metaInfo;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "sop_fk")
    @ToString.Exclude
    private SOP sop;

    @Column(name = "device_image_name", nullable = true)
    @ToString.Exclude
    private String imageName;

    @Lob
    @JsonIgnore
    @Column(name = "device_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;


    public Device(DeviceDTO device) {
        setFromDevice(device);
    }

    public void setFromDevice(DeviceDTO device) {
        this.name = device.getName();
        this.description = device.getDescription();
        this.metaInfo = device.getMetaInfo();
        this.sop = (device.getSop().getTitle() != null) ? device.getSop() : null;
        this.image = device.getImage();
        this.imageName = device.getImageName();
    }

}
