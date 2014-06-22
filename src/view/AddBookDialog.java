package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class AddBookDialog extends JDialog {	
	private JTextField titleField;
	private JTextField descField;
	private JTextField authorField;
	private JSpinner qtyField;
	
	private JButton add;
	private JButton cancel;

	public AddBookDialog(){
		initDialog();
		initComponents();
		addListeners();
	}
	
	private void initDialog(){
		setSize(400,500);
		setLayout(null);
		setTitle("LIBro - Add a Book");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initComponents(){
		JLabel dialogTitle = new JLabel("Add a Book");
		dialogTitle.setBounds(20,20,130,30);
		dialogTitle.setFont(new Font("Segoe UI", 0, 24));
		add(dialogTitle);
		
		JSeparator sep = new JSeparator();
		sep.setBounds(20,55,354,2);
		sep.setForeground(new Color(27, 161, 226));
		sep.setBackground(new Color(27, 161, 226));
		add(sep);
		
		JLabel titleLabel = new JLabel("Title: ");
		titleLabel.setFont(new Font("Segoe UI", 0, 18));
		titleLabel.setBounds(20,70,100,30);
		add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(20,110,354,30);
		add(titleField);
		
		JLabel descLabel = new JLabel("Description: ");
		descLabel.setFont(new Font("Segoe UI", 0, 18));
		descLabel.setBounds(20,150,100,30);
		add(descLabel);
		
		descField = new JTextField();
		descField.setBounds(20,190,354,30);
		add(descField);
		
		JLabel authorLabel = new JLabel("Author: ");
		authorLabel.setFont(new Font("Segoe UI", 0, 18));
		authorLabel.setBounds(20,230,100,30);
		add(authorLabel);
		
		authorField = new JTextField();
		authorField.setBounds(20,270,354,30);
		add(authorField);
		
		JLabel qtyLabel = new JLabel("Qty: ");
		qtyLabel.setFont(new Font("Segoe UI", 0, 18));
		qtyLabel.setBounds(20,310,100,30);
		add(qtyLabel);
		
		qtyField = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
		qtyField.setBounds(20,350,354,30);
		add(qtyField);
		
		add = new JButton("Add");
		add.setBounds(80,410,100,30);
		add(add);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(220,410,100,30);
		add(cancel);
	}
	
	private void addListeners(){
		cancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				dispose();
			}
		});
	}
	
	private void clear(){
		titleField.setText("");
		descField.setText("");
		authorField.setText("");
		qtyField.setValue(0);
	}
	
	public JButton getAdd() {
		return add;
	}
	
	public JTextField getTitleField() {
		return titleField;
	}
	
	public JTextField getDescField() {
		return descField;
	}
	
	public JTextField getAuthorField() {
		return authorField;
	}
	
	public JSpinner getQtyField() {
		return qtyField;
	}
}
