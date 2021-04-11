package animals;

public class Buzzard extends Animal {

    public Buzzard(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Buzzards are incompatible with parrots.
        if (animal instanceof Buzzard) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Cage";
    }
}
