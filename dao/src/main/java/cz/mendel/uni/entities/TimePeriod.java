package cz.mendel.uni.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "timeperiods", schema = "public")
public class TimePeriod {
    @Id
    private long id;
    private Date startDate;
    private Date endDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
