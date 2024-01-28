import java.util.ArrayList;

public class EmployeeInfomation {
    private String Name;
    private String Preferences;
    private String Skill;
    private int WorkHour;
    private shift shift;
    public EmployeeInfomation(String name, String preferred, String skill, int workHour) {
        Name = name;
        Preferences = preferred;
        Skill = skill;
        WorkHour = workHour;
    }

    public EmployeeInfomation() {

    }

    public String getName() {
        return Name;
    }
    public String getPreferences() {
        return Preferences;
    }
    public String getSkill() {
        return Skill;
    }
    public int getWorkHour() {
        return WorkHour;
    }

    public shift getShift() {
        return shift;
    }

    public void setShift(shift shift) {
        this.shift = shift;
    }
    public void Show(ArrayList<EmployeeInfomation> employees){
        System.out.printf("|%-20s |%-20s |%-20s |%-10s%n", "Name|", "Preference|", "Skill|", "Work Hours|"+"\n");
        for (EmployeeInfomation e : employees) {
            System.out.printf("%-20s  %-20s  %-20s  %-10d%n", e.getName(), e.getPreferences(), e.getSkill(), e.getWorkHour());
        }
    }


}
