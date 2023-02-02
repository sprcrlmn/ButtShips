package org.example;

public class Main {

    public static void main(String[] args) {
        Game player1 = new Game();
        player1.setPlayerNum(1);
        Game player2 = new Game();
        player2.setPlayerNum(2);

        player1.setGame();

        player2.setGame();
        boolean stop = false;
        while(!stop) {
            if (!stop) {
                stop = player1.takeAShot(player2.field.open);
            }
            if (!stop) {
                stop = player2.takeAShot(player1.field.open);
            }

        }

        //game.takeAShot(field.getField(), field.getFogField());
    }

}
