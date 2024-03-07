package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application
{
    //score updating so outside declared
    private Label playerXScoreLabel, playerOScoreLabel;
    private Button buttons[][]=new Button[3][3];
    private boolean playerXTurn=true;
    private int playerXScore=0,playerOScore=0;


    private BorderPane createContent()
    {
        BorderPane root= (BorderPane) new BorderPane();
        root.setPadding(new Insets(20));
        //title
        Label titleLabel=new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold");
        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        //game board
        GridPane gridPane=new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Button button=new Button();
                button.setStyle("-fx-font-size : 30pt; -fx-font-weight : bold");
                button.setOnAction(event->buttonClick(button));
                button.setPrefSize(100,100);
                buttons[i][j]=button;//here row-i and column-j
                gridPane.add(button,j,i);//here column-j and row-i
            }
        }
        root.setCenter(gridPane);
        //BorderPane.setAlignment(gridPane, Pos.CENTER);

        //score
        HBox scoreBoard=new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel=new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold");
        playerOScoreLabel=new Label("Player O : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold");

        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);
        root.setBottom(scoreBoard);

        return root;
    }

    private void buttonClick(Button button)
    {
        if(button.getText().equals(""))
        {
            if(playerXTurn)
            {
                button.setText("X");
            }
            else
            {
                button.setText("O");
            }
            playerXTurn=!playerXTurn;
            checkWinner();
        }
    }

    private void checkWinner()
    {
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty())
            {
                String winner=buttons[row][0].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //col
        for (int col = 0; col < 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty())
            {
                String winner=buttons[0][col].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //diagonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty())
        {
            String winner=buttons[0][0].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if(buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty())
        {
            String winner=buttons[2][0].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        //tie--reset code reverse
        boolean tie=true;
        for(Button row[]: buttons)
        {
            for(Button button: row)
            {
                if(button.getText().isEmpty())
                {
                    tie=false;
                    break;
                }
            }
        }

        if(tie)
        {
            showTie();
            resetBoard();
        }

    }

    private void showWinner(String winner)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNNER!!!");
        alert.setContentText("Congratulations "+ winner + "! You've won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTie()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER!");
        alert.setContentText("It's a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner)
    {
        if(winner.equals("x"))
        {
            playerXScore++;
            playerXScoreLabel.setText("Player X : "+playerXScore);
        }
        else
        {
            playerOScore++;
            playerOScoreLabel.setText("Player O : "+playerOScore);
        }
    }

    private void resetBoard()
    {
        for(Button row[]: buttons)
        {
            for(Button button: row)
            {
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}