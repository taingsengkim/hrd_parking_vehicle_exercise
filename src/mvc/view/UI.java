package mvc.view;

import mvc.controller.StudentController;
import mvc.model.dto.StudentCreateDto;
import mvc.model.dto.StudentResponseDto;
import mvc.model.dto.StudentUpdateDto;
import mvc.model.repository.StudentRepository;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.awt.desktop.SystemEventListener;
import java.lang.foreign.SymbolLookup;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {

    private static void thumnail(){
        System.out.println("""
                ================= Student Management =================
                1 ) Create Student
                2 ) Search Student By UUID
                3 ) Update Student By UUID
                4 ) Delete Student By UUID
                5 ) Find All Students 
                0 ) Exit !
                """);

    }
        public static void getUI(){
      do {
          StudentController studentController = new StudentController();

          thumnail();
          System.out.println("[+] Insert Option : ");
          int opt = new Scanner(System.in).nextInt();

          switch (opt ){
              case 1 ->{
                  System.out.println("[+] Create user feature");
                  System.out.print("[+] Insert student's name: ");
                  String name = new Scanner(System.in).nextLine();
                  System.out.print("[+] Insert student's email: ");
                  String email = new Scanner(System.in).nextLine();
                  System.out.print("[+] Insert student's password: ");
                  String pass = new Scanner(System.in).nextLine();
                  System.out.println("[+] Insert birth of date: YYY-MMM-DD");
                  System.out.print("[+] Insert year: ");
                  Integer year = new Scanner(System.in).nextInt();
                  System.out.print("[+] Insert month: ");
                  Integer month = new Scanner(System.in).nextInt();
                  System.out.print("[+] Insert day: ");
                  Integer day = new Scanner(System.in).nextInt();
                  LocalDate bod = LocalDate.of(year,month,day);
                  StudentCreateDto studentCreateDto
                          = new StudentCreateDto(name,email,pass,bod);
                  StudentResponseDto studentResponseDto
                          = studentController.createNewStudent(studentCreateDto);
                  System.out.println(studentResponseDto);
              }
              case 2->{
                  System.out.println("Enter Student UUID : ");
                  String uuid = new Scanner(System.in).nextLine();
                  StudentResponseDto stuDto = studentController.findByUUID(uuid);
                  StudentDetails(stuDto);

              }
              case 3->{
                  System.out.println("Enter Student UUID For Update : ");
                  String uuid = new Scanner(System.in).nextLine();
                  if(studentController.findByUUID(uuid).uuid()==null){
                      System.out.println("User with this id doesn't exist!");
                      return;
                  }
                  System.out.print("[+] Insert student's name: ");
                  String name = new Scanner(System.in).nextLine();
                  System.out.print("[+] Insert student's email: ");
                  String email = new Scanner(System.in).nextLine();
                  System.out.print("[+] Insert student's profile: ");
                  String profile = new Scanner(System.in).nextLine();
                  System.out.println("[+] Insert birth of date: YYY-MMM-DD");
                  System.out.print("[+] Insert year: ");
                  Integer year = new Scanner(System.in).nextInt();
                  System.out.print("[+] Insert month: ");
                  Integer month = new Scanner(System.in).nextInt();
                  System.out.print("[+] Insert day: ");
                  Integer day = new Scanner(System.in).nextInt();
                  LocalDate bod = LocalDate.of(year,month,day);
                  StudentUpdateDto studentUpdateDto = new StudentUpdateDto(name,email,profile,bod);
                    if(studentController.updateByUUID(studentUpdateDto)>0){
                        System.out.println("Student Updated Successfully!");
                        break;
                    }
                  System.out.println("Failed To Updated!");
              }
              case 4->{
                  System.out.println("Enter Student UUID To Delete : ");
                  String uuid= new Scanner(System.in).nextLine();
                  System.out.println(
                          studentController.deleteByUUID(uuid)>0
                                  ?"Student deleted successfully!"
                                  :"Failed to deleted student!");
              }
              case 5 ->{
                  System.out.println("\n\n");
                   List<StudentResponseDto> studentResponseDtos = studentController.findAll();
                  Table table = new Table(6, BorderStyle.CLASSIC_COMPATIBLE);
                  table.addCell("UUID");
                  table.addCell("EMAIL");
                  table.addCell("USERNAME");
                  table.addCell("PROFILE");
                  table.addCell("CARD ID");
                  table.addCell("Date Of Birth");
                   for (StudentResponseDto studentResponseDto : studentResponseDtos){
                       table.addCell(studentResponseDto.uuid());
                       table.addCell(studentResponseDto.email());
                       table.addCell(studentResponseDto.username());
                       table.addCell(studentResponseDto.profile());
                       table.addCell(studentResponseDto.cardId());
                        table.addCell(studentResponseDto.birthOfDate().toString());
                   }
                  System.out.printf(table.render());
                  System.out.println("\n\n");
              }
              default -> {
                  System.out.println("Invali Option!");
              }
          }

      }while (true);
    }

    public static void ListStudentDetail(List<StudentResponseDto> studentResponseDtos){
        Table table = new Table(6, BorderStyle.CLASSIC_COMPATIBLE);
        table.addCell("UUID");
        table.addCell("EMAIL");
        table.addCell("USERNAME");
        table.addCell("PROFILE");
        table.addCell("CARD ID");
        table.addCell("Date Of Birth");
        for (StudentResponseDto responseDto : studentResponseDtos){
            table.addCell(responseDto.uuid());
            table.addCell(responseDto.email());
            table.addCell(responseDto.username());
            table.addCell(responseDto.profile());
            table.addCell(responseDto.cardId());
            table.addCell(responseDto.birthOfDate().toString());
        }
        System.out.printf(table.render());
        System.out.println("\n\n");
    }

    public static void StudentDetails(StudentResponseDto studentResponseDto){
        Table table = new Table(6, BorderStyle.CLASSIC_COMPATIBLE);
        table.addCell("UUID");
        table.addCell("EMAIL");
        table.addCell("USERNAME");
        table.addCell("PROFILE");
        table.addCell("CARD ID");
        table.addCell("Date Of Birth");
            table.addCell(studentResponseDto.uuid());
            table.addCell(studentResponseDto.email());
            table.addCell(studentResponseDto.username());
            table.addCell(studentResponseDto.profile());
            table.addCell(studentResponseDto.cardId());
            table.addCell(studentResponseDto.birthOfDate().toString());
        System.out.printf(table.render());
        System.out.println("\n\n");
    }
}
