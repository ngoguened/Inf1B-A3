package areas;

import java.util.ArrayList;

/**
 * The implementation of IArea.
 */
public abstract class Area implements IArea {

    /**
     * ArrayList of areaIds adjacent to this area.
     */
    private ArrayList<Integer> adjacentAreas = new ArrayList<Integer>();

    /**
     * @param areaId the areaId to be added to adjacentAreas.
     */
    public void addAdjacentArea(int areaId) {
        adjacentAreas.add(areaId);
    }

    /**
     * @param areaId the areaId to be removed from adjacentAreas.
     */
	public void removeAdjacentArea(int areaId){
        adjacentAreas.remove(Integer.valueOf(areaId));
    }

    /**
     * @return ArrayList of adjacent areas.
     */
    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreas;
    }
    
}
