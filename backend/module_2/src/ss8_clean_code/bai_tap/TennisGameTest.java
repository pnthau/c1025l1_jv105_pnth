package module_2.src.ss8_clean_code.bai_tap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

public class TennisGameTest {

    // 1. Xóa các biến toàn cục và Constructor cũ đi
    // JUnit 5 sẽ truyền thẳng tham số vào phương thức test bên dưới

    // Hàm cung cấp dữ liệu giữ nguyên (nhưng cần là static)
    public static Collection<Object[]> getAllScores() {
        return Arrays.asList(new Object[][]{
                {0, 0, "Love-All"},
                {1, 1, "Fifteen-All"},
                {2, 2, "Thirty-All"},
                {3, 3, "Forty-All"},
                {4, 4, "Deuce"},

                {1, 0, "Fifteen-Love"},
                {0, 1, "Love-Fifteen"},
                {2, 0, "Thirty-Love"},
                {0, 2, "Love-Thirty"},
                {3, 0, "Forty-Love"},
                {0, 3, "Love-Forty"},
                {4, 0, "Win for player1"},
                {0, 4, "Win for player2"},

                {2, 1, "Thirty-Fifteen"},
                {1, 2, "Fifteen-Thirty"},
                {3, 1, "Forty-Fifteen"},
                {1, 3, "Fifteen-Forty"},
                {4, 1, "Win for player1"},
                {1, 4, "Win for player2"},

                {3, 2, "Forty-Thirty"},
                {2, 3, "Thirty-Forty"},
                {4, 2, "Win for player1"},
                {2, 4, "Win for player2"},

                {4, 3, "Advantage player1"},
                {3, 4, "Advantage player2"},
                {5, 4, "Advantage player1"},
                {4, 5, "Advantage player2"},
                {15, 14, "Advantage player1"},
                {14, 15, "Advantage player2"},

                {6, 4, "Win for player1"},
                {4, 6, "Win for player2"},
                {16, 14, "Win for player1"},
                {14, 16, "Win for player2"},
        });
    }

    @ParameterizedTest
    @MethodSource("getAllScores")
    // 2. Thêm tham số trực tiếp vào hàm checkAllScores
    public void checkAllScores(int player1Score, int player2Score, String expectedScore) {

        // Logic giả lập điểm số (giữ nguyên logic của bạn nhưng thay đổi biến)
        int highestScore = Math.max(player1Score, player2Score);
        int m_score1 = 0;
        int m_score2 = 0;

        for (int i = 0; i < highestScore; i++) {
            if (i < player1Score) // Bỏ 'this.'
                m_score1 += 1;
            if (i < player2Score) // Bỏ 'this.'
                m_score2 += 1;
        }

        // Gọi hàm getScore cần test
        assertEquals(expectedScore, TennisGame.getScore("John", "Bill", m_score1, m_score2));
    }
}