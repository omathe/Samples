package metadata;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.Assert;
import org.junit.Test;

import fr.omathe.metadata.classes.ClassTools;

public class ClassToolsTest {

	public static Map<Long, Map<String, Integer>> map = new HashMap<>();

	@Test
	public void findAllClasses() {

		Type type = String.class;
		Set<Class<?>> classes = ClassTools.findAllClasses(new HashSet<>(), type, c -> true);
		classes.stream().forEach(System.out::println);
		Assert.assertEquals(classes.size(), 1);
		Assert.assertTrue(classes.contains(String.class));

		type = TypeUtils.parameterize(List.class, String.class);
		classes = ClassTools.findAllClasses(new HashSet<>(), type, c -> true);
		classes.stream().forEach(System.out::println);
		Assert.assertEquals(classes.size(), 2);
		Assert.assertTrue(classes.contains(List.class));
		Assert.assertTrue(classes.contains(String.class));

		type = TypeUtils.parameterize(Map.class, Long.class, TypeUtils.parameterize(Map.class, String.class, Integer.class));
		classes = ClassTools.findAllClasses(new HashSet<>(), type, c -> true);
		classes.stream().forEach(System.out::println);
		Assert.assertEquals(classes.size(), 4);
		Assert.assertTrue(classes.contains(Map.class));
		Assert.assertTrue(classes.contains(Long.class));
		Assert.assertTrue(classes.contains(String.class));
		Assert.assertTrue(classes.contains(Integer.class));

		classes = ClassTools.findAllClasses(new HashSet<>(), null, c -> true);
		Assert.assertTrue(classes.isEmpty());

	}

	@Test
	public void typeAsString() {

		Type type = String.class;
		String typeAsString = ClassTools.typeAsString(type);
		System.out.println("typeAsString = " + typeAsString);
		Assert.assertEquals(typeAsString, "String");

		type = TypeUtils.parameterize(List.class, String.class);
		typeAsString = ClassTools.typeAsString(type);
		System.out.println("typeAsString = " + typeAsString);
		Assert.assertEquals(typeAsString, "List<String>");

		type = TypeUtils.parameterize(Map.class, Long.class, TypeUtils.parameterize(Map.class, String.class, Integer.class));
		System.out.println("type = " + type);
		typeAsString = ClassTools.typeAsString(type);
		System.out.println("typeAsString = " + typeAsString);
		Assert.assertEquals(typeAsString, "Map<Long,Map<String,Integer>>");

		type = TypeUtils.parameterize(List.class, TypeUtils.wildcardType().build());
		System.out.println("type = " + type);
		typeAsString = ClassTools.typeAsString(type);
		System.out.println("typeAsString = " + typeAsString);
		Assert.assertEquals(typeAsString, "List<?>");

		typeAsString = ClassTools.typeAsString(null);
		Assert.assertEquals(typeAsString, "");
	}

}