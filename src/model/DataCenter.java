 package model;

import java.util.ArrayList;
import java.util.Iterator;

public class DataCenter {

	private Miniroom[][] runners; //The matrix of the miniroom 
	private ArrayList<Company> company; //arraylist for companys because... we LOVE arraylist

	/**
	 *	
	 * Constructor of DataCenter
	 *@param value, This is the base value for all minirroms (we ask it at the start of main)
	 */ 
	public DataCenter(double value){
		runners= new Miniroom[8][50];
		company = new ArrayList<Company>();
		for (int i=0;i<runners.length;i++ ) {
			for (int j = 0; j < runners[0].length; j++) {
				runners[i][j] = new Miniroom(i,j,value);//start the values for minirroms
			}
		}
	}

	/**
	 *
	 * We get a miniroom from id
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom
	 * @return out, Miniroom THE miniroom hehe
	 */
	public Miniroom getRunner(int row, int columns) {
		Miniroom out = runners[row][columns];

		return out;
	}

	/**
	 *
	 * Edit a miniroom, i just do it because... all methot needs sets but
	 * we dont really use it(maybe in a future but i want sleep after this)
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom
	 * @return miniroom, Miniroom THE miniroom again hehehehehe
	 */
	public void setRunner(int row, int columns, Miniroom miniroom) {
		this.runners[row][columns] = miniroom;
	}

