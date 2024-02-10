
import java.util.ArrayList;
import java.util.Iterator;
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
    public void createShift(ArrayList<EmployeeInfomation> E, Scanner input) {
        System.out.print("Enter Shift tittle                  : ");
        String ShiftTittle = input.nextLine();
        System.out.print("Enter Start-End Time (24-hour)      : ");
        String Start = input.nextLine().toLowerCase();
        System.out.print("Enter Break Time (24-hour)          : ");
        String BreakTime = input.nextLine().toLowerCase();
        System.out.print("Enter Description                   : ");
        String Description = input.nextLine();
        shift shift = new shift();
        int TeamMember;
        do {
            System.out.print("Enter Team member                   : ");
            while (!input.hasNextInt()) {
                System.out.println("That's not a number! Please enter a number: ");
                input.next();
            }
            TeamMember = input.nextInt();
        } while (!(TeamMember >= 0));

        while (!(TeamMember <= shift.availabilityCount(E))){
            System.out.println("\n\u001B[31mWe don't have enough available employee  !!!\u001B[0m\n\u001B[32m*Available count : \u001B[0m"+shift.availabilityCount(E));
            System.out.print("Enter valid number :");
            TeamMember = input.nextInt();
        }
        shift = new shift(ShiftTittle, Start, BreakTime, TeamMember, Description);
        input.nextLine();
            if (!(TeamMember > 0)) {
                System.out.println("\n============================");
                System.out.println("No Employee for this Shift ? !\n\u001B[34m*But you can add employee later\u001B[0m\nDo you want to save this Shift ? (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) : ");
                String optionYN = input.nextLine();
                if (optionYN.equalsIgnoreCase("yes")) {
                    System.out.println("\u001B[32mShift has been Save !!!\u001B[0m");
                    shifts.add(shift);
                } else {
                    System.out.println("\u001B[31mYour Shift have not been saved.\u001B[0m ");
                }
            } else shifts.add(shift);
        String assignOption;
        do {
            System.out.println("\u001B[32mDo you want to assign to employee now ?\u001B[0m* (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) :");
            assignOption = input.nextLine().toLowerCase();
            switch (assignOption) {
                case "yes":
                    shift.addEmployeeToShift(E, input, shift, TeamMember);
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    System.out.println("\u001B[32m[]\u001B[0m      \u001B[34mShift has been save!!\u001B[0m   \u001B[32m[]\u001B[0m");
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    break;
                case "no":
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    System.out.println("\u001B[32m[]\u001B[0m      \u001B[34mShift has been save!!\u001B[0m   \u001B[32m[]\u001B[0m");
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31m[]==============================[]\u001B[0m");
                    System.out.println("\u001B[31m[]\u001B[0m       \u001B[34m Invalid option \u001B[0m       \u001B[31m[]\u001B[0m");
                    System.out.println("\u001B[31m[]==============================[]\u001B[0m");
            }
        } while (!assignOption.equals("yes") && !assignOption.equals("no"));

    }

    public void ShowAllShift(){
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
                if(sb.isEmpty()){
                    System.out.print("\u001B[31m[ NO EMPLOYEE ASSIGN YET ]\u001B[0m");
                }
                System.out.println("\n| Start-End Time: "+sh.getStartToEnd());
                System.out.println("| Break time    : "+sh.getBreakTime());
                System.out.println("| Description   : "+sh.getDescription());
                i++;
            }

     }
        if(shifts.isEmpty()){
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            System.out.println("\u001B[32m[]\u001B[0m      \u001B[34mNo shift create yet\u001B[0m     \u001B[32m[]\u001B[0m");
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
        }
    }
    public void modifyShift(ArrayList<EmployeeInfomation> Emp,Scanner in,shift shift){
        if(!shifts.isEmpty()){
            System.out.println("\n\u001B[32m[]==============================\u001B[0m");
            System.out.println("1. \u001B[32mDelete Shift\u001B[0m ");
            System.out.println("2. \u001B[32mUpdate Shift\u001B[0m ");
            System.out.println("\u001B[31mExit\u001B[0m .");
            System.out.print("\u001B[32mPlease make a choice : \u001B[0m");
            String optionShift = in.nextLine();
            switch (optionShift){
                case "1" :
                    ShowAllShiftName(" Delete Shift ");
                    System.out.print("\u001B[32mEnter Shift title to delete : \u001B[0m");
                    String DeleteShift = in.nextLine();
                    deleteShift(Emp,in,DeleteShift);
                    break;
                case "2" :
                    ShowAllShiftName(" Update Shift ");
                    updateShift(Emp,in);
                    break;
                case "Exit" :
                case "exit" :
                    break;
                default:
                    System.out.println("\u001B[31m Invalid option !!! \u001B[0m");
            }

        }
    }
