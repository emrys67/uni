package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom getById(long id){
        return classroomRepository.findById(id).get();
    }
    public Classroom save(Classroom classroom){
        return classroomRepository.save(classroom);
    }
}
