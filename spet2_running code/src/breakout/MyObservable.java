package breakout;

public interface MyObservable 
{
	//LinkedList listObservers;
	
	public void registerObserver(MyObserver observer);
	public void unRegisterObserver(MyObserver observer);
	public void notifyAllObservers();

}
