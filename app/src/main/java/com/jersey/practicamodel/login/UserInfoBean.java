package com.jersey.practicamodel.login;

import com.jersey.practicalmodel.http.ResponBody;

import java.io.Serializable;

public class UserInfoBean extends ResponBody implements Serializable {
    /**
     * "name":"xiaoming",
     *     "sex":"man",
     *     "age":"28",
     *     "city":"beijing",
     *     "enName":"Jersey"
     */

    private String name;
    private String sex;
    private String age;
    private String city;
    private String enName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", city='" + city + '\'' +
                ", enName='" + enName + '\'' +
                '}';
    }
}
