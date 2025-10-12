package pt.unl.fct.pds.proj1server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "work_data")
public class WorkData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fName;
    @Column(nullable = false)
    private String lName;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String education;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String workplace;
    @Column(nullable = false)
    private String department;

    public WorkData() {
    }

    public WorkData(Long id,
            String fName,
            String lName,
            String postalCode,
            String education,
            String gender,
            String workplace,
            String department) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.postalCode = postalCode;
        this.education = education;
        this.gender = gender;
        this.workplace = workplace;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEducation() {
        return education;
    }

    public String getGender() {
        return gender;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isLinkedDeId(MedDataDeId other) {
        return this.gender.equals(other.getGender()) && this.postalCode.equals(other.getPostalCode());
    }

    public boolean isLinkedK(MedDataK other) {
        // has letters
        String[] otherPostalCodeInterval = other.getPostalCode().split("-");

        int otherPostalCodeMin = Integer.valueOf(otherPostalCodeInterval[0]);
        int otherPostalCodeMax = Integer.valueOf(otherPostalCodeInterval[1]);

        String[] hifenPostalCode = this.postalCode.split("-");
        String[] spacePostalCode = this.postalCode.split(" ");

        if (hifenPostalCode.length == 2) { // has -, como 11900-000
            int thisPostalCodeFirst = Integer.parseInt(hifenPostalCode[0]);
            int thisPostalCodeSecond = Integer.parseInt(hifenPostalCode[1]);

            if (thisPostalCodeFirst >= otherPostalCodeMin && thisPostalCodeFirst >= otherPostalCodeMin)
                return thisPostalCodeSecond >= otherPostalCodeMin && thisPostalCodeSecond <= otherPostalCodeMax;
            else
                return false;
        } else if (spacePostalCode.length == 2) { // has space, como 92980 CEDEX ou 755 01
            int thisPostalCodeNumber = Integer.parseInt(spacePostalCode[0]);

            return thisPostalCodeNumber >= otherPostalCodeMin && thisPostalCodeNumber >= otherPostalCodeMin;
        } else if (spacePostalCode.length == 3) { // has two spaces, como 75712 CEDEX 15
            int thisPostalCodeNumber = Integer.parseInt(spacePostalCode[0]);

            return thisPostalCodeNumber >= otherPostalCodeMin && thisPostalCodeNumber >= otherPostalCodeMin;
        } else if (this.postalCode.isBlank()) { // is blank
            return true;
        } else if (Character.isLetter(this.postalCode.charAt(0))) { //has letters, como L9T
            return false;
        }

        int thisPostalCode = Integer.parseInt(this.postalCode);

        if (thisPostalCode >= otherPostalCodeMin && thisPostalCode <= otherPostalCodeMax)
            return true;
        else
            return false;
    }
}
