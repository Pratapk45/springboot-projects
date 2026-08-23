package com.example.demo.enums;

public enum RechargePlan {

    JIO_349(Operator.JIO, 349, 28, PlanType.UNLIMITED_5G),
    JIO_399(Operator.JIO, 399, 28, PlanType.UNLIMITED_5G),

    AIRTEL_349(Operator.AIRTEL, 349, 28, PlanType.UNLIMITED_5G),
    AIRTEL_449(Operator.AIRTEL, 449, 28, PlanType.UNLIMITED_5G),

    VI_409(Operator.VI, 409, 28, PlanType.DATA),
    VI_509(Operator.VI, 509, 28, PlanType.UNLIMITED_VOICE),

    BSNL_299(Operator.BSNL, 299, 30, PlanType.LONG_VALIDITY);


    private final Operator operator;
    private final int amount;
    private final int validityDays;
    private final PlanType planType;


     RechargePlan(
            Operator operator,
            int amount,
            int validityDays,
            PlanType planType) {

        this.operator = operator;
        this.amount = amount;
        this.validityDays = validityDays;
        this.planType = planType;
    }


    public Operator getOperator() {
        return operator;
    }


    public int getAmount() {
        return amount;
    }


    public int getValidityDays() {
        return validityDays;
    }


    public PlanType getPlanType() {
        return planType;
    }
}