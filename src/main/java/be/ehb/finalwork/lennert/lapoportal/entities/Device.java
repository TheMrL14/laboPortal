package be.ehb.finalwork.lennert.lapoportal.entities;




import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String description;

    @Column(name = "meta_info")
    private String metaInfo;

    @ManyToOne
    @JoinColumn(name = "sop_fk")
    private SOP sop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public SOP getSop() {
        return sop;
    }

    public void setSop(SOP sop) {
        this.sop = sop;
    }


}
