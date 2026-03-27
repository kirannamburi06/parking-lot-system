package Repository;

import Entity.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepo {

    Map<Long, Ticket> tickets = new HashMap<>();

    public void add(Ticket ticket){
        tickets.put(ticket.getId(), ticket);
    }

    public Map<Long, Ticket> getTickets() {
        return tickets;
    }

    public Ticket findById(Long id){
        return tickets.getOrDefault(id, null);
    }
}
