package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private long id;
    private int studyYear;
    @NotBlank(message = "Firstname may not be empty")
    private String firstname;
    @NotBlank(message = "Lastname may not be empty")
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "id_gender")
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate dateOfBirth;
    @ManyToMany(mappedBy = "students")
    private List<Group> group;
}
