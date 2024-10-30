import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class roadsscholar {
	/**
	 * @param args
	 * This program reads the input from a file and saves each edge in a weight matrix
     * and runs Floyd Warshall's APSP algorithm on it. Next, given the sign edge and distance,
     * it calculates the distance to each city that the edge with the sign is used in the 
     * optimal solution for that city. Lastly, it prints the contents of each sign.
     * Each sign has the name and distance per city in which it is used in the optimal path.
     **/
	public static void main(String[] args){
		try { //parsing input
			File file = new File("input.txt");
			Scanner sc = new Scanner(file);
            int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
            double[][] weight = new double[n][n];
            int[][] pred = new int[n][n];
            for(int x = 0; x < n; ++x){ //initialize Floyd Warshall's APSP weight and pred matrixies
                for(int y = 0; y < n; ++y){
                    weight[x][y] = (x == y) ?  0 : Double.MAX_VALUE;
                    pred[x][y] = x;
                }
            }
            for(int x = 0; x < m; ++x){ //fill in the known data from input
                int y = sc.nextInt(), z = sc.nextInt();
                double dist = sc.nextDouble();
                weight[y][z] = dist;
                weight[z][y] = dist;
            }
            HashMap<Integer, String> cities = new HashMap<Integer, String>();
            for(int x = 0; x < k; ++x){ //get the cities from input
                cities.put(sc.nextInt(), sc.next());
            }
            Sign[] signs = new Sign[sc.nextInt()];
            for(int x = 0; x < signs.length; ++x){ //get the signs from the input
                signs[x] = new Sign(sc.nextInt(), sc.nextInt(), sc.nextDouble());
            }
            for(int x = 0; x < n; ++x){ //Floyd Warshall's APSP
                for(int y = 0; y < n; ++y){
                    for(int z = 0; z < n; ++z){
                        if(weight[y][x] + weight[x][z] < weight[y][z]){
                            weight[y][z] = weight[y][x] + weight[x][z];
                            pred[y][z] = pred[x][z];
                        }
                    }
                }
            }
            for(Sign sign : signs){
                for(int city : cities.keySet()){
                    int predecessor = pred[city][sign.i1];
                    if(predecessor == sign.i2){ //if road is used in the optimal path
                        double distance = weight[city][sign.i1];
                        while (predecessor != pred[predecessor][sign.i1]){ //until back to city
                            distance += weight[predecessor][sign.i1];
                            predecessor = pred[predecessor][sign.i1];
                        } 
                        sign.cities.put(cities.get(city), (int) Math.round(distance - sign.dist));
                    } //subtract the distance from the start intersection to the sign ^^^^^^^^^
                }
            }
            for(Sign sign : signs){
                //https://stackoverflow.com/questions/48818051/how-to-sort-a-hashmap-first-by-value-and-then-by-key-in-case-of-duplicates
                LinkedHashMap<String, Integer> sortedCities = sign.cities.entrySet().stream() //sorting a hashmap by value then key
                    .sorted(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                for(String city : sortedCities.keySet()){//print each city with its distance
                    System.out.print(city + " ");
                    System.out.println(sign.cities.get(city));
                }
                System.out.println();
            }
            sc.close();
        } catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
    }
}