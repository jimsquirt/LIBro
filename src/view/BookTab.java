package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class BookTab extends JPanel {
	// table
	private JTable bookTable;
	private JScrollPane bookTableScrollPane;

	// buttons
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;

	private JButton borrowButton;
	private JButton searchButton;

	// filter components
	private JTextField searchField;
	private JComboBox filter;

	Object columnNames[] = { "Book ID", "Title", "Author", "Description",
			"Quantity" };

	public BookTab() {
		setLayout(null);

		// table
		bookTable = new JTable(new DefaultTableModel(new Object[][] {},
				columnNames)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		bookTable.setRowSorter(new TableRowSorter(bookTable.getModel()));

		bookTable.setRowHeight(25);
		bookTable.setShowGrid(false);
		bookTable.setFillsViewportHeight(true);
		bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableCellRenderer renderer = new EvenOddRenderer();
		bookTable.setDefaultRenderer(Object.class, renderer);
		
		TableCellRenderer rendererFromHeader = bookTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);

		bookTableScrollPane = new JScrollPane(bookTable);
		bookTableScrollPane.setBounds(15, 50, 740, 250);

		bookTable.getTableHeader().setReorderingAllowed(false);

		bookTable.getColumnModel().getColumn(0).setMinWidth(80);
		bookTable.getColumnModel().getColumn(0).setMaxWidth(80);

		bookTable.getColumnModel().getColumn(1).setMinWidth(250);
		bookTable.getColumnModel().getColumn(1).setMaxWidth(250);

		bookTable.getColumnModel().getColumn(4).setMinWidth(80);
		bookTable.getColumnModel().getColumn(4).setMaxWidth(80);

		// buttons
		addButton = new JButton("Add a Book");
		addButton.setBounds(115, 315, 120, 35);

		editButton = new JButton("Update Book Info");
		editButton.setBounds(250, 315, 120, 35);

		deleteButton = new JButton("Delete a Book");
		deleteButton.setBounds(385, 315, 120, 35);

		borrowButton = new JButton("Borrow a Book");
		borrowButton.setBounds(520, 315, 120, 35);

		searchButton = new JButton("Search");
		searchButton.setBounds(645, 15, 110, 25);

		// text area
		searchField = new JTextField();
		searchField.setFont(new Font("Dialog", Font.PLAIN, 12));
		searchField.setBounds(15, 15, 450, 25);
		searchField.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY, 1));

		String[] filterTypes = { "Title", "Description", "Author" };
		filter = new JComboBox(filterTypes);
		filter.setBounds(480, 15, 150, 25);
		filter.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		// add to pane
		this.add(bookTableScrollPane);
		this.add(addButton);
		this.add(editButton);
		this.add(deleteButton);
		this.add(borrowButton);
		this.add(searchButton);
		this.add(searchField);
		this.add(filter);
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getBorrowButton() {
		return borrowButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JComboBox getFilter() {
		return filter;
	}

	private class EvenOddRenderer implements TableCellRenderer {
		public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			DEFAULT_RENDERER.setHorizontalAlignment(JLabel.CENTER);
			Component renderer = DEFAULT_RENDERER
					.getTableCellRendererComponent(table, value, isSelected,
							hasFocus, row, column);
			((JLabel) renderer).setOpaque(true);
			Color foreground, background, select = new Color(159, 187, 198), blue = new Color(
					138, 224, 204);

			foreground = Color.black;
			if (isSelected) {
				background = select;
			} else {
				if (row % 2 == 0) {
					background = Color.white;
				} else {
					background = blue;
				}
			}

			renderer.setForeground(foreground);
			renderer.setBackground(background);
			return renderer;
		}
	}

}
