 package model;

public class Server {

	private double cacheMemory;
	private int processerNumber;
	private Trademark trademark;
	private double ramMemory;
	private int discs;
	private double discCapacity;

	public Server(double cacheMemory, int processerNumber,Trademark trademark, double ramMemory,int discs,
		double discCapacity){

		this.cacheMemory = cacheMemory;
		this.processerNumber = processerNumber;
		this.trademark = trademark;
		this.ramMemory = ramMemory;
		this.discs = discs;
		this.discCapacity = discCapacity;

	}
	public double getCacheMemory() {
		return this.cacheMemory;
	}

	/**
	 * 
	 * @param cacheMemory
	 */
	public void setCacheMemory(double cacheMemory) {
		this.cacheMemory = cacheMemory;
	}

	public int getProcesserNumber() {
		return this.processerNumber;
	}

	/**
	 * 
	 * @param processerNumber
	 */
	public void setProcesserNumber(int processerNumber) {
		this.processerNumber = processerNumber;
	}

	public Trademark getTrademark() {
		return this.trademark;
	}

	/**
	 * 
	 * @param trademark
	 */
	public void setTrademark(Trademark trademark) {
		this.trademark = trademark;
	}

	public double getRamMemory() {
		return this.ramMemory;
	}

	/**
	 * 
	 * @param ramMemory
	 */
	public void setRamMemory(double ramMemory) {
		this.ramMemory = ramMemory;
	}

	public int getDiscs() {
		return this.discs;
	}

	/**
	 * 
	 * @param discs
	 */
	public void setDiscs(int discs) {
		this.discs = discs;
	}

	public double getDiscCapacity() {
		return this.discCapacity;
	}

	/**
	 * 
	 * @param discCapacity
	 */
	public void setDiscCapacity(double discCapacity) {
		this.discCapacity = discCapacity;
	}

}