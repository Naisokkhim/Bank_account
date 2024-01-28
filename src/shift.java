public class shift {
    private String ShiftName;
    private String startToEnd;
    private String breakTime;
    private int maximumEmployee;

    public shift(String name, String startToEnd, String BreakTime, int maximumEmployee) {
        this.ShiftName = name;
        this.startToEnd = startToEnd;
        this.breakTime = breakTime;
        this.maximumEmployee = maximumEmployee;
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
