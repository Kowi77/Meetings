package kov.develop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DTO object for Meeting
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = MeetingForUi.GET_ALL, query = MeetingForUi.QUERY + " GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_DEPART, query = MeetingForUi.QUERY + " WHERE e.depart_id=:departId GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_EMPLOYER, query = MeetingForUi.QUERY + " WHERE e.id=:empId GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_DATE, query = MeetingForUi.QUERY + " WHERE Meets.date BETWEEN :start AND :end GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_MEMBER, query = MeetingForUi.QUERY + " WHERE Meets.id = ANY (SELECT m.meet_id FROM members m WHERE m.employer_id=:memId) GROUP BY Meets.theme", resultClass = MeetingForUi.class),
})
public class MeetingForUi {

    public static final String GET_ALL = "MeetingForUi.getAll";
    public static final String GET_FILTERED_BY_DEPART = "MeetingForUi.getFilteredByDepart";
    public static final String GET_FILTERED_BY_EMPLOYER = "MeetingForUi.getFilteredByEmployer";
    public static final String GET_FILTERED_BY_DATE = "MeetingForUi.getFilteredByDate";
    public static final String GET_FILTERED_BY_MEMBER = "MeetingForUi.getFilteredByMember";
    public static final String QUERY = "SELECT Meets.id, Meets.date, Meets.theme, e.fullName AS employer, d.name AS depart, COUNT(m.employer_id) AS quantity FROM Meets" +
            " LEFT JOIN Employers e ON Meets.employer_id = e.id" +
            " LEFT JOIN Departs d ON e.depart_id = d.id" +
            " LEFT JOIN Members m ON Meets.id = m.meet_id";

    @Id
    private Integer id;
    @Column (name = "date")
    private LocalDateTime date;
    @Column (name = "theme")
    private String theme;
    @Column (name = "depart")
    private String depart;
    @Column (name = "employer")
    private String employer;
    @Column (name = "quantity")
    private Integer quantity;



    public MeetingForUi(@JsonProperty("id") Integer id,
                        @JsonProperty("date") LocalDateTime date,
                        @JsonProperty("theme") String theme,
                        @JsonProperty("depart") String depart,
                        @JsonProperty("employer") String employer,
                        @JsonProperty("quantity") Integer quantity) {
        this.id = id;
        this.date = date;
        this.theme = theme;
        this.depart = depart;
        this.employer = employer;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MeetingForUi{" +
                "id=" + id +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                ", depart='" + depart + '\'' +
                ", employer='" + employer + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingForUi that = (MeetingForUi) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (depart != null ? !depart.equals(that.depart) : that.depart != null) return false;
        if (employer != null ? !employer.equals(that.employer) : that.employer != null) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (depart != null ? depart.hashCode() : 0);
        result = 31 * result + (employer != null ? employer.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
