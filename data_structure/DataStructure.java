package data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//Stack by hand
class Stack_hand{
    double[] a;
    int top;
    int size;
    public Stack_hand(int size){
        this.size = size;
        this.top = -1;
        this.a = new double[size];
    }
    boolean isEmpty(){
        return top == -1;
    }
    boolean isFull(){
        return top == size;
    }
    void push(double x){
        if(isFull()){
            System.out.println("Overfloat!");
            return;
        }
        a[++top] = x;
    }
    double pop(){
        if(isEmpty()){
            System.out.println("Underfloat!");
            return -1;
        }
        return a[top--];
    }
    double peek(){
        if(isEmpty()){
            System.out.println("Empty!");
            return -1;
        }
        return a[top];
    }
}

//Circular Queue by hand
class CircularQueue_hand{
    double[] a;
    int head;
    int tail;
    int size;
    int capacity;
    public CircularQueue_hand(int capacity){
        this.a = new double[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    boolean isEmpty(){
        return head == tail;
    }
    boolean isFull(){
        return size == capacity;
    }
    void enqueue(int x){
        if(isFull()){
            System.out.println("Overfloat!");
            return;
        }
        size++;
        a[tail] = x;
        tail = (tail + 1) % capacity;
    }
    double dequeue(){
        if(isEmpty()){
            System.out.println("Underfloat!");
            return -1;
        }
        size--;
        double value = a[head];
        head = (head + 1) % capacity;
        return value;
    }
    double front(){
        if(isEmpty()){
            System.out.println("Empty!");
            return -1;
        }
        return a[head];
    }
    double rear(){
        if(isEmpty()){
            System.out.println("Empty!");
            return -1;
        }
        return a[(tail - 1 + capacity) % capacity];
    }
}

class QueueUse2Stacks{
    Stack<Double> stack1;
    Stack<Double> stack2;
    public QueueUse2Stacks(){
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }
    boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }
    void enqueue(double x){
        stack1.push(x);
    }
    double dequeue(){
        if(stack2.isEmpty()){
            if(stack1.isEmpty()){
                throw new IllegalStateException("Underfloat!");
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    double peek(){
        if(stack2.isEmpty()){
            if(stack1.isEmpty()){
                throw new IllegalStateException("Empty!");
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
    
}

class StackUse2Queues{
    Queue<Double> queue1;
    Queue<Double> queue2;
    public StackUse2Queues(){
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }
    boolean isEmpty(){
        return queue1.isEmpty();
    }
    void push(double x){
        queue1.add(x);
    }
    double pop(){
        if(queue1.isEmpty()){
            throw new IllegalStateException("Underfloat!");
        }
        while(queue1.size() > 1){
            queue2.add(queue1.poll());
        }
        double poped = queue1.poll();
        Queue<Double> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
        return poped;
    }
    double peek(){
        if(queue1.isEmpty()){
            throw new IllegalStateException("Empty!");
        }
        while(queue1.size() > 1){
            queue2.add(queue1.poll());
        }
        double peeked = queue1.peek();
        Queue<Double> tmp = queue2;
        queue2 = queue1;
        queue1 = tmp;
        return peeked;
    }
}

// Doubly Linked List
class Node{
    int key;
    Node prev;
    Node next;
    public Node(int key){
        this.key = key;
        this.prev = null;
        this.next = null;
    }
}
class DoublyLinkedList{
    Node head;
    public DoublyLinkedList(){
        this.head = null;
    }
    Node Search(int key){
        Node currentNode = head;
        while(currentNode != null && currentNode.key != key){
            currentNode = currentNode.next;
        }
        return currentNode;
    }
    void insertHead(Node node){
        node.next = head;
        if(head != null){
            head.prev = node;
        }
        head = node;
        node.prev = null;
    }
    void delete(Node node){
        if(node == null)    return;
        if(node.next != null){
            node.next.prev = node.prev;
        }
        if(node.prev != null){
            node.prev.next = node.next;
        }
        else{
            head = node.next;
        }
    }
    void printList(){
        Node currentNode = head;
        while(currentNode != null){
            System.out.printf(currentNode.key + " ");
            currentNode = currentNode.next;
        }
    }
}

// Sentinels List
class SentinelsList{
    Node nil;
    public SentinelsList(){
        this.nil = new Node(-1);
        nil.next = nil;
        nil.prev = nil;
    }
    Node Search(int key){
        Node currentNode = nil.next;
        while(currentNode != nil && currentNode.key != key){
            currentNode = currentNode.next;
        }
        return currentNode;
    }
    void insert(Node node, int position){
        Node currentNode = nil.next;
        int currentPos = 0;
        while(currentNode != nil && currentPos < position){
            currentNode = currentNode.next;
            currentPos++;
        }
        if(currentPos == position){
            node.next = currentNode;
            node.prev = currentNode.prev;
            currentNode.prev.next = node;
            currentNode.prev = node;
        }
    }
    void insertHead(Node node){
        insert(node, 0);
    }
    void delete(Node node){
        if(node != null && node != nil){
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }
    void printList(){
        Node currentNode = nil.next;
        while(currentNode != nil){
            System.out.printf(currentNode.key + " ");
            currentNode = currentNode.next;
        }
    }
}

// binary tree
class TreeNode{
    int key;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    public TreeNode(int key){
        this.key = key;
        this.left = null;
        this.right = null;
    }
    void PreOrder(TreeNode root){
        if(root == null)    return;
        System.out.printf(root.key + " ");
        PreOrder(root.left);
        PreOrder(root.right);
    }
    void InOrder(TreeNode root){
        if(root == null)    return;
        InOrder(root.left);
        System.out.printf(root.key + " ");
        InOrder(root.right);
    }
    void PostOrder(TreeNode root){
        if(root == null)    return;
        PostOrder(root.left);
        PostOrder(root.right);
        System.out.printf(root.key + " ");
    }
}
class BST{
    boolean isBST(TreeNode root){
        if(root == null)    return true;
        if(root.left != null && max(root.left).key > root.key)  return false;
        if(root.right != null && min(root.right).key < root.key)    return false;
        return isBST(root.left) && isBST(root.right);
    }
    TreeNode min(TreeNode root){
        if(root.left != null)    return min(root.left);
        return root;
    }
    TreeNode max(TreeNode root){
        if(root.right != null)  return max(root.right);
        return root;
    }
    TreeNode Successor(TreeNode node){
        if(node.right != null)  return min(node.right);
        TreeNode parent = node.parent;
        while(parent != null && parent.left != node){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
    TreeNode Predecessor(TreeNode node){
        if(node.left != null)   return max(node.left);
        TreeNode parent = node.parent;
        while(parent != null && parent.right != node){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
    TreeNode insert(TreeNode root, int key){
        TreeNode newNode = new TreeNode(key);
        if(root == null)    return newNode;
        TreeNode currentNode = root;
        TreeNode parent = null;
        while(currentNode != null){
            parent = currentNode;
            if(key < currentNode.key)   currentNode = currentNode.left;
            else    currentNode = currentNode.right;
        }
        newNode.parent = parent;
        if(key < parent.key)    parent.left = newNode;
        else parent.right = newNode;
        return root;
    }
    TreeNode search(TreeNode root, int key){
        while(root != null){
            if(root.key == key) return root;
            else if(key < root.key) root = root.left;
            else    root = root.right;
        }
        return null;
    }
    TreeNode delete(TreeNode root, int key){
        TreeNode node = search(root, key);
        if(node == null)    return node;
        if(node.left == null && node.right == null){
            if(root == node)    root = null;
            else{
                if(node.parent.left == node)    node.parent.left = null;
                else    node.parent.right = null;
            }
        }
        else if(node.left == null || node.right == null){
            TreeNode child = (node.left != null) ? node.left : node.right;
            if(node == root)    root = child;
            else{
                if(node.parent.left == node)    node.parent.left = child;
                else    node.parent.right = child;
            }
            child.parent = node.parent;
        }
        else{
            TreeNode successor = Successor(node);
            node.key = successor.key;
            delete(root, successor.key);
        }
        return root;
    }
    int Height(TreeNode root){
        if(root == null)    return 0;
        return 1 + Math.max(Height(root.left), Height(root.right));
    }
}


public class DataStructure {
    public static void main(String[] args) throws Exception{

        // Stack by hand
        System.out.println("Stack by hand: ");
        Stack_hand stack = new Stack_hand(5);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        // use inner stack
        System.out.println("Inner Stack: ");
        Stack<Double> stk = new Stack<>();
        stk.push(10.);
        stk.push(20.);
        stk.push(30.);
        System.out.println(stk);
        System.out.println(stk.pop());
        System.out.println(stk);
        System.out.println(stk.peek());

        // Queue by hand
        System.out.println("Queue by hand: ");
        CircularQueue_hand Q = new CircularQueue_hand(5);
        Q.enqueue(1);
        Q.enqueue(2);
        Q.enqueue(3);
        System.out.println(Q.dequeue());
        System.out.println(Q.rear());
        System.out.println(Q.front());

        // use inner queue
        System.out.println("Inner Queue: ");
        Queue<Double> q1 = new LinkedList<>();
        q1.add(1.);
        q1.add(2.);
        q1.add(3.);
        System.out.println(q1);
        System.out.println(q1.poll());
        System.out.println(q1);
        System.out.println(q1.peek());

        // queue by 2 stacks
        System.out.println("Queue by 2 stacks: ");
        QueueUse2Stacks q2 = new QueueUse2Stacks();
        q2.enqueue(1.);
        q2.enqueue(2.);
        q2.enqueue(3.);
        System.out.println(q2.dequeue());
        System.out.println(q2.peek());
        System.out.println(q2.isEmpty());

        // stack by 2 queues
        System.out.println("Stack by 2 queues: ");
        StackUse2Queues s2 = new StackUse2Queues();
        s2.push(1.);
        s2.push(2.);
        s2.push(3.);
        System.out.println(s2.peek());
        System.out.println(s2.pop());
        System.out.println(s2.isEmpty());

        // heap
        System.out.println("Heap by hand: ");
        double[] a = {4.0, 10.0, 3.0, 5.0, 1.0};
        DataStructure heap = new DataStructure();
        heap.buildMaxHeap(a);
        System.out.println("After buildMaxHeap: " + Arrays.toString(a));
        int heapSize = a.length;
        double max = heap.heapExtractMax(a, heapSize);
        heapSize--;
        System.out.println("Extracted max: " + max);
        System.out.println("After extractMax: " + Arrays.toString(Arrays.copyOf(a, heapSize)));
        double[] b = Arrays.copyOf(a, a.length + 1); 
        heapSize = heap.maxHeapInsert(b, heapSize, 15.0);
        System.out.println("After insert: " + Arrays.toString(Arrays.copyOf(b, heapSize)));

        // heap by priority queue
        System.out.println("Heap by priority queue: ");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(5);
        maxHeap.add(4);
        System.out.println("Initial max heap: " + maxHeap);
        int max2 = maxHeap.poll(); 
        System.out.println("Extracted max: " + max2);
        System.out.println("After extract max heap: " + maxHeap);
        maxHeap.add(15);
        System.out.println("After insert: " + maxHeap);

        // Doubly LinkedList by hand
        System.out.println("Doubly LinkedList: ");
        DoublyLinkedList list = new DoublyLinkedList();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        list.insertHead(n1);
        list.insertHead(n2);
        list.insertHead(n3);
        list.printList();
        Node foundNode = list.Search(2);
        System.out.println("Found node with key 2: " + (foundNode != null ? foundNode.key : "Not Found"));
        list.delete(n2);
        System.out.print("List after deleting node with key 2: ");
        list.printList();

        // Sentinel List
        System.out.println("Sentinels List: ");
        SentinelsList list2 = new SentinelsList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        list2.insertHead(node1);
        list2.insertHead(node2);
        list2.insertHead(node3);
        System.out.print("Initial List: ");
        list2.printList();
        Node node4 = new Node(4);
        list2.insert(node4, 2);  
        System.out.print("List after inserting 4 at position 2: ");
        list2.printList();
        Node found = list2.Search(2);
        System.out.println("Found node with key 2: " + (found != null ? found.key : "Not Found"));
        list2.delete(node2);
        System.out.print("List after deleting node with key 2: ");
        list2.printList();

        // list by ArrayList
        System.out.println("list by ArrayList: ");
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        System.out.println(list3);
        list3.add(2, 4);
        System.out.println(list3);
        System.out.println(list3.indexOf(2));
        list3.remove(3);
        System.out.println(list3);

        // list by LinkedList
        System.out.println("list by LinkedList: ");
        LinkedList<Integer> list4 = new LinkedList<>();
        list4.add(1);
        list4.add(2);
        list4.add(3);
        System.out.println(list4);
        list4.add(2, 4);
        System.out.println(list4);
        System.out.println(list4.indexOf(2));
        list4.remove(2);
        System.out.println(list4);

        // binary tree
        System.out.println("Binary tree: ");
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("Pre-order: ");
        root.PreOrder(root);
        System.out.println();
        System.out.println("In-order: ");
        root.InOrder(root);
        System.out.println();
        System.out.println("Post-order: ");
        root.PostOrder(root);
        System.out.println();

        BST Btree = new BST();
        TreeNode root2 = null;
        root2 = Btree.insert(root2, 10);
        root2 = Btree.insert(root2, 5);
        root2 = Btree.insert(root2, 15);
        root2 = Btree.insert(root2, 3);
        root2 = Btree.insert(root2, 7);
        System.out.println("Is BST: " + Btree.isBST(root2)); 
        System.out.println("Height of tree: " + Btree.Height(root2)); 
        root = Btree.delete(root2, 7);
        System.out.println("Is BST after deletion: " + Btree.isBST(root2)); 
    
    }

    // heap
    void maxHeapify(double []a, int heapSize, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if(l < heapSize && a[l] > a[largest])   largest = l;
        if(r < heapSize && a[r] > a[largest])   largest = r;
        if(i != largest){
            double tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;
            maxHeapify(a, heapSize, largest);
        }
    }
    void buildMaxHeap(double []a){
        int n = a.length;
        for(int i = n / 2 - 1; i >= 0; i--){
            maxHeapify(a, n, i);
        }
    }
    double heapExtractMax(double []a, int heapSize) throws Exception{
        if(heapSize < 1)    throw new IllegalStateException("Underfloat!");
        double max = a[0];
        a[0] = a[a.length - 1];
        maxHeapify(a, --heapSize, 0);
        return max;
    }
    void maxHeapIncreaseKey(double []a, int i, double key) throws Exception{
        if(key < a[i])  throw new IllegalStateException("Valu Error!");
        a[i] = key;
        while(i > 0 && a[i] > a[(i - 1) / 2]){
            double tmp = a[i];
            a[i] = a[(i - 1) / 2];
            a[(i - 1) / 2] = tmp;
            i = (i - 1) / 2;
        }
    }
    int maxHeapInsert(double []a, int heapSize, double key) throws Exception{
        if(heapSize == a.length)    throw new IllegalStateException("Overfloat!");
        heapSize++;
        a[heapSize - 1] = Double.NEGATIVE_INFINITY;
        maxHeapIncreaseKey(a, heapSize - 1, key);
        return heapSize;
    }
}
