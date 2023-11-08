package data.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurencyFormatter {
    public String formatCurrency(BigDecimal amount) {
        DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        currencyFormat.setGroupingUsed(true);
        currencyFormat.setPositivePrefix("Rp ");
        return currencyFormat.format(amount);
    }

    public String spellCurrency(double amount) {
        String[] units = { "", "Ribu", "Juta", "Miliar", "Triliun" };

        int group = 0;
        while (amount >= 1000) {
            amount /= 1000;
            group++;
        }

        String spelledAmount = spellAmount(amount);
        return spelledAmount + " " + units[group] + " Rupiah";
    }

    public String spellAmount(double amount) {
        String[] numbers = { "Nol", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan" };
        int wholePart = (int) amount;

        if (wholePart == 0) {
            return "Nol";
        }

        String spelledAmount = "";
        while (wholePart > 0) {
            int digit = wholePart % 10;
            spelledAmount = numbers[digit] + " " + spelledAmount;
            wholePart /= 10;
        }

        return spelledAmount.trim();
    }
}
