/*
 ================
 = MEGAGOLF GUI =
 ================
 */
package MegaGolf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 * @author Claude Ernzer, Daniel Warnimont, Luc Welter
 */
public class mgGui {

	//FIELDS
	private JFrame MGframe;
	private JPanel pnlCtrl;
	private JPanel pnlMain;
	private JPanel pnlStatus;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	public JTextField textField1;
	public static mgGame game;
	public static mgClock clk;
	public static mgSQL sql;
	public java.util.Timer guiTimer;
	public guiTimerTask guiTask;
	Graphics g;

	//CONSTRUCTORS
	public mgGui() {
		initComponents();
	}

	//METHODS
	public void initComponents() {
		//Component initialization
		MGframe = new JFrame();
		pnlCtrl = new JPanel();
		pnlMain = new JPanel();
		pnlStatus = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		textField1 = new JTextField();
		game = new mgGame();
		clk = new mgClock();
		sql = new mgSQL();
		guiTimer = new java.util.Timer();
		guiTask = new guiTimerTask();

		// ======== MGframe ========
		{
			MGframe.setBackground(new Color(255, 102, 255));
			MGframe.setTitle("Megagolf v1.33.7");
			Container MGframeContentPane = MGframe.getContentPane();
			MGframeContentPane.setLayout(new BorderLayout());
			MGframe.setResizable(false);

			// ======== pnlCtrl ========
			{
				pnlCtrl.setBackground(new Color(102, 255, 102));
				pnlCtrl.setPreferredSize(new Dimension(100, 40));

				// ---- Watermark ----
				pnlCtrl.setBorder(new javax.swing.border.CompoundBorder(
						new javax.swing.border.TitledBorder(
								new javax.swing.border.EmptyBorder(0, 0, 0, 0),
								"Minigolf FSTC SS 12-13 INFO II",
								javax.swing.border.TitledBorder.CENTER,
								javax.swing.border.TitledBorder.BOTTOM,
								new java.awt.Font("Dialog", java.awt.Font.BOLD,
										12), java.awt.Color.red), pnlCtrl
										.getBorder()));
				pnlCtrl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
					public void propertyChange(java.beans.PropertyChangeEvent e) {
						if ("border".equals(e.getPropertyName())) {
							throw new RuntimeException();
						}
					}
				});

				pnlCtrl.setLayout(null);

				// ---- button1 ---- (START BUTTON)

				button1.setText("Start");
				pnlCtrl.add(button1);
				button1.setBounds(5, 5, 90, 30);
				button1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						button1ActionPerformed(e);
					}
				});

				// ---- button2 ---- (PAUSE BUTTON)

				button2.setText("Pause");
				pnlCtrl.add(button2);
				button2.setBounds(105, 5, 90, 30);
				button2.setEnabled(false);
				button2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						button2ActionPerformed(e);
					}
				});

				// ---- label1 ----
				label1.setText(" Name:");
				pnlCtrl.add(label1);
				label1.setBounds(565, 5, 40, 29);

				// ---- textField1 ----
				textField1.setPreferredSize(new Dimension(55, 23));
				pnlCtrl.add(textField1);
				textField1.setBounds(615, 5, 90, 30);

				// ---- button3 ---- (SUBMIT BUTTON)

				button3.setText("Submit");
				button3.setPreferredSize(new Dimension(55, 23));
				pnlCtrl.add(button3);
				button3.setBounds(715, 5, 80, 30);
				button3.setEnabled(false);
				button3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						button3ActionPerformed(e);
					}
				});

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
				for (int i = 0; i < pnlCtrl.getComponentCount(); i++) {
					Rectangle bounds = pnlCtrl.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width,
							preferredSize.width);
					preferredSize.height = Math.max(bounds.y
							+ bounds.height, preferredSize.height);
				}
				Insets insets = pnlCtrl.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				pnlCtrl.setMinimumSize(preferredSize);
				pnlCtrl.setPreferredSize(preferredSize);
				}
			}
			MGframeContentPane.add(pnlCtrl, BorderLayout.SOUTH);

			// ======== pnlMain ========
			{
				pnlMain.setBackground(new Color(102, 51, 255));
				pnlMain.setPreferredSize(new Dimension(800, 600));
				pnlMain.setMaximumSize(new Dimension(800, 600));
				pnlMain.setMinimumSize(new Dimension(800, 600));
				pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
				pnlMain.add(game);

			}

			MGframeContentPane.add(pnlMain, BorderLayout.CENTER);

			// ======== pnlStatus ========
			{
				pnlStatus.setPreferredSize(new Dimension(100, 40));
				pnlStatus.setLayout(null);

				// ---- label2 ----
				label2.setText("Time passed:");
				label2.setHorizontalAlignment(SwingConstants.CENTER);
				label2.setFont(new Font("Tahoma", Font.BOLD, 14));
				pnlStatus.add(label2);
				label2.setBounds(0, 0, label2.getPreferredSize().width, 40);

				// ---- label3 ----
				label3.setText("Ball Shots:");
				label3.setHorizontalAlignment(SwingConstants.CENTER);
				label3.setFont(new Font("Tahoma", Font.BOLD, 14));
				pnlStatus.add(label3);
				label3.setBounds(525, 0, 75, 40);

				// ---- label4 ----
				label4.setHorizontalAlignment(SwingConstants.CENTER);
				label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				pnlStatus.add(label4);
				label4.setBounds(100, 0, 80, 40);

				// ---- label5 ----
				label5.setHorizontalAlignment(SwingConstants.CENTER);
				label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				pnlStatus.add(label5);
				label5.setBounds(600, 0, 75, 40);


				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for (int i = 0; i < pnlStatus.getComponentCount(); i++) {
						Rectangle bounds = pnlStatus.getComponent(i)
								.getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width,
								preferredSize.width);
						preferredSize.height = Math.max(bounds.y
								+ bounds.height, preferredSize.height);
					}
					Insets insets = pnlStatus.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					pnlStatus.setMinimumSize(preferredSize);
					pnlStatus.setPreferredSize(preferredSize);
				}
			}
			MGframeContentPane.add(pnlStatus, BorderLayout.NORTH);
			MGframe.pack();
			MGframe.setLocationRelativeTo(MGframe.getOwner());
		}
	}

	//Update Labels
	class guiTimerTask extends TimerTask{

		public void run(){

			//update timer & hit counter
			label4.setText(clk.toStringdsp());
			label5.setText(" " + game.clicks);

			//toggle submit button
			if (game.goal.hasBall() && !textField1.getText().isEmpty()) {button3.setEnabled(true);}
			else {button3.setEnabled(false);}

			//ends the game
			if(game.goal.hasBall()){
				game.stop();
				clk.stop();
				button1.setEnabled(true);
				button2.setEnabled(false);
				button2.setText("Pause");
			}	
		}
	}

	//Start Button
	private void button1ActionPerformed(ActionEvent e) {
		clk.start();
		game.start();
		button1.setEnabled(false);
		button2.setEnabled(true);
	}

	//Pause Button
	private void button2ActionPerformed(ActionEvent e) {
		if (clk.paused) {
			clk.resume();
			game.resume();
			button2.setText("Pause");
			//			System.out.println("Resumed");
		} else {
			clk.stop();
			game.pause();
			button2.setText("Resume");
			//			System.out.println("Paused");
		}
	}

	//Submit Button
	public void button3ActionPerformed(ActionEvent e) {
		try {
			sql.nick = textField1.getText();
			sql.hits = game.clicks;
			sql.time = clk.toStringdsp();
			sql.submit();
			System.out.println("Submitted Score");
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	//MAIN METHOD
	public static void main(String arg[]) {
		mgGui gui = new mgGui();

		//establish connection to mySQL DB
		sql.connect();

		gui.guiTimer.schedule(gui.guiTask, 0, 1);
		gui.MGframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.MGframe.setVisible(true);
		gui.MGframe.pack(); 
	}
}
