package kov.develop.model;

import lombok.*;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( exclude = "id")
public class Meeting implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Integer employerId;

    @CollectionTable(name = "members", joinColumns = @JoinColumn(name = "meet_id"))
    @Column(name = "employer_id")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Integer> members;
}
