package be.ehb.finalwork.lennert.lapoportal.entities;

import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.security.entities.User;
import be.ehb.finalwork.lennert.lapoportal.validation.ModificationAfterCreation;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sops")
@ModificationAfterCreation
@NoArgsConstructor
public @Data
class SOP extends BaseEntity {

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

    @OneToMany(cascade = {CascadeType.ALL},targetEntity=Step.class)
    private List<Step> procedure;

    @OneToMany( cascade = {CascadeType.ALL},targetEntity= AbbreviationAndDescription.class)
    private List<AbbreviationAndDescription> abbreviationAndDescriptions;

    @Column(name="sop_image_name", nullable=true)
    @ToString.Exclude private String imageName;

    @Lob
    @JsonIgnore
    @Column(name="sop_image", nullable=true, columnDefinition="mediumblob")
    @ToString.Exclude private byte[] image;


    public void setFromSop(SopDTO sopDetails) {
        this.title = sopDetails.getTitle();
        this.description = sopDetails.getDescription();
        this.authors = sopDetails.getAuthors();
        this.revisors = sopDetails.getRevisors();
        this.procedure = sopDetails.getProcedure();
        this.procedure.forEach(step -> step.setSop(this));
        this.image = sopDetails.getImage();
        this.imageName = sopDetails.getImageName();
        this.abbreviationAndDescriptions =sopDetails.getAbbreviationAndDescriptions();
    }

    public void addAuthor(User u){authors.add(u);}

    public void addRevisor(User u){authors.add(u);}

    public SOP(SopDTO e) {
        setFromSop(e);
    }

}
