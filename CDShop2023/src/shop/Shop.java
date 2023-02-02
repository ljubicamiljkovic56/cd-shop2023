package shop;

import java.util.ArrayList;

import articles.Article;
import articles.Book;
import articles.CD;
import articles.Composition;
import persons.Seller;

//This class contains all the methods for the entities
public class Shop {
	
	private ArrayList<Seller> sellers;
	private ArrayList<Book> books;
	private ArrayList<CD> cds;
	private ArrayList<Composition> compositions;
	private CashRegister[] cashRegister;
	
	public Shop() {
		this.sellers = new ArrayList<Seller>();
		this.books = new ArrayList<Book>();
		this.cds = new ArrayList<CD>();
		this.compositions = new ArrayList<Composition>();
		this.cashRegister = new CashRegister[3];
	}

	//get and set methods, and methods for adding and removing sellers, books, etc
	public ArrayList<Seller> getSellers() {
		return sellers;
	}
	
	public void addSeller(Seller seller) {
		this.sellers.add(seller);
	}
	
	public void removeSeller(Seller seller) {
		this.sellers.remove(seller);
	}

	public void setSellers(ArrayList<Seller> sellers) {
		this.sellers = sellers;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}
	
	public void addBooks(Book book) {
		this.books.add(book);
	}
	
	public void removeBooks(Book book) {
		this.books.remove(book);
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<CD> getCds() {
		return cds;
	}
	
	public void addCD(CD cd) {
		this.cds.add(cd);
	}
	
	public void removeCD(CD cd) {
		this.cds.remove(cd);
	}

	public void setCds(ArrayList<CD> cds) {
		this.cds = cds;
	}

	public ArrayList<Composition> getCompositions() {
		return compositions;
	}
	
	public void addComposition(Composition composition) {
		this.compositions.add(composition);
	}
	
	public void removeComposition(Composition composition) {
		this.compositions.remove(composition);
	}

	public void setCompositions(ArrayList<Composition> compositions) {
		this.compositions = compositions;
	}

	public CashRegister[] getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister[] cashRegister) {
		this.cashRegister = cashRegister;
	}

	//the login method
	public Seller login(String username, String password) {
		for(Seller seller : sellers) {
			if(seller.getUsername().equals(username) && 
					seller.getPassword().equals(password)) {
				return seller;
			}
		}
		return null;
	}
	
	//find seller by username
	public Seller findSeller(String username) {
		for(Seller seller : sellers) {
			if(seller.getUsername().equals(username)) {
				return seller;
			}
		}
		return null;
	}
	
	//find article by idCode, whether it's book or cd
	public Article findArticle(String idCode) {
		for(CD cd : cds) {
			if(cd.getIdCode().equals(idCode)) {
				return cd;
			}
		}
		for(Book book : books) {
			if(book.getIdCode().equals(idCode)) {
				return book;
			}
		}
		return null;
	}
	
	//find cd by some composition
	public CD findCD(Composition composition) {
		for(CD cd : cds) {
			if(cd.getCompositions().contains(composition)) {
				return cd;
			}
		}
		return null;
	}
	
	//find CD by id code
	public CD findCDbyId(String idCode) {
		for(CD cd : cds) {
			if(cd.getIdCode().equals(idCode)) {
				return cd;
			}
		}
		return null;
	}
	
	//find book by id code
	public Book findBookById(String idCode) {
		for(Book book : books) {
			if(book.getIdCode().equals(idCode)) {
				return book;
			}
		}
		return null;
	}
	
	//find article by id, no matter if it's a book or cd
	public Article findArticleById(String idCode) {
		Article article = findCDbyId(idCode);
		if(article == null) {
			article = findBookById(idCode);
			
		}
		return article;
	}
	
	//find composition by name
	public Composition findComposition(String name) {
		for(Composition composition : compositions) {
			if(composition.getName().equals(name)) {
				return composition;
			}
		}
		return null;
	}
}