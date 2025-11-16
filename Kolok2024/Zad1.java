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
public class Patnik{
    private String ime;
    private String prezime;
    private boolean prva_klasa=false;
    Patnik(String a, String b, boolean c){
        ime=a;
        prezime=b;
        prva_klasa=c;
    }
    Patnik(String a, String b){
        ime=a;
        prezime=b;
    }
    public Boolean klasa(){
        return prva_klasa;
    }
        public void print(){
        System.out.println(ime+" "+prezime);
    }
}
public class Vagon {
    public Patnik [] patnici=new Patnik[20];
    private Integer br_na_Patnici=0;
    public boolean prva_klasa=false;
    Vagon (boolean b){
        prva_klasa=b;
    }
    void addPatnik(Patnik a){
        patnici[br_na_Patnici]=a;
        br_na_Patnici+=1;
    }
    public boolean klasa (){
        return prva_klasa;
    }
    public boolean isFull(){
        return br_na_Patnici==20;
    }
    void print(){
        System.out.println(br_na_Patnici);
        System.out.println(prva_klasa);
        for (int i=0;i<br_na_Patnici;i++){
            patnici[i].print();
        }
    }

}
public class Voz{
    SLinkedList <Vagon> vagoni=new SLinkedList<Vagon> ();
    Integer br_na_vagoni;
    Integer br_na_prva;
    Node <Vagon> prva=null;
    Node <Vagon> obicen=null;
    Voz(Integer a, Integer b){
        br_na_prva=b;
        br_na_vagoni=a;
        for (int i=0;i<br_na_vagoni;i++){
            Vagon temp=new Vagon(i<br_na_prva);
            vagoni.insertLast(temp);
        }
        prva=vagoni.getHead();
        obicen=vagoni.getHead();
        for (int i=0;i<br_na_prva;i++){
            obicen=obicen.next;
        }
    }
    void getPrva(){
        prva=prva.next;
        if (prva.data.klasa()!=true){
            prva=vagoni.getHead();
            while (prva.data.isFull()==true && prva.next!=null){
                prva=prva.next;
            }
            if (prva.data.klasa()!=true || prva.data.isFull()){
                prva=null;
            }
        }
    }
    void getVtora(){
                obicen=obicen.next;
        if (obicen.data.klasa()!=true){
            obicen=vagoni.getHead();
            for (int i=0;i<br_na_prva;i++){
            obicen=obicen.next;
            }
            while (obicen.data.isFull()==true && obicen.next!=null){
                obicen=obicen.next;
            }
            if (obicen.data.isFull()){
                obicen=null;
            }

        }
    }
    void dodajPatnik (Patnik a){
        if (a.klasa() && prva!=null){
                prva.data.addPatnik(a);
                getPrva();
        }
        else if (a.klasa()==false && obicen!=null){
            obicen.data.addPatnik(a);
            getVtora();
        }
    }
    void print(){
        Node <Vagon> v= vagoni.getHead();
        for (int i=0;i<br_na_vagoni;i++){  
            v.data.print();
            v=v.next;
        }
    }
}
public class Zad1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Integer vagoni=in.nextInt();
        Integer prva=in.nextInt();
        Voz v=new Voz(vagoni, prva);
        v.print();
        for (int i=0;i<vagoni*20;i++){
            String ime=in.next();
            String prezime=in.next();
            String klasa=in.next();
            Patnik p=new Patnik(ime, prezime,klasa.equals("1"));
            v.dodajPatnik(p);
            v.print();
        }
    }
}
