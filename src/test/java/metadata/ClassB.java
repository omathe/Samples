package metadata;

public class ClassB extends ClassA implements InterfaceC1 {

	public static Integer POSITION = 21;

	protected Integer height;
	private Integer size = 2;

	public ClassB() {

	}

	public ClassB(String name, Integer height) {
		super(name);
		this.height = height;
	}

}
