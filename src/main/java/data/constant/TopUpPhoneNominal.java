package data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopUpPhoneNominal {
    P10K(10000),
    P20K(20000),
    P50K(50000),
    P100K(100000),
    ;
    private final long name;

    public static TopUpPhoneNominal getByOrder(int amount) {
        switch (amount) {
            case 0:
                return P10K;
            case 1:
                return P20K;
            case 2:
                return P50K;
            case 3:
                return P100K;
            default:
                throw new IllegalArgumentException("Cannot find BankCompany of amount " + amount);
        }
    }
}
