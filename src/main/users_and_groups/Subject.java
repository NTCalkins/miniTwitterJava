package main.users_and_groups;

/**
 * Subject for Observer design pattern
 * 
 * @author Nicholas Calkins
 *
 */
public interface Subject {
	
    public void attach(Observer observer);
    
    public void notifyObservers();
}
