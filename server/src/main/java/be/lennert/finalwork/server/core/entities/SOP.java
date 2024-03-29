package be.lennert.finalwork.server.core.entities;

import be.lennert.finalwork.server.rest.dto.SopDTO;
import be.lennert.finalwork.server.rest.security.entities.User;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sops")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter(value = AccessLevel.PACKAGE)
@Builder(toBuilder = true)
@Data
public class SOP extends BaseEntity {

    private String title;

    @Lob
    @Column(length = 100000)
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL})
    @JoinTable(name = "jnd_sop_auth",
            joinColumns = @JoinColumn(name = "sop_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_fk", referencedColumnName = "id"))
    private List<User> authors = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.ALL})
    @JoinTable(name = "jnd_sop_revisors",
            joinColumns = @JoinColumn(name = "sop_fk"),
            inverseJoinColumns = @JoinColumn(name = "revisors_fk"))
    private List<User> revisors;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, targetEntity = Step.class)
    private List<Step> procedure;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, targetEntity = Abbreviation.class)
    private List<Abbreviation> abbreviations;

    @Column(name = "sop_image_name", nullable = true)
    @ToString.Exclude
    private String imageName;

    @Lob
    @JsonIgnore
    @Column(name = "sop_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "sop_type", nullable = false)
    private SopType type;

    public SOP(SopDTO e) {
        setFromSop(e);
    }

    public void setProcedureWithStepNr(List<Step> steps) {
        this.procedure = new ArrayList<>();
        for (Step step : steps) {
            step.setStepNr(getNextNumber(step.getStepType()));
            this.procedure.add(step);
        }
    }

    private Integer getNextNumber(StepType stepType) {
        return Math.toIntExact(procedure.stream()
                .filter(step -> step.isStepType(stepType))
                .count() + 1);
    }

    public void setFromSop(SopDTO sopDetails) {
        this.title = sopDetails.getTitle();
        this.description = sopDetails.getDescription();
        this.authors = sopDetails.getAuthors();
        this.revisors = sopDetails.getRevisors();
        this.procedure = sopDetails.getProcedure();
        this.procedure.forEach(step -> step.setSop(this));
        this.image = sopDetails.getImage();
        this.imageName = sopDetails.getImageName();
        this.abbreviations = !isEmptyAbbr(sopDetails.getAbbreviations()) ? sopDetails.getAbbreviations() : null;
        this.type = SopType.INDEPENDENT;
    }

    public void hasDevice() {
        this.type = SopType.DEVICE;
    }


    private boolean isEmptyAbbr(List<Abbreviation> abbreviations) {
        return abbreviations.stream().allMatch(Abbreviation::isEmpty);
    }


    public void addAuthor(User u) {
        authors.add(u);
    }

    public void addRevisor(User u) {
        authors.add(u);
    }

    public enum SopType {
        INDEPENDENT,
        DEVICE
    }

}
