package kov.develop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "departs")
@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude = "id")
public class Depart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    @NotBlank
    @Size(max = 60)
    private String name;

    @Override
    public String toString() {
        return "Depart{" +
                "fullName='" + name + '\'' +
                '}';
    }
}
