package lotto.model.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class LottoResult {

    public static final int DEFAULT_VALUE = 0;

    private final EnumMap<Rank, Integer> lottoResult;
    private final double profitRate;

    public LottoResult(final List<Rank> ranks, final LottoMoney lottoMoney) {
        this.lottoResult = new EnumMap<>(Rank.class);
        ranks.forEach(rank -> this.lottoResult.put(
                rank,
                this.lottoResult.getOrDefault(rank, DEFAULT_VALUE) + 1
        ));
        this.profitRate = calculateProfitRate(lottoMoney);
    }

    private double calculateProfitRate(final LottoMoney lottoMoney) {
        long totalPrize = lottoResult.entrySet().stream()
                .mapToLong(entry -> Rank.getTotalPrize(entry.getKey(), entry.getValue()))
                .sum();
        return totalPrize / lottoMoney.getPurchasedMoney();
    }

    public int getCount(final Rank rank) {
        return lottoResult.getOrDefault(rank, DEFAULT_VALUE);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
