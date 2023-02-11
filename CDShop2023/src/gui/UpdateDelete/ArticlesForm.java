package gui.UpdateDelete;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import articles.Article;
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
		
	}
	
	private void initActions() {
		
	}

}
