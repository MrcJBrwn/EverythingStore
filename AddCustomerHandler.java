import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class AddCustomerHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
      CustomerDAO customer = new CustomerDAO();
      //code to go here for DB
     out.write(
      "<html>" +
      "<head> <title>Customer List</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
       "<div class=\"container\">"+
      "<h1> Add Customer </h1>"+
      "<form method=\"get\" action=\"/processAddProduct\">" +
      "<div class=\"form-group\"> "+
       
      "<label for=\"customerid\">Customer ID</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"customerid\" id=\"customerid\"> " + 
       
      "<label for=\"customer forename\">Customer Forename</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"customerforename\" id=\"customerforename\"> " + 

      "<label for=\"customer surname\">Customer Surname</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"customersurname\" id=\"customersurname\"> " + 

       "<label for=\"customer address\">Customer Address</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"customeraddress\" id=\"customeraddress\"> " + 

       "<label for=\"customer tel no\">Customer Telephone Number</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"customertelno\" id=\"customertelno\"> " + 

      "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " + 
      "</div>" + 
      "</form>" +
      "<a href=\"/\"> Back to List </a>"+
      "</div>" +
      "</body>" +
      "</html>");
   
    out.close();

}
}
