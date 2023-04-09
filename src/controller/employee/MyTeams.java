package controller.employee;

import controller.EasyDB;
import data.CurrentEmployee;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MyTeams extends EasyDB
{
    @FXML
    private AnchorPane anchMain;

    //variables
    double cardX = 0, cardY = 20;
    CurrentEmployee currentEmployee = null;

    public void initialize()
    {
        currentEmployee = CurrentEmployee.getInstance();
        setupCards();
    }//end initialize


    private void setupCards()
    {
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> skills = new ArrayList<>();
        try {
            ResultSet resultSet = super.getTableResultSet("INFO", "post", currentEmployee.getPost());

            while (resultSet.next()) {
                id.add(resultSet.getInt("id"));
                names.add(resultSet.getString("name") + " " + resultSet.getString("lastname"));
                skills.add(resultSet.getString("post"));
            }
            resultSet.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        sortCardsArrayLists(id,names,skills);


        for(int i=0;i<names.size();i++)
        {
            Image image = new Image("file:data/profilePictures/" + id.get(i) + ".png", 60,60,false,false);
            if(i%4==0 && i>0)
                cardY += 86;

            cardX = ((i%3) * 300) + ((i%3)*26.6) + 10;

            anchMain.getChildren().add(makeCard(id.get(i),names.get(i), skills.get(i), image, cardX, cardY));

        }//end for

    }//end setupCards


    private Pane makeCard(int id, String name, String skill, Image image, double x, double y)
    {
        //set up card pane
        Pane pane = new Pane();
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setPrefSize(300, 74);
        pane.setMaxSize(300, 74);


        //set up round image
        Circle imageCircle = new Circle(30);
        imageCircle.setLayoutX(37);
        imageCircle.setLayoutY(38);
        imageCircle.setStroke(Color.TRANSPARENT);
        imageCircle.setFill(new ImagePattern(image));
        //imageCircle.setEffect(new DropShadow(5, Color.BLACK));
        //end with rounding image


        //set up name label
        Label lblName = new Label(name);
        lblName.setLayoutX(79);
        lblName.setLayoutY(10);

        //set up skill label
        Label lblSkill = new Label(skill);
        lblSkill.setLayoutX(79);
        lblSkill.setLayoutY(40);

        //set up style
        pane.getStylesheets().add("/style/card.css");
        pane.getStyleClass().clear();
        pane.getStyleClass().add("pane");

        lblName.getStylesheets().add("/style/card.css");
        lblName.getStyleClass().clear();
        lblName.getStyleClass().add("name");

        lblSkill.getStylesheets().add("/style/card.css");
        lblSkill.getStyleClass().clear();
        lblSkill.getStyleClass().add("skill");



        //add all to pane as children
        pane.getChildren().addAll(imageCircle, lblName, lblSkill);

        EventHandler<Event> event = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                cardClick();
            }
        };
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event);

        return pane;
    }//end makeCard


    private void cardClick()
    {
        System.out.println("Card Clicked");
    }


    private void sortCardsArrayLists(ArrayList<Integer> id, ArrayList<String> names, ArrayList<String> skills)
    {

        for(int i=1;i<names.size();i++)
        {
            int thisID = id.get(i);
            String thisSkill = skills.get(i);
            String thisName = names.get(i);
            int j = i-1;

            while (j>=0 && names.get(j).compareTo(thisName)>0)
            {
                id.set(j+1, id.get(j));
                skills.set(j+1, skills.get(j));
                names.set(j+1, names.get(j));
                j--;
            }
            skills.set(j+1, thisSkill);
            id.set(j+1, thisID);
            names.set(j+1, thisName);
        }//end for
    }//end sortCardsArrayLists


}
