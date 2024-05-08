import java.io.Serializable;
import java.util.ArrayList;

public class Clothes implements Serializable {
    private Long id;
    private String title;
    private String desc;
    private int price;
    private String type;
    private ArrayList<String> sizes;
    private ArrayList<String> colors;
    private int quantity;
    private boolean available;

    public Clothes(Long id, String title, String desc, int price, String type, ArrayList<String> sizes, ArrayList<String> colors,int quantity,boolean available) {
        this.id=id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.type = type;
        this.sizes=sizes;
        this.colors=colors;
        this.quantity=quantity;
        this.available=available;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<String> sizes) {
        this.sizes = sizes;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String s= id+" "+type+" "+title+" "+price+"\n Sizes:";
        for (int i=0;i<sizes.size();i++){
            s+=sizes.get(i);
        }
        s+="\n Colors:";
        for (int i=0;i<colors.size();i++){
            s+=colors.get(i);
        }
        return s;
    }
}
