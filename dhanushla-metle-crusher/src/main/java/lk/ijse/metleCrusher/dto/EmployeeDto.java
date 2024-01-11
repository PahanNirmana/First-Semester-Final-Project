package lk.ijse.metleCrusher.dto;

public class EmployeeDto {
    private String id;
    private String nic;
    private String name;
    private String address;
    private String gander;
    private String tel;
    private double salary;


    public EmployeeDto(String id, String nic, String name, String address,String gander,String tel,double salary) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gander = gander;
        this.tel = tel;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGander(String gander) {
        this.gander = gander;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getNic() {
        return nic;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGander() {
        return gander;
    }

    public String getTel() {
        return tel;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id='" + id + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gander='" + gander + '\'' +
                ", tel='" + tel + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
