package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom findById(long id){
        return classroomRepository.findById(id).get();
    }
    public Classroom save(Classroom classroom){
        return classroomRepository.save(classroom);
    }
    public void update(Classroom classroom){
        classroomRepository.update(classroom.getCapacity(), classroom.getId());
    }
    public List<Classroom> findAll(){
        return classroomRepository.findAll();
    }
    public void deleteById(long id){
        classroomRepository.deleteById(id);
    }
}
