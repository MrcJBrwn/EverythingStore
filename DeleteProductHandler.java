import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteProductHandler implements HttpHandler{
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

    ProductDAO product = new ProductDAO();

    try{
      // get the dvd details before we delete from the Database
      Product deletedProduct = product.getProduct(ID);
      // actually delete from database;
      product.deleteProduct(ID);
      
    out.write(
      "<html>" +
      "<head> <title>Product Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> Product Deleted </h1>"+
      "<table class=\"table\">" +
      "<thead>" +
      "  <tr>" +
      "    <th>ID</th>" +
      "    <th>Category</th>" +
      "    <th>Artist</th>" +
      "    <th>Album</th>" +
      "    <th>Genre</th>" +
      "    <th>SKU</th>" +
      "    <th>Price</th>" +
      "    <th>Quantity</th>" +
      "  </tr>" +
      "</thead>" +
      "<tbody>");

      
        out.write(
      "  <tr>"       +
      "    <td>"+ deletedProduct.getID() + "</td>" +
      "    <td>"+ deletedProduct.getCategory() + "</td>" +
      "    <td>"+ deletedProduct.getArtist()+ "</td>" +
      "    <td>"+ deletedProduct.getAlbum() + "</td>" +
      "    <td>"+ deletedProduct.getGenre() + "</td>" +
      "    <td>"+ deletedProduct.getSKU() + "</td>" +
      "    <td>"+ deletedProduct.getPrice() + "</td>" +
      "    <td>"+ deletedProduct.getQuantity() + "</td>" +
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
