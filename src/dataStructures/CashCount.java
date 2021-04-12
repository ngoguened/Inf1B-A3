package dataStructures;

/**
 * The implementation of ICashCount.
 */
public class CashCount implements ICashCount {

    /**
     * Index constants for each cash value in cashSupply.
     */
    public static final int TWENTY_POUND_NOTE = 0;
    public static final int TEN_POUND_NOTE = 1;
    public static final int FIVE_POUND_NOTE = 2;
    public static final int TWO_POUND_COIN = 3;
    public static final int ONE_POUND_COIN = 4;
    public static final int FIFTY_P_COIN = 5;
    public static final int TWENTY_P_COIN = 6;
    public static final int TEN_P_COIN = 7;

    /**
     * The storage of number of each type of cash.
     */
    private int[] cashSupply = new int[8];
    

    /**
     * @param noteCount the notecount for each respective type of cash.
     */
    public void setNrNotes_20pounds(int noteCount) {
        cashSupply[TWENTY_POUND_NOTE] = noteCount;
    }

    public void setNrNotes_10pounds(int noteCount) {
        cashSupply[TEN_POUND_NOTE] = noteCount;
    }

    public void setNrNotes_5pounds(int noteCount) {
        cashSupply[FIVE_POUND_NOTE] = noteCount;
    }

    public void setNrCoins_2pounds(int coinCount) {
        cashSupply[TWO_POUND_COIN] = coinCount;
    }

    public void setNrCoins_1pound(int coinCount) {
        cashSupply[ONE_POUND_COIN] = coinCount;
    }

    public void setNrCoins_50p(int coinCount) {
        cashSupply[FIFTY_P_COIN] = coinCount;
    }

    public void setNrCoins_20p(int coinCount) {
        cashSupply[TWENTY_P_COIN] = coinCount;
    }

    public void setNrCoins_10p(int coinCount) {
        cashSupply[TEN_P_COIN] = coinCount;
    }

    /**
     * @return the notecount for each respective type of cash.
     */
    public int getNrNotes_20pounds() {
        return cashSupply[TWENTY_POUND_NOTE];
    }

    public int getNrNotes_10pounds() {
        return cashSupply[TEN_POUND_NOTE];
    }

    public int getNrNotes_5pounds() {
        return cashSupply[FIVE_POUND_NOTE];
    }

    public int getNrCoins_2pounds() {
        return cashSupply[TWO_POUND_COIN];
    }

    public int getNrCoins_1pound() {
        return cashSupply[ONE_POUND_COIN];
    }

    public int getNrCoins_50p() {
        return cashSupply[FIFTY_P_COIN];
    }

    public int getNrCoins_20p() {
        return cashSupply[TWENTY_P_COIN];
    }

    public int getNrCoins_10p() {
        return cashSupply[TEN_P_COIN];
    }

}