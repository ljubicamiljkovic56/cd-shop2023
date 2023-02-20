package gui.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.Read.ArticlesWindow;
import gui.Read.CompositionsWindow;
import gui.Read.SellersWindow;
import persons.Seller;
import shop.Shop;

//after log in, this class starts the entry window
@SuppressWarnings("serial")
public class EntryWindow extends JFrame {

	private JMenuBar mainMenu;
	private JMenu articlesMenu;
	private JMenuItem articlesItem;
	private JMenuItem compositionsItem;
	private JMenu sellersMenu;
	private JMenuItem sellersItem;
	
	private Shop shop;
	@SuppressWarnings("unused")
	private Seller loggedSeller;
	
	public EntryWindow(Shop shop, Seller loggedSeller) {
		this.shop = shop;
		this.loggedSeller = loggedSeller;
		setTitle("Welcome to shop - " + loggedSeller.getUsername());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		
	}
	
	private void initGUI() {
		this.mainMenu = new JMenuBar();
		this.articlesMenu = new JMenu("Articles");
		this.articlesItem = new JMenuItem("Show all articles");
		this.compositionsItem = new JMenuItem("Show all compositions");
		this.sellersMenu = new JMenu("Sellers");
		this.sellersItem = new JMenuItem("Show all sellers");
		
		this.articlesMenu.add(articlesItem);
		this.articlesMenu.add(compositionsItem);
		this.sellersMenu.add(sellersItem);
		
		this.mainMenu.add(articlesMenu);
		this.mainMenu.add(sellersMenu);
		
		setJMenuBar(this.mainMenu);
		
		JLabel background;
		ImageIcon img = new ImageIcon(getClass().getResource("/pictures/cdicon.jpg"));
		background = new JLabel(img);
		background.setBounds(0,0,500,500);
		add(background);
	}
	
	private void initActions() {
		compositionsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompositionsWindow cw = new CompositionsWindow(shop);
				cw.setVisible(true);
			}
		});
		sellersItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SellersWindow sw = new SellersWindow(shop);
				sw.setVisible(true);
			}
		});
		articlesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArticlesWindow aw = new ArticlesWindow(shop);
				aw.setVisible(true);
			}
		});
	}
}
