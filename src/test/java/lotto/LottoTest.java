package lotto;

import java.util.stream.Stream;
import lotto.constant.LottoConstants;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    private static Stream<Arguments> provideLottoNumberList() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(1)),
                Arguments.of(List.of(1, 2)),
                Arguments.of(List.of(1, 2, 3)),
                Arguments.of(List.of(1, 2, 3, 4)),
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
        );
    }

    private static Stream<Arguments> provideDuplicatedLottoNumberList() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5,5)),
                Arguments.of(List.of(1,2,3,5,5,5)),
                Arguments.of(List.of(1,2,5,5,5,5)),
                Arguments.of(List.of(1,5,5,5,5,5)),
                Arguments.of(List.of(5,5,5,5,5,5))
        );
    }

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("로또 번호의 개수 6이 아닌 경우 이유를 나타내는 메세지를 포함한 예외가 발생한다.")
    @MethodSource("provideLottoNumberList")
    void createLottoByInvalidSize(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoConstants.INVALID_LOTTO_SIZE_MSG);
    }

    @ParameterizedTest
    @DisplayName("로또 숫자 중 중복된 값이 있는 경우 이유를 나타내는 메세지를 포함한 예외가 발생한다.")
    @MethodSource("provideDuplicatedLottoNumberList")
    void createLottoByDuplicatedNumbers(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoConstants.DUPLICATE_NUMBER_EXIST_MSG);
    }

    @Test
    @DisplayName("사용자에게 입력받은 값이 비어있는 경우 이유를 나타내는 메세지를 포함한 예외가 발생한다.")
    void createLottoByUserInputWithEmptyValue() {
        //given
        String userInput ="";
        assertThatThrownBy(() -> new Lotto(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoConstants.EMPTY_LOTTO_VALUE_MSG);
    }
}
