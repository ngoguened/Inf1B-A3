package areas;

import java.util.ArrayList;
import java.util.HashMap;
import animals.Animal;

/**
 * Area subclass with auxiliary functions and animal storage for animal habitats
 * that don't apply to human areas. Applies to Aquarium, Cage, and Enclosure.
 */
public abstract class AnimalHabitat extends Area {
    /**
     * The maximum number of animals that can be added to this animal habitat.
     */
    private int capacity;

    /**
     * The ArrayList of animals that live in this animal habitat.
     */
    private ArrayList<Animal> animals = new ArrayList<Animal>();

    /**
     * The animal habitat constructor. A capacity must be assigned upon
     * initialization.
     * 
     * @param c the maximum capacity of the animal habitat.
     */
    public AnimalHabitat(int c) {
        capacity = c;
    }

    /**
     * @param as the ArrayList to set the animals as in the animal habitat.
     */
    public void setAnimals(ArrayList<Animal> as) {
        animals = as;
    }

    /**
     * @return the animals in the animal habitat.
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * @return the maximum capaity of the animal habitat.
     */
    public int getCapacity() {
        return capacity;
    }
}