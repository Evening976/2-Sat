import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sat {
    private final int size;
    private final int clauses;
    private final HashMap<Integer, List<Integer>> litterals;
    public Sat(int size, int clauses, HashMap<Integer, List<Integer>> litterals){
        this.size = size;
        this.clauses = clauses;
        this.litterals = litterals;
    }

    public HashMap<Integer, List<Integer>> getLitterals() {
        return litterals;
    }

    public static Sat readFromFile(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            HashMap<Integer, List<Integer>> litterals;
            int size, clauses;

            if(line.startsWith("c")){
                System.out.println("Commentaire : " + line.replaceFirst("c", ""));
                line = reader.readLine();
            }

            line = line.replaceAll("[\\D]", "");
            size = Integer.parseInt(String.valueOf(line.charAt(0)));
            clauses = Integer.parseInt(String.valueOf(line.charAt(1)));
            litterals = new HashMap<>(size * 2);

            while((line = reader.readLine()) != null){
                String[] split = line.split(" ");
                litterals.computeIfAbsent(Integer.parseInt(split[0]), k -> new ArrayList<>());
                litterals.get(Integer.parseInt(split[0])).add(Integer.parseInt(split[1]));
            }

            return new Sat(size, clauses, litterals);
        }
        catch (Exception e){
            System.out.println("Error reading file : " + e.getMessage());
        }
        return null;
    }


    public static boolean checkSat(List<List<Integer>> scc){
        for(List<Integer> component : scc){
            for(int i : component){
                if(component.contains(-i) && i != 0){
                    System.out.println("Resultat :     Unsatisfiable");
                    return false;
                }
            }
            
        }
        System.out.println("Resultat :     Satisfiable");
        return true;
    }

    @Override
    public String toString() {
        return size +"-Sat {" +
                "clauses = " + clauses +
                ", litterals=" + litterals +
                '}';
    }
}
