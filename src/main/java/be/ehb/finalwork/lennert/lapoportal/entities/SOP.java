package be.ehb.finalwork.lennert.lapoportal.entities;



//import be.ehb.finalwork.lennert.lapoportal.validation.ModificationAfterCreation;



import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.validation.ModificationAfterCreation;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sops")
@ModificationAfterCreation
public class SOP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column( length = 100000 )
    private String description;

    @ManyToMany
    @JoinTable(name = "jnd_sop_auth",
            joinColumns = @JoinColumn(name = "sop_fk",referencedColumnName="id" ),
            inverseJoinColumns = @JoinColumn(name = "author_fk",referencedColumnName="id"))
    private List<User> authors;

    @ManyToMany
    @JoinTable(name = "jnd_sop_revisors",
            joinColumns = @JoinColumn(name = "sop_fk"),
            inverseJoinColumns = @JoinColumn(name = "revisors_fk"))
    private List<User> revisors;


    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;


    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "sop",cascade = CascadeType.PERSIST)
    private List<Step> procedure;


    public SOP() {
    }

    public SOP(String title, String description, List<User> authors, LocalDate creationDate, List<Step> procedure) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.creationDate = creationDate;
        this.procedure = procedure;
    }

    public static SOP fromDTO(SopDTO dto,List<User> authors){
        return new SOP(dto.getTitle(),dto.getDescription(),authors,dto.getCreationDate(),dto.getProcedure());
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAuthors() {
        return authors;
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    public List<User> getRevisors() {
        return revisors;
    }

    public void setRevisors(List<User> revisors) {
        this.revisors = revisors;
    }

    public List<Step> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<Step> procedure) {
        this.procedure = procedure;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }




}
