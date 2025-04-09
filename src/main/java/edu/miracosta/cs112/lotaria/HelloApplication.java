package edu.miracosta.cs112.lotaria;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.Random;
 
public class HelloApplication extends Application {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };
    private LoteriaCard[] drawnCards = new LoteriaCard[LOTERIA_CARDS.length];

    private ImageView imageView = new ImageView();
    private Label messageLabel = new Label("The progress bar shows how many cards have been drawn.");
    private ProgressBar progressBar = new ProgressBar(0);
    private double drawnCardCount = 0.0;
    private Button drawCardButton = new Button("Draw Card Button");
    private Label cardImageView = new Label("Click the button below to draw your first card!");

    @Override
    public void start(Stage stage) { 
        //Root VBox Setup
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);

        //Scene setup and set stage title
        Scene scene = new Scene(root, 350, 500);
        stage.setTitle("Loteria!");

        //Creates Title Label
        Label titleLabel = new Label("Loteria Card Game");
        titleLabel.setPadding(new Insets(10, 0, 0, 0));
        
        //Creates ImageView
        
        imageView.setFitWidth(200);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);

        //Creates cardImageView label, messageLabel, drawCardButton, and progressBar 
        
        cardImageView.setPadding(new Insets(-10, 0, 0, 0));
        
        //Adds elements to the root VBox
        root.getChildren().addAll(titleLabel, imageView, cardImageView, messageLabel, drawCardButton, progressBar);

        drawCardButton.setOnAction(e -> drawCard());

        //sets the scene and then shows it
        stage.setScene(scene);
        stage.show();

    }
    public void drawCard(){
        //Verifies progress bar isn't full before drawing card.
        if (progressBar.getProgress() == 1.0) {
            LoteriaCard logoCard = new LoteriaCard();
            drawCardButton.setDisable(true);
            imageView.setImage(logoCard.getImage());
            progressBar.setStyle("-fx-accent: red;");
            cardImageView.setText("GAME OVER. No more cards!");
            messageLabel.setText("Exit and run program again to reset ^_^");   
        } else {
            // Draw a random card from the deck. Picks any card even if it has already been drawn.
            Random random = new Random();
            int randomNumber = random.nextInt(LOTERIA_CARDS.length);
            int newCard = randomNumber; // Update the current card index
            
            // Updates the drawnCards array if the card has not been drawn yet.
            // If the card has already been drawn, pick another card. (User is unaware. Their previous card remains until new card is drawn.)
            // Drawn card array indexes equal those of Loteria Cards deck.
            if( LOTERIA_CARDS[newCard].equals(drawnCards[newCard])) {
                drawCard();
            }else {
                drawnCards[newCard] = LOTERIA_CARDS[newCard];
                imageView.setImage(LOTERIA_CARDS[newCard].getImage());
                cardImageView.setText(LOTERIA_CARDS[newCard].getCardName());
                drawnCardCount++;
                progressBar.setProgress( drawnCardCount / (double) LOTERIA_CARDS.length );    
            }
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
