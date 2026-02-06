package mvc.controller;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.repository.StudentRepository;
import mvc.model.service.StudentService;
import mvc.model.service.StudentServiceImpl;

import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentServiceImpl();
    public StudentResponseDto createNewStudent(StudentCreateDto studentCreateDto){
        return studentService.createStudent(studentCreateDto);
    }
    public List<StudentResponseDto> findAll(){
        return studentService.getAllStudents();
    }
    public StudentResponseDto findByUUID(String uuid){
        return studentService.findByUUID(uuid);
    }
    public int deleteByUUID(String uuid){
        return studentService.deleteByUUID(uuid);
    }

    public int updateByUUID( StudentUpdateDto studentUpdateDto){
        return studentService.updateStudentByUuid(studentUpdateDto);
    }
}
