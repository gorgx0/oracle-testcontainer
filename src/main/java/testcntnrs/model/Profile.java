package testcntnrs.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Profile {

    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;

}
