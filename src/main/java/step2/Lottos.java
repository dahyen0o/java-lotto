package step2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(LottoNumbers... numbers) {
        this.lottos = Arrays.stream(numbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public Lottos(List<LottoNumbers> numbers) {
        this.lottos = numbers
                .stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public Lottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        this.lottos = new ArrayList<Lotto>() {{
            addAll(manualLottos);
            addAll(autoLottos);
        }};
    }

    public int getCount() {
        return this.lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public WinResults getWinResults(LottoNumbers answer, LottoNumber bonusNumber) {
        return new WinResults(
                this.lottos.stream()
                        .map(item -> item.getWinResult(answer, bonusNumber))
                        .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return lottos.stream().map(Lotto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
