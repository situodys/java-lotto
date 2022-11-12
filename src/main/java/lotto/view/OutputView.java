package lotto.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Result;

import static lotto.constant.OutputViewConstants.*;

public class OutputView {

    public static void announcePayNotice() {
        System.out.println(PAY_NOTICE);
    }

    public static void announceLottos(Lottos lottos) {
        System.out.println(lottos.countLottos()+ PAY_COUNT_NOTICE);
        for (Lotto lotto : lottos.provideLottos()) {
            System.out.println(LOTTO_PREFIX+lotto+LOTTO_SUFFIX);
        }
    }

    public static void announceError(String msg) {
        System.out.println(ERR_PREFIX+msg);
    }

    public static void announceWinningLottoInputNotice() {
        System.out.println(WINNING_LOTTO_INPUT_NOTICE);
    }

    public static void announceBonusBallInputNotice() {
        System.out.println(BONUSBALL_INPUT_NOTICE);
    }

    public static void announceResult(Result result, String profitRate) {
        NumberFormat numberFormat = new DecimalFormat("#,##0.0");

        System.out.println(RESULT_HEADER);
        System.out.print(result);
        System.out.println(PROFIT_RATE_PREFIX+numberFormat.format(Double.parseDouble(profitRate))+PROFIT_RATE_SUFFIX);
    }
}
