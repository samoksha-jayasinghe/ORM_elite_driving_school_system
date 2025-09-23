package com.example.orm_elite_driving_school_system.bo.custom.util;

import com.example.orm_elite_driving_school_system.dto.*;
import com.example.orm_elite_driving_school_system.entity.*;

public class EntityDTOConverter {
    public CourseDTO getCourseDTO(Course course){
        CourseDTO dto=new CourseDTO();
        dto.setCourse_id(course.getCourse_id());
        dto.setCourse_name(course.getCourse_name());
        dto.setDuration(course.getDuration());
        dto.setFee(course.getFee());
        dto.setDescription(course.getDescription());
        dto.setInstructor_id(course.getInstructors().getInstructor_id());
        return dto;
    }

    public Course getCourseEntity(CourseDTO dto){
        Course course=new Course();
        Instructors instructors=new Instructors();
        course.setCourse_id(dto.getCourse_id());
        course.setCourse_name(dto.getCourse_name());
        course.setDuration(dto.getDuration());
        course.setFee(dto.getFee());
        course.setDescription(dto.getDescription());
        instructors.setInstructor_id(dto.getInstructor_id());
        course.setInstructors(instructors);
        return course;
    }

    public InstructorsDTO getInstructorsDTO(Instructors instructors){
        InstructorsDTO dto=new InstructorsDTO();
        dto.setInstructor_id(instructors.getInstructor_id());
        dto.setFirst_name(instructors.getFirst_name());
        dto.setLast_name(instructors.getLast_name());
        dto.setEmail(instructors.getEmail());
        dto.setPhone(instructors.getPhone());
        dto.setSpecialization(instructors.getSpecialization());
        dto.setAvailability(instructors.getAvailability());
        return dto;
    }

    public Instructors getInstructorsEntity(InstructorsDTO dto){
        Instructors instructors=new Instructors();
        instructors.setInstructor_id(dto.getInstructor_id());
        instructors.setFirst_name(dto.getFirst_name());
        instructors.setLast_name(dto.getLast_name());
        instructors.setEmail(dto.getEmail());
        instructors.setPhone(dto.getPhone());
        instructors.setSpecialization(dto.getSpecialization());
        instructors.setAvailability(dto.getAvailability());
        return instructors;
    }

    public LessonsDTO getLessonsDTO(Lessons lessons){
        LessonsDTO dto=new LessonsDTO();
        dto.setLesson_id(lessons.getLessonId());
        dto.setLessonDate(lessons.getLessonDate());
        dto.setStartTime(lessons.getStartTime());
        dto.setEndTime(lessons.getEndTime());
        dto.setStudent_id(lessons.getStudents().getStudentId());
        dto.setCourse_id(lessons.getCourse().getCourse_id());
        dto.setInstructor_id(lessons.getInstructors().getInstructor_id());
        return dto;
    }

    public  Lessons getLessonsEntity(LessonsDTO dto){
        Lessons lessons=new Lessons();
        Instructors instructors=new Instructors();
        Course course=new Course();
        Students student=new Students();
        lessons.setLessonId(dto.getLesson_id());
        lessons.setLessonDate(dto.getLessonDate());
        lessons.setStartTime(dto.getStartTime());
        lessons.setEndTime(dto.getEndTime());
        student.setStudentId(dto.getStudent_id());
        lessons.setStudents(student);
        course.setCourse_id(dto.getCourse_id());
        lessons.setCourse(course);
        instructors.setInstructor_id(dto.getInstructor_id());
        lessons.setInstructors(instructors);
        return lessons;
    }

    public PaymentsDTO getPaymentsDTO(Payments payments){
        PaymentsDTO dto=new PaymentsDTO();
        dto.setPaymentId(payments.getPaymentId());
        dto.setPaymentDate(payments.getPaymentDate());
        dto.setAmount(payments.getAmount());
        dto.setAmount(payments.getAmount());
        dto.setPaymentMethod(payments.getPaymentMethod());
        dto.setStatus(payments.getStatus());
        dto.setStudentId(payments.getStudents().getStudentId());
        return dto;
    }

    public Payments getPaymentsEntity(PaymentsDTO dto){
        Payments payments=new Payments();
        Students students=new Students();
        payments.setPaymentId(dto.getPaymentId());
        payments.setPaymentDate(dto.getPaymentDate());
        payments.setAmount(dto.getAmount());
        payments.setPaymentMethod(dto.getPaymentMethod());
        payments.setStatus(dto.getStatus());
        students.setStudentId(dto.getStudentId());
        payments.setStudents(students);
        return payments;
    }

    public StudentsDTO getStudentsDTO(Students students){
        StudentsDTO dto=new StudentsDTO();
        dto.setStudentId(students.getStudentId());
        dto.setFirstName(students.getFirstName());
        dto.setLastName(students.getLastName());
        dto.setEmail(students.getEmail());
        dto.setPhone(students.getPhone());
        dto.setAddress(students.getAddress());
        dto.setDob(students.getDob());
        dto.setRegistrationDate(students.getRegistrationDate());
        dto.setCourses(
                students.getCourses()
                        .stream()
                        .map(this::getCourseDTO) // convert entity → DTO
                        .toList() // collect into List
        );
        return dto;
    }

    public Students getStudentsEntity(StudentsDTO dto){
        Students students=new Students();
        students.setStudentId(dto.getStudentId());
        students.setFirstName(dto.getFirstName());
        students.setLastName(dto.getLastName());
        students.setEmail(dto.getEmail());
        students.setPhone(dto.getPhone());
        students.setAddress(dto.getAddress());
        students.setDob(dto.getDob());
        students.setRegistrationDate(dto.getRegistrationDate());
        return students;
    }

    public UserDTO getUserDTO(User user){
        UserDTO dto=new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        return dto;
    }

    public User getUserEntity(UserDTO dto){
        User user=new User();
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setStatus(dto.getStatus());
        return user;
    }
}
