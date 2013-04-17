/*
 * Created by JFormDesigner on Wed Apr 17 12:30:19 CEST 2013
 */

package minigolf;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.swingx.*;

/**
 * @author Pol Dubinski
 */
public class MinigolfGui extends JFrame {
	public MinigolfGui() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Pol Dubinski
		panel1 = new JPanel();
		button1 = new JButton();
		button2 = new JButton();
		label1 = new JLabel();
		textField1 = new JTextField();
		button3 = new JButton();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();

		//======== this ========
		setBackground(new Color(255, 102, 255));
		setTitle("SUPER MINIGOLF!!!!1111!!!!!11!1                                1337");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel1 ========
		{
			panel1.setBackground(new Color(102, 255, 102));
			panel1.setPreferredSize(new Dimension(100, 40));

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(null);

			//---- button1 ----
			button1.setText("Start");
			panel1.add(button1);
			button1.setBounds(5, 5, 60, 30);

			//---- button2 ----
			button2.setText("Stop");
			panel1.add(button2);
			button2.setBounds(75, 5, 60, 30);

			//---- label1 ----
			label1.setText(" Name:");
			panel1.add(label1);
			label1.setBounds(145, 5, 40, 29);

			//---- textField1 ----
			textField1.setPreferredSize(new Dimension(55, 23));
			panel1.add(textField1);
			textField1.setBounds(185, 5, 90, 30);

			//---- button3 ----
			button3.setText("Submit");
			button3.setPreferredSize(new Dimension(55, 23));
			panel1.add(button3);
			button3.setBounds(300, 5, 70, 30);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < panel1.getComponentCount(); i++) {
					Rectangle bounds = panel1.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = panel1.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				panel1.setMinimumSize(preferredSize);
				panel1.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(panel1, BorderLayout.SOUTH);

		//======== panel2 ========
		{
			panel2.setBackground(new Color(102, 51, 255));
			panel2.setPreferredSize(new Dimension(800, 600));
			panel2.setMaximumSize(new Dimension(800, 600));
			panel2.setMinimumSize(new Dimension(800, 600));
			panel2.setLayout(new BorderLayout());
		}
		contentPane.add(panel2, BorderLayout.CENTER);

		//======== panel3 ========
		{
			panel3.setPreferredSize(new Dimension(100, 40));
			panel3.setLayout(null);

			//---- label2 ----
			label2.setText("Time passed:");
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			label2.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel3.add(label2);
			label2.setBounds(0, 0, label2.getPreferredSize().width, 40);

			//---- label3 ----
			label3.setText("Ball Shots:");
			label3.setHorizontalAlignment(SwingConstants.CENTER);
			label3.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel3.add(label3);
			label3.setBounds(525, 0, 75, 40);

			//---- label4 ----
			label4.setText("MM:SS:HH");
			label4.setHorizontalAlignment(SwingConstants.CENTER);
			label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			panel3.add(label4);
			label4.setBounds(100, 0, 80, 40);

			//---- label5 ----
			label5.setText(" 1337");
			label5.setHorizontalAlignment(SwingConstants.CENTER);
			label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			panel3.add(label5);
			label5.setBounds(600, 0, 75, 40);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < panel3.getComponentCount(); i++) {
					Rectangle bounds = panel3.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = panel3.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				panel3.setMinimumSize(preferredSize);
				panel3.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(panel3, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Pol Dubinski
	private JPanel panel1;
	private JButton button1;
	private JButton button2;
	private JLabel label1;
	private JTextField textField1;
	private JButton button3;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
