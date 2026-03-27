package Entity;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;
    private final String phone;

    public Customer(Long id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return this.id;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }
}
