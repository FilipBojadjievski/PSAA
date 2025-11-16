import java.util.Stack;

public class Zad2 {

public static void main(String[] args) {
    Stack <Integer> s=new Stack<Integer>();

   s.push(3);
   s.push(2);
   s.push(1);
   s.push(1);
   s.push(2);
   s.push(3);
   s.push(3);
   s.push(4);
   s.push(2);
   s.push(3);
   s.push(1);
   transform(s);
   while (!s.isEmpty()){
    System.out.println(s.pop());
   }
}
public static void transform(Stack <Integer> a){
    Stack <Integer> temp=new Stack <Integer> ();
    while (a.isEmpty()!=true) {
        temp.push(a.pop());
    }   
    while (temp.isEmpty()!=true){
        Integer cur=temp.pop();
        if (a.isEmpty()){
            a.push(cur);
            continue;
        }
        while (cur>=a.peek()){
            if (cur==a.peek() && cur!=5){
                cur=5;
                a.pop();
                int i=2;
                while (!a.isEmpty() && i>0 && a.peek()!=5){
                        i--;
                        a.pop();
                }
                break;
            }
            else {
                a.pop();
            }
        }
        a.push(cur);
    }
}
}
