package model;

public class Book {
	
	private int book_id;
	private String title;
	private String description;
	private String author;
	private int quantity;
	
	public Book(int aBook_id, String aTitle, String aDescription, String anAuthor, int aQuantity) {
		setBook_id(aBook_id);
		setTitle(aTitle);
		setDescription(aDescription);
		setAuthor(anAuthor);
		setQuantity(aQuantity);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
