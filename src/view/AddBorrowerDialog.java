package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class AddBorrowerDialog extends JDialog {	
	private JTextField nameField;
	private JTextField addressField;
	
	private JButton add;
	private JButton cancel;

	public AddBorrowerDialog(){
		initDialog();
		initComponents();
		addListeners();
	}
	
	private void initDialog(){
		setSize(400,340);
		setLayout(null);
		setTitle("LIBro - Add a Borrower");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initComponents(){
		JLabel dialogTitle = new JLabel("Add a Borrower");
		dialogTitle.setBounds(20,20,170,30);
		dialogTitle.setFont(new Font("Segoe UI", 0, 24));
		add(dialogTitle);
		
		JSeparator sep = new JSeparator();
		sep.setBounds(20,55,354,2);
		sep.setForeground(new Color(27, 161, 226));
		sep.setBackground(new Color(27, 161, 226));
		add(sep);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Segoe UI", 0, 18));
		nameLabel.setBounds(20,70,100,30);
		add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(20,110,354,30);
		add(nameField);
		
		JLabel addressLabel = new JLabel("Address: ");
		addressLabel.setFont(new Font("Segoe UI", 0, 18));
		addressLabel.setBounds(20,150,100,30);
		add(addressLabel);
		
		addressField = new JTextField();
		addressField.setBounds(20,190,354,30);
		add(addressField);
		
		add = new JButton("Add");
		add.setBounds(80,250,100,30);
		add(add);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(220,250,100,30);
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
		nameField.setText("");
		addressField.setText("");
	}
	
	public JButton getAdd() {
		return add;
	}
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextField getAddressField() {
		return addressField;
	}
}
