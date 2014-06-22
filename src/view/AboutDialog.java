package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog() {
		initDialog();
		initComponents();
		initKatakana();
	}

	private void initDialog() {
		setSize(350, 400);
		setLayout(null);
		setTitle("LIBro - About");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void initComponents() {
		JLabel logo = new JLabel(new ImageIcon("images/logo_blue_about.png"));
		logo.setBounds(20, 20, 304, 100);
		add(logo);

		JSeparator sep = new JSeparator();
		sep.setBounds(20, 145, 304, 2);
		sep.setForeground(new Color(27, 161, 226));
		sep.setBackground(new Color(27, 161, 226));
		sep.setOpaque(true);
		add(sep);

		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrib, StyleConstants.ALIGN_JUSTIFIED);
		StyleConstants.setFontFamily(attrib, "Segoe UI");
		StyleConstants.setFontSize(attrib, 14);
		StyleConstants.setItalic(attrib, true);

		JTextPane aboutMsg = new JTextPane();
		aboutMsg.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vel risus ac justo adipiscing dictum. Donec sed dictum erat, eget molestie tortor. Ut pulvinar malesuada turpis sed tincidunt. Phasellus ut felis lacus.");
		aboutMsg.setBounds(20, 160, 304, 100);
		aboutMsg.setEditable(false);
		aboutMsg.setBackground(null);
		aboutMsg.setParagraphAttributes(attrib, true);
		add(aboutMsg);

		JLabel caption = new JLabel("For AWS internal use only.");
		caption.setBounds(20, 345, 140, 15);
		add(caption);

		JLabel copyright = new JLabel("\u00a9" + "  2014");
		copyright.setBounds(280, 345, 45, 15);
		copyright.setHorizontalAlignment(JLabel.RIGHT);
		add(copyright);
	}

	private void initKatakana() {
		SimpleAttributeSet attrib = new SimpleAttributeSet();
		StyleConstants.setAlignment(attrib, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontFamily(attrib, "Segoe UI");
		StyleConstants.setFontSize(attrib, 14);
		StyleConstants.setItalic(attrib, true);

		JTextPane pane1 = new JTextPane();
		pane1.setText("#02 エデン" + "\t\t" + "#06 カエル" + "\t\t" + "#09 エリック");
		pane1.setBounds(20, 270, 304, 20);
		pane1.setEditable(false);
		pane1.setBackground(null);
		pane1.setParagraphAttributes(attrib, true);
		add(pane1);

		JTextPane pane2 = new JTextPane();
		pane2.setText("#14 パット" + "\t\t\t\t" + "#20 ジャン");
		pane2.setBounds(20, 295, 304, 20);
		pane2.setEditable(false);
		pane2.setBackground(null);
		pane2.setParagraphAttributes(attrib, true);
		add(pane2);

		JSeparator sep = new JSeparator();
		sep.setBounds(20, 335, 304, 2);
		sep.setForeground(Color.LIGHT_GRAY);
		sep.setBackground(Color.LIGHT_GRAY);
		sep.setOpaque(true);
		add(sep);
	}
}
