package lotto.model.domain;

import java.util.Map;

public final class LottoResult {

    public static final int DEFAULT_VALUE = 0;

    private final Map<Rank, Integer> lottoResult;
    private final double profitRate;

    private LottoResult(final Ranks ranks, final LottoMoney purchase) {
        this.lottoResult = ranks.allCounts();
        this.profitRate = calculateProfitRate(purchase);
    }

    public static LottoResult of(final Ranks ranks, final LottoMoney purchase) {
        return new LottoResult(ranks, purchase);
    }

    private double calculateProfitRate(final LottoMoney purchase) {
        final LottoMoney totalPrize = LottoMoney.valueOf(calculateTotalPrize());
        return totalPrize.rate(purchase);
    }

    private long calculateTotalPrize() {
        return lottoResult.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().getTotalPrize(entry.getValue()))
                .sum();
    }

    public int getCount(final Rank rank) {
        return lottoResult.getOrDefault(rank, DEFAULT_VALUE);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
