import java.util.Stack;

public class Magacin <E> {
    public Stack <E> s=new Stack<E>();
    void push(E a){
        s.push(a);
    }
    void pop(){
        Stack <E> passed=new Stack<E>();
        Stack <E> temp=new Stack<E>();
        Integer maxcount=0;
        Integer maxposition=0;
        Integer currentposition=0;
        while (!s.isEmpty()){
            E cur=s.pop();
            Integer count=0;
            while (s.isEmpty()!=true){
                E tem=s.pop();
                if (tem==cur){
                    count+=1;
                }
                temp.push(tem);
            }
            while (!temp.isEmpty()){
                s.push(temp.pop());
            }
            if (count>maxcount){
                maxcount=count;
                maxposition=currentposition;
            }
            currentposition+=1;
            passed.push(cur);
        }
        while (!passed.isEmpty()){
            E cur=passed.pop();
            currentposition-=1;
            if (currentposition==maxposition){
                continue;
            }
            
            s.push(cur);
        }
    }
}
public class Grupa12 {
    public static void main(String[] args) {
        Magacin <Integer> s=new Magacin<Integer> ();
        s.push(2);
        s.push(3);
        s.push(6);
        s.push(2);
        s.push(3);
        s.push(7);
        s.pop();
        while (!s.s.isEmpty()){
            System.out.println(s.s.pop());
        }
    }
}
