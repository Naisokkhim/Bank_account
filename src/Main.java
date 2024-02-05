
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EmployeeInfomation employeeInfomation = new EmployeeInfomation();
        ArrayList<EmployeeInfomation> employees = new ArrayList<>();
        employees.add(new EmployeeInfomation("Sok Khim","Remote","Front-end",8,true));
        employees.add(new EmployeeInfomation("Danin","On-site","UX/UI",8,true));
        employees.add(new EmployeeInfomation("Leak","Remote","Back-end",8,true));
        employees.add(new EmployeeInfomation("Channy","On-site","Front-end",8,true));
        employees.add(new EmployeeInfomation("Vannareak","On-site","Back-end",8,true));
        User user = new User();
        shift Shift = new shift();
        String post;
        String EmployeeOption;
        do {
            System.out.println("=======> Login <=======");
            System.out.print("User Name : ");
            String Username = in.next();
            System.out.print("Password  : ");
            String Password = in.next();
            post = user.UserAuthentication(Username,Password,employees);
               String option;
            if (post.equals("Manager")){
                do {
                    System.out.println("======> "+post+" <======");
                    System.out.println("1. Create Shift. ");
                    System.out.println("2. View Shift Schedule. ");
                    System.out.println("3. View Employee info.");
                    System.out.println("4. Logout .");
                    System.out.println("Exit.");
                    System.out.print("Please make a choice : "); option = in.nextLine();
                    if (option.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting System.......");
                        return;
                    } else if (option.equalsIgnoreCase("1")) {
                        System.out.println("=====> [ Create Shift ] <=====");
                        Shift.ShiftToEmployee(employees);
                    } else if (option.equalsIgnoreCase("2")) {
                        Shift.Show(employees);
                        int ShiftOption ;
                            System.out.println("1. Show all Shift  ");
                            System.out.println("2. Back");
                            ShiftOption = in.nextInt();in.nextLine();
                            if(ShiftOption == 1){
                                Shift.ShowAllShift();
                                Shift.modifyShift(employees);
                            }else {
                                break;
                            }

                    } else if (option.equalsIgnoreCase("3")) {
                        employeeInfomation.Show(employees);
                    } else if (option.equalsIgnoreCase("4")) {
                        break;
                    } else {
                        System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
                    }
                } while(!option.equalsIgnoreCase("4"));
            }
            for (EmployeeInfomation em: employees
                 ) {
                if (post.equals(em.getName())){
                    do {
                        System.out.println("======> "+post+" <======");
                        System.out.println("1. View Shift Schedule. ");
                        System.out.println("2. Logout .");
                        System.out.println("Exit.");
                        System.out.print("Please make a choice : ");
                        EmployeeOption = in.nextLine();
                        if (EmployeeOption.equalsIgnoreCase("exit")){
                            return;
                        } else if (EmployeeOption.equalsIgnoreCase("1")) {
                            Shift.Show(employees);

                        } else if (EmployeeOption.equalsIgnoreCase("2")) {
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }while(!EmployeeOption.equalsIgnoreCase("2"));
                }
            }
        } while(!post.equalsIgnoreCase("Exit"));
        in.close();
    }

}