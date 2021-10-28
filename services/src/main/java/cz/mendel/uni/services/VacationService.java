package cz.mendel.uni.services;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.repositories.VacationRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class VacationService {
    private VacationRepository vacationRepository;

    public Vacation findById(long id){
        return vacationRepository.findById(id).get();
    }
    public Vacation save(Vacation vacation){
        return vacationRepository.save(vacation);
    }
    public List<Vacation> findAll(){
        return vacationRepository.findAll();
    }
    public void deleteById(long id){
        vacationRepository.deleteById(id);
    }
}
