
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EmployeeInfomation employeeInfomation = new EmployeeInfomation();
        ArrayList<EmployeeInfomation> employees = new ArrayList<>();
        employees.add(new EmployeeInfomation("SokKhim","Remote","Front-end",8,true));
        employees.add(new EmployeeInfomation("Danin","On-site","UX/UI",8,true));
        employees.add(new EmployeeInfomation("Leak","Remote","Back-end",8,true));
        employees.add(new EmployeeInfomation("Channy","On-site","Front-end",8,true));
        employees.add(new EmployeeInfomation("Kisin","On-site","Back-end",8,true));
        employees.add(new EmployeeInfomation("Narin","Remote","Front-end",8,true));
        employees.add(new EmployeeInfomation("Ayato","On-site","UX/UI",8,true));

        User user = new User();
        shift Shift = new shift();
        String post,EmployeeOption,option ;
        do {
//            System.out.println("=======> Login <=======");
//            System.out.print("User Name : ");
//            String Username = in.nextLine();
//            System.out.print("Password  : ");
//            String Password = in.nextLine();
//            post = user.UserAuthentication(Username,Password,employees);
            post = "Manager";
            if (post.equals("Manager")){

                do {
                    System.out.println("\n======> "+post+" <======");
                    System.out.println("1. Create Shift. ");
                    System.out.println("2. View Shift Schedule. ");
                    System.out.println("3. View Employee info.");
                    System.out.println("4. Logout .");
                    System.out.println("Exit.");
                    System.out.print("Please make a choice : ");
                    option = in.nextLine().toLowerCase();
                    switch (option) {
                        case "1":
                            System.out.println("=====> [ Create Shift ] <=====");
                            Shift.ShiftToEmployee(employees,in);
                            break;
                        case "2":
                            Shift.Show(employees);
                            int ShiftOption;
                            if(!Shift.getShiftHistory().isEmpty()){
                                System.out.println("1. Show all Shift  ");
                                System.out.println("2. Back");
                                System.out.print("Please make a choice : ");
                                ShiftOption = in.nextInt(); in.nextLine();
                                if(ShiftOption == 1){
                                    Shift.ShowAllShift();
                                    Shift.modifyShift(employees,in);
                                } else {
                                    System.out.println("========================\n");
                                }
                            }
                            break;
                        case "3":
                            employeeInfomation.Show(employees);
                            break;
                        case "4":
                            break;
                        case "exit":
                            System.out.println("Exiting System.......");
                            return;
                        default:
                            System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m");
                            break;
                    }
                } while(!option.equals("4"));

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
