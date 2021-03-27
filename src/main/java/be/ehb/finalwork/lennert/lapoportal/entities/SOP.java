package be.ehb.finalwork.lennert.lapoportal.entities;

import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.validation.ModificationAfterCreation;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "sop", cascade = CascadeType.PERSIST)
    private List<Step> procedure;

    public void setFromSop(SopDTO sopDetails) {
        this.title = sopDetails.getTitle();
        this.description = sopDetails.getDescription();
        this.authors = sopDetails.getAuthors();
        this.revisors = sopDetails.getRevisors();
        this.procedure = sopDetails.getProcedure();
        this.procedure.forEach(step -> step.setSop(this));
    }

    public void addAuthor(User u){authors.add(u);}

    public void addRevisor(User u){authors.add(u);}

    public SOP(SopDTO e) {
        setFromSop(e);
    }

    //TEST FASE
    public SOP(SopDTO e, String a) {
        var lennert = new User();
        lennert.setId(1L);
        this.addAuthor(lennert);
        setFromSop(e);
    }
}
