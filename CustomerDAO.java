import java.sql.*;
import java.util.ArrayList;
// import org.sqlite.JDBC;
public class CustomerDAO {


    public CustomerDAO() {
    }

    private static Connection getDBConnection() { //function
        Connection dbConnection = null; //object
        try {
            Class.forName("org.sqlite.JDBC"); //type of jar file driver being used
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String URL = "jdbc:sqlite:customers.sqlite"; // product database
            dbConnection = DriverManager.getConnection(URL);
            // System.out.println("Connected...\n");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        System.out.println("Retrieving all customers:\n");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM " + "customers";
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            //System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {
                int customerID = result.getInt(1);
                String customerForename = result.getString(2);
                String customerSurname = result.getString(3);
                String customerAddress = result.getString(4);
                String customerTelNo = result.getString(5);


                customers.add(new Customer(customerID, customerForename, customerSurname, customerAddress, customerTelNo));
            }
        } catch(Exception e) {
            System.out.println("get all customers: " + e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return customers;
    }
    // -----------------------------------------------------------------------------------------------------

    public Customer getCustomerID(int cID) throws SQLException {
        Customer cust = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        //String query = "SELECT customers.id, customers.forename, customers.surname, customers.telno, customers.address, address.houseno, address.addressline1, address.addressline2, address.country, address.postcode FROM customers, address WHERE customers.id=adddress.customerid";
        //String query = "SELECT * FROM customers WHERE CustomerID =" + cID + ";";
        String query = "SELECT * FROM customers WHERE CustomerID = ?"; //dynamic parameter
      // SELECT classes._id, students.studentname, classes.classname, classes.attend, classes.late, classes.dtime
      //FROM students, classes
      //WHERE students._id=classes.student

        try {
            dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //Scanner sc = new Scanner(System.in); //close
            //int scan = sc.nextInt();
            //statement = dbConnection.createStatement();
            preparedStatement.setInt(1, cID);
            //result = statement.executeQuery(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("customerID");
                String forename = rs.getString("customerForename");
                String surname = rs.getString("customerSurname");
                String address = rs.getString("customerAddress");
                String telno = rs.getString("customerTelNo");

                cust = new Customer(cID, forename, surname, address, telno);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return cust;
    }

        // -----------------------------------------------------------------------------------------------------

    public boolean addCustomer(Customer in) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String insert = "INSERT INTO product" + " (customerID, customerForename, customerSurname, customerAddress, customerTelNo) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?);";

        //String insert = "INSERT INTO customers (customerID, customerForename, customerSurname, customerAddress, customerTelNo) VALUES ("+ ins.getCustomerID()+",'" + ins.getCustomerForename() + "','" + ins.getCustomerSurname() + "','" + ins.getCustomerAddress() "','" + ins.getCustomerTelNo() + "');";

        boolean yn = false;

        try {
            dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
            //statement = dbConnection.createStatement();
            System.out.println(insert);
            preparedStatement.setInt(1, in.getCustomerID());
            preparedStatement.setString(2, in.getCustomerForename());
            preparedStatement.setString(3, in.getCustomerSurname());
            preparedStatement.setString(4, in.getCustomerAddress());
            preparedStatement.setString(5, in.getCustomerTelNo());
            System.out.println("New customer added.");
            preparedStatement.executeUpdate();
            //statement.executeUpdate(insert);

            yn = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return yn;
    }
    // -----------------------------------------------------------------------------------------------------
    public Boolean updateCustomer (Customer up) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String update = "UPDATE customers " + "SET customerID = '" + up.getCustomerID() + "'," + "customerForename = '" + up.getCustomerForename() + "'," + "customerSurname = '" + up.getCustomerSurname() + "',"
                + "customerAddress = " + up.getCustomerAddress() + ", customerTelNo = " + up.getCustomerTelNo() + " WHERE ID = " + up.getCustomerID() + ";";
        //"Year= " + dvd.getYear() + ", Rating=" + dvd.getRating() + " WHERE ID = " + dvd.getID() + ";";
        try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement(update);
            statement = dbConnection.createStatement();
            // preparedStatement.setInt(1, up.getCustomerID());
            // preparedStatement.setString(2, up.getCustomerForename());
            // preparedStatement.setString(3, up.getCustomerSurname());
            // preparedStatement.setString(4, up.getCustomerAddress());
            // preparedStatement.setString(5, up.getCustomerTelNo());
            System.out.println("Customer updated.");
            statement.executeUpdate(update);
            //preparedStatement.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        } finally {

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return true;
    }
    public boolean deleteCustomer(int cID) throws SQLException {
        System.out.println("Deleting customer...");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        //String query = "DELETE FROM customers WHERE customerID = ?;";
        String query = "DELETE FROM customers WHERE customerID =" + cID + ";";
        try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //preparedStatement.setInt(1, product_ID);
            statement = dbConnection.createStatement();
            //	System.out.println(query);
            // execute SQL query
            result = statement.executeUpdate(query);
            System.out.println("Customer deleted.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
        // -----------------------------------------------------------------------------------------------------

//        public ArrayList<Product> getAllProducts;
//        public Product getProduct ( int customerID);
//        public void addProduct (Product product);
//        public void amendProduct (Product product);
//        public void deleteProduct (Product product);



