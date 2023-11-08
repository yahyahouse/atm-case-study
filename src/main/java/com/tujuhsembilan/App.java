package com.tujuhsembilan;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tujuhsembilan.logic.ATMLogic;

import data.constant.BankCompany;
import data.constant.Feature;
import data.model.ATM;
import data.model.Bank;
import data.repository.ATMRepo;
import data.repository.BankRepo;

import static com.tujuhsembilan.logic.ConsoleUtil.*;

public class App {

    public static void main(String[] args) {
        boolean loop = true;
        while (loop) {
            printClear();

            int num = 1;
            for (String menu : Arrays.asList(BankCompany.values()).stream()
                    .map(item -> "ATM " + item.getName())
                    .collect(Collectors.toList())) {
                System.out.println(" " + num + ". " + menu);
                num++;
            }
            printDivider('-');
            System.out.println(" 0. EXIT");
            printDivider();

            System.out.print(" > ");
            int selection = in.nextInt() - 1;
            if (selection >= 0 && selection < BankCompany.values().length) {
                new App(BankCompany.getByOrder(selection).getName()).start();
            } else if (selection == -1) {
                loop = false;
            } else {
                System.out.println("Invalid input");
                delay();
            }
        }
    }

    /// --- --- --- --- ---

    final Bank bank;
    final ATM atm;

    public App(String bankName) {
        Bank lBank = null;
        ATM lAtm = null;

        Optional<Bank> qBank = BankRepo.findBankByName(bankName);
        if (qBank.isPresent()) {
            Optional<ATM> qAtm = ATMRepo.findATMByBank(qBank.get());
            if (qAtm.isPresent()) {
                lBank = qBank.get();
                lAtm = qAtm.get();
            }
        }

        this.bank = lBank;
        this.atm = lAtm;
    }

    public void start() {

        if (bank != null && atm != null) {
            ATMLogic.login();
            boolean loop = ATMLogic.loggedInAccount != null;
            while (loop) {
                int num = 1;
                for (String menu : Arrays.asList(Feature.values()).stream()
                        .map(Feature::getName)
                        .collect(Collectors.toList())) {
                    System.out.println(" " + num + ". " + menu);
                    num++;
                }
                System.out.println(" 0. EXIT");

                printDivider();

                System.out.print(" > ");
                int selection = in.nextInt();
                if (selection >= 0 && selection < Feature.values().length) {
                    menuBank(selection);
                } else if (selection == -1) {
                    loop = false;
                } else {
                    System.out.println("Invalid input");
                    delay();
                }
            }

        } else {
            System.out.println("Cannot find Bank or ATM");
            delay();
        }
    }

    public void menuBank(int selection) {
        switch (selection) {
            case 1:
                ATMLogic.accountBalanceInformation();
                break;
            case 2:
                ATMLogic.moneyWithdrawal();
                break;
            case 3:
                ATMLogic.phoneCreditsTopUp();
                break;
            case 4:
                ATMLogic.electricityBillsToken();
                break;
            case 5:
                ATMLogic.universityPayment();
                break;
            case 6:
                ATMLogic.accountMutation();
                break;
            case 7:
                ATMLogic.moneyDeposit();
                break;
            default:
                System.out.println("Invalid input");
                delay();
                break;
        }

    }

}

