package mvc.model.repository;

import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.entities.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentRepository {
    private final String url = "jdbc:postgresql://localhost:5432/scool_db";
    private final String username = "postgres";
    private final String password = "04182022";




    public List<Student> findAll(){
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                ){
            String SQL = """
                SELECT * FROM students;
            """;
            Statement statement = connection.createStatement();
            ResultSet rs=  statement.executeQuery(SQL);
            List<Student> students = new ArrayList<>();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setUuid(rs.getString("uuid"));
                student.setUsername(rs.getString("user_name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setProfile(rs.getString("profile"));
                student.setCardId(rs.getString("card_id"));
                student.setBirthOfDate(rs.getDate("birth_of_date").toLocalDate());
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            throw new RuntimeException("Error From Repo ( Find All ) : " + e.getMessage());
        }
    }

    public  Student findByUUID(String uuid){
        try(Connection connection = DriverManager.getConnection(url,username,password)) {
            String SQL = """
                     SELECT * FROM students WHERE uuid = ?;
                     """;
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1,uuid);
            ResultSet rs = pstm.executeQuery();
            Student student = new Student();
            if (rs.next()){
                student.setId(rs.getInt("id"));
                student.setUuid(rs.getString("uuid"));
                student.setUsername(rs.getString("user_name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setProfile(rs.getString("profile"));
                student.setCardId(rs.getString("card_id"));
                student.setBirthOfDate(rs.getDate("birth_of_date").toLocalDate());
            }
            return student;
        }catch (SQLException e){
            throw new RuntimeException("Error From Repo ( Find By ID ) :" + e.getMessage());
        }
    }


    public Boolean save(Student student){
        try(Connection connection = DriverManager.getConnection(url,username,password)) {
            String SQL = """
                    INSERT INTO students  (uuid, user_name, email, password,birth_of_date)
                    VALUES (?,?,?,?,?);                   
            """;
            System.out.println(student);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, student.getUuid());
            pstm.setString(2,student.getUsername());
            pstm.setString(3,student.getEmail());
            pstm.setString(4,student.getPassword());
            pstm.setDate(5, Date.valueOf(student.getBirthOfDate()));
            int rowAffected = pstm.executeUpdate();
            if(rowAffected>0){
              return true;
            }
            return false;
        }catch (SQLException e){
            throw new RuntimeException("Error From Repo ( Save ) : " + e.getMessage());
        }
    }

    public int delete(String uuid){
        try(Connection connection = DriverManager.getConnection(url,username,password)){
            String SQL = """
                        DELETE FROM students
                        WHERE uuid = ?;
                    """;
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1,uuid);
            int rowAffected = pstm.executeUpdate();
            if(rowAffected>0){
                return 1;
            }
            return 0;
        }catch (SQLException e){
            throw new RuntimeException("Error From Repo ( Delete ) " + e.getMessage());
        }
    }
    public int update(Student student){
        try(Connection connection = DriverManager.getConnection(url,username,password)) {
            String SQL = """
                  UPDATE students
                    SET user_name =?, email = ?,  password = ?,profile = ?, card_id = ?, birth_of_date = ?
                     WHERE uuid = ?;
                                                          
            """;
            System.out.println(student);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, student.getUsername());
            pstm.setString(2,student.getEmail());
            pstm.setString(3,student.getPassword());
            pstm.setString(4,student.getProfile());
            pstm.setString(5,student.getCardId());
            pstm.setDate(6, Date.valueOf(student.getBirthOfDate()));
            pstm.setString(7, student.getUuid());
            int rowAffected = pstm.executeUpdate();
            if(rowAffected>0){
                return 1;
            }
            return 0;
        }catch (SQLException e){
            throw new RuntimeException("Error From Repo ( Update ) : " + e.getMessage());
        }    }
}
