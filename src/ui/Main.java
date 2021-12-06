package ui;

import model.DataCenter;
import java.util.Scanner;
import model.Spinner;
import model.Trademark;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main of proyect DataCenter. 
 * @author Jhonatan Castaño
 * @version 1.0
 * @since November 2021
 */
public class Main {

	/**
	 * dt is the conection with DataCenter
	 */
	DataCenter dt;
	/**
	 * sc is the conection with Scanner
	 */
	Scanner sc;

	/**
	 * Constructor of Main.  
	 * Start the base value for all minirroms and inicizialice the objects (srr for my english)
	 */ 
	public Main(){

		 sc = new Scanner(System.in);
		 System.out.print("\nSelect a value base for all minirooms\n\n");
		 double value = sc.nextDouble();
		 dt = new DataCenter(value);

	}

	/**
	 * "The main"
	 * @param String
	 * Inicizialice the object for spinner who the great animation from start 7w7
	 * Then Start the while for the menu
	 */
	public static void main(String[] args) throws InterruptedException{

		Spinner spin = new Spinner();
		Main pc = new Main();
		System.out.print("\n\n");
		for (int i = 0; i < 20; i++) {
            spin.animate();
            //simulate a piece of task
            Thread.sleep(300);
        }
        System.out.print("\n\n");

		


		int option;
		System.out.println(" WELCOME TO YOUR DATACENTER \n");
		do {
		    option = pc.showMenu();
		    pc.switchAns(option);
		}while (option !=0);		
	}

	/**
	 * 
	 * @return op, int
	 * Just show the menu and return the answer to then do the methot
	 */
	public int showMenu(){
		
		System.out.println("(1) Show info minirooms\n"+
			"(2) Rent a miniroom\n"+
			"(3) Show map of minirooms\n"+
			"(4) Cancel rent of miniroom\n"+
			"(5) Cancel all rents of minirooms of an company\n"+
			"(6) Turn on all minirooms\n"+
			"(7) Simulate turn off with a character command\n"+
			"(8) Finish the simulation\n"+
			"(0) To exit");
		int op = sc.nextInt();

		return op;
	}

	/**
	 * 
	 * @param int, option
	 * read the answer and call a methot for the user
	 */
	public void switchAns(int option){
		switch(option){
			case 0:
				System.out.println("Finish...");
				break;
			case 1:
				showInfo();
				break;
			case 2:
				verifyRent();
				break;
			case 3:
				showMap();
				break;
			case 4:
				cancelRent();
				break;
			case 5:
				cancelRentOfACompany();
				break;
			case 6:
				turnOnAllMinirooms();
				break;
			case 7:
				simulateTurnOff();
				break;
			case 8:
				returnFromStart();
				break;
		}
	}
	/**
	 * 
	 * Print the info from all minirroms
	 */
	public void showInfo(){
		System.out.println("\n\n");
		System.out.println(dt.showMinirooms());
	}

