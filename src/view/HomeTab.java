package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HomeTab extends JPanel{
	private JButton viewBooks;
	private JButton viewBorrowers;
	private GUI gui;
	
	public HomeTab(GUI gui){
		this.gui = gui;
		initPanel();
		initComponents();
		addListeners();
	}
	
	private void initPanel(){
		setLayout(null);
	}
	
	private void initComponents(){
		JTextPane textPane = new JTextPane();
		textPane.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel risus ac justo adipiscing dictum. Donec sed dictum erat, eget molestie tortor. Ut pulvinar malesuada turpis sed tincidunt. Phasellus ut felis lacus. Morbi ut felis commodo ante rhoncus venenatis ac et metus. Ut sit amet euismod eros. Morbi sit amet lorem in justo varius commodo.");
		textPane.setBounds(10,50,747,100);
		textPane.setFont(new Font("Segoe UI", 0, 16));
		textPane.setBackground(null);
		textPane.setEditable(false);
		add(textPane);
		
		viewBooks = new JButton("View Books");
		viewBooks.setIcon(new ImageIcon("images/book_icon.png"));
		viewBooks.setIconTextGap(10);
		viewBooks.setVerticalTextPosition(SwingConstants.CENTER);
		viewBooks.setBounds(100,220,250,100);
		viewBooks.setForeground(Color.WHITE);
		viewBooks.setContentAreaFilled(false);
		viewBooks.setBackground(new Color(27,161,226));
		viewBooks.setFont(new Font("Segoe UI", 1, 20));
		viewBooks.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewBooks.setToolTipText("View list of books");
		viewBooks.setOpaque(true);
		add(viewBooks);
		
		viewBorrowers = new JButton("View Borrowers");
		viewBorrowers.setIcon(new ImageIcon("images/borrower_icon.png"));
		viewBorrowers.setIconTextGap(10);
		viewBorrowers.setVerticalTextPosition(SwingConstants.CENTER);
		viewBorrowers.setBounds(420,220,250,100);
		viewBorrowers.setForeground(Color.WHITE);
		viewBorrowers.setContentAreaFilled(false);
		viewBorrowers.setBackground(new Color(27,161,226));
		viewBorrowers.setFont(new Font("Segoe UI", 1, 20));
		viewBorrowers.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewBorrowers.setToolTipText("View list of borrowers");
		viewBorrowers.setOpaque(true);
		add(viewBorrowers);
	}
	
	private void addListeners(){
		viewBooks.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gui.tabbedPane.setSelectedIndex(1);
			}
		});
		viewBorrowers.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gui.tabbedPane.setSelectedIndex(2);
			}
		});
	}

}
