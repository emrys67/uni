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
@Table(name = "group", schema = "public")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "group_student",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> students;
    @ManyToMany(mappedBy = "groups")
    private List<Lecture> lectures;
}
