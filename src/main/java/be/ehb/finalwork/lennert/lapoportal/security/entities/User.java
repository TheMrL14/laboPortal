package be.ehb.finalwork.lennert.lapoportal.security.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    private UUID id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "username",unique = true)
    private String username;

    private Boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<UserAuthority> userAuthorities = new ArrayList<>();

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @PrePersist
    protected void prePersist(){creationDate = LocalDate.now();}

    @PreUpdate
    protected void preUpdate(){lastModifiedDate = LocalDate.now();}

    public void addAuthority(UserAuthority authority){
        userAuthorities.add(authority);
    }

/*
copyconstructor
user.toBuilder().build()
 */

}
