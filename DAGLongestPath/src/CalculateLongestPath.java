import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateLongestPath {

	public static void main(String[] args) {
		Vertex startVertex = new Vertex(1L);
		List<Edge> allEdges = new ArrayList<Edge>();
		//Given a DAG and a vertex, calculate the longest directed path from that vertex
		
		processVertex(0, startVertex, allEdges);
		
		System.out.println("Longest directed path:" + processVertex(0, startVertex, allEdges));
	}
	
	private static List<Edge> findEdges(List<Edge> allEdges, Vertex startVertex) {
		//Find edges
		return allEdges.stream().filter(e -> e.getFrom() == startVertex).collect(Collectors.toList());
	}
	
	private static int processVertex(int currentPath, Vertex vertex, List<Edge> allEdges) {
		List<Edge> startEdges = findEdges(allEdges, vertex);
		
		int longestPath = currentPath;
		//Traverse the path starting at each edge
		for(Edge edge: startEdges) {
			if(edge.getTo() != null) {
				int pathLength = processVertex(currentPath + 1, edge.getTo(), allEdges);
				//If this path was the longest seen so far, store it as the longest path
				if(pathLength > longestPath) {
					longestPath = pathLength;
				}
			}
		}
		return longestPath;
	}

}
