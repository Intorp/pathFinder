package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Graph<E> {
	
	public List<Vertex<E>> vertices = new ArrayList<>();

	public Path<E> findShortestPath(Vertex<E> source, Vertex<E> dest) {
		List<Path<E>> allPaths = findAllPaths(source, dest);
		Path<E> result = allPaths.get(0);
		for (Path<E> path : allPaths) {
			if(path.getWeight().compareTo(result.getWeight()) < 0) {
				result = path;
			}
		}
		return result;
	}
	
	public Path<E> findShortestPathLambda(Vertex<E> source, Vertex<E> dest) {
		List<Path<E>> allPaths = findAllPaths(source, dest);

		return allPaths
				.stream()
				.min((a,b) -> a.getWeight().compareTo(b.getWeight()))
				.get();
	}	
	
	public List<Path<E>> findAllPaths(
			Vertex<E> source, 
			Vertex<E> target) {
		
		List<Path<E>> paths = new LinkedList<Path<E>>();
		findPaths(source, target, paths, new Path<E>());
		return paths;
	}

	private void findPaths(
			Vertex<E> current, 
			Vertex<E> target, 
			List<Path<E>> paths,
			Path<E> path) {

		path.getVertices().add(current);
		if (current.equals(target)) {
			paths.add(path.getCopy());
			path.setWeight(0);
			path.getVertices().remove(current);
			return;
		}

		for (Vertex<E>.Edge edge : current.getNeighbors()) {
			path.setWeight(path.getWeight() + edge.getWeight());
			findPaths(edge.getDestination(), target, paths, path);
		}

		path.getVertices().remove(current);

	}	
	
	public boolean hasPath(
			Vertex<E> source, 
			Vertex<E> destination) {

		Set<Vertex<E>> visited = new HashSet<>();
		Queue<Vertex<E>> queue = new LinkedList<>();
		queue.add(source);
		visited.add(source);

		while (!queue.isEmpty()) {
			Vertex<E> vertex = queue.poll();
			if (vertex.equals(destination)) {
				return true;
			}
			Set<Vertex<E>.Edge> neighbors = vertex.getNeighbors();
			for (Vertex<E>.Edge edge : neighbors) {
				Vertex<E> neighbor = edge.getDestination();
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
		return false;
	}	

	public void traverse(Vertex<Integer> source) {
		Set<Vertex<Integer>> visited = new HashSet<>();
		Queue<Vertex<Integer>> queue = new LinkedList<>();
		queue.add(source);
		visited.add(source);

		while (!queue.isEmpty()) {
			Vertex<Integer> vertex = queue.poll();
			System.out.println(vertex.getValue());
			Set<Vertex<Integer>.Edge> neighbors = vertex.getNeighbors();
			for (Vertex<Integer>.Edge edge : neighbors) {
				Vertex<Integer> neighbor = edge.getDestination();
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
	}	
	
	public Vertex<E> addVertex(E value) {
		Vertex<E> vertex = new Vertex<>(value);
		vertices.add(vertex);
		return vertex;
	}

}
