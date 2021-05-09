package be.lennert.finalwork.server.rest.mappers;

import be.lennert.finalwork.server.core.entities.Device;
import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeviceClassMapper implements Mapper<Device, DeviceDTO> {

    @Override
    public DeviceDTO fromEntity(Device e) {
        return new DeviceDTO(e.getId(), e.getName(), e.getDescription(), e.getMetaInfo(), e.getSop(), e.getImage(), e.getImageName());
    }


}
