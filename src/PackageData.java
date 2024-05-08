import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operation;
    private Clothes cloth;
    private ArrayList<Clothes> clothes;

    public PackageData(String operation, Clothes cloth) {
        this.operation = operation;
        this.cloth = cloth;
    }

    public PackageData(String operation) {
        this.operation = operation;
    }

    public PackageData(ArrayList<Clothes> clothes) {
        this.clothes = clothes;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Clothes getCloth() {
        return cloth;
    }

    public void setCloth(Clothes cloth) {
        this.cloth = cloth;
    }

    public ArrayList<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(ArrayList<Clothes> clothes) {
        this.clothes = clothes;
    }
}
