package kov.develop.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty()
    private Integer id;

    @Column(name = "fullname")
    @NotBlank
    @Size(max = 60)
    @JsonProperty("fullname")
    private String fullname;

    @Column(name = "birthday")
    @NotNull
    @JsonProperty("birthday")
    private LocalDate birthday;

    @Column(name = "depart_id")
    @NotNull
    @JsonProperty("departId")
    private Integer departId;

}