	/**
	 * 
	 * First to call the methot that real rent the miniroom, this
	 * valid all the information before the rent, ask if the rent is for
	 * a company or for a proyect
	 *
	 *Ask too all the info from the company, the id of the minirrom(In my proyect the id
	 * is two number. First : the row, and the second : the column). Valid if the id is correct
	 * or if is already rented, if it is ask if you want to override(save too the
	 * company info)
	 */
	public void verifyRent(){

		System.out.println("Who is going to rent the miniroom?\n"+
			"A company (1)\n"+
			"Proyect of investigation (2)");
		int option = sc.nextInt();
		while(option!=1 && option!=2){
				System.out.println("Not valid answer");
				option = sc.nextInt();
		}
			String name;
			String nit;
		if (option == 1) {
			 sc.nextLine();
			System.out.println("Write the name of the company");
			 name = sc.nextLine();

			System.out.println("Write the nit of the company");
			 nit = sc.next();
			sc.nextLine();
			while(!dt.validCompanyNit( name,  nit)){
				System.out.println("This nit or name is register already");

				System.out.println("Write the name of the company again");
			 	name = sc.nextLine();

			 	System.out.println("Write the nit of the company");
			 	nit = sc.next();
			}
		}else{
			name = "ICESI";
			System.out.println("Write the id of the Proyect");
			nit = sc.nextLine();
			sc.nextLine();
		}

		System.out.println("Write the position of the miniroom");
		System.out.println("Pls write the room");
		int row = sc.nextInt();
		System.out.println("Pls write the miniroom");
		int column = sc.nextInt();
		row--;
		column--;
		while (!dt.verifyId(row,column)) {
			System.out.println("The id is incorrect");
			row = sc.nextInt();
			column = sc.nextInt();
			row--;
			column--;
		}
		if (!dt.isRented(row,column)) {
			rent( row,  column, name);
			dt.CompanyRent(name,nit,row,column);
		}else{
			System.out.println("This miniroom is already rented, you want to override? (y/n)\n");
			String ans = sc.next();

			while(!ans.equals("y") && !ans.equals("n")){
				System.out.println("Not valid answer");
				ans = sc.next();
			}
			if (ans.equals("y")) {
				rent( row,  column, name);
				dt.clearIdCompany(row,column);
				dt.CompanyRent(name,nit,row,column);
			}
		}
		

	}

	/**
	 * 
	 * Ask all the information for the RACK and the servers, i use a new methot to get the
	 * date, i show user if he will pay more for use less than 4 servers in his minirrom
	 * and then call the methots who will save all the info of the rented minirrom
	 */
	public void rent(int row, int column, String name){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");/* I learn to use it here 
		https://www.delftstack.com*/ 

		System.out.println("How many servers you want in your miniroom");
		int servers = sc.nextInt();
		boolean conti = true;
		if (servers<4) {
			conti = false;
		}
		while (!conti && servers<4) {
			System.out.println("If you rent with less of 4 servers you will pay 15% more\n"+
				"Are you sure? y/n");
			String ans = sc.next();
			while(!ans.equals("y") && !ans.equals("n")){
				System.out.println("Not valid answer");
				ans = sc.next();
			}
			if (ans.equals("y")) {
				conti = true;
			}else{
				System.out.println("How many servers you want in your miniroom");
				servers = sc.nextInt();
			}
		}
		sc.nextLine();
		dt.serversQuantity( row, column,  servers);

		for (int i=0;i<servers ;i++ ) {


			System.out.println("Write the server "+(i+1)+" information:");
			System.out.println("Write the cache memory");
			double cacheMemory = sc.nextDouble();
			sc.nextLine();
			System.out.println("Number of processer");
			int processerNumber = sc.nextInt();
			sc.nextLine();
			System.out.println("Trademark 1: INTEL, 2: AMD");
			int trademark = sc.nextInt();
			sc.nextLine();
			while(trademark<1 || trademark>2){
				System.out.println("Not valid answer");
				trademark = sc.nextInt();
			}
			Trademark tradeMark = Trademark.INTEL;
			switch(trademark){
				case 1:
					 tradeMark = Trademark.INTEL;
					break;
				case 2:
					 tradeMark = Trademark.AMD;
					break;
			}
			System.out.println("Ram memory");
			int ram = sc.nextInt();
			sc.nextLine();
			System.out.println("Number of discs");
			int discs = sc.nextInt();
			sc.nextLine();
			System.out.println("Discs capacity (TB)");
			double capacityDiscs = sc.nextDouble();

			dt.serversInfo( row,  column,  cacheMemory,  processerNumber, tradeMark,  ram, discs,
		 	capacityDiscs);
		}
		String date = dtf.format(LocalDateTime.now());
		double value = dt.rent(row, column, date);

		System.out.println("\nThe rent of the miniroom was succesful!\n");
		System.out.println("The minirrom "+(row+1)+(column+1)+" was rented in "+date+
			" with "+servers+" servers and for a value of \n"+value+ " per month"+" to "+name+"\n\n");
	}

