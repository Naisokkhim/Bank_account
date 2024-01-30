public class User extends EmployeeInfomation{

    public String UserAuthentication(String UserName,String Password){

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
}
