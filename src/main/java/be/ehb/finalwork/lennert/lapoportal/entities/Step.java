package be.ehb.finalwork.lennert.lapoportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@JsonIgnoreProperties(value = { "sop" })
public @Data class Step extends BaseEntity {

    @Positive
    private Integer stepNr;

    @Lob
    @Column( length = 100000 )
    private String message;

    @ManyToOne
    @JoinColumn(name = "sop_id")
    private SOP sop;

}
