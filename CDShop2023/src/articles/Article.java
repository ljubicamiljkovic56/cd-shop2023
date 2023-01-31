package articles;

public abstract class Article {
	
	protected String idCode;
	protected String publisher;
	protected int yearOfRelease;
	protected double price;
	protected int numberOfAvailableCopies;
	protected int numberOfSoldOutCopies;
	protected String name;
	
	public Article() {
		this.idCode = "";
		this.publisher = "";
		this.yearOfRelease = 1990;
		this.price = 0;
		this.numberOfAvailableCopies = 0;
		this.numberOfSoldOutCopies = 0;
		this.name = "";
	}
	
	public Article(String idCode, String publisher, int yearOfRelease, double price, int numberOfAvailableCopies,
			int numberOfSoldOutCopies, String name) {
		super();
		this.idCode = idCode;
		this.publisher = publisher;
		this.yearOfRelease = yearOfRelease;
		this.price = price;
		this.numberOfAvailableCopies = numberOfAvailableCopies;
		this.numberOfSoldOutCopies = numberOfSoldOutCopies;
		this.name = name;
	}

	public Article(Article original) {
		this.idCode = original.idCode;
		this.publisher = original.publisher;
		this.yearOfRelease = original.yearOfRelease;
		this.price = original.price;
		this.numberOfAvailableCopies = original.numberOfAvailableCopies;
		this.numberOfSoldOutCopies = original.numberOfSoldOutCopies;
		this.name = original.name;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}
	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfAvailableCopies() {
		return numberOfAvailableCopies;
	}

	public void setNumberOfAvailableCopies(int numberOfAvailableCopies) {
		this.numberOfAvailableCopies = numberOfAvailableCopies;
	}

	public int getNumberOfSoldOutCopies() {
		return numberOfSoldOutCopies;
	}

	public void setNumberOfSoldOutCopies(int numberOfSoldOutCopies) {
		this.numberOfSoldOutCopies = numberOfSoldOutCopies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Article "
				+ "idCode=" + idCode + 
				", publisher=" + publisher + 
				", yearOfRelease=" + yearOfRelease
				+ ", price=" + price + 
				", numberOfAvailableCopies=" + numberOfAvailableCopies
				+ ", numberOfSoldOutCopies=" + numberOfSoldOutCopies + 
				", name=" + name + " ";
	}
	
}