package step4.iksoo.lottoManual;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    FAIL(0, 0);

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return this.countOfMatch;
    }

    public int getWinningMoney() {
        return this.winningMoney;
    }

    public static List<Rank> winValues() {
        return Arrays.stream(values())
                .sorted(Comparator.reverseOrder())
                .filter(n -> n.getCountOfMatch() > FAIL.getCountOfMatch())
                .collect(Collectors.toList());
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        Rank[] ranks = values();
        return Arrays.stream(ranks)
                .sorted(Comparator.reverseOrder())
                .filter(rank -> rank.countOfMatch == countOfMatch)
                .map(rank -> checkRank(rank, matchBonus))
                .findAny()
                .orElse(Rank.FAIL);
    }

    private static Rank checkRank(Rank rank, boolean matchBonus) {
        if (rank == THIRD && matchBonus)
            return SECOND;
        return rank;
    }

    public int prizeAmount(int winCount) {
        return this.getWinningMoney() * winCount;
    }
}
