package be.lennert.finalwork.server.core.dao;

import be.lennert.finalwork.server.core.entities.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceDAO extends CrudRepository<Device, Long> {
    Device findByName(String name);
}
