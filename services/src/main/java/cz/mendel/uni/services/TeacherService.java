package cz.mendel.uni.services;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.TeacherRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;

    public Teacher findById(long id){
        return teacherRepository.findById(id).get();
    }
    public Teacher save(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }
    public void deleteById(long id){
        teacherRepository.deleteById(id);
    }
}
