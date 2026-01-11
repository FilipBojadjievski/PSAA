import java.util.Scanner;
import java.lang.Math;
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

public class Zad1Juli {
    public static void main(String[] args) {
        System.out.println('1'+1+1);
                Scanner in=new Scanner(System.in);
        Integer n=in.nextInt();
        SLinkedList<Integer> slist=new SLinkedList<Integer>();
        for (int i=0;i<n;i++){
            slist.insertLast(in.nextInt());
        }
                Integer m=in.nextInt();
        SLinkedList<Integer> slist2=new SLinkedList<Integer>();
        for (int i=0;i<m;i++){
            slist2.insertLast(in.nextInt());
        }

         SLinkedList<Integer> slista=func(slist,slist2);
         slista.printList();
    }
    public static <E> Node<E> flip(Node<E> tail){
        if (tail.next==null){
            return tail;
        }
        Node <E> end=flip(tail.next);
        end.next=tail;
        tail.next=null;
        return tail;
    }
    public static boolean prost(Integer a){
        if (a<=1){
            return false;
        }
        for (int i=2;i*i<=a;i++){
            if (a%i==0){
                return false;
            }
        }
        return true;
    }
    public static SLinkedList<Integer>  func(SLinkedList<Integer> prva,SLinkedList<Integer> vtora){
        Node <Integer> head2=vtora.getHead();
        while (head2.next!=null) {
            head2=head2.next;
        }
        flip(vtora.getHead());
        vtora.setHead(head2);
        Node <Integer> head1=prva.getHead();
        SLinkedList<Integer> slista=new SLinkedList<Integer>();
        while (head1!=null && head2!=null){
            if (prost(Math.abs(head1.data-head2.data))){
                slista.insertLast(Math.min(head1.data,head2.data));
            }   
            head1=head1.next;
            head2=head2.next;
        }
        return slista;
    }
}
