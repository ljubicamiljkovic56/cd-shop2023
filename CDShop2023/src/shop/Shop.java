package shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import articles.Article;
import articles.Book;
import articles.CD;
import articles.Composition;
import persons.Gender;
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
	
	//add some article to bill
	public void addToBill(String idCode, int cashRegNumber) {
		Article article = findArticle(idCode);
		if(article != null) {
			cashRegister[cashRegNumber].addArticleOnBill(article);
		}
	}
	
	//method to save article to file articles.txt
	public void saveArticleToFile() {
		try {
			File articlesFile = new File("src/files/articles.txt");
			String content = "";
			for (CD cd : cds) {
				content += "CD| " + cd.getIdCode() + "|" +
							cd.getPublisher() + "|" +
							cd.getYearOfRelease() + "|" +
							cd.getPrice() + "|" +
							cd.getNumberOfAvailableCopies() + "|" +
							cd.getNumberOfSoldOutCopies() + "|" +
							cd.getName() + "|" +
							cd.getPerformer() + "|" +
							cd.getGenre() + "\n";
			}
			for (Book book : books) {
				content += "Book|" + book.getIdCode() + "|" +
							book.getPublisher() + "|" +
							book.getYearOfRelease() + "|" +
							book.getPrice() + "|" + 
							book.getNumberOfAvailableCopies() + "|" +
							book.getNumberOfSoldOutCopies() + "|" +
							book.getName() + "|" +
							book.getAuthor() + "|" +
							book.getNumberOfPages() + "|" +
							book.isHardCover() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(articlesFile));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//method to save seller to file sellers.txt
	public void saveSellerToFile() {
		try {
			File sellersFile = new File("src/files/sellers.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(sellersFile));
			String content = "";
			for (Seller seller : sellers) {
				content += 	seller.getName() + "|" + 
							seller.getSurname() + "|" +
							seller.getUsername() + "|" + 
							seller.getPassword() +
							"|" + Gender.toInt(seller.getGender()) + "\n";
			}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//method to save composition to file compositions.txt
	public void saveCompositionToFile() {
		try {
			File compositionsFile = new File("src/files/compositions.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(compositionsFile));
			String content = "";
			for (Composition composition : compositions) {
				content += 	composition.getName() + "|" + 
							composition.getDuration() + "|";
				CD cd = findCD(composition);
				if(cd != null) {
					content += cd.getIdCode();
				}
				content += "\n";
			}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//load articles from file, no matter if it's book or cd
	public void loadArticleFromFile() {
		try {
			File articlesFile = new File("src/files/articles.txt");
			BufferedReader br = new BufferedReader(new FileReader(articlesFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String articleType = split[0];
				String idCode = split[1];
				String publisher = split[2];
				String yearOfReleaseString = split[3];
				int yearOfRelease = Integer.parseInt(yearOfReleaseString);
				String priceString = split[4];
				double price = Double.parseDouble(priceString);
				String availableString = split[5];
				int availableCopies = Integer.parseInt(availableString);
				String soldOutString = split[6];
				int soldOut = Integer.parseInt(soldOutString);
				String name = split[7];
				if(articleType.equals("CD")) {
					String performer = split[8];
					String genre = split[9];
					ArrayList<Composition> compositions = new ArrayList<Composition>();
					CD cd = new CD(idCode, publisher, yearOfRelease, price, availableCopies, soldOut, 
							name, performer, genre, compositions);
					cds.add(cd);
				}else if(articleType.equals("Book")) {
					String author = split[8];
					String numberOfPagesString = split[9];
					int numberOfPages = Integer.parseInt(numberOfPagesString);
					String coverString = split[10];
					boolean cover = Boolean.parseBoolean(coverString);
					Book book = new Book(idCode, publisher, yearOfRelease, price, availableCopies, soldOut, 
							name, author, numberOfPages, cover);
					books.add(book);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//method to load sellers from sellers.txt file
	public void loadSellersFromFile() {
		try {
			File sellersFile = new File("src/files/sellers.txt");
			BufferedReader br = new BufferedReader(new FileReader(sellersFile));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String name = split[0];
				String surname = split[1];
				String username = split[2];
				String password = split[3];
				int genderInt = Integer.parseInt(split[4]);
				Gender gender = Gender.toValue(genderInt);
				Seller seller = new Seller(name, surname, username, password, gender);
				sellers.add(seller);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//method to load compositions from compositions.txt
	public void loadCompositionsFromFile() {
		try {
			File compositionsFile = new File("src/files/compositions.txt");
			BufferedReader reader = new BufferedReader(new FileReader(compositionsFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String name = split[0];
				int duration = Integer.parseInt(split[1]);
				String cdIdCode = split[2];
				Composition composition = new Composition(name, duration);
				CD cd = (CD) findArticle(cdIdCode);
				if(cd != null) {
					cd.getCompositions().add(composition);
				}
				compositions.add(composition);
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}