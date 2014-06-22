package model;

public class Borrower {
	private int borrower_id;
	private String name;
	private String address;
	
	public Borrower(int aBorrower_id, String aName, String anAddress){
		setBorrower_id(aBorrower_id);
		setName(aName);
		setAddress(anAddress);
	}

	public int getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
