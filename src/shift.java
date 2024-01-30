import java.util.ArrayList;
import java.util.Scanner;
public class shift {
    private String ShiftName;
    private String startToEnd;
    private String breakTime;
    private int maximumEmployee;
    private String Description;

    public shift(String name, String startToEnd, String BreakTime, int maximumEmployee) {
        this.ShiftName = name;
        this.startToEnd = startToEnd;
        this.breakTime = BreakTime;
        this.maximumEmployee = maximumEmployee;
    }
    public void ShiftToEmloyee(ArrayList<EmployeeInfomation> E){
        Scanner in = new Scanner(System.in);
        System.out.println("=====> Create Shift <=====");
        System.out.print("Enter Shift name                    : ");String ShiftName = in.next();
        System.out.print("Enter Start-End Time (24-hour)      : ");String Start = in.next();
        System.out.print("Enter Break Time (24-hour)          : ");String BreakTime = in.next();
        System.out.print("Enter Maximum member                : ");int maxMem = in.nextInt();
        shift s = new shift(ShiftName, TimeConvert(Start), TimeConvert(BreakTime), maxMem);

        int i=0;
        for (EmployeeInfomation e : E) {
            System.out.println(i+". "+e.getName()+"\t("+(e.isAvailability() ? "Available":"Unavailable")+")");
            i++;
        }
        for(int num = 1; num <= maxMem; num++){
            System.out.println("Please Choose "+num+" employee Handle the Shift : ");
            int ChooseEm = in.nextInt();
            while (true) {
                if(E.get(ChooseEm).isAvailability()){
                    E.get(ChooseEm).setShift(s);
                    System.out.println("Shift has been add too "+E.get(ChooseEm).getName()+" Schedule !!!");
                    E.get(ChooseEm).setAvailability(false);
                    break;
                }else{
                    System.out.println(E.get(ChooseEm).getName()+" is not available .....\nPlease choose someone else ");
                    ChooseEm = in.nextInt(); // Ask for another employee
                }
            }
        }
    }
    private String TimeConvert(String timeString){
        String[] splitTime = timeString.split("-");
        int startHour = Integer.parseInt(splitTime[0]);
        int endHour = Integer.parseInt(splitTime[1]);
        String startTime = startHour > 12 ? (startHour - 12) + "pm" : startHour + "am";
        String endTime = endHour > 12 ? (endHour - 12) + "pm" : endHour + "am";
        return startTime+"-"+endTime;
    }
    public shift(){}
    public String getShiftName() {
        return ShiftName;
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
