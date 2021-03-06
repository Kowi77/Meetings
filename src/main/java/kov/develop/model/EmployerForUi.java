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
@NamedNativeQueries({@NamedNativeQuery(name=EmployerForUi.GET_ALL_MEMBERS, query="SELECT m.employer_id AS id, e.fullname, e.birthday, d.name AS departName FROM members m " +
                "LEFT JOIN employers e ON m.employer_id = e.id LEFT JOIN departs d ON e.depart_id = d.id " +
                "WHERE m.meet_id=:meetId", resultClass = EmployerForUi.class),
        @NamedNativeQuery(name=EmployerForUi.GET_ALL, query="SELECT e.id, e.fullname, e.birthday, d.name AS departName FROM employers e " +
                "LEFT JOIN departs d ON e.depart_id = d.id", resultClass = EmployerForUi.class),
        @NamedNativeQuery(name=EmployerForUi.GET, query="SELECT e.id, e.fullname, e.birthday, d.name AS departName FROM employers e " +
                "LEFT JOIN departs d ON e.depart_id = d.id WHERE e.id=:empId", resultClass = EmployerForUi.class)})
public class EmployerForUi {

    public static final String GET_ALL_MEMBERS = "EmployerForUi.getAllMembersOfMeeting";
    public static final String GET_ALL = "EmployerForUi.getAll";
    public static final String GET = "EmployerForUi.get";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployerForUi that = (EmployerForUi) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        return departName != null ? departName.equals(that.departName) : that.departName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (departName != null ? departName.hashCode() : 0);
        return result;
    }
}
