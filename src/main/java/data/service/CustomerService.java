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
    public void topUpElectricityBill(Customer customer, long amount) {
        long balance = customer.getBalance().longValue();
        customer.setBalance(BigDecimal.valueOf(balance - amount));
    }
    public void deposit(Customer customer, long amount) {
        long balance = customer.getBalance().longValue();
        customer.setBalance(BigDecimal.valueOf(balance + amount));
    }

    public void transfer(Customer customer,Bank bank, long amount) {
        Bank customerBankName = bankRepo.findBankByCustomerName(customer.getAccount());
        if (customerBankName.getName().equals(bank.getName())){
            long balance = customer.getBalance().longValue();
            customer.setBalance(BigDecimal.valueOf(balance - amount));
        }else {
            int fee = 2500;
            long balance = customer.getBalance().longValue();
            customer.setBalance(BigDecimal.valueOf(balance - amount-fee));
        }

    }
}
