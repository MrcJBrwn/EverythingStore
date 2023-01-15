public class Address extends Customer{
    private String houseNo;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String postcode;


    public Address (int customerID, String customerForename, String customerSurname, String customerAddress, String customerTelNo, String houseNo, String addressLine1, String addressLine2, String country, String postcode){
        super(customerID, customerForename, customerSurname, customerAddress, customerTelNo);
        this.houseNo = houseNo;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.country = country;
        this.postcode = postcode;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString(){
        return "Address: "
                + "\n House Number: " + houseNo
                + "\n First Line of address: " + addressLine1
                + "\n Second Line of Address: " + addressLine2
                + "\n country: " + country
                + "\n postcode: " + postcode;
    }
}

