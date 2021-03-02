package be.ehb.finalwork.lennert.lapoportal.dao;

import be.ehb.finalwork.lennert.lapoportal.entities.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceDAO extends CrudRepository<Device,Long> {
}
