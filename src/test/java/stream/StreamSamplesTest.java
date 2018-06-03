package stream;

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
}
