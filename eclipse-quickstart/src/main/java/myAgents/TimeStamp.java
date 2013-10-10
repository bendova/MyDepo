package myAgents;

import uk.ac.imperial.presage2.core.Time;

public class TimeStamp implements Time{
	private double time;
	public TimeStamp()
	{
		time = System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return Double.toString(time);
	}
	@Override
	public boolean equals(Time t) {
		if((t != null) && (t instanceof TimeStamp))
		{
			return (time == ((TimeStamp)t).time);
		}
		return false;
	}

	@Override
	public void increment() {
		time++;
	}

	@Override
	public void setTime(Time t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean greaterThan(Time t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Time clone()
	{
		return new TimeStamp();
	}
}
