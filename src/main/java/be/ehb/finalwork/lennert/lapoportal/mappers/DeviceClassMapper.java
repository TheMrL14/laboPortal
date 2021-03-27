package be.ehb.finalwork.lennert.lapoportal.mappers;

import be.ehb.finalwork.lennert.lapoportal.dto.DeviceDTO;
import be.ehb.finalwork.lennert.lapoportal.entities.Device;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeviceClassMapper implements Mapper<Device, DeviceDTO> {

    @Override
    public DeviceDTO fromEntity(Device e) {
        return new DeviceDTO(e.getId(),e.getName(), e.getDescription(), e.getMetaInfo(), e.getSop(), e.getImage(),e.getImageName());
    }


}
