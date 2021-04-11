package animals;

public class Starfish extends Animal {

    public Starfish(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Starfish live with both seals and sharks.
        if (animal instanceof Shark || animal instanceof Seal || animal instanceof Starfish) { 
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Aquarium";
    }
    
}
