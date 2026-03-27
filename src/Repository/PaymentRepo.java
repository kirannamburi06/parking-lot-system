package Repository;

import Entity.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepo {

    Map<Long, Payment> payments = new HashMap<>();

    public void add(Payment payment){
        payments.put(payment.getId(), payment);
    }

    public Map<Long, Payment> getPayments() {
        return payments;
    }

    public Payment findById(Long id){
        return payments.getOrDefault(id, null);
    }
}
