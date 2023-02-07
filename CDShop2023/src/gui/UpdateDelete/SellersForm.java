package gui.UpdateDelete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import persons.Gender;
import persons.Seller;
import shop.Shop;

//class for editing sellers form
@SuppressWarnings("serial")
public class SellersForm extends JFrame {
	
	private JLabel lblName = new JLabel("Name");
	private JTextField txtName = new JTextField(20);
	private JLabel lblSurname = new JLabel("Surname");
	private JTextField txtSurname = new JTextField(20);
	private JLabel lblUsername = new JLabel("Username");
	private JTextField txtUsername = new JTextField(20);
	private JLabel lblPassword = new JLabel("Password");
	private JPasswordField pfPassword = new JPasswordField(20);
	private JLabel lblGender = new JLabel("Gender");
	private JComboBox<Gender> cbGender = new JComboBox<Gender>(Gender.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Shop shop;
	private Seller seller;
	
	public SellersForm(Shop shop, Seller seller) {
		this.shop = shop;
		this.seller = seller;
		if(this.seller == null) {
			setTitle("Add new seller");
		}else {
			setTitle("Edit seller - " + this.seller.getUsername());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.seller != null) {
			fillFields();
		}
		
		add(lblName);
		add(txtName);
		add(lblSurname);
		add(txtSurname);
		add(lblUsername);
		add(txtUsername);
		add(lblPassword);
		add(pfPassword);
		add(lblGender);
		add(cbGender);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validation() == true) {
					String name = txtName.getText().trim();
					String surname = txtSurname.getText().trim();
					String username = txtUsername.getText().trim();
					String password = new String(pfPassword.getPassword()).trim();
					Gender gender = (Gender) cbGender.getSelectedItem();
					if(seller == null) {
						seller = new Seller(name, surname, username, password, gender);
						shop.getSellers().add(seller);
					}else {
						seller.setName(name);
						seller.setSurname(surname);
						seller.setUsername(username);
						seller.setPassword(password);
						seller.setGender(gender);
					}
					shop.saveSellerToFile();
					SellersForm.this.dispose();
					SellersForm.this.setVisible(false);
				}
			}
		});
	}
	
	private void fillFields() {
		txtName.setText(this.seller.getName());
		txtSurname.setText(this.seller.getSurname());
		txtUsername.setText(this.seller.getUsername());
		pfPassword.setText(this.seller.getPassword());
		cbGender.setSelectedItem(this.seller.getGender());
	}
	
	private boolean validation() {
		boolean ok = true;
		String message = "Please, enter the valid data";
		if(txtName.getText().trim().equals("")) {
			message += "- Enter name\n";
			ok = false;
		}
		if(txtSurname.getText().trim().equals("")) {
			message += "- Enter surname\n";
			ok = false;
		}
		if(txtUsername.getText().trim().equals("")) {
			message += "- Enter username\n";
			ok = false;
		}
		String password = new String(pfPassword.getPassword()).trim();
		if(password.trim().equals("")) {
			message += "- Enter password\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, message, "Invalid data", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}