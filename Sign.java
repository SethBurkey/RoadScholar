import java.util.HashMap;

public class Sign {
    int i1;
    int i2;
    double dist;
    HashMap<String, Integer> cities;
    Sign(int i1, int i2, double dist){
        this.i1 = i1;
        this.i2 = i2;
        this.dist = dist;
        this.cities = new HashMap<String, Integer>();
    }
}