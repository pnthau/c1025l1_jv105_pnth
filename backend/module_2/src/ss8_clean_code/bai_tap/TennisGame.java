package module_2.src.ss8_clean_code.bai_tap;

import org.jetbrains.annotations.NotNull;

public class TennisGame {

    public static final int LOVE = 0;
    public static final int FIFTEEN = 1;
    public static final int THIRTY = 2;
    public static final int FOURTY = 3;
    public static final int MAX_POINT_TURN = 4;

    public static String getScore(String player1Name, String player2Name, int pointsPlayer1, int pointsPlayer2) {
        String currentResult = "";
        boolean isDeuce = pointsPlayer1 == pointsPlayer2;
        if (isDeuce) {
            currentResult = getDeuceName(pointsPlayer1);
        } else {
            boolean isAdvantagePlayerOrWinner = pointsPlayer1 >= MAX_POINT_TURN || pointsPlayer2 >= MAX_POINT_TURN;
            if (isAdvantagePlayerOrWinner) {
                currentResult = getAdvantagePlayerOrWinner(pointsPlayer1, pointsPlayer2);
            } else {
                currentResult = updatePointsPlayer(pointsPlayer1, pointsPlayer2, currentResult);
            }
        }
        return currentResult;
    }

    private static @NotNull String updatePointsPlayer(int pointsPlayer1, int pointsPlayer2, String pointName) {
        int tempPoint;
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempPoint = pointsPlayer1;
            else {
                pointName += "-";
                tempPoint = pointsPlayer2;
            }
            switch (tempPoint) {
                case LOVE:
                    pointName += "Love";
                    break;
                case FIFTEEN:
                    pointName += "Fifteen";
                    break;
                case THIRTY:
                    pointName += "Thirty";
                    break;
                case FOURTY:
                    pointName += "Forty";
                    break;
            }
        }
        return pointName;
    }

    private static @NotNull String getAdvantagePlayerOrWinner(int pointsPlayer1, int pointsPlayer2) {
        String result;
        int minusResult = pointsPlayer1 - pointsPlayer2;
        if (minusResult == 1) result = "Advantage player1";
        else if (minusResult == -1) result = "Advantage player2";
        else if (minusResult >= 2) result = "Win for player1";
        else result = "Win for player2";
        return result;
    }

    private static @NotNull String getDeuceName(int points) {
        String result;
        switch (points) {
            case LOVE:
                result = "Love-All";
                break;
            case FIFTEEN:
                result = "Fifteen-All";
                break;
            case THIRTY:
                result = "Thirty-All";
                break;
            case FOURTY:
                result = "Forty-All";
                break;
            default:
                result = "Deuce";
                break;

        }
        return result;
    }
}