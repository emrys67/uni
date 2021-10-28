package cz.mendel.uni.services;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.repositories.GenderRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class GenderService {
    private GenderRepository genderRepository;

    public Gender findById(long id){
        return genderRepository.findById(id).get();
    }
    public Gender save(Gender gender){
        return genderRepository.save(gender);
    }
    public List<Gender> findAll(){
        return genderRepository.findAll();
    }
    public void deleteById(long id){
        genderRepository.deleteById(id);
    }

}
