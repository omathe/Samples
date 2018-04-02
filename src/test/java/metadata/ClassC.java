package metadata;

public class ClassC extends ClassB {

	public static Integer POSITION = 21;

	protected Integer width;
	private String description;

	public ClassC() {

	}

	public ClassC(String name, Integer height, Integer width) {
		super(name, height);
		this.height = height;
		this.width = width;
	}

}
