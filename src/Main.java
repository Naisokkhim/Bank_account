
import java.util.ArrayList;
import java.util.Scanner;
// 1 shift maybe 1week
// 1 shift can be many group
// Employee can work in two shift but not in the same day

public class Main {
    public  static  String UserLogin(String UserName,String Password){

        String post;
          if(UserName.equals("TestManager") && Password.equals("Test123")){
              return post = "Manager";
          }
          else if (UserName.equals("TestEmployee") && Password.equals("Test321")){
              return post = "Employee";
          }else{
              System.out.println("Wrong username or password !!");
              return post = "";
          }
    }
    public static void ShiftToEmloyee(ArrayList<EmployeeInfomation> E,shift s){
        Scanner in = new Scanner(System.in);
        System.out.println("=====> Create Shift <=====");
        System.out.print("Enter Shift name       : ");String ShiftName = in.next();
        System.out.print("Enter Start Time       : ");String Start = in.next();
        System.out.print("Enter Break time       : ");String BreakTime = in.next();
        System.out.print("Enter Maximum member   : ");int maxMem = in.nextInt();
        new shift(ShiftName,Start,BreakTime,maxMem);
        for (EmployeeInfomation e : E) {
            System.out.println(e.getName());
        }
        System.out.println("Please Choose An employee Handle the Shift : ");int ChooseEm = in.nextInt();
        E.get(ChooseEm).setShift(s);
        System.out.println("Shift has been add too "+E.get(ChooseEm).getName()+" Schedule !!!");
        in.close();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<EmployeeInfomation> employee = new ArrayList<>();
        employee.add(new EmployeeInfomation("Sokkhim","Night","Front-end",8));
        employee.add(new EmployeeInfomation("Danin","Day","UX/UI",8));
        employee.add(new EmployeeInfomation("Leak","Night","Back-end",8));
        employee.add(new EmployeeInfomation("Channy","Day","Front-end",8));
        employee.add(new EmployeeInfomation("Vannareak","Night","Back-end",8));
        EmployeeInfomation employeeInfomation = new EmployeeInfomation();
        shift Shift = new shift();

        ShiftToEmloyee(employee,Shift);
       // employeeInfomation.Show(employee);

//        System.out.println("=======> Login <=======");
//        System.out.println("User Name : ");String Username = in.nextLine();
//        System.out.println("Password  : ");String Password = in.nextLine();
//        String post = UserLogin(Username,Password);
//        if (post.equals("Manager")){
//            Manager can do here
//            System.out.println("======> "+post+" <======");
//            System.out.println("1. Create Shift. ");
//            System.out.println("2. Use An Exist Shift.");
//            System.out.println("3. View Schedule. ");
//            System.out.println("4. View Employee info.");
//            System.out.println("Exit.");
//
//
//
//        }else if (post.equals("Employee")){
//            System.out.println("this is "+post);
//            // Employee can do here
//        }

    in.close();
    }

}