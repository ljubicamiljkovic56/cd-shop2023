package gui.UpdateDelete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import articles.Article;
import articles.Book;
import articles.CD;
import articles.Composition;
import net.miginfocom.swing.MigLayout;
import shop.Shop;

//class for editing articles form
@SuppressWarnings("serial")
public class ArticlesForm extends JFrame {
	
	private JLabel lblIdCode = new JLabel("ID Code");
	private JTextField txtIdCode = new JTextField(20);
	private JLabel lblPublisher = new JLabel("Publisher");
	private JTextField txtPublisher = new JTextField(20);
	private JLabel lblYear = new JLabel("Year of Release");
	private JTextField txtYear = new JTextField(20);
	private JLabel lblPrice = new JLabel("Price");
	private JTextField txtPrice = new JTextField(20);
	private JLabel lblAvailable = new JLabel("Available Copies");
	private JTextField txtAvailable = new JTextField(20);
	private JLabel lblSoldOut = new JLabel("Sold Out Copies");
	private JTextField txtSoldOut = new JTextField(20);
	private JLabel lblName = new JLabel("Name");
	private JTextField txtName = new JTextField(20);
	private JLabel lblType = new JLabel("Type");
	private JRadioButton rbtnCD = new JRadioButton("CD");
	private JRadioButton rbtnBook = new JRadioButton("Book");
	
	private JLabel lblPerformer = new JLabel("Performer");
	private JTextField txtPerformer = new JTextField(20);
	private JLabel lblGenre = new JLabel("Genre");
	private JTextField txtGenre = new JTextField(20);
	private JLabel lblAuthor = new JLabel("Author");
	private JTextField txtAuthor = new JTextField(20);
	private JLabel lblNumberOfPages = new JLabel("Number of Pages");
	private JTextField txtNumberOfPages = new JTextField(20);
	private JLabel lblHardCover = new JLabel("Hard Cover");
	private JCheckBox cbHardCover = new JCheckBox();
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Shop shop;
	private Article article;
	
