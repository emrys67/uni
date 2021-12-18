package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "group_of_students", schema = "public")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group_of_students")
    private long id;
    @NotBlank(message = "Name may not be empty")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "group_of_students_student",
            joinColumns = {@JoinColumn(name = "id_group_of_students")},
            inverseJoinColumns = {@JoinColumn(name = "id_student")}
    )
    private List<Student> students;
    @ManyToMany(mappedBy = "groups")
    private List<Lecture> lectures;
}
