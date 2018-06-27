/**Prabhjyot**/

package crats.mvcbaseproject.model;

public class User {
    private String gender = "";
    private String firstName = "";
    private String lastName = "";
    private String dob = "";
    private int age;

    public User(String gender, String firstName, String lastName, String dob, int age) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = age;

    }



    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getGender() {
        return this.gender;
    }

    public String getDob() {
        return this.dob;
    }

    public int getAge() {
        return this.age;
    }
}
