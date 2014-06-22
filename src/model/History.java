package model;

public class History {
	private int transaction_id;
	private String date_returned;
	private String date_borrowed;
	
	public History(int aTransaction_id, String aDate_returned, String aDate_borrowed){
		setTransaction_id(aTransaction_id);
		setDate_returned(aDate_returned);
		setDate_borrowed(aDate_borrowed);			
	}
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getDate_returned() {
		return date_returned;
	}
	public void setDate_returned(String date_returned) {
		this.date_returned = date_returned;
	}
	public String getDate_borrowed() {
		return date_borrowed;
	}
	public void setDate_borrowed(String date_borrowed) {
		this.date_borrowed = date_borrowed;
	}
	
}
