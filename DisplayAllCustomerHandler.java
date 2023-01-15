import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DisplayAllCustomerHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody() ));

        CustomerDAO customers = new CustomerDAO();
      
        try{
            ArrayList<Customer> allCustomers = customers.getAllCustomers();
      
            out.write(
                    "<html>" +
                            "<head> <title>Customer Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer List </h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID:</th>" +
                            "    <th>Forename:</th>" +
                            "    <th>Surname:</th>" +
                            "    <th>Address:</th>" +
                            "    <th>Telephone Number:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            for (Customer c : allCustomers){
                out.write(
                        "  <tr>"       +
                                "    <td><b>"+ c.getCustomerID() + "<b></td>" +
                                "    <td>"+ c.getCustomerForename() + "</td>" +
                                "    <td>"+ c.getCustomerSurname() + "</td>" +
                                "    <td>"+ c.getCustomerAddress() + "</td>" +
                                "    <td>"+ c.getCustomerTelNo() + "</td>" +

                                "<td><a href=\"/deleteCustomer?id=" + c.getCustomerID() + "\"> delete </a><td>" +
                                "  </tr>"
                );
            }
                out.write(
                                "</tbody>" +
                                "</table>" +
                                "<a href=\"/\"> Back to Menu </a>"+
                                "</div>" +   
                                       
                                "</body>" +
                                "</html>");
            }catch(SQLException se){
              System.out.println(se.getMessage());
            }
                out.close();

    }
}