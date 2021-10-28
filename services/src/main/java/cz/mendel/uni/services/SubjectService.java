package cz.mendel.uni.services;

import cz.mendel.uni.entities.Student;
import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.repositories.SubjectRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;

    public Subject findById(long id){
        return subjectRepository.findById(id).get();
    }
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }
    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }
    public void deleteById(long id){
        subjectRepository.deleteById(id);
    }
}
