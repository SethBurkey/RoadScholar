public class Print{
    public static void PrintWeightAndPred(double[][] weight, int[][] pred, int n){
        System.out.println("  | 0    1    2    3    4    5    6    7");
        System.out.println("__|________________________________________");
        for(int a = 0; a < n; ++a){
            System.out.print(a + " | ");
            for(int b = 0; b < n; ++b){
                String formatted = String.format("%-4s", weight[a][b]).substring(0, 4);
                System.out.print(formatted + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("  | 0    1    2    3    4    5    6    7");
        System.out.println("__|______________________________________");
        for(int a = 0; a < n; ++a){
            System.out.print(a + " | ");
            for(int b = 0; b < n; ++b){
                String formatted = String.format("%-4s", pred[a][b]).substring(0, 4);
                System.out.print(formatted + " ");
            }
            System.out.println();
        }
    }
}