package shop;

import java.util.ArrayList;

import articles.Article;

//Class CashRegister contains a list of bought articles
//methods for adding and deleting articles from bill and method for paying a bill
public class CashRegister {
	
	private ArrayList<Article> bill;
	public static final double tax = .25;
	
	public CashRegister() {
		this.bill = new ArrayList<Article>();
	}

	public ArrayList<Article> getBill(){
		return bill;
	}
	
	public void addArticleOnBill(Article article) {
		this.bill.add(article);
	}
	
	public void removeArticleFromBill(Article article) {
		this.bill.remove(article);
	}
	public double payTheBill() {
		double finalPrice = 0;
		for(Article article : bill) {
			finalPrice += article.getPrice();
		}
		bill.clear();
		return finalPrice * (1 + tax);
	}
}