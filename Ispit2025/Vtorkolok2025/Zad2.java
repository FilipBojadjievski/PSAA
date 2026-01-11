import java.util.ArrayList;
import java.util.List;
import java.math.*;
public class Zad2 {
    static SLLHT<String,Film> filmovi=new SLLHT<String,Film>(50);
    static SLLHT<String,Showing> showing=new SLLHT<String,Showing>(50);
    public static void main(String[] args) {
        String []titles={"Inception","Big Lebowski"};
        for (int i=0;i<2;i++){
        filmovi.insert(titles[i],new Film(titles[i],2024));
        }
        showing.insert(titles[0]+"2025-10-5",new Showing(titles[0], "2025-10-5", 10));
        showing.insert(titles[0]+"2024-10-5",new Showing(titles[0], "2024-10-5", 10));
        showing.insert(titles[0]+"2023-10-5",new Showing(titles[1], "2023-10-5", 10));
        findall("Inception", 2024, 2025);
    }
    public static void findall(String title,Integer begin,Integer end){
        if (filmovi.print(title).size()==0){
            System.out.println("Ne postoi");
            return;
        }
        else {

            Film podatoci=filmovi.print(title).get(0);
            if (podatoci.year>end){
                System.out.println("Nema validni datumi");
            }
            else {
                for (Integer i=Math.max(podatoci.year, begin);i<=end;i++){
                    List <Showing> showings=showing.print(title+i.toString());
                    for (int j=0;j<showings.size();j++){
                        System.out.println(showings.get(i).date);
                    }
                }
            }
        }
    }
}
public class Film{
    public String title;
    public String production;
    public Integer year;
    public String genre;
    public float rating;
    Film (String a,Integer b){
        title=a;
        year=b;
    }
    Film(String a, String b, Integer c,String d,float e){
        title=a;
        production=b;
        year=c;
        genre=d;
        rating=e;
    }
}
public class Showing {
    public String title;
    public String date;//yyyy.mm.dd
    public Integer viewers;
    Showing (String a, String b, Integer c){
        title=a;
        date=b;
        viewers=c;
    }
    public String getKey(){
        
        return title+date;
    }
    
}
class Map<K extends Comparable<K>, E> {
    public K key;
    public E value;
    
    public Map(K key, E value) {
        this.key = key;
        this.value = value;
    }
}

class SLLNode<E> {
    public E info;
    public SLLNode<E> next;
    
    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {
    private SLLNode<Map<K, E>>[] htable;
    
    public SLLHT(int n) {
        htable = new SLLNode[n];
        for (int i = 0; i < n; i++) {
            htable[i] = null;
        }
    }
    
    private int hash(String key) {
        
        String value=key.split("-")[0];
        Integer sum=0;
        for (int i=0;i<key.length();i++){
            Integer a=(int)key.charAt(i);
            sum+=a*(key.length()-i);
        }
         return 0;
    }
    
    public SLLNode<Map<K, E>> find(K look) {
        int h = hash((String)look);
        
        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }
        
        return null;
    }
    
    public void insert(K key, E value) {
        Map<K, E> entry = new Map(key, value);
        int h = hash((String)key);
                for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }
        
        htable[h] = new SLLNode<Map<K, E>> (entry, htable[h]);
    }
    
    public void delete (K key) {
        int h = hash((String)key);
        
        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; 
                pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }
    }
    public List <E> print (String shows){
        List<E> temp=new ArrayList<E>();
        int h=hash(shows);
        SLLNode<Map<K, E>> node=htable[h];
        while (node!=null) {
            String val=(String)node.info.key;

            if (shows==val.split("-")[0]){
                temp.add(node.info.value);
            }
            node=node.next;
        }
        return temp;
    }
}