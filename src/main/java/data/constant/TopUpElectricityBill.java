package data.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopUpElectricityBill {
    P50K(50000),
    P100K(100000),
    P200K(200000),
    P500K(500000),
    ;
    private final long name;

    public static TopUpElectricityBill getByOrder(int amount) {
        switch (amount) {
            case 0:
                return P50K;
            case 1:
                return P100K;
            case 2:
                return P200K;
            case 3:
                return P500K;
            default:
                throw new IllegalArgumentException("Cannot find BankCompany of amount " + amount);
        }
    }
}
