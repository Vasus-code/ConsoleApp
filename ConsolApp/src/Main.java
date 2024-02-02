import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import static java.lang.Math.round;



public class Main {
    public static void main(String[] args) throws IOException {
        boolean run = true;

        boolean draw = false;
        int width = 80;
        int height = 21;
        String frame = "";
        long fps = 1000/30;
        String[] symbols = new String[]{"\n", "-", "@"};

        System.out.print("\033\143"); // clear terminal


        Controller controller = new Controller();


        while (run){
            for (int j = 0; j != height; j++){
                if (j == round(controller.y)){
                    draw = true;
                }
                for (int i = 0; i != width; i++) {

                    if ((i == controller.x) & (draw)){
                        frame += symbols[2];
                        draw = false;
                    }else{
                        frame += symbols[1];
                    }




                }
                frame += symbols[0];
            }

            System.out.println(frame);
            frame = "";
            try{
                Thread.sleep(fps);
            }catch (Exception e){
                System.out.println(e);
            }

            controller.move();
            controller.wallCheck(height, width);
            controller.labelXY.setText("X: " + controller.x + "   Y: " + controller.y );

        }
    }
}

 class Controller extends JFrame implements KeyListener {

     int x = 40;
     float y = 12f;

    public int direction = -1;
    /*
    -1 - no direction
    0-360 - direction in degrees
    0 - right
    90 - up
    */
    Controller(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,150);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        add(labelXY);


    }
     JLabel labelXY = new JLabel("Coordinates");


     public void move(){
         if (direction == 0){
             x++;
         }if (direction == 180){
             x--;
         }if (direction == 90){
             y -= 0.5f;
         }if (direction == 270){
             y += 0.5f;
         }
     }
     public void wallCheck(int height, int width){
        if (x >= 79){
            x = 79;
        } else if (x < 0) {
            x = 0;
        }else if (round(y) < 0) {
            y = 0;
        }else if (round(y) >= 19.5f) {
            y = 19.5f;
        }
     }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        if (key == KeyEvent.VK_W){
            direction = 90;
        } if (key == KeyEvent.VK_A){
            direction = 180;
        } if (key == KeyEvent.VK_D){
            direction = 0;
        } if (key == KeyEvent.VK_S){
            direction = 270;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        direction = -1;
    }
}