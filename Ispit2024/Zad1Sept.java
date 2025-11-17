import java.util.Scanner;
import java.lang.Math;
import java.util.Stack;
class Node<E> {
    protected E data;
    protected Node<E> next;
    
    public Node() {
        data = null;
        next = null;
    }
    
    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}

class SLinkedList<E> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public void setHead(Node<E> n) {
        head = n;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, head);
        head = first;
    }
    public void insertAt(int n, E a){
        if (n==0){
            Node <E> nov=new Node<E>(a,getHead());
            setHead(nov);
            return;
        }
        Node <E> head= getHead();
        for (int i=n-1;i>0 && head.next!=null;i--){
            head=head.next;
        }
        Node <E> nov=new Node<E>(a,head.next);
        head.next=nov;

    }
    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, n.next);
            n.next = node;
        } else {
            System.out.println("Error.");
        }
    }
    
    public void insertBefore(E e, Node<E> n) {
        if (head != null) {
            Node<E> tmp = head;
            if (tmp == n) {
                this.insertFirst(e);
                return;
            }
            
            while(tmp.next != n && tmp.next != null) {
                tmp = tmp.next;
            }
            
            if (tmp.next == n) {
                Node<E> node = new Node(e, n);
                tmp.next = node;
            }
        }
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            
            Node<E> last = new Node(e, null);
            tmp.next = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        } else {
            System.out.println("Error.");
        }
    }
    public void deleteAt(int a){
        if (a==0){
            if (head!=null){
            setHead(getHead().next);
            }
            return;
        }
        Node <E> head=getHead();
        int i;
        for (i=a-1;i>0 && head.next!=null;i--){
            head=head.next;
        }
        if (head.next==null){
            head=getHead();
            if (head.next==null){
                setHead(null);
                return;
            }
            while (head.next.next!=null){
                head=head.next;
            }
            head.next=null;
        }
        else {
            head.next=head.next.next;
        }
        return;

    }
    public int size() {
        Node<E> tmp = head;
        int size = 0;
        
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        
        return size;
    }
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data);
    }
}
public class Magacini{
    SLinkedList <Stack<Integer>> magacin=new SLinkedList<Stack<Integer>>();
    Integer height;
    SLinkedList<Integer> free =new SLinkedList<Integer>();
    Node<Integer> freed=null;
    Node<Stack<Integer>> sloboden=null;
    Magacini(Integer a){
        height=a;
        free.insertLast(a);
        Stack <Integer> s=new Stack<Integer>();
        magacin.insertLast(s);
        sloboden=magacin.getHead();
        freed=free.getHead();
    }
    public void pushElement(Integer a){

        if (freed.data==0){
            freed=free.getHead();
            sloboden=magacin.getHead();
            while (freed.next!=null && freed.data==0){
                freed=freed.next;
                sloboden=sloboden.next;
            }
            if (freed.next==null && freed.data==0){
                free.insertLast(height);
                freed=freed.next;
                Stack <Integer> s=new Stack<Integer>();
                magacin.insertLast(s);
                sloboden=sloboden.next;
            }
        }
        sloboden.data.push(a);
        freed.data-=1;

    }
    public void pushStackAt(Integer indx,Stack <Integer> a){
        Stack <Integer> temp=new Stack<Integer>();
        Integer broj=0;
        while (!a.isEmpty()){
            temp.push(a.pop());
            broj+=1;
        }
        while (!temp.isEmpty()){
            a.push(temp.pop());
        }
        if (broj<height){
            free.insertAt(indx, broj);
            magacin.insertAt(indx, temp);
        }
    }
    public void popStackAt(Integer indx){
        free.deleteAt(indx);
        magacin.deleteAt(indx);
        freed=free.getHead();
        sloboden=magacin.getHead();
    }
    public void print(){
        free.printList();
    }
}
public class Zad1Sept{
    public static void main(String[] args) {
        Magacini a=new Magacini(5);
        for (int i=0;i<21;i++){
            a.pushElement(i);
        }
        Stack <Integer> s=new Stack <Integer>();
        for (int i=0;i<2;i++){
            s.push(i);
        }
        a.print();
        a.pushStackAt(0, s);
        a.print();
        a.pushStackAt(2, s);
        a.print();
        a.popStackAt(5);
                for (int i=0;i<7;i++){
            a.pushElement(i);
            a.print();
        }

    }
}