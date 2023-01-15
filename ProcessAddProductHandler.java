import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddProductHandler implements HttpHandler{
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
  

    ProductDAO products = new ProductDAO();

    //System.out.println("about to get data");
    int ID = Integer.parseInt(parms.get("id"));
    String category = parms.get("category");
    String artist = parms.get("artist");
    String album = parms.get("album");
    String genre = parms.get("genre");
    String sku = parms.get("sku");
    int price = Integer.parseInt(parms.get("price"));
    int quantity = Integer.parseInt(parms.get("quantity"));
    
   
    System.out.println("creating product"); // Debugging message 
    Product product = new Product(ID, category, artist, album,genre, sku, price, quantity);
    System.out.println("Product to add" + product);

    try {  
    products.addProduct(product); // add to database 
      

     out.write(
      "<html>" +
      "<head> <title>Product Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
      "<h1> Product Added </h1>"+
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
      "    <td>"+ product.getID() + "</td>" +
      "    <td>"+ product.getCategory() + "</td>" +
      "    <td>"+ product.getArtist() + "</td>" +
      "    <td>"+ product.getAlbum() + "</td>" +
      "    <td>"+ product.getGenre() + "</td>" +
      "    <td>"+ product.getSKU() + "</td>" +
      "    <td>"+ product.getPrice() + "</td>" +
      "    <td>"+ product.getQuantity() + "</td>" +
      "  </tr>" 
        );
   
      out.write(
      "</tbody>" +
      "</table>" +
      "<a href=\"/\"> Back to List </a>"+
      "</body>" +
      "</html>");
   }catch(SQLException se){
      System.out.println(se.getMessage());
    }
  
    out.close();

}
}
