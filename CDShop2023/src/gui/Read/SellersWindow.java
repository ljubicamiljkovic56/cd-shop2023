package gui.Read;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.UpdateDelete.SellersForm;
import persons.Seller;
import shop.Shop;

//window that shows sellers
@SuppressWarnings("serial")
public class SellersWindow extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable sellersTable;
	
	private Shop shop;
	
	public SellersWindow(Shop shop) {
		this.shop = shop;
		setTitle("Sellers");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/pictures/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/pictures/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/pictures/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] header = new String[] {"Name", "Surname", "Gender", "Username", "Password"};
		Object[][] data = new Object[this.shop.getSellers().size()][header.length];
		
		for(int i = 0; i < this.shop.getSellers().size(); i++) {
			Seller seller = this.shop.getSellers().get(i);
			data[i][0] = seller.getName();
			data[i][1] = seller.getSurname();
			data[i][2] = seller.getGender();
			data[i][3] = seller.getUsername();
			data[i][4] = seller.getPassword();
		}
		
		tableModel = new DefaultTableModel(data, header);
		sellersTable = new JTable(tableModel);
		sellersTable.setRowSelectionAllowed(true);
		sellersTable.setColumnSelectionAllowed(false);
		sellersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sellersTable.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(sellersTable);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SellersForm sf = new SellersForm(shop, null);
				sf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = sellersTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick a row in order to edit.", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)sellersTable.getModel();
					String username = model.getValueAt(row, 3).toString();
					Seller seller = shop.findSeller(username);
					if(seller != null) {
						SellersForm sf = new SellersForm(shop, seller);
						sf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Cannot find selected seller.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = sellersTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick a row in order to delete.", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)sellersTable.getModel();
					String username = model.getValueAt(row, 3).toString();
					Seller seller = shop.findSeller(username);
					if(seller != null) {
						int pick = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete seller?", seller.getUsername() 
								+ " - Confirm?", JOptionPane.YES_NO_OPTION);
						if(pick == JOptionPane.YES_OPTION) {
							shop.getSellers().remove(seller);
							model.removeRow(row);
							shop.saveSellerToFile();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Cannot find selected seller.", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}