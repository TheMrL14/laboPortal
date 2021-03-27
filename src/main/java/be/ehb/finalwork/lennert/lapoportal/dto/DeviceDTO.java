package be.ehb.finalwork.lennert.lapoportal.dto;

import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DeviceDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final String metaInfo;
    private final SOP sop;
    private final byte[] image;
    private final String imageName;
}
