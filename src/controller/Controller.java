package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.Book;
import model.Borrower;
import model.MySQL;
import view.GUI;

public class Controller {
	private MySQL mySQL;
	private GUI gui;
	
	public Controller (MySQL sql, GUI g) {
		mySQL = sql;
		gui = g;
		addListeners();
	}
	
	private void addListeners() {
		gui.getTabbedPane().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int index = gui.getTabbedPane().getSelectedIndex();
				
				if(index == 1)
					searchBook(mySQL.getAllBook());
				else if(index == 2)
					searchBorrower(mySQL.getAllBorrower());
			}
		});
		
		gui.getAbout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.getAboutDialog().setVisible(true);
			}
		});
		
		gui.getBookTab().getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultSet res = null;
				if(gui.getBookTab().getFilter().getSelectedIndex() == 0)
					res = mySQL.getBookByTitle(gui.getBookTab().getSearchField().getText());
				else if(gui.getBookTab().getFilter().getSelectedIndex() == 1)
					res = mySQL.getBookByDescription(gui.getBookTab().getSearchField().getText());
				else if(gui.getBookTab().getFilter().getSelectedIndex() == 2)
					res = mySQL.getBookByAuthor(gui.getBookTab().getSearchField().getText());
				searchBook(res);
			}
		});
		
		gui.getBorrowTab().getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ResultSet res = null;
				if(gui.getBorrowTab().getFilter().getSelectedIndex() == 0)
					res = mySQL.getBorrowerByName(gui.getBorrowTab().getSearchArea().getText());
				else if(gui.getBorrowTab().getFilter().getSelectedIndex() == 1)
					res = mySQL.getBorrowerByAddress(gui.getBorrowTab().getSearchArea().getText());
				searchBorrower(res);
			}
		});
		
		gui.getBookTab().getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.getAddBookDialog().setVisible(true);
			}
		});
		
		gui.getBookTab().getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gui.getBookTab().getBookTable().getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want "
						+ "to delete this book?", "Message", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.NO_OPTION)
					return;
				
				int row = gui.getBookTab().getBookTable().getSelectedRow();
				int book_id = (int) gui.getBookTab().getBookTable().getValueAt(row, 0);	
				
				mySQL.deleteBook(book_id);
				searchBook(mySQL.getAllBook());
			}
		});
		
		gui.getAddBookDialog().getAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = gui.getAddBookDialog().getTitleField().getText();
				String desc = gui.getAddBookDialog().getDescField().getText();
				String author = gui.getAddBookDialog().getAuthorField().getText();
				int quantity = (int) gui.getAddBookDialog().getQtyField().getValue();
				
				mySQL.addNewBook(title, author, desc, quantity);
				gui.getAddBookDialog().dispose();
				
				searchBook(mySQL.getAllBook());
			}
		});
		
		gui.getBookTab().getEditButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gui.getBookTab().getBookTable().getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				
				int row = gui.getBookTab().getBookTable().getSelectedRow();
				String title = gui.getBookTab().getBookTable().getValueAt(row, 1).toString();
				String desc = gui.getBookTab().getBookTable().getValueAt(row, 2).toString();
				String author = gui.getBookTab().getBookTable().getValueAt(row, 3).toString();
				int quantity = (int) gui.getBookTab().getBookTable().getValueAt(row, 4);
				
				gui.getEditBookDialog().loadBookData(title, desc, author, quantity);
				gui.getEditBookDialog().setVisible(true);
			}
		});
		
		gui.getEditBookDialog().getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getBookTab().getBookTable().getSelectedRow();
				
				int book_id = (int) gui.getBookTab().getBookTable().getValueAt(row, 0);
				String title = gui.getEditBookDialog().getTitleField().getText();
				String desc = gui.getEditBookDialog().getAuthorField().getText();
				String author = gui.getEditBookDialog().getDescField().getText();
				int quantity = (int) gui.getEditBookDialog().getQtyField().getValue();
				
				mySQL.updateBook(book_id, title, author, desc, quantity);
				gui.getEditBookDialog().dispose();
				
				searchBook(mySQL.getAllBook());
			}
		});
		
		gui.getBorrowTab().getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.getAddBorrowerDialog().setVisible(true);
			}
		});
		
		gui.getAddBorrowerDialog().getAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = gui.getAddBorrowerDialog().getNameField().getText();
				String address = gui.getAddBorrowerDialog().getAddressField().getText();
				
				mySQL.addNewBorrower(name, address);
				gui.getAddBorrowerDialog().dispose();
				
				searchBorrower(mySQL.getAllBorrower());
			}
		});
		
		gui.getBorrowTab().getEditButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gui.getBorrowTab().getBorrowerTable().getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				
				int row = gui.getBorrowTab().getBorrowerTable().getSelectedRow();
				String name = gui.getBorrowTab().getBorrowerTable().getValueAt(row, 1).toString();
				String address = gui.getBorrowTab().getBorrowerTable().getValueAt(row, 2).toString();
				
				gui.getEditBorrowerDialog().loadBorrowerData(name, address);
				gui.getEditBorrowerDialog().setVisible(true);
			}
		});
		
		gui.getEditBorrowerDialog().getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getBorrowTab().getBorrowerTable().getSelectedRow();
				
				int borrower_id = (int) gui.getBorrowTab().getBorrowerTable().getValueAt(row, 0);
				String name = gui.getEditBorrowerDialog().getNameField().getText();
				String address = gui.getEditBorrowerDialog().getAddressField().getText();
				
				mySQL.updateBorrower(borrower_id, name, address);
				gui.getEditBorrowerDialog().dispose();
				
				searchBorrower(mySQL.getAllBorrower());
			}
		});
		
		gui.getBorrowTab().getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gui.getBorrowTab().getBorrowerTable().getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want "
						+ "to delete this borrower?", "Message", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.NO_OPTION)
					return;
				
				int row = gui.getBorrowTab().getBorrowerTable().getSelectedRow();
				int borrower_id = (int) gui.getBorrowTab().getBorrowerTable().getValueAt(row, 0);	
				
				mySQL.deleteBorrower(borrower_id);
				searchBorrower(mySQL.getAllBorrower());
			}
		});
		
		gui.getBookTab().getBorrowButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getBookTab().getBookTable().getSelectedRow();
				
				if(row < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error,", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				ResultSet res = mySQL.getAllBorrower();
				try {
					gui.getBorrowDialog().getNameField().removeAllItems();
					while(res.next())
						gui.getBorrowDialog().getNameField().addItem(res.getString("name"));
					gui.getBorrowDialog().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
				
		gui.getBorrowDialog().getBorrow().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getBookTab().getBookTable().getSelectedRow();
				
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
						+ "complete this transaction?", "Message", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.NO_OPTION)
					return;
				
				String name = gui.getBorrowDialog().getNameField().getSelectedItem().toString();
				int borrower_id = mySQL.getBorrowerIdByName(name);
				int book_id = (int) gui.getBookTab().getBookTable().getValueAt(row, 0);
				int quantity = (int) gui.getBookTab().getBookTable().getValueAt(row, 4);
				int input_quantity = (int) gui.getBorrowDialog().getQtyField().getValue();
				
				if(input_quantity > quantity) {
					JOptionPane.showMessageDialog(null, "The entered quantity is greater than the "
							+ "current number of stocks.", "Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				mySQL.addNewTransaction(book_id, borrower_id, input_quantity);		
				mySQL.updateBookQuantity(book_id, quantity - input_quantity);
				gui.getBorrowDialog().dispose();
				
				searchBook(mySQL.getAllBook());
			}
		});

		gui.getBorrowTab().getReturnButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getBorrowTab().getBorrowerTable().getSelectedRow();
				
				if(row < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}	
								
				String name = gui.getBorrowTab().getBorrowerTable().getValueAt(row, 1).toString();
				int borrower_id = mySQL.getBorrowerIdByName(name);
				searchBorrowerTransaction(mySQL.getBorrowerTransaction(borrower_id));
				
				gui.getReturnDialog().setVisible(true);
			}
		});

		gui.getReturnDialog().getReturn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getReturnDialog().getTransactionTable().getSelectedRow();
				
				if(row < 0) {
					JOptionPane.showMessageDialog(null, "Please select a row.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}	
				
				int transaction_id = (int) gui.getReturnDialog().getTransactionTable().getValueAt(row, 0);
				String title = gui.getReturnDialog().getTransactionTable().getValueAt(row, 1).toString();
				int cur_quantity = (int) gui.getReturnDialog().getTransactionTable().getValueAt(row, 2);
				int quantity = (int) gui.getReturnDialog().getQuantityField().getValue();
				
				if(quantity > cur_quantity) {
					JOptionPane.showMessageDialog(null, "Entered quantity is greater than current quantity.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}	
				
				mySQL.returnBookTransaction(transaction_id, title, quantity);
				
				int borrowerRow = gui.getBorrowTab().getBorrowerTable().getSelectedRow();
				String name = gui.getBorrowTab().getBorrowerTable().getValueAt(borrowerRow, 1).toString();
				int borrower_id = mySQL.getBorrowerIdByName(name);
				searchBorrowerTransaction(mySQL.getBorrowerTransaction(borrower_id));
			}
		});

	}
	
	private void searchBook(ResultSet res) {
		((DefaultTableModel) gui.getBookTab().getBookTable().getModel())
				.getDataVector().removeAllElements();
		((DefaultTableModel) gui.getBookTab().getBookTable().getModel())
				.fireTableDataChanged();
		
		try {
			while (res.next()) {
				Book book = new Book(res.getInt("book_id"),
						res.getString("title"), res.getString("description"),
						res.getString("author"), res.getInt("quantity"));
				((DefaultTableModel) gui.getBookTab().getBookTable().getModel())
						.addRow(new Object[] { book.getBook_id(),
								book.getTitle(), book.getAuthor(),
								book.getDescription(), book.getQuantity() });

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void searchBorrower(ResultSet res) {
		((DefaultTableModel) gui.getBorrowTab().getBorrowerTable().getModel())
				.getDataVector().removeAllElements();
		((DefaultTableModel) gui.getBorrowTab().getBorrowerTable().getModel())
				.fireTableDataChanged();
		
		try {
			while (res.next()) {
				Borrower borrower = new Borrower(res.getInt("borrower_id"),
						res.getString("name"), res.getString("address"));
				((DefaultTableModel) gui.getBorrowTab().getBorrowerTable().getModel())
						.addRow(new Object[] { borrower.getBorrower_id(), 
								borrower.getName(), borrower.getAddress() });

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void searchBorrowerTransaction(ResultSet res) {
		((DefaultTableModel) gui.getReturnDialog().getTransactionTable()
				.getModel()).getDataVector().removeAllElements();
		((DefaultTableModel) gui.getReturnDialog().getTransactionTable()
				.getModel()).fireTableDataChanged();
		
		try {
			while (res.next())			
				((DefaultTableModel) gui.getReturnDialog().getTransactionTable().getModel())
						.addRow(new Object[] { res.getInt("transID"), res.getString("Title"), 
						res.getInt("Quantity") });			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
