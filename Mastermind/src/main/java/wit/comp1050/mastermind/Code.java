package wit.comp1050.mastermind;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

//this was added to test the push
public class Code {

    Code(int codeSize) {
        this.codeSize = codeSize;
        this.colorCombination = new PegColor[codeSize];
        this.positions = new int[codeSize];
    }

    public void getCode(boolean duplicates) {
        int random_num;
        PegColor[] code = new PegColor[codeSize];
        ArrayList<Integer> usedColorIndexs = new ArrayList<>();

        if (duplicates) {
            for (int i = 0; i < codeSize; i++) {
                do {
                    random_num = getRandom(0, PegColor.values().length - 1);
                } while ((random_num == 5) || (random_num == 6));
                colorCombination[i] = PegColor.values()[random_num];
            }

        } else {

            for (int i = 0; i < codeSize; i++) {

                random_num = getRandom(0, PegColor.values().length - 1);

                do {
                    random_num = getRandom(0, PegColor.values().length - 1);
                } while (usedColorIndexs.contains(random_num) || (random_num == 5 || (random_num == 6)));
                usedColorIndexs.add(random_num);
                colorCombination[i] = PegColor.values()[random_num];

            }
        }

    }


    private int getRandom(int min, int max) {

        int range = max - min + 1;

        return (int) (Math.random() * range) + min;
    }

    public String[] getHintPegColors(PegColor[] guess) {
        getPositions(guess);
        return colorFromPositions();

    }


    public int[] getPositions(PegColor[] guess) {
        //Setting holder for other methods
        this.guess = guess;

        if (guess.length == colorCombination.length) {
            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == colorCombination[i]) {
                    positions[i] = 1;
                } else {
                    positions[i] = 0;
                }
            }
        }
        return positions;
    }

    public String[] colorFromPositions() {

        ArrayList<Integer> checkIndexs = new ArrayList<>();

        String[] hintPegs = new String[codeSize];



        //Check if the color is in the wrong spot
        //BLUE, GREEN, PURPLE, RED, YELLOW;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != 1 && positions[i] != 2) {
                checkIndexs.add(i);
            }
        }

        //every user guess that didnt match


        for (int i = 0; i < MainApp.CODE_SIZE; i++) {
            //ArrayList<Integer> toDelete = new ArrayList<>();
                if (checkIndexs.contains(i)) {
                    for(int j = 0; j < MainApp.CODE_SIZE; j++){
                        if (checkIndexs.contains(j)){
                            if (guess [j] == colorCombination[i]){
                                positions[i] = 2;
                                break;
                            }
                        }
                    }
                }
        }

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                hintPegs[i] = "RED";
            } else if (positions[i] == 0) {
                hintPegs[i] = "TRANSPARENT";
            } else if (positions[i] == 2) {
                hintPegs[i] = "WHITE";
            }
        }

        System.out.println(Arrays.toString(positions));

        return hintPegs;
    }

    public PegColor[] getColorCombination() {
        return colorCombination;
    }

    public void reset() {
        positions = null;
        used_Colors = null;
        colorCombination = null;
    }


    protected int[] used_Colors = new int[5];
    protected PegColor[] guess;
    protected int[] positions;
    public PegColor[] colorCombination;
    public int codeSize;


}
