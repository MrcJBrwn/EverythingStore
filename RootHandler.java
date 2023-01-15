import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class RootHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    
   
    out.write(
      "<html>" +
      "<head> <title>Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<div class=\"container\">" +
      "<a href=\"/products\">Display Products</a> " +
      "<br>" + 
      "<a href=\"/searchproduct\">Search Product by ID</a> " + // change add to the product root handler (search)
      "<br>" +
      "<a href=\"/add\">Add Product</a> " +
      "<br>" +
      "<a href=\"/customers\">Display Customers</a> " + //change products to DAcustomers root handler
      "<br>" +
      "<a href=\"/searchcustomer\">Search Customer by ID</a> " + // change add to the customer root handler(search)
      "<br>" + 
      "<a href=\"/addCustomer\">Add Customer</a> " + //change add to customeradd handler
      "</div>" + 
      "</body>" +
      "</html>");
   
    out.close();

// public class RootHandler implements HttpHandler{
//     public void handle(HttpExchange he) throws IOException {
//         he.sendResponseHeaders(200,0);
//         BufferedWriter out = new BufferedWriter(
//                 new OutputStreamWriter(he.getResponseBody() ));

//         ProductDAO product = new ProductDAO();
//         try{
//             ArrayList<Product> allProducts = product.getAllProducts();

//             out.write(
//                     "<html>" +
//                             "<head> <title>Product Library</title> "+
//                             "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
//                             "</head>" +
//                             "<body>" +
//                             "<h1> Products in Stock </h1>"+
//                             "<table class=\"table\">" +
//                             "<thead>" +
//                             "  <tr>" +
//                             "    <th>ID:</th>" +
//                             "    <th>Category:</th>" +
//                             "    <th>Artist:</th>" +
//                             "    <th>Album:</th>" +
//                             "    <th>Genre:</th>" +
//                             "    <th>SKU:</th>" +
//                             "    <th>Price:</th>" +
//                             "    <th>Quantity:</th>" +
//                             "  </tr>" +
//                             "</thead>" +
//                             "<tbody>");

//             for (Product p : allProducts){
//                 out.write(
//                         "  <tr>"       +
//                                 "    <td><b>"+ p.getID() + "<b></td>" +
//                                 "    <td>"+ p.getCategory() + "</td>" +
//                                 "    <td>"+ p.getArtist() + "</td>" +
//                                 "    <td>"+ p.getAlbum() + "</td>" +
//                                 "    <td>"+ p.getGenre() + "</td>" +
//                                 "    <td>"+ p.getSKU() + "</td>" +
//                                 "    <td>"+ p.getPrice() + "</td>" +
//                                 "    <td>"+ p.getQuantity() + "</td>" +
//                                 "  </tr>"
//                 );
//             }
//             out.write(
//                     "</tbody>" +
//                             "</table>" +
//                             "</body>" +
//                             "</html>");
//         }catch(SQLException se){
//             System.out.println(se.getMessage());
//         }
//         out.close();

    }

}