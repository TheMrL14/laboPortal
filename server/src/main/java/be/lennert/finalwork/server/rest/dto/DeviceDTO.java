package be.lennert.finalwork.server.rest.dto;

import be.lennert.finalwork.server.core.entities.SOP;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeviceDTO {
    private Long id;
    private String name;
    private String description;
    private String metaInfo;
    private SOP sop;
    private byte[] image;
    private String imageName;

    public DeviceDTO(Long id, String name, String description, String metaInfo, SOP sop, byte[] image, String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.metaInfo = metaInfo;
        this.sop = sop;
        this.image = image;
        this.imageName = imageName;
    }

    public void setSOP(SOP sop) {
        this.sop = sop;
    }
}
