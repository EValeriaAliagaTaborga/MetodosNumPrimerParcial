import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame f =new JFrame();//creating instance of JFrame
        JFrame test1 = new JFrame();

        //f.setContentPane(test1);
        test1.setVisible(false);
        test1.setSize(500,500);
        test1.setLayout(null);

        JButton enter = new JButton("Enter");//creating instance of JButton
        JButton primerP = new JButton("Primer Parcial");
        enter.setBounds(130,200,100, 40);//x axis, y axis, width, height

        test1.add(primerP);
        f.add(enter);//adding button in JFrame

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test1.setVisible(true);
            }
        });


        f.setSize(800,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.getContentPane().setBackground(new java.awt.Color(39, 77, 140));
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

    }
}
