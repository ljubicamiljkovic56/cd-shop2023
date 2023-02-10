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
import articles.Composition;
import gui.UpdateDelete.CompositionsForm;
import shop.Shop;

//window to show all compositions
@SuppressWarnings("serial")
public class CompositionsWindow extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable compositionsTable;
	
	private Shop shop;
	
	public CompositionsWindow(Shop shop) {
		this.shop = shop;
		setTitle("Compositions");
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
		
		String[] header = new String[] {"Name", "Duration"};
		Object[][] data = new Object[this.shop.getCompositions().size()][header.length];
				
		for(int i = 0; i < this.shop.getCompositions().size(); i++) {
			Composition composition = shop.getCompositions().get(i);
			data[i][0] = composition.getName();
			data[i][1] = composition.getDuration();
		}
		
		tableModel = new DefaultTableModel(data, header);
		compositionsTable = new JTable(tableModel);
		compositionsTable.setRowSelectionAllowed(true);
		compositionsTable.setColumnSelectionAllowed(false);
		compositionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		compositionsTable.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(compositionsTable);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompositionsForm cf = new CompositionsForm(shop, null);
				cf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = compositionsTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick a row.", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)compositionsTable.getModel();
					String name = model.getValueAt(row, 0).toString();
					Composition composition = shop.findComposition(name);
					if(composition != null) {
						CompositionsForm cf = new CompositionsForm(shop, composition);
						cf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Cannot find seleted composition.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = compositionsTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick a row.", 
							"Error", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)compositionsTable.getModel();
					String name = model.getValueAt(row, 0).toString();
					Composition composition = shop.findComposition(name);
					if(composition != null) {
						int pick = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete a composition?", 
								composition.getName() + " - Confirm", JOptionPane.YES_NO_OPTION);
						if(pick == JOptionPane.YES_OPTION) {
							shop.getCompositions().remove(composition);
							model.removeRow(row);
							shop.saveCompositionToFile();
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"Cannot find selected composition.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}