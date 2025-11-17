import java.util.Scanner;
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
public class Grupa121 {
    static Integer n;
 public static void main(String[] args) {
    Scanner in=new Scanner(System.in);
     n=in.nextInt();
    SLinkedList <Integer> [] lista=new SLinkedList [n];
    for (int i=0;i<n;i++){
        lista[i]=new SLinkedList<Integer>();  
        Integer k=in.nextInt();
        for (int j=0;j<k;j++){
            lista[i].insertLast(in.nextInt());
        }  
        
    }
    funct(lista).printList();

 }   
 public static SLinkedList <Integer> funct (SLinkedList <Integer> [] lista){
    Node <Integer> [] a=new Node[n];
    SLinkedList <Integer> slist=new SLinkedList<Integer>();
    for (int i=0;i<n;i++){
        a[i]=lista[i].getHead();
    }
    Boolean izvrsi=true;
    while (izvrsi){
        izvrsi=false;
        for (int i=0;i<n;i++){
            if (a[i]==null){
                continue;
            }
            izvrsi=true;
            slist.insertLast(a[i].data);
            a[i]=a[i].next;
        }
    }
    return slist;
 }
}
