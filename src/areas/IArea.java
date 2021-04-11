package areas;

import java.util.ArrayList;

/**
 * This file must remain exactly as it is.
 */
public interface IArea
{
	/**
	 * @return Returns the IDs of the areas adjacent to this one.
	 */
	public ArrayList<Integer> getAdjacentAreas();

	public void addAdjacentArea(int areaId);

	public void removeAdjacentArea(int areaId);

}
