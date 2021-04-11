package animals;

public class Shark extends Animal {

    public Shark(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Sharks live with starfish but not seals.
        if (animal instanceof Starfish || animal instanceof Shark) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Aquarium";
    }
    
}
