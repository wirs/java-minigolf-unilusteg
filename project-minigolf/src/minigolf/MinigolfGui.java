/*
 * Created by JFormDesigner on Wed Apr 17 12:30:19 CEST 2013
 */

package minigolf;

import java.awt.*;
import javax.swing.*;
//import org.jdesktop.swingx.*;

import minigolf.MinigolfGame.MGTimerTask;

/**
 * @author Pol Dubinski
 */
public class MinigolfGui {
	public MinigolfGui() {
		initComponents();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Pol Dubinski
		MGframe = new JFrame();
		pnlCtrl = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		label1 = new JLabel();
		textField1 = new JTextField();
		button3 = new JButton();
		pnlMain = new JPanel();
		pnlStatus = new JPanel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		Game = new MinigolfGame();
			

		//======== MGframe ========
		{
			MGframe.setBackground(new Color(255, 102, 255));
			MGframe.setTitle("SUPER MINIGOLF!!!!1111!!!!!11!1                                1337");
			Container MGframeContentPane = MGframe.getContentPane();
			MGframeContentPane.setLayout(new BorderLayout());

			//======== pnlCtrl ========
			{
				pnlCtrl.setBackground(new Color(102, 255, 102));
				pnlCtrl.setPreferredSize(new Dimension(100, 40));

				// JFormDesigner evaluation mark
				pnlCtrl.setBorder(new javax.swing.border.CompoundBorder(
					new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
						"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
						javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
						java.awt.Color.red), pnlCtrl.getBorder())); pnlCtrl.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

				pnlCtrl.setLayout(null);

				//---- button1 ----
				button1.setText("Start");
				pnlCtrl.add(button1);
				button1.setBounds(5, 5, 60, 30);

				//---- button2 ----
				button2.setText("Stop");
				pnlCtrl.add(button2);
				button2.setBounds(75, 5, 60, 30);

				//---- label1 ----
				label1.setText(" Name:");
				pnlCtrl.add(label1);
				label1.setBounds(145, 5, 40, 29);

				//---- textField1 ----
				textField1.setPreferredSize(new Dimension(55, 23));
				pnlCtrl.add(textField1);
				textField1.setBounds(185, 5, 90, 30);

				//---- button3 ----
				button3.setText("Submit");
				button3.setPreferredSize(new Dimension(55, 23));
				pnlCtrl.add(button3);
				button3.setBounds(300, 5, 70, 30);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < pnlCtrl.getComponentCount(); i++) {
						Rectangle bounds = pnlCtrl.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = pnlCtrl.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					pnlCtrl.setMinimumSize(preferredSize);
					pnlCtrl.setPreferredSize(preferredSize);
				}
			}
			MGframeContentPane.add(pnlCtrl, BorderLayout.SOUTH);

			//======== pnlMain ========
			{
				pnlMain.setBackground(new Color(102, 51, 255));
				pnlMain.setPreferredSize(new Dimension(800, 600));
				pnlMain.setMaximumSize(new Dimension(800, 600));
				pnlMain.setMinimumSize(new Dimension(800, 600));
				pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
				 
				pnlMain.add(Game);
				
				
			}
			
			MGframeContentPane.add(pnlMain, BorderLayout.CENTER);

			//======== pnlStatus ========
			{
				pnlStatus.setPreferredSize(new Dimension(100, 40));
				pnlStatus.setLayout(null);

				//---- label2 ----
				label2.setText("Time passed:");
				label2.setHorizontalAlignment(SwingConstants.CENTER);
				label2.setFont(new Font("Tahoma", Font.BOLD, 14));
				pnlStatus.add(label2);
				label2.setBounds(0, 0, label2.getPreferredSize().width, 40);

				//---- label3 ----
				label3.setText("Ball Shots:");
				label3.setHorizontalAlignment(SwingConstants.CENTER);
				label3.setFont(new Font("Tahoma", Font.BOLD, 14));
				pnlStatus.add(label3);
				label3.setBounds(525, 0, 75, 40);

				//---- label4 ----
				label4.setText("MM:SS:HH");
				label4.setHorizontalAlignment(SwingConstants.CENTER);
				label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
				pnlStatus.add(label4);
				label4.setBounds(100, 0, 80, 40);

				//---- label5 ----
				label5.setText(" 1337");
				label5.setHorizontalAlignment(SwingConstants.CENTER);
				label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
				pnlStatus.add(label5);
				label5.setBounds(600, 0, 75, 40);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < pnlStatus.getComponentCount(); i++) {
						Rectangle bounds = pnlStatus.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Pol Dubinski
	private JFrame MGframe;
	private JPanel pnlCtrl;
	private JButton button1;
	private JButton button2;
	private JLabel label1;
	private JTextField textField1;
	private JButton button3;
	private JPanel pnlMain;
	private JPanel pnlStatus;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	public MinigolfGame Game;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public static void main(String arg[]){
		MinigolfGui panel=new MinigolfGui();
	
		//MinigolfGame panel = new MinigolfGame(); 

		panel.MGframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//panel.MGframe.setContentPane(panel); 
		panel.MGframe.setVisible(true);
		java.util.Timer 
		vgTimer = new java.util.Timer();  // Create a Timer object
		vgTimer.schedule(panel.Game.mgTask, 0, 20);
		
		
	
	}
}
