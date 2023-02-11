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

import articles.Article;
import articles.Book;
import articles.CD;
import gui.UpdateDelete.ArticlesForm;
import shop.Shop;

@SuppressWarnings("serial")
public class ArticlesWindow extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable articlesTable;
	private Shop shop;
	
	public ArticlesWindow(Shop shop) {
		this.shop = shop;
		setTitle("Articles");
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
		
		int numberArticles = shop.getBooks().size() + shop.getCds().size();
		String[] header = new String[] {"Type", "ID", "Name", "Publisher", 
				"Year of Release", "Price", 
				"Available", "Sold Out", "Performer", "Genre", 
				"Author", "Number of Pages", "Hard Cover"};
		Object[][] data = new Object[numberArticles][header.length];
		
		for(int i = 0; i < shop.getCds().size(); i++) {
			CD cd = shop.getCds().get(i);
			data[i][0] = "CD";
			data[i][1] = cd.getIdCode();
			data[i][2] = cd.getName();
			data[i][3] = cd.getPublisher();
			data[i][4] = cd.getYearOfRelease();
			data[i][5] = cd.getPrice();
			data[i][6] = cd.getNumberOfAvailableCopies();
			data[i][7] = cd.getNumberOfSoldOutCopies();
			data[i][8] = cd.getPerformer();
			data[i][9] = cd.getGenre();
			data[i][10] = "--";
			data[i][11] = "--";
			data[i][12] = "--";
		}
		for(int i = 0; i < shop.getBooks().size(); i++) {
			Book book = shop.getBooks().get(i);
			int row = shop.getCds().size() + i;
			data[row][0] = "Book";
			data[row][1] = book.getIdCode();
			data[row][2] = book.getName();
			data[row][3] = book.getPublisher();
			data[row][4] = book.getYearOfRelease();
			data[row][5] = book.getPrice();
			data[row][6] = book.getNumberOfAvailableCopies();
			data[row][7] = book.getNumberOfSoldOutCopies();
			data[row][8] = "--";
			data[row][9] = "--";
			data[row][10] = book.getAuthor();
			data[row][11] = book.getNumberOfPages();
			data[row][12] = book.isHardCover() ? "Yes" : "No";
		}
		
		tableModel = new DefaultTableModel(data, header);
		articlesTable = new JTable(tableModel);
		articlesTable = new JTable(tableModel);
		articlesTable.setRowSelectionAllowed(true);
		articlesTable.setColumnSelectionAllowed(false);
		articlesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		articlesTable.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(articlesTable);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticlesForm af = new ArticlesForm(shop, null);
				af.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = articlesTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, 
							"You have to pick a row in order to edit.", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = articlesTable.getValueAt(row, 1).toString();
					Article article = shop.findArticle(id);
					if(article != null) {
						ArticlesForm af = new ArticlesForm(shop, article);
						af.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Cannot find selected article", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = articlesTable.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, 
							"You have to pick a row in order to delete.", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = articlesTable.getValueAt(row, 1).toString();
					Article article = shop.findArticle(id);
					if(article != null) {
						int pick = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete an article?", 
								article.getName() + " - Confirm?", JOptionPane.YES_NO_OPTION);
						if(pick == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) articlesTable.getModel();
							if(article instanceof CD) {
								shop.getCds().remove(article);
							}else {
								shop.getBooks().remove(article);
							}
							model.removeRow(row);
							shop.saveArticleToFile();
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"Cannot find selected article", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}