public class Product {
    private int ID;
    private String category;
    private String artist;
    private String album;
    private String genre;
    private String SKU;
    private double price;
    private int quantity;


    public Product (int ID, String category, String artist, String album, String genre, String SKU, double price, int quantity){
        this.ID = ID;
        this.category = category;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.SKU = SKU;
        this.price = price;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Product: "
                + "\n ID: " + ID + "\n Category: " + category + "\n Artist: " + artist + "\n Album: " + album
                + "\n Genre: " + genre + "\n SKU: " + SKU + "\n Price: " + price + "\n Quantity: " + quantity;
    }

    public void addProduct(ProductDAO product) {
    }
    public void updateProduct(ProductDAO product){

    }

}
