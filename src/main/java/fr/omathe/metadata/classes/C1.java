package fr.omathe.metadata.classes;

public class C1 {

	public static String COLOR = "red";
	protected String name;
	private String confidential = "secretC1";
	private Integer max = 100;

	public C1() {

	}

	public C1(String name, String confidential, Integer max) {
		super();
		this.name = name;
		this.confidential = confidential;
		this.max = max;
	}

}