	public ArticlesForm(Shop shop, Article article) {
		this.shop = shop;
		this.article = article;
		if(article != null) {
			setTitle("Edit this article - " + article.getName());
		}else {
			setTitle("Add new article");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(article != null) {
			fillTheFields();
		}else {
			rbtnCD.setSelected(true);
			enableCDFields(true);
		}
		
		add(lblIdCode);
		add(txtIdCode);
		add(lblPublisher);
		add(txtPublisher);
		add(lblYear);
		add(txtYear);
		add(lblPrice);
		add(txtPrice);
		add(lblAvailable);
		add(txtAvailable);
		add(lblSoldOut);
		add(txtSoldOut);
		add(lblName);
		add(txtName);
		add(lblType);
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(rbtnCD);
		typeGroup.add(rbtnBook);
		add(rbtnCD, "split 2");
		add(rbtnBook);
		add(lblPerformer);
		add(txtPerformer);
		add(lblGenre);
		add(txtGenre);
		add(lblAuthor);
		add(txtAuthor);
		add(lblNumberOfPages);
		add(txtNumberOfPages);
		add(lblHardCover);
		add(cbHardCover);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions() {
		rbtnCD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableCDFields(rbtnCD.isSelected());
			}
		});
		rbtnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableBookFields(rbtnBook.isSelected());
			}
		});
				
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validation() == true) {
					String idCode = txtIdCode.getText().trim();
					String publisher = txtPublisher.getText().trim();
					int year = Integer.parseInt(txtYear.getText().trim());
					double price = Double.parseDouble(txtPrice.getText().trim());
					int available = Integer.parseInt(txtAvailable.getText().trim());
					int soldOut = Integer.parseInt(txtSoldOut.getText().trim());
					String name = txtName.getText().trim();
					if(rbtnCD.isSelected()) {
						String performer = txtPerformer.getText().trim();
						String genre = txtGenre.getText().trim();
						if(article != null) {
							CD cd = (CD) article;
							cd.setIdCode(idCode);
							cd.setPublisher(publisher);
							cd.setYearOfRelease(year);
							cd.setPrice(price);
							cd.setNumberOfAvailableCopies(available);
							cd.setNumberOfSoldOutCopies(soldOut);
							cd.setName(name);
							cd.setPerformer(performer);
							cd.setGenre(genre);
						}else {
							CD cd = new CD(idCode, publisher, 
									year, price, available, soldOut, 
									name, performer, genre, new ArrayList<Composition>());
							shop.getCds().add(cd);
						}
					}else {
						String author = txtAuthor.getText().trim();
						int numberOfPages = Integer.parseInt(txtNumberOfPages.getText().trim());
						boolean hardCover = cbHardCover.isSelected();
						if(article != null) {
							Book book = (Book) article;
							book.setIdCode(idCode);
							book.setPublisher(publisher);
							book.setYearOfRelease(year);
							book.setPrice(price);
							book.setNumberOfAvailableCopies(available);
							book.setNumberOfSoldOutCopies(soldOut);
							book.setName(name);
							book.setAuthor(author);
							book.setNumberOfPages(numberOfPages);
							book.setHardCover(hardCover);
						}else {
							Book book = new Book(idCode, publisher, year, price, 
									available, soldOut, name, author, 
									numberOfPages, hardCover);
							shop.getBooks().add(book);
						}
					}
					shop.saveArticleToFile();
					ArticlesForm.this.dispose();
					ArticlesForm.this.setVisible(false);
				}
			}
		});
	}
	
	private void enableCDFields(boolean enable) {
		txtPerformer.setEnabled(enable);
		txtGenre.setEnabled(enable);
		txtAuthor.setEnabled(!enable);
		txtNumberOfPages.setEnabled(!enable);
		cbHardCover.setEnabled(!enable);
	}
	
	private void enableBookFields(boolean enable) {
		txtPerformer.setEnabled(!enable);
		txtGenre.setEnabled(!enable);
		txtAuthor.setEnabled(enable);
		txtNumberOfPages.setEnabled(enable);
		cbHardCover.setEnabled(enable);
	}
	
	private void fillTheFields() {
		txtIdCode.setText(article.getIdCode());
		txtPublisher.setText(article.getPublisher());
		txtYear.setText(String.valueOf(article.getYearOfRelease()));
		txtPrice.setText(String.valueOf(article.getPrice()));
		txtAvailable.setText(String.valueOf(article.getNumberOfAvailableCopies()));
		txtSoldOut.setText(String.valueOf(article.getNumberOfSoldOutCopies()));
		txtName.setText(article.getName());
		if(article instanceof CD) {
			CD cd = (CD) article;
			enableCDFields(true);
			txtPerformer.setText(cd.getPerformer());
			txtGenre.setText(cd.getGenre());
		}else if(article instanceof Book) {
			Book book = (Book) article;
			enableBookFields(true);
			txtAuthor.setText(book.getAuthor());
			txtNumberOfPages.setText(String.valueOf(book.getNumberOfPages()));
			cbHardCover.setSelected(book.isHardCover());
		}
		rbtnCD.setEnabled(false);
		rbtnBook.setEnabled(false);
		
	}
	
	private boolean validation() {
		boolean ok = true;
		String message = "Please enter valid data:\n";
		
		if(txtIdCode.getText().trim().equals("")) {
			message += "- Enter ID code\n";
			ok = false;
		}
		if(txtPublisher.getText().trim().equals("")) {
			message += "- Enter publisher\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtYear.getText().trim());
		}catch (NumberFormatException e) {
			message += "- Enter year of release\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtPrice.getText().trim());
		}catch (NumberFormatException e) {
			message += "- Enter price\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtAvailable.getText().trim());
		}catch (NumberFormatException e) {
			message += "- Enter number of available copies\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtSoldOut.getText().trim());
		}catch (NumberFormatException e) {
			message += "- Enter number of sold out copies\n";
			ok = false;
		}
		if(txtName.getText().trim().equals("")) {
			message += "- Enter name\n";
			ok = false;
		}
		if(rbtnCD.isSelected()) {
			if(txtPerformer.getText().trim().equals("")) {
				message += "- Enter perfomer\n";
				ok = false;
			}
			if(txtGenre.getText().trim().equals("")) {
				message += "- Enter genre\n";
				ok = false;
			}
		}else {
			if(txtAuthor.getText().trim().equals("")) {
				message += "- Enter author\n";
				ok = false;
			}
			try {
				Integer.parseInt(txtNumberOfPages.getText().trim());
			}catch (NumberFormatException e) {
				message += "- Enter a number of pages (number)\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, message, "Invalid data", 
					JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
