package be.ehb.finalwork.lennert.lapoportal.security.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Data public class UserAuthority {

    @Id
    private UUID id;

    @ManyToOne
    private User user;

    private String authority;
}
