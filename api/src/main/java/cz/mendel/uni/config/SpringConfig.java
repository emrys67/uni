package cz.mendel.uni.config;

import cz.mendel.uni.controllers.ClassroomController;
import cz.mendel.uni.repositories.*;
import cz.mendel.uni.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TimePeriodRepository timePeriodRepository;
    @Autowired
    private VacationRepository vacationRepository;

    @Bean
    public ClassroomService classroomService(){
        return new ClassroomService(classroomRepository);
    }
    @Bean
    public GenderService genderService(){
        return new GenderService(genderRepository);
    }
    @Bean
    public GroupService groupService(){
        return new GroupService(groupRepository);
    }
    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository);
    }
    @Bean
    public SubjectService subjectService(){
        return new SubjectService(subjectRepository);
    }
    @Bean
    public TeacherService teacherService(){
        return new TeacherService(teacherRepository);
    }
    @Bean
    public TimePeriodService timePeriodService(){
        return new TimePeriodService(timePeriodRepository);
    }
    @Bean
    public VacationService vacationService(){
        return new VacationService(vacationRepository);
    }

    @Bean
    public ClassroomController classroomController(){
        return new ClassroomController();
    }
}
