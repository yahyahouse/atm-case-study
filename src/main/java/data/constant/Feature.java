package data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Feature {
    VIEW_BALANCE("View Balance"),
    WITHDRAW("Withdraw"),
    TOP_UP_PHONE_CREDIT("Top Up Phone Credit"),
    TOP_UP_ELECTRICITY_BILL("Top Up Electricity Bill"),
    UNIVERSITY_PAYMENT("University Payment"),
    ACCOUNT_MUTATION("Account Mutation"),
    DEPOSIT("Deposit"),

    ;
    private final String name;
    public static Feature getByOrder(int order) {
        switch (order) {
            case 0:
                return VIEW_BALANCE;
            case 1:
                return WITHDRAW;
            case 2:
                return TOP_UP_PHONE_CREDIT;
            case 3:
                return TOP_UP_ELECTRICITY_BILL;
            case 4:
                return UNIVERSITY_PAYMENT;
            case 5:
                return ACCOUNT_MUTATION;
            case 6:
                return DEPOSIT;
            default:
                throw new IllegalArgumentException("Cannot find Featur of order " + order);
        }
    }
}
