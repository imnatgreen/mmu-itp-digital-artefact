package me.nathangreen.digitalartefact;

public class Config {
    private String company;
    private String[] name = new String[2];

    public Config(String company, String firstName, String lastName) {
        this.company = company;
        this.name[0] = firstName;
        this.name[1] = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.name[0], this.name[1]);
    }

    public String getFirstName() {
        return this.name[0];
    }

    public void setFirstName(String firstName) {
        this.name[0] = firstName;
    }

    public String getLastName() {
        return this.name[1];
    }

    public void setLastName(String lastName) {
        this.name[1] = lastName;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
