package be.lennert.finalwork.server.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;

enum StepType {
    SUBHEADER,
    STEP
}

@Entity
@JsonIgnoreProperties(value = {"sop"})
public @Data
class Step extends BaseEntity {

    @Positive
    private Integer stepNr;

    @Lob
    @Column(length = 100000)
    private String message;

    @Enumerated(EnumType.STRING)
    private StepType stepType = StepType.STEP;
    @ManyToOne
    @JoinColumn(name = "sop_id")
    @JsonIgnore
    @ToString.Exclude
    private SOP sop;
    @Column(name = "step_image_name", nullable = true)
    @ToString.Exclude
    private String imageName;
    @Lob
    @JsonIgnore
    @Column(name = "step_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;

    public Step() {
    }

    public Step(String message, boolean isHeader) {
        message = message.trim();
        String cleanMessage = message.substring(0, 1).toUpperCase() + message.substring(1);
        this.message = cleanMessage;
        this.stepType = isHeader ? StepType.SUBHEADER : StepType.STEP;
    }

    public boolean isStepType(StepType stepTypeToCompare) {
        return stepType.equals(stepTypeToCompare);
    }


}
