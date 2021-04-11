package animals;

/**
 * The superclass for each animal. Buzzard, Gazelle, Lion, Parrot, Seal, Shark,
 * Starfish, and Zebra subclasses all extend Animals.
 */
public abstract class Animal {
	/**
	 * The stored nickname for each individual animal object.
	 */
	private String nickname;

	/**
	 * The animal constructor. All animals are given a nickname upon initialization.
	 * 
	 * @param n the nickname of the animal as a string.
	 */
	public Animal(String n) {
		nickname = n;
	}

	/**
	 * @return Returns this animal's given name.
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Check whether two animals can live together.
	 * 
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);

	/**
	 * @return Returns the area where an animal can be contained.
	 */
	public abstract String contained();
}
