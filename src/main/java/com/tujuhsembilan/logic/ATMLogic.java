package com.tujuhsembilan.logic;

import com.tujuhsembilan.App;
import data.constant.CurrencyNominal;
import data.constant.Feature;
import data.constant.TopUpPhoneNominal;
import data.model.Bank;
import data.model.Customer;
import data.repository.ATMRepo;
import data.service.CustomerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.tujuhsembilan.logic.ConsoleUtil.*;

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
        assert loggedIn != null;
        if (loggedIn.getAccount().equals(accountNumber) && loggedIn.getPin().equals(pin)) {
            loggedInAccount = loggedIn;
            System.out.println(loggedIn.getFullName());
            System.out.println("Login berhasil.");
        } else {
            System.out.println("Login gagal. Nomor rekening atau kata sandi salah.");
        }
    }

    public static void accountBalanceInformation() {
        System.out.println("Saldo Anda saat ini adalah Rp" + loggedInAccount.getBalance());
    }

    public static void moneyWithdrawal() {
        int num = 1;
        for (long menu : Arrays.asList(CurrencyNominal.values()).stream()
                .map(CurrencyNominal::getName)
                .collect(Collectors.toList())) {
            System.out.println(" " + num + ". " + menu);
            num++;
        }
        printDivider();

        System.out.print(" > ");

        int selection = in.nextInt()-1;
        if (selection >= 0 && selection < CurrencyNominal.values().length) {
            customerService.cashWithdrawal(loggedInAccount, CurrencyNominal.getByOrder(selection).getName());
            System.out.println("Penarikan uang berhasil.");
            System.out.println("Saldo Anda saat ini adalah Rp" + loggedInAccount.getBalance());
        } else {
            System.out.println("Invalid input");
            delay();
        }
    }

    public static void phoneCreditsTopUp() {
        System.out.println("Masukkan nomor telepon Anda: ");
        String phoneNumber = scanner.nextLine();
        int num = 1;
        for (long menu : Arrays.asList(TopUpPhoneNominal.values()).stream()
                .map(TopUpPhoneNominal::getName)
                .collect(Collectors.toList())) {
            System.out.println(" " + num + ". " + menu);
            num++;
        }
        printDivider();

        System.out.print(" > ");

        int selection = in.nextInt()-1;
        if (selection >= 0 && selection < TopUpPhoneNominal.values().length) {
            customerService.topUpPhoneCredit(loggedInAccount, TopUpPhoneNominal.getByOrder(selection).getName());
            System.out.println("Pengisian pulsa berhasil ke nomor " + phoneNumber + ".");
            System.out.println("Nominal pulsa yang diisi adalah Rp" + TopUpPhoneNominal.getByOrder(selection).getName());
            System.out.println("Saldo Anda saat ini adalah Rp" + loggedInAccount.getBalance());
        } else {
            System.out.println("Invalid input");
            delay();
        }
    }

    public static void electricityBillsToken() {
    }

    public static void accountMutation() {
    }

    public static void moneyDeposit() {
    }

}
