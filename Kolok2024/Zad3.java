import java.util.Scanner;

public class Zad3 {
    static Integer n;
    static Integer m;
    static Integer [][]a;
    public static Integer value (Integer i, Integer j){
        if (i<0 || j<0){
            return 0;
        }
        if (i>=0 && j>=0){
        return a[i][j];
        }
        return 0;
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        n=in.nextInt();
        m=in.nextInt();
        a=new Integer [n][m];
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                a[i][j]=in.nextInt();
                if (i>0){
                    a[i][j]+=a[i-1][j];
                }
                if (j>0){
                    a[i][j]+=a[i][j-1];
                }
                if (i>0 && j>0) {
                    a[i][j]-=a[i-1][j-1];
                }
                // System.out.print(i);
                // System.out.print(" ");
                // System.out.print(j);
                // System.out.print(" ");
                // System.out.println(a[i][j]);
            }
        }

        Integer brojka=0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                for (int z=brojka;i+z<n && j+z<n;z++){
                    Integer suma=value(i+z,j+z)+value(i-1,j-1)-value(i-1,j+z)-value(i+z,j-1);
                    if (suma==(z+1)*(z+1)){
                        brojka=z;
                    }
            }
            }
        }
        System.out.println(brojka);
    }
}
