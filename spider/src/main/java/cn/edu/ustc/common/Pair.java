package cn.edu.ustc.common;

public class Pair<F, S> implements Comparable<Pair<F, S>>{
	private F first;
	private S second;

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public String toString() {
		return String.format("[first: %1$s,second: %2$s]", this.first.toString(),this.second.toString());
	}

	@Override
	public int compareTo(Pair<F, S> o) {
		if(this.first.equals(o.first.toString()) && this.second.toString().equals(o.toString()))
			return 0;
		return 1;
	}

	public int hashCode()
	{
		String s=this.first.toString()+this.second.toString();
		int hashCode=0;
		for(int i=0;i<s.length();i++)
			hashCode+=s.charAt(i);
		return hashCode;
	}
}
