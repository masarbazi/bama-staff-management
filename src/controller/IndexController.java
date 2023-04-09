package controller;

import data.CurrentEmployee;
import data.CurrentManager;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.util.ArrayList;


public class IndexController extends EasyDB
{
    @FXML
    private Circle circleLD;
    @FXML
    private Label lblLDName, lblPeopleMenu, lblTeamsMenu, lblSkillsMenu, lblLoginMenu;
    @FXML
    private Button btnLDLogin, btnLDClose;
    @FXML
    private TextField txtLDPassword;
    @FXML
    private AnchorPane anchMain;
    @FXML
    private Pane paneMenu, paneUnderLine, paneHeader, paneJobTitles, paneBody, panePeople, paneLoginDrop;
    @FXML
    private ImageView imgLogo;



    //my variables
    double cardX = 0,cardY = 20;
    double underLineX = 191;
    boolean dropdownOpen = false;


    public void initialize()
    {
        setupCards();
        setupUnderLine();
    }//end initialize

    @FXML
    private void login()
    {
        String username = lblLDName.getText();
        String password = txtLDPassword.getText();

        if(!username.isEmpty() && !password.isEmpty())
        {
            try
            {
                ResultSet resultSet = super.getTableResultSet("INFO");
                resultSet.last();
                int lastRow = resultSet.getRow();
                resultSet.absolute(0);

                while (resultSet.next())
                {
                    String dbName = resultSet.getString("name") + " " + resultSet.getString("lastname");
                    String dbpassword = resultSet.getString("password");
                    int currentRow = resultSet.getRow();

                    if(username.equals(dbName) && password.equals(dbpassword))
                    {
                        int id = resultSet.getInt("id");
                        String post = resultSet.getString("post");
                        Parent root = null;

                        if(post.equals("CEO"))
                        {
                            CurrentManager currentManager = CurrentManager.getInstance();
                            currentManager.setId(id);
                            //open up Manager page
                            root = FXMLLoader.load(getClass().getResource("/scene/Manager.fxml"));
                        }
                        else
                        {
                            CurrentEmployee currentEmployee = CurrentEmployee.getInstance();
                            currentEmployee.setId(resultSet.getInt("id"));
                            currentEmployee.setPost(resultSet.getString("post"));
                            //open up Employee page
                            root = FXMLLoader.load(getClass().getResource("/scene/Employee.fxml"));
                        }
                        Stage stage = new Stage();
                        //stage.initStyle(StageStyle.TRANSPARENT);
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        break;
                    }
                    else if(currentRow == lastRow)
                        System.out.println("wrong password dudeeee!!!");

                }//end while
                resultSet.close();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        else
        {
            System.out.println("Print some message");
        }

    }//end login


    @FXML
    private void menuClick(MouseEvent event)
    {
        try {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.4), paneUnderLine);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(1);
            lblLoginMenu.setStyle("-fx-text-fill: black");
            lblPeopleMenu.setStyle("-fx-text-fill: black");
            lblSkillsMenu.setStyle("-fx-text-fill: black");
            lblTeamsMenu.setStyle("-fx-text-fill: black");
            String source = "";
            paneBody.getChildren().remove(0);
            paneBody.getChildren().removeAll(panePeople);
            boolean peoplePaneClicked = false;

            if (event.getSource() == lblPeopleMenu) {
                lblPeopleMenu.setStyle("-fx-text-fill: #22c609");
                translateTransition.setByX(lblPeopleMenu.getLayoutX() + 10 - underLineX);
                underLineX = lblPeopleMenu.getLayoutX() + 10;
                source = "";
                paneBody.getChildren().add(panePeople);
                peoplePaneClicked = true;
            }
            else if (event.getSource() == lblLoginMenu) {
                lblLoginMenu.setStyle("-fx-text-fill: #22c609");
                translateTransition.setByX(lblLoginMenu.getLayoutX() + 6 - underLineX);
                underLineX = lblLoginMenu.getLayoutX() + 10;
                source = "/scene/Login.fxml";
            }
            else if (event.getSource() == lblSkillsMenu) {
                lblSkillsMenu.setStyle("-fx-text-fill: #22c609");
                translateTransition.setByX(lblSkillsMenu.getLayoutX() + 10 - underLineX);
                underLineX = lblSkillsMenu.getLayoutX() + 10;
            }
            else if (event.getSource() == lblTeamsMenu) {
                lblTeamsMenu.setStyle("-fx-text-fill: #22c609");
                translateTransition.setByX(lblTeamsMenu.getLayoutX() + 10 - underLineX);
                underLineX = lblTeamsMenu.getLayoutX() + 10;
            }


            if(!peoplePaneClicked)
            {
                Parent root = FXMLLoader.load(getClass().getResource(source));
                paneBody.getChildren().add(root);
            }//end if


            translateTransition.play();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end menuClick


    private void setupUnderLine()
    {
        paneUnderLine.setStyle("-fx-background-color: #22c609; -fx-background-radius: 2");
        lblPeopleMenu.setStyle("-fx-text-fill: #22c609");
        paneUnderLine.setPrefSize(60, 3);
        paneUnderLine.setLayoutX(lblPeopleMenu.getLayoutX() + 10);
        paneUnderLine.setLayoutY(50);
    }//end setupUnderLine


    private void setupCards()
    {
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> skills = new ArrayList<>();
        try {
            ResultSet resultSet = super.getTableResultSet("INFO");
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
        sortCardsArrayLists(id,names,skills);//  ...


        for(int i=0;i<names.size();i++)
        {
            Image image = new Image("file:data/profilePictures/" + id.get(i) + ".png", 60,60,false,false);
            if(i%4==0 && i>0)
                cardY += 86;

            cardX = ((i%4) * 300) + ((i%4)*26.6) + 10;

            panePeople.getChildren().add(makeCard(id.get(i),names.get(i), skills.get(i), image, cardX, cardY));
            // makeCard is method

        }//end for

        for(int i=1;i<=4;i++)
        {
            String url = "file:images/header/header" + i + ".png";
            Image image = new Image(url,120,120,false,false);
            String[] titles = new String[]{"Design Team", "Support", "Marketing", "Achievements"};
            int x = 14 + ((i-1) * (140));

            paneHeader.getChildren().add(makeHeaderCard(titles[i-1], "New Company\nTech. Design ..", image, x, 59));

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
                loginDropdown(id, name);
            }
        };
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event);

        return pane;
    }//end makeCard


    private Pane makeHeaderCard(String title, String info, Image image, double x, double y)
    {
        Pane pane = new Pane();
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setPrefSize(120,120);
        pane.setMaxSize(120,120);

        Rectangle rectangle = new Rectangle(0, 0, 120, 120);
        rectangle.setArcWidth(10.0);   // Corner radius
        rectangle.setArcHeight(10.0);

        ImagePattern pattern = new ImagePattern(image);

        rectangle.setFill(pattern);
        //rectangle.setEffect(new DropShadow(20, Color.BLACK));  // Shadow

        Label lblTitle = new Label(title);
        lblTitle.setStyle("-fx-font-family: 'Impact'; -fx-font-size: 20; -fx-text-fill: #323030");
        lblTitle.setLayoutY(20);
        lblTitle.setLayoutX(2);
        lblTitle.setOpacity(0);

        Label lblInfo = new Label(info);
        lblInfo.setStyle("-fx-font-family: Consolas; -fx-font-size: 15; -fx-text-fill: #ffffff");
        lblInfo.setLayoutX(2);
        lblInfo.setLayoutY(50);
        lblInfo.setOpacity(0);



        //handle events
        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                if(event.getEventType() == MouseEvent.MOUSE_ENTERED)
                    hoverOnHeader(pane, rectangle, lblTitle, lblInfo, true);
                else
                    hoverOnHeader(pane, rectangle, lblTitle, lblInfo, false);
            }//end handle
        };

