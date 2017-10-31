package kov.develop.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude = "id")
public class Employer implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fullname")
    @NotBlank
    @Size(max = 60)
    private String fullname;

    @Column(name = "birthday")
    @NotNull
    private LocalDate birthday;

    @Column(name = "depart_id")
    @NotNull
    //@OneToOne (fetch = FetchType.LAZY, mappedBy = "employer")
    private Integer departId;

    @Override
    public String toString() {
        return "Employer{" +
                "fullName='" + fullname + '\'' +
                ", birthDay=" + birthday +
                '}';
    }
}
