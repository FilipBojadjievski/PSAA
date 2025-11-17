import java.util.Scanner;

class Node<E> {
    protected E data;
    protected Node<E> prev, next;
    
    public Node() {
        data = null;
        prev = next = null;
    }
    
    public Node(E data, Node<E> prev, Node<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class DLinkedList<E extends Comparable <E>> {
    private Node<E> head, tail;
    
    public Node<E> getHead() {
        return head;
    }
    
    public Node<E> getTail() {
        return tail;
    }
    
    public DLinkedList() {
        head = null;
        tail = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, null, head);
        
        if (head != null) {
            head.prev = first;
        }
        
        if (tail == null) {
            tail = first;
        }
        
        head = first;
    }
    
    public void insertLast(E e) {
        if (head != null) {
            Node<E> last = new Node(e, tail, null);
            tail.next = last;
            tail = last;
        } else {
            this.insertFirst(e);
        }
    }
    
    public void printList() {
        Node<E> tmp = head;
        if (tmp != null) {
            while (tmp.next != null) {
                System.out.print(tmp.data + " <-> ");
                tmp = tmp.next;
            }
            System.out.println(tmp.data);
        }
    }
    public Node <E> sort (Node <E> head, Node <E> tail){
        if (head==tail){
            Node <E> temp=new Node<E>();
            temp.data=head.data;
            return temp;
        }
        Node<E> mid1=head;
        Node<E> mid2=tail;
        while (mid1.next!=mid2 && mid1!=mid2){
            mid1=mid1.next;
            mid2=mid2.prev;
        }
        if (mid1==mid2){
            mid1=mid1.prev;
        }
        Node <E> headfirst=sort(head, mid1);
        Node <E> headsecond=sort(mid2,tail);
        Node <E> newHead=null;
        Node <E> temp=newHead;
        while (headfirst!=null && headsecond!=null){
            if (newHead==null){
                newHead=new Node<E>();
                temp=newHead;
            }
            else {
            temp.next=new Node <E> ();
            temp.next.prev=temp;
            temp=temp.next;
            }
            if (headfirst.data.compareTo(headsecond.data)<0){
                temp.data=headfirst.data;
                headfirst=headfirst.next;
            }
            else {
                temp.data=headsecond.data;
                headsecond=headsecond.next;
            }

        }
        while (headfirst!=null){
                            temp.next=new Node <E> ();
            temp.next.prev=temp;
            temp=temp.next;
                temp.data=headfirst.data;
                headfirst=headfirst.next;

        }
                while (headsecond!=null){
                                    temp.next=new Node <E> ();
            temp.next.prev=temp;
            temp=temp.next;
                temp.data=headsecond.data;
                headsecond=headsecond.next;

        }
        this.head=newHead;
        while (newHead.next!=null){
            if (newHead.next.data==newHead.data){
                temp=newHead.next;
                newHead.next=temp.next;
                if (temp.next!=null){
                temp.next.prev=newHead;
                }
            }
            if (newHead.next==null){
                break;
            }
            newHead=newHead.next;
        }
        this.tail=newHead;

        return this.head;

    }
    
}
public class Zad1juni {
    public static void zbir (Integer n, DLinkedList<Integer> dl){
            Node <Integer> head=dl.getHead();
        Node <Integer> second=dl.getTail();
        if (head==null){
            return;
        }
        if (head.next==null){
            return;
        }

        while (head!=second){
            if (head.data+second.data==n){
                System.out.print(head.data);
                System.out.print(" ");
                System.out.println(second.data);
                head=head.next;
            }
            else if (head.data+second.data>n){
                second=second.prev;
            }
            else {
                head=head.next;
            }
        }
    }
    public static void main(String[] args) {
            DLinkedList <Integer> dlista=new DLinkedList<Integer>();
    Scanner in=new Scanner(System.in);
    Integer n;
    n=in.nextInt();
    for (int i=0;i<n;i++){
        dlista.insertLast(in.nextInt());
    }
    
    Node A=dlista.sort(dlista.getHead(),dlista.getTail());
    dlista.printList();
    zbir(5,dlista);
    }

}
