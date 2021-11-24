package cz.mendel.uni.entities;//package cz.mendel.uni.dao.entities;

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
            name = "lecture_group",
            joinColumns = {@JoinColumn(name = "id_lecture")},
            inverseJoinColumns = {@JoinColumn(name = "id_group")}
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

    public void addGroup(Group group) {
        groups.add(group);
        group.getLectures().add(this);
    }

    public void deleteGroup(Group group) {
        groups.remove(group);
        group.getLectures().remove(this);
    }
}
