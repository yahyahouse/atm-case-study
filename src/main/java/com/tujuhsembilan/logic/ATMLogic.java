package com.tujuhsembilan.logic;

import com.tujuhsembilan.App;
import data.constant.*;
import data.model.Bank;
import data.model.Customer;
import data.repository.ATMRepo;
import data.repository.BankRepo;
import data.service.CurencyFormatter;
import data.service.CustomerService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tujuhsembilan.logic.ConsoleUtil.*;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ATMLogic {

    public static final CustomerService customerService = new CustomerService();
    public static final Scanner scanner = new Scanner(System.in);
    public static Customer loggedInAccount = null;
    public static final CurencyFormatter curencyFormatter = new CurencyFormatter();

    public static void login() {
        System.out.println("Login");
        int maxLoginAttempts = 3;
        int loginAttempts = 0;

        while (loginAttempts < maxLoginAttempts) {
            printDivider();
            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine();
            printDivider();
            System.out.print("Enter your PIN: ");
            String pin = scanner.nextLine();
            Customer loggedIn = customerService.findCustomerByAccountAndPin(accountNumber, pin).orElse(null);

            if (loggedIn != null && loggedIn.getAccount().equals(accountNumber) && loggedIn.getPin().equals(pin)) {
                loggedInAccount = loggedIn;
                System.out.println(loggedIn.getFullName());
                System.out.println("Login berhasil.");
                break;
            } else {
                loginAttempts++;
                int remainingAttempts = maxLoginAttempts - loginAttempts;
                System.out.println("Login gagal. Nomor rekening atau kata sandi salah. Sisa percobaan: " + remainingAttempts);
            }
        }

        if (loginAttempts >= maxLoginAttempts) {
            System.out.println("Akun diblokir karena terlalu banyak percobaan login yang gagal.");
            delay();
        }
    }

    public static void accountBalanceInformation() {
        printDivider();
        System.out.println("Saldo Anda saat ini adalah : ");
        System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
        System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
    }

    public static void moneyWithdrawal() {
        printDivider();
        int num = 1;
        for (long menu : Arrays.asList(CurrencyNominal.values()).stream()
                .map(CurrencyNominal::getName)
                .collect(Collectors.toList())) {
            System.out.println(" " + num + ". " + menu);
            num++;
        }
        printDivider();

        System.out.print(" > ");

        int selection = in.nextInt() - 1;
        if (selection >= 0 && selection < CurrencyNominal.values().length) {
            customerService.cashWithdrawal(loggedInAccount, CurrencyNominal.getByOrder(selection).getName());
            System.out.println("Penarikan uang berhasil.");
            System.out.println("Saldo Anda saat ini adalah Rp :");
            System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
            System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
        } else {
            System.out.println("Invalid input");
            delay();
        }
    }

    public static void phoneCreditsTopUp() {
        printDivider();
        System.out.println("Masukkan nomor telepon Anda: ");
        String phoneNumber = scanner.nextLine();
        if (phoneNumber.length() <= 3 || phoneNumber.length() > 15) {
            System.out.println("Nomor telepon tidak valid");
        } else {
            int num = 1;
            for (long menu : Arrays.asList(TopUpPhoneNominal.values()).stream()
                    .map(TopUpPhoneNominal::getName)
                    .collect(Collectors.toList())) {
                System.out.println(" " + num + ". " + menu);
                num++;
            }
            printDivider();

            System.out.print(" > ");

            int selection = in.nextInt() - 1;
            if (selection >= 0 && selection < TopUpPhoneNominal.values().length) {
                customerService.topUpPhoneCredit(loggedInAccount, TopUpPhoneNominal.getByOrder(selection).getName());
                System.out.println("Pengisian pulsa berhasil ke nomor " + phoneNumber + ".");
                System.out.println("Nominal pulsa yang diisi adalah Rp" + TopUpPhoneNominal.getByOrder(selection).getName());
                System.out.println("Saldo Anda saat ini adalah Rp :");
                System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
                System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
            } else {
                System.out.println("Invalid input");
                delay();
            }
        }

    }

    public static void electricityBillsToken() {
        printDivider();
        System.out.println("Mausukan nomor ID pelanggan Anda: ");
        String customerId = scanner.nextLine();
        int num = 1;
        for (long menu : Arrays.asList(TopUpElectricityBill.values()).stream()
                .map(TopUpElectricityBill::getName)
                .collect(Collectors.toList())) {
            System.out.println(" " + num + ". " + menu);
            num++;
        }
        printDivider();

        System.out.print(" > ");

        int selection = in.nextInt() - 1;
        if (selection >= 0 && selection < TopUpElectricityBill.values().length) {
            UUID uuid = UUID.randomUUID();
            customerService.topUpElectricityBill(loggedInAccount, TopUpElectricityBill.getByOrder(selection).getName());
            System.out.println("Top Up Token Listrik Berhasil dengan ID " + customerId + ".");
            System.out.println("Nominal Token Listrik yang diisi adalah Rp" + TopUpElectricityBill.getByOrder(selection).getName());
            System.out.println("Token Listrik Anda adalah " + uuid.toString().substring(0, 8) + "-" + TopUpElectricityBill.getByOrder(selection).getName());
            System.out.println("Saldo Anda saat ini adalah Rp :");
            System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
            System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
        } else {
            System.out.println("Invalid input");
            delay();
        }
    }

    public static void accountMutation() {
        printDivider();
        System.out.println("Nomor rekening tujuan: ");
        String noRekTujuan = scanner.nextLine();
        int num1 = 1;
        for (String menu : Arrays.asList(BankCompany.values()).stream()
                .map(item -> "ATM " + item.getName())
                .collect(Collectors.toList())) {
            System.out.println(" " + num1 + ". " + menu);
            num1++;
        }
        int tujuaBank = in.nextInt() - 1;
        int num2 = 1;
        for (long menu : Arrays.asList(CurrencyNominal.values()).stream()
                .map(CurrencyNominal::getName)
                .collect(Collectors.toList())) {
            System.out.println(" " + num2 + ". " + menu);
            num2++;
        }
        int nominal = in.nextInt();

        Bank bank = BankRepo.findBankByBankName(BankCompany.getByOrder(tujuaBank).getName());
        customerService.transfer(loggedInAccount, bank, CurrencyNominal.getByOrder(nominal).getName());
        System.out.println("Transfer uang ke " + noRekTujuan + " berhasil.");
        System.out.println("anda mentransfer uang sebesar Rp " + CurrencyNominal.getByOrder(nominal).getName());
        System.out.println("Saldo Anda saat ini adalah Rp :");
        System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
        System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
    }

    public static void moneyDeposit() {
        printDivider();
        System.out.println("Masukkan jumlah uang yang akan disetor: ");
        long uangSetor = scanner.nextLong();
        customerService.deposit(loggedInAccount, uangSetor);
        System.out.println("Setoran uang berhasil.");
        System.out.println("Saldo Anda saat ini adalah Rp:");
        System.out.println(curencyFormatter.formatCurrency(loggedInAccount.getBalance()));
        System.out.println(curencyFormatter.spellCurrency(loggedInAccount.getBalance().doubleValue()));
    }

    public static void universityPayment() {
        printDivider();
        System.out.println("Fitur ini belum tersedia");
    }

}
