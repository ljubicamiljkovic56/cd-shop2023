package articles;

public class Book extends Article {
	
	private String author;
	private int numberOfPages;
	private boolean hardCover;
	
	public Book() {
		this.author = "";
		this.numberOfPages = 0;
		this.hardCover = false;
	}
	
	public Book(String idCode, String publisher, int yearOfRelease, double price, int numberOfAvailableCopies,
			int numberOfSoldOutCopies, String name, String author, int numberOfPages, boolean hardCover) {
		super(idCode, publisher, yearOfRelease, price, numberOfAvailableCopies, numberOfSoldOutCopies, name);
		this.author = author;
		this.numberOfPages = numberOfPages;
		this.hardCover = hardCover;
	}
	
	public Book(Book original) {
		this.author = original.author;
		this.numberOfPages = original.numberOfPages;
		this.hardCover = original.hardCover;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public boolean isHardCover() {
		return hardCover;
	}

	public void setHardCover(boolean hardCover) {
		this.hardCover = hardCover;
	}

	@Override
	public String toString() {
		return "Book  "
				+ "author=" + author + 
				", numberOfPages=" + numberOfPages + 
				", hardCover=" + hardCover + " ";
	}

}