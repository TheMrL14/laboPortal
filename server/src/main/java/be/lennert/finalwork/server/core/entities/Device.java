package be.lennert.finalwork.server.core.entities;


import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "external_links", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "external_link")
    private List<String> externalLinks;

    @Lob
    @JsonIgnore
    @Column(name = "device_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;

    @ElementCollection
    @CollectionTable(name = "device_videos", joinColumns = @JoinColumn(name = "id"))
    private List<String> videos;


    public Device(DeviceDTO device) {
        setFromDevice(device);
    }

    public void setFromDevice(DeviceDTO device) {
        this.name = device.getName();
        this.description = device.getDescription();
        this.sop = (device.getSop() != null && device.getSop().getTitle() != null) ? device.getSop() : null;
        this.image = device.getImage();
        this.imageName = device.getImageName();
        this.externalLinks = device.getExternalLinks();
        this.videos = device.getVideoFiles();
    }

}
