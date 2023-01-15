import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    static final private int PORT = 8080;

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Hello world!");

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new RootHandler() );
        server.createContext("/products", new DisplayAllProductHandler() );
        //server.createContext("/searchProduct", new SearchProductHandler() );
        server.createContext("/delete", new DeleteProductHandler() );
        server.createContext("/add", new AddProductHandler() );
        server.createContext("/processAddProduct", new ProcessAddProductHandler());
        server.createContext("/customers", new DisplayAllCustomerHandler() );
        // server.createContext("/searchCustomer", new SearchCustomerHandler() );
        server.createContext("/deleteCustomer", new DeleteCustomerHandler() );
        server.createContext("/addCustomer", new AddCustomerHandler() );
        server.createContext("/processAddCustomer", new ProcessAddCustomerHandler() );
      
        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);
      
        Scanner scanner = new Scanner(System.in);
        ProductDAO product = new ProductDAO();
        CustomerDAO customers = new CustomerDAO();
        //HTTP http = new HTTP();
        //RootHandler roothandler = new RootHandler();
        
        String options;

        do {
            System.out.println("\nThe Everything (Music) Store");

            System.out.println("\nChoose from the following options:");

            System.out.println("\n[1] List all products"); // 1. list all products
            System.out.println("[2] List all customers"); // 2. list all customers

            System.out.println("\n[3] Search product by ID"); // 3. search prodcuct by ID
            System.out.println("[4] Search customer by ID"); // 4. Search customer by ID

            System.out.println("\n[5] Insert new product"); // 5. Add new product
            System.out.println("[6] Insert new customer"); // 6. Add new customer

            System.out.println("\n[7] Update product"); // 7. Amend product
            System.out.println("[8] Update customer"); // 8. Amend customer

            System.out.println("\n[9] Amend order");

            System.out.println("\n[10] Delete product"); // 9. Delete product
            System.out.println("[11] Delete customer"); // 10. Delete customer
            System.out.println("\n[12] Delete order");

            System.out.println("\n[13] Exit"); // 11. exit

            options = scanner.nextLine();

            switch (options) {
                case "1" -> {
                    System.out.println("\nList all products");
                    ArrayList<Product> allProducts = product.getAllProducts();
                    for (int i = 0; i < allProducts.size(); i++) {
                        System.out.println(allProducts.get(i));
                    }
                    System.out.println();
                    break;
                }
                case "2" -> {
                    System.out.println("\nList all customers");
                    ArrayList<Customer> allCustomers = customers.getAllCustomers();
                    for (int i = 0; i < allCustomers.size(); i++) {
                        System.out.println(allCustomers.get(i));
                    }
                    System.out.println();
                    break;
                }
                case "3" -> {
                    System.out.println("\nSearch by product ID: ");
                    int ID = Integer.parseInt(scanner.nextLine());
                    System.out.println(product.getProduct(ID));
                    System.out.println();
                    break;
                    //case "3" -> System.out.println("List of all orders");
                }
                case "4" -> {
                    System.out.println("\nSearch by customer ID: ");
                    int cID = Integer.parseInt(scanner.nextLine());
                    System.out.println(customers.getCustomerID(cID));
                    System.out.println();
                    break;
                }
                case "5" -> {
                    System.out.println("\nCreate new product: ");
                    Product products = createProduct();
                    product.addProduct(products);
                    System.out.println("");
                    break;
                }
                case "6" -> {
                    System.out.println("\nCreate new customer: ");
                    Customer customerA = createCustomer();
                    customers.addCustomer(customerA);
                    System.out.println();
                    break;
                }
                case "7" -> {
                    System.out.println("\nUpdate product: ");
                    System.out.println("Enter product ID: ");
                    int xID = Integer.parseInt(scanner.nextLine());
                    System.out.println(product.getProduct(xID));
                    Product updatedProduct = updateProduct(product.getProduct(xID));
                    product.updateProduct(updatedProduct);
                    break;
                }
                case "8" -> {
                    System.out.println("\nUpdate customer: ");
                    System.out.println("Enter customer ID: ");
                    int cUID = Integer.parseInt(scanner.next());
                    System.out.println(customers.getCustomerID(cUID));
                    Customer updatedCustomer = updateCustomer(customers.getCustomerID(cUID));
                    customers.updateCustomer(updatedCustomer);

                }
                case "9" -> System.out.println("Amend order");
                case "10" -> {
                    System.out.println("Delete product");
                    System.out.println("Enter product ID to be deleted: ");
                    int dID = Integer.parseInt(scanner.nextLine());
                    product.deleteProduct(dID);
                }
                case "11" -> {
                System.out.println("Delete customer");
                    System.out.println("Enter customer ID to be deleted: ");
                    int cdID = Integer.parseInt(scanner.nextLine());
                    customers.deleteCustomer(cdID);
                }
                case "12" -> System.out.println("Delete orders");
                case "13" -> System.out.println("Exit");
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("13"));
    }
    private static Product createProduct() {
        int ID;
        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        Scanner scanner = new Scanner(System.in); //close

        System.out.println("Please enter ID: ");
        ID = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter Category: ");
        category = scanner.nextLine();
        System.out.println("Please enter Artist: ");
        artist = scanner.nextLine();
        System.out.println("Please enter Album: ");
        album = scanner.nextLine();
        System.out.println("Please enter Genre: ");
        genre = scanner.nextLine();
        System.out.println("Please enter SKU: ");
        SKU = scanner.nextLine();
        System.out.println("Please enter Price: ");
        price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter Quantity: ");
        quantity = Integer.parseInt(scanner.nextLine());

        return new Product (ID, category, artist, album, genre, SKU, price, quantity);
    }
    private static Customer createCustomer() {
        int customerID;
        String customerForename;
        String customerSurname;
        String customerAddress;
        String customerTelNo;

        Scanner scanner = new Scanner (System.in); //close

        System.out.println("Please enter ID: ");
        customerID = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter customer forename: ");
        customerForename = scanner.nextLine();
        System.out.println("Please enter customer surname: ");
        customerSurname = scanner.nextLine();
        System.out.println("Please enter address: ");
        customerAddress = scanner.nextLine();
        System.out.println("Please enter telephone number: ");
        customerTelNo = scanner.nextLine();
        return new Customer (customerID, customerForename, customerSurname, customerAddress, customerTelNo);
    }

    private static Product updateProduct(Product up) {
        int ID;
        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update product with product ID: " + up.getID());

        System.out.println("Update category: ");
        category = scanner.nextLine();
        if (category.equals(""))
            category = up.getCategory();

        System.out.println("Update artist: ");
        artist = scanner.nextLine();
        if (artist.equals(""))
            artist = up.getArtist();

        System.out.println("Update album title: ");
        album = scanner.nextLine();
        if (album.equals(""))
            album = up.getAlbum();

        System.out.println("Update genre: ");
        genre = scanner.nextLine();
        if (genre.equals(""))
            genre = up.getGenre();

        System.out.println("Update SKU: ");
        SKU = scanner.nextLine();
        if (SKU.equals(""))
            SKU = up.getSKU();

        System.out.println("Update price: ");
        String cvprice = scanner.nextLine();
        if (cvprice.equals(""))
            price = up.getPrice();
        else
            price = Double.parseDouble(cvprice);

        System.out.println("Update quantity: ");
        String cvquantity = scanner.nextLine();
        if (cvquantity.equals(""))
            quantity = up.getQuantity();
        else
            quantity = Integer.parseInt(cvquantity);

        return new Product(up.getID(), category, artist, album, genre, SKU, price, quantity);
    }
    private static Customer updateCustomer(Customer up) {
        int customerID;
        String customerForename;
        String customerSurname;
        String customerAddress;
        String customerTelNo;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update customer with customer ID: " + up.getCustomerID());

        System.out.println("Update Forename: ");
        customerForename = scanner.nextLine();
        if (customerForename.equals(""))
            customerForename = up.getCustomerForename();

        System.out.println("Update Surname: ");
        customerSurname = scanner.nextLine();
        if (customerSurname.equals(""))
            customerSurname = up.getCustomerSurname();

        System.out.println("Update Address: ");
        customerAddress = scanner.nextLine();
        if (customerAddress.equals(""))
            customerAddress = up.getCustomerAddress();

        System.out.println("Update Telephone Number: ");
        customerTelNo = scanner.nextLine();
        if (customerTelNo.equals(""))
            customerTelNo = up.getCustomerTelNo();

        return new Customer(up.getCustomerID(), customerForename, customerSurname, customerAddress, customerTelNo);
    }
}


//        Product product = new Product(0000000, "Default", "Default SKU0000000", "Default", 0.00);
//        Customer customer = new Customer();
//
//        System.out.print(product);
//        scanner.close();

