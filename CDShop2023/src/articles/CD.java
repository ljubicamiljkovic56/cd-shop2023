package articles;

import java.util.ArrayList;

public class CD extends Article {

	private String performer;
	private String genre;
	private ArrayList<Composition> compositions;
	
	public CD() {
		this.performer = "";
		this.genre = "";
		this.compositions = new ArrayList<Composition>();
	}
	
	public CD(String idCode, String publisher, int yearOfRelease, double price, int numberOfAvailableCopies,
			int numberOfSoldOutCopies, String name, String performer, String genre,
			ArrayList<Composition> compositions) {
		super(idCode, publisher, yearOfRelease, price, numberOfAvailableCopies, numberOfSoldOutCopies, name);
		this.performer = performer;
		this.genre = genre;
		this.compositions = compositions;
	}
	
	public CD(CD original) {
		this.performer = original.performer;
		this.genre = original.genre;
		this.compositions = original.compositions;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public ArrayList<Composition> getCompositions() {
		return compositions;
	}

	public void setCompositions(ArrayList<Composition> compositions) {
		this.compositions = compositions;
	}

	@Override
	public String toString() {
		String phrase = "CD " + super.toString() + 
				"Performer: " + this.performer +
				"Genre: " + this.genre;
		for (Composition compositon : compositions) {
			phrase += "\n" + compositon;
		}
		return phrase;
	}
}