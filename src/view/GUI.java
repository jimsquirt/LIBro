package view;

import java.awt.Cursor;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import model.Quotes;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JButton about;

	private HomeTab homeTab;
	private BookTab bookTab = new BookTab();
	private BorrowTab borrowTab = new BorrowTab();

	private AboutDialog aboutDialog;

	private JTextPane quote;
	private AddBookDialog addBookDialog;
	private AddBorrowerDialog addBorrowerDialog;

	private EditBookDialog editBookDialog;
	private EditBorrowerDialog editBorrowerDialog;

	private BorrowDialog borrowDialog;
	private ReturnDialog returnDialog;

	public JTabbedPane tabbedPane;

	public GUI() {
		initFrame();
		initComponents();
		randomizeQuote();
	}

	private void initFrame() {
		setLayout(null);
		setSize(800, 600); // x = width - 6, y = height - 32
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("LIBro - A Library Management System");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void initComponents() {
		homeTab = new HomeTab(this);
		initHeader();
		initMid();
		initFooter();

		aboutDialog = new AboutDialog();
		aboutDialog.setModal(true);
		aboutDialog.setLocationRelativeTo(null);

		addBookDialog = new AddBookDialog();
		addBookDialog.setModal(true);
		addBookDialog.setLocationRelativeTo(null);

		addBorrowerDialog = new AddBorrowerDialog();
		addBorrowerDialog.setModal(true);
		addBorrowerDialog.setLocationRelativeTo(null);

		editBookDialog = new EditBookDialog();
		editBookDialog.setModal(true);
		editBookDialog.setLocationRelativeTo(null);

		editBorrowerDialog = new EditBorrowerDialog();
		editBorrowerDialog.setModal(true);
		editBorrowerDialog.setLocationRelativeTo(null);

		// start pat code
		setBorrowDialog(new BorrowDialog());
		getBorrowDialog().setModal(true);
		getBorrowDialog().setLocationRelativeTo(null);
		// end pat code...

		// start pat code
		setReturnDialog(new ReturnDialog());
		getReturnDialog().setModal(true);
		getReturnDialog().setLocationRelativeTo(null);
		// end pat code...
	}

	private void initHeader() {
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(10, 10, 787, 150);
		header.setOpaque(false);
		add(header);

		JLabel title = new JLabel(new ImageIcon("images/logo_blue_about.png"));
		title.setBounds(0, 0, 304, 100);
		header.add(title);

		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrib, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontFamily(attrib, "Segoe UI");
		StyleConstants.setFontSize(attrib, 26);
		StyleConstants.setItalic(attrib, true);
		StyleConstants.setRightIndent(attrib, 15);

		quote = new JTextPane();
		quote.setBounds(370, 10, 413, 80);
		quote.setEditable(false);
		quote.setBackground(null);
		quote.setParagraphAttributes(attrib, true);
		header.add(quote);
	}

	private void initMid() {
		JPanel mid = new JPanel();
		mid.setLayout(null);
		mid.setBounds(10, 120, 774, 400);
		mid.setOpaque(false);
		add(mid);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 774, 400);
		tabbedPane.setOpaque(false);
		mid.add(tabbedPane);

		tabbedPane
				.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Home</body></html>",
						homeTab);
		tabbedPane
				.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Books</body></html>",
						bookTab);
		tabbedPane
				.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Borrowers</body></html>",
						borrowTab);

	}

	private void initFooter() {
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(10, 530, 774, 30);
		add(footer);

		about = new JButton("About");
		about.setBounds(0, 5, 70, 20);
		about.setContentAreaFilled(false);
		about.setFont(new Font("Segoe UI", 1, 12));
		about.setCursor(new Cursor(Cursor.HAND_CURSOR));
		about.setToolTipText("Read more information about LIBro");
		footer.add(about);

		JLabel copyright = new JLabel("For AWS internal use only");
		copyright.setBounds(623, 5, 150, 20);
		copyright.setHorizontalAlignment(JLabel.RIGHT);
		footer.add(copyright);
	}

	private void randomizeQuote() {
		Random rand = new Random();
		int n = rand.nextInt(Quotes.quotes.length - 0) + 0;
		quote.setText("\"" + Quotes.quotes[n] + "\"");
	}

	public BorrowDialog getBorrowDialog() {
		return borrowDialog;
	}

	public void setBorrowDialog(BorrowDialog borrowDialog) {
		this.borrowDialog = borrowDialog;
	}

	public ReturnDialog getReturnDialog() {
		return returnDialog;
	}

	public void setReturnDialog(ReturnDialog returnDialog) {
		this.returnDialog = returnDialog;
	}

	public HomeTab getHomeTab() {
		return homeTab;
	}

	public BookTab getBookTab() {
		return bookTab;
	}

	public BorrowTab getBorrowTab() {
		return borrowTab;
	}

	public AddBookDialog getAddBookDialog() {
		return addBookDialog;
	}

	public AddBorrowerDialog getAddBorrowerDialog() {
		return addBorrowerDialog;
	}

	public EditBookDialog getEditBookDialog() {
		return editBookDialog;
	}

	public EditBorrowerDialog getEditBorrowerDialog() {
		return editBorrowerDialog;
	}

	public AboutDialog getAboutDialog() {
		return aboutDialog;
	}

	public JButton getAbout() {
		return about;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
