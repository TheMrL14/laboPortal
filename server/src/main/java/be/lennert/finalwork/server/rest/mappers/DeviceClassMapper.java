package be.lennert.finalwork.server.rest.mappers;

import be.lennert.finalwork.server.core.entities.Device;
import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import lombok.NoArgsConstructor;

import static be.lennert.finalwork.server.core.utils.ObjectUtils.nullifyStringsAndArrays;

@NoArgsConstructor
public class DeviceClassMapper implements Mapper<Device, DeviceDTO> {

    @Override
    public DeviceDTO fromEntity(Device device) {
        Device e = nullifyStringsAndArrays(device);
        return new DeviceDTO(e.getId(), e.getName(), e.getDescription(), e.getExternalLinks(), e.getSop(), e.getImage(), e.getImageName());
    }


}
