package com.sunsigne.tuto.util;

public class Cycloid<T> {

	private T[] objects;	
	private T state;
	
	private int size;
	private int index;
	
	@SuppressWarnings("unchecked")
	public Cycloid(T... objects) {
		this.objects = objects;
		state = objects[index];
		
		size = objects.length;
	}
	
	
	public T getState() {
		return state;
	}
	
	public void setState(T state) {
		this.state = state;
		synchoniseIndex();
	}
	
	public void setState(int index) {
		if(index > size)
			index = size - 1;
		
		this.index = index;
		state = objects[index];
	}
	
	// WARNING ! If several states are identical in objects,
	// this method will automatically select the index of the last one.
	private void synchoniseIndex() {
		for (int i = 0; i < size; i++) {
			if(objects[i] == state)
				index = i;
		}
	}

	public void cycle() {
		index++;
		if(index >= size)
			index = 0;
		state = objects[index];
	}
	
}
