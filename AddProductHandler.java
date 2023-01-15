import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class AddProductHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
      ProductDAO product = new ProductDAO();
      //code to go here for DB
     out.write(
      "<html>" +
      "<head> <title>Product Library</title> "+
         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
      "</head>" +
      "<body>" +
       "<div class=\"container\">"+
      "<h1> Add Product </h1>"+
      "<form method=\"get\" action=\"/processAddProduct\">" +
      "<div class=\"form-group\"> "+
       
      "<label for=\"ID\">ID</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"id\" id=\"id\"> " + 
       
      "<label for=\"category\">Category</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\"> " + 

      "<label for=\"artist\">Artist</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"artist\" id=\"artist\"> " + 

       "<label for=\"album\">Album</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"album\" id=\"album\"> " + 

       "<label for=\"genre\">Genre</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"genre\" id=\"genre\"> " + 

       "<label for=\"sku\">SKU</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\"> " + 

      "<label for=\"price\">Price</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\"> " + 

      "<label for=\"quantity\">Quantity</label> " + 
      "<input type=\"text\" class=\"form-control\" name=\"quantity\" id=\"quantity\" >" +
       
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
