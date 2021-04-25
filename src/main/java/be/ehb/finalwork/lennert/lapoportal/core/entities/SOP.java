package be.ehb.finalwork.lennert.lapoportal.core.entities;

import be.ehb.finalwork.lennert.lapoportal.rest.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.rest.security.entities.User;
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

    @ManyToMany
    @JoinTable(name = "jnd_sop_auth",
            joinColumns = @JoinColumn(name = "sop_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_fk", referencedColumnName = "id"))
    private List<User> authors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "jnd_sop_revisors",
            joinColumns = @JoinColumn(name = "sop_fk"),
            inverseJoinColumns = @JoinColumn(name = "revisors_fk"))
    private List<User> revisors;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Step.class)
    private List<Step> procedure;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Abbreviation.class)
    private List<Abbreviation> abbreviations;

    @Column(name = "sop_image_name", nullable = true)
    @ToString.Exclude
    private String imageName;

    @Lob
    @JsonIgnore
    @Column(name = "sop_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;


    public SOP(SopDTO e) {
        setFromSop(e);
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
        this.abbreviations = sopDetails.getAbbreviations();
    }

    public void addAuthor(User u) {
        authors.add(u);
    }

    public void addRevisor(User u) {
        authors.add(u);
    }

}
