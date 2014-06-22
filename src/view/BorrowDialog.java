package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class BorrowDialog extends JDialog {
	private JComboBox nameField;
	private JSpinner qtyField;

	private JButton borrow;
	private JButton cancel;

	public BorrowDialog() {
		initDialog();
		initComponents();
		addListeners();
	}

	private void initDialog() {
		setSize(400, 350);
		setLayout(null);
		setTitle("LIBro - Borrow a Book");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JLabel dialogTitle = new JLabel("Borrow a Book");
		dialogTitle.setBounds(20, 20, 200, 30);
		dialogTitle.setFont(new Font("Segoe UI", 0, 24));
		add(dialogTitle);

		JSeparator sep = new JSeparator();
		sep.setBounds(20, 55, 354, 2);
		sep.setForeground(new Color(27, 161, 226));
		sep.setBackground(new Color(27, 161, 226));
		add(sep);

		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Segoe UI", 0, 18));
		nameLabel.setBounds(20, 70, 100, 30);
		add(nameLabel);

		nameField = new JComboBox();
		nameField.setBounds(20, 110, 354, 30);
		add(nameField);

		JLabel qtyLabel = new JLabel("Qty: ");
		qtyLabel.setFont(new Font("Segoe UI", 0, 18));
		qtyLabel.setBounds(20, 150, 100, 30);
		add(qtyLabel);

		qtyField = new JSpinner(new SpinnerNumberModel(1, 0, 999, 1));
		qtyField.setBounds(20, 190, 354, 30);
		add(qtyField);

		borrow = new JButton("Borrow");
		borrow.setBounds(80, 250, 100, 30);
		add(getBorrow());

		cancel = new JButton("Cancel");
		cancel.setBounds(220, 250, 100, 30);
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
		nameField.setToolTipText("");
		qtyField.setValue(0);
	}

	public JButton getBorrow() {
		return borrow;
	}

	public JComboBox getNameField() {
		return nameField;
	}
	
	public JSpinner getQtyField() {
		return qtyField;
	}

}
