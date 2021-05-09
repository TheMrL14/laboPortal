package be.lennert.finalwork.server.rest.security.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Data
public class UserAuthority {

    @Id
    private UUID id;

    @ManyToOne
    private User user;

    private String authority;
}
