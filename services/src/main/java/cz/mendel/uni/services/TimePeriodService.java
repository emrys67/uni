package cz.mendel.uni.services;

import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.repositories.TimePeriodRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class TimePeriodService {
    private TimePeriodRepository timePeriodRepository;

    public TimePeriod findById(long id){
        return timePeriodRepository.findById(id).get();
    }
    public TimePeriod save(TimePeriod timePeriod){
        return timePeriodRepository.save(timePeriod);
    }
    public List<TimePeriod> findAll(){
        return timePeriodRepository.findAll();
    }
    public void deleteById(long id){
        timePeriodRepository.deleteById(id);
    }
}
