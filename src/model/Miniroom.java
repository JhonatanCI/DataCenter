 package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Miniroom {

	private boolean roomWithWindow;
	private String id;
	private ArrayList<Server> rACK;
	private boolean on = false;
	private double value;
	private String dateOfRent;
	private boolean rented = false;

	public Miniroom(int rows, int columns, double baseValue){
		this.id = ""+rows+columns;
		this.roomWithWindow = hasWindow( rows,  columns);
		this.value = calculateValue(rows,  columns, baseValue);
	}

	/**
	 *
	 * Evaluate all the conditions to change the value and return the
	 * total
	 * @param row, where i see this before...? the row where is the miniroom
	 * @param column, the column where is the miniroom
	 * @param basevalue, base value for all minirooms
	 * @return out, the total
	 */
	public double calculateValue(int rows, int columns, double baseValue) {
		double out = baseValue;
		if (roomWithWindow==true) {
			out = out - (out*0.1);
		}
		if (rows==7) {
			out = out - (out*0.15);
		}
		if (rows>0 && rows<6){
			out = out + (out*0.25);
		}
		return out;
	}

	/**
	 * 
	 * @return roomWithWindow
	 */
	public boolean getRoomWithWindow() {
		return this.roomWithWindow;
	}

	/**
	 * 
	 * @param roomWithWindow
	 */
	public void setRoomWithWindow(boolean roomWithWindow) {
		this.roomWithWindow = roomWithWindow;
	}

	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return rACK[id]
	 */
	public Server getrACK(int id) {
		return rACK.get(id);
	}

	public int getRACKSize(){
		return rACK.size();
	}

	/**
	 * 
	 * @param i, int
	 */

	public void deleteRACK(int i){
		this.rACK.clear();
	}
	/**
	 * 
	 * @return out
	 */
	public boolean FourLess() {
		boolean out = false;
		return out;

	}

	/**
	 * 
	 * @return on
	 */
	public boolean getOn() {
		return this.on;
	}

	/**
	 * 
	 * @param on
	 */
	public void setOn(boolean on) {
		this.on = on;
	}

	/**
	 * Evaluate conditions for know if has window
	 * @param row, int
	 * @param columns, int
	 * @return out, boolean
	 */
	public boolean hasWindow(int row,int  columns){
		boolean out=false;
		if (row==0 || row==7 || columns==0 || columns==49) {
			out=true;
		}
		return out;
	}

	/**
	 * 
	 * @param row, int
	 * @param columns, int
	 * @return out, String
	 */
	public String toString(int row, int  columns){
		row += 1;
		columns += 1;
		String out = "Runner : "+row+"\n"+
		"Has window : "+roomWithWindow+"\n"+
		"Column : "+ columns+ "\n"+
		"Value : "+ value+"\n";

		return out;
	}

	
	public void setDate(String date){
		this.dateOfRent = date;
	}

	
	public double getValue(){
		return this.value;
	}

	/**
	 * Just define the RACK and change on and rented
	 * @param length
	 */
	public void defineRACK(int length){
		rACK = new ArrayList<Server>();
		if (length<4) {
			value += (value*0.15);
		}

		setRented(true);
		setOn(true);
	}

	public boolean getRented(){
		return this.rented;
	}

	public void setRented(boolean rented){
		this.rented = rented;
	}

	/**
	*omg so much params...
	*saves the server in the loved arraylist rack
	*@param cacheMemory, double cachemomory
	*@param processerNumber, int Number of processer
	*@param trademark, Trademark the... trademark of the... processer
	*@param ramMemory, double ramMemory
	*@param discs, int How many discs
	*@param discCapacity, double the capacity of the discs
	*"Ha i finish me from the past >:D" 
	*/
	public void saveServer(double cacheMemory, int processerNumber,Trademark trademark, double ramMemory,
		int discs,double discCapacity){

		rACK.add(new Server( cacheMemory,  processerNumber, trademark,  ramMemory,
		 discs, discCapacity));
	}
	/**
	*Do THE HARD PROCESS to get the id from the user, (i burn my coco here)
	*@return idForUser, The glorius ID 
	*/
	public String getIdForUser(){
		String idForUser = "";
		int n1=Character.getNumericValue(id.charAt(0))+1;
		int n2,n3;
		if (id.length()>2) {
			n2=Character.getNumericValue(id.charAt(1));
			n3=Character.getNumericValue(id.charAt(2));
			n3+=n2*10;
			n3+=1;
			idForUser+=n1+""+n3;
		}else{
			n2=Character.getNumericValue(id.charAt(1))+1;
			idForUser+=n1+""+n2;
		}
		return idForUser;
	}
}