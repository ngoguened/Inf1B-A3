package dataStructures;

/**
 * This file must remain exactly as it is.
 *
 * The ticket machine does not accept any denominations not already listed here.
 * Specifically: 1p, 2p and 5p coins are not used and neither are notes above £20.
 */
public interface ICashCount
{
	// ---------------- SETTERS ----------------
	
	/**
	 * @param noteCount Sets the number of 20 pound notes available in this pool of cash.
	 */
	public void setNrNotes_20pounds(int noteCount);
	
	/**
	 * @param noteCount Sets the number of 10 pound notes available in this pool of cash.
	 */
	public void setNrNotes_10pounds(int noteCount);
	
	/**
	 * @param noteCount Sets the number of 5 pound notes available in this pool of cash.
	 */
	public void setNrNotes_5pounds(int noteCount);
	
	/**
	 * @param coinCount Sets the number of 2 pound coins available in this pool of cash.
	 */
	public void setNrCoins_2pounds(int coinCount);
	
	/**
	 * @param coinCount Sets the number of 1 pound coins available in this pool of cash.
	 */
	public void setNrCoins_1pound(int coinCount);
	
	/**
	 * @param coinCount Sets the number of 50 pence coins available in this pool of cash.
	 */
	public void setNrCoins_50p(int coinCount);
	
	/**
	 * @param coinCount Sets the number of 20 pence coins available in this pool of cash.
	 */
	public void setNrCoins_20p(int coinCount);
	
	/**
	 * @param coinCount Sets the number of 10 pence coins available in this pool of cash.
	 */
	public void setNrCoins_10p(int coinCount);
	
	// ---------------- GETTERS ----------------
	
	/**
	 * @return Returns the number of 20 pound notes available in this pool of cash.
	 */
	public int getNrNotes_20pounds();
	
	/**
	 * @return Returns the number of 10 pound notes available in this pool of cash.
	 */
	public int getNrNotes_10pounds();
	
	/**
	 * @return Returns the number of 5 pound notes available in this pool of cash.
	 */
	public int getNrNotes_5pounds();
	
	/**
	 * @return Returns the number of 2 pound coins available in this pool of cash.
	 */
	public int getNrCoins_2pounds();
	
	/**
	 * @return Returns the number of 1 pound coins available in this pool of cash.
	 */
	public int getNrCoins_1pound();
	
	/**
	 * @return Returns the number of 50 pence coins available in this pool of cash.
	 */
	public int getNrCoins_50p();
	
	/**
	 * @return Returns the number of 20 pence coins available in this pool of cash.
	 */
	public int getNrCoins_20p();
	
	/**
	 * @return Returns the number of 10 pence coins available in this pool of cash.
	 */
	public int getNrCoins_10p();
}
