package Model;

public class ProductModel {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public ProductModel(int id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }    
    public ProductModel(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    //setters
    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
    public void setPrice(double price){ this.price = price; }
    //getters
    public int getId(){ return id; }
    public String getName(){ return name; }
    public int getQuantity(){ return quantity; }
    public double getPrice(){ return price; }
    
    
}
