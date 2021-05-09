package be.lennert.finalwork.server.core.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract @Data
class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @PrePersist
    protected void prePersist() {
        creationDate = LocalDate.now();
    }

    @PreUpdate
    protected void preUpdate() {
        lastModifiedDate = LocalDate.now();
    }

}
