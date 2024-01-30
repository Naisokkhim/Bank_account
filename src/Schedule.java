import java.util.ArrayList;

public class Schedule {
    EmployeeInfomation em = new EmployeeInfomation();
    shift shift = new shift();
    public void Show(ArrayList<EmployeeInfomation> emArray,shift shift){
        System.out.println("==========> Shift Schedule <==========");
        System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n","EmployeeName|","ShiftName|","Start-End|","BreakTime|","maximumTeamMember|");
        for (EmployeeInfomation employee : emArray) {
            shift employeeShift = employee.getShift();
            if (employeeShift != null) {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), employeeShift.getShiftName(), employeeShift.getStart(), employeeShift.getBreakTime(), employeeShift.getMaximumEmployee());
            } else {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), "No shift assigned", "", "", "");
            }
        }
    }

}
