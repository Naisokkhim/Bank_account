
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EmployeeInfomation employeeInfomation = new EmployeeInfomation();
        ArrayList<EmployeeInfomation> employees = new ArrayList<>();
        employees.add(new EmployeeInfomation("SokKhim","Remote","Front-end",8,true));
        employees.add(new EmployeeInfomation("Danin","On-site","UX/UI",8,true));
        employees.add(new EmployeeInfomation("VorLeak","Remote","Back-end",8,true));
        employees.add(new EmployeeInfomation("Channy","On-site","Front-end",8,true));
        employees.add(new EmployeeInfomation("Kisin","On-site","Back-end",8,true));
        employees.add(new EmployeeInfomation("Narin","Remote","Front-end",8,true));
        employees.add(new EmployeeInfomation("Ayato","On-site","UX/UI",8,true));

        User user = new User();
        shift Shift = new shift();
        String post,EmployeeOption,option ;
        //Shift.autoGenerateShift(in);
        do {
//            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
//            System.out.println("\u001B[32m[]      \u001B[34m       Login       \u001B[0m     \u001B[32m[]\u001B[0m");
//            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
//            System.out.print("User Name : ");
//            String Username = in.nextLine();
//            System.out.print("Password  : ");
//            String Password = in.nextLine();
//            post = user.UserAuthentication(Username,Password,employees);
            post = "Manager";
            if (post.equals("Manager")){

                do {
                    System.out.println("\n\n");
                    System.out.println("\u001B[32m[]===============================[]\u001B[0m");
                    System.out.println("\u001B[32m[]      \u001B[34m      "+post+"       \u001B[0m     \u001B[32m[]\u001B[0m");
                    System.out.println("\u001B[32m[]===============================[]\u001B[0m");
                    System.out.println("1. Create Shift. ");
                    System.out.println("2. Generate Shift for employee");
                    System.out.println("3. View Shift ");
                    System.out.println("4. View Schedule. ");
                    System.out.println("5. View Employee info.");
                    System.out.println("6. Logout .");
                    System.out.println("Exit.");
                    System.out.print("\u001B[32mPlease make a choice : \u001B[0m");
                    option = in.nextLine().toLowerCase();
                    switch (option) {
                        case "1":
                            System.out.println("=====> [ Create Shift ] <=====");
                            Shift.createShift(employees,in);
                            break;
                        case "2" :

                            break;
                        case "3" :
                                   Shift.ShowAllShift();
                                   Shift.modifyShift(employees,in);
                                   break;
                        case "4":
                            Shift.Show(employees);
                            break;
                        case "5":
                            employeeInfomation.Show(employees);
                            break;
                        case "6":
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
