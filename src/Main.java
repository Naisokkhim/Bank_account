
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EmployeeInfomation employeeInfomation = new EmployeeInfomation();
        ArrayList<EmployeeInfomation> employees = new ArrayList<>();
        employees.add(new EmployeeInfomation("Sokkhim","Night","Front-end",8,true));
        employees.add(new EmployeeInfomation("Danin","Day","UX/UI",8,true));
        employees.add(new EmployeeInfomation("Leak","Night","Back-end",8,true));
        employees.add(new EmployeeInfomation("Channy","Day","Front-end",8,true));
        employees.add(new EmployeeInfomation("Vannareak","Night","Back-end",8,true));
        User user = new User();
        shift Shift = new shift();
        Schedule schedule = new Schedule();
        String post;
        String EmployeeOption="";
        do {
            System.out.println("=======> Login <=======");
            System.out.print("User Name : ");
            String Username = in.nextLine();
            System.out.print("Password  : ");
            String Password = in.nextLine();
            post = user.UserAuthentication(Username,Password);
            if (post.equals("Manager")){
                String option;
                do {
                    System.out.println("======> "+post+" <======");
                    System.out.println("1. Create Shift. ");
                    System.out.println("2. View Schedule. ");//1. delete shift
                                                             //2. add employee to exist shift
                    System.out.println("3. View Employee info.");
                    System.out.println("4. Logout .");
                    System.out.println("Exit.");
                    System.out.println("Please make a choice : ");
                    option = in.nextLine();
                    if (option.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting System.......");
                        return;
                    } else if (option.equalsIgnoreCase("1")) {
                        Shift.ShiftToEmloyee(employees);
                    } else if (option.equalsIgnoreCase("2")) {
                        schedule.Show(employees,Shift);
                    } else if (option.equalsIgnoreCase("3")) {
                        employeeInfomation.Show(employees);
                    } else if (option.equalsIgnoreCase("4")) {
                        break;
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                } while(!option.equalsIgnoreCase("4"));
            } else if (post.equals("Employee")){
                do {

                    System.out.println("======> "+post+" <======");
                    System.out.println("1. View Schedule. ");
                    System.out.println("2. Logout .");
                    System.out.println("Exit.");
                    System.out.print("Please make a choice : ");
                    EmployeeOption = in.next();
                    if (EmployeeOption.equalsIgnoreCase("exit")){
                        return;
                    } else if (EmployeeOption.equalsIgnoreCase("1")) {
                        schedule.Show(employees,Shift);
                    } else if (EmployeeOption.equalsIgnoreCase("2")) {
                        break;
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                }while(!EmployeeOption.equalsIgnoreCase("2"));
            }
        } while(!post.equalsIgnoreCase("Exit"));
        in.close();
    }

}