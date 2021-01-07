package application;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton blueButton;

    @FXML
    private JFXButton orangeButton;

    Circle circle;
    @FXML
    void initialize() {
    	
    	fadeAnimation(.5, orangeButton, 1, 0);
    	
    	blueButton.setOnAction(e->{
    		
    		 circle=new Circle(50);
    		
    		PathTransition pathTransition=new PathTransition(Duration.seconds(1),circle);
    		pathTransition.setNode(blueButton);
    		pathTransition.setCycleCount(1);
    		pathTransition.play();
    		
    		pathTransition.setOnFinished(e1->{
    			
    			ScaleTransition scaleTransition=scaleAnimation(1, blueButton, 10, 10);
    			
    			scaleTransition.setOnFinished(e2->{
    				
    				TranslateTransition transition=translateAnimation(.5, blueButton, 0, -500);
    				
    				transition.setOnFinished(e3->{
    				FadeTransition fadeTransition=	fadeAnimation(.5, orangeButton, 0, 1);
    				
    				fadeTransition.setOnFinished(e4->{
    				  translateAnimation(.5, orangeButton, 0, -400);
    				});
    				});
    			});
    		});
    	});
       
    	orangeButton.setOnAction(e->{
    		TranslateTransition transition=translateAnimation(.5, orangeButton, 0, 0);
    		transition.setOnFinished(e1->{
    			
    			fadeAnimation(.5, orangeButton, 1, 0);
    			
    			TranslateTransition transition1=translateAnimation(.5, blueButton, 0, 0);
    			
    			transition1.setOnFinished(e2->{
    				ScaleTransition scaleTransition=scaleAnimation(1, blueButton, -10, -10);
    				scaleTransition.setOnFinished(e3->{
    					PathTransition pathTransition=new PathTransition(Duration.seconds(1),circle);
    		    		pathTransition.setNode(blueButton);
    		    		pathTransition.setCycleCount(1);
    		    		pathTransition.play();
    				});
    				
    			});
    		});
    	});
    }
    
    
    public FadeTransition fadeAnimation(double duration,Node node,double from,double to) {
    	
    	FadeTransition fadeTransition=new FadeTransition(Duration.seconds(duration),node);
    	fadeTransition.setFromValue(from);
    	fadeTransition.setToValue(to);
    	fadeTransition.play();
    	return fadeTransition;
    }
    
    public ScaleTransition scaleAnimation(double duration,Node node,double byX,double byY) {
    	ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(duration),node);
    	scaleTransition.setByX(byX);
    	scaleTransition.setByY(byY);
    	scaleTransition.play();
    	return scaleTransition;
    }
    
    public TranslateTransition translateAnimation(double duration,Node node,double byX,double byY) {
    	TranslateTransition transition=new TranslateTransition(Duration.seconds(duration),node);
    	transition.setToX(byX);
    	transition.setToY(byY);
    	transition.play();
    	return transition;
    }
}
