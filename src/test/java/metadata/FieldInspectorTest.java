package metadata;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import fr.omathe.metadata.FieldInspector;
import fr.omathe.metadata.FieldValue;

public class FieldInspectorTest {

	@Test
	public void getInterfaces() {

		List<Class<?>> list = FieldInspector.getInterfaces(InterfaceA1.class).collect(Collectors.toList());
		Assert.assertTrue(list.isEmpty());

		list = FieldInspector.getInterfaces(InterfaceA2.class).collect(Collectors.toList());
		Assert.assertTrue(list.isEmpty());

		list = FieldInspector.getInterfaces(InterfaceB1.class).collect(Collectors.toList());
		Assert.assertEquals(list.size(), 2);
		Assert.assertEquals(list.get(0), InterfaceA1.class);
		Assert.assertEquals(list.get(1), InterfaceA2.class);

		list = FieldInspector.getInterfaces(InterfaceB2.class).collect(Collectors.toList());
		Assert.assertTrue(list.isEmpty());

		list = FieldInspector.getInterfaces(InterfaceC1.class).collect(Collectors.toList());
		Assert.assertEquals(list.size(), 4);
		Assert.assertEquals(list.get(0), InterfaceB1.class);
		Assert.assertEquals(list.get(1), InterfaceB2.class);
		Assert.assertEquals(list.get(2), InterfaceA1.class);
		Assert.assertEquals(list.get(3), InterfaceA2.class);

		list = FieldInspector.getInterfaces(ClassC.class).collect(Collectors.toList());
		Assert.assertTrue(list.isEmpty());

		list = FieldInspector.getInterfaces(ClassB.class).collect(Collectors.toList());
		Assert.assertEquals(list.size(), 5);
		Assert.assertEquals(list.get(0), InterfaceC1.class);
		Assert.assertEquals(list.get(1), InterfaceB1.class);
		Assert.assertEquals(list.get(2), InterfaceB2.class);
		Assert.assertEquals(list.get(3), InterfaceA1.class);
		Assert.assertEquals(list.get(4), InterfaceA2.class);

		list = FieldInspector.getInterfaces(ClassA.class).collect(Collectors.toList());
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void findFieldValueInInterfaceFromInterface() {

		// InterfaceA1
		FieldValue<String> color = FieldInspector.findFieldValue("COLOR", InterfaceA1.class);
		Assert.assertTrue(color.isPresent());
		Assert.assertEquals(color.getValue(), "red");

		FieldValue<String> unknown = FieldInspector.findFieldValue("UNKNOWN", InterfaceA1.class);
		Assert.assertFalse(unknown.isPresent());

		// InterfaceA2
		FieldValue<Integer> size = FieldInspector.findFieldValue("SIZE", InterfaceA2.class);
		Assert.assertTrue(size.isPresent());
		Assert.assertEquals(size.getValue(), Integer.valueOf(100));

		unknown = FieldInspector.findFieldValue("UNKNOWN", InterfaceA2.class);
		Assert.assertFalse(unknown.isPresent());

		// InterfaceB1
		size = FieldInspector.findFieldValue("SIZE", InterfaceB1.class);
		Assert.assertTrue(size.isPresent());
		Assert.assertEquals(size.getValue(), Integer.valueOf(120));

		unknown = FieldInspector.findFieldValue("UNKNOWN", InterfaceB1.class);
		Assert.assertFalse(unknown.isPresent());

		color = FieldInspector.findFieldValue("COLOR", InterfaceB1.class);
		Assert.assertTrue(color.isPresent());
		Assert.assertEquals(color.getValue(), "red");

		// InterfaceB2
		FieldValue<Integer> altitude = FieldInspector.findFieldValue("ALTITUDE", InterfaceB2.class);
		Assert.assertTrue(altitude.isPresent());
		Assert.assertEquals(altitude.getValue(), Integer.valueOf(80));

		unknown = FieldInspector.findFieldValue("UNKNOWN", InterfaceB2.class);
		Assert.assertFalse(unknown.isPresent());

		// InterfaceC1
		FieldValue<Integer> speed = FieldInspector.findFieldValue("SPEED", InterfaceC1.class);
		Assert.assertTrue(speed.isPresent());
		Assert.assertEquals(speed.getValue(), Integer.valueOf(200));

		unknown = FieldInspector.findFieldValue("UNKNOWN", InterfaceC1.class);
		Assert.assertFalse(unknown.isPresent());
	}

	@Test
	public void findFieldValueInInterfaceFromClass() {

		FieldValue<String> color = FieldInspector.findFieldValue("COLOR", ClassA.class);
		Assert.assertFalse(color.isPresent());

		color = FieldInspector.findFieldValue("COLOR", ClassB.class);
		Assert.assertTrue(color.isPresent());
		Assert.assertEquals(color.getValue(), "red");
	}

	@Test
	public void findFieldValueInClassFromClass() {

		// ClassA
		FieldValue<String> type = FieldInspector.findFieldValue("TYPE", ClassA.class);
		Assert.assertTrue(type.isPresent());
		Assert.assertEquals(type.getValue(), "cool");

		FieldValue<String> position = FieldInspector.findFieldValue("POSITION", ClassA.class);
		Assert.assertTrue(position.isPresent());
		Assert.assertEquals(position.getValue(), Integer.valueOf(20));

		FieldValue<String> name = FieldInspector.findFieldValue("name", ClassA.class);
		Assert.assertTrue(name.isPresent());
		Assert.assertNull(name.getValue());

		FieldValue<Integer> size = FieldInspector.findFieldValue("size", ClassA.class);
		Assert.assertTrue(size.isPresent());
		Assert.assertNull(size.getValue());

		FieldValue<String> unknown = FieldInspector.findFieldValue("UNKNOWN", ClassA.class);
		Assert.assertFalse(unknown.isPresent());

		// ClassB
		size = FieldInspector.findFieldValue("size", ClassB.class);
		Assert.assertTrue(size.isPresent());
		Assert.assertNull(size.getValue());

		position = FieldInspector.findFieldValue("POSITION", ClassB.class);
		Assert.assertTrue(position.isPresent());
		Assert.assertEquals(position.getValue(), Integer.valueOf(21));

		FieldValue<String> height = FieldInspector.findFieldValue("height", ClassB.class);
		Assert.assertTrue(height.isPresent());
		Assert.assertNull(height.getValue());

		FieldValue<String> color = FieldInspector.findFieldValue("COLOR", ClassB.class);
		Assert.assertTrue(color.isPresent());
		Assert.assertEquals(color.getValue(), "red");

		unknown = FieldInspector.findFieldValue("UNKNOWN", ClassB.class);
		Assert.assertFalse(unknown.isPresent());
	}

	@Test
	public void findFieldValueInClassFromInstance() {

		ClassA classA = new ClassA();

		FieldValue<String> name = FieldInspector.findFieldValue("name", classA);
		Assert.assertTrue(name.isPresent());
		Assert.assertNull(name.getValue());

		classA = new ClassA("toto");
		name = FieldInspector.findFieldValue("name", classA);
		Assert.assertTrue(name.isPresent());
		Assert.assertEquals(name.getValue(), "toto");

		ClassB classB = new ClassB();

		FieldValue<String> color = FieldInspector.findFieldValue("COLOR", ClassB.class);
		Assert.assertTrue(color.isPresent());
		Assert.assertEquals(color.getValue(), "red");

		name = FieldInspector.findFieldValue("name", classB);
		Assert.assertTrue(name.isPresent());
		Assert.assertNull(name.getValue());

		classB = new ClassB("titi", 300);
		name = FieldInspector.findFieldValue("name", classB);
		Assert.assertTrue(name.isPresent());
		Assert.assertEquals(name.getValue(), "titi");
	}

}