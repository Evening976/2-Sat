import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tester {

    public static boolean check(String filename){
        System.out.println("Testing : " + filename.split("/")[1] );
        Sat test = Sat.readFromFile(filename);
        Graph graph = GraphUtils.createImplicationGraph(test.getLitterals());

        List<List<Integer>> scc = graph.findStronglyConnectedComponents();
        return Sat.checkSat(scc);
    }

    @Test
    public void testSat6() {
        assertTrue(check("data/sat6"));
    }

    @Test
    public void testSat6bis() {
        assertTrue(check("data/sat6bis"));
    }

    @Test
    public void testSat10() {
        assertTrue(check("data/sat10"));
    }

    @Test
    public void testSat100() {
        assertTrue(check("data/sat100"));
    }

    @Test
    public void testSat1000() {
        assertTrue(check("data/sat1000"));
    }

    @Test
    public void testSat10000() {
        assertTrue(check("data/sat10000"));
    }

    @Test
    public void testSat100000() {
        assertTrue(check("data/sat100000"));
    }

    @Test
    public void testUnsat6() {
        assertFalse(check("data/unsat6"));
    }

    @Test
    public void testUnsat20() {
        assertFalse(check("data/unsat20"));
    }

    @Test
    public void testUnsat1000() {
        assertFalse(check("data/unsat1000"));
    }

    @Test
    public void testUnsat10000() {
        assertFalse(check("data/unsat10000"));
    }
}
