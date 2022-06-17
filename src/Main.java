import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {

    public static JFrame frame = new JFrame();
    public static JLabel player1 = new JLabel();
    public static JLabel player2 = new JLabel();
    public static JLabel ball = new JLabel();
    public static JPanel panel = new JPanel();

    public static int startPlayer1 = 90, startPlayer2 = 90;
    public static int startBallX = 300, startBallY = 220;
    public static double speedX = 5, speedY = 5;

    public static void main(String[] args) {
        frame.setTitle("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setSize(640, 480);
        frame.setResizable(false);

        player1.setIcon(new ImageIcon("img/bar.png"));
        player1.setBounds(10, startPlayer1, 40, 270);

        player2.setIcon(new ImageIcon("img/bar.png"));
        player2.setBounds(590, startPlayer2, 40, 270);

        ball.setIcon(new ImageIcon("img/ball.png"));
        ball.setBounds(startBallX, startBallY, 20, 20);

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(640, 480));
        panel.setBackground(Color.black);
        panel.add(player1);
        panel.add(player2);
        panel.add(ball);

        Events events = new Events();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.addKeyListener(events);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        ballMovement();
    }

    public static void ballMovement(){
        Thread thread = new Thread(() -> {
            while(true) {
                if(startBallX == 620)
                    speedX = -speedX;
                else if(startBallX <= 0)
                    speedX = speedX * -1;

                if(startBallY >= 460)
                    speedY = -speedY;
                else if(startBallY <= 0)
                    speedY = speedY * -1;

                startBallX += speedX;
                startBallY += speedY;

                ball.setLocation(startBallX, startBallY);
                try{
                    Thread.sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static class Events implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                startPlayer1 -= 10;
                player1.setLocation(10, startPlayer1);
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                startPlayer1 += 10;
                player1.setLocation(10, startPlayer1);
            }
            else if(e.getKeyCode() == KeyEvent.VK_W){
                startPlayer2 -= 10;
                player2.setLocation(575, startPlayer2);
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                startPlayer2 += 10;
                player2.setLocation(575, startPlayer2);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}