package cz.mendel.uni.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vacations", schema = "public")
public class Vacation {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "timeperiod_id")
    private TimePeriod timePeriod;
    private String description;
}
