package cz.mendel.uni.services;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.repositories.StudentRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public Student findById(long id){
        return studentRepository.findById(id).get();
    }
    public Student save(Student student){
        return studentRepository.save(student);
    }
    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    public void deleteById(long id){
        studentRepository.deleteById(id);
    }
}
