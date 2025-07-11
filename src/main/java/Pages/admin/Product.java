package Pages.admin;

public class Product {
    private String name;
    private String price;
    private String quality;
    private String sales;
    private String manufacture;
    private String specification;
    private String imagePath;

    public Product(String name, String price, String quality, String sales, String manufacture, String specification, String imagePath) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.sales = sales;
        this.manufacture = manufacture;
        this.specification = specification;
        this.imagePath = imagePath;
    }

    // Getters
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getQuality() { return quality; }
    public String getSales() { return sales; }
    public String getManufacture() { return manufacture; }
    public String getSpecification() { return specification; }
    public String getImagePath() { return imagePath; }
}
