import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
public class Zad3 {
    public static void main(String[] args) {
        Integer []infos={0,1,2,3,4};
        Graph <Integer> graph=new Graph(5, infos);
        for (int i=0;i<4;i++){
            graph.addEdge(i+1, i, 1);
        }
        graph.addEdge(4, 2, 0);
        graph.addEdge(2, 3, 0);
        List <Edge> edges=holding(graph);
        for (int i=0;i<edges.size();i++){
           System.out.print(edges.get(i).from);
           System.out.print("->");
            System.out.println(edges.get(i).to);
            
        }
    }
    public static List <Edge> holding (Graph <Integer> graph){
        List <Edge> temp=new ArrayList<Edge>();
        Edge [] edges=graph.getAllEdges();
        for (int j=0;j<edges.length;j++){
            Edge e=edges[j];
            Boolean critical=true;
            graph.deleteEdge(e.from, e.to);
            for (int i=0;i<graph.n;i++){
                int[] visited=new int[graph.n];
                for (int k=0;k<graph.n;k++){
                    visited[k]=0;
                }
                Integer var=graph.dfs(visited,i);
                if (var==graph.n){
                    critical=false;
                    break;
                }
            }
            graph.addEdge(e.from,e.to,e.weight);
            if (critical==true){
                temp.add(e);
            }
        }
        return temp;
    }
}
class Edge {
    public int from, to;
    public float weight;
    
    public Edge(int from, int to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class GNeighbour<E extends Comparable<E>> {
    public GNode<E> node;
    public float weight;
    
    public GNeighbour(GNode<E> node) {
        this.node = node;
        this.weight = 0;
    }
    
    public GNeighbour(GNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }
}

class GNode<E extends Comparable<E>> {
    public int num;
    public E info;
    public LinkedList<GNeighbour<E>> list;
    
    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList<GNeighbour<E>>();
    }
    
    public void addNeighbour(GNode<E> node, float weight) {
        list.add(new GNeighbour(node, weight));
    }
    
    public void deleteNeighbour(GNode<E> node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                list.remove(i);
            }
        }
    }
    
    public boolean hasNeighbour(GNode<E> node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void updateNeighbourWeight(GNode<E> node, float weight) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                list.get(i).weight = weight;
            }
        }
    }
}

class Graph<E extends Comparable<E>> {
    int n;
    GNode<E> graph[];
    
    public Graph(int n) {
        this.n = n;
        
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, null);
        }
    }
    
    public Graph(int n, E[] infos) {
        this.n = n;
        
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, infos[i]);
        }
    }
    
    boolean hasNeighbour(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }
    
    void addEdge(int x, int y, float w) {
        if (graph[x].hasNeighbour(graph[y])) {
            graph[x].updateNeighbourWeight(graph[y], w);
        } else {
            graph[x].addNeighbour(graph[y], w);
        }
    }
    
    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
    }
    
    public Edge[] kruskal() {
        Edge[] fin = new Edge[n - 1];
        
        Edge[] edges = getAllEdges();
        sort(edges);
        
        int[] roditel = new int[n];
        for (int i = 0; i < n; i++) {
            roditel[i] = i;
        }
        
        int dodadeni = 0, i = 0;
        
        while (dodadeni < n - 1) {
            Edge e = edges[i++];
            
            if (roditel[e.from] != roditel[e.to]) {
                promeni(e.from, e.to, roditel);
                fin[dodadeni++] = e;
            }
        }
        
        return fin;
    }
    
    public Edge[] getAllEdges() {
        int vk = 0, ind = 0;
        
        for (int i = 0; i < n; i++) {
            vk += graph[i].list.size();
        }
        
        Edge[] edges = new Edge[vk];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].list.size(); j++) {
                edges[ind++] = new Edge(graph[i].num, 
                        graph[i].list.get(j).node.num,
                        graph[i].list.get(j).weight);
            }
        }
        
        return edges;
    }
    
    public void sort(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge pom = edges[i];
                    edges[i] = edges[j];
                    edges[j] = pom;
                }
            }
        }
    }
    
    public void promeni(int from, int to, int[] roditel) {
        int pomal, pogolem;
        
        if (from < to) {
            pomal = roditel[from];
            pogolem = roditel[to];
        } else {
            pomal = roditel[to];
            pogolem = roditel[from];
        }
        
        for (int i = 0; i < roditel.length; i++) {
            if (roditel[i] == pogolem) {
                roditel[i] = pomal;
            }
        }
    }
    
    public Edge[] prim(int start) {
        Edge[] fin = new Edge[n - 1];
        int dodadeni = 0;
        
        boolean[] voDrvo = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            voDrvo[i] = false;
        }
        
        voDrvo[start] = true;
        
        while (dodadeni < n - 1) {
            Edge e = findMin(voDrvo);
            voDrvo[e.from] = voDrvo[e.to] = true;
            fin[dodadeni++] = e;
        }
        
        return fin;
    }
    
    public Edge findMin(boolean[] voDrvo) {
        float min_t = Float.MAX_VALUE;
        int from_n = -1, to_n = -1;
        
        for (int i = 0; i < n; i++) {
            if (voDrvo[i] == true) {
                for (int j = 0; j < graph[i].list.size(); j++) {
                    if (voDrvo[graph[i].list.get(j).node.num] == false &&
                            min_t > graph[i].list.get(j).weight) {
                        min_t = graph[i].list.get(j).weight;
                        from_n = graph[i].num;
                        to_n = graph[i].list.get(j).node.num;
                    }
                }
            }
        }
        
        return new Edge(from_n, to_n, min_t);
    }
    
    public float[] dijkstra(int start) {
        float[] dist = new float[n];
        boolean[] fin = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            fin[i] = false;
            dist[i] = -1;
        }
        
        fin[start] = true;
        dist[start] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[start].list.size(); j++) {
                if (fin[graph[start].list.get(j).node.num] == false) {
                    if (dist[graph[start].list.get(j).node.num] == -1) {
                        dist[graph[start].list.get(j).node.num] = dist[start] + 
                                graph[start].list.get(j).weight;
                    } else if (dist[graph[start].list.get(j).node.num] > 
                            dist[start] + graph[start].list.get(j).weight) {
                        dist[graph[start].list.get(j).node.num] = 
                                dist[start] + graph[start].list.get(j).weight;
                    }
                }
            }
            
            float min_cena = Float.MAX_VALUE;
            for (int k = 0; k < n; k++) {
                if (dist[k] != -1 && fin[k] == false) {
                    if (min_cena > dist[k]) {
                        min_cena = dist[k];
                        start = k;
                    }
                }
            }
            
            fin[start] = true;
        }
        
        
        return dist;
    }
    
    public float[] bellman_ford(int start) {
        float[] dist = new float[n];
        Arrays.fill(dist, Float.MAX_VALUE);
        
        dist[start] = 0;
        
        Edge[] edges = getAllEdges();
        
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                int node_out = edge.from;
                int node_in = edge.to;
                float link_weight = edge.weight;
                
                if (dist[node_out] != Float.MAX_VALUE && 
                        dist[node_in] > dist[node_out] + link_weight) {
                    
                    if (i == n - 1) {
                        return new float[]{-1};
                    }
                    
                    dist[node_in] = dist[node_out] + link_weight;
                }
            }
        }
        
        return dist;
    }
    Integer dfs(int visited[], int start) {
        Integer connected=1;
        visited[start] = 1;
        System.out.println("Node: " + graph[start].info);
        
        GNode<E> pom = graph[start];
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i).node;
            
            if (visited[next.num] == 0) {
               connected+=dfs(visited, next.num);
            }
        }
        return connected;
    }
}