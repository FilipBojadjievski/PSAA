import java.util.Stack;

public class Grupa1 {
    public static void main(String [] args){
        Stack <Integer> s=new Stack <Integer> ();
        s.push(5);
        s.push(3);
        s.push(6);
        s.push(2);
        s.push(1);
        s.push(7);
        sortStack(s);
        while (!s.isEmpty()){
            System.out.println(s.pop());
        }
    }
    public static void sortStack(Stack <Integer> a){
        if (a.isEmpty()==true){
            return;
        }
        Stack <Integer> move1 = new Stack<Integer>();
        Stack <Integer> move2 = new Stack<Integer>();
        Integer min=-1;
        while (a.isEmpty()!=true){
           
            Integer cur=a.pop();
                if (min==-1){
                    min=cur;
                }
            else if (min>cur){

                move1.push(min);
                min=cur;
            }
            else {
                move1.push(cur);
            }
        }
        a.push(min);
        min=-1;

        while (move1.isEmpty()!=true || move2.isEmpty()!=true){
             Boolean izvrseno=false;
             
            while (move1.isEmpty()!=true){
                
                izvrseno=true;
                Integer cur=move1.pop();

            if (min==-1){
                min=cur;
            }
            else if (cur<min){

                move2.push(min);
                min=cur;
            }
            else {
                move2.push(cur);
            }
            }
            if (izvrseno){
                    a.push(min);
        min=-1;
            izvrseno=false;
            }
            while (move2.isEmpty()!=true){
                izvrseno=true;
                Integer cur=move2.pop();
                if (min==-1){
                    min=cur;
                }
                else if (cur<min){

                move1.push(min);
                min=cur;
            }
            else {
                move1.push(cur);
            }
            }
            if (izvrseno){
                    a.push(min);
        min=-1;
            izvrseno=false;
            }
        }
        
    }
}
