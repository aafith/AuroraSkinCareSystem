public abstract class Person {
    protected String name;
    protected String email;

    public String getName() {
        return name;
    }
    
    // This method is abstract, so it must be implemented by the subclasses
    public abstract String getInfo();
}
