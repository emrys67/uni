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
@Table(name = "student", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private long id;
    private int studyYear;
    private String firstname;
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    private Date dateOfBirth;
    @ManyToMany(mappedBy = "students")
    private List<Group> group;
}
