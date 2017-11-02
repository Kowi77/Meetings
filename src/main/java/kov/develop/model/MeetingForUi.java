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
@NamedNativeQueries({ @NamedNativeQuery(name = MeetingForUi.GET_ALL, query = "SELECT Meets.id, Meets.date, Meets.theme, Employers.fullName AS employer, Departs.name AS depart, COUNT(Members.employer_id) AS quantity FROM Meets" +
        " LEFT JOIN Employers ON Meets.employer_id = Employers.id" +
        " LEFT JOIN Departs ON Employers.depart_id = Departs.id" +
        " LEFT JOIN Members ON Meets.id = Members.meet_id" +
        " GROUP BY Meets.theme", resultClass = MeetingForUi.class) })
public class MeetingForUi {

    public static final String GET_ALL = "MeetingForUi.getAll";
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
