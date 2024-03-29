package mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models;


import lombok.Data;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

}
