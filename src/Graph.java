import java.util.*;

public class Graph {
    private final TreeMap<Integer, TreeSet<Integer>> graph;
    public Graph(){
        this.graph = new TreeMap<>();
    }

    public Graph(Map<Integer, TreeSet<Integer>> graph){
        this.graph = new TreeMap<>(graph);
    }

    public int getSize(){
        return graph.size();
    }

    public void addArc(int source, int destination){
        graph.computeIfAbsent(source, k -> new TreeSet<>());
        graph.get(source).add(destination);
    }

    public Set<Integer> getDestination(int vertex) {
        return graph.getOrDefault(vertex, new TreeSet<>());
    }

    public Iterable<Integer> getSources(){
        return graph.keySet();
    }

    public Graph reverseGraph(){
        TreeMap<Integer, TreeSet<Integer>> temp = new TreeMap<>();
        for(int i : graph.keySet()){
            for(int j : graph.get(i)){
                temp.computeIfAbsent(j, k -> new TreeSet<>());
                temp.get(j).add(i);
            }
        }
        return new Graph(temp);
    }

    public List<List<Integer>> findStronglyConnectedComponents() {
        List<List<Integer>> sccList = new ArrayList<>();
        Stack<Integer> topology = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Graph transposedGraph = reverseGraph();

        if(getSize() >= 10000){
            return iterativelyFindStronglyConnectedComponents(sccList, topology, visited, transposedGraph);
        }

        for (int vertex : getSources()) {
            if (!visited.contains(vertex)) {
               DFS(vertex, visited, topology);
            }
        }

        visited.clear();
        System.out.println(topology);

        while (!topology.isEmpty()) {
            int vertex = topology.pop();
            if (!visited.contains(vertex)) {
                List<Integer> scc = new ArrayList<>();
                transposedGraph.DFS(vertex, visited, scc);
                sccList.add(scc);
            }
        }
        return sccList;
    }

    private List<List<Integer>> iterativelyFindStronglyConnectedComponents(List<List<Integer>> sccList, Stack<Integer> topology, Set<Integer> visited, Graph transposedGraph){

        for(int vertex : getSources()){
            if(!visited.contains(vertex)){
                iterativeDFS(vertex, visited, topology);
            }
        }

        visited.clear();

        while(!topology.isEmpty()){
            int vertex = topology.pop();
            if(!visited.contains(vertex)){
                List<Integer> scc = new ArrayList<>();
                transposedGraph.iterativeDFS(vertex, visited, scc);
                sccList.add(scc);
            }
        }
        return sccList;
    }

    private void iterativeDFS(int vertex, Set<Integer> visited, Collection<Integer> stack) {
        Stack<Integer> toVisit = new Stack<>();
        toVisit.push(vertex);
        while(!toVisit.isEmpty()){
            int current = toVisit.pop();
            if(!visited.contains(current)){
                visited.add(current);
                stack.add(current);
                for(int i : getDestination(current)){
                    toVisit.push(i);
                }
            }
        }
    }

    private void DFS(int vertex, Set<Integer> visited, Collection<Integer> result) {
        visited.add(vertex);

        for (int neighbor : getDestination(vertex)) {
            if (!visited.contains(neighbor)) {
                DFS(neighbor, visited, result);
            }
        }

        result.add(vertex);
    }

    private void InvertDFS(int vertex, Set<Integer> visited, Collection<Integer> result){
        visited.add(vertex);
        result.add(vertex);

        for(int neighbor : getDestination(vertex)){
            if(!visited.contains(neighbor)){
                InvertDFS(neighbor, visited, result);
            }
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i : graph.keySet()){
            result.append(i).append(" : ").append(graph.get(i)).append("\n");
        }
        return result.toString();
    }
}
