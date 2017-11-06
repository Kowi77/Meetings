package kov.develop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DTO object for Meeting
 */
@Getter
@NoArgsConstructor
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = MeetingForUi.GET_ALL, query = MeetingForUi.QUERY + " GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_DEPART, query = MeetingForUi.QUERY + " WHERE e.depart_id=:departId GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_EMPLOYER, query = MeetingForUi.QUERY + " WHERE e.id=:empId GROUP BY Meets.theme", resultClass = MeetingForUi.class),
    @NamedNativeQuery(name = MeetingForUi.GET_FILTERED_BY_DATE, query = MeetingForUi.QUERY, resultClass = MeetingForUi.class),
})
public class MeetingForUi {

    public static final String GET_ALL = "MeetingForUi.getAll";
    public static final String GET_FILTERED_BY_DEPART = "MeetingForUi.getFilteredByDepart";
    public static final String GET_FILTERED_BY_EMPLOYER = "MeetingForUi.getFilteredByEmployer";
    public static final String GET_FILTERED_BY_DATE = "MeetingForUi.getFilteredByDate";
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


}
