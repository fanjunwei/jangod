package net.asfun.template.tag;

public class ForLoop {

	private int index = 0;
	private int counter;
	private int revindex;
	private int revcounter;
	private int length;
	private boolean first = true;
	private boolean last;
	
	public ForLoop(int len) {
		length = len;
		if (len < 2) {
			revindex = 0;
			counter = len;
			revcounter = len;
			last = true;
		} else {
			revindex = len -1;
			counter = 1;
			revcounter = len;
			last = false;
		}
	}
	
	public void next() {
		if (counter < length) {
			index++;
			counter++;
			revindex--;
			revcounter--;
			first = false;
			if (counter == length) {
				last = true;
			}
		}
	}

	public int getIndex() {
		return index;
	}

	public int getCounter() {
		return counter;
	}

	public int getRevindex() {
		return revindex;
	}

	public int getRevcounter() {
		return revcounter;
	}

	public int getLength() {
		return length;
	}

	public boolean isFirst() {
		return first;
	}

	public boolean isLast() {
		return last;
	}
	
}
