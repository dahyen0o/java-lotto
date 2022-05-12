package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

  private static final Pattern WINNING_NUMBER_PATTERN = Pattern.compile("^(\\d+, ){5}\\d+$");

  private static final String INPUT_PURCHASED_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
  private static final String INPUT_LAST_WEEK_WINNING_LOTTO_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
  private static final String WINNING_NUMBER_PATTERN_NOT_MATCH_ERROR_MESSAGE = "입력된 우승 로또 번호는 %s으로 숫자 이외의 문자가 입력되었습니다.";
  private static final String INPUT_BONUS_LOTTO_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";
  private static final String INPUT_NUMBER_OF_MANUAL_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요.";
  private static final String INPUT_OVER_FROM_STANDARD_ERROR_MESSAGE = "전체 구매한 로또 %d개 보다 더 많은 수동 로또의 %d개를 입력했습니다.";
  private static final String INPUT_MANUAL_LOTTO_NUMBER_PROMPT = "수동으로 구매할 번호를 입력해 주세요.";

  private static final Scanner SCANNER = new Scanner(System.in);

  private InputView() {
    throw new AssertionError();
  }

  public static String inputPurchasedAmount() {
    System.out.println(INPUT_PURCHASED_AMOUNT_PROMPT);
    return SCANNER.nextLine();
  }

  public static String inputLastWeekWinningLotto() {
    System.out.println(INPUT_LAST_WEEK_WINNING_LOTTO_NUMBERS_PROMPT);
    String winningLottoNumber = SCANNER.nextLine();

    validate(winningLottoNumber);

    return winningLottoNumber;
  }

  private static void validate(String winningLottoNumber) {
    if (!WINNING_NUMBER_PATTERN.matcher(winningLottoNumber).matches()) {
      throw new IllegalArgumentException(
          String.format(WINNING_NUMBER_PATTERN_NOT_MATCH_ERROR_MESSAGE, winningLottoNumber));
    }
  }

  public static int inputBonusLottoNumber() {
    System.out.println(INPUT_BONUS_LOTTO_NUMBER_PROMPT);
    return SCANNER.nextInt();
  }

  public static int inputNumberOfManual(int purchasedLotto) {
    System.out.println(INPUT_NUMBER_OF_MANUAL_PROMPT);
    int purchasedManual = Integer.parseInt(SCANNER.nextLine());

    validateOverPurchasedLotto(purchasedManual, purchasedLotto);

    return purchasedManual;
  }

  public static List<String> inputManualLottoNumbers(int purchasedManual) {
    List<String> manualLottos = new ArrayList<>();

    System.out.println(INPUT_MANUAL_LOTTO_NUMBER_PROMPT);

    for (int i = 0; i < purchasedManual; i++) {
      String lotto = SCANNER.nextLine();
      validate(lotto);
      manualLottos.add(lotto);
    }

    return manualLottos;
  }

  private static void validateOverPurchasedLotto(int purchasedManual, int purchasedLotto) {
    if (purchasedManual > purchasedLotto) {
      throw new IllegalArgumentException(
          String.format(INPUT_OVER_FROM_STANDARD_ERROR_MESSAGE, purchasedLotto, purchasedManual));
    }
  }
}
