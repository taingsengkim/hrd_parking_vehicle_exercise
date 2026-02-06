package mvc.model.service;

import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;

import java.lang.classfile.attribute.LineNumberTableAttribute;
import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentCreateDto studentCreateDto);
    int updateStudentByUuid(StudentUpdateDto studentUpdateDto);
    List<StudentResponseDto> searchStudentByName(String name);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto findByUUID(String uuid);
    int deleteByUUID(String uuid);
}

