package be.lennert.finalwork.server.rest.mappers;

import be.lennert.finalwork.server.core.entities.Device;
import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeviceClassMapper implements Mapper<Device, DeviceDTO> {

    @Override
    public DeviceDTO fromEntity(Device e) {
        return DeviceDTO.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .sop(e.getSop())
                .image(e.getImage())
                .imageName(e.getImageName())
                .build();
    }


}
