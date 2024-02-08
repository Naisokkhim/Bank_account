
import java.util.ArrayList;
import java.util.Scanner;
public class shift {
    private String ShiftName;
    private String startToEnd;
    private String breakTime;
    private int TeamMember;
    private String Description;
    private ArrayList<shift> shifts = new ArrayList<>();
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
    public shift(String name, String startToEnd, String BreakTime, int TeamMember, String Description) {
        this.ShiftName = name;
        this.startToEnd = startToEnd;
        this.breakTime = BreakTime;
        this.TeamMember = TeamMember;
        this.Description = Description;
    }
    public void createShift(ArrayList<EmployeeInfomation> E, Scanner input, shift shift,ArrayList<shift> shiftsList){
        System.out.print("Enter Shift name                    : ");String ShiftTittle = input.nextLine();
        System.out.print("Enter Start-End Time (24-hour)      : ");String Start = input.nextLine().toLowerCase();
        System.out.print("Enter Break Time (24-hour)          : ");String BreakTime = input.nextLine().toLowerCase();
        System.out.print("Enter Description                   : ");String Description = input.nextLine();
        int TeamMember;
        do {
        System.out.print("Enter Team member                   : ");
            while (!input.hasNextInt()){
                System.out.println("That's not a number! Please enter a number: ");
                input.next();
            }
            TeamMember = input.nextInt();
            break;
        } while (TeamMember >= 0);
         shift = new shift(ShiftTittle, Start, BreakTime, TeamMember, Description);
        input.nextLine();
        if (!(TeamMember > 0) ){
            System.out.println("\n============================");
            System.out.println("No Employee for this Shift ? !\n\u001B[34m*But you can add employee later\u001B[0m\nDo you want to save this Shift ? (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) : ");
            String optionYN = input.nextLine();
            if(optionYN.equalsIgnoreCase("yes")){
                System.out.println("\u001B[32mShift has been Save !!!\u001B[0m");
                shifts.add(shift);
            }else{
                System.out.println("\u001B[31mYour Shift have not been saved.\u001B[0m ");
            }
            }else {
            shifts.add(shift);
            System.out.println("*Do you want to assign to employee now ? (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) :");
            String assignOption = input.nextLine().toLowerCase();
            switch (assignOption){
                case "yes" :
                    shift.addEmployeeToShift(E,input,shift,shiftsList);
                    break;

            }
        }


    }

