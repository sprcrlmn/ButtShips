package org.example;

import java.io.IOException;
import java.util.Scanner;


public class Game {
    int playerNum;

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    Field field = new Field();

    static Scanner scanner = new Scanner(System.in);
    private int hitCounter = 0;
    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player\n...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setGame() {
        System.out.printf("Player %d, place your ships on the game field\n", playerNum);
        System.out.println();
        Field.printField(field.open);
        shipInstaller(Ships.AIRCRAFTCARRIER, field.open);
        shipInstaller(Ships.BATTLESHIP, field.open);
        shipInstaller(Ships.SUBMARINE, field.open);
        shipInstaller(Ships.CRUISER, field.open);
        shipInstaller(Ships.DESTROYER, field.open);
        promptEnterKey();
    }
    private boolean checkLocation(int x1, int y1, int x2, int y2) {
        if(x1 == x2 || y1 == y2) {
            return true;
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
    }
    private boolean checkLenght(int x1, int y1, int x2, int y2, String name, int len){

        if(Math.abs(x1 - x2) == (len-1)
                || Math.abs(y1 - y2) == (len-1)) {
            return true;
        } else {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", name);
            return false;
        }
    }

    private boolean checkAnotherShip(int x1, int y1, int x2, int y2, String[][] field) {

        for(int line = y1; line  <= y2; line++) {
            for(int column = x1; column <= x2; column++) {
                if(field[line][column].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
                if (line > 0) {
                    if(field[line-1][column].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    if (column > 0) {
                        if(field[line-1][column -1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                    if (column < 9) {
                        if(field[line-1][column +1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }

                }
                if(line < 9) {
                    if(field[line+1][column].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    if(column > 0) {
                        if(field[line+1][column -1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                    if (column < 9) {
                        if(field[line+1][column +1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
                if(column > 0) {
                    if(field[line][column -1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    if(line > 0) {
                        if(field[line-1][column -1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                    if (line < 9) {
                        if(field[line+1][column -1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }
                if(column < 9) {
                    if(field[line][column +1].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    if(line > 0) {
                        if(field[line-1][column +1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                    if (line < 9) {
                        if(field[line+1][column +1].equals("O")) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                    }
                }

            }

        }
        return true;
    }


    public void shipInstaller(Ships ships, String[][] field) {

        String name = ships.getName();
        int len = ships.getLen();
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", name, len);
        boolean check = false;
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        while (!check) {
            String enter = scanner.nextLine();
            x1 = Integer.parseInt(enter.split(" ")[0].substring(1)) - 1;
            y1 = enter.split(" ")[0].charAt(0) - 65;
            x2 = Integer.parseInt(enter.split(" ")[1].substring(1)) - 1;
            y2 = enter.split(" ")[1].charAt(0) - 65;
            if(x1 > x2 || y1 > y2) {
                int a0 = x1;
                int a1 = y1;
                x1 = x2;
                y1 = y2;
                x2 =  a0;
                y2 =  a1;
            }

            if (!checkLocation(x1, y1, x2, y2)) {
                continue;
            }
            if(!checkLenght(x1, y1, x2, y2, name, len)) {
                continue;
            }
            if(!checkAnotherShip(x1, y1, x2, y2, field)) {
                continue;
            }
            check = true;
        }
        for(int line = y1; line  <= y2; line++) {
            for(int collum = x1; collum  <= x2; collum++) {
                field[line][collum] = "O";
            }
        }
        Field.printField(field);
    }

    public boolean takeAShot(String[][] enemyField) {
        System.out.printf("Player %d, it's your turn:\n", playerNum);
        System.out.println();
        boolean check = true;
        Field.printField(field.fog);
        System.out.println("---------------------");
        Field.printField(field.open);
        int y = -1;
        int x = -1;
        while (check) {
            String shot = scanner.nextLine();
            y = shot.charAt(0) - 65;
            x = Integer.parseInt(shot.substring(1)) - 1;
            if ((x < 0 || x > 9) || (y < 0 || y > 9)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                break;
            }
        }
        if (enemyField[y][x].equals("O") || field.open[y][x].equals("X")) {
            if (enemyField[y][x].equals("O")){
                hitCounter++;
            }
            enemyField[y][x] = "X";
            field.fog[y][x] = "X";
            Field.printField(field.fog);
            if (hitCounter == 17) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                return true;
            }
            boolean sank = true;

            for(int i = x; i >= 0; i--) {
                if(enemyField[y][i].equals("O")) {
                    sank = false;
                } else if (enemyField[y][i].equals("~") || enemyField[y][i].equals("M")) {
                    i = -1;
                }
            }
            for(int i = x; i <= 9; i++) {
                if(enemyField[y][i].equals("O")) {
                    sank = false;
                } else if (enemyField[y][i].equals("~") || enemyField[y][i].equals("M")) {
                    i = 10;
                }
            }
            for(int i = y; i >= 0; i--) {
                if(enemyField[i][x].equals("O")) {
                    sank = false;
                } else if (enemyField[i][x].equals("~") || enemyField[i][x].equals("M")) {
                    i = -1;
                }
            }
            for(int i = y; i <=9; i++) {
                if(enemyField[i][x].equals("O")) {
                    sank = false;
                } else if (enemyField[i][x].equals("~") || enemyField[i][x].equals("M")) {
                    i = 10;
                }
            }
            if (sank) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship!");

            }
        } else {
            enemyField[y][x] = "M";
            field.fog[y][x] = "M";
            Field.printField(field.fog);
            System.out.println("You missed!");


        }
        promptEnterKey();
        return false;



    }

}

