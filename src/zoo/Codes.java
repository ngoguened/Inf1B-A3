package zoo;

/**
 * This file must remain exactly as it is.
 */
public final class Codes
{
	/**
	 * The animal was successfully added to the habitat.
	 */
	public static final byte ANIMAL_ADDED = 0;
	
	/**
	 * Cannot place animals into non-habitat areas, such as the picnic area.
	 */
	public static final byte NOT_A_HABITAT = 1;
	
	/**
	 * Animals can only be placed into appropriate habitats e.g. parrots cannot be kept in an aquarium.
	 * This error code takes precedence over HABITAT_FULL and INCOMPATIBLE_INHABITANTS.
	 */
	public static final byte WRONG_HABITAT = 2;
	
	/**
	 * The animal could not be added because the habitat is already full to capacity.
	 * This error code takes precedence over INCOMPATIBLE_INHABITANTS.
	 */
	public static final byte HABITAT_FULL = 3;
	
	/**
	 * The animal could not be added because it cannot share the habitat with its current occupants.
	 */
	public static final byte INCOMPATIBLE_INHABITANTS = 4;
}
