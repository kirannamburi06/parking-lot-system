package Entity;

public class Payment {

    private final Long id;
    private final Ticket ticket;
    private final double amount;

    public Payment(Long id, Ticket ticket, double amount) {
        this.id = id;
        this.ticket = ticket;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
