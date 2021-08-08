package testcntnrs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users_table")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login ;

    @Embedded
    private Profile profile;

    @ManyToMany
    @JoinTable(
        name = "users_to_groups",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "group_id")}
    )
    private Set<Group> groups;
}
