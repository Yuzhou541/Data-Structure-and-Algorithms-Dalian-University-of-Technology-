package Graph;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
//import java.util.TreeMap;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javafx.scene.paint.Color;

// Adjacency Matrix
class Graph_matrix{
    int[][] AdjacencyMatrix = new int[5][5];
    public Graph_matrix(){
        for(int i = 0; i < AdjacencyMatrix.length; i++){
            for(int j = 0; j < AdjacencyMatrix[0].length; j++){
                AdjacencyMatrix[i][j] = 0;
            }
        }
    }
    void connect(int x, int y){
        AdjacencyMatrix[x][y] = 1;
        AdjacencyMatrix[y][x] = 1;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdjacencyMatrix.length; i++){
            for (int j = 0; j < AdjacencyMatrix[i].length; j++){
                sb.append(AdjacencyMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

// Adjacency List
class Vertex implements Comparable<Vertex>{
    char key;
    public Vertex(char key){
        this.key = key;
    }
    @Override
    public String toString() {
        return key + "";
    }
    @Override
    public int compareTo(Vertex o) {
        return Character.compare(o.key, this.key);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex)obj;
        return key == vertex.key;
    }
    @Override
    public int hashCode() {
        return Character.hashCode(key);
    }
}

// BFS
class VertexBFS{
    char key;
    double d;
    VertexBFS pi;
    Color color;
    public VertexBFS(char key){
        this.key = key;
        this.d = Double.POSITIVE_INFINITY;
        this.pi = null;
        this.color = Color.WHITE;
    }
    @Override
    public String toString() {
        return "" + key;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        VertexBFS vertexBFS = (VertexBFS)obj;
        return vertexBFS.key == this.key;
    }
    @Override
    public int hashCode() {
        return Character.hashCode(key);
    }
}

// DFS
class VertexDFS{
    char key;
    Color color;
    int d;
    int f;
    VertexDFS pi;
    public VertexDFS(char key){
        this.key = key;
        this.color = Color.WHITE;
        this.d = Integer.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
        this.pi = null;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        VertexDFS vertexDFS = (VertexDFS)obj;
        return key == vertexDFS.key; 
    }
    @Override
    public int hashCode() {
        return Character.hashCode(key);
    }
}

// Kurskal & Prim & Dijkstra
class VertexPath{
    char key;
    public VertexPath(char key){
        this.key = key;
    }
    @Override
    public String toString() {
        return "" + key;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        VertexPath vertex = (VertexPath)obj;
        return key == vertex.key;
    }
    @Override
    public int hashCode() {
        return Character.hashCode(key);
    }
}


public class GraphAlgorithm {

    private static int timeDFS;
    private static int timeDFStop;
    private static int timeSCC;
    public static void main(String[] args) {
        // Adjacency Matrix
        System.out.println("Graph stored by adjacency matrix: ");
        Graph_matrix graph_matrix = new Graph_matrix();
        graph_matrix.connect(1, 2);
        graph_matrix.connect(2, 3);
        graph_matrix.connect(0, 4);
        System.out.println(graph_matrix);

        // Adjancency List
        System.out.println("Graph stored by adjancenct list: ");
        Vertex A = new Vertex('a');
        Vertex B = new Vertex('b');
        Vertex C = new Vertex('c');
        Vertex D = new Vertex('d');
        Vertex E = new Vertex('e');
        LinkedList<Vertex> aN = new LinkedList<>();
        aN.add(B);
        aN.add(D);
        LinkedList<Vertex> bN = new LinkedList<>();
        bN.add(A);
        bN.add(C);
        LinkedList<Vertex> cN = new LinkedList<>();
        cN.add(B);
        cN.add(D);
        cN.add(E);
        LinkedList<Vertex> dN = new LinkedList<>();
        dN.add(A);
        dN.add(C);
        dN.add(E);
        LinkedList<Vertex> eN = new LinkedList<>();
        eN.add(C);
        eN.add(D);
        Map<Vertex, LinkedList<Vertex>> G = new HashMap<>(); // Map<Vertex, LinkedList<Vertex>> G = new TreeMap<>()
        G.put(A, aN);
        G.put(B, bN);
        G.put(C, cN);
        G.put(D, dN);
        G.put(E, eN);
        for(Map.Entry<Vertex, LinkedList<Vertex>> entry : G.entrySet()){
            Vertex vertex = entry.getKey();
            LinkedList<Vertex> neighbors = entry.getValue();
            System.out.printf(vertex + " -> ");
            for(Vertex neighbor : neighbors){
                System.out.printf(neighbor + " ");
            }
            System.out.println();
        }

        // BFS
        System.out.println("BFS: ");
        VertexBFS s = new VertexBFS('s');
        VertexBFS r = new VertexBFS('r');
        VertexBFS w = new VertexBFS('w');
        VertexBFS t = new VertexBFS('t');
        VertexBFS x = new VertexBFS('x');
        LinkedList<VertexBFS> sN = new LinkedList<>();
        LinkedList<VertexBFS> rN = new LinkedList<>();
        LinkedList<VertexBFS> wN = new LinkedList<>();
        LinkedList<VertexBFS> tN = new LinkedList<>();
        LinkedList<VertexBFS> xN = new LinkedList<>();
        sN.add(r);
        sN.add(w);
        rN.add(s);
        wN.add(s);
        wN.add(t);
        wN.add(x);
        tN.add(w);
        tN.add(x);
        xN.add(w);
        xN.add(t);
        Map<VertexBFS, LinkedList<VertexBFS>> G2 = new HashMap<>();
        G2.put(s, sN);
        G2.put(r, rN);
        G2.put(w, wN);
        G2.put(t, tN);
        G2.put(x, xN);
        new GraphAlgorithm().BFS(G2, s);     

        // DFS
        System.out.println("DFS: ");
        VertexDFS u1 = new VertexDFS('u');
        VertexDFS x1 = new VertexDFS('x');
        VertexDFS v1 = new VertexDFS('v');
        VertexDFS y1 = new VertexDFS('y');
        VertexDFS w1 = new VertexDFS('w');
        VertexDFS z1 = new VertexDFS('z');
        LinkedList<VertexDFS> u1N = new LinkedList<>();
        LinkedList<VertexDFS> x1N = new LinkedList<>();
        LinkedList<VertexDFS> v1N = new LinkedList<>();
        LinkedList<VertexDFS> y1N = new LinkedList<>();
        LinkedList<VertexDFS> w1N = new LinkedList<>();
        LinkedList<VertexDFS> z1N = new LinkedList<>();
        u1N.add(x1);
        u1N.add(v1);
        x1N.add(v1);
        v1N.add(y1);
        y1N.add(x1);
        w1N.add(y1);
        w1N.add(z1);
        z1N.add(z1);
        Map<VertexDFS, LinkedList<VertexDFS>> G3 = new HashMap<>();
        G3.put(x1, x1N);
        G3.put(v1, v1N);
        G3.put(u1, u1N);
        G3.put(y1, y1N);
        G3.put(z1, z1N);
        G3.put(w1, w1N);
        new GraphAlgorithm().DFS(G3, u1);
        System.out.println("Version2: ");
        new GraphAlgorithm().DFS2(G3, u1);

        // Topological Sort
        System.out.println("Topological Sort:");
        LinkedList<VertexDFS> sortedList = new GraphAlgorithm().topologicalSort(G3);
        for (VertexDFS vertex : sortedList) {
            System.out.print(vertex.key + " ");
        }
        System.out.println();

        // Strongly connected components
        System.out.println("Strongly connected components: ");
        List<List<VertexDFS>> scc = new GraphAlgorithm().stronglyConnectedComponents(G3);
        for (List<VertexDFS> component : scc) {
            System.out.print("{ ");
            for (VertexDFS vertex : component) {
                System.out.print(vertex.key + " ");
            }
            System.out.println("}");
        }


        // Kruscal
        System.out.println("Kruskal: ");
        VertexPath Ak = new VertexPath('a');
        VertexPath Bk = new VertexPath('b');
        VertexPath Ck = new VertexPath('c');
        VertexPath Dk = new VertexPath('d');
        VertexPath Ek = new VertexPath('e');
        Map<VertexPath, Map<VertexPath, Double>> graphK = new HashMap<>();
        Map<VertexPath, Double> aNeighbors = new HashMap<>();
        aNeighbors.put(Bk, 2.0);
        aNeighbors.put(Dk, 1.0);
        graphK.put(Ak, aNeighbors);
        Map<VertexPath, Double> bNeighbors = new HashMap<>();
        bNeighbors.put(Ak, 2.0);
        bNeighbors.put(Ck, 3.0);
        graphK.put(Bk, bNeighbors);
        Map<VertexPath, Double> cNeighbors = new HashMap<>();
        cNeighbors.put(Bk, 3.0);
        cNeighbors.put(Dk, 4.0);
        cNeighbors.put(Ek, 5.0);
        graphK.put(Ck, cNeighbors);
        Map<VertexPath, Double> dNeighbors = new HashMap<>();
        dNeighbors.put(Ak, 1.0);
        dNeighbors.put(Ck, 4.0);
        dNeighbors.put(Ek, 6.0);
        graphK.put(Dk, dNeighbors);
        Map<VertexPath, Double> eNeighbors = new HashMap<>();
        eNeighbors.put(Ck, 5.0);
        eNeighbors.put(Dk, 6.0);
        graphK.put(Ek, eNeighbors);
        GraphAlgorithm Gk = new GraphAlgorithm();
        System.out.println("Executing Kruskal's Algorithm:");
        Gk.Kruskal(graphK);
        System.out.println("Prim: ");
        Gk.Prim(graphK, Ak);
        System.out.println("Dijkstra: ");
        Gk.dijkstra(graphK, Ak);
        

    }

    // BFS
    void BFS(Map<VertexBFS, LinkedList<VertexBFS>> graph, VertexBFS start){
        for(VertexBFS vertexBFS : graph.keySet()){
            vertexBFS.color = Color.WHITE;
            vertexBFS.d = Double.POSITIVE_INFINITY;
            vertexBFS.pi = null;
        }
        start.d = 0;
        start.color = Color.GRAY;
        start.pi = null;
        Queue<VertexBFS> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            VertexBFS u = queue.poll();
            System.out.println("Visiting vertex " + u.key + " at time " + u.d + " with predecessor " + u.pi);
            for(VertexBFS v : graph.get(u)){
                if(v.color == Color.WHITE){
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.pi = u;
                    queue.add(v);
                }
            }
            u.color = Color.BLACK;
        }
    }

    // DFS
    void DFS(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS start){
        for(VertexDFS vertexDFS : graph.keySet()){
            vertexDFS.d = Integer.MAX_VALUE;
            vertexDFS.color = Color.WHITE;
            vertexDFS.f = Integer.MAX_VALUE;
            vertexDFS.pi = null;
        }
        if(start.color == Color.WHITE){
            dfsVisit(graph, start);
        }
        for(VertexDFS vertexDFS : graph.keySet()){
            if(vertexDFS.color == Color.WHITE){
                dfsVisit(graph, vertexDFS);
            }
        }
    }
    void dfsVisit(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS u){
        timeDFS++;
        u.d = timeDFS;
        u.color = Color.GRAY;
        System.out.println("Visiting vertex " + u.key + " at time " + u.d);
        for(VertexDFS v : graph.get(u)){
            if(v.color == Color.WHITE){
                v.pi = u;
                dfsVisit(graph, v);
            }
        }
        timeDFS++;
        u.f = timeDFS;
        u.color = Color.BLACK;
        System.out.println("Finishing vertex " + u.key + " at time " + u.f);
    }

    void DFS2(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS start){
        for(VertexDFS vertexDFS : graph.keySet()){
            vertexDFS.color = Color.WHITE;
            vertexDFS.d = Integer.MAX_VALUE;
            vertexDFS.f = Integer.MAX_VALUE;
            vertexDFS.pi = null;
        }
        start.color = Color.GRAY;
        start.d = 0;
        start.pi = null;
        Stack<VertexDFS> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            VertexDFS u = stack.pop();
            System.out.println("Visiting vertex " + u.key + " at time " + u.d);
            for(VertexDFS v : graph.get(u)){
                if(v.color == Color.WHITE){
                    v.color = Color.GRAY;
                    v.d = u.d + 1;
                    v.pi = u;
                    stack.push(v);
                }
            }
            u.color = Color.BLACK;
        }
    }

    // topological sort
    void dfsVisitForTopologicalSort(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS u, LinkedList<VertexDFS> sortedList){
        timeDFStop++;
        u.d = timeDFStop;
        u.color = Color.GRAY;
        for (VertexDFS v : graph.get(u)) {
            if (v.color == Color.WHITE) {
                v.pi = u;
                dfsVisitForTopologicalSort(graph, v, sortedList);
            }
        }
        u.color = Color.BLACK;
        timeDFStop++;
        u.f = timeDFStop;
        sortedList.addFirst(u);
    }
    LinkedList<VertexDFS> topologicalSort(Map<VertexDFS, LinkedList<VertexDFS>> graph) {
        LinkedList<VertexDFS> sortedList = new LinkedList<>();
        timeDFS = 0;
        for (VertexDFS vertex : graph.keySet()) {
            vertex.color = Color.WHITE;
            vertex.pi = null;
        }
        for (VertexDFS vertex : graph.keySet()) {
            if (vertex.color == Color.WHITE) {
                dfsVisitForTopologicalSort(graph, vertex, sortedList);
            }
        }
        return sortedList;
    }

    // Strongly Connected Components
    List<List<VertexDFS>> stronglyConnectedComponents(Map<VertexDFS, LinkedList<VertexDFS>> graph) {
        LinkedList<VertexDFS> finishedOrder = new LinkedList<>();
        DFSForSCC(graph, finishedOrder);
        Map<VertexDFS, LinkedList<VertexDFS>> transposedGraph = transposeGraph(graph);
        List<List<VertexDFS>> scc = new ArrayList<>();
        for (VertexDFS vertex : transposedGraph.keySet()) {
            vertex.color = Color.WHITE; 
        }
        for (VertexDFS vertex : finishedOrder) {
            if (vertex.color == Color.WHITE) {
                List<VertexDFS> component = new ArrayList<>();
                dfsVisitForSCC(transposedGraph, vertex, component);
                scc.add(component);
            }
        }
        return scc;
    }
    void DFSForSCC(Map<VertexDFS, LinkedList<VertexDFS>> graph, LinkedList<VertexDFS> finishedOrder) {
        timeSCC = 0;
        for (VertexDFS vertex : graph.keySet()) {
            vertex.color = Color.WHITE;
            vertex.pi = null;
        }

        for (VertexDFS vertex : graph.keySet()) {
            if (vertex.color == Color.WHITE) {
                dfsVisitForSCCOrder(graph, vertex, finishedOrder);
            }
        }
    }

    void dfsVisitForSCCOrder(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS u, LinkedList<VertexDFS> finishedOrder) {
        timeSCC++;
        u.d = timeSCC;
        u.color = Color.GRAY;
        for (VertexDFS v : graph.get(u)) {
            if (v.color == Color.WHITE) {
                v.pi = u;
                dfsVisitForSCCOrder(graph, v, finishedOrder);
            }
        }
        u.color = Color.BLACK;
        timeSCC++;
        u.f = timeSCC;
        finishedOrder.addFirst(u); 
    }
    void dfsVisitForSCC(Map<VertexDFS, LinkedList<VertexDFS>> graph, VertexDFS u, List<VertexDFS> component) {
        u.color = Color.GRAY;
        component.add(u); 
        for (VertexDFS v : graph.get(u)) {
            if (v.color == Color.WHITE) {
                dfsVisitForSCC(graph, v, component);
            }
        }

        u.color = Color.BLACK;
    }
    Map<VertexDFS, LinkedList<VertexDFS>> transposeGraph(Map<VertexDFS, LinkedList<VertexDFS>> graph) {
        Map<VertexDFS, LinkedList<VertexDFS>> transposed = new HashMap<>();
        for (VertexDFS u : graph.keySet()) {
            transposed.putIfAbsent(u, new LinkedList<>());
            for (VertexDFS v : graph.get(u)) {
                transposed.putIfAbsent(v, new LinkedList<>());
                transposed.get(v).add(u); 
            }
        }
        return transposed;
    }

    // Kruskal
    void Kruskal(Map<VertexPath, Map<VertexPath, Double>> graph) {
        ArrayList<Map.Entry<VertexPath, Map.Entry<VertexPath, Double>>> edges = new ArrayList<>();
        ArrayList<Set<VertexPath>> components = new ArrayList<>();
        for (VertexPath u : graph.keySet()) {
            for (Map.Entry<VertexPath, Double> entry : graph.get(u).entrySet()) {
                VertexPath v = entry.getKey();
                double weight = entry.getValue();
                if (u.hashCode() < v.hashCode()) {
                    edges.add(new AbstractMap.SimpleEntry<>(u, new AbstractMap.SimpleEntry<>(v, weight)));
                }
            }
            Set<VertexPath> component = new HashSet<>();
            component.add(u);
            components.add(component);
        }
        edges.sort(Comparator.comparingDouble(e -> e.getValue().getValue()));
        List<String> mst = new ArrayList<>();
        for (Map.Entry<VertexPath, Map.Entry<VertexPath, Double>> edge : edges) {
            VertexPath u = edge.getKey();
            VertexPath v = edge.getValue().getKey();
            double weight = edge.getValue().getValue();
            Set<VertexPath> componentU = findComponent(components, u);
            Set<VertexPath> componentV = findComponent(components, v);
            if (componentU != componentV) {
                mst.add(u + " - " + v + " : " + weight);
                componentU.addAll(componentV);
                components.remove(componentV);
            }
        }
        System.out.println("Kruskal's MST:");
        for (String edge : mst) {
            System.out.println(edge);
        }
    }
    Set<VertexPath> findComponent(ArrayList<Set<VertexPath>> components, VertexPath vertex) {
        for (Set<VertexPath> component : components) {
            if (component.contains(vertex)) {
                return component;
            }
        }
        return null;
    }

    // Prim
    void Prim(Map<VertexPath, Map<VertexPath, Double>> graph, VertexPath start) {
        Set<VertexPath> MST = new HashSet<>();
        Map<VertexPath, Double> key = new HashMap<>();
        Map<VertexPath, VertexPath> parents = new HashMap<>();
        for(VertexPath vertexPath : graph.keySet()){
            key.put(vertexPath, Double.POSITIVE_INFINITY);
            parents.put(vertexPath, null);
        }
        key.put(start, 0.0);
        parents.put(start, null);
        PriorityQueue<VertexPath> pq = new PriorityQueue<>(Comparator.comparing(key::get));
        pq.add(start);
        while(!pq.isEmpty()){
            VertexPath u = pq.poll();
            if(MST.contains(u)) continue;
            MST.add(u);
            for(Map.Entry<VertexPath, Double> entry : graph.get(u).entrySet()){
                VertexPath v = entry.getKey();
                double weight = entry.getValue();
                if(!MST.contains(v) && weight < key.get(v)){
                    key.put(v, weight);
                    parents.put(v, u);
                    pq.add(v);
                }
            }
        }
        System.out.println("Prim's MST: ");
        for(Map.Entry<VertexPath, VertexPath> entry : parents.entrySet()){
            if(entry.getValue() != null){
                System.out.println(entry.getValue() + " - " + entry.getKey());
            }
        }
    }

    // Dijkstra
    void dijkstra(Map<VertexPath, Map<VertexPath, Double>> graph, VertexPath source) {
        Map<VertexPath, Double> distance = new HashMap<>();
        for(VertexPath vertexPath : graph.keySet()){
            distance.put(vertexPath, Double.POSITIVE_INFINITY);
        }
        distance.put(source, 0.0);
        PriorityQueue<VertexPath> pq = new PriorityQueue<>(Comparator.comparing(distance::get));
        pq.add(source);
        while(!pq.isEmpty()){
            VertexPath u = pq.poll();
            for(Map.Entry<VertexPath, Double> entry : graph.get(u).entrySet()){
                VertexPath v = entry.getKey();
                double weight = entry.getValue();
                double newDist = weight + distance.get(u);
                if(newDist < distance.get(v)){
                    distance.put(v, newDist);
                    pq.add(v);
                }
            }
        }
        System.out.println("Dijkstra's shortest path: ");
        for(Map.Entry<VertexPath, Double> entry : distance.entrySet()){
            System.out.println(source + " -> " + entry.getKey() + " : " + entry.getValue());
        }
    }
}
