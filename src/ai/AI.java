package ai;
/*
 * Athens 2021
 *
 * Created on : 2021-09-16
 * Author     : Christos Kormaris
 */

import tic_tac_toe.Board;
import tic_tac_toe.Move;

public abstract class AI {

    // Variable that holds which letter this player controls.
    private int aiPlayer;

    public AI(int aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public int getAiPlayer() {
        return aiPlayer;
    }

    public void setAiPlayer(int aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public abstract Move getNextMove(Board board);

}
