import java.util.ArrayList;

public class EmployeeInfomation {
    private String Name;
    private String Preferences;
    private String Skill;
    private int WorkHour;
    private shift shift;
    private boolean availability ;
    public EmployeeInfomation(){

    }

    public EmployeeInfomation(String name, String preferred, String skill, int workHour,boolean availabilty) {
        this.Name = name;
        this.Preferences = preferred;
        this.Skill = skill;
        this.WorkHour = workHour;
        this.availability = availabilty;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void Show(ArrayList<EmployeeInfomation> employees){
          System.out.printf("[%-20s [%-20s [%-20s [%-11s [%-12s%n", "Name]", "Preference]", "Skill]", "Work Hours]","Availability]");
        System.out.println("|---------------------|---------------------|---------------------|------------|------------|");
            for (EmployeeInfomation e : employees) {
                System.out.printf("|%-20s |%-20s |%-20s |%-11s |%-12s|%n", e.getName(), e.getPreferences(), e.getSkill(), e.getWorkHour(),e.isAvailability() ? "Yes" : "No");
            }
        System.out.println("|---------------------|---------------------|---------------------|------------|------------|");

    }



    }
