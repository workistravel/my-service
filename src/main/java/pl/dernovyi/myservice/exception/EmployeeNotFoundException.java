package pl.dernovyi.myservice.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id, String name){
        super("Could not find " + id + " " + name);
    }
    public EmployeeNotFoundException(Long id){
        super("Could not find employee " + id );
    }
}
