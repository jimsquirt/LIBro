package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class ReturnDialog extends JDialog {
	private String[] columnNames = { "ID", "Book Title", "Quantity" };
	
	private JScrollPane scrollPane;
	private JTable transactionTable;	
	private JSpinner quantityField;

	private JButton returnBook;
	private JButton cancel;

	public ReturnDialog() {
		initDialog();
		initComponents();
		addListeners();
	}

	private void initDialog() {
		setSize(400, 350);
		setLayout(null);
		setTitle("LIBro - Return a Book");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		transactionTable = new JTable(new DefaultTableModel(new Object[][] {},
				columnNames)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		transactionTable.setRowSorter(new TableRowSorter(transactionTable.getModel()));
		scrollPane = new JScrollPane(transactionTable);
		scrollPane.setBounds(15, 14, 369, 250);
		add(scrollPane);
		
		transactionTable.setRowHeight(25);
		transactionTable.setShowGrid(false);
		transactionTable.setFillsViewportHeight(true);
		transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		transactionTable.getColumnModel().getColumn(0).setMinWidth(60);
		transactionTable.getColumnModel().getColumn(0).setMaxWidth(60);
		
		transactionTable.getColumnModel().getColumn(2).setMinWidth(70);
		transactionTable.getColumnModel().getColumn(2).setMaxWidth(70);
		
		TableCellRenderer renderer = new EvenOddRenderer();
		transactionTable.setDefaultRenderer(Object.class, renderer);
		
		TableCellRenderer rendererFromHeader = transactionTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel qtyLabel = new JLabel("Quantity:");
		qtyLabel.setBounds(15, 285, 50, 15);
		add(qtyLabel);

		quantityField = new JSpinner(new SpinnerNumberModel(1, 0, 999, 1));
		quantityField.setBounds(70, 279, 97, 25);
		add(quantityField);

		returnBook = new JButton("Return");
		returnBook.setBounds(176, 279, 100, 25);
		add(returnBook);

		cancel = new JButton("Cancel");
		cancel.setBounds(285, 279, 100, 25);
		add(cancel);
	}

	private void addListeners() {
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				dispose();
			}
		});
	}

	private void clear() {
		quantityField.setValue(0);
	}

	public JButton getReturn() {
		return returnBook;
	}
	
	public JSpinner getQuantityField() {
		return quantityField;
	}

	public JTable getTransactionTable() {
		return transactionTable;
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
