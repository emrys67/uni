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
@Table(name = "teachers", schema = "public")
public class Teacher {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;
    @OneToOne
    @JoinColumn(name = "working_hours_id")
    private TimePeriod workingHours;
    private String firstname;
    private String lastname;
    private String gender;
    private Date dateOfBirth;
}
