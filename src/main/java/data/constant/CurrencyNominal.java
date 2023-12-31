package data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyNominal {
    M10K(10000),
    M20K(20000),
    M50K(50000),
    M100K(100000),
    ;
    private final long name;

    public static CurrencyNominal getByOrder(int amount) {
        switch (amount) {
            case 1:
                return M10K;
            case 2:
                return M20K;
            case 3:
                return M50K;
            case 4:
                return M100K;
            default:
                throw new IllegalArgumentException("Cannot find BankCompany of amount " + amount);
        }
    }
}
