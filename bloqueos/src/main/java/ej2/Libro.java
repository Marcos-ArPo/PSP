package ej2;

public class Libro {
    private final String id;
    
    public Libro(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return id;
    }
}