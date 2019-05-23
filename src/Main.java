import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame f =new JFrame();//creating instance of JFrame

        JButton enter = new JButton("Enter");//creating instance of JButton
        JButton primerP = new JButton("Primer Parcial");
        enter.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(enter);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible





    }
}
