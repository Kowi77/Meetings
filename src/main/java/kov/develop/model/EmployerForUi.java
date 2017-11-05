package kov.develop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * DTO object for Employer
 */

@Getter
@NoArgsConstructor
@Entity
@NamedNativeQuery(name=EmployerForUi.GET_ALL_MEMBERS, query="SELECT m.employer_id AS id, e.fullname, e.birthday, d.name AS departName FROM members m " +
        "LEFT JOIN employers e ON m.employer_id = e.id LEFT JOIN departs d ON e.depart_id = d.id " +
        "WHERE m.meet_id=:meetId", resultClass = EmployerForUi.class)
public class EmployerForUi {

    public static final String GET_ALL_MEMBERS = "EmployerForUi.getAllMembersOfMeeting";

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "departName")
    private String departName;

    public EmployerForUi(@JsonProperty("id")Integer id,
                          @JsonProperty("fullname")String fullname,
                          @JsonProperty("birthday")LocalDate birthday,
                          @JsonProperty("departName")String departName) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.departName = departName;
    }
}
