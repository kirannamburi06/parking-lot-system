package Service;

import Entity.Customer;
import Entity.ParkingSpot.ParkingSpot;
import Entity.Ticket;
import Entity.Vehicle.Vehicle;
import Repository.TicketRepo;

import java.util.List;

public class TicketService {

    private final TicketRepo ticketRepo;
    private Long id = 1L;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket generateTicket(Customer customer, Vehicle vehicle, List<ParkingSpot> parkingSpots){
        Ticket ticket = new Ticket(id++, parkingSpots, customer, vehicle);
        ticketRepo.add(ticket);

        return ticket;
    }

    public List<Ticket> getAllTickets(){
        return ticketRepo.getTickets().values().stream().toList();
    }

    public Ticket findById(Long id){
        return ticketRepo.findById(id);
    }
}
