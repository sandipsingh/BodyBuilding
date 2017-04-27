package model;

/**
 * Created by Pradeep on 12/21/2016.
 */
public class PersonDetails {

    int id;
    String f_name;

    public PersonDetails() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getW_sign() {
        return w_sign;
    }

    public void setW_sign(String w_sign) {
        this.w_sign = w_sign;
    }

    public String getHieght() {
        return hieght;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
    }

    public String getH_sign() {
        return h_sign;
    }

    public void setH_sign(String h_sign) {
        this.h_sign = h_sign;
    }

    public PersonDetails(int id, String f_name, String l_name, String email, String dob, String mob_no, String weight, String w_sign, String hieght, String h_sign) {

        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.dob = dob;
        this.mob_no = mob_no;
        this.weight = weight;
        this.w_sign = w_sign;
        this.hieght = hieght;
        this.h_sign = h_sign;
    }

    String l_name;
    String email;
    String dob;
    String mob_no;
    String weight;
    String w_sign;
    String hieght;
    String h_sign;
}
