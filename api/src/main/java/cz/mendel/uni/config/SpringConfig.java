package cz.mendel.uni.config;

import cz.mendel.uni.controllers.*;
import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController;
import cz.mendel.uni.repositories.*;
import cz.mendel.uni.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ClassroomService classroomService(ClassroomRepository classroomRepository) {
        return new ClassroomService(classroomRepository);
    }

    @Bean
    public GenderService genderService(GenderRepository genderRepository) {
        return new GenderService(genderRepository);
    }

    @Bean
    public GroupService groupService(GroupRepository groupRepository) {
        return new GroupService(groupRepository);
    }

    @Bean
    public StudentService studentService(StudentRepository studentRepository) {
        return new StudentService(studentRepository);
    }

    @Bean
    public SubjectService subjectService(SubjectRepository subjectRepository) {
        return new SubjectService(subjectRepository);
    }

    @Bean
    public TeacherService teacherService(TeacherRepository teacherRepository) {
        return new TeacherService(teacherRepository);
    }

    @Bean
    public TimePeriodService timePeriodService(TimePeriodRepository timePeriodRepository) {
        return new TimePeriodService(timePeriodRepository);
    }

    @Bean
    public VacationService vacationService(VacationRepository vacationRepository) {
        return new VacationService(vacationRepository);
    }

    @Bean
    public LectureService lectureService(LectureRepository lectureRepository) {
        return new LectureService(lectureRepository);
    }

    @Bean
    public ClassroomController classroomController(ClassroomService classroomService) {
        return new ClassroomController(classroomService);
    }

    @Bean
    public TimeperiodController timeperiodController(TimePeriodService timePeriodService) {
        return new TimeperiodController(timePeriodService);
    }

    @Bean
    public VacationController vacationController(VacationService vacationService, TimePeriodService timePeriodService) {
        return new VacationController(vacationService, timePeriodService);
    }

    @Bean
    public GenderController genderController(GenderService genderService) {
        return new GenderController(genderService);
    }

    @Bean
    public GroupController groupController(GroupService groupService, StudentService studentService) {
        return new GroupController(groupService, studentService);
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

    @Bean
    public StudentController studentController(StudentService studentService) {
        return new StudentController(studentService);
    }

    @Bean
    public TeacherController teacherController(TimePeriodService timePeriodService, VacationService vacationService, TeacherService teacherService) {
        return new TeacherController(timePeriodService, vacationService,
                teacherService);
    }

    @Bean
    public SubjectController subjectController(SubjectService subjectService, TeacherService teacherService) {
        return new SubjectController(subjectService, teacherService);
    }

    @Bean
    public ExceptionHandlerController exceptionHandlerController() {
        return new ExceptionHandlerController();
    }
}
