package be.ehb.finalwork.lennert.lapoportal.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper=false)
public @Data
class User extends BaseEntity{

    @NotNull
    @Email
    private String email;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "user_level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Override
    public String toString() {
        return firstName +" " + lastName;
    }

}

enum Level{
    STUDENT,
    MANAGER,
    ADMIN
}
