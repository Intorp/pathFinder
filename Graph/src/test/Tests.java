package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import graph.Graph;
import graph.Path;
import graph.Vertex;

public class Tests {

	@Test
	public void ExistPathTest() {
		Graph<Character> graph = new Graph<>();
		Vertex<Character> m = graph.addVertex('M');
		Vertex<Character> l = graph.addVertex('L');
		Vertex<Character> r = graph.addVertex('R');
		Vertex<Character> g = graph.addVertex('G');
		Vertex<Character> d = graph.addVertex('D');
		Vertex<Character> f = graph.addVertex('F');
		Vertex<Character> o = graph.addVertex('O');

		m.addEdge(l, 40);
		l.addEdge(r, 5);
		g.addEdge(r, 30);
		d.addEdge(g, 100);
		d.addEdge(f, 10);
		f.addEdge(r, 75);
		f.addEdge(m, 25);
		f.addEdge(o, 15);
		o.addEdge(m, 55);
		
		Assert.assertTrue(graph.hasPath(m, r));
		Assert.assertFalse(graph.hasPath(m, o));
	}
	
	@Test
	public void CountPathTest() {
		Graph<Character> graph = new Graph<>();
		Vertex<Character> m = graph.addVertex('M');
		Vertex<Character> l = graph.addVertex('L');
		Vertex<Character> r = graph.addVertex('R');
		Vertex<Character> g = graph.addVertex('G');
		Vertex<Character> d = graph.addVertex('D');
		Vertex<Character> f = graph.addVertex('F');
		Vertex<Character> o = graph.addVertex('O');

		m.addEdge(l, 40);
		l.addEdge(r, 5);
		g.addEdge(r, 30);
		d.addEdge(g, 100);
		d.addEdge(f, 10);
		f.addEdge(r, 75);
		f.addEdge(m, 25);
		f.addEdge(o, 15);
		o.addEdge(m, 55);
		
		List<Path<Character>> list = graph.findAllPaths(m, o);
		List<Path<Character>> list2 = graph.findAllPaths(m, r);
		
		Assert.assertTrue(list.size() == 0);
		Assert.assertTrue(list2.size() > 0);
	}
	
	@Test
	public void ShortestPathTest() {
		Graph<Character> graph = new Graph<>();
		Vertex<Character> m = graph.addVertex('M');
		Vertex<Character> l = graph.addVertex('L');
		Vertex<Character> r = graph.addVertex('R');
		Vertex<Character> g = graph.addVertex('G');
		Vertex<Character> d = graph.addVertex('D');
		Vertex<Character> f = graph.addVertex('F');
		Vertex<Character> o = graph.addVertex('O');

		m.addEdge(l, 40);
		l.addEdge(r, 5);
		g.addEdge(r, 30);
		d.addEdge(g, 100);
		d.addEdge(f, 10);
		f.addEdge(r, 75);
		f.addEdge(m, 25);
		f.addEdge(o, 15);
		o.addEdge(m, 55);
		
		Path<Character> list = graph.findShortestPath(d, r);
		
		Assert.assertTrue(list.getWeight() == 70);
	}
	
}
