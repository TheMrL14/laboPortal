package be.ehb.finalwork.lennert.lapoportal.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abbreviations")
@NoArgsConstructor
public @Data class AbbreviationAndDescription extends BaseEntity {

    private String abbreviation;
    private String description;
}
