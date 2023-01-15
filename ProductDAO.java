import java.sql.*;
import java.util.ArrayList;

// import org.sqlite.JDBC;
public class ProductDAO {
    public ProductDAO() {
    }

    private static Connection getDBConnection() { //function
        Connection dbConnection = null; //object
        try {
            Class.forName("org.sqlite.JDBC"); //type of driver being used
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            String URL = "jdbc:sqlite:product.sqlite"; // product database
            dbConnection = DriverManager.getConnection(URL);
            //System.out.println("Connected..."); // tests connection
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    // -----------------------------------------------------------------------------------------------------
    public ArrayList<Product> getAllProducts() throws SQLException {
        System.out.println("Retrieving all products:\n");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM product;";
        // prepared statement: String query = "SELECT ID, category, artist, album, genre, SKU, price, quantity FROM product where id = ?;";
        ArrayList<Product> products = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement (query);
            statement = dbConnection.createStatement();
            //preparedStatement.setInt(1,1);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            //ResultSet rs = preparedStatement.executeQuery();
            while (result.next()) {

                int ID = result.getInt("ID");
                String category = result.getString("Category");
                String artist = result.getString("Artist");
                String album = result.getString("Album");
                String genre = result.getString("Genre");
                String SKU = result.getString("SKU");
                double price = result.getDouble("Price");
                int quantity = result.getInt("Quantity");

                products.add(new Product(ID, category, artist, album, genre, SKU, price, quantity));
            }
        } catch (Exception e) {
            System.out.println("Get all products: " + e);
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
        return products;
    }

    // -----------------------------------------------------------------------------------------------------
    public Product getProduct(int ID) throws SQLException {
        Product prod = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        //String query = "SELECT * FROM product WHERE ID =" + ID + ";";
        String query = "SELECT * FROM product WHERE ID = ?"; //dynamic parameter

        try {
            dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //Scanner sc = new Scanner(System.in); //close
            //int scan = sc.nextInt();
            statement = dbConnection.createStatement();
            preparedStatement.setInt(1, ID);
            //result = statement.executeQuery(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String category = rs.getString(2);
                String artist = rs.getString(3);
                String album = rs.getString(4);
                String genre = rs.getString(5);
                String SKU = rs.getString(6);
                double price = rs.getDouble(7);
                int quantity = rs.getInt(8);

                prod = new Product(ID, category, artist, album, genre, SKU, price, quantity);
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
        return prod;
    }

    // -----------------------------------------------------------------------------------------------------
  
    public boolean addProduct(Product in) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        //String insert = "INSERT INTO product" + " (ID, category, artist, album, genre, SKU, price, quantity) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);"; 
        String insert = "INSERT INTO product (ID, category, artist, album, genre, SKU, price, quantity) VALUES ("+ in.getID()+",'" + in.getCategory() + "','" + in.getArtist() + "','" + in.getAlbum() + "','" + in.getGenre() + "','" + in.getSKU() + "'," + in.getPrice() + "," + in.getQuantity() + ");";
      
        boolean yn = false;
        try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
            statement = dbConnection.createStatement();
            // preparedStatement.setInt(1, in.getID());
            // preparedStatement.setString(2, in.getCategory());
            // preparedStatement.setString(3, in.getArtist());
            // preparedStatement.setString(4, in.getAlbum());
            // preparedStatement.setString(5, in.getGenre());
            // preparedStatement.setString(6, in.getSKU());
            // preparedStatement.setDouble(7, in.getPrice());
            // preparedStatement.setInt(8, in.getQuantity());
            System.out.println("New product added.");
              
            //preparedStatement.executeUpdate();
            //preparedStatement.close();
            statement.executeUpdate(insert);

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
  
  public Boolean updateProduct (Product up) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String update = "UPDATE product " + "SET ID = '" + up.getID() + "'," + "Category = '" + up.getCategory() + "'," + "Artist = '" + up.getArtist() + "'," + "Album = '" + up.getAlbum() + "'," + "Genre = '" + up.getGenre() + "'," + "SKU = '" + up.getSKU() + "'," + "Price = " + up.getPrice() + ", Quantity = " + up.getQuantity() + " WHERE ID = " + up.getID() + ";";
   try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement(update);
            statement = dbConnection.createStatement();
            // preparedStatement.setInt(1, up.getID());
            // preparedStatement.setString(2, up.getCategory());
            // preparedStatement.setString(3, up.getArtist());
            // preparedStatement.setString(4, up.getAlbum());
            // preparedStatement.setString(5, up.getGenre());
            // preparedStatement.setString(6, up.getSKU());
            // preparedStatement.setDouble(7, up.getPrice());
            // preparedStatement.setInt(8, up.getQuantity());
            System.out.println("Product updated.");
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
  
  // -----------------------------------------------------------------------------------------------------
    public boolean deleteProduct(int product_ID) throws SQLException {
        System.out.println("Deleting product");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        //String query = "DELETE FROM product WHERE ID = ?;";
        String query = "DELETE FROM product WHERE ID =" + product_ID + ";";
        try {
            dbConnection = getDBConnection();
            //PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            //preparedStatement.setInt(1, product_ID);
            statement = dbConnection.createStatement();
            //	System.out.println(query);
            // execute SQL query
           result = statement.executeUpdate(query);
           System.out.println("Product deleted.");
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

//        public ArrayList<Product> getAllProducts;
//        public Product getProduct ( int customerID);
//        public void addProduct (Product product);
//        public void amendProduct (Product product);
//        public void deleteProduct (Product product);
