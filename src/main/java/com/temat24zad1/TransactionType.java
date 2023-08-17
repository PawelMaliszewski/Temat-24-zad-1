package com.temat24zad1;

public enum TransactionType {
    SPENDING("Wydatek"), INCOME("Przychód");

    private String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static TransactionType transactionTypeByString(String name) {
        for (TransactionType type : TransactionType.values()) {
            if (type.description.equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
}


