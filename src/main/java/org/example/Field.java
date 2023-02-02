package org.example;

public class Field {
    public String[][] open = initField();
    public String[][] fog = initField();


    public String[][] initField() {
        String[][] field = (new String[10][10]);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = "~";
            }
        }
        return field;
    }

    public String[][] getFogField() {
        return fog;
    }

    public static void printField(String[][] field) {
        System.out.print(" ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        int i = 0;
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.print(c);
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + field[i][j]);
            }
            i++;
            System.out.println();
        }
        System.out.println();
    }
}
