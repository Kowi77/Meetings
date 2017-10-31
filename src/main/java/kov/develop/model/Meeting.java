package kov.develop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "meets")
@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude = "id")
public class Meeting implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "theme")
    @NotBlank
    @Size(max = 60)
    private String theme;

    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

    @Column(name = "employer_id")
    @NotNull
    //@OneToOne( fetch = FetchType.LAZY, mappedBy = "meeting")
    private Integer employerId;

    @CollectionTable(name = "members", joinColumns = @JoinColumn(name = "meet_id"))
    @Column(name = "employer_id")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Integer> members;
    @Override
    public String toString() {
        return "Meeting{" +
                "theme='" + theme + '\'' +
                ", date=" + date +
                ", employerId=" + employerId +
                '}';
    }
}
