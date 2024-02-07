import java.util.ArrayList;

public class User {
    public String UserAuthentication(String UserName, String Password, ArrayList<EmployeeInfomation> em) {
        if(UserName.equalsIgnoreCase("TestManager") && Password.equalsIgnoreCase("Test123")){
            return "Manager";
        }
        for (EmployeeInfomation ems: em) {
               if(UserName.equalsIgnoreCase(ems.getName()) && Password.equalsIgnoreCase("Test321")){
                    return ems.getName();
                }
        }
        System.out.println(" Wrong Username or password !!!");
        return "";
    }
}