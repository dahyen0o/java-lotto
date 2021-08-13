package lotto.domain;

import java.util.Objects;

public class ProfitRate {
    private final double profitRate;

    private ProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

    public static ProfitRate of(double profitRate) {
        return new ProfitRate(profitRate);
    }

    public double value() {
        return profitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfitRate that = (ProfitRate) o;
        return Double.compare(that.profitRate, profitRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profitRate);
    }
}