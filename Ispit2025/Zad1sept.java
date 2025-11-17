import java.util.Scanner;
import java.lang.Math;
public class Zad1sept{
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Integer n=in.nextInt();
        Integer [][] a= new Integer[n][];
        for (int i=0;i<n;i++){
            a[i]=new Integer[i+1];
            for (int j=0;j<=i;j++){
                a[i][j]=in.nextInt();
            }
        }
        for (int i=n-2;i>=0;i--){
            for (int j=i;j>=0;j--){
                Integer val=a[i+1][j];
                for (int z=j+1;z<=i+1;z++){
                    val=Math.min(val,a[i+1][z]);
                }
                a[i][j]+=val;
            }
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<=i;j++){
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }

    }
}