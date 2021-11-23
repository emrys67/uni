package cz.mendel.uni.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "timeperiod", schema = "public")
public class TimePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_timeperiod")
    private long id;
    @DateTimeFormat(iso = DATE_TIME)
    private LocalDateTime startTime;
    @DateTimeFormat(iso = DATE_TIME)
    private LocalDateTime endTime;
}
