public void insert(int item, int position){
	IntNode p = first;
	for(i=0;i<position;i++){
		if (p.next == null){
			break;
		} else {
			p = p.next;
		}
	}
	IntNode temp = p.next;
	p.next = new IntNode(item, temp);
}


public void reverse(){
	IntNode front_of_reversed = null;
	IntNode next_node_to_add = first;
	while(next_node_to_add != null){
		remainder_of_original = next_node_to_add.next;
		next_node_to_add.next = front_of_reversed;
		front_of_reversed = next_node_to_add;
		next_node_to_add = remainder_of_original;
	}
	first = front_of_reversed
}


public IntNode helper(IntNode node){
	if (node.next == null){
		return node;
	} else {
		IntNode reversed = helper(node.next);
		node.next.next = node;
		node.next = null;
		return reversed;
	}
}


public void reverse(){
	first = helper(first);
}


public static int[] insert(int[] arr, int item, int position){
	int[] result = new int[arr.length+1];
	position = Math.min(arr.length, position);
	for(int i = 0; i < position; i++){
		result[i] = arr[i];
	}
	result[position] = item;
	for(i = position; i < arr.length; i++){
		result[i + 1] = arr[i]; 
	}
	return result;
}


public static void reverse(int[] arr){
	int f = 0;
	int b = arr.length - 1;
	int temp
	for(;f < b; f++, b--){
		temp = arr[f];
		arr[f] = arr[b];
		arr[b] = temp;
	}
}


public static int[] replicate(int[] arr){
	int i;
	int j = 0;
	int sum = 0;
	for(i=0;i<arr.length;i++){
		sum += arr[i];
	}
	int[] result = new int[sum]
	for(i=0;i<arr.length;i++){
		for(;j<arr[i];j++){
			result[j] = arr[i];
		}
	}
	return result;
}

public static int[] flatten(int[][] x){
	int totalLength = 0;
	int sum = 0;
	int i, j;
	for(i=0;i<x.length;i++){
			sum += x[i].length;
	}
	int[] result = new int[sum];
	for(int[] item1 : x){
		for(j = 0; j < item1.length; j++, totalLength++){
			result[totalLength] = item1[j];
		}
	}
	return result;
}


public class IntList {
	public int first;
	public IntList rest;

	@Override
	public boolean equals(Object o){...}
	public static IntList list(int... args){...}


	public void skippify(){
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p.rest
			for(i = 0; i < n; i++){
				if(next is null){
					break;
				}
				next = next.next;
			}
			n++;
			p.next = next;
			p = p.next;
		}
	}
}


public static void removeDuplicates(IntList p){
	if (p == null){
		return;
	}

	IntList current = p.rest;
	IntList previous = p;
	while(current != null){
		if(current == previous){
			previous.rest = current.rest;
		} else {
			previous = previous.rest;
		}
		current = current.rest;
	}
}


public class Cat extends Animal{
	super();
	this.noise = "Meow!";
}

a.greet() = "Huh?"
c.greet() // "Meow!"


class D{
	public static void main(String[] args){
		A b0 = new B();
		System.out.println(b0.x); // 5
		b0.m1(); // Am1-> 5
		b0.m2(); // Bm2-> 5
		//b0.m2(61); // Bm2y-> 61
		B b1 = new B();
		b1.m2(61); // Bm2y-> 61
		b1.m3(); // Bm3-> called
		A c0 = new C();
		c0.m2(); //
		//C c1 = (A) new C();
		A a1 = (A) c0;
		C c2 = (C) a1;
		c2.m3();
		b0.update();
		b0.m1(); // Am1-> 99
	}
}


d.play(d) D
d.play(c) E
c.play(d) D
c.play(c) E


Cat c = new Animal(); //
Animal a = new Cat();
Dog d = new Cat(); //
Tree t = new Animal(); //

Animal a = (Cat) new Cat();
Animal a = (Animal) new Cat(); 
Dog d = (Dog) new Animal(); //
Cat c = (Cat) new Dog(); //
Animal a = (Animal) new Tree(); //


public class SLListVista extends SLList{
	@Override
	public int indexOf(int x){
		result = super.indexOf();
		if result == -1:
			throw new NoSuchElementException;
		return result;
	}
}


sentinel = new IntNode(-1000, new LastIntNode);

public class LastIntNode extends IntNode{
	public LastIntNode(){
		super(0, null);
	}
	@Override
	public int max(){
		return 0;
	}
}


news letter : map
sort : queue deque Stack
forward and back : deque


public Queue<E> {
	private Stack<E> stack = new Stack<E>();

	public void push(E element){
		stack.push(element);
	}

	public E pop(){
		Stack<E> temp = new Stack<E>();
		while (!stack.isEmpty()) {
			temp.push(stack.pop());
		}
		E toPop = temp.pop();
		while (!temp.isEmpty()){
			stack.push(temp.pop());
		}
		return toPop;
	}
}


a.2 bit stack
  n bit stack

b.map
list

public Queue<E> {
	private Stack stack1 = new Stack();
	private Stack stack2 = new Stack();
	public void push(E element) {
		stack1.push(element);
	}

	public E poll(){
		while (!stack1.isEmpty()){
		stack2.push(stack1.poll());	
		}
		result = stack2.poll();
		while(!stack2.isEmpty()){
			stack1.push(stack2.poll());
		}
		return result;
	}
}


public SortedStack<E> {
	private Stack stack1 = new Stack();
	private Stack stack2 = new Stack();

	public void push(E t) {
		while(!stack1.isEmpty() && stack1.peek().compareto(t) < 0){
			stack2.push(stack1.poll());
		}
		stack1.push(t);
		while(!stack2.isEmpty()) {
			stack1.push(stack2.poll());
		}
	}

	public E poll() {
		stack1.poll();
	}
}


Rock
MommaRock


try{
	BadIntegerStack stack = new BadIntegerStack()
} catch (NullPointerException e) {
	System.out.println("Success!");
}

Node npe1 = new Node(1, null);
Node npe2 = new Node(1, npe1);
npe1.prev = npe2;

package ParkingLot
interface Space{
	number
	in()
	out()
}
class RegularSpace
class CompactSpace
class HandicappedOnlySpace
class view
number
position
interface Car

("mystery of 1 is 
	x was Zero!
	0")
("mystery of 6 is
  3
  counter is 1
  1
  counter is 2
  x was Zero!
  0 ")