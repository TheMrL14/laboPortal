package be.ehb.finalwork.lennert.lapoportal.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive

    private Integer stepNr;

    @Lob
    @Column( length = 100000 )
    private String message;

    @ManyToOne
    @JoinColumn(name = "sop_id")
    private SOP sop;

    public Step() {
    }

    public Step(@Positive @NotBlank Integer stepNr, @NotBlank String message) {
        this.stepNr = stepNr;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStepNr() {
        return stepNr;
    }

    public void setStepNr(Integer stepNr) {
        this.stepNr = stepNr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
