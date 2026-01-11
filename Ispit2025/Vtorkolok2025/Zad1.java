import java.util.Stack;
public class Zad1 {
    static Integer maxdepth=0;
    static Integer suma=0;
    public static void main(String [] args){
        BTree<Integer> tree=new BTree<Integer>(10);
        Integer [] arr1={6,4,2,11};
        Integer [] arr2={5,7,15,3,8};
        BNode<Integer> prev=tree.root;
        for (int i=0;i<4;i++){
            if (i==1){
                tree.addChild(prev, 2, arr1[i]);
                continue;
            }
            prev=tree.addChild(prev, 1, arr1[i]);
            
        }
        prev=tree.addChild(tree.root, 2, arr2[0]);
        for (int i=1;i<5;i++){

            if (i==1 || i==3){
                tree.addChild(prev, 2, arr2[i]);
                continue;
            }
            prev=tree.addChild(prev, 1, arr2[i]);
            
        }
        sum(tree.root);
        System.out.println(suma);

    }
    public static void sum (BNode<Integer> root){
        if (root.left==null && root.right==null){
            if (maxdepth<root.depth){
                suma=root.info;
                maxdepth=root.depth;
            }
            else if (maxdepth==root.depth) {
                suma+=root.info;
            }
            else {
                return;
            }
        }
        if (root.left!=null){
            sum(root.left);
        }
        if (root.right!=null){
            sum(root.right);
        }
    }
}
class BNode<E> {
    public E info;
    public BNode<E> left, right;
    public Integer depth=1;
    static int LEFT = 1, RIGHT = 2;
    
    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
        this.depth=1;
    }
    
    public BNode(E info,Integer b, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.depth=b;
    }
}

class BTree<E> {
    public BNode<E> root;
    
    public BTree() {
        root = null;
    }
    
    public BTree(E info) {
        root = new BNode<E>(info);
    }
    
    public BNode<E> addChild(BNode<E> node, int where, E info) {
        BNode<E> tmp = new BNode<E>(info);
        
        if (where == BNode.LEFT) {
            if (node.left != null) {
                return null;
            }
            tmp.depth=node.depth+1;
            node.left = tmp;
        } else {
            if (node.right != null) {
                return null;
            }
            tmp.depth=node.depth+1;
            node.right = tmp;
        }
        
        return tmp;
    }
    
    public void inorder(BNode<E> r) {
        if (r != null) {
            inorder(r.left);
            System.out.println(r.info);
            inorder(r.right);
        }
    }
    
    public void preorder(BNode<E> r) {
        if (r != null) {
            System.out.println(r.info);
            preorder(r.left);
            preorder(r.right);
        }
    }
    
    public void postorder(BNode<E> r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.println(r.info);
        }
    }
    
    public void inorderUsingStack(BNode<E> r) {
        Stack<BNode<E>> s = new Stack();
        
        BNode<E> p = root;
        
        while (true) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }
            
            if (s.isEmpty()) {
                break;
            }
            
            p = s.pop();
            
            System.out.println(p.info.toString());
            
            p = p.right;
        }
    }
}