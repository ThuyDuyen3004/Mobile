package Models;

import java.util.Objects;

public class Product {
    private String name;
    private String price;
    private String quality;
    private String sales;
    private String manufacture;
    private String specification;
    private String imagePath;


    public Product(String name, String price, String quality, String sales, String manufacture,
                   String specification, String imagePath) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.sales = sales;
        this.manufacture = manufacture;
        this.specification = specification;
        this.imagePath = imagePath;
    }

    public Product(String nameProductAfterAdd, String priceProductAfterAdd, String qualityProductAfterAdd,
                   String saleProductAfterAdd, String manufactureProductAfterAdd) {
        this.name = nameProductAfterAdd;
        this.price = priceProductAfterAdd;
        this.quality = qualityProductAfterAdd;
        this.sales = saleProductAfterAdd;
        this.manufacture = manufactureProductAfterAdd;
        this.specification = specification;
        this.imagePath = imagePath;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(quality, product.quality) &&
                Objects.equals(sales, product.sales) &&
                Objects.equals(manufacture, product.manufacture);
//        return this.name.equals(product.name) &&
//                this.price.equals(product.price) &&
//                this.quality.equals(product.quality) &&
//                this.sales.equals(product.sales) &&
//                this.manufacture.equals(product.manufacture) &&
//                this.specification.equals(product.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quality, sales, manufacture);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quality='" + quality + '\'' +
                ", sales='" + sales + '\'' +
                ", manufacture='" + manufacture + '\'' +
                '}';
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuality() {
        return quality;
    }

    public String getSales() {
        return sales;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getSpecification() {
        return specification;
    }

    public String getImagePath() {
        return imagePath;
    }
}
