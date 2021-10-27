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
@Table(name = "groups", schema = "public")
public class Group {
    @Id
    private long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "group_id")
    private List<Student> studentId;
}
