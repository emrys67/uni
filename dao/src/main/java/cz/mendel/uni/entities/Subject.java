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
    private long id;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Teacher supervisor;
    @OneToMany()
    private List<Teacher> teachers;
}
