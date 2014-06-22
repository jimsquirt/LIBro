package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class BorrowTab extends JPanel {
	// table
	private JTable borrowerTable;
	private JScrollPane borrowerTableScrollPane;

	// buttons
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton borrowButton;
	private JButton searchButton;

	// filter components
	private JTextArea searchArea;
	private JComboBox filter;

	Object columnNames[] = { "Borrower ID", "Name", "Address", };

	public BorrowTab() {
		setLayout(null);

		// table
		borrowerTable = new JTable(new DefaultTableModel(new Object[][] {},
				columnNames)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		borrowerTable.setRowSorter(new TableRowSorter(borrowerTable.getModel()));

		borrowerTable.setRowHeight(25);
		borrowerTable.setShowGrid(false);
		borrowerTable.setFillsViewportHeight(true);
		borrowerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		TableCellRenderer renderer = new EvenOddRenderer();
		borrowerTable.setDefaultRenderer(Object.class, renderer);
		
		TableCellRenderer rendererFromHeader = borrowerTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);

		borrowerTableScrollPane = new JScrollPane(borrowerTable);
		borrowerTableScrollPane.setBounds(15, 50, 740, 250);

		borrowerTable.getTableHeader().setReorderingAllowed(false);

		borrowerTable.getColumnModel().getColumn(0).setMinWidth(100);
		borrowerTable.getColumnModel().getColumn(0).setMaxWidth(100);

		borrowerTable.getColumnModel().getColumn(1).setMinWidth(250);
		borrowerTable.getColumnModel().getColumn(1).setMaxWidth(250);

		borrowerTable.getColumnModel().getColumn(2).setMinWidth(400);
		borrowerTable.getColumnModel().getColumn(2).setMaxWidth(400);

		// buttons
		addButton = new JButton("Add a Borrower");
		addButton.setBounds(115, 315, 120, 35);

		editButton = new JButton("Update Info");
		editButton.setBounds(250, 315, 120, 35);

		deleteButton = new JButton("Delete a Borrower");
		deleteButton.setBounds(385, 315, 120, 35);

		borrowButton = new JButton("Return Book");
		borrowButton.setBounds(520, 315, 120, 35);

		searchButton = new JButton("Search");
		searchButton.setBounds(645, 15, 110, 25);

		// text area
		searchArea = new JTextArea("", 1, 1);
		searchArea.setFont(new Font("Dialog", Font.PLAIN, 12));
		searchArea.setBounds(15, 15, 450, 25);
		searchArea.setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY, 1));

		String[] filterTypes = { "Name", "Address" };
		filter = new JComboBox(filterTypes);
		filter.setBounds(480, 15, 150, 25);
		filter.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		// add to pane
		this.add(borrowerTableScrollPane);
		this.add(addButton);
		this.add(editButton);
		this.add(deleteButton);
		this.add(borrowButton);
		this.add(searchButton);
		this.add(searchArea);
		this.add(filter);
	}

	public JTable getBorrowerTable() {
		return borrowerTable;
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

	public JButton getReturnButton() {
		return borrowButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTextArea getSearchArea() {
		return searchArea;
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
