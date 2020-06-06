package fr.omathe.csv;

/**
 * This class is a class test for syntax coloring
 * @author olivier
 *
 */
public class Test extends ParentTest {
	
	enum Choice {
		ONE, TWO, THREE
	}
	
	interface Move {
		
	}
	
	static String STATIC_FIELD = "toto";
	static final String FINAL_STATIC_FIELD = "titi";
	
	private String name;
	private Choice choice;

	
	public Test(Integer age, String name) {
		super(age);
		this.name = name; // TODO do not forget
		this.choice = Choice.ONE; // good practice
	}
	
	public void display(String color) {
		
		String localVariable = "abc";
		
		String c = STATIC_FIELD;
		
		String c2 = FINAL_STATIC_FIELD + localVariable + color;
		
		int value = 258;
	}

	public void calculate(String color) {
		
	}
	
	/*
	public void oldMethod() {
		
	}
	*/

	@Override
	public String toString() {
		return "Test [name=" + name + ", age=" + age + "]";
	}
	
	

}
