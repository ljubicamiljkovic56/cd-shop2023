package main;

import shop.Shop;

public class ShopMain {

	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.loadSellersFromFile();
		shop.loadArticleFromFile();
		shop.loadCompositionsFromFile();
	}

}
