package gui.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import persons.Seller;
import shop.Shop;
//login class
@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	
	private JLabel lblMessage;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField pfPassword;
	private JButton btnOK;
	private JButton btnCancel;
	private Shop shop;
	
	public LoginWindow(Shop shop) {
		this.shop = shop;
		setTitle("Login");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblMessage = new JLabel("Welcome, in order to continue, please log in");
		this.lblUsername = new JLabel("Username: ");
		this.txtUsername= new JTextField(20);
		this.lblPassword = new JLabel("Password: ");
		this.pfPassword = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblMessage, "span 2");
		add(lblUsername);
		add(txtUsername);
		add(lblPassword);
		add(pfPassword);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String username = txtUsername.getText().trim();
						String password = new String(pfPassword.getPassword()).trim();
						if(username.equals("") || password.equals("")) {
							JOptionPane.showMessageDialog(null, "Username or password are empty.");
						}else {
							Seller seller = shop.login(username, password);
							if(shop != null) {
								LoginWindow.this.setVisible(false);
								LoginWindow.this.dispose();
								EntryWindow entry = new EntryWindow(shop, seller);
								entry.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(null, "Invalid data.");
							}
						}
					}
				});
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LoginWindow.this.setVisible(false);
						LoginWindow.this.dispose();
					}
				});			
	}
}