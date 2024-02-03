import java.util.ArrayList;

public class User {
    public String UserAuthentication(String UserName, String Password, ArrayList<EmployeeInfomation> em) {
        for (EmployeeInfomation ems: em) {
            if(UserName.equals("TestManager") && Password.equals("Test123")){
                return "Manager";
            }
            if (UserName.equalsIgnoreCase(ems.getName()) && Password.equals("Test321")){
                return ems.getName();
            }
        }
        System.out.println("Invalid username or password !!");
        return "Invalid";
    }
}