public int availabilityCount(ArrayList<EmployeeInfomation> E){
    int availabilityCount = 0;
    for (EmployeeInfomation e:
            E) {
        if (e.isAvailability()){
            availabilityCount+=1;
        }
    }
     return availabilityCount;
}

    private void addEmployeeToShift(ArrayList<EmployeeInfomation> E, Scanner input, shift s,int TeamMember) {
                if(TeamMember <= s.availabilityCount(E)) {
                    int num = 1;
                    int ChooseEm;
                    do {
                        for (int i = 0; i < E.size(); i++) {
                            EmployeeInfomation e = E.get(i);
                            String availability = e.isAvailability() ? "\u001B[32mAvailable\u001B[0m" : "\u001B[31mUnavailable\u001B[0m";
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

    private void updateShift(ArrayList<EmployeeInfomation> E, Scanner in) {
        if (shifts.isEmpty()) {
            System.out.println("The shift list is empty.");
            return;
        }

        String shiftTittle;
        int shiftIndex;
        shift shiftUpdate = new shift();

        do {
            System.out.print("\n\u001B[32mEnter shift tittle to update or\u001B[0m (\u001B[32mExit\u001B[0m ) \u001B[32mto leave  : \u001B[0m");
            shiftTittle = in.nextLine();
                if(shiftTittle.equalsIgnoreCase("exit")) {
                break;
            }
            for (shiftIndex = 0; shiftIndex < shifts.size(); shiftIndex++) {
                if (shiftTittle.equalsIgnoreCase(shifts.get(shiftIndex).getShiftName())) {
                    shiftUpdate = shifts.get(shiftIndex);
                }
            }

            System.out.print("\n================\n1. Tittle \n2. Start-End Time \n3. Break Time \n4. Description \n5. TeamMember \n6. All \n7. Exit \nSelect Field you wants to update : ");
            String updateOption = in.nextLine();
            switch (updateOption) {
                case "1":
                    System.out.print("Enter new tittle : ");
                    String newTittle = in.nextLine();
                    System.out.println("*Tittle \u001B[31m[ "+shiftUpdate.getShiftName()+"  ]\u001B[0m has changed to \u001B[32m[ "+newTittle+" ]\u001B[0m");
                    shiftUpdate.setShiftName(newTittle);
                    break;

                case "2":
                    System.out.print("Enter new Start-End : ");
                    String newStartEnd = in.nextLine();
                    System.out.println("*Start-End time  \u001B[31m[ "+shiftUpdate.getStart()+"  ]\u001B[0m has changed to \u001B[32m[ "+newStartEnd+" ]\u001B[0m");
                    shiftUpdate.setStart(newStartEnd);
                    break;
                case "3":
                    System.out.print("Enter new Break-Time : ");
                    String newBreakTime = in.nextLine();
                    System.out.println("*Break-Time \u001B[31m[ "+shiftUpdate.getBreakTime()+"  ]\u001B[0m has changed to \u001B[32m[ "+newBreakTime+" ]\u001B[0m");
                    shiftUpdate.setBreakTime(newBreakTime);
                    break;
                case "4":
                    System.out.print("Enter new Description : ");
                    String newDescription = in.nextLine();
                    System.out.println("\u001B[32m *Description has been changed \u001B[0m");
                    shiftUpdate.setDescription(newDescription);
                    break;
                case "5":
                    System.out.println("\n====== [ Team member modify ] ======");
                    System.out.print("1. Add Member \n2. Remove  Member \nPlease make a choice  : ");
                    String teamModifyOption = in.nextLine();
                    switch (teamModifyOption) {
                        case "1":
                            System.out.println("How much member you want to add :");
                            int addMoreMember = in.nextInt();
                            int NewTeamMember = shiftUpdate.getTeamMember()+addMoreMember;
                            shiftUpdate.setTeamMember(NewTeamMember);
                            addEmployeeToShift(E,in,shiftUpdate,addMoreMember);
                            System.out.println("\u001B[32m[ "+addMoreMember+"  ]\u001B[0m Member has been add to  \u001B[32m[ "+shiftUpdate.getShiftName()+" ]\u001B[0m shift");

                            break;
                        case "2":
                            for (int indexOfEmployee = 0; indexOfEmployee < E.size() ; indexOfEmployee++){
                                if(E.get(indexOfEmployee).getShift() != null){
                                    if (E.get(indexOfEmployee).getShift().getShiftName().equalsIgnoreCase(shiftTittle)){
                                        System.out.println("-"+E.get(indexOfEmployee).getName());
                                    }
                                }
                            }
                            System.out.println("Which one Employee you want to remove : ");
                            String removeEmployee = in.nextLine();
                            for (int indexOfEmployee = 0; indexOfEmployee < E.size() ; indexOfEmployee++){
                                if(E.get(indexOfEmployee).getShift() != null){
                                    if (E.get(indexOfEmployee).getShift().getShiftName().equalsIgnoreCase(shiftTittle) && E.get(indexOfEmployee).getName().equalsIgnoreCase(removeEmployee)){
                                        E.get(indexOfEmployee).setShift(null);
                                        E.get(indexOfEmployee).setAvailability(true);
                                        break;
                                    }
                                }
                            }
                            if(!shifts.isEmpty()){
                                for (shift sh: shifts) {
                                    Iterator<EmployeeInfomation> iterator = sh.getAssignedEmployees().iterator();
                                    while (iterator.hasNext()) {
                                        EmployeeInfomation emp = iterator.next();
                                        if(removeEmployee.equalsIgnoreCase(emp.getName())){
                                            System.out.println("\u001B[32m[Successfully Remove "+emp.getName()+" !!]\u001B[0m");
                                            iterator.remove();
                                            break;
                                        }
                                    }
                                    if(shiftTittle.equalsIgnoreCase(sh.getShiftName())){
                                        int afterRemoveMember = sh.getTeamMember() -1;
                                        sh.setTeamMember(afterRemoveMember);
                                    }
                                }
                            }
                            break;
                        case "Exit":
                        case "exit":
                            break;
                        default:System.out.println("\u001B[31m Invalid choice .......\u001B[0m");
                            break;
                    }
                    break;
                case "6":
                    System.out.print("Enter new tittle : ");
                    String newTittle6 = in.nextLine();
                    shiftUpdate.setShiftName(newTittle6);
                    System.out.print("Enter new Start-End : ");
                    String newStartEnd6 = in.nextLine();
                    shiftUpdate.setStart(newStartEnd6);
                    System.out.print("Enter new Break-Time : ");
                    String newBreakTime6 = in.nextLine();
                    shiftUpdate.setBreakTime(newBreakTime6);
                    System.out.print("Enter new Description : ");
                    String newDescription6 = in.nextLine();
                    shiftUpdate.setDescription(newDescription6);
                    System.out.print("Enter new Team Member :");
                    int newMember = in.nextInt();
                    shiftUpdate.setTeamMember(newMember);
                    addEmployeeToShift(E,in,shiftUpdate,newMember);
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    System.out.println("\u001B[32m[]\u001B[0m    \u001B[34mShift has been update\u001B[0m     \u001B[32m[]\u001B[0m");
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    break;
                case "7":
                    break;
                default:
                    System.out.println("\u001B[31mInvalid choice .......\u001B[0m");
                    break;
            }
        } while (!shiftTittle.equalsIgnoreCase("exit"));
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
        System.out.println("======= \u001B[32m[ "+OptionName+" ]\u001B[0m ======");
        for (shift sh: shifts
        ){
            System.out.println((ShiftNumber+1)+". Shift tittle  : "+sh.getShiftName());
            ShiftNumber++;
        }
    }
    public void deleteShift(ArrayList<EmployeeInfomation> Emp,Scanner in,String DeleteShift){
        int count = 0;
        for (int index = 0; index < shifts.size(); index++) {
            shift sh = shifts.get(index);
            if (sh.getShiftName().equalsIgnoreCase(DeleteShift)) {
                shifts.remove(index);
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
