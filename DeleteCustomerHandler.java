import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteCustomerHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    // Get param from URL
    Map <String,String> parms = Util.requestStringToMap
    (he.getRequestURI().getQuery());

    // print the params for debugging 
    System.out.println(parms);

    //get ID number
    int ID = Integer.parseInt(parms.get("id"));

    CustomerDAO customer = new CustomerDAO();

    try{
      // get the dvd details before we delete from the Database
      Customer deletedCustomer = customer.getCustomerID(ID);
      // actually delete from database;
      customer.deleteCustomer(ID); // cID? 
      
    out.write(
      "<html>" +
      "<head> <title>Customer Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> Customer Deleted </h1>"+
      "<table class=\"table\">" +
      "<thead>" +
      "  <tr>" +
      "    <th>ID</th>" +
      "    <th>Customer Forename</th>" +
      "    <th>Customer Surname</th>" +
      "    <th>Address</th>" +
      "    <th>Customer Telephone Number</th>" +

      "  </tr>" +
      "</thead>" +
      "<tbody>");

      
        out.write(
      "  <tr>"       +
      "    <td>"+ deletedCustomer.getCustomerID() + "</td>" +
      "    <td>"+ deletedCustomer.getCustomerForename() + "</td>" +
      "    <td>"+ deletedCustomer.getCustomerSurname()+ "</td>" +
      "    <td>"+ deletedCustomer.getCustomerAddress() + "</td>" +
      "    <td>"+ deletedCustomer.getCustomerTelNo() + "</td>" +
      "  </tr>" 
        );
      
      out.write(
      "</tbody>" +
      "</table>" +
      "<a href=\"/\"> Back to Menu </a>" +
      "</body>" +
      "</html>");
     }catch(SQLException se){
      System.out.println(se.getMessage());
    }
    out.close();

}
}
