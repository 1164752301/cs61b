public size(Intlist r){
	if (r=null){
		return 1;
	}else{
		return 1 + size(r.first, r.rest);
	}
}

public Iterativesize(Intlist r){
	int size = 0;
	while(r!=null){
		size += 1;
		r = r.rest;
	}
	return size;
}