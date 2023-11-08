package com.tujuhsembilan.logic;

import data.model.Bank;
import data.model.Customer;
import data.repository.ATMRepo;
import data.service.CustomerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ATMLogic {

    public static final CustomerService customerService = new CustomerService();
    public static final Scanner scanner = new Scanner(System.in);
    public static Customer loggedInAccount = null;
    public static void login() {
        System.out.println("Login");
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        Customer loggedIn = customerService.findCustomerByAccountAndPin(accountNumber, pin).orElse(null);
        if (loggedIn.getAccount().equals(accountNumber) && loggedIn.getPin().equals(pin)) {
            loggedInAccount = loggedIn;
            System.out.println(loggedIn.getFullName());
            System.out.println("Login berhasil.");
        } else {
            System.out.println("Login gagal. Nomor rekening atau kata sandi salah.");
        }
    }

    public static void accountBalanceInformation() {
    }

    public static void moneyWithdrawal() {
    }

    public static void phoneCreditsTopUp() {
    }

    public static void electricityBillsToken() {
    }

    public static void accountMutation() {
    }

    public static void moneyDeposit() {
    }

}
