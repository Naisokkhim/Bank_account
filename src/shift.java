
import java.util.ArrayList;
import java.util.Scanner;
public class shift {
    private String ShiftName;
    private String startToEnd;
    private String breakTime;
    private int maximumEmployee;
    private String Description;

    public String getStartToEnd() {
        return startToEnd;
    }

    public void setStartToEnd(String startToEnd) {
        this.startToEnd = startToEnd;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<shift> getShiftHistory() {
        return shiftHistory;
    }

    public void setShiftHistory(ArrayList<shift> shiftHistory) {
        this.shiftHistory = shiftHistory;
    }
    private ArrayList<shift> shiftHistory = new ArrayList<>();
        private ArrayList<EmployeeInfomation> assignedEmployees = new ArrayList<>();

    public ArrayList<EmployeeInfomation> getAssignedEmployees() {
        return assignedEmployees;
    }

    private String[] Member ;
    public shift(String name, String startToEnd, String BreakTime, int maximumEmployee,String Description) {
        this.ShiftName = name;
        this.startToEnd = startToEnd;
        this.breakTime = BreakTime;
        this.maximumEmployee = maximumEmployee;
        this.Description = Description;
    }
    public void ShiftToEmloyee(ArrayList<EmployeeInfomation> E){
        Scanner in = new Scanner(System.in);
        System.out.println("=====> [-Create Shift-] <=====");
        System.out.print("Enter Shift name                    : ");String ShiftName = in.nextLine();
        System.out.print("Enter Start-End Time (24-hour)      : ");String Start = in.nextLine().toLowerCase();
        System.out.print("Enter Break Time (24-hour)          : ");String BreakTime = in.nextLine().toLowerCase();
        System.out.print("Enter Description                   : ");String Description = in.nextLine();
        System.out.print("Enter Maximum member                : ");int maxMem = in.nextInt();
        shift s = new shift(ShiftName, Start, BreakTime, maxMem,Description);
        shiftHistory.add(s);
        int i=0;
        for (EmployeeInfomation e : E) {
            String availability = e.isAvailability() ? "\u001B[32mAvailable\u001B[0m" : "\u001B[31mUnavailable\u001B[0m";
            System.out.println(i + ". " + e.getName() + "\t\t(" + availability + ")");
            i++;
        }

        for(int num = 1; num <= maxMem; num++){
            System.out.println("Please Choose employee Handle the Shift ("+(num-1)+"/"+maxMem+"): ");
            int ChooseEm = in.nextInt();
            while (true) {
                if(E.get(ChooseEm).isAvailability()){
                    E.get(ChooseEm).setShift(s);
                    s.getAssignedEmployees().add(E.get(ChooseEm));
                    System.out.println("Shift has been add too "+E.get(ChooseEm).getName()+" Schedule !!!");
                    E.get(ChooseEm).setAvailability(false);
                    break;
                }else{
                    System.out.println(E.get(ChooseEm).getName()+" is not available .....\nPlease choose someone else :");
                    ChooseEm = in.nextInt();
                }
            }

        }

    }
    public void ShowAllShift(){
        int i=1;
        for (shift sh: shiftHistory
             ) {
            System.out.println("\n<=============[ N0."+i+" Shift ]=============>");
            System.out.println("| Shift tittle  : "+sh.getShiftName());
            System.out.print("| Team Member ("+sh.getMaximumEmployee()+"): ");
            StringBuilder sb = new StringBuilder();
            for (EmployeeInfomation e : sh.getAssignedEmployees()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("[").append(e.getName()).append("]");
            }
            System.out.print(sb.toString());

            System.out.println("\n| Start-End Time: "+sh.getStartToEnd());
            System.out.println("| Break time    : "+sh.getBreakTime());
            System.out.println("| Description   : "+sh.getDescription());
            i++;
        }

    }
    public shift(){}
    public String getShiftName() {
        return ShiftName;
    }
    public void Show(ArrayList<EmployeeInfomation> emArray){
        System.out.println("==========> Shift Schedule for all employee <==========");
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

    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    public String getStart() {
        return startToEnd;
    }

    public void setStart(String startToEnd) {
        this.startToEnd = startToEnd;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public int getMaximumEmployee() {
        return maximumEmployee;
    }

    public void setMaximumEmployee(int maximumEmployee) {
        this.maximumEmployee = maximumEmployee;
    }
}
