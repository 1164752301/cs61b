public class LinkedListDeque<T> implements Deque<T>{
	public TNode stnel;
	public int size;

	private class TNode{
			public T item;
	        public TNode next;
	        public TNode prev;

	        public TNode(T i, TNode n, TNode x){
	        	item = i;
	        	next = n;
	        	prev = x;
	        }
	}

	public LinkedListDeque(){
		stnel = new TNode(null, null, null);
		stnel.next = stnel;
		stnel.prev = stnel;
		size = 0;
	}

	@Override
	public void addFirst(T item){
		TNode l =new TNode(item, stnel.next, stnel);
            stnel.next.prev = l;
		    stnel.next = l;
		    size += 1;
		    stnel.next.prev = stnel;
	}

	@Override
	public void addLast(T item){
		size += 1;
		TNode l = new TNode(item, stnel, stnel.prev);
		stnel.prev.next = l;
		stnel.prev = l;
	}

	@Override
	public boolean isEmpty(){
		return (size == 0);
	}

	@Override
	public int size(){
		return size;
	}

	@Override
	public void printDeque(){
		TNode p = stnel.next;
		int i;
		for(i=0;i<size;i++){
			System.out.print(p.item + " ");
			p = p.next;
		}
	}

	@Override
	public T removeFirst(){
		if (size == 0){
			return null;
		}else{
			size -= 1;
			TNode removed = stnel.next;
			stnel.next = stnel.next.next;
			stnel.next.prev = stnel;
			return removed.item;
		}
	}

	@Override
	public T removeLast(){
		if (size == 0){
			return null;
		}else {
			size -= 1;
			TNode removed = stnel.prev;
			stnel.prev = stnel.prev.prev;
			stnel.prev.next = stnel;
			return removed.item;
		}
	}

	@Override
	public T get(int index){
		int i;
		if (index > size-1){
			return null;
		}
		if (index < 0){
			return null;
		}
		TNode p = stnel.next;
		for(i=0;i<index;i++){
			p = p.next;
		}
		return p.item;
	}

	private T GetRecursive_Helper(int index, TNode p){
		if(index > size){
			return null;
		}
		if(index > 0){
			return GetRecursive_Helper(index - 1, p.next);
		}
		return p.next.item;}

	public T getRecursive(int index) {
		return GetRecursive_Helper(index, stnel);
	}
}