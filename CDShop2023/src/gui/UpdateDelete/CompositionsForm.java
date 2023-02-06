package gui.UpdateDelete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import articles.CD;
import articles.Composition;
import net.miginfocom.swing.MigLayout;
import shop.Shop;

//class for editing composition form
@SuppressWarnings("serial")
public class CompositionsForm extends JFrame {
	
	private JLabel lblName = new JLabel("Name");
	private JTextField txtName = new JTextField(20);
	private JLabel lblDuration = new JLabel("Duration");
	private JTextField txtDuration = new JTextField(20);
	private JLabel lblCD = new JLabel("CD");
	private JComboBox<String> cbCD = new JComboBox<String>();
	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private Shop shop;
	private Composition composition;
	
	
	public CompositionsForm(Shop shop, Composition composition) {
		this.shop = shop;
		this.composition = composition;
		if(this.composition == null) {
			setTitle("Add new composition");
		}else {
			setTitle("Edit composition - " + this.composition.getName());
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
		
		if(this.composition != null) {
			fillFields();
		}
		
		for (CD cd : this.shop.getCds()) {
			cbCD.addItem(cd.getIdCode());
		}
		
		add(lblName);
		add(txtName);
		add(lblDuration);
		add(txtDuration);
		add(lblCD);
		add(cbCD);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validation()) {
					String name = txtName.getText().trim();
					int duration = Integer.parseInt(txtDuration.getText().trim());
					String cdIdCode = cbCD.getSelectedItem().toString();
					CD cd = shop.findCDbyId(cdIdCode);
					
					if(composition == null) {
						composition = new Composition(name, duration);
						if(cd != null) {
							cd.getCompositions().add(composition);
						}
						shop.getCompositions().add(composition);
					}else {
						composition.setName(name);
						composition.setDuration(duration);
						CD editCD = shop.findCD(composition);
						if(editCD != null) {
							editCD.getCompositions().remove(composition);
						}
						if(cd != null) {
							cd.getCompositions().add(composition);
						}
					}
					shop.saveCompositionToFile();
					CompositionsForm.this.dispose();
					CompositionsForm.this.setVisible(false);
				}
			}
		});
		
	}
	
	private void fillFields() {
		txtName.setText(this.composition.getName());
		txtDuration.setText(String.valueOf(this.composition.getDuration()));
		CD cd = this.shop.findCD(this.composition);
		cbCD.setSelectedItem(cd.getIdCode());
	}
	
	private boolean validation() {
		boolean ok = true;
		if(txtName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the name of composition", "Invalid data", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		try {
			Integer.parseInt(txtDuration.getText().trim());
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Composition duration must be a number", "Invalid data", JOptionPane.WARNING_MESSAGE);
			ok = false;
		}
		return ok;
	}

}