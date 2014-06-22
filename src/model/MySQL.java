package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MySQL {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/libro";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private CallableStatement cs = null;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	public MySQL() {
		try {
			Class.forName(DB_DRIVER);
			connect = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			statement = connect.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getAllBook() {
		try {
			cs = this.connect.prepareCall("{call show_all_book()}");
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet getBookByTitle(String title) {
		try {
			cs = this.connect.prepareCall("{call show_book_by_title(?)}");
			cs.setString(1, title);
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet getBookByAuthor(String author) {
		try {
			cs = this.connect.prepareCall("{call show_book_by_author(?)}");
			cs.setString(1, author);
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet getBookByDescription(String description) {
		try {
			cs = this.connect.prepareCall("{call show_book_by_description(?)}");
			cs.setString(1, description);
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public void addNewBook(String title, String author, String desc,
			int quantity) {
		String insertString = "INSERT INTO Book(title, author, description, quantity) "
				+ " VALUES(?, ?, ?, ?) ";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sucessfully added a new book.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBook(int book_id, String title, String author,
			String desc, int quantity) {
		String insertString = "UPDATE Book SET"
				+ " title = ?, author = ?, description = ?, quantity = ? "
				+ " WHERE Book_id = ?;";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, desc);
			preparedStatement.setInt(4, quantity);
			preparedStatement.setInt(5, book_id);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Sucessfully updated book information.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBookQuantity(int book_id, int quantity) {
		String insertString = "UPDATE Book SET"
				+ " quantity = ? "
				+ " WHERE Book_id = ?;";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, book_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(int book_id) {
		String insertString = "DELETE FROM Book " + " WHERE Book_id = ?;";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setInt(1, book_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/********************************************* BORROWER QUERIES ***************************************/

	public ResultSet getAllBorrower() {
		try {
			cs = this.connect.prepareCall("{call show_all_borrower()}");
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet getBorrowerByName(String name) {
		try {
			cs = this.connect.prepareCall("{call show_borrower_by_name(?)}");
			cs.setString(1, name);
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}
	
	public int getBorrowerIdByName(String name) {
		int value = -1;
		try {
			cs = this.connect.prepareCall("{call show_borrower_by_id(?)}");
			cs.setString(1, name);
			resultSet = cs.executeQuery();
			while(resultSet.next())
				value = resultSet.getInt("borrower_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return value;
	}

	public ResultSet getBorrowerByAddress(String address) {
		try {
			cs = this.connect.prepareCall("{call show_borrower_by_address(?)}");
			cs.setString(1, address);
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public void addNewBorrower(String name, String address) {
		String insertString = "INSERT INTO Borrower(name, address) "
				+ " VALUES(?,?) ";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Sucessfully added a new borrower.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBorrower(int borrower_id, String name, String address) {
		String insertString = "UPDATE Borrower SET" + " name = ?, address = ? "
				+ " WHERE Borrower_id = ?;";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setInt(3, borrower_id);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Sucessfully updated borrower information.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBorrower(int borrower_id) {
		String insertString = "DELETE FROM Borrower "
				+ " WHERE Borrower_id = ?;";

		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setInt(1, borrower_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/********************************************* TRANSACTION QUERIES ***************************************/

	public ResultSet getAllTransactions() {
		try {
			cs = this.connect.prepareCall("{call show_all_transaction(?)}");
			resultSet = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}
	
	public ResultSet getBorrowerTransaction(int borrower_id) {
		StringBuilder sb = new StringBuilder(50);
		sb.append("SELECT t.book_id as 'bookID', ");
		sb.append("t.transaction_id as 'transID', ");
		sb.append("t.borrower_id as 'borrower_id', ");
		sb.append("b.title as 'Title', ");
		sb.append("t.quantity as 'Quantity' ");
		sb.append("FROM book b, borrower bo, transaction t ");
		sb.append("WHERE b.book_id = t.book_id AND ");
		sb.append("bo.borrower_id = t.borrower_id AND ");
		sb.append("t.borrower_id = ?");
		
		try {
			preparedStatement = connect.prepareStatement(sb.toString());
			preparedStatement.setInt(1, borrower_id);
			resultSet = preparedStatement.executeQuery();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;	    
	}
	
	public void returnBookTransaction(int transaction_id, String title, int quantity) {
		StringBuilder sb = new StringBuilder(50);
		sb.append("UPDATE Transaction ");
		sb.append("SET Quantity = Quantity - ? ");
		sb.append("WHERE transaction_id = ?");
		
		try {
			preparedStatement = connect.prepareStatement(sb.toString());
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, transaction_id);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sb.setLength(0);
		sb.trimToSize();
		
		sb.append("UPDATE Book ");
		sb.append("SET Quantity = Quantity + ? ");
		sb.append("WHERE Title = ?");
				
		try {
			preparedStatement = connect.prepareStatement(sb.toString());
			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, title);	
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sb.setLength(0);
		sb.trimToSize();
		
		JOptionPane.showMessageDialog(null, "Book successfully returned.");
	}

	public void addNewTransaction(int book_id, int borrower_id, int quantity) {
		String insertString = "INSERT INTO Transaction (Book_id, Borrower_id, Quantity) "
				+ " VALUES (?, ?, ?);";
		try {
			preparedStatement = connect.prepareStatement(insertString);
			preparedStatement.setInt(1, book_id);
			preparedStatement.setInt(2, borrower_id);
			preparedStatement.setInt(3, quantity);
			preparedStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sucessfully completed transaction.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			statement.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
