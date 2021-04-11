package zoo;

import animals.Animal;
import areas.IArea;
import dataStructures.ICashCount;

import java.util.ArrayList;

/**
 * This file must remain exactly as it is.
 */
public interface IZoo
{
	// ---------------- BASIC ----------------
	
	/**
	 * Adds a new area to the zoo.
	 * @param area The area to be added.
	 * @return An ID by which the added area can be uniquely identified
	 */
	public int addArea(IArea area);
	
	/**
	 * Removes the specified area from the zoo.
	 * @param areaId The ID of the area to be removed.
	 */
	public void removeArea(int areaId);
	
	/**
	 * Returns the area associated with the given ID.
	 * @param areaId The ID of the area to be fetched.
	 * @return The area corresponding to the given ID.
	 */
	public IArea getArea(int areaId);
	
	/**
	 * Tries to add the given animal to the specified area
	 * @param areaId The ID of the area the animal is to be added to.
	 * @param animal The animal to be added.
	 * @return Returns a code indicating success or failure. See {@link Codes}.
	 */
	public byte addAnimal(int areaId, Animal animal);
	
	
	// ---------------- INTERMEDIATE ----------------
	
	/**
	 * Allows visitors to move between areas in the given direction (from -> to).
	 * @param fromAreaId The ID of the area from which the destination is to be accessible.
	 * @param toAreaId The ID of the destination area.
	 */
	public void connectAreas(int fromAreaId, int toAreaId);
	
	/**
	 * Checks if the given path obeys the one-way system.
	 * @param areaIds The path is provided as a list of area IDs. It starts with the area ID at index 0.
	 * @return Returns true iff visitors are allowed to visit the areas in the order given by the passed in list.
	 */
	public boolean isPathAllowed(ArrayList<Integer> areaIds);
	
	/**
	 * Visits the areas in the specified order and records the names of all animals seen.
	 * @param areaIdsVisited Areas IDs in the order they were visited.
	 * @return Returns a list of the names of all animals seen during the visit in the order they were seen.
	 */
	public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited);
	
	/**
	 * Finds all areas that cannot be reached from the entrance of the zoo.
	 * @return The IDs of all inaccessible areas (unordered).
	 */
	public ArrayList<Integer> findUnreachableAreas();
	
	
	// ---------------- ADVANCED ----------------
	
	/**
	 * Sets a new ticket price in pounds and pence.
	 * @param pounds The first part of the cost before the point e.g. 17 for a ticket that costs £17.50
	 * @param pence The second part of the cost after the point e.g. 50 for a ticket that costs £17.50
	 */
	public void setEntranceFee(int pounds, int pence);
	
	/**
	 * Stocks the ticket machine with the provided pool of cash.
	 * @param coins The number of notes and coins of different denominations available.
	 */
	public void setCashSupply(ICashCount coins);
	
	/**
	 * Used to inspect the ticket machine's currently available pool of cash.
	 * @return The amount of each note and coin currently in the machine.
	 */
	public ICashCount getCashSupply();
	
	/**
	 * Takes an amount of cash inserted into the ticket machine and returns the appropriate change
	 * (if any) after deducting the amount of the entrance fee as set by @setEntranceFee.
	 * @param cashInserted The notes and coins inserted by the user buying a ticket.
	 * @return The change returned to the user (see assignment instructions for precise specification).
	 */
	public ICashCount payEntranceFee(ICashCount cashInserted);
}
