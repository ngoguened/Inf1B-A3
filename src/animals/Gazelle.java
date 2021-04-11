package animals;

public class Gazelle extends Animal {
    
    public Gazelle(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Gazelles are compatible with zebras but not lions.
        if (animal instanceof Gazelle || animal instanceof Zebra) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Enclosure";
    }
}
