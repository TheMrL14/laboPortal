package be.ehb.finalwork.lennert.lapoportal.entities;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abbreviations")
@NoArgsConstructor
public @Data class Abbreviation extends BaseEntity {

    private String abbreviation;
    private String description;
}
