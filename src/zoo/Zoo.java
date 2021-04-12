package zoo;

import animals.Animal;
import animals.Zebra;
import animals.Seal;
import animals.Shark;
import animals.Starfish;
import areas.AnimalHabitat;
import areas.Enclosure;
import areas.Aquarium;
import areas.Entrance;
import areas.HumanArea;
import areas.IArea;
import areas.PicnicArea;
import dataStructures.ICashCount;
import dataStructures.CashCount;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The implementation of ICashCount. Stores Areas and CashCounts (Ticket machines).
 */
public class Zoo implements IZoo {

    /**
     * The values of each cash denomination in pence. This reduces code duplication
     * when used in conjuction with getNrCash and setNrCash.
     */
    public static final Integer CASH_VALUES[] = new Integer[] { 
        2000, 1000, 500, 200, 100, 50, 20, 10 
    };

    /**
     * HashMap representing the areas in the zoo. Stores the areaIds as a key and
     * the IArea objects as a value.
     */
    private HashMap<Integer, IArea> areas = new HashMap<Integer, IArea>();

    /**
     * ICashCount representing the cash inside the cash machine.
     */
    private ICashCount cashCount = new CashCount();

    /**
     * Integer representing the entrance fee in pence.
     */
    private int entranceFeePence;

    /**
     * Zoo constructor. All zoos must have an entrance. The constructor means every
     * zoo is created with an entrance.
     */
    public Zoo() {
        Entrance entrance = new Entrance();
        addArea(entrance);
    }

    /**
     * Generates a unique areaId for a given area. An entrance area will always have
     * an areaId of 0.
     * 
     * @param area the area that will be assigned an areaId.
     * @return the areaId for the given area.
     */
    public int generateAreaId(IArea area) {
        if (area instanceof Entrance) {
            return 0;
        }
        int areaId = 1;
        while (areas.containsKey(areaId)) {
            areaId++;
        }
        return areaId;
    }

    /**
     * When given an area, addArea generates an id for it (assuming it is not
     * already in areas.) and puts the area and the key in areas.
     * 
     * @param area the area to be stored in areas.
     * @return the generated key.
     * @throws IllegalArgumentException Checks that same area is not already in the
     *                                  zoo.
     */
    public int addArea(IArea area) {
        if (areas.containsValue(area)) {
            throw new IllegalArgumentException("You cannot add an already existing area!");
        }
        int key = generateAreaId(area);
        areas.put(key, area);
        return key;
    }

    /**
     * Removes an area from areas.
     * 
     * @param areaId the area to be removed from areas.
     * @throws IllegalArgumentException the entrance cannot be removed from the zoo.
     */
    public void removeArea(int areaId) {
        if (!(getArea(areaId) instanceof Entrance)) {
            areas.remove(areaId);
            return;
        }
        throw new IllegalArgumentException("You cannot remove the entrance!");
    }

    /**
     * Gets the area from areas that pairs with the given areaId key.
     * 
     * @param areaId the areaId key for an area in areas.
     * @return the area associated with the areaId key in areas.
     */
    public IArea getArea(int areaId) {
        return areas.get(areaId);
    }

