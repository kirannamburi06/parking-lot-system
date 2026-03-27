package Service;

import Entity.Payment;
import Entity.Ticket;
import Repository.PaymentRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final TicketService ticketService;
    private Long id = 1L;

    public PaymentService(PaymentRepo paymentRepo, TicketService ticketService) {
        this.paymentRepo = paymentRepo;
        this.ticketService = ticketService;
    }

    public Payment generatePayment(Long ticketId){

        Ticket ticket = ticketService.findById(id);
        if(ticket == null){
            System.out.println("Ticket not found!");
            return null;
        }

        double amount = calculateAmount(ticket.getEntryTime());

        Payment payment = new Payment(id++, ticket, amount);
        paymentRepo.add(payment);

        return payment;
    }

    private double calculateAmount(LocalDateTime entryTime){

        LocalDateTime currentTime = LocalDateTime.now();

        Duration duration = Duration.between(entryTime, currentTime);

        long totalMinutes = duration.toMinutes();

        long days = totalMinutes / (24 * 60);
        long hours = (totalMinutes % (24 * 60)) / 60;
        long minutes = totalMinutes % 60;

        int perDay = 1000;
        int perHour = 500;
        int perMinute = 10;

        return (double) (days * perDay + hours * perHour + minutes * perMinute);
    }

    public List<Payment> getAllPayments(){
        return new ArrayList<>(paymentRepo.getPayments().values());
    }
}