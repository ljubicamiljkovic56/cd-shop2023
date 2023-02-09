package main;

import gui.Login.LoginWindow;
import shop.Shop;
//main class that starts the app
public class ShopMain {

	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.loadSellersFromFile();
		shop.loadArticleFromFile();
		shop.loadCompositionsFromFile();
		
		LoginWindow login = new LoginWindow(shop);
		login.setVisible(true);
	}

}
