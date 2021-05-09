package be.lennert.finalwork.server.core.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abbreviations")
public @Data
class Abbreviation extends BaseEntity {

    @Column(name = "abbreviation")
    private String abbreviationShort;
    private String description;

    public Abbreviation() {
    }

    public Abbreviation(String abbreviationAndDiscription) {
        this.abbreviationShort = abbreviationAndDiscription.substring(0, abbreviationAndDiscription.indexOf(" "));
        this.description = abbreviationAndDiscription.substring(abbreviationAndDiscription.indexOf(" ") + 1);
    }
}
