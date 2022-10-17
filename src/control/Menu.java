package control;

import static control.BombermanGame.HEIGHT;
import static control.BombermanGame.WIDTH;

import graphics.Sprite;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu {
    boolean mouseInText(Text text) {
        PointerInfo inf = MouseInfo.getPointerInfo();
        Point p = inf.getLocation();
        System.out.println(p.getX() + " " + p.getY());
        return false;
    }
    public void display(Group root) {
        Rectangle rectangle = new Rectangle(0, 0, WIDTH * Sprite.SCALED_SIZE,
            HEIGHT * Sprite.SCALED_SIZE);
        rectangle.setFill(Color.BLACK);
        rectangle.setStroke(Color.BLACK);
        root.getChildren().add(rectangle);

        Text bombermanText = new Text("BOMBERMAN");
        bombermanText.setFont(Font.loadFont(Menu.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 72));
        bombermanText.setFill(Color.WHITE);
        bombermanText.setStroke(Color.DARKBLUE);
        bombermanText.setStrokeWidth(5);
        bombermanText.setX(85);
        bombermanText.setY(200);
        root.getChildren().add(bombermanText);

        Text newgameText = new Text("NEW GAME");
        newgameText.setFont(Font.loadFont(Menu.class.getResourceAsStream("/fonts/Bomberman.ttf"), 30));
        newgameText.setFill(Color.WHITE);
        newgameText.setStroke(Color.WHITE);
        newgameText.setX(300);
        newgameText.setY(350);
        root.getChildren().add(newgameText);


        System.out.println(newgameText.getLayoutBounds().getWidth());

        Text continueText = new Text("CONTINUE");
        continueText.setFont(Font.loadFont(Menu.class.getResourceAsStream("/fonts/Bomberman.ttf"), 30));
        continueText.setFill(Color.WHITE);
        continueText.setStroke(Color.WHITE);
        continueText.setX(300);
        continueText.setY(430);
        root.getChildren().add(continueText);

        Text exitText = new Text("EXIT");
        exitText.setFont(Font.loadFont(Menu.class.getResourceAsStream("/fonts/Bomberman.ttf"), 30));
        exitText.setFill(Color.WHITE);
        exitText.setStroke(Color.WHITE);
        exitText.setX(300);
        exitText.setY(510);
        root.getChildren().add(exitText);

            mouseInText(newgameText);
    }
}