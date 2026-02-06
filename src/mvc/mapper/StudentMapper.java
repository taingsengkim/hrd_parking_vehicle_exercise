package mvc.mapper;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;
import mvc.model.repository.StudentRepository;

import java.util.Random;
import java.util.UUID;

public class StudentMapper {
    public Student mapFromStudentCreateDtoToStudent(StudentCreateDto studentCreateDto){
        Student student = new Student();
        student.setId(new Random().nextInt(99999999));
        student.setUuid(UUID.randomUUID().toString());
        student.setUsername(studentCreateDto.username());
        student.setEmail(studentCreateDto.email());
        student.setPassword(studentCreateDto.password());
        student.setProfile(null);
        student.setBirthOfDate(studentCreateDto.birthOfDate());
        return student;
    }

    public StudentResponseDto mapFromStudentToStudentResponseDto(Student student){
        StudentResponseDto studentResponseDto = new StudentResponseDto(
                student.getUuid(),
                student.getUsername(),
                student.getEmail(),
                student.getProfile(),
                student.getCardId(),
                student.getBirthOfDate()
        );
        return studentResponseDto;
    };

    public Student mapFromStudentUpdateDtoToStudent(StudentUpdateDto studentUpdateDto){
        Student student = new Student();
        student.setUsername(studentUpdateDto.username());
        student.setEmail(studentUpdateDto.email());
        student.setProfile(studentUpdateDto.profile());
        student.setBirthOfDate(studentUpdateDto.birthOfDate());
        return student;
    }
}
