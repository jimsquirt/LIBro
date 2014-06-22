package model;

public class Transaction {
	private int transaction_id;
	private int borrower_id;
	private int book_id;
	
	public Transaction(int aTransaction_id, int aBorrower_id, int aBook_id){
		setTransaction_id(aTransaction_id);
		setBorrower_id(aBorrower_id);
		setBook_id(aBook_id);
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getBorrower_id() {
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	
}