    /**
     * Checks that an animal is compatible with a HashMap of animals
     * 
     * @param animal  the animal being compared to the other animals.
     * @param animals the animal HashMap where each member is compared against
     *                animal.
     * @return true if the animal is compatible with each member of animals.
     *         Otherwise false.
     */
    public boolean isAnimalCompatibleWithOthers(Animal animal, ArrayList<Animal> animals) {
        for (Animal a : animals) {
            if (!animal.isCompatibleWith(a)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to add an animal to the given area's animal HashMap.
     * 
     * @param areaId the areaId of the area where the given animal is being
     *               attempted to be palced.
     * @param animal the animal being attempted to be placed in the given area.
     * @return a byte specifying the outcome of addAnimal. See Codes.java for byte
     *         descriptions.
     */
    public byte addAnimal(int areaId, Animal animal) {
        IArea area = getArea(areaId);
        Codes codes = new Codes();
        if (area instanceof HumanArea) {
            return codes.NOT_A_HABITAT;
        } else if (area.getClass().getName().toString() != animal.contained()) {
            return codes.WRONG_HABITAT;
        }
        AnimalHabitat habitat = (AnimalHabitat) area;
        ArrayList<Animal> animals = habitat.getAnimals();
        if (habitat.isHabitatFull()) {
            return codes.HABITAT_FULL;
        } else if (!isAnimalCompatibleWithOthers(animal, animals)) {
            return codes.INCOMPATIBLE_INHABITANTS;
        } else {
            animals.add(animal);
            habitat.setAnimals(animals);
            return codes.ANIMAL_ADDED;
        }
    }

    // Intermediate

    /**
     * Connects one area to another area. This connection is one way. Connections
     * must be unique. Given areas must exist. If already connected, does nothing.
     * 
     * @param fromAreaId the area receiving a connection.
     * @param toAreaId   the area fromAreaId is receiving.
     * @throws IllegalArgumentException one or both of fromAreaId and toAreaId do not exist.
     */
    public void connectAreas(int fromAreaId, int toAreaId) {
        if (areas.get(fromAreaId).getAdjacentAreas().contains(toAreaId)) {
            return;
        }
        if (!areas.containsKey(fromAreaId) || !areas.containsKey(toAreaId)) {
            throw new IllegalArgumentException("At least one argument does not exist in areas.");
        }
        areas.get(fromAreaId).addAdjacentArea(toAreaId);
    }

    /**
     * Checks if a given areaId path is allowed.
     * 
     * @param areaIds the ArrayList of areaIds that represent the path order.
     * @return true if the areaId is the last in the areaIds list. If at any point
     *         in the loop the areaId is not adjacent to the next areaId in areaIds,
     *         it returns false.
     */
    public boolean isPathAllowed(ArrayList<Integer> areaIds) {

        for (int i = 0; i < areaIds.size(); i++) {
            if (i == areaIds.size() - 1) {
                // Is the current area the final area of the path.
                if (areaIds.get(i) == areaIds.get(areaIds.size() - 1)) {
                    return true;
                }
                return false;
            }
            // Is the next area of the path in the adjacent area of the current area.
            if (!areas.get(areaIds.get(i)).getAdjacentAreas().contains(areaIds.get(i + 1))) {
                return false;
            }
        }
        return false;
    }

    /**
     * The list of nicknames of animals a visitor would write down when going down a
     * given path.
     * 
     * @param areaIdsVisited the path the visitor takes acoss the zoo. Must be a
     *                       valid path.
     * @return the list of nickames of animals the visitor saw. In order of when
     *         they are added to the habitat.
     */

    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        if (!isPathAllowed(areaIdsVisited)) {
            return null;
        }
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < areaIdsVisited.size(); i++) {
            if (areas.get(areaIdsVisited.get(i)) instanceof AnimalHabitat) {
                AnimalHabitat aH = (AnimalHabitat) areas.get(areaIdsVisited.get(i));
                // Add all of the animal nicknames in each animal habitat to names.
                for (int j = 0; j < aH.getAnimals().size(); j++) {
                    names.add(aH.getAnimals().get(j).getNickname());
                }
            }
        }
        return names;
    }

    /**
     * Initiates a depth-first tree search of the areas in the zoo through
     * recursion. When an area is reached, it is removed from unreachableAreas.
     * 
     * @param unreachableAreas the list of areas that have not been discovered at
     *                         that point by the search.
     * @param areaId           the area that is being searched for adjacent areas.
     */
    public void unreachableAreasRec(ArrayList<Integer> unreachableAreas, Integer areaId) {
        if (!unreachableAreas.contains(areaId)) {
            return;
        }
        unreachableAreas.remove(areaId);
        ArrayList<Integer> areaIds = new ArrayList<Integer>();
        areaIds.addAll(areas.get(areaId).getAdjacentAreas());
        for (int i = 0; i < areaIds.size(); i++) {
            unreachableAreasRec(unreachableAreas, areaIds.get(i));
        }
    }

    /**
     * Finds unreachable areas in the zoo from the entrance.
     * 
     * @return the unreachable areas.
     */
    public ArrayList<Integer> findUnreachableAreas() {
        ArrayList<Integer> unreachableAreas = new ArrayList<Integer>();
        unreachableAreas.addAll(areas.keySet());
        Integer areaId = unreachableAreas.get(0);
        unreachableAreasRec(unreachableAreas, areaId);
        return unreachableAreas;
    }

    // Advanced

    /**
     * returns the value of pence from pounds. Makes setEntranceFee more obvious.
     * 
     * @param pounds value in pounds.
     * @return value in pence.
     */
    public int toPence(int pounds) {
        return pounds * 100;
    }

    /**
     * Sets the entrance fee. All money stored as pence to keep int type.
     * 
     * @param pounds the number of pounds the entrace fee will be set at.
     * @param pence  the number of pence the entrace fee will be set at.
     */
    public void setEntranceFee(int pounds, int pence) {
        entranceFeePence = pence + toPence(pounds);
    }

    /**
     * Sets the amount of cash in the cash machine. Deep copy because machine is
     * actually being stocked rather than representing another machine.
     * 
     * @param coins the ICashCount values for the cash to be stored in the machine.
     */
    public void setCashSupply(ICashCount coins) {
        for (int i = 0; i < CASH_VALUES.length; i++) {
            setNrCash(cashCount, CASH_VALUES[i], getNrCash(coins, CASH_VALUES[i]));
        }
    }

    /**
     * returns the cash supply in the cash machine.
     */
    public ICashCount getCashSupply() {
        return cashCount;
    }

    /**
     * Calculates the change.Goes in order from largest to smallest denominations
     * for minimal number of change.
     * 
     * @param changePence     the change in pence as an integer that needs to be
     *                        returned.
     * @param cashCountChange an empty ICashCount object.
     * @return the cashCountChange with the correct denomination of cash.
     */
    public ICashCount calculateChange(int changePence, ICashCount cashCountChange) {
        for (int i = 0; i < CASH_VALUES.length; i++) {
            while (changePence >= CASH_VALUES[i] && getNrCash(cashCount, CASH_VALUES[i]) >= 1) {
                // Add cash to change, remove from machine, remove pence value.
                setNrCash(cashCountChange, CASH_VALUES[i], getNrCash(cashCountChange, CASH_VALUES[i]) + 1);
                setNrCash(cashCount, CASH_VALUES[i], getNrCash(cashCount, CASH_VALUES[i]) - 1);
                changePence = changePence - CASH_VALUES[i];
            }
        }
        if (changePence != 0) {
            return null;
        }
        return cashCountChange;
    }

    /**
     * Pays the entrance fee.
     * 
     * @param cashInserted the ICashCount object represeting the cash inserted into
     *                     the machine.
     * @return If the cash inserted is not enough to pay for the fee, cashInserted
     *         is returned. Otherwise, the correct change is returned.
     */
    public ICashCount payEntranceFee(ICashCount cashInserted) {
        int valueInsertedPence = 0;
        // Makes the valueInsertedPence integer equal to the sum of all coinage values
        // in the given ICashCount.
        for (int i = 0; i < CASH_VALUES.length; i++) {
            valueInsertedPence = valueInsertedPence + getNrCash(cashInserted, CASH_VALUES[i]) * CASH_VALUES[i];
        }
        if (valueInsertedPence < entranceFeePence) {
            return cashInserted;
        }
        // Put inserted money in machine
        for (int i = 0; i < CASH_VALUES.length; i++) {
            setNrCash(cashCount, CASH_VALUES[i],
                    getNrCash(cashCount, CASH_VALUES[i]) + getNrCash(cashInserted, CASH_VALUES[i]));
        }
        int changePence = valueInsertedPence - entranceFeePence;
        ICashCount cashCountChange = new CashCount();
        calculateChange(changePence, cashCountChange);
        if (cashCountChange == null) {
            // Inserted money taken out and given back.
            for (int i = 0; i < CASH_VALUES.length; i++) {
                setNrCash(cashCount, CASH_VALUES[i],
                        getNrCash(cashCount, CASH_VALUES[i]) - getNrCash(cashInserted, CASH_VALUES[i]));
            }
            return cashInserted;
        }
        return cashCountChange;
    }

    // Setters

    /**
     * Sets notes based on type input. Reduces code duplication. When all notes need
     * to be set, a for loop with this setter can be used.
     * 
     * @param cashCount the ICashCount object that will be set.
     * @param type      the value of cash being set.
     * @param coincount the number to set the cash as.
     */
    public void setNrCash(ICashCount cashCount, int type, int coincount) {
        if (type == 2000) {
            cashCount.setNrNotes_20pounds(coincount);
            return;
        }
        if (type == 1000) {
            cashCount.setNrNotes_10pounds(coincount);
            return;
        }
        if (type == 500) {
            cashCount.setNrNotes_5pounds(coincount);
            return;
        }
        if (type == 200) {
            cashCount.setNrCoins_2pounds(coincount);
            return;
        }
        if (type == 100) {
            cashCount.setNrCoins_1pound(coincount);
            return;
        }
        if (type == 50) {
            cashCount.setNrCoins_50p(coincount);
            return;
        }
        if (type == 20) {
            cashCount.setNrCoins_20p(coincount);
            return;
        }
        if (type == 10) {
            cashCount.setNrCoins_10p(coincount);
            return;
        }
        throw new IllegalArgumentException("Must be 2000, 1000, 500, 200, 100, 50, 20, or 10.");
    }

    // Getters

    /**
     * Gives the areas. Used in personal tests.
     * 
     * @return the areas.
     */
    public HashMap<Integer, IArea> getAreas() {
        return areas;
    }

    /**
     * Gets the number of notes based on type input. Reduces code duplication. When
     * all notes need to be got, a for loop with this getter can be used.
     * 
     * @param cashCount the ICashCount object that will be set.
     * @param type      the value of cash being set.
     * @return the number of cash of the given type.
     */
    public int getNrCash(ICashCount cashCount, int type) {
        if (type == 2000) {
            return cashCount.getNrNotes_20pounds();
        }
        if (type == 1000) {
            return cashCount.getNrNotes_10pounds();
        }
        if (type == 500) {
            return cashCount.getNrNotes_5pounds();
        }
        if (type == 200) {
            return cashCount.getNrCoins_2pounds();
        }
        if (type == 100) {
            return cashCount.getNrCoins_1pound();
        }
        if (type == 50) {
            return cashCount.getNrCoins_50p();
        }
        if (type == 20) {
            return cashCount.getNrCoins_20p();
        }
        if (type == 10) {
            return cashCount.getNrCoins_10p();
        }
        throw new IllegalArgumentException("Must be 2000, 1000, 500, 200, 100, 50, 20, or 10.");
    }

    public static void main(String[] args) {
        // Testing

        // generateAreaId
        Zoo zoo = new Zoo();
        Entrance entrance = new Entrance();
        assert (zoo.generateAreaId(entrance) == 0) : zoo.generateAreaId(entrance);

        PicnicArea picnicArea1 = new PicnicArea();
        assert (zoo.generateAreaId(picnicArea1) == 1);

        PicnicArea picnicArea2 = new PicnicArea();
        zoo.getAreas().put(1, picnicArea1);
        assert (zoo.getAreas().containsKey(1));
        assert (zoo.generateAreaId(picnicArea2) == 2);
        zoo.getAreas().put(0, entrance);
        assert (zoo.generateAreaId(picnicArea2) == 2);
        assert (zoo.generateAreaId(entrance) == 0);

        PicnicArea picnicArea3 = new PicnicArea();
        zoo.getAreas().put(2, picnicArea2);
        assert (zoo.getAreas().containsKey(2));
        assert (zoo.generateAreaId(picnicArea3) == 3);
        zoo.getAreas().clear();

        // addArea
        Entrance entrance2 = new Entrance();
        zoo.getAreas().put(0, entrance);
        assert (zoo.addArea(entrance2) == 0);
        assert (zoo.getAreas().size() == 1);

        zoo.addArea(picnicArea1);
        assert (zoo.getAreas().size() == 2);
        zoo.getAreas().clear();

        // removeArea
        zoo.addArea(entrance);
        zoo.addArea(picnicArea1);
        zoo.removeArea(1);
        assert (zoo.getAreas().size() == 1) : zoo.getAreas();
        zoo.getAreas().clear();

        // getArea
        zoo.addArea(entrance);
        assert (zoo.getArea(0) == entrance);
        zoo.addArea(picnicArea1);
        assert (zoo.getArea(1) == picnicArea1);
        zoo.getAreas().clear();

        // addAnimal
        Enclosure enclosure = new Enclosure(1);
        Zebra zebra = new Zebra("Stripes");
        zoo.addArea(enclosure);
        assert (zoo.addAnimal(1, zebra) == 0);
        zoo.addArea(entrance);
        assert (zoo.addAnimal(0, zebra) == 1);
        Seal seal = new Seal("Blubber");
        assert (zoo.addAnimal(1, seal) == 2);

        zoo.addAnimal(1, zebra);
        assert (!enclosure.getAnimals().isEmpty());
        assert (zoo.addAnimal(1, zebra) == 3);

        Aquarium aquarium = new Aquarium(5);
        zoo.addArea(aquarium);
        zoo.addAnimal(2, seal);
        Shark shark = new Shark("Sharky");
        assert (zoo.addAnimal(2, shark) == 4);
        assert (enclosure.getAnimals().size() == 1);
        zoo.getAreas().clear();

        // addAdjacentAreas
        AnimalHabitat habitat = new AnimalHabitat(10) {
        };
        habitat.addAdjacentArea(1);
        assert (!habitat.getAdjacentAreas().isEmpty());

        // removeAdjacentAreas
        habitat.removeAdjacentArea(1);
        assert (habitat.getAdjacentAreas().isEmpty());

        // connectAreas
        zoo.addArea(entrance);
        zoo.addArea(enclosure);
        zoo.connectAreas(0, 1);
        assert (!entrance.getAdjacentAreas().isEmpty());

        // isPathAllowed
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(0);
        path.add(1);
        assert (zoo.isPathAllowed(path));
        path.clear();

        path.add(1);
        path.add(0);
        assert (!zoo.isPathAllowed(path));
        path.clear();

        Enclosure enclosure2 = new Enclosure(10);
        zoo.addArea(enclosure2);
        zoo.addArea(aquarium);
        zoo.connectAreas(1, 3);

        path.add(0);
        path.add(1);
        path.add(2);
        assert (!zoo.isPathAllowed(path));
        path.clear();

        path.add(0);
        path.add(1);
        path.add(3);
        assert (zoo.isPathAllowed(path));

        // visit
        zoo.connectAreas(3, 2);
        path.add(2);
        Starfish starfish = new Starfish("Star");
        zoo.addAnimal(3, starfish);
        // System.out.println(zoo.visit(path)); prints what I expect in correct order!

        // findUnreachableAreas
        ArrayList<Integer> sampleUnreachableAreas = new ArrayList<Integer>();
        assert (zoo.findUnreachableAreas().equals(sampleUnreachableAreas)) : (zoo.findUnreachableAreas());

        Aquarium aquarium2 = new Aquarium(1);
        zoo.addArea(aquarium2);
        sampleUnreachableAreas.add(4);
        assert (zoo.findUnreachableAreas().equals(sampleUnreachableAreas)) : (zoo.findUnreachableAreas());

        path.clear();
        zoo.areas.clear();

        zoo.addArea(entrance);
        zoo.addArea(aquarium);
        zoo.connectAreas(0, 1);
        zoo.connectAreas(1, 0);
        assert (zoo.findUnreachableAreas().isEmpty());

        // setNrNotes and getNrNotes
        zoo.cashCount.setNrNotes_10pounds(1);
        assert (zoo.cashCount.getNrNotes_10pounds() == 1);

        // setCashSupply
        ICashCount cashSupply = new CashCount();
        cashSupply.setNrNotes_5pounds(2);
        zoo.setCashSupply(cashSupply);
        assert (zoo.cashCount.getNrNotes_10pounds() == 0);
        assert (zoo.cashCount.getNrNotes_5pounds() == 2);

        // setEntranceFee
        zoo.setEntranceFee(5, 0);
        assert (zoo.entranceFeePence == 500);

        // payEntranceFee
        ICashCount cashInserted = new CashCount();
        cashInserted.setNrNotes_10pounds(1);

        assert (zoo.payEntranceFee(cashInserted).getNrNotes_5pounds() == (zoo.cashCount.getNrNotes_5pounds()))
                : zoo.payEntranceFee(cashInserted).getNrNotes_5pounds();
        assert (zoo.cashCount.getNrNotes_10pounds() == 1);
        assert (zoo.calculateChange(501, cashInserted) == null);
    }
}