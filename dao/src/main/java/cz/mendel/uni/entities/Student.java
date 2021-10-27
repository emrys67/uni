package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students", schema = "public")
public class Student {
    @Id
    private long id;
    private int studyYear;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupId;
}
