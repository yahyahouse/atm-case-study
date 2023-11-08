package data.service;

import data.model.Bank;
import data.model.Customer;
import data.repository.ATMRepo;
import data.repository.BankRepo;

import java.math.BigDecimal;
import java.util.Optional;

public class CustomerService {
    private static final ATMRepo atmRepo = new ATMRepo();
    private static final BankRepo bankRepo = new BankRepo();
    public Optional<Customer> findCustomerByAccountAndPin(String account, String pin) {
        return bankRepo.findBankByAccountAndPin(account, pin);
    }

    public void cashWithdrawal(Customer customer, long amount) {
        long balance = customer.getBalance().longValue();
        customer.setBalance(BigDecimal.valueOf(balance - amount));
    }
    public void topUpPhoneCredit(Customer customer, long amount) {
        long balance = customer.getBalance().longValue();
        customer.setBalance(BigDecimal.valueOf(balance - amount));
    }
}
