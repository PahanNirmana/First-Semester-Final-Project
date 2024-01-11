package lk.ijse.metleCrusher.dto.tm;

public class EmployeeTm {
    private String id;
    @lombok.Getter
    @lombok.Setter
    private String nic;
    private String name;
    private String address;
    @lombok.Getter
    @lombok.Setter
    private String gander;
    private String tel;
    @lombok.Getter
    @lombok.Setter
    private double salary;

    public EmployeeTm() {
    }

    public EmployeeTm(String id, String nic, String name, String address,String gander, String tel, double salary) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gander = gander;
        this.tel = tel;
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "id='" + id + '\'' +
                ", nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gander='" + gander + '\'' +
                ", tel='" + tel + '\'' +
                ", salary=" + salary +
                '}';
    }
}
