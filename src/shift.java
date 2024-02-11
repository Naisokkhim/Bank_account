
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class shift {
    private String ShiftName;
    private String startToEnd;
    private String breakTime;
    private int TeamMember;
    private String Description;
    private ArrayList<shift> shiftsLists = new ArrayList<>();
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
        System.out.print("Enter Start-End Time                : ");
        String Start = input.nextLine().toLowerCase();
        System.out.print("Enter Break Time                    : ");
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
        shift = new shift(ShiftTittle,convertTimeFormat(Start),convertTimeFormat(BreakTime), TeamMember, Description);
        input.nextLine();
            if (!(TeamMember > 0)) {
                System.out.println("\n============================");
                System.out.println("No Employee for this Shift ? !\n\u001B[34m*But you can add employee later\u001B[0m\nDo you want to save this Shift ? (\u001B[32mYes\u001B[0m/\u001B[31mNo\u001B[0m ) : ");
                String optionYN = input.nextLine();
                if (optionYN.equalsIgnoreCase("yes")) {
                    System.out.println("\u001B[32mShift has been Save !!!\u001B[0m");
                    shiftsLists.add(shift);
                } else {
                    System.out.println("\u001B[31mYour Shift have not been saved.\u001B[0m ");
                }
            } else shiftsLists.add(shift);
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
        if(!shiftsLists.isEmpty()){
            int i=1;
            for (shift sh: shiftsLists
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
        if(shiftsLists.isEmpty()){
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            System.out.println("\u001B[32m[]\u001B[0m      \u001B[34mNo shift create yet\u001B[0m     \u001B[32m[]\u001B[0m");
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
        }
    }
    public void modifyShift(ArrayList<EmployeeInfomation> Emp,Scanner in){
        if(!shiftsLists.isEmpty()){
            System.out.println("\n\u001B[32m[]============= Option ===========[]\u001B[0m");
            System.out.println("1. Delete Shift");
            System.out.println("2. Update Shift");
            System.out.println("3. Exit.");
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
                case "3" :
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
        if (shiftsLists.isEmpty()) {
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
            for (shiftIndex = 0; shiftIndex < shiftsLists.size(); shiftIndex++) {
                if (shiftTittle.equalsIgnoreCase(shiftsLists.get(shiftIndex).getShiftName())) {
                    shiftUpdate = shiftsLists.get(shiftIndex);
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
                    shiftUpdate.setStart(convertTimeFormat(newStartEnd));
                    break;
                case "3":
                    System.out.print("Enter new Break-Time : ");
                    String newBreakTime = in.nextLine();
                    System.out.println("*Break-Time \u001B[31m[ "+shiftUpdate.getBreakTime()+"  ]\u001B[0m has changed to \u001B[32m[ "+newBreakTime+" ]\u001B[0m");
                    shiftUpdate.setBreakTime(convertTimeFormat(newBreakTime));
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
                            for (EmployeeInfomation employeeInfomation : E) {
                                if (employeeInfomation.getShift() != null) {
                                    if (employeeInfomation.getShift().getShiftName().equalsIgnoreCase(shiftTittle)) {
                                        System.out.println("-" + employeeInfomation.getName());
                                    }
                                }
                            }
                            System.out.println("*Which one Employee you want to remove : ");
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
                            int count = 0 ;
                            if(!shiftsLists.isEmpty()){
                                for (shift sh: shiftsLists) {
                                    Iterator<EmployeeInfomation> iterator = sh.getAssignedEmployees().iterator();
                                    while (iterator.hasNext()) {
                                        EmployeeInfomation emp = iterator.next();
                                        if(removeEmployee.equalsIgnoreCase(emp.getName())){
                                            System.out.println("Successfully Remove \u001B[32m[ "+emp.getName()+" ]\u001B[0m from \u001B[32m[ "+shiftTittle+" ]\u001B[0m Shift !" );
                                            iterator.remove();
                                            if(shiftTittle.equalsIgnoreCase(sh.getShiftName())){
                                                int afterRemoveMember = sh.getTeamMember() -1;
                                                sh.setTeamMember(afterRemoveMember);
                                            }
                                            count = 1;
                                            break;
                                        }
                                    }
                                }
                                if(count == 0){
                                    System.out.println("\nNo employee found !!!!\n");
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
                    shiftUpdate.setStart(convertTimeFormat(newStartEnd6));
                    System.out.print("Enter new Break-Time : ");
                    String newBreakTime6 = in.nextLine();
                    shiftUpdate.setBreakTime(convertTimeFormat(newBreakTime6));
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

    public void deleteShift(ArrayList<EmployeeInfomation> Emp,Scanner in,String DeleteShift){
        int count = 0;
        for (int index = 0; index < shiftsLists.size(); index++) {
            shift sh = shiftsLists.get(index);
            if (sh.getShiftName().equalsIgnoreCase(DeleteShift)) {
                shiftsLists.remove(index);
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
    public void ShowAllShiftName(String OptionName){
        int ShiftNumber=0;
        System.out.println("======= \u001B[32m[ "+OptionName+" ]\u001B[0m ======");
        for (shift sh: shiftsLists
        ){
            System.out.println((ShiftNumber+1)+". Shift tittle  : "+sh.getShiftName());
            ShiftNumber++;
        }
    }
    public shift(){}
    public String getShiftName() {
        return ShiftName;
    }
    public void Show(ArrayList<EmployeeInfomation> emArray){
        System.out.println("===============================[ \u001B[32mDay Shift Schedule \u001B[0m]================================");
        System.out.printf("[%-20s [%-20s [%-20s [%-20s [%-10s%n","EmployeeName]","ShiftName]","Start-End]","BreakTime]","Description]");
        printShifts(emArray, "7:00am - 5:00pm");
        System.out.println("=====================================================================================================");

        System.out.println("===============================[ \u001B[32mNight Shift Schedule \u001B[0m]================================");
        System.out.printf("[%-20s [%-20s [%-20s [%-20s [%-10s%n","EmployeeName]","ShiftName]","Start-End]","BreakTime]","Description]");
        printShifts(emArray, "4:00pm - 11:00pm");
        System.out.println("=====================================================================================================");
    }

    public void printShifts(ArrayList<EmployeeInfomation> emArray, String shiftType){
        for (EmployeeInfomation employee : emArray) {
            shift employeeShift = employee.getShift();
            if (employeeShift != null) {
                String shifts = employeeShift.getStartToEnd();
                if(shifts.equalsIgnoreCase(shiftType)){
                    System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), employeeShift.getShiftName(), employeeShift.getStart(), employeeShift.getBreakTime(), employeeShift.getDescription());
                }
            } else {
                System.out.printf("|%-20s |%-20s |%-20s |%-20s |%-10s%n", employee.getName(), "No shift assigned", "", "", "");
            }
        }
    }


    public void autoGenerateShift(ArrayList<EmployeeInfomation> em,Scanner in){
        shift shiftsGen = new shift();
        int count = 0 ,autoAssign = 0 ,countBackEnd = 0;
        String startTimeDay = "7:00am-5:00pm",startTimeNight = "4:00pm-11:00pm",breakTimeDay = "11:30am-2:00pm",breakTimeNight = "8:00pm-9:30pm";
        String frontEndDescription =" code UIs, ensure user-friendliness .";
        String backEndDescription =" manage databases, implement APIs .";
        System.out.println("\u001B[32m[]==============================[]\u001B[0m");
        System.out.println("\u001B[32m[]      \u001B[34mAuto Genarate Shift\u001B[0m     \u001B[32m[]\u001B[0m");
        System.out.println("\u001B[32m[]==============================[]\u001B[0m");
        System.out.print("\nPlease give a Tittle of the shift : "); String givenTittle = in.nextLine();
        int givenMaximumTeam;
        do {
            System.out.print("Maximum of the Team member        : "); givenMaximumTeam = in.nextInt();in.nextLine();
            if(givenMaximumTeam > shiftsGen.availabilityCount(em)){
                System.out.println("\nWe don't have enough available employee !!");
                break;
            }
        if(givenTittle.toLowerCase().contains("front")){
            String frontShift =startTimeDay,frontBreak = breakTimeDay;
            if (givenTittle.toLowerCase().contains("night")){
                frontShift = startTimeNight;
                frontBreak = breakTimeNight;
            }
            shift shiftsGenFront = new shift(givenTittle,convertTimeFormat(frontShift),convertTimeFormat(frontBreak),givenMaximumTeam,frontEndDescription);
            count =1;
            for(int i = 0 ; i < givenMaximumTeam ;i++){
                int assCount=0;
                for (EmployeeInfomation E:
                     em) {
                    if(E.getSkill().toLowerCase().contains("front-end") || E.getSkill().toLowerCase().contains("ux/ui")){
                        if(E.isAvailability()){
                            E.setShift(shiftsGenFront);
                            assCount = 1;
                            E.setAvailability(false);
                            shiftsGenFront.getAssignedEmployees().add(E);
                            break;
                        }

                    }
                    if (assCount == 0){
                        if(E.isAvailability()){
                            E.setShift(shiftsGenFront);
                            E.setAvailability(false);
                            shiftsGenFront.getAssignedEmployees().add(E);
                            break;
                        }
                    }
                }
            }
        shiftsLists.add(shiftsGenFront);
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            System.out.println("\u001B[32m[]\u001B[0m    \u001B[34mShift has been Create!!\u001B[0m   \u001B[32m[]\u001B[0m");
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            break;
        }
        else if(givenTittle.toLowerCase().contains("back")){
            String backShift = startTimeDay,backBreak = breakTimeDay;
            if (givenTittle.toLowerCase().contains("night")){
                backShift = startTimeNight;
                backBreak = breakTimeNight;
            }
            shift shiftsGenBack = new shift(givenTittle,convertTimeFormat(backShift),convertTimeFormat(backBreak),givenMaximumTeam,backEndDescription);
            count =1;
            for(int i = 0 ; i < givenMaximumTeam ;i++){
                int assCount=0;
                for (EmployeeInfomation E:
                        em) {
                    if(E.getSkill().toLowerCase().contains("back-end")){
                        if(E.isAvailability()){
                            E.setShift(shiftsGenBack);
                            assCount = 1;
                            E.setAvailability(false);
                            shiftsGenBack.getAssignedEmployees().add(E);
                            break;
                        }

                    }
                    if (assCount == 0){
                        if(E.isAvailability()){
                            E.setShift(shiftsGenBack);
                            E.setAvailability(false);
                            shiftsGenBack.getAssignedEmployees().add(E);
                            break;
                        }
                    }
                }
            }
            shiftsLists.add(shiftsGenBack);
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            System.out.println("\u001B[32m[]\u001B[0m    \u001B[34mShift has been Create!!\u001B[0m   \u001B[32m[]\u001B[0m");
            System.out.println("\u001B[32m[]==============================[]\u001B[0m");
            break;
        }
        if(count == 0){
            System.out.print("\n Seem like we don't have employee that skill to the tittle !!" +
                    "\nDo you want to force create the shift and assign to random employee ? (yes/no) : ");String optionAutoGen = in.nextLine();
            switch (optionAutoGen.toLowerCase()){
                case "yes" :
                    System.out.print("\n\u001B[32mgive description of this shift : \u001B[0m");String givenDescription = in.nextLine();
                    shiftsGen = new shift(givenTittle,convertTimeFormat("7am-5pm"),convertTimeFormat("11:30am-2:00pm"),givenMaximumTeam,givenDescription);
                    for(int assignCount = 0; assignCount < givenMaximumTeam ; assignCount++ ){
                        int countAssgined = 0;
                        for (EmployeeInfomation emp: em) {
                            if(emp.isAvailability()){
                                if((givenDescription.contains("Create button design") && (emp.getSkill().equals("Front-end") || emp.getSkill().equals("UX/UI"))) ||
                                        (givenDescription.contains("Working on server side , APIs,Database") && emp.getSkill().equals("Back-end"))){
                                    emp.setAvailability(false);
                                    emp.setShift(shiftsGen);
                                    shiftsGen.getAssignedEmployees().add(emp);
                                    countAssgined = 1 ;
                                    break;
                                }
                                if(countAssgined == 0){
                                    emp.setAvailability(false);
                                    emp.setShift(shiftsGen);
                                    shiftsGen.getAssignedEmployees().add(emp);
                                    break;
                                }
                            }
                        }
                    }
                    shiftsLists.add(shiftsGen);
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    System.out.println("\u001B[32m[]\u001B[0m    \u001B[34mShift has been Create!!\u001B[0m   \u001B[32m[]\u001B[0m");
                    System.out.println("\u001B[32m[]==============================[]\u001B[0m");
                    break;
                case "no"  :
                    break;
                default:
                    System.out.println("\nInvalid choice !!!!\n");
            }
        }
        }while(shiftsGen.availabilityCount(em) < givenMaximumTeam);
    }
    public static String convertTimeFormat(String time) {
        String[] parts = time.split("-");
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("h,ma", Locale.ENGLISH);
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("ha", Locale.ENGLISH);
        SimpleDateFormat inputFormat3 = new SimpleDateFormat("h:ma", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("h:mma", Locale.ENGLISH);

        try {
            String startTime = outputFormat.format(inputFormat1.parse(parts[0]));
            String endTime = outputFormat.format(inputFormat1.parse(parts[1]));
            return startTime + " - " + endTime;
        } catch (ParseException e) {
            try {
                String startTime = outputFormat.format(inputFormat2.parse(parts[0]));
                String endTime = outputFormat.format(inputFormat2.parse(parts[1]));
                return startTime + " - " + endTime;
            } catch (ParseException ex) {
                try {
                    String startTime = outputFormat.format(inputFormat3.parse(parts[0]));
                    String endTime = outputFormat.format(inputFormat3.parse(parts[1]));
                    return startTime + " - " + endTime;
                } catch (ParseException exc) {
                    exc.printStackTrace();
                    return null;
                }
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
