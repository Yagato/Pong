import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JFrame {

    JLabel player1 = new JLabel();
    JLabel player2 = new JLabel();
    JLabel labelScorePlayer1 = new JLabel("0");
    JLabel labelScorePlayer2 = new JLabel("0");
    JLabel ball = new JLabel();
    JPanel panel = new JPanel();
    Random random = new Random();

    int startPlayer1 = 90, startPlayer2 = 90;
    int startBallX = 300, startBallY = 220;
    double speedX = 15, speedY = 15;
    int randomStartX = random.nextInt(2 - 1 + 1) + 1;
    int randomStartY = random.nextInt(2 - 1 + 1) + 1;
    int scorePlayer1 = 0, scorePlayer2 = 0;

    public Game(){
        super("Pong");
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setSize(640, 480);
        setResizable(false);

        player1.setIcon(new ImageIcon("img/bar.png"));
        player1.setBounds(10, startPlayer1, 40, 270);

        player2.setIcon(new ImageIcon("img/bar.png"));
        player2.setBounds(590, startPlayer2, 40, 270);

        ball.setIcon(new ImageIcon("img/ball.png"));
        ball.setBounds(startBallX, startBallY, 20, 20);

        labelScorePlayer1.setFont(new Font("Arial", Font.BOLD, 42));
        labelScorePlayer1.setForeground(Color.white);
        labelScorePlayer1.setBounds(280, 10, 42, 42);

        labelScorePlayer2.setFont(new Font("Arial", Font.BOLD, 42));
        labelScorePlayer2.setForeground(Color.white);
        labelScorePlayer2.setBounds(340, 10, 42, 42);

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(640, 480));
        panel.setBackground(Color.black);
        panel.add(player1);
        panel.add(labelScorePlayer1);
        panel.add(player2);
        panel.add(labelScorePlayer2);
        panel.add(ball);

        Events events = new Events();

        getContentPane().add(panel);
        pack();
        addKeyListener(events);
        setVisible(true);
        setLocationRelativeTo(null);

        if(randomStartX == 1)
            speedX *= -1;
        if(randomStartY == 1)
            speedY *= -1;

        ballMovement();

        wincon();
    }

    public void ballMovement(){
        Thread thread = new Thread(() -> {
            while(true) {
                if(startBallX >= 620) {
                    speedX = -speedX;
                    scorePlayer1 += 5;
                    labelScorePlayer1.setText(Integer.toString(scorePlayer1));
                }
                else if(startBallX <= 0) {
                    speedX *= -1;
                    scorePlayer2 += 5;
                    labelScorePlayer2.setText(Integer.toString(scorePlayer2));
                }

                if(startBallY >= 460)
                    speedY = -speedY;
                else if(startBallY <= 0)
                    speedY *= -1;

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

    public void wincon(){
        Thread thread = new Thread(() -> {
            while(true) {
                if (scorePlayer1 == 5) {
                    JOptionPane.showMessageDialog(this, "Player 1 won");
                    restart();
                } else if (scorePlayer2 == 5) {
                    JOptionPane.showMessageDialog(this, "Player 2 won");
                    restart();
                }
                try{
                    Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.run();
    }

    public void restart(){
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        startBallX = 300;
        startBallY = 220;
        startPlayer1 = 90;
        startPlayer2 = 90;
        randomStartX = random.nextInt(2 - 1 + 1) + 1;
        randomStartY = random.nextInt(2 - 1 + 1) + 1;
        player1.setBounds(10, startPlayer1, 40, 270);
        player2.setBounds(590, startPlayer2, 40, 270);
        ball.setBounds(startBallX, startBallY, 20, 20);
        labelScorePlayer1.setText("0");
        labelScorePlayer2.setText("0");
    }

    public class Events implements KeyListener {
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
