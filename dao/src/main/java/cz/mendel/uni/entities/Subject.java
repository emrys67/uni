package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subjects", schema = "public")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Teacher supervisor;
    @ManyToMany
    @JoinTable(
            name = "subjects_teachers",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
    )
    private List<Teacher> teachers;

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.getSubjects().add(this);
    }

    public void deleteTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.getSubjects().remove(this);
    }
}
