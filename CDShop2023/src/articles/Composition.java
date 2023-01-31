package articles;

public class Composition {
	
	private String name;
	private int duration;
	
	public Composition() {
		this.name = "";
		this.duration = 0;
	}
	
	public Composition(String name, int duration) {
		super();
		this.name = name;
		this.duration = duration;
	}
	
	public Composition(Composition original) {
		this.name = original.name;
		this.duration = original.duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Composition  "
				+ "name=" + name + 
				", duration=" + duration + " ";
	}
}