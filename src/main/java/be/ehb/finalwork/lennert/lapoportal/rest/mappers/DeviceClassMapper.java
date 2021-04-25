package be.ehb.finalwork.lennert.lapoportal.rest.mappers;

import be.ehb.finalwork.lennert.lapoportal.core.entities.Device;
import be.ehb.finalwork.lennert.lapoportal.rest.dto.DeviceDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeviceClassMapper implements Mapper<Device, DeviceDTO> {

    @Override
    public DeviceDTO fromEntity(Device e) {
        return new DeviceDTO(e.getId(), e.getName(), e.getDescription(), e.getMetaInfo(), e.getSop(), e.getImage(), e.getImageName());
    }


}
