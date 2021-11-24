package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teacher", schema = "public")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private long id;
    @OneToOne
    @JoinColumn(name = "id_vacation")
    private Vacation vacation;
    @OneToOne
    @JoinColumn(name = "id_working_hours")
    private TimePeriod workingHours;
    private String firstname;
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "id_gender")
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;
}
