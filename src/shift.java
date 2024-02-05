
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
    public shift(String name, String startToEnd, String BreakTime, int maximumEmployee,String Description) {
        this.ShiftName = name;
        this.startToEnd = startToEnd;
        this.breakTime = BreakTime;
        this.maximumEmployee = maximumEmployee;
        this.Description = Description;
    }
    Scanner in = new Scanner(System.in);
    public void ShiftToEmployee(ArrayList<EmployeeInfomation> E){
        System.out.print("Enter Shift name                    : ");String ShiftTittle = in.nextLine();
        System.out.print("Enter Start-End Time (24-hour)      : ");String Start = in.nextLine().toLowerCase();
        System.out.print("Enter Break Time (24-hour)          : ");String BreakTime = in.nextLine().toLowerCase();
        System.out.print("Enter Description                   : ");String Description = in.nextLine();
        System.out.print("Enter Team member                : ");int TeamMember = in.nextInt();in.nextLine();
        shift s = new shift(ShiftTittle, Start, BreakTime, TeamMember,Description);
        int i=0;
        if (TeamMember > 0)
        {   shiftHistory.add(s);
            for (EmployeeInfomation e : E) {
            String availability = e.isAvailability() ? "\u001B[32mAvailable\u001B[0m" : "\u001B[31mUnavailable\u001B[0m";
            System.out.println(i + ". " + e.getName() + "\t\t(" + availability + ")");
            i++;
            }
            for(int num = 1; num <= TeamMember; num++){
                System.out.println("Please Choose employee Handle the Shift ("+(num-1)+"/"+TeamMember+"): ");
                int ChooseEm = in.nextInt();in.nextLine();
                while (true) {
                if(E.get(ChooseEm).isAvailability()){
                    E.get(ChooseEm).setShift(s);
                    s.getAssignedEmployees().add(E.get(ChooseEm));
                    System.out.println("\u001B[32mShift has been add too "+E.get(ChooseEm).getName()+" Schedule !!!\u001B[0m");
                    E.get(ChooseEm).setAvailability(false);
                    break;
                }else{
                    System.out.println(E.get(ChooseEm).getName()+" is not available .....\nPlease choose someone else :");
                    ChooseEm = in.nextInt();in.nextLine();
                }
            }
           }

        }
        else {
            System.out.println("\n============================");
            System.out.println("No Employee for this Shift ? !\n\u001B[34m*But you can add employee later\u001B[0m\nDo you want to save this Shift ? (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) : ");
            String optionYN ;
            optionYN = in.nextLine();
            if(optionYN.equalsIgnoreCase("yes")){
                System.out.println("\u001B[32mShift has been Save !!!\u001B[0m");
                shiftHistory.add(s);
            }else{
                System.out.println("\u001B[31mYour Shift have not been saved.\u001B[0m ");
            }
        }

    }
    public boolean ShowAllShift(){
        if(!shiftHistory.isEmpty()){

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
                System.out.print(sb);

                System.out.println("\n| Start-End Time: "+sh.getStartToEnd());
                System.out.println("| Break time    : "+sh.getBreakTime());
                System.out.println("| Description   : "+sh.getDescription());
                i++;
            }
            return true;
        }else{
            System.out.println("====================================");
            System.out.println("|\u001B[34mNo Shift has been created yet !!\u001B[0m  |");
            System.out.println("====================================");
            return false;
        }
    }
    public void modifyShift(ArrayList<EmployeeInfomation> Emp){
        System.out.println("---------------------------------");
        System.out.println("1. \u001B[32mDelete Shift\u001B[0m ");
        System.out.println("2. \u001B[32mUpdate Shift\u001B[0m ");
        System.out.println("3. \u001B[32mAdd Employee to Exist Shift\u001B[0m");
        System.out.println("\u001B[31mExit\u001B[0m .");
        System.out.print("Please make a choice : ");
        String optionShift = in.nextLine();
        switch (optionShift){
            case "1" :
                ShowAllShiftName("Delete Shift");
                deleteShift(Emp);
                System.out.println("\u001B[32mShift deleted !!!\u001B[0m");
                break;
            case "2" :
                ShowAllShiftName("Update Shift");
                updateShift(Emp);
                System.out.println("\u001B[32mShift Updated !!!\u001B[0m");
                break;
            case "3" :
                ShowAllShiftName("All Shift");
                addEmployeeToExistShift(Emp);
                System.out.println("Done ----");
                break;
            case "Exit" :break;
            default:
                System.out.println("\u001B[31mInvalid option !!!\u001B[0m");
        }
    }

    private void addEmployeeToExistShift(ArrayList<EmployeeInfomation> E) {
        int maxMem = 0;
        shift sh = null;
        System.out.println("--------------------------------");
        System.out.print("Enter Shift you want to add : ");String AddEmToSh = in.nextLine();
        for (int index = 0; index < shiftHistory.size(); index++) {
             sh = shiftHistory.get(index);
            if (sh.getShiftName().equalsIgnoreCase(AddEmToSh)) {
                 if(sh.getMaximumEmployee() == 0){
                     sh.setMaximumEmployee(1);
                     maxMem = sh.getMaximumEmployee();
                 }
                break;
            }
        }
        int i=0;
        if (sh != null && maxMem !=0)
        {
            for (EmployeeInfomation e : E) {
                String availability = e.isAvailability() ? "\u001B[32mAvailable\u001B[0m" : "\u001B[31mUnavailable\u001B[0m";
                System.out.println(i + ". " + e.getName() + "\t\t(" + availability + ")");
                i++;
            }
            for(int num = 1; num <= maxMem; num++){
                System.out.println("Please Choose employee Handle the Shift ("+(num-1)+"/"+maxMem+"): ");
                int ChooseEm = in.nextInt();in.nextLine();
                while (true) {
                    if(E.get(ChooseEm).isAvailability()){
                        E.get(ChooseEm).setShift(sh);
                        sh.getAssignedEmployees().add(E.get(ChooseEm));
                        System.out.println("\u001B[32mShift has been add too "+E.get(ChooseEm).getName()+" Schedule !!!\u001B[0m");
                        E.get(ChooseEm).setAvailability(false);
                        break;
                    }else{
                        System.out.println(E.get(ChooseEm).getName()+" is not available .....\nPlease choose someone else :");
                        ChooseEm = in.nextInt();in.nextLine();
                    }
                }
            }

        }
    }

    private void updateShift(ArrayList<EmployeeInfomation> Emp) {
       deleteShift(Emp);
       ShiftToEmployee(Emp);
    }

    public shift(){}
    public String getShiftName() {
        return ShiftName;
    }
    public void Show(ArrayList<EmployeeInfomation> emArray){
        System.out.println("==========> Shift Schedule for all employee <==========");
        System.out.printf("[%-20s [%-20s [%-20s [%-20s [%-10s%n","EmployeeName]","ShiftName]","Start-End]","BreakTime]","Description]");
        for (EmployeeInfomation employee : emArray) {
            shift employeeShift = employee.getShift();
            if (employeeShift != null) {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), employeeShift.getShiftName(), employeeShift.getStart(), employeeShift.getBreakTime(), employeeShift.getDescription());
            } else {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), "No shift assigned", "", "", "");
            }
        }
    }
    public void ShowAllShiftName(String OptionName){
        int ShiftNumber=0;
        System.out.println("======= \u001B[31m[ "+OptionName+" ]\u001B[0m ======");
        for (shift sh: shiftHistory
        ){
            System.out.println((ShiftNumber+1)+". Shift tittle  : "+sh.getShiftName());
            ShiftNumber++;
        }
    }
    public void deleteShift(ArrayList<EmployeeInfomation> Emp){
        System.out.println("------------------------------");
        System.out.print("Enter Shift title to delete : ");
        String DeleteShift = in.nextLine();
        for (int index = 0; index < shiftHistory.size(); index++) {
            shift sh = shiftHistory.get(index);
            if (sh.getShiftName().equalsIgnoreCase(DeleteShift)) {
                shiftHistory.remove(index);

                break;

            }
            else{
                System.out.println("\u001B[34mNo Shift found !!!\u001B[0m");
            }
        }
        for (EmployeeInfomation emp : Emp) {
            if (emp.getShift() != null && emp.getShift().getShiftName().equalsIgnoreCase(DeleteShift)){
                emp.setShift(null);
                emp.setAvailability(true);
                break;
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
