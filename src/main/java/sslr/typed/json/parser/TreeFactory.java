package sslr.typed.json.parser;

import java.util.ArrayList;
import java.util.List;

import sslr.typed.json.parser.tree.ArrayTree;
import sslr.typed.json.parser.tree.JsonTree;
import sslr.typed.json.parser.tree.ObjectTree;
import sslr.typed.json.parser.tree.PairTree;
import sslr.typed.json.parser.tree.TypeTree;
import sslr.typed.json.parser.tree.ValueTree;
import sslr.typed.json.parser.tree.impl.ArrayTreeImpl;
import sslr.typed.json.parser.tree.impl.JsonTreeImpl;
import sslr.typed.json.parser.tree.impl.ObjectTreeImpl;
import sslr.typed.json.parser.tree.impl.PairImpl;
import sslr.typed.json.parser.tree.impl.ValueTreeImpl;

import com.sonar.sslr.api.typed.Optional;


public class TreeFactory {

	// Helpers

	public static class Triple<T, U, V> {
		private final T first;
		private final U second;
		private final V third;

		public Triple(T first, U second, V third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public T first() {
			return first;
		}

		public U second() {
			return second;
		}

		public V third() {
			return third;
		}
	}

	private <T, U, V> Triple<T, U, V> newTriple(T first, U second, V third) {
		return new Triple<>(first, second, third);
	}

	public <T, U, V> Triple<T, U, V> newTriple1(T first, U second, V third) {
		return newTriple(first, second, third);
	}

	public <T, U, V> Triple<T, U, V> newTriple2(T first, U second, V third) {
		return newTriple(first, second, third);
	}

	public static class Tuple<T, U> {
		private final T first;
		private final U second;

		public Tuple(T first, U second) {
			this.first = first;
			this.second = second;
		}

		public T first() {
			return first;
		}

		public U second() {
			return second;
		}
	}

	private <T, U> Tuple<T, U> newTuple(T first, U second) {
		return new Tuple<>(first, second);
	}

	public <T, U> Tuple<T, U> newTuple1(T first, U second) {
		return newTuple(first, second);
	}

	public <T, U> Tuple<T, U> newTuple2(T first, U second) {
		return newTuple(first, second);
	}

	public JsonTree json(TypeTree type) {
		return new JsonTreeImpl(type);
	}

	public ObjectTree object(
			InternalSyntaxToken openCurlyBraceToken,
			InternalSyntaxToken openWhitespaceToken,
			Optional<Tuple<PairTree, Optional<List<Triple<InternalSyntaxToken, InternalSyntaxToken, PairTree>>>>> tuple,
			InternalSyntaxToken closeCurlyBraceToken, InternalSyntaxToken closeWhitespaceToken) {
		List<PairTree> pairs = new ArrayList<>();
		if (tuple.isPresent()) {
			PairTree first = tuple.get().first();
			pairs.add(first);
			// TODO use second()
		}
		return new ObjectTreeImpl(openCurlyBraceToken, pairs, closeCurlyBraceToken);
	}

	public ArrayTree array(
			InternalSyntaxToken openBracketToken,
			InternalSyntaxToken openWhitespaceToken,
			Optional<Tuple<ValueTree, Optional<List<Triple<InternalSyntaxToken, InternalSyntaxToken, ValueTree>>>>> tuple,
			InternalSyntaxToken closeBracketToken, InternalSyntaxToken closeWhitespaceToken) {
		List<ValueTree> elements = new ArrayList<>();
		if (tuple.isPresent()) {
			ValueTree value = tuple.get().first();
			elements.add(value);
			
			if (tuple.get().second().isPresent()) {
				value = tuple.get().first();
				elements.add(value);
			}
		}
		
		return new ArrayTreeImpl(openBracketToken, elements, closeBracketToken);
	}

	public PairTree pair(InternalSyntaxToken name, InternalSyntaxToken colonToken,
			InternalSyntaxToken whitespaceToken, ValueTree value) {
		return new PairImpl(name, value);
	}

	public ValueTree value(Tree value) {
		return new ValueTreeImpl(value);
	}

}
