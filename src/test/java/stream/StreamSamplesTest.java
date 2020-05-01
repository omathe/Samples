package stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import fr.omathe.stream.StreamSamples;

public class StreamSamplesTest {

	@Test
	public void pairs() {

		Stream<String> stream = Stream.of("a", "b", "c", "d");

		String pairs = StreamSamples.pairs(stream);
		System.out.println(pairs);
	}
	
	@Test
	public void displayIndex() {
		
		List<Integer> indexes = StreamSamples.displayIndex();
		assertTrue(indexes.size() == 3);
	}
}
