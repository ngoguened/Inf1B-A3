package animals;

public class Zebra extends Animal {
    
    public Zebra(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Zebraas are compatible with gazelles but not lions.
        if (animal instanceof Gazelle || animal instanceof Zebra) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Enclosure";
    }
}
