package ncut.excel.model;

import java.util.Date;

/**
 * \
 *
 * @author NikoBelic
 * @create 20/11/2016 01:16
 */
public class User
{
    private int id;
    private String name;
    private int age;
    private Date birthDay;

    public User(int id, String name, int age, Date birthDay) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
