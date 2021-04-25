package be.ehb.finalwork.lennert.lapoportal.core.dao;

import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<SOP, Long> {
}