	/**
	 * 
	 *This methot just cancel a rent!
	 *Ask the id of the minirrom, valid it, and say you if the minirrom is rented already
	 *(i know i know all the methots do the same, pls dont say me i need a methot for that)
	 */
	public void cancelRent(){
		System.out.println("Write the position of the miniroom");
		System.out.println("Pls write the room");
		int row = sc.nextInt();
		System.out.println("Pls write the miniroom");
		int column = sc.nextInt();
		row--;
		column--;
		while (!dt.verifyId(row,column)) {
			System.out.println("The id is incorrect");
			row = sc.nextInt();
			column = sc.nextInt();
			row--;
			column--;
		}
		if (dt.isRented(row,column)) {
			System.out.println(dt.showCapacity(row, column));
			dt.cancelRent( row,  column);
			dt.clearIdCompany(row , column);
			dt.deleteCompanyIfIsEmpty();
		}else{
			System.out.println("This miniroom is not rented, you want to exit? (y/n)\n");
			String ans = sc.next();

			while(!ans.equals("y") && !ans.equals("n")){
				System.out.println("Not valid answer");
				ans = sc.next();
			}
			if (ans.equals("n")) {
				cancelRent();
			}
		}
	}

	/**
	 * 
	 *This methot show you a menu with all the companies you register before
	 *and shows you what minirroms are rented for that company (i said i like program?)
	 *then you select one and just delete it with all his info and set rented minirroms
	 *to not rented
	 */
	public void cancelRentOfACompany(){
		if (dt.getCompanySize()>0) {
			System.out.println("Select the company");
			System.out.println(dt.showCompanys());
			int company = sc.nextInt();
		
			while(company>dt.getCompanySize() || company<=0){
			System.out.println("Not valid answer");
			company = sc.nextInt();
			}
			dt.deleteCompany(company);
		}else{
			System.out.println("\nNo rents yet\n");
		}
		
	}
	/**
	 * 
	 *show the map ;3
	 * i would like to do it more beatiful but you know the console
	 * makes it so hard
	 */
	public void showMap(){
		System.out.println(dt.drawMap());
	}

	/**
	 * 
	 *Turn on all minirooms for simulate the turn off commands
	 */
	public void turnOnAllMinirooms(){
		dt.turnOn();
		System.out.println("\nDone!\n");
	}

	/**
	 * 
	 *Evaluate all the options for the differents commands for
	 *turn off the minirooms(sorry for do the diagonal command so weard)
	 *
	 *weird******* :'D
	 */
	public void simulateTurnOff(){
		System.out.println("\nPls enter the command");
		String command = sc.next();
		if (command.equalsIgnoreCase("l")) {
			dt.offAllFirst();
			System.out.println("\nDone!\n");
			showMap();
		}else if (command.equalsIgnoreCase("z")) {
			dt.offDiagonal();
			System.out.println("\nDone!\n");
			showMap();
		}else if (command.equalsIgnoreCase("h")){
			dt.offOdd();
			System.out.println("\nDone!\n");
			showMap();
		}else if (command.equalsIgnoreCase("o")) {
			dt.offWithWindow();
			System.out.println("\nDone!\n");
			showMap();
		}else if (command.equalsIgnoreCase("m")) {
			System.out.println("Write the column");
			int column = sc.nextInt();
			sc.nextLine();
			dt.offWithColumn( column);
			//System.out.println("\nI like program but I couldn't do this...\n"+" Maybe in a future!"); ok no...
			System.out.println("\nDone!\n");
			showMap();
		}else if (command.equalsIgnoreCase("p")) {
			System.out.println("Write the row");
			int row = sc.nextInt();
			sc.nextLine();
			dt.offWithRow( row);
			System.out.println("\nDone!\n");
			showMap();
		}
		else{
			System.out.println("Not valid command");
		}

		
	}

	/**
	 * 
	 *If we "simulate something" we obviosly want to return to the start values
	 *the rubric dont say it but anyways i do the methot (just because i like program... oh i already say it XD)
	 */
	public void returnFromStart(){
		dt.returnFromStart();
		System.out.println("\nDone!\n");
		showMap();
	}
}