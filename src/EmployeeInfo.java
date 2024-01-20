public class Employee {
    private int Id;
    private String Name;
    private String Phone;
    private String Gmail;
    public Employee(int id,String Name ,String Phone,String Gmail){
        this.Id = id;
        this.Name= Name;
        this.Phone = Phone;
        this.Gmail = Gmail;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }
}
