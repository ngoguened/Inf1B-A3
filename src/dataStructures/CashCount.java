package dataStructures;

/**
 * The implementation of ICashCount.
 */
public class CashCount implements ICashCount {
    /**
     * The storage of number of each type of cash. Index 0 = 20 pound notes, index 1
     * = 10 pound notes, index 2 = 5 pound notes, index 3 = 2 pound coins, index 4 =
     * 1 pound coins, index 5 = 50 pence coins. index 6 = 20 pence coins, index 7 =
     * 10 pence coins.
     */
    private int[] cashSupply = new int[8];

    /**
     * @param noteCount the notecount for each respective type of cash.
     */
    public void setNrNotes_20pounds(int noteCount) {
        cashSupply[0] = noteCount;
    }

    public void setNrNotes_10pounds(int noteCount) {
        cashSupply[1] = noteCount;
    }

    public void setNrNotes_5pounds(int noteCount) {
        cashSupply[2] = noteCount;
    }

    public void setNrCoins_2pounds(int coinCount) {
        cashSupply[3] = coinCount;
    }

    public void setNrCoins_1pound(int coinCount) {
        cashSupply[4] = coinCount;
    }

    public void setNrCoins_50p(int coinCount) {
        cashSupply[5] = coinCount;
    }

    public void setNrCoins_20p(int coinCount) {
        cashSupply[6] = coinCount;
    }

    public void setNrCoins_10p(int coinCount) {
        cashSupply[7] = coinCount;
    }

    /**
     * @return the notecount for each respective type of cash.
     */
    public int getNrNotes_20pounds() {
        return cashSupply[0];
    }

    public int getNrNotes_10pounds() {
        return cashSupply[1];
    }

    public int getNrNotes_5pounds() {
        return cashSupply[2];
    }

    public int getNrCoins_2pounds() {
        return cashSupply[3];
    }

    public int getNrCoins_1pound() {
        return cashSupply[4];
    }

    public int getNrCoins_50p() {
        return cashSupply[5];
    }

    public int getNrCoins_20p() {
        return cashSupply[6];
    }

    public int getNrCoins_10p() {
        return cashSupply[7];
    }

}