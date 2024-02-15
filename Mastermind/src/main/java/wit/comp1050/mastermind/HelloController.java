package wit.comp1050.mastermind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class HelloController {

    @FXML
    public VBox rowVBox;

    @FXML
    public Circle blueCircle;

    @FXML
    public Circle greenCircle;

    @FXML
    public Circle redCircle;

    @FXML
    public Circle yellowCircle;

    @FXML
    public Circle purpleCircle;

    @FXML
    public Rectangle currentColorDisplay;

    @FXML
    public VBox rightVbox;

    @FXML
    public Button checkButton;

    @FXML
    public Circle computerCode1;
    @FXML
    public Circle computerCode2;
    @FXML
    public Circle computerCode3;
    @FXML
    public Circle computerCode4;

    @FXML
    public Button restartButton;

    //Tracked Game Variables
    public static boolean GAME_RUNNING = true;
    public static int CURRENT_GAME_ROW = 9;
    private final PegColor[] colorsInRow = new PegColor[MainApp.CODE_SIZE];
    private static String[] currentGameboardPegColor;
    public static PegColor CURRENT_COLOR = PegColor.BLUE;
    boolean gameWin = false;

    //Constants
    private static final int ROWS = 10;
    private static final int COLS = 4;
    private static final double DEFAULT_RADIUS = 20.0;


    public HelloController() {
        gameBoard = new Peg[ROWS][COLS];
        hintPegBoard = new Peg[ROWS][MainApp.CODE_SIZE];
    }

    public void initialize() {
        Audio.playAudio("gameStart.wav");

        //Clear Previous inputs;
        Code code = new Code(MainApp.CODE_SIZE);
        code.getCode(MainApp.DUPLICATES_ALLOWED_IN_CODE);
        System.out.println(Arrays.toString(code.getColorCombination()));


        for (int row = 0; row < ROWS; row++) {
            HBox hbox = new HBox();
            String hboxID = String.format("%sbox", row);
            hbox.setId(hboxID);

            for (int col = 0; col < COLS; col++) {
                Peg p = new Peg(PegColor.TRANSPARENT, DEFAULT_RADIUS);
                String circleID = String.format("%d,%d", row, col);
                p.circle.setId(circleID);
                gameBoard[row][col] = p;

                p.circle.setOnMouseClicked(me -> {
                    String id = p.circle.getId();
                    //System.out.printf("You clicked %s%n", id);

                    int commaIndex = id.indexOf(",");
                    int rowNum = Integer.parseInt(String.valueOf(id.charAt(commaIndex - 1)));
                    int colNum = Integer.parseInt(String.valueOf(id.charAt(commaIndex + 1)));
                    //System.out.println("This is the " + rowNum + " row");
                    //System.out.println("This is the " + colNum + " col");

                    if (rowNum == CURRENT_GAME_ROW) {
                        if (MainApp.DUPLICATES_ALLOWED_IN_CODE) {
                            Audio.playAudio("pegPlace.wav");
                            p.circle.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                            p.color = CURRENT_COLOR;
                            System.out.println(String.format("Peg color : %s", p.color));
                        } else {
                            //String ColorVarifyID = String.format("%d,%d", CURRENT_GAME_ROW, i);

                            boolean isUsed = false;
                            for (int c = 0; c < MainApp.CODE_SIZE; c++) {
                                PegColor pci = gameBoard[CURRENT_GAME_ROW][c].color;
                                if (c != colNum) {
                                    if (pci == CURRENT_COLOR) {
                                        isUsed = true;
                                    }
                                }
                            }
                            if (!isUsed) {
                                Audio.playAudio("pegPlace.wav");
                                p.circle.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                                p.color = CURRENT_COLOR;
                            }
                        }
                    }
                });
                hbox.setSpacing(20);
                hbox.getChildren().add(p.circle);
            }
            rowVBox.setSpacing(20);
            rowVBox.getChildren().add(hbox);
        }
        createHintPegs();

        restartButton.setOnAction(actionEvent -> {
            Audio.playAudio("gameStart.wav");
            code.getCode(MainApp.DUPLICATES_ALLOWED_IN_CODE);
            System.out.println(Arrays.toString(code.getColorCombination()));
            resetBoard();

            computerCode1.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));
            computerCode2.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));
            computerCode3.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));
            computerCode4.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));

        });

        checkButton.setOnAction(actionEvent -> {
                    Audio.playAudio("checkSound.wav");
                    getCurrentPegs();
                    boolean isEmpty = false;
                    for (int i = 0; i < colorsInRow.length; i++) {
                        if (colorsInRow[i] == PegColor.TRANSPARENT) {
                            isEmpty = true;
                        }
                    }

                    if (!isEmpty) {

                        if (CURRENT_GAME_ROW > 0) {
                            currentGameboardPegColor = code.getHintPegColors(colorsInRow);
                            int[] positions = code.positions;
                            gameWin = true;

                            for (int z = 0; z < positions.length; z++) {
                                if (positions[z] != 1) {
                                    gameWin = false;
                                }
                            }

                            for (int i = 0; i < MainApp.CODE_SIZE; i++) {
                                switch (currentGameboardPegColor[i]) {
                                    case "RED":
                                        hintPegBoard[CURRENT_GAME_ROW][i].color = PegColor.RED;
                                        hintPegBoard[CURRENT_GAME_ROW][i].circle.setFill(Color.RED);
                                        break;
                                    case "TRANSPARENT":
                                        hintPegBoard[CURRENT_GAME_ROW][i].color = PegColor.TRANSPARENT;
                                        hintPegBoard[CURRENT_GAME_ROW][i].circle.setFill(Color.TRANSPARENT);
                                        break;
                                    case "WHITE":
                                        hintPegBoard[CURRENT_GAME_ROW][i].color = PegColor.WHITE;
                                        hintPegBoard[CURRENT_GAME_ROW][i].circle.setFill(Color.WHITE);
                                        break;
                                }
                            }
                            CURRENT_GAME_ROW--;

                            if (gameWin == true) {
                                Audio.playAudio("gameWin.wav");
                                computerCode1.setFill(Paint.valueOf(toString(code.getColorCombination()[0])));
                                computerCode2.setFill(Paint.valueOf(toString(code.getColorCombination()[1])));
                                computerCode3.setFill(Paint.valueOf(toString(code.getColorCombination()[2])));
                                computerCode4.setFill(Paint.valueOf(toString(code.getColorCombination()[3])));

                                CURRENT_GAME_ROW = -19999;

                            }


                        } else if (CURRENT_GAME_ROW <= 0 && gameWin == true) {
                            //Change display to show computer code
                            // TODO: 04/19/2022 make code to restart
                            Audio.playAudio("gameWin.wav");
                            computerCode1.setFill(Paint.valueOf(toString(code.getColorCombination()[0])));
                            computerCode2.setFill(Paint.valueOf(toString(code.getColorCombination()[1])));
                            computerCode3.setFill(Paint.valueOf(toString(code.getColorCombination()[2])));
                            computerCode4.setFill(Paint.valueOf(toString(code.getColorCombination()[3])));

                            CURRENT_GAME_ROW = -19999;


                        } else if (CURRENT_GAME_ROW <= 0 && gameWin == false) {
                            //Change display to show computer code
                            //User Lost the game
                            // TODO: 04/19/2022 make code to restart
                            Audio.playAudio("gameLost.wav");
                            computerCode1.setFill(Paint.valueOf(toString(code.getColorCombination()[0])));
                            computerCode2.setFill(Paint.valueOf(toString(code.getColorCombination()[1])));
                            computerCode3.setFill(Paint.valueOf(toString(code.getColorCombination()[2])));
                            computerCode4.setFill(Paint.valueOf(toString(code.getColorCombination()[3])));

                            CURRENT_GAME_ROW = -19999;


                        }
                    }
                }
        );

        //<editor-fold desc="Color Panel Selectors">
        blueCircle.setOnMouseClicked(
                mouseEvent -> {
                    CURRENT_COLOR = PegColor.BLUE;
                    currentColorDisplay.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                    //System.out.println("Blue pressed.");
                }
        );

        greenCircle.setOnMouseClicked(
                mouseEvent -> {
                    CURRENT_COLOR = PegColor.GREEN;
                    currentColorDisplay.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                    //System.out.println("green pressed.");
                }
        );

        redCircle.setOnMouseClicked(
                mouseEvent -> {
                    CURRENT_COLOR = PegColor.RED;
                    currentColorDisplay.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                    //System.out.println("red pressed.");
                }
        );

        yellowCircle.setOnMouseClicked(
                mouseEvent -> {
                    CURRENT_COLOR = PegColor.YELLOW;
                    currentColorDisplay.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                    //System.out.println("yellow pressed.");
                }
        );

        purpleCircle.setOnMouseClicked(
                mouseEvent -> {
                    CURRENT_COLOR = PegColor.PURPLE;
                    currentColorDisplay.setFill(Paint.valueOf(toString(CURRENT_COLOR)));
                    //System.out.println("purple pressed.");
                }
        );
        //</editor-fold>

    }

    private void createHintPegs() {
        for (int i = 0; i < ROWS; i++) {
            HBox hintHBox = new HBox();
            for (int j = 0; j < MainApp.CODE_SIZE; j++) {
                Peg h = new Peg(PegColor.BLUE, 10);
                String hintpegID = String.format("%d,%d", i, j);
                h.circle.setId(hintpegID);
                hintPegBoard[i][j] = h;
                //Circle a = new Circle(20);
                h.circle.setStroke(Color.BLACK);
                h.circle.setStrokeWidth(2);

                hintHBox.setSpacing(10);
                hintHBox.getChildren().addAll(h.circle);
            }
            rightVbox.getChildren().add(hintHBox);
            rightVbox.setSpacing(30);
        }
    }

    private void resetBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                gameBoard[row][col].color = PegColor.TRANSPARENT;
                gameBoard[row][col].circle.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < MainApp.CODE_SIZE; j++) {
                hintPegBoard[i][j].circle.setFill(Paint.valueOf(toString(PegColor.TRANSPARENT)));
                hintPegBoard[i][j].color = PegColor.TRANSPARENT;

            }
        }
        CURRENT_GAME_ROW = 9;
    }

    private void getCurrentPegs() {
        for (int i = 0; i < MainApp.CODE_SIZE; i++) {
            colorsInRow[i] = gameBoard[CURRENT_GAME_ROW][i].color;
        }
        //System.out.println(colorsInRow);
    }

    private String toString(PegColor currentColor) {
        return String.format(String.valueOf(currentColor));
    }


    Peg[][] gameBoard;
    Peg[][] hintPegBoard;

}