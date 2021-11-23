package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
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
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;
    @OneToOne
    @JoinColumn(name = "working_hours_id")
    private TimePeriod workingHours;
    private String firstname;
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    private Date dateOfBirth;
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;
}
