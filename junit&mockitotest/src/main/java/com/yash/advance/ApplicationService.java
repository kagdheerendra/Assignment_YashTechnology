package com.yash.advance;

public class ApplicationService {
    Application application[] = new Application[0];
	int size;  // point the last product in array
	int capacity;  // define the capacity of array
    
    public ApplicationService() {
		// TODO Auto-generated constructor stub
    	application = new Application[2];
        size=0;
        capacity=2;
	}
    
    public void ensureCapacity(int minCapacity){
    	Application a[] = new Application[capacity*minCapacity];
        for (int i=0; i < capacity; i++){
            a[i] = application[i];
        }
        application = a;
        capacity = capacity * minCapacity;
    }
    
	public String addApplication(Application a){
		if (size == capacity){
            ensureCapacity(2); 
        }
        application[size] = a;
        size++;
        return a.toString();
	}
	
	public Application getApplicationById(int id) {
		Application ap = new Application();
		System.out.println(size);
		for(int i=0; i<size; i++) {
			System.out.println(application[i].toString());
			if(application[i].getId() == id) {
				ap = application[i];
			}
		}
		return ap;
	}
	
	Application updateApplication(int applicationId , String name) {
		Application a = new Application();
		for(int i=0; i<size; i++) {
			if(application[i].getId() == applicationId) {
				application[i].setName(name);
				a = application[i];
			}
		}
		return a;
	}
	
	public Application[] getAllApplications() {
		Application p[] = new Application[size];
		for(int i=0; i<size; i++) {
           p[i] = application[i];
		}
		return p;
	}
}
