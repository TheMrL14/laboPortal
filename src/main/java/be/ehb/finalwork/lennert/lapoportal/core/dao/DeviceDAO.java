package be.ehb.finalwork.lennert.lapoportal.core.dao;

import be.ehb.finalwork.lennert.lapoportal.core.entities.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceDAO extends CrudRepository<Device, Long> {
    Device findByName(String name);
}
