package rad.concurency;

public class Razak implements Comparable<Razak>{

	private double age;
	
	private long time;

	public Razak(double age, long time) {
		super();
		this.age = age;
		this.time = time;
	}

	public double getAge() {
		return age;
	}

	public long getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Razak other = (Razak) obj;
		if (time != other.time)
			return false;
		return true;
	}

/*	@Override
	public int compareTo(Razak o) {
		
		return Long.valueOf(this.getTime()).compareTo(Long.valueOf(o.getTime()));
	}
*/
	@Override
	public String toString() {
		return "Razak [age=" + age + ", time=" + time + "]";
	}

	@Override
	public int compareTo(Razak o) {
		Long current=this.getTime();
		Long others=o.getTime();
		return current.compareTo(others);
	}

	
	
	
}
