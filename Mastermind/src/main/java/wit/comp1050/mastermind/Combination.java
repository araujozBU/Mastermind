package wit.comp1050.mastermind;

public class Combination {

    public static PegColor[] getCode(int codeSize){

        PegColor[] code = new PegColor[codeSize];

        for (int i = 0; i < codeSize; i++){
            int random_num;
            do {
                random_num = getRandom(0, PegColor.values().length-1);
            } while (random_num == 5);

            code[i] = PegColor.values()[random_num];

        }
        return code;
    }

    private static int getRandom(int min, int max){

        int range = max - min + 1;

        return (int)(Math.random() * range) + min;
    }


}
