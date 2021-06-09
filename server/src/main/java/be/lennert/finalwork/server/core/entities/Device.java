package be.lennert.finalwork.server.core.entities;


import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static be.lennert.finalwork.server.core.utils.ObjectUtils.copyNonNullProperties;
import static be.lennert.finalwork.server.core.utils.ObjectUtils.nullifyStringsAndArrays;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.ALL})
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

    @ElementCollection
    @Column(name = "external_links", nullable = true)
    private List<String> externalLinks = new ArrayList<String>();


    public Device(DeviceDTO device) {
        setFromDevice(device);
    }

    public void setFromDevice(Device device) {
        this.name = device.getName();
        this.description = device.getDescription();
        this.sop = device.getSop();
        this.imageName = device.getImageName();
        this.image = device.getImage();
        this.externalLinks = device.getExternalLinks();
    }


    public void fillWithDeviceDTO(DeviceDTO sourceDevice) {
        Device newDevice = copyNonNullProperties(new Device(sourceDevice), this);
        setFromDevice(newDevice);
    }

    public void setFromDevice(DeviceDTO device) {
        DeviceDTO normalizedDevice = nullifyStringsAndArrays(device);
        this.name = normalizedDevice.getName();
        this.description = normalizedDevice.getDescription();
        this.externalLinks = normalizedDevice.getExternalLinks();
        this.sop = normalizedDevice.getSop();
        this.image = normalizedDevice.getImage();
        this.imageName = normalizedDevice.getImageName();
    }

}
