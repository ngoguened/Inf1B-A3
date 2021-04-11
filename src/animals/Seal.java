package animals;

public class Seal extends Animal {

    public Seal(String n) {
        super(n);
    }

    public boolean isCompatibleWith(Animal animal) {
        // Seals live with starfish but not sharks.
        if (animal instanceof Starfish || animal instanceof Seal) {
            return true;
        }
        return false;
    }

    public String contained() {
        return "areas.Aquarium";
    }
    

}
