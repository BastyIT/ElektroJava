package Elektrotechnik;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import myUtil.WinUtil;

public class Fenster extends JFrame implements ActionListener, WindowListener
{
	private JLabel lbInhalt;
	private JButton btnGrundlagen, btnWechselStrom, btnDrehStrom;
	
	private JMenuBar menuBar;
	private JMenu menuDatei;
	private JMenuItem miBeenden;

	
	public Fenster()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		// Notwendige Anweisungen um einen Frame transparent machen zu können.
		this.setUndecorated(true);
		this.getRootPane() .setWindowDecorationStyle(JRootPane.FRAME);

		this.setTitle("Lernprogramm \"Elektrotechnik-Simulator\"");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/elektrikLOGO.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);

		this.setSize(350, 175);
		this.setResizable(false);
		this.setLayout(null);
		
		//===========MENU===========================================//
		
		menuBar = new JMenuBar();
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miBeenden = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Beenden", null, 'D', "Programm beenden");
		this.setJMenuBar(menuBar);
		
		//==========================================================//


		
		
		lbInhalt = new JLabel("inhalt".toUpperCase(), JLabel.CENTER);
//		lbInhalt.setForeground(Color.RED);
		lbInhalt.setFont(new Font("Serif", Font.BOLD, 20));
		lbInhalt.setBounds(20, 20, 250, 20);
		this.add(lbInhalt);
		
		
		
		btnGrundlagen = new JButton("Grundbegriffe der Elektrotechnik");
		btnGrundlagen.setForeground(Color.BLUE);
		btnGrundlagen.setFont(new Font("Serif", Font.ITALIC, 15));
		btnGrundlagen.setBounds(20, 50, 250, 20);
		btnGrundlagen.addActionListener(this);
		this.add(btnGrundlagen);
		
		btnWechselStrom = new JButton("Wechselstrom");
		btnWechselStrom.setForeground(Color.BLUE);
		btnWechselStrom.setFont(new Font("Serif", Font.ITALIC, 15));
		btnWechselStrom.setBounds(20, 70, 250, 20);
		btnWechselStrom.addActionListener(this);
		this.add(btnWechselStrom);
		
		btnDrehStrom = new JButton("Dreiphasestrom");
		btnDrehStrom.setForeground(Color.BLUE);
		btnDrehStrom.setFont(new Font("Serif", Font.ITALIC, 15));
		btnDrehStrom.setBounds(20, 90, 250, 20);
		btnDrehStrom.addActionListener(this);
		this.add(btnDrehStrom);			
		
	}
	
	private void callGrundlagen()
	{
		Grundlagen elGrundlagen = new Grundlagen();
//		elGrundlagen.showGrundlagen();
		elGrundlagen.showDialog(this);
	}
	
	private void callWechselStrom()
	{
		Wechselstrom ac = new Wechselstrom();
		ac.showDialog(this);

	}
	
	private void callDrehStrom()
	{
		Drehstrom threePhase = new Drehstrom();
		threePhase.showDialog();
	}
	
	
	public void showFrame()
	{
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnGrundlagen)
		{
			callGrundlagen();
			System.out.println("Fenster \"Grundlagen der Elektrotechnik wurde aufgerufen\"");
		}
		
		if (e.getSource() == btnWechselStrom)
		{
			callWechselStrom();
			System.out.println("Fenster \"Wechselstrom\" wurde aufgerufen");
		}
		
		if (e.getSource() == btnDrehStrom)
		{
			callDrehStrom();
			System.out.println("Fenster \"Drehstrom\" wurde aufgerufen");
		}
		
		if (e.getSource() == miBeenden)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (menuDatei.isEnabled())
			this.dispose();

		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
