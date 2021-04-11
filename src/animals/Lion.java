package animals;

public class Lion extends Animal {
    
    public Lion(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Lions are incompatible with zebras and gazelles.
        if (animal instanceof Lion) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Enclosure";
    }
}
