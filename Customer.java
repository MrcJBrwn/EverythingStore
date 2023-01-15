public class Customer {
    public Customer addCustomer;
    private int customerID;
    private String customerForename;
    private String customerSurname;
    private String customerAddress;
    private String customerTelNo;

    public Customer (int customerID, String customerForename, String customerSurname, String customerAddress, String customerTelNo){
        this.customerID = customerID;
        this.customerForename = customerForename;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerTelNo = customerTelNo;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerForename() {
        return customerForename;
    }

    public void setCustomerForename(String customerForename) {
        this.customerForename = customerForename;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTelNo() {
        return customerTelNo;
    }

    public void setCustomerTelNo(String customerTelNo) {
        this.customerTelNo = customerTelNo;
    }

    @Override
    public String toString(){
        return "Customer: "
                + "\n Customer ID: " + customerID
                + "\n Customer Forename: " + customerForename
                + "\n Customer Surname: " + customerSurname
                + "\n Customer Address: " + customerAddress
                + "\n Telelphone Number: " + customerTelNo;
    }
    public void addCustomer(CustomerDAO customer) {
    }
    public void updateCustomer(CustomerDAO customer) {

    }
}
