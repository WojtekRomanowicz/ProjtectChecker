package checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;




public class CheckersApp extends Application {


    public static final int TILE_SIZE = 100;


    Board board = new Board();
    Player player1;
    Player player2;
    Group root = new Group();


    @Override
    public void start(Stage primaryStage) throws Exception {


        Scene scene = new Scene(root, TILE_SIZE * 8, TILE_SIZE * 8);
        GridPane interfaceWindow = createInterface();
        interfaceWindow.setTranslateX(TILE_SIZE * 2);
        interfaceWindow.setTranslateY(TILE_SIZE * 2);

        root.getChildren().addAll(board.createBoard(),interfaceWindow );

        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);



    }

    public GridPane createInterface(){

        GridPane root = new GridPane();
        Rectangle window = new Rectangle(TILE_SIZE * 4, TILE_SIZE * 4);
        window.setFill(Color.PINK);

        Button newGame = new Button("New game");
        newGame.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        newGame.setTranslateX(TILE_SIZE + TILE_SIZE / 2);
        newGame.setTranslateY(- TILE_SIZE);
        newGame.setOnMouseClicked((e) -> {
            System.out.println("New game");
            root.setVisible(false);
            newGame();
        });

        Button loadGame = new Button("Load game");
        loadGame.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        loadGame.setTranslateX(TILE_SIZE + TILE_SIZE / 2);
        loadGame.setTranslateY(0);
        loadGame.setOnMouseClicked((e) -> {
            System.out.println("Load game");
        });

        Button showResults = new Button("Show results");
        showResults.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        showResults.setTranslateX(TILE_SIZE + TILE_SIZE / 2);
        showResults.setTranslateY( TILE_SIZE);
        showResults.setOnMouseClicked((e) -> {
            System.out.println("Show results");

        });





         root.getChildren().addAll(window, newGame, loadGame, showResults);


        return root;
    }

    private void newGame(){
        root.getChildren().addAll(board.putPiecesOnBoard());
        System.out.println("pionki sie rysuja");
        Group newGameGroup = new Group();

        Rectangle window = new Rectangle(TILE_SIZE * 4, TILE_SIZE * 4);
        window.setFill(Color.PINK);
        window.setTranslateX(TILE_SIZE *2);
        window.setTranslateY(TILE_SIZE *2);


        Text text1 = new Text("Select game mode ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setTranslateX(3 *TILE_SIZE );
        text1.setTranslateY(2 * TILE_SIZE + TILE_SIZE / 2);

        Button singlePlayer = new Button("Single Player");
        Button multiPlayer = new Button("MultiPlayer");
        Button submit = new Button("Submit");
        submit.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        submit.setTranslateX(3 *TILE_SIZE + TILE_SIZE / 2);
        submit.setTranslateY(5 * TILE_SIZE);



        singlePlayer.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        singlePlayer.setTranslateX(3 *TILE_SIZE + TILE_SIZE / 2);
        singlePlayer.setTranslateY(3 * TILE_SIZE);
        singlePlayer.setOnMouseClicked((e) -> {
            System.out.println("Single Player");

            TextField player1Name = new TextField();
            player1Name.setTranslateX(3 *TILE_SIZE );
            player1Name.setTranslateY(4 * TILE_SIZE);

            Text text2 = new Text("Player name: ");
            text2.setStyle("-fx-font: normal bold 20px 'serif' ");
            text2.setTranslateX(3 *TILE_SIZE );
            text2.setTranslateY(4 * TILE_SIZE);

            newGameGroup.getChildren().remove(singlePlayer);
            newGameGroup.getChildren().remove(multiPlayer);
            newGameGroup.getChildren().addAll(player1Name, text2, submit);
            submit.setOnMouseClicked((a) -> {
                player1 = new User(player1Name.getText());
                root.getChildren().remove(newGameGroup);

                System.out.println(player1.getName());
                System.out.println(player2.getName());
            });

            player2 = new User("computer");


        });


        multiPlayer.setPrefSize(TILE_SIZE, TILE_SIZE / 2);
        multiPlayer.setTranslateX(3 *TILE_SIZE + TILE_SIZE / 2);
        multiPlayer.setTranslateY(4 * TILE_SIZE);
        multiPlayer.setOnMouseClicked((e) -> {
            System.out.println("MultiPlayer");

            TextField player1Name = new TextField();
            player1Name.setTranslateX(3 *TILE_SIZE );
            player1Name.setTranslateY(3 * TILE_SIZE+ TILE_SIZE / 2);

            Text text2 = new Text("Player 1 name: ");
            text2.setStyle("-fx-font: normal bold 20px 'serif' ");
            text2.setTranslateX(3 *TILE_SIZE );
            text2.setTranslateY(3 * TILE_SIZE+ TILE_SIZE / 2);

            TextField player2Name = new TextField();
            player2Name.setTranslateX(3 *TILE_SIZE );
            player2Name.setTranslateY(4 * TILE_SIZE );

            Text text3 = new Text("Player 2 name: ");
            text3.setStyle("-fx-font: normal bold 20px 'serif' ");
            text3.setTranslateX(3 *TILE_SIZE );
            text3.setTranslateY(4 * TILE_SIZE);

            newGameGroup.getChildren().remove(singlePlayer);
            newGameGroup.getChildren().remove(multiPlayer);
            newGameGroup.getChildren().addAll(player1Name, player2Name, text2, text3, submit);
            submit.setOnMouseClicked((a) -> {
                player1 = new User(player1Name.getText());
                player2 = new User(player2Name.getText());
                root.getChildren().remove(newGameGroup);

                System.out.println(player1.getName());
                System.out.println(player2.getName());
            });


        });

        newGameGroup.getChildren().addAll(window, text1, singlePlayer, multiPlayer);
        root.getChildren().add(newGameGroup);






    }


}
