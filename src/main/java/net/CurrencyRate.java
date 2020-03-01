package net;

import java.math.BigDecimal;

public class CurrencyRate {
    private final String currency;
    private final String code;
    private final BigDecimal mid;

    public CurrencyRate(String currency, String code, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getMid() {
        return mid;
    }

    @Override
    public String toString() {
        return currency;
    }
}
