package be.lennert.finalwork.server.core.dao;


import be.lennert.finalwork.server.core.entities.SOP;
import org.springframework.data.repository.CrudRepository;

public interface SopDAO extends CrudRepository<SOP, Long> {
}
