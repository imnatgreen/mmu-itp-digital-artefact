package me.nathangreen.digitalartefact;

public class Address {
    private String line1;
    private String line2;
    private String town;
    private String postcode;

    public Address(String line1, String line2, String town, String postcode) {
        this.setLine1(line1);
        this.setLine2(line2);
        this.setTown(town);
        this.setPostcode(postcode);
    }

    public String getAsLine() {
        return String.format("%s, %s, %s",
                this.getLine2() != null && !this.getLine2().isEmpty() ? String.format("%s, %s", this.getLine1(), this.getLine2()) : this.getLine1(),
                this.getTown(),
                this.getPostcode());
    }

    public String getAsBlock() {
        return this.getAsLine().replaceAll(", ", ",\n");
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
