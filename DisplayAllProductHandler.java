import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DisplayAllProductHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody() ));

        ProductDAO products = new ProductDAO();
      
        try{
            ArrayList<Product> allProducts = products.getAllProducts();
      
            out.write(
                    "<html>" +
                            "<head> <title>Product Library</title> "+
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Products in Stock </h1>"+
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>ID:</th>" +
                            "    <th>Category:</th>" +
                            "    <th>Artist:</th>" +
                            "    <th>Album:</th>" +
                            "    <th>Genre:</th>" +
                            "    <th>SKU:</th>" +
                            "    <th>Price:</th>" +
                            "    <th>Quantity:</th>" +
                            "    <th>Delete:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            for (Product p : allProducts){
                out.write(
                        "  <tr>"       +
                                "    <td><b>"+ p.getID() + "<b></td>" +
                                "    <td>"+ p.getCategory() + "</td>" +
                                "    <td>"+ p.getArtist() + "</td>" +
                                "    <td>"+ p.getAlbum() + "</td>" +
                                "    <td>"+ p.getGenre() + "</td>" +
                                "    <td>"+ p.getSKU() + "</td>" +
                                "    <td>"+ p.getPrice() + "</td>" +
                                "    <td>"+ p.getQuantity() + "</td>" +
                                "<td><a href=\"/delete?id=" + p.getID() + "\"> delete </a><td>" +
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