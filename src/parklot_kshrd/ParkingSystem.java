package parklot_kshrd;

import FileIO.UseFileOutPutStream;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.lang.classfile.Interfaces;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ParkingSystem {
    static void main(String[] args) {
        String regex = "^[0-9][A-Z]{2}-[0-9]{4}$";
        String regexFloorSpot = "^F\\d+-\\d+$";
        Scanner scanner = new Scanner(System.in);
        System.out.println("============ Setup Parking Lot System ============");
        System.out.println("--> Enter number of floors : ");
        Integer floors = Integer.parseInt(scanner.nextLine());
        System.out.println("--> Enter number of spots per floor ( 10-100 ) : ");
        Integer spots = Integer.parseInt(scanner.nextLine());
        String[][] parkingSpace = new String[floors][spots];
        parkingSpace[0][0] = "1GG-2507,0,0," + LocalDateTime.now().minusMinutes(30);
        parkingSpace[0][1] = "2KL-4839,0,1," + LocalDateTime.now().minusHours(1);
        parkingSpace[0][2] = "3AD-1902,0,2," + LocalDateTime.now().minusHours(2);
        parkingSpace[0][3] = "4AB-8888,0,3," + LocalDateTime.now().minusHours(5).minusMinutes(20);
       do {
           System.out.println("1 ) Parking Operation");
           System.out.println("2 ) View Parking Information");
           System.out.println("3 ) System Setting");
           System.out.println("4 ) Exit");
           System.out.println("Enter Opt : ");
           String op = scanner.nextLine();
           switch (op){
               case "1" : {
                   System.out.println("1 ) Check In");
                   System.out.println("2 ) Check Out");
                   System.out.println("3 ) Back");
                   System.out.println("Enter Opt : ");
                   String op1 = scanner.nextLine();
                   switch (op1) {
                       case "1" : {
                           System.out.println("========= Check In =========");
                           System.out.println("--> Enter vehicle palte number (e.g., 1ad-1902 or 2kl-4839 ) : ");

                           String plate ;
                           do{
                               plate=scanner.nextLine().toUpperCase();
                                if (plate.matches(regex)){
                                    break;
                                }
                               System.out.println("Please Enter Valid Plate (Ex : 1AD-1902 or 2KL-4839 ) !");
                           }while (true);
                           System.out.println("Checking nearest available parking space");
                           outer:
                           for (int i = 0 ; i < parkingSpace.length ; i++){
                               for (int j = 0 ; j < parkingSpace[i].length ; j++){

                                   if(parkingSpace[i][j]==null  ) {

                                       System.out.println("Vehicle parked successfully!");
                                       System.out.println("Vehicle : " + plate +"\nFloor :  " + i + "\nSpot : " + j + "\nEntry Time : " + LocalDateTime.now());

                                       parkingSpace[i][j] = plate + "," + i + "," + j + "," + LocalDateTime.now();
                                       break outer;
                                   }
                                   if(parkingSpace[i][j]!=null && parkingSpace[i][j].split(",")[0].equals(plate)){
                                       System.out.println("This Vechicle Is Already Park.");
                                       break outer;
                                   }

                               }
                               System.out.println();
                           }
                           break;
                       }
                       case "2" : {
                           System.out.println("========= Check Out =========");
                           System.out.println("--> Enter vehicle palte number (e.g., 1ad-1902 or 2kl-4839 ) : ");
                           String plate ;
                           do{
                               plate=scanner.nextLine().toUpperCase();
                               if (plate.matches(regex)){
                                   break;
                               }
                               System.out.println("Please Enter Valid Plate (Ex : 1AD-1902 or 2KL-4839 ) !");
                           }while (true);
                           outer:
                           for (int i = 0 ; i < parkingSpace.length ; i++){
                               for (int j = 0 ; j < parkingSpace[i].length ; j++){
                                    if(parkingSpace[i][j] != null &&  parkingSpace[i][j].split(",")[0].equals(plate)){
                                        System.out.println("Vehicle Found!");
                                        LocalDateTime entryTime = LocalDateTime.parse(parkingSpace[i][j].split(",")[3]);
                                        LocalDateTime exitTime = LocalDateTime.now();
                                        Duration duration = Duration.between(entryTime, exitTime);

                                        long totalMinutes = duration.toMinutes();
                                        long hours = totalMinutes / 60;
                                        long minutes = totalMinutes % 60;
                                        long billableHours = (long) Math.ceil(totalMinutes / 60.0);
                                        double fee;
                                        if (billableHours <= 1) {
                                            fee = 0.50;
                                        } else {
                                            fee = 0.50 + (billableHours - 1) * 1.50;
                                        }
                                        if (fee > 10) {
                                            fee = 10;
                                        }
                                        System.out.println("Floor : " + i + " | " + " Spot : " + j);
                                        System.out.println("Entry Time : " + entryTime );
                                        System.out.println("Exit Time : " + LocalDateTime.now());
                                        System.out.println("\n\nParking Duration : " + hours + "hours " + minutes + " minutes");
                                        System.out.println("Parking Fee : $"+fee);

                                        parkingSpace[i][j] = null;

                                        break outer;
                                    }
                               }
                               System.out.println();
                           }
                           break;
                       }
                       case "3" : break;
                   }
                break;
               }
               case "2" :{
                   Table head =  new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                   Table table =  new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                   head.addCell("Display All Floors Report",5);
                   table.addCell("Floor Id");
                   table.addCell("Total Spot");
                   table.addCell("Occupied Spots");
                   table.addCell("Available Spot");
                   table.addCell("Rate");
                   for (Integer i = 0 ; i < parkingSpace.length ; i++){
                       Integer occupiedSpot = 0;
                       for (Integer j = 0 ; j < parkingSpace[i].length ; j++){
                           if(parkingSpace[i][j] !=null){
                               occupiedSpot++;
                           }
                       }
                       System.out.println();
                       Integer totalSpot = parkingSpace[i].length;
                       Integer availableSpot = totalSpot - occupiedSpot;

                       table.addCell(i.toString());
                       table.addCell(totalSpot.toString());
                       table.addCell(occupiedSpot.toString());
                       table.addCell(availableSpot.toString());
                       table.addCell("0%");
                   }
                   System.out.println(head.render());
                   System.out.println(table.render());

                   System.out.println("1 ) Display spot by floor ");
                   System.out.println("2 ) Search For Parked Car ");
                   System.out.println("0 ) Back ");
                   System.out.println("Please Input Your Choices (1-7) : ");
                   String opt = scanner.nextLine();
                   switch (opt) {
                       case "1":{
                           System.out.println("--> Enter the floor : ");
                           Integer floor = Integer.parseInt(scanner.nextLine());
                           Table floorDetails =  new Table(8, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                           floorDetails.addCell("Floor #"+floor,8);
                           for (int i = 0 ; i < parkingSpace[floor].length;i++){
                                if (parkingSpace[floor][i] != null) {
                                    String plateOnly = parkingSpace[floor][i].split(",")[0];
                                    floorDetails.addCell("F" + floor + "-" + i + "[" + plateOnly + "]");
                               } else {
                                    floorDetails.addCell("F" + floor + "-" + i + "[EMPTY]");
                               }
                            }
                           System.out.println(floorDetails.render());

                           System.out.println("1 ) Move parking spot \t2 ) Update parking spot status \t3) Back");
                           System.out.println("Please input your choice (1-3) : ");
                           String choice = scanner.nextLine();
                           switch (choice){
                               case "1" : {
                                   System.out.println("--> Enter vehicle plate number to move : ");
                                   String plate ;
                                   do{
                                       plate=scanner.nextLine().toUpperCase();
                                       if (plate.matches(regex)){
                                           break;
                                       }
                                       System.out.println("Please Enter Valid Plate (Ex : 1AD-1902 or 2KL-4839 ) !");
                                   }while (true);



                                   outer:
                                   for (int i = 0 ; i < parkingSpace.length ; i++){
                                       for (int j = 0 ; j < parkingSpace[i].length ; j++){
                                           if (parkingSpace[i][j] != null && parkingSpace[i][j].split(",")[0].equals(plate)) {
                                               System.out.println("Vehicle Found!");

                                               System.out.println("--> Enter spot's ID (Ex : F1-21) : ");
                                               String regSpot ;
                                               do{
                                                   regSpot=scanner.nextLine().toUpperCase();
                                                   if (regSpot.matches(regexFloorSpot)){
                                                       break;
                                                   }
                                                   System.out.println("Please Enter Valid Floor And Spot (Ex : F2-10 Or F12-1 ) !");
                                               }while (true);





                                               String[] parts = regSpot.substring(1).split("-");
                                               int newFloor = Integer.parseInt(parts[0]);
                                               int newSpot  = Integer.parseInt(parts[1]);

                                               if (parkingSpace[newFloor][newSpot] != null){
                                                   System.out.println("This spot already parked by other vehicle!");
                                                   break;
                                               }

                                               String oldFloor = parkingSpace[i][j].split(",")[1];
                                               String oldSpot = parkingSpace[i][j].split(",")[2];

                                               System.out.println("Old =>> Floor : " + oldFloor + " | " + " Spot : " + oldSpot);
                                               System.out.println("Move To =>> Floor : " + newFloor + " | " + " Spot : " + newSpot);

                                               parkingSpace[newFloor][newSpot] = parkingSpace[i][j];
                                               parkingSpace[i][j] = null;

                                               System.out.println("Vehicle moves to new spot successfully!");

                                               break outer;
                                           }
                                       }
                                       System.out.println();
                                   }





                                   break;
                               }
                               case "3" : break;
                           }
                            break;
                       }
                       case "2":{
                           System.out.println("============= Search For Parked Car =============");
                           System.out.println("Enter Plate Number : ");
                           String plate ;
                           do{
                               plate=scanner.nextLine().toUpperCase();
                               if (plate.matches(regex)){
                                   break;
                               }
                               System.out.println("Please Enter Valid Plate (Ex : 1AD-1902 or 2KL-4839 ) !");
                           }while (true);
                           outer:
                           for (int i = 0 ; i < parkingSpace.length ; i++){
                               for (int j = 0 ; j < parkingSpace[i].length ; j++){
                                   if (parkingSpace[i][j] != null && parkingSpace[i][j].split(",")[0].equals(plate)) {
                                       String floor = parkingSpace[i][j].split(",")[1];
                                       String spot = parkingSpace[i][j].split(",")[2];
                                       String entryTime = parkingSpace[i][j].split(",")[3];
                                       System.out.println("Vehicle Found!");
                                       System.out.println("---------------------------------------------");
                                       System.out.println("Floor : " + floor + " | " + " Spot : " + spot);
                                       System.out.println("Entry Time : " + entryTime);
                                       System.out.println("---------------------------------------------");
                                       break outer;
                                   }
                               }
                               System.out.println();
                           }

                           break;
                       }
                       case "0":break;
                   }
                   break;
               }
               case "4" : return;
           }
       }while (true);
    }
}