        pane.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler);
        pane.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandler);

        pane.getChildren().addAll(rectangle, lblTitle, lblInfo);
        return pane;
    }//end makeHeaderCard


    private void hoverOnHeader(Pane recPane, Rectangle rectangle, Label lblTitle, Label lblInfo, boolean status)
    {
        //status true means enter
        BoxBlur blur = new BoxBlur(3,3,3);

        FadeTransition titleFade =  new FadeTransition();
        FadeTransition infoFade = new FadeTransition();
        titleFade.setNode(lblTitle);
        infoFade.setNode(lblInfo);
        titleFade.setAutoReverse(true);
        infoFade.setAutoReverse(true);
        titleFade.setCycleCount(1);
        infoFade.setCycleCount(1);

        if(status)
        {
            rectangle.setEffect(blur);
            titleFade.setFromValue(0);
            titleFade.setToValue(1);
            titleFade.setDuration(Duration.seconds(0.4));
            infoFade.setFromValue(0);
            infoFade.setToValue(1);
            infoFade.setDuration(Duration.seconds(0.4));
        }
        else
        {
            rectangle.setEffect(null);
            titleFade.setFromValue(1);
            titleFade.setToValue(0);
            titleFade.setDuration(Duration.seconds(0.3));
            infoFade.setFromValue(1);
            infoFade.setToValue(0);
            infoFade.setDuration(Duration.seconds(0.3));
        }

        //TODO debug this shit
        /*
        titleFade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Point point =  MouseInfo.getPointerInfo().getLocation();
                int stageX = (int) Main.stage.getX();
                int stageY = (int) Main.stage.getY();

                point.x -= stageX;
                point.y -= stageY;

                System.out.println(point.x + "   " + point.y);
                if((point.x > recPane.getLayoutX() + 120 || point.x < recPane.getLayoutX() || point.y < 59 || point.y > 179) && status)
                {
                    System.out.println(point.x + "  " + recPane.getLayoutX());
                    System.out.println(point.y + "  " + recPane.getLayoutY());
                    hoverOnHeader(recPane, rectangle, lblTitle, lblInfo, false);
                }
            }
        });
        */

        titleFade.play();
        infoFade.play();
    }//end hoverOnHeader


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


    public void loginDropdown(int id, String name)
    {
        try
        {
            txtLDPassword.setText("");
            double yValue = 0;
            if(dropdownOpen)
            {
                //close it
                yValue = -350;
                dropdownOpen = false;
                anchMain.setEffect(null);
            }
            else
            {
                //open it
                dropdownOpen = true;
                yValue = 350;
                BoxBlur blur = new BoxBlur(3,3,5);
                anchMain.setEffect(blur);
                //set up card info
                String imagePath = "file:data/profilePictures/" + id + ".png";
                circleLD.setFill(new ImagePattern(new Image(imagePath, 70, 70, false, false)));
                lblLDName.setText(name);
            }

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.6),paneLoginDrop);
            translateTransition.setByY(yValue);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(1);
            translateTransition.play();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//end loginDropdown


    @FXML
    private void closeLD()
    {
        loginDropdown(0, "");
    }//end closeLD


}//end class
