	/**
	 *
	 * Make a String with all the minirooms that are avaible for rent
	 * @return print, String ALL THE miniroomS again HEHEHEHEHEHE
	 */
	public String showMinirooms() {
		String print ="";
		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (runners[i][j].getRented()==false) {
					print += runners[i][j].toString(i,j) + "\t";
				}
			}
			print += "\n";
		}		

		return print;
	}

	/**
	 *
	 * Verify if the id exist, just that
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom
	 * @return out, boolean ALL THE... okay just a boolean that says you true or false
	 */
	public boolean verifyId(int row, int column){
		boolean out = false;
		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (row==i && column==j) {
					out = true;
				}
			}
		}
		return out;
	}

	/**
	 *
	 * Verify if the id exist, just that
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom
	 * @param date, the date of the rent
	 * @return value, double the total value of the rent
	 */
	public double rent(int rows, int columns, String date){
		

		runners[rows][columns].setDate(date);

		return runners[rows][columns].getValue();
	}

	/**
	 *
	 * Set the length of the servers (i try to be funny for no do bored read my code because that
	 * could be a hard work but im without ideas sorry UnU)
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom
	 * @param length, the length of the array of servers
	 */
	public void serversQuantity(int rows, int columns, int length){
		

		runners[rows][columns].defineRACK(length);
	}

	/**
	 *
	 * Idk why i named serversInfo but anyways just saves all the info for a server
	 * @param row, i think i said it enought times...(pls im sacrificing my 5 dont give me bad grade for 
	 * this)
	 * @param columns, hmmmm... you know what is a deja vu?
	 * @param cacheMemory, The name says it right?... okay okay "the cacheMemory of the server"
	 * @param processerNumber, ...Again? "How many processer we can get?"
	 * @param trademark, I know this maybe is bored but "trademark"
	 * @param ramMemory, The ram memory of the server
	 * @param discs, Before the university i was a good boy i promise "The number of discs"
	 * @param discCapacity, The capacity of... yes, you know it
	 */
	public void serversInfo(int rows, int columns, double cacheMemory, int processerNumber,
		Trademark trademark, double ramMemory,int discs,double discCapacity){

		runners[rows][columns].saveServer(cacheMemory,  processerNumber, trademark,  ramMemory,
		 discs, discCapacity);

	}

	/**
	 *
	 * Says you if the miniroom with that id is rented or no, simply but effective
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom(...?)
	 * @return rented, boolean says true if is rented or false if not
	 */
	public boolean isRented(int rows,int columns){
		return runners[rows][columns].getRented();
	}

	/**
	 *
	 * Identify if the company is register before, if yes save the miniroom
	 * on his array if not create a new company (IM A GENIUS MUAHAHAHAH)
	 * @param name, The name of the company
	 * @param nit, The nit of the company (Here i had the idea to valid the unique nit, i like sleep
	 * but i like more program >:D)
	 * @param row, the row where is the miniroom
	 * @param columns, the column where is the miniroom(...omg...)
	 * @return rented, boolean says true if is rented or false if not
	 */
	public void CompanyRent(String name, String nit, int row, int column){
		String id = ""+row+column;

		if (foundPositionCompany(nit)>=0) {
			company.get(foundPositionCompany(nit)).addMiniroom(id);
		}else{
			company.add(new Company(name,nit,id));
		}

	}

	/**
	 *
	 * Found the position of the company with his nit
	 * @param nit, THE nit hehe
	 * @return out, return -1 if the company doesnt is so this methot say
	 * 1: if the company exist and 2: where is the company if exist
	 */
	public int foundPositionCompany(String nit){
		int out = -1;
		for (int i = 0;i<company.size() ;i++ ) {
			if (company.get(i).getNit().equals(nit)) {
				out = i;
			}
		}
		return out;
	}

	/**
	 *
	 * The same that before just searching with an id of miniroom
	 * @param row, the row where is the miniroom
	 * @param columns, you kwno what is a deja vu?...
	 * @return out, return -1 if the company doesnt is so this methot say
	 * 1: if the company exist and 2: where is the company if exist
	 */
	public int foundCompanyFromId(int row, int column){
		int out = -1;
		String id = ""+row+column;
		for (int i = 0;i<company.size() ;i++ ) {
			for (int j = 0;j<400 ;j++ ) {
				if (company.get(i).getidOfMiniroomRented(j)!= null){
					if (company.get(i).getidOfMiniroomRented(j).equals(id)) {
						out = i;
					}
					
				}
			}	
		}

		return out;
	}

	/**
	 *
	 * Ok you lisen about this before hehe,
	 * valid if the name and nit exist because we dont should have tow names or
	 * nits equals but the nit or names different (english is hard no?)
	 * @param name, name of the company 
	 * @param nit, like id for the company
	 * @return out, boolean says if the name and nit are valid for register
	 */
	public boolean validCompanyNit(String name, String nit){
		boolean out = true;
		for (int i = 0;i<company.size() ;i++) {
			if (company.get(i).getName().equalsIgnoreCase(name) || 
				company.get(i).getNit().equalsIgnoreCase(nit)){

					out=false;

				if (company.get(i).getNit().equalsIgnoreCase(nit) && 
					company.get(i).getName().equalsIgnoreCase(name)) {

					out=true;//just change it if nit and name are equals and for then save
							 // the id in the array of ids rented
				
				}	
			}
		}

		return out;

	}

	/**
	 *
	 * Delete a company if no have rents
	 */
	public void deleteCompanyIfIsEmpty(){
		boolean noDelete = false;
		for (int i = 0;i<company.size() ;i++) {
			for (int j = 0;j<400 ;j++ ){
				if (company.get(i).getidOfMiniroomRented(j)!= null){
					noDelete = true;
				}
			}
			if (!noDelete) {
				deleteCompany(i+1);
			}
			noDelete=false;
		}
	}

	/**
	 *
	 * If you override the rent, this methot delete the id from the company that rent it
	 * before
	 * @param row, you know what is a... ok no. the row where is the miniroom
	 * @param column, the column where is the miniroom
	 */
	public void clearIdCompany(int row, int column){
		int i = foundCompanyFromId(row, column);
		String id = ""+row+column;
		for (int j = 0;j<400 ;j++ ) {
			if (company.get(i).getidOfMiniroomRented(j)!= null){
				if (company.get(i).getidOfMiniroomRented(j).equals(id)){
					company.get(i).setidOfMiniroomRented( j, null);
				}

			}
				
		}	
		
	}
	/**
	 *
	 * This methot turn no rented all the minirooms from a company and then delete the company
	 * @param position, the company that user selected  
	 */
	public void deleteCompany(int position){
		position-=1;
		String id = "";
		for (int i=0;i<company.get(position).getidOfMiniroomRentedSize();i++) {
			if (company.get(position).getidOfMiniroomRented(i)!=null) {
				id = company.get(position).getidOfMiniroomRented(i);
				for (int t=0; t< runners.length; t++ ) { 
					for (int j = 0; j < runners[0].length; j++) {
						if (id.equals(runners[t][j].getId())) {
							runners[t][j].setRented(false);
							runners[t][j].setOn(false);
						}
					}
				}
			}
			
		}
		 
		company.remove(position);
	}

	/**
	 *
	 * Nothing to say... juts return the size of the arraylist company like you can read
	 * @return company.size(), the size of... well... the company...
	 */
	public int getCompanySize(){
		return company.size();
	}

	/**
	 *
	 * Cancel the rent and delete all the servers
	 * @param row, REALYY??, YOU AGAIN???
	 * @param column, the column where is the miniroom...
	 */
	public void cancelRent(int row, int column){
		runners[row][column].setRented(false);
		runners[row][column].setOn(false);
		int lim = runners[row][column].getRACKSize();

		for (int i = 0;i<lim ;i++ ) {
			runners[row][column].deleteRACK(i);
		}
	}

	/**
	 *
	 * Shows the capacity of the server
	 * @param row, ok im tired, the row where is the miniroom
	 * @param column, the column where is the miniroom
	 * @return out, String all the info in one string What offer 
	 */
	public String showCapacity(int row, int column){
		String out = "";
		for (int i = 0;i<runners[row][column].getRACKSize() ;i++ ) {
			out+="Capacity of disc from the server "+(i+1)+": "+runners[row][column].getrACK(i).getDiscCapacity()+"\n";
			out+="Capacity of ram froms the server "+(i+1)+": "+runners[row][column].getrACK(i).getRamMemory()+"\n";
		}
		return out;
	}

	/**
	 *
	 *This is the menu with all companies 
	 * @return out, String return the String of the menu (I'm so proud :'3)
	 */
	public String showCompanys(){
		String out = "";
		for (int i=0;i<company.size();i++) {
			out+="("+(i+1)+")"+company.get(i);		
		}
		return out;
	}

	/**
	 *
	 * The worst map you ever seen, i know i know but even a genius have a LIMIT
	 * and i dont know how to do it beatiful XD
	 * @return out, String the entiry map
	 */
	public String drawMap(){
		String out="";

		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (runners[i][j].getOn()) {
					out+="("+runners[i][j].getIdForUser()+") ";
					out+="ON    ";
				}else{
					out+="("+runners[i][j].getIdForUser()+") ";
					out+="OFF    ";
				}
			}
			out+="\n\n";
		}

		return out;
	}

	/**
	 *
	 * Turn on all the minirooms with a for
	 */
	public void turnOn(){

		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				runners[i][j].setOn(true);
			}
		}

	}

	/**
	 *
	 * The methot for the command "l"
	 */
	public void offAllFirst(){
		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (Character.getNumericValue(runners[i][j].getId().charAt(0))==0) {
					runners[i][j].setOn(false);
				}else if (Character.getNumericValue(runners[i][j].getId().charAt(1))==0) {
					runners[i][j].setOn(false);
				}
			}
		}
	}

	/**
	 *
	 * (Don't see this UnU)
	 *I Know is bad but
	 *The methot for the command "z"
	 */
	public void offDiagonal(){
		int n1 = 0; int n2 = 0; int n3= 0; int inv;

		for (int i=0; i< runners.length; i++ ) { 
			inv = 10;
			for (int j = 0; j < runners[0].length; j++) {
				if (Character.getNumericValue(runners[i][j].getId().charAt(0))==0) {
					runners[i][j].setOn(false);
				}else if (Character.getNumericValue(runners[i][j].getId().charAt(0))==7) {
					runners[i][j].setOn(false);
				}else if(runners[i][j].getId().length()>2) {
					n1 = Character.getNumericValue(runners[i][j].getIdForUser().charAt(1));
					n2 = Character.getNumericValue(runners[i][j].getIdForUser().charAt(2));
					n3 = (n1*10)+n2;
					if ((runners[0].length-(5*inv))==n3) {
						runners[i][j].setOn(false);
					}
				}else{
					n1 = Character.getNumericValue(runners[i][j].getIdForUser().charAt(1));
					if ((runners[0].length-(5*inv))==n1) {
						runners[i][j].setOn(false);
					}
				}
				if (j%5==0) {
					inv-=1;
				}
			}
		}

	}

	/**
	 *
	 * The methot for the command "h"
	 */
	public void offOdd(){

		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if ((Character.getNumericValue(runners[i][j].getId().charAt(0)))%2==0) {
					runners[i][j].setOn(false);
				}
			}
		}

	}

	/**
	 *
	 * The methot for the command "o"
	 */
	public void offWithWindow(){

		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (runners[i][j].getRoomWithWindow()) {
					runners[i][j].setOn(false);
				}
			}
		}

	}

	/**
	 *
	 * The methot for the command "m"
	 */
	public void offWithColumn(int col){
		col++;
		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (j==col) {
					runners[i][j].setOn(false);
				}
			}
		}

	}

	/**
	 *
	 * The methot for the command "p"
	 */
	public void offWithRow(int row){
		row++;
		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (j==row) {
					runners[i][j].setOn(false);
				}
			}
		}

	}

	/**
	 *
	 * I'm tired, you are tired but you can read this if you want
	 * is the methot that restar all from start, just i use the atributte rented
	 * to know what should be on or off
	 */
	public void returnFromStart(){

		for (int i=0; i< runners.length; i++ ) { 
			for (int j = 0; j < runners[0].length; j++) {
				if (runners[i][j].getRented()) {
					runners[i][j].setOn(true);
				}else{
					runners[i][j].setOn(false);
				}
			}
		}

	}

}