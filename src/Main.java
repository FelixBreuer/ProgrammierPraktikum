import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame implements ActionListener {
	JButton[] buttons = new JButton[100];
	Color btnDefault;
    Color btnSecond = new Color(0,0,0);
    int[][] array = new int[10][10];

	public Main() {

		array[8][1] = 3; //Start
		array[2][8] = 4; //Ziel
		
		this.getContentPane().setLayout(new GridLayout(11,11));

		for(int i=0; i<28; i++){
			buttons[i] = new JButton(""+i);
			buttons[i].addActionListener(this);
			buttons[i].setIcon(new ImageIcon(this.getClass().getResource("ressources/grass.jpg")));
			buttons[i].setName("0");
			add(buttons[i]);
		}
		JLabel target = new JLabel("Ziel");
		add(target);
		for(int i=29; i<81; i++){
			buttons[i] = new JButton(""+i);
			buttons[i].addActionListener(this);
			buttons[i].setIcon(new ImageIcon(this.getClass().getResource("ressources/grass.jpg")));
			buttons[i].setName("0");
			add(buttons[i]);
		}
		JLabel start = new JLabel("Start");
		add(start);
		for(int i=82; i<100; i++){
			buttons[i] = new JButton(""+i);
			buttons[i].addActionListener(this);
			buttons[i].setIcon(new ImageIcon(this.getClass().getResource("ressources/grass.jpg")));
			buttons[i].setName("0");
			add(buttons[i]);
		}
		
		JButton option1 = new JButton("Show");
		option1.addActionListener(this);
		JButton option2 = new JButton("Run");
		option2.addActionListener(this);
		add(option1);
		add(option2);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		
		if (action.equals("Show")){
			for(int i=0; i<10; i++){
				System.out.println();
			}
			for(int i=0; i<10; i++){
				for(int j=0; j<10; j++){
					System.out.print(array[i][j]);
				}
				System.out.println();
			}
		}
		
		else if(action.equals("Run")){
			System.out.println("hi");
		}
		else {
			for(int i=0; i<100; i++){
				if (action.equals(""+i)) {
					
					if(buttons[i].getName().equals("0"))
					{
						System.out.println("Button "+i+" changed to Wall!");
						buttons[i].setIcon(new ImageIcon(this.getClass().getResource("ressources/wall.jpg")));
						buttons[i].setName("1");
						array[i/10][i%10] = 1;
					}
					else
					{
						System.out.println("Button "+i+" changed to Grass!");
						buttons[i].setIcon(new ImageIcon(this.getClass().getResource("ressources/grass.jpg")));
						buttons[i].setName("0");
						array[i/10][i%10] = 0;
					}
				}
			}	
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new Main();
		frame.setSize(500,550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createAndShowGUI();
//			}
//		});
		
		aStar aStar = new aStar();

	}
}
