package fr.omathe.lambda;

import java.time.Instant;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class LambdaSamples {

	public static void test() {

		final Integer count = 0;
		final Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c");

		stream
				.forEach(s -> {
					Integer c2 = count;
					c2++;
					System.out.println(s);
					System.out.println(c2);
				});
	}

	public Object fromStringToLambda(final String function, final String value) throws ScriptException {

		final long t0 = Instant.now().toEpochMilli();
		
		final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

		@SuppressWarnings("unchecked")
		final Function<Object, Object> f = (Function<Object, Object>) engine.eval(function);
		final Object object = f.apply(value);
		
		System.out.println("duration = " + (Instant.now().toEpochMilli() - t0) + " ms");
		return object;
	}

	public static void main(final String[] args) throws ScriptException {

		LambdaSamples.test();
		
		final LambdaSamples lambdaSamples =  new LambdaSamples();
		System.out.println(lambdaSamples.fromStringToLambda("new java.util.function.Function(function(x) 3 * x + 1)", "10"));
	}

}
