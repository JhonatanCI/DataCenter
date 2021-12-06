 package model;

public class Company {

	private String nit;
	private String name;
	private String[] idOfMiniroomRented= new String[400];


	public Company(String name, String nit, String idOfMiniroomRented){
		this.nit = nit;
		this.name = name;
		addMiniroom(idOfMiniroomRented);

	}

	public String getNit() {
		return this.nit;
	}

	/**
	 * 
	 * @param nit
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getidOfMiniroomRented(int i){
		return this.idOfMiniroomRented[i];
	}

	public int getidOfMiniroomRentedSize(){
		return this.idOfMiniroomRented.length;
	}
	public void setidOfMiniroomRented(int i, String change){
		idOfMiniroomRented[i] = change;
	}

	public void addMiniroom(String id){
		boolean finish=false;
		for (int i = 0;i<idOfMiniroomRented.length && !finish ;i++ ) {
			if (idOfMiniroomRented[i]==null) {

				idOfMiniroomRented[i]=id;
				finish = true;

			}
		}
	}

	public String toString(){
		String minirooms = "";
		int n1=0;
		int n2=0;
		int n3=0;
		for (int i = 0;i<idOfMiniroomRented.length;i++) {
			if (idOfMiniroomRented[i]!=null) {
				n1=Character.getNumericValue(idOfMiniroomRented[i].charAt(0))+1;
				if (idOfMiniroomRented[i].length()>2) {
					n2=Character.getNumericValue(idOfMiniroomRented[i].charAt(1));
					n3=Character.getNumericValue(idOfMiniroomRented[i].charAt(2));
					n3+=n2*10;
					n3+=1;
					minirooms+=n1+""+n3+" ";
				}else{
					n2=Character.getNumericValue(idOfMiniroomRented[i].charAt(1))+1;
					minirooms+=n1+""+n2+" ";
				}
				n1=0;n2=0;n3=0;
				
			}
		}
		String out = "Name: "+name+"\n"+
		"Nit: "+nit+"\n"+
		"Minirooms rented :"+minirooms+"\n";

		return out;
	}

}