package org.kosa.myproject.domain;

public class Member {
    private Integer id; // AUTO_INCREMENT에 맞게 Integer로 변경
    private String password;
    private String name;
    private String address;

    public Member() {
        super();
    }

    public Member(Integer id, String password, String name, String address) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + "]";
    }
}
