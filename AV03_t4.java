package av03_t4;

class Node<E extends Comparable<E>> {
    protected E data;
    protected int count;
    protected Node<E> next;
    
    public Node() {
        data = null;
        count = 0;
        next = null;
    }
    
    public Node(E data, int count, Node<E> next) {
        this.data = data;
        this.count = count;
        this.next = next;
    }
}

class SLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    
    public Node<E> getHead() {
        return head;
    }
    
    public SLinkedList() {
        head = null;
    }
    
    public void insertFirst(E e) {
        Node<E> first = new Node(e, 0, head);
        head = first;
    }
    
    public void insertAfter(E e, Node<E> n) {
        if (n != null) {
            Node<E> node = new Node(e, 0, n.next);
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
                Node<E> node = new Node(e, 0, n);
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
            
            Node<E> last = new Node(e, 0, null);
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
    
    public void printList() {
        Node<E> tmp = head;
        
        while (tmp.next != null) {
            System.out.print(tmp.data + "(" + tmp.count + ")" + " -> ");
            tmp = tmp.next;
        }
        System.out.println(tmp.data + "(" + tmp.count + ")");
    }
    
    public void reverse() {
        if (head != null) {
            Node<E> curr = head;
            Node<E> prev = null, next = null;
            
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            
            head = prev;
        }
    }
    
    public void deleteDuplicates() {
        if (head != null) {
            Node<E> tmp1 = head;
            Node<E> tmp2 = head.next;
            Node<E> prev = tmp1;
            
            while (tmp1.next != null) {
                while (tmp2 != null) {
                    if (tmp1.data.compareTo(tmp2.data) == 0) {
                        tmp1.count += 1;
                        prev.next = tmp2.next;
                    } else {
                        prev = tmp2;
                    }
                    tmp2 = tmp2.next;
                }
                tmp1 = tmp1.next;
                prev = tmp1;
                tmp2 = tmp1.next;
            }
        }
    }
}


public class AV03_t4 {
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
