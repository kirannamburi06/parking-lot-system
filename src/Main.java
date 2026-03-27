import Entity.Customer;
import Entity.ParkingLot;
import Entity.ParkingSpot.ParkingSpot;
import Entity.Payment;
import Entity.Ticket;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleSize;
import Repository.CustomerRepo;
import Repository.PaymentRepo;
import Repository.TicketRepo;
import Repository.VehicleRepo;
import Service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no of Spots in each row: ");
        int spots = sc.nextInt();

        System.out.println("Enter no of Rows in each level : ");
        int rows = sc.nextInt();

        System.out.println("Enter no of Levels in the parking lot: ");
        int levels = sc.nextInt();

        ParkingLotService parkingLotService = new ParkingLotService();
        ParkingLot parkingLot = parkingLotService.initializeParkingLot(levels,
                rows, spots);

        CustomerRepo customerRepo = new CustomerRepo();
        CustomerService customerService = new CustomerService(customerRepo);

        VehicleRepo vehicleRepo = new VehicleRepo();
        VehicleService vehicleService = new VehicleService(vehicleRepo);

        TicketRepo ticketRepo = new TicketRepo();
        TicketService ticketService = new TicketService(ticketRepo);

        PaymentRepo paymentRepo = new PaymentRepo();
        PaymentService paymentService = new PaymentService(paymentRepo, ticketService);

        Customer customer = null;
        Ticket ticket = null;
        while(true){

            System.out.println("Enter your choice:\n" +
                    "[1 : Get current status] \n" +
                    "[2: Add a customer] \n" +
                    "[3: Get all customers] \n" +
                    "[4: Generate a ticket] \n" +
                    "[5: View all tickets] \n" +
                    "[6: Generate payment] \n" +
                    "[7: View all payments] \n");
            int ch = sc.nextInt();

            switch (ch){
                case 1:
                    System.out.println("=========CURRENT STATUS========\n");
                    parkingLotService.status(parkingLot);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("=========Add Customer Dashboard=========\n");
                    System.out.println("Enter customer name : ");
                    sc.nextLine();
                    String customerName = sc.nextLine();
                    System.out.println("Enter customer email: ");
                    String email = sc.nextLine();
                    System.out.println("Enter customer phone: ");
                    String phone = sc.nextLine();

                    customer = customerService.addCustomer(customerName, email, phone);

                    System.out.println("Customer registered successfully.");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("==========Customers==========\n");
                    List<Customer> customers = customerService.getCustomers();
                    for (Customer c : customers){
                        System.out.println("Id: " + c.getId() +
                        "\nName : " + c.getName() +
                                "\nEmail : " + c.getEmail() +
                                "\nPhone : " + c.getPhone());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("========Generate a ticket=========\n");

                    if(customer == null){
                        System.out.println("Please register a customer first!");
                        System.out.println();
                        break;
                    }

                    System.out.println("Enter vehicle size (small, medium, large) : ");
                    sc.nextLine();
                    String size = sc.nextLine();

                    VehicleSize vehicleSize;
                    if(size.equalsIgnoreCase("small")){
                        vehicleSize = VehicleSize.SMALL;
                    } else if(size.equalsIgnoreCase("medium")){
                        vehicleSize = VehicleSize.MEDIUM;
                    } else if(size.equalsIgnoreCase("large")){
                        vehicleSize = VehicleSize.LARGE;
                    } else{
                        System.out.println("Enter a valid size!\n");
                        break;
                    }

                    List<ParkingSpot> parkingSpots = parkingLotService.findNearestParkingSpots(parkingLot, vehicleSize);
                    if(parkingSpots == null){
                        System.out.println("No parking spots available! ");
                        System.out.println();
                        break;
                    }

                    System.out.println("Enter vehicle number: ");
                    String vehicleNumber = sc.nextLine();

                    Vehicle vehicle = vehicleService.addVehicle(vehicleNumber, vehicleSize);

                    for (ParkingSpot parkingSpot : parkingSpots){
                        parkingSpot.occupySpot(vehicle);
                    }

                    ticket = ticketService.generateTicket(customer, vehicle, parkingSpots);
                    System.out.println("Ticket booked successfully");
                    System.out.println("Ticket id : " + ticket.getId() + "\n" +
                            "Customer name : " + ticket.getCustomer().getName() + "\n" +
                            "Vehicle Number : " + ticket.getVehicle().getVehicleNumber() + "\n" +
                            "Entry Time : " + ticket.getEntryTime() + "\n");
                    System.out.println("Allocated spots: ");
                    for(ParkingSpot parkingSpot : parkingSpots){
                        System.out.println("Spot id : " + parkingSpot.getSpotId() +
                                "\nRow id: " + parkingSpot.getParkingRow().getId() +
                                "\nLevel id: " + parkingSpot.getParkingRow().getParkingLevel().getId() +
                                "\nSpot type: " + parkingSpot.getParkingSpotType());
                    }

                    System.out.println();
                    break;
                case 5:
                    System.out.println("========View all tickets=========\n");

                    List<Ticket> tickets = ticketService.getAllTickets();
                    for (Ticket t : tickets){
                        System.out.println("Ticket id : " + t.getId() + "\n" +
                                "Customer name : " + t.getCustomer().getName() + "\n" +
                                "Vehicle Number : " + t.getVehicle().getVehicleNumber() + "\n" +
                                "Entry Time : " + t.getEntryTime() + "\n");
                    }

                    System.out.println();
                    break;
                case 6:
                    System.out.println("========Generate Payment=========\n");
                    System.out.println("Enter ticket id: ");
                    Long id = sc.nextLong();

                    Payment payment = paymentService.generatePayment(id);
                    if(payment == null){
                        System.out.println();
                        break;
                    }

                    System.out.println("Total payable amount : " + payment.getAmount());
                    System.out.println("Processing payment...........\n Payment successful!");

                    Ticket t = ticketService.findById(id);
                    for(ParkingSpot parkingSpot : t.getSpots()){
                        parkingSpot.vacate();
                    }

                    System.out.println();
                    break;
                case 7:
                    System.out.println("========View all Payments=========\n");
                    List<Payment> payments = paymentService.getAllPayments();

                    for(Payment p : payments){
                        System.out.println("Ticket id : " + p.getTicket().getId() + "\nAmount : " + p.getAmount());
                    }

                    System.out.println();
                    break;
                default: break;
            }

        }


    }
}
