public class EmlpoyeeInfomation {
    private String Name;
    private String Preferred;
    private String Skill;
    private int WorkHour;

    public EmlpoyeeInfomation(String name, String preferred, String skill, int workHour) {
        Name = name;
        Preferred = preferred;
        Skill = skill;
        WorkHour = workHour;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPreferred() {
        return Preferred;
    }

    public void setPreferred(String preferred) {
        Preferred = preferred;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public int getWorkHour() {
        return WorkHour;
    }

    public void setWorkHour(int workHour) {
        WorkHour = workHour;
    }
}
