import java.util.Scanner;
import java.util.Stack;
public class History{
    private Stack <String> pozadi=new Stack<String>();
    private Stack <String> posle=new Stack<String>();
    public History(){
        pozadi.push("google.com");
    }
    public String moveBack(Integer x){
        if (x<0){
            return "Error";
        }
        for (int i=0;i<x && pozadi.isEmpty()!=true;i++){
            posle.push(pozadi.pop());
        }
        if(pozadi.isEmpty()==true){
            pozadi.push(posle.pop());
        }
        return pozadi.peek();
    }
    public String moveForward(Integer x){
        if (x<0){
            return "Error";
        }
        for (int i=0;i<x && posle.isEmpty()!=true;i++){
            pozadi.push(posle.pop());
        }
        return pozadi.peek();
    }
    public void newTab(String a){
        pozadi.push(a);
        while (posle.isEmpty()!=true){
            posle.pop();
        }
    }
    public String currentTab(){
        return pozadi.peek();
    }

}
public class Zad1{
public static void main(String args [] ){
    History h=new History();
    System.out.println(h.currentTab());
    Scanner s=new Scanner(System.in);

    while (true){
    String command=s.next();

    if (command.charAt(0)=='n'){
            
        command=s.next();
        h.newTab(command);
    }
    else if (command.charAt(0)=='m'){

        Integer places=s.nextInt();
        if (places>0){
            h.moveForward(places);
        }    
        else {
            h.moveBack(-places);
        }
    }
    System.out.println(h.currentTab()); 
    }
}
}