    public boolean ShowAllShift(){
        if(!shifts.isEmpty()){
            int i=1;
            for (shift sh: shifts
            ) {
                System.out.println("\n<=============[ N0."+i+" Shift ]=============>");
                System.out.println("| Shift tittle  : "+sh.getShiftName());
                System.out.print("| Team Member ("+sh.getTeamMember()+"): ");
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
            System.out.println("=============================================\n");
        return true;
        }else{
            System.out.println("====================================");
            System.out.println("|\u001B[34mNo Shift has been created yet !!\u001B[0m  |");
            System.out.println("====================================");
        return false;
        }
    }
    public void modifyShift(ArrayList<EmployeeInfomation> Emp,Scanner in,shift shift,ArrayList<shift> shiftList){
        if(!shiftList.isEmpty()){
            System.out.println("\n1. \u001B[32mDelete Shift\u001B[0m ");
            System.out.println("2. \u001B[32mUpdate Shift\u001B[0m ");
            System.out.println("3. \u001B[32mAdd Employee to Exist Shift\u001B[0m");
            System.out.println("\u001B[31mExit\u001B[0m .");
            System.out.print("Please make a choice : ");
            String optionShift = in.nextLine();
            switch (optionShift){
                case "1" :
                    ShowAllShiftName("Delete Shift");
                    deleteShift(Emp,in);
                    break;
                case "2" :
                    ShowAllShiftName("Update Shift");
                    updateShift(Emp,in);
                    System.out.println("\u001B[32mShift Updated !!!\u001B[0m");
                    break;
                case "3" :
                    ShowAllShiftName("All Shift");
                    addEmployeeToShift(Emp,in,shift,shiftList);
                    System.out.println("Done ----");
                    break;
                case "Exit" :break;
                default:
                    System.out.println("\u001B[31mInvalid option !!!\u001B[0m");
            }

        }
    }

    private void addEmployeeToShift(ArrayList<EmployeeInfomation> E, Scanner input, shift s, ArrayList<shift> shifts) {
        s.ShowAllShift();
        int TeamMember = 0,availabilityCount = 0;
        System.out.println("Which shift you want to use : ");String shiftSelected = input.nextLine();
        for (shift sh: shifts
             ) {
            if(shiftSelected.equalsIgnoreCase(sh.getShiftName())){
                TeamMember = sh.getTeamMember();

            }
        }
        for (EmployeeInfomation e:
                E) {
            if (e.isAvailability()){
                availabilityCount+=1;
            }
        }
                if(TeamMember <= availabilityCount) {
                    int num = 1;
                    int ChooseEm = 0;

                    do {
                        for (int i = 0; i < E.size(); i++) {
                            EmployeeInfomation e = E.get(i);
                            String availability = e.isAvailability() ? "Available" : "Unavailable";
                            System.out.println(i + ". " + e.getName() + "\t\t(" + availability + ")");
                        }

                        System.out.println("Please Choose employee Handle the Shift (" + (num - 1) + "/" + TeamMember + "): ");
                        while (!input.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            input.next();
                        }
                        ChooseEm = input.nextInt();
                        if (ChooseEm >= 0 && ChooseEm < E.size()) {
                            EmployeeInfomation chosenEmployee = E.get(ChooseEm);
                            if (!chosenEmployee.isAvailability()) {
                                System.out.println(chosenEmployee.getName() + " is not available .....\nPlease choose someone else :");
                            } else if (s.getAssignedEmployees().contains(chosenEmployee)) {
                                System.out.println(chosenEmployee.getName() + " is already assigned to this shift. Please choose someone else :");
                            } else {
                                chosenEmployee.setShift(s);
                                s.getAssignedEmployees().add(chosenEmployee);
                                System.out.println("\u001B[32mShift has been add too " + chosenEmployee.getName() + " Schedule !!!\u001B[0m");
                                chosenEmployee.setAvailability(false);
                                num++;
                            }
                        } else {
                            System.out.println("No Employee in that number !!");
                        }

                    } while (num <= TeamMember);
                }else{
                    System.out.println("We Dont have that much employee !!");
                }
            input.nextLine();
    }

    private void updateShift(ArrayList<EmployeeInfomation> Emp,Scanner in) {
       deleteShift(Emp,in);
      // createShift(Emp, in);
    }

    public shift(){}
    public String getShiftName() {
        return ShiftName;
    }
    public void Show(ArrayList<EmployeeInfomation> emArray){
        System.out.println("===============================[ Shift Schedule for all employee ]================================");
        System.out.printf("[%-20s [%-20s [%-20s [%-20s [%-10s%n","EmployeeName]","ShiftName]","Start-End]","BreakTime]","Description]");
        for (EmployeeInfomation employee : emArray) {
            shift employeeShift = employee.getShift();
            if (employeeShift != null) {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), employeeShift.getShiftName(), employeeShift.getStart(), employeeShift.getBreakTime(), employeeShift.getDescription());
            } else {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), "No shift assigned", "", "", "");
            }
        }
        System.out.println("=====================================================================================================");
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
    public void deleteShift(ArrayList<EmployeeInfomation> Emp,Scanner in){
        System.out.print("Enter Shift title to delete : ");
        String DeleteShift = in.nextLine();
        int count = 0;
        for (int index = 0; index < shiftHistory.size(); index++) {
            shift sh = shiftHistory.get(index);
            if (sh.getShiftName().equalsIgnoreCase(DeleteShift)) {
                shiftHistory.remove(index);
                System.out.println("\u001B[32mShift deleted !!!\u001B[0m");
                 count =1;
            }
        }
        if(count!=1){
            System.out.println("\u001B[34mNo Shift found !!!\u001B[0m");
        }
        for (EmployeeInfomation emp : Emp) {
            if (emp.getShift() != null && emp.getShift().getShiftName().equalsIgnoreCase(DeleteShift)){
                emp.setShift(null);
                emp.setAvailability(true);
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

    public int getTeamMember() {
        return TeamMember;
    }
    public void setTeamMember(int teamMember) {
        this.TeamMember = teamMember;
    }
}
