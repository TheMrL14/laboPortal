package be.ehb.finalwork.lennert.lapoportal.dao;

import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<SOP,Long> {
}
