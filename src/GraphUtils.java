import java.util.*;

public class GraphUtils {

    public static Graph createImplicationGraph(HashMap<Integer, List<Integer>> literals) {
        Graph implicationGraph = new Graph();

        for (int l1 : literals.keySet()) {
            for (int l2 : literals.get(l1)){
                implicationGraph.addArc(-l1, l2); // ¬l1 => l2
                implicationGraph.addArc(-l2, l1); // ¬l3 => l1
            }
        }
        return implicationGraph;
    }

}
