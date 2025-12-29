import java.util.Scanner;
import java.util.Stack;

public class Zad2 {
    static BSTree<Integer> tree;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Integer Level=in.nextInt();
        Integer Value=in.nextInt();
        tree=new BSTree<Integer>(10);
        Integer a[]={6,12,5,3,7,9,11};
        for (int i=0;i<a.length;i++){
            tree.insert(a[i],tree.root);
        }
        System.out.println(findNode(Value, Level));
        
    }
    public static BNode <Integer> findNode (Integer value,Integer level){
        Stack<Tuple> s = new Stack();
        Integer depth=1;
        Tuple p = new Tuple(tree.root,1);
        Boolean found=false;


        while (true) {
            while (p.val != null && depth<=level) { 
                //System.out.println(Integer.toString(depth)+" "+Integer.toString(p.val.info));
                s.push(p);
                p = new Tuple(p.val.left,++depth);

            }
            
            if (s.isEmpty()) {
                break;
            }

            p = s.pop();
            depth=p.depth;
            System.out.println(Integer.toString(p.depth)+" "+Integer.toString(p.val.info));
            if (depth==level && found==true){
                return p.val;
            }
            
            if (depth==level && p.val.info==value){
                found=true;
            }
            p = new Tuple(p.val.right,++depth);
            
        }
        return null;
    }
}
class Tuple{
    public BNode<Integer> val;
    public Integer depth;
    public Tuple(BNode<Integer> a,Integer b){
        val=a;
        depth=b;
    }
}   
class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left, right;
    
    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
    
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;
    
    public BSTree() {
        root = null;
    }
    
    public BSTree(E info) {
        root = new BNode(info);
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.info + ", ");
            inorder(r.right);
        }
    }
    
    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode(info);
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) { //left
            r.left = insert(info, r.left);
        } else if (comp > 0) { //right
            r.right = insert(info, r.right);
        } else {
            // ne pravi nishto
        }
        
        return r;
    }
    
    public boolean contains(E info, BNode<E> r) {
        if (r == null) {
            return false;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return true;
        }
    }
    
    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }
        
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // brishi go jazolot info
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else if (r.right != null) {
                    return r.right;
                } else {
                    return null;
                }
            }
        }
        
        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }
}
