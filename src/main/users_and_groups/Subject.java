package main.users_and_groups;

public interface Subject {
	
    public void attach(Observer observer);
    
    public void notifyObservers();
}
