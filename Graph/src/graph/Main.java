package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String fileName = "exploracao_lunar_v2.input";
		
		//fileName = "teste_controlado.input";
		
		BufferedReader br = new BufferedReader(new FileReader("C:/" + fileName));
		
		Graph<String> graph = new Graph<>();
		int numberOfLines = 0;
		int actualLine = 0;
		Boolean start = true;
		
		String[][] text = new String[0][0];
		
		while(br.ready()){ 
			String linha = br.readLine();
			if(!linha.equals(" ")) {
				if(actualLine >= numberOfLines) {
					start = linha.split(" ").length == 1;
				}
				if(start) {
					graph = new Graph<>();
					numberOfLines = Integer.parseInt(linha);
					text = new String[numberOfLines][numberOfLines];
					for (int i = 0; i <= numberOfLines; i++) {
						graph.addVertex(Integer.toString(i));
					}
					actualLine = 0;
					start = false;
				} else {
					if(actualLine < numberOfLines) {
						String[] connections = linha.split(" ");
						for (int i = 0; i < connections.length; i++) {
							if(!connections[i].equals(".") && !connections[i].equals("*")) {
								graph.vertices.get(i).setValue(connections[i]);
							}else if (connections[i].equals(".")){
								graph.vertices.get(actualLine).addEdge(graph.vertices.get(i));
							}
						}
					}else {
						try {
							String[] tests = linha.split(" ");
							Vertex<String> origin = new Vertex<String>("0");
							Vertex<String> destiny = new Vertex<String>("1");
							for (Vertex<String> vert : graph.vertices) {
								if(vert.getValue().equals(tests[0])) {
									origin = vert;
								}
								if(vert.getValue().equals(tests[1])) {
									destiny = vert;
								}
							}
							System.out.print(graph.hasPath(origin, destiny) ? "* " : "! ");
						}catch (Exception e){
							System.out.println("Ferrou: " + linha);
						}
					}
					actualLine++;
				}
			}else {
				System.out.println();
			}
		} 
		br.close();
	}
	
	public void Teste() {
		Graph<String> graph = new Graph<>();
		
		int numberOfLines = 0;
		int remainingLines = 0;
		
		for (int i = 0; i < numberOfLines; i++) {
			graph.addVertex(Integer.toString(i));
		}
		
		String[][] text = new String[numberOfLines][numberOfLines];
		
		text = new String[][] {
			{"1", ".", "*", "*"},
			{"*", "2", ".", "*"},
			{"*", "*", "3", "."},
			{"*", "*", "*", "4"}
		};
		
		for (int i = 0; i < text.length; i++) {
			for (int j = 0; j < text[i].length; j++) {
				if(text[i][j] != "." && text[i][j] != "*") {
					graph.vertices.get(i).setValue(text[i][j]);;
				}else if (text[i][j] == "."){
					graph.vertices.get(i).addEdge(graph.vertices.get(j));
				}
			}
		}
		
		String[][] tries =  new String[][] {
			{"1", "2"},
			{"2", "1"},
			{"3", "2"}
		};
		
		for (String[] cs : tries) {
			Vertex<String> origin = new Vertex<String>("0");
			Vertex<String> destiny = new Vertex<String>("1");
			for (Vertex<String> vert : graph.vertices) {
				if(vert.getValue() == cs[0]) {
					origin = vert;
				}
				if(vert.getValue() == cs[1]) {
					destiny = vert;
				}
			}
			System.out.println(graph.hasPath(origin, destiny) ? "*" : "!");
		}
	}
}
