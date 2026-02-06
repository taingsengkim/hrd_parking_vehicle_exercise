package mvc.model.service;

import mvc.mapper.StudentMapper;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;
import mvc.model.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository = new StudentRepository();
    private StudentMapper mapper = new StudentMapper();

    @Override
    public StudentResponseDto createStudent(StudentCreateDto studentCreateDto) {
        Student student = mapper.mapFromStudentCreateDtoToStudent(studentCreateDto);
        studentRepository.save(student);
        return mapper.mapFromStudentToStudentResponseDto(student);
    }


    @Override
    public int updateStudentByUuid( StudentUpdateDto studentUpdateDto) {

        int affected = studentRepository.update(mapper.mapFromStudentUpdateDtoToStudent(studentUpdateDto));
        if(affected>0){
            return 1;
        }
        return 0;
    }


    @Override
    public List<StudentResponseDto> searchStudentByName(String name) {
        return List.of();
    }

    @Override
    public List<StudentResponseDto> getAllStudents()
    {
        return studentRepository.findAll().stream().map(mapper::mapFromStudentToStudentResponseDto).toList();
    }

    @Override
    public StudentResponseDto findByUUID(String uuid) {

        return mapper.mapFromStudentToStudentResponseDto(studentRepository.findByUUID(uuid));
    }

    @Override
    public int deleteByUUID(String uuid) {
        return studentRepository.delete(uuid);
    }
}
