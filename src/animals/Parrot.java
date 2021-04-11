package animals;

public class Parrot extends Animal {
    
    public Parrot(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Parrots are incompatible with buzzards.
        if (animal instanceof Parrot) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Cage";
    }
}
