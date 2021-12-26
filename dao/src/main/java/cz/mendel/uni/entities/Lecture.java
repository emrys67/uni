package cz.mendel.uni.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lecture", schema = "public")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lecture")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;
    @ManyToMany
    @JoinTable(
            name = "lecture_group_of_students",
            joinColumns = {@JoinColumn(name = "id_lecture")},
            inverseJoinColumns = {@JoinColumn(name = "id_group_of_students")}
    )
    private List<Group> groups;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "id_timeperiod")
    private TimePeriod timePeriod;
    @ManyToOne
    @JoinColumn(name = "id_classroom")
    private Classroom classroom;
}
