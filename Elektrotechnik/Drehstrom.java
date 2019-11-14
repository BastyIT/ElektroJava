package Elektrotechnik;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import myUtil.WinUtil;

public class Drehstrom  extends JDialog implements WindowListener, ChangeListener, KeyListener, FocusListener, ActionListener
{
	//===========Initialisierung von Konstanten=================//
	private static final int WERT_MIN = 0;
	private static final int VOTAGE_MAX = 400;
	private static final int RESISTANCE_MAX = 1000;
	
	//==========================================================//
	
	//===========Deklarationen von Instanzen====================//
	
	// Labels
	private JLabel lbSpannung1, lbSpannung2, lbSpannung3;
	private JLabel lbSpannung12, lbSpannung23, lbSpannung31;	
	private JLabel lbPhaseU1, lbPhaseU2, lbPhaseU3;
	private JLabel lbPhaseU12, lbPhaseU23, lbPhaseU31;
	
	private JLabel lbWirkWiderstand1, lbBlindWiderstand1;
	private JLabel lbWirkWiderstand2, lbBlindWiderstand2;
	private JLabel lbWirkWiderstand3, lbBlindWiderstand3;
	
	private JLabel lbStrom1, lbStrom2, lbStrom3, lbStromN;	
	private JLabel lbStrom12, lbStrom21, lbStrom31; 
	
	private JLabel lbPhaseI1, lbPhaseI2, lbPhaseI3, lbPhaseIN;
	private JLabel lbPhaseI12, lbPhaseI23, lbPhaseI31;
	
	private JLabel lbLeistungQ, lbWirkLeistungQ, lbBlindLeistungQ;
	private JLabel lbLeistungV, lbWirkLeistungV, lbBlindLeistungV;
	
	// Textfelder
	private JTextField txtSpannung1, txtSpannung2, txtSpannung3;
	private JTextField txtSpannung12, txtSpannung23, txtSpannung31;
	private JTextField txtPhaseU1, txtPhaseU2, txtPhaseU3;
	private JTextField txtPhaseU12, txtPhaseU23, txtPhaseU31;

	
	private JTextField txtWirkWiderstand1, txtBlindWiderstand1; 
	private JTextField txtWirkWiderstand2, txtBlindWiderstand2;
	private JTextField txtWirkWiderstand3, txtBlindWiderstand3;
	
	private JTextField txtStrom1, txtStrom2, txtStrom3, txtStromN;
	private JTextField txtStrom12, txtStrom21, txtStrom31;
	
	private JTextField txtPhaseI1, txtPhaseI2, txtPhaseI3, txtPhaseIN;
	private JTextField txtPhaseI12, txtPhaseI23, txtPhaseI31;
	
	private JTextField txtLeistungQ, txtWirkLeistungQ, txtBlindLeistungQ;
	private JTextField txtLeistungV, txtWirkLeistungV, txtBlindLeistungV;
	
	// Schibenregler
	private JSlider slSpannung;
	private JSlider slWirkWiderstand1, slBlindWiderstand1;
	private JSlider slWirkWiderstand2, slBlindWiderstand2;
	private JSlider slWirkWiderstand3, slBlindWiderstand3;

	
	private JMenuBar menuBar;
	private JMenu menuDatei;
	private JMenuItem miSchliessen, miOeffnen;
	
	private File fcFile;
	
	
	private Hashtable<Integer, JLabel> sliderLabelTabelleVolt, sliderLabelTabelleRes;
	
	private Component owner;
	
	//==========================================================//
	
	
	
	
	public Drehstrom()		// Konstruktor
	{
		initializeComponents();
	}
	
	public void initializeComponents()
	{
//		this.setUndecorated(true);
//		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		this.setTitle("Dreiphasestrom");
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/elektrikLOGO.png")));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.addWindowListener(this);

		this.setSize(800, 800);
		this.setLocation(RESISTANCE_MAX, RESISTANCE_MAX/4);
		this.setResizable(false);
		this.setLayout(null);
		

		//===========MENU===========================================//
		
		menuBar = new JMenuBar();
		menuDatei = WinUtil.createMenu(menuBar, "Datei", null, 'D');
		miOeffnen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Öffnen", null, 'Ö', "Datei öffnen...");
//		this.setJMenuBar(menuBar);
		miSchliessen = WinUtil.createMenuItem(menuDatei, null, WinUtil.MenuItemType.ITEM_PLAIN, this, "Schließen", null, 'S', "Fenster schließen...");
		this.setJMenuBar(menuBar);
		
		//==========================================================//

		

		//===========SPANNUNG=======================================//
		
		// U1,U12 		
		JLabel lbSpannung = new JLabel("Spannung");
		lbSpannung.setFont(new Font("Serif", Font.ITALIC, 20));
		lbSpannung.setBounds(20, 50, 150, 20);
		this.add(lbSpannung);
		
		slSpannung = new JSlider(JSlider.HORIZONTAL, WERT_MIN, VOTAGE_MAX, WERT_MIN);
		slSpannung.setBounds(130, 35, 300, 50);		
				
		// Einstellungen für die Skala des Schiebereglers
					
		slSpannung.setMajorTickSpacing(50);		// Distanz zwischen des Hauptmarkierungen der Skala	
		slSpannung.setMinorTickSpacing(10);		// Distanz zwischen den Zwischenmarkierungen der Skala	
		slSpannung.setPaintTicks(true);			// Skala anzeigen	
		slSpannung.setPaintLabels(true);
				
		sliderLabelTabelleVolt = new Hashtable<>();
				
		sliderLabelTabelleVolt.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleVolt.put(VOTAGE_MAX, new JLabel(Integer.toString(VOTAGE_MAX)));
		slSpannung.setLabelTable(sliderLabelTabelleVolt);
		slSpannung.addChangeListener(this);
		slSpannung.addFocusListener(this);
		this.add(slSpannung);
				
		lbSpannung1 = new JLabel("U1");
		lbSpannung1.setBounds(20, 100, 30, 15);
		this.add(lbSpannung1);
		
		txtSpannung1 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung1.setBounds(60, 100, 40, 20);
		txtSpannung1.addKeyListener(this);
		txtSpannung1.addFocusListener(this);
		this.add(txtSpannung1);
				
		JLabel lbVolt1 = new JLabel("V");
		lbVolt1.setForeground(Color.BLUE);
		lbVolt1.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt1.setBounds(100, 100, 20, 15);
		this.add(lbVolt1);
		
		
		lbPhaseU1 = new JLabel("Phase(U1)");
		lbPhaseU1.setBounds(150, 100, 70, 15);
		this.add(lbPhaseU1);
		
		txtPhaseU1 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU1.setBounds(230, 100, 40, 20);
		txtPhaseU1.addKeyListener(this);
		txtPhaseU1.addFocusListener(this);
		this.add(txtPhaseU1);
				
		JLabel lbGrad1 = new JLabel("°");
		lbGrad1.setForeground(Color.BLUE);
		lbGrad1.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad1.setBounds(270, 100, 20, 15);
		this.add(lbGrad1);
		
		
		lbSpannung12 = new JLabel("U12");
		lbSpannung12.setBounds(310, 100, 30, 15);
		this.add(lbSpannung12);
		
		txtSpannung12 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung12.setBounds(350, 100, 40, 20);
		txtSpannung12.addKeyListener(this);
		txtSpannung12.addFocusListener(this);
		this.add(txtSpannung12);
				
		JLabel lbVolt12 = new JLabel("V");
		lbVolt12.setForeground(Color.BLUE);
		lbVolt12.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt12.setBounds(390, 100, 20, 15);
		this.add(lbVolt12);
		
		
		lbPhaseU12 = new JLabel("Phase(U12)");
		lbPhaseU12.setBounds(430, 100, 70, 15);
		this.add(lbPhaseU12);
		
		txtPhaseU12 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU12.setBounds(510, 100, 40, 20);
		txtPhaseU12.addKeyListener(this);
		txtPhaseU12.addFocusListener(this);
		this.add(txtPhaseU12);
				
		JLabel lbGrad12 = new JLabel("°");
		lbGrad12.setForeground(Color.BLUE);
		lbGrad12.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad12.setBounds(550, 100, 20, 15);
		this.add(lbGrad12);
		
		// U2,U23		
		lbSpannung2 = new JLabel("U2");
		lbSpannung2.setBounds(20, 125, 30, 15);
		this.add(lbSpannung2);
		
		txtSpannung2 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung2.setBounds(60, 125, 40, 20);
		txtSpannung2.addKeyListener(this);
		txtSpannung2.addFocusListener(this);
		this.add(txtSpannung2);
				
		JLabel lbVolt2 = new JLabel("V");
		lbVolt2.setForeground(Color.BLUE);
		lbVolt2.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt2.setBounds(100, 125, 20, 15);
		this.add(lbVolt2);
		
		
		lbPhaseU2 = new JLabel("Phase(U2)");
		lbPhaseU2.setBounds(150, 125, 70, 15);
		this.add(lbPhaseU2);
		
		txtPhaseU2 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU2.setBounds(230, 125, 40, 20);
		txtPhaseU2.addKeyListener(this);
		txtPhaseU2.addFocusListener(this);
		this.add(txtPhaseU2);
				
		JLabel lbGrad2 = new JLabel("°");
		lbGrad2.setForeground(Color.BLUE);
		lbGrad2.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad2.setBounds(270, 125, 20, 15);
		this.add(lbGrad2);
		
		
		lbSpannung23 = new JLabel("U23");
		lbSpannung23.setBounds(310, 125, 30, 15);
		this.add(lbSpannung23);
		
		txtSpannung23 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung23.setBounds(350, 125, 40, 20);
		txtSpannung23.addKeyListener(this);
		txtSpannung23.addFocusListener(this);
		this.add(txtSpannung23);
				
		JLabel lbVolt23 = new JLabel("V");
		lbVolt23.setForeground(Color.BLUE);
		lbVolt23.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt23.setBounds(390, 125, 20, 15);
		this.add(lbVolt23);
		
		
		lbPhaseU23 = new JLabel("Phase(U23)");
		lbPhaseU23.setBounds(430, 125, 70, 15);
		this.add(lbPhaseU23);
		
		txtPhaseU23 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU23.setBounds(510, 125, 40, 20);
		txtPhaseU23.addKeyListener(this);
		txtPhaseU23.addFocusListener(this);
		this.add(txtPhaseU23);
				
		JLabel lbGrad23 = new JLabel("°");
		lbGrad23.setForeground(Color.BLUE);
		lbGrad23.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad23.setBounds(550, 125, 20, 15);
		this.add(lbGrad23);
		
		// U3,U31		
		lbSpannung3 = new JLabel("U3");
		lbSpannung3.setBounds(20, 150, 30, 15);
		this.add(lbSpannung3);
		
		txtSpannung3 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung3.setBounds(60, 150, 40, 20);
		txtSpannung3.addKeyListener(this);
		txtSpannung3.addFocusListener(this);
		this.add(txtSpannung3);
				
		JLabel lbVolt3 = new JLabel("V");
		lbVolt3.setForeground(Color.BLUE);
		lbVolt3.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt3.setBounds(100, 150, 20, 15);
		this.add(lbVolt3);
		
		
		lbPhaseU3 = new JLabel("Phase(U3)");
		lbPhaseU3.setBounds(150, 150, 70, 15);
		this.add(lbPhaseU3);
		
		txtPhaseU3 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU3.setBounds(230, 150, 40, 20);
		txtPhaseU3.addKeyListener(this);
		txtPhaseU3.addFocusListener(this);
		this.add(txtPhaseU3);
				
		JLabel lbGrad3 = new JLabel("°");
		lbGrad3.setForeground(Color.BLUE);
		lbGrad3.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad3.setBounds(270, 150, 20, 15);
		this.add(lbGrad3);
		
		
		lbSpannung31 = new JLabel("U31");
		lbSpannung31.setBounds(310, 150, 30, 15);
		this.add(lbSpannung31);
		
		txtSpannung31 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung31.setBounds(350, 150, 40, 20);
		txtSpannung31.addKeyListener(this);
		txtSpannung31.addFocusListener(this);
		this.add(txtSpannung31);
				
		JLabel lbVolt31 = new JLabel("V");
		lbVolt31.setForeground(Color.BLUE);
		lbVolt31.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt31.setBounds(390, 150, 20, 15);
		this.add(lbVolt31);
		
		
		lbPhaseU31 = new JLabel("Phase(U31)");
		lbPhaseU31.setBounds(430, 150, 70, 15);
		this.add(lbPhaseU31);
		
		txtPhaseU31 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtPhaseU31.setBounds(510, 150, 40, 20);
		txtPhaseU31.addKeyListener(this);
		txtPhaseU31.addFocusListener(this);
		this.add(txtPhaseU31);
				
		JLabel lbGrad31 = new JLabel("°");
		lbGrad31.setForeground(Color.BLUE);
		lbGrad31.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad31.setBounds(550, 150, 20, 15);
		this.add(lbGrad31);
		
		// ==========================================================//

		// ===========WIDERSTÄNDE=================================//
	
		JLabel lbWiderstand = new JLabel("Widerstände");
		lbWiderstand.setFont(new Font("Serif", Font.ITALIC, 20));
		lbWiderstand.setBounds(20, 200, 150, 20);
		this.add(lbWiderstand);
		
		// R1			
		lbWirkWiderstand1 = new JLabel("R1");
		lbWirkWiderstand1.setBounds(20, 240, 30, 15);
		this.add(lbWirkWiderstand1);

		slWirkWiderstand1 = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, RESISTANCE_MAX);
		slWirkWiderstand1.setBounds(50, 230, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slWirkWiderstand1.setMajorTickSpacing(200); // Distanz zwischen des
													// Hauptmarkierungen der
													// Skala
		slWirkWiderstand1.setMinorTickSpacing(50); 	// Distanz zwischen den
													// Zwischenmarkierungen der
													// Skala
		slWirkWiderstand1.setPaintTicks(true); 		// Skala anzeigen
		slWirkWiderstand1.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slWirkWiderstand1.setLabelTable(sliderLabelTabelleRes);
		slWirkWiderstand1.addChangeListener(this);
		this.add(slWirkWiderstand1);

		txtWirkWiderstand1 = new JTextField(Integer.valueOf(RESISTANCE_MAX).toString());
		txtWirkWiderstand1.setBounds(230, 240, 40, 20);
		txtWirkWiderstand1.addKeyListener(this);
		txtWirkWiderstand1.addFocusListener(this);
		this.add(txtWirkWiderstand1);

		JLabel lbWirkOhm = new JLabel("Ohm");
		lbWirkOhm.setForeground(Color.BLUE);
		lbWirkOhm.setFont(new Font("Serif", Font.ITALIC, 15));
		lbWirkOhm.setBounds(270, 240, 40, 15);
		this.add(lbWirkOhm);

		// jX1
		lbBlindWiderstand1 = new JLabel("jX1");
		lbBlindWiderstand1.setBounds(310, 240, 30, 15);
		this.add(lbBlindWiderstand1);

		slBlindWiderstand1 = new JSlider(JSlider.HORIZONTAL, WERT_MIN - 500, RESISTANCE_MAX / 2, WERT_MIN);
		slBlindWiderstand1.setBounds(340, 230, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slBlindWiderstand1.setMajorTickSpacing(200); 	// Distanz zwischen des
														// Hauptmarkierungen der
														// Skala
		slBlindWiderstand1.setMinorTickSpacing(50); 	// Distanz zwischen den
														// Zwischenmarkierungen der
														// Skala
		slBlindWiderstand1.setPaintTicks(true); 		// Skala anzeigen
		slBlindWiderstand1.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN - 500, new JLabel(Integer.toString(WERT_MIN - 500)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX / 2, new JLabel(Integer.toString(RESISTANCE_MAX / 2)));
		slBlindWiderstand1.setLabelTable(sliderLabelTabelleRes);
		slBlindWiderstand1.addChangeListener(this);
		this.add(slBlindWiderstand1);

		txtBlindWiderstand1 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtBlindWiderstand1.setBounds(510, 240, 40, 20);
		txtBlindWiderstand1.addKeyListener(this);
		txtBlindWiderstand1.addFocusListener(this);
		this.add(txtBlindWiderstand1);

		JLabel lbBlindOhm1 = new JLabel("Ohm");
		lbBlindOhm1.setForeground(Color.BLUE);
		lbBlindOhm1.setFont(new Font("Serif", Font.ITALIC, 15));
		lbBlindOhm1.setBounds(550, 240, 40, 15);
		this.add(lbBlindOhm1);
		
		// R2
		lbWirkWiderstand2 = new JLabel("R2");
		lbWirkWiderstand2.setBounds(20, 290, 30, 15);
		this.add(lbWirkWiderstand2);

		slWirkWiderstand2 = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, RESISTANCE_MAX);
		slWirkWiderstand2.setBounds(50, 280, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slWirkWiderstand2.setMajorTickSpacing(200); // Distanz zwischen des
													// Hauptmarkierungen der
													// Skala
		slWirkWiderstand2.setMinorTickSpacing(50); 	// Distanz zwischen den
													// Zwischenmarkierungen der
													// Skala
		slWirkWiderstand2.setPaintTicks(true); 		// Skala anzeigen
		slWirkWiderstand2.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slWirkWiderstand2.setLabelTable(sliderLabelTabelleRes);
		slWirkWiderstand2.addChangeListener(this);
		this.add(slWirkWiderstand2);

		txtWirkWiderstand2 = new JTextField(Integer.valueOf(RESISTANCE_MAX).toString());
		txtWirkWiderstand2.setBounds(230, 290, 40, 20);
		txtWirkWiderstand2.addKeyListener(this);
		txtWirkWiderstand2.addFocusListener(this);
		this.add(txtWirkWiderstand2);

		JLabel lbWirkOhm2 = new JLabel("Ohm");
		lbWirkOhm2.setForeground(Color.BLUE);
		lbWirkOhm2.setFont(new Font("Serif", Font.ITALIC, 15));
		lbWirkOhm2.setBounds(270, 290, 40, 15);
		this.add(lbWirkOhm2);

		// jX2
		lbBlindWiderstand2 = new JLabel("jX2");
		lbBlindWiderstand2.setBounds(310, 290, 30, 15);
		this.add(lbBlindWiderstand2);

		slBlindWiderstand2 = new JSlider(JSlider.HORIZONTAL, WERT_MIN - 500, RESISTANCE_MAX / 2, WERT_MIN);
		slBlindWiderstand2.setBounds(340, 280, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slBlindWiderstand2.setMajorTickSpacing(200); 	// Distanz zwischen des
														// Hauptmarkierungen der
														// Skala
		slBlindWiderstand2.setMinorTickSpacing(50); 	// Distanz zwischen den
														// Zwischenmarkierungen der
														// Skala
		slBlindWiderstand2.setPaintTicks(true); 		// Skala anzeigen
		slBlindWiderstand2.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN - 500, new JLabel(Integer.toString(WERT_MIN - 500)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX / 2, new JLabel(Integer.toString(RESISTANCE_MAX / 2)));
		slBlindWiderstand2.setLabelTable(sliderLabelTabelleRes);
		slBlindWiderstand2.addChangeListener(this);
		this.add(slBlindWiderstand2);

		txtBlindWiderstand2 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtBlindWiderstand2.setBounds(510, 290, 40, 20);
		txtBlindWiderstand2.addKeyListener(this);
		txtBlindWiderstand2.addFocusListener(this);
		this.add(txtBlindWiderstand2);

		JLabel lbBlindOhm2 = new JLabel("Ohm");
		lbBlindOhm2.setForeground(Color.BLUE);
		lbBlindOhm2.setFont(new Font("Serif", Font.ITALIC, 15));
		lbBlindOhm2.setBounds(550, 290, 40, 15);
		this.add(lbBlindOhm2);
		
		// R3
		lbWirkWiderstand3 = new JLabel("R3");
		lbWirkWiderstand3.setBounds(20, 340, 30, 15);
		this.add(lbWirkWiderstand3);

		slWirkWiderstand3 = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, RESISTANCE_MAX);
		slWirkWiderstand3.setBounds(50, 330, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slWirkWiderstand3.setMajorTickSpacing(200); // Distanz zwischen des
													// Hauptmarkierungen der
													// Skala
		slWirkWiderstand3.setMinorTickSpacing(50); 	// Distanz zwischen den
													// Zwischenmarkierungen der
													// Skala
		slWirkWiderstand3.setPaintTicks(true); 		// Skala anzeigen
		slWirkWiderstand3.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slWirkWiderstand3.setLabelTable(sliderLabelTabelleRes);
		slWirkWiderstand3.addChangeListener(this);
		this.add(slWirkWiderstand3);

		txtWirkWiderstand3 = new JTextField(Integer.valueOf(RESISTANCE_MAX).toString());
		txtWirkWiderstand3.setBounds(230, 340, 40, 20);
		txtWirkWiderstand3.addKeyListener(this);
		txtWirkWiderstand3.addFocusListener(this);
		this.add(txtWirkWiderstand3);

		JLabel lbWirkOhm3 = new JLabel("Ohm");
		lbWirkOhm3.setForeground(Color.BLUE);
		lbWirkOhm3.setFont(new Font("Serif", Font.ITALIC, 15));
		lbWirkOhm3.setBounds(270, 340, 40, 15);
		this.add(lbWirkOhm3);

		// jX3
		lbBlindWiderstand3 = new JLabel("jX3");
		lbBlindWiderstand3.setBounds(310, 340, 30, 15);
		this.add(lbBlindWiderstand3);

		slBlindWiderstand3 = new JSlider(JSlider.HORIZONTAL, WERT_MIN - 500, RESISTANCE_MAX / 2, WERT_MIN);
		slBlindWiderstand3.setBounds(340, 330, 150, 50);

		// Einstellungen für die Skala des Schiebereglers

		slBlindWiderstand3.setMajorTickSpacing(200); 	// Distanz zwischen des
														// Hauptmarkierungen der
														// Skala
		slBlindWiderstand3.setMinorTickSpacing(50); 	// Distanz zwischen den
														// Zwischenmarkierungen der
														// Skala
		slBlindWiderstand3.setPaintTicks(true); 		// Skala anzeigen
		slBlindWiderstand3.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN - 500, new JLabel(Integer.toString(WERT_MIN - 500)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX / 2, new JLabel(Integer.toString(RESISTANCE_MAX / 2)));
		slBlindWiderstand3.setLabelTable(sliderLabelTabelleRes);
		slBlindWiderstand3.addChangeListener(this);
		this.add(slBlindWiderstand3);

		txtBlindWiderstand3 = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtBlindWiderstand3.setBounds(510, 340, 40, 20);
		txtBlindWiderstand3.addKeyListener(this);
		txtBlindWiderstand3.addFocusListener(this);
		this.add(txtBlindWiderstand3);

		JLabel lbBlindOhm3 = new JLabel("Ohm");
		lbBlindOhm3.setForeground(Color.BLUE);
		lbBlindOhm3.setFont(new Font("Serif", Font.ITALIC, 15));
		lbBlindOhm3.setBounds(550, 340, 40, 15);
		this.add(lbBlindOhm3);
		

		// ==========================================================//
//
//		Grundformeln formeln = new Grundformeln(slSpannung.getValue(), slWirkWiderstand.getValue(), slBlindWiderstand.getValue());
//
//		// ===========STROMSTÄRKE====================================//
//
//		lbStrom = new JLabel("Stromstärke");
//		lbStrom.setBounds(20, 200, 100, 15);
//		this.add(lbStrom);
//
//		txtStrom = new JTextField(Double.valueOf(formeln.getCurrency()).toString());
//		txtStrom.setBounds(130, 200, 40, 20);
//		txtStrom.setFocusable(false);
//		txtStrom.addKeyListener(this);
//		txtStrom.addFocusListener(this);
//		this.add(txtStrom);
//
//		JLabel lbAmper = new JLabel("A");
//		lbAmper.setBackground(Color.BLUE);
//		lbAmper.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbAmper.setBounds(180, 200, 20, 15);
//		this.add(lbAmper);
//
//		lbStromPhase = new JLabel("Phasenverschiebung");
//		lbStromPhase.setBounds(300, 200, 150, 15);
//		this.add(lbStromPhase);
//
//		txtStromPhase = new JTextField(formeln.getCurrPhase());
//		txtStromPhase.setBounds(450, 200, 40, 20);
//		txtStromPhase.setFocusable(false);
//		txtStromPhase.addKeyListener(this);
//		txtStromPhase.addFocusListener(this);
//		this.add(txtStromPhase);
//
//		JLabel lbGrad = new JLabel("°");
//		lbGrad.setBackground(Color.BLUE);
//		lbGrad.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbGrad.setBounds(500, 200, 20, 15);
//		this.add(lbGrad);
//
//		// ==========================================================//
//
//		// ===========LEISTUNG=======================================//
//
//		// QUELLE
//
//		JLabel lbQ = new JLabel("QUELLE");
//		lbQ.setFont(new Font("Serif", Font.ITALIC, 21));
//		lbQ.setBounds(20, 250, 150, 20);
//		this.add(lbQ);
//
//		// Wirkleistung
//
//		lbWirkLeistungQ = new JLabel("Wirkleistung");
//		lbWirkLeistungQ.setBounds(20, 300, 100, 15);
//		this.add(lbWirkLeistungQ);
//
//		txtWirkLeistungQ = new JTextField(Double.valueOf(formeln.getPower("WirkLeistung")).toString());
//		txtWirkLeistungQ.setBounds(130, 300, 40, 20);
//		txtWirkLeistungQ.setFocusable(false);
//		this.add(txtWirkLeistungQ);
//
//		JLabel lbWatt1Q = new JLabel("W");
//		lbWatt1Q.setBackground(Color.BLUE);
//		lbWatt1Q.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWatt1Q.setBounds(180, 300, 20, 15);
//		this.add(lbWatt1Q);
//
//		// Blindleistung
//
//		lbBlindLeistungQ = new JLabel("Blindleistung");
//		lbBlindLeistungQ.setBounds(300, 300, 100, 15);
//		this.add(lbBlindLeistungQ);
//
//		txtBlindLeistungQ = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString()
//						 */);
//		txtBlindLeistungQ.setBounds(450, 300, 40, 20);
//		txtBlindLeistungQ.setFocusable(false);
//		this.add(txtBlindLeistungQ);
//
//		JLabel lbWatt2Q = new JLabel("var");
//		lbWatt2Q.setBackground(Color.BLUE);
//		lbWatt2Q.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWatt2Q.setBounds(500, 300, 25, 15);
//		this.add(lbWatt2Q);
//
//		// Scheinleistung
//
//		lbLeistungQ = new JLabel("SCHEINLEISTUNG");
//		lbLeistungQ.setBounds(170, 350, 100, 15);
//		this.add(lbLeistungQ);
//
//		txtLeistungQ = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString() */);
//		txtLeistungQ.setBounds(300, 350, 40, 20);
//		txtLeistungQ.setFocusable(false);
//		this.add(txtLeistungQ);
//
//		JLabel lbWattQ = new JLabel("VA");
//		lbWattQ.setBackground(Color.BLUE);
//		lbWattQ.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWattQ.setBounds(350, 350, 25, 15);
//		this.add(lbWattQ);
//
//		// VERBRAUCHER
//
//		JLabel lbV = new JLabel("VERBRAUCHER");
//		lbV.setFont(new Font("Serif", Font.ITALIC, 21));
//		lbV.setBounds(20, 400, 150, 20);
//		this.add(lbV);
//
//		// Wirkleistung
//
//		lbWirkLeistungV = new JLabel("Wirkleistung");
//		lbWirkLeistungV.setBounds(20, 450, 100, 15);
//		this.add(lbWirkLeistungV);
//
//		txtWirkLeistungV = new JTextField(Double.valueOf(formeln.getPower("WirkLeistung")).toString());
//		txtWirkLeistungV.setBounds(130, 450, 40, 20);
//		txtWirkLeistungV.setFocusable(false);
//		this.add(txtWirkLeistungV);
//
//		JLabel lbWatt1V = new JLabel("W");
//		lbWatt1V.setBackground(Color.BLUE);
//		lbWatt1V.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWatt1V.setBounds(180, 450, 20, 15);
//		this.add(lbWatt1V);
//
//		// Blindleistung
//
//		lbBlindLeistungV = new JLabel("Blindleistung");
//		lbBlindLeistungV.setBounds(300, 450, 100, 15);
//		this.add(lbBlindLeistungV);
//
//		txtBlindLeistungV = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString()*/);
//		txtBlindLeistungV.setBounds(450, 450, 40, 20);
//		txtBlindLeistungV.setFocusable(false);
//		this.add(txtBlindLeistungV);
//
//		JLabel lbWatt2V = new JLabel("var");
//		lbWatt2V.setBackground(Color.BLUE);
//		lbWatt2V.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWatt2V.setBounds(500, 450, 25, 15);
//		this.add(lbWatt2V);
//
//		// Scheinleistung
//
//		lbLeistungV = new JLabel("SCHEINLEISTUNG");
//		lbLeistungV.setBounds(170, 500, 100, 15);
//		this.add(lbLeistungV);
//
//		txtLeistungV = new JTextField("0.0"/*Double.valueOf(formeln.getPower("Blindleistung")).toString()*/);
//		txtLeistungV.setBounds(300, 500, 40, 20);
//		txtLeistungV.setFocusable(false);
//		this.add(txtLeistungV);
//
//		JLabel lbWattV = new JLabel("VA");
//		lbWattV.setBackground(Color.BLUE);
//		lbWattV.setFont(new Font("Serif", Font.ITALIC, 19));
//		lbWattV.setBounds(350, 500, 25, 15);
//		this.add(lbWattV);

		// ==========================================================//
	}
	

	private void initDialog()
	{
		this.setLocationRelativeTo(owner);
		
//		this.setModalityType(DEFAULT_MODALITY_TYPE);
		// oder:
		this.setModal(true);
		
	}
	
	public void showDialog()
	{
		initDialog();
		this.setVisible(true);
	}
	
	public void showDialog(Component owner)
	{
		this.owner = owner;
		showDialog();
	}


	
	public void scrollSlider()
	{
//		int valSpannung = slSpannung.getValue();
//		int valWiderstandRe = slWirkWiderstand.getValue();
//		int valWiderstandIm = slBlindWiderstand.getValue();
//
//		txtSpannung.setText(String.valueOf(valSpannung));
//		txtWirkWiderstand.setText(String.valueOf(valWiderstandRe));
//		txtBlindWiderstand.setText(String.valueOf(valWiderstandIm));
//
//		txtStrom.setText(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm).getCurrency());
//		txtStromPhase.setText(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm).getCurrPhase());
//
//		txtWirkLeistungQ.setText(
//				(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("WirkLeistung")));
//		txtBlindLeistungQ.setText(
//				(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("BlindLeistung")));
//		txtLeistungQ.setText(
//				(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("ScheinLeistung")));
//		txtWirkLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher")
//				.getPower("WirkLeistung")));
//		txtBlindLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher")
//				.getPower("BlindLeistung")));
//		txtLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher")
//				.getPower("ScheinLeistung")));
		
	}
	
	public void showGrundlagen()
	{
		this.setVisible(true);
	}
	
	////////////////////////////////////////////////////////////////////

	/**
	 * Durch Eingabe eines Wertes ins Textfeld, ändert sich der Wert des
	 * Shiebereglers
	 * @param tf
	 */
	private void setSliderValue(JTextField tf)
	{
		
//		int value = 0;
//		
//		// Schieberegler wird deklariert.
//		JSlider slider = null;
//		
//		// Umschalten auf den Schieberegler für den Wert Spannung
//		if (tf == txtSpannung)
//			slider = slSpannung;
//		// Umschalten auf den Schieberegler für den Wert Wirkiderstand
//		else if (tf == txtWirkWiderstand)
//			slider = slWirkWiderstand;
//		// Umschalten auf den Schieberegler für den Wert Blindwiderstand
//		else if (tf == txtBlindWiderstand)
//			slider = slBlindWiderstand;
//		
//		
//		
//		value = Integer.parseInt("0" + tf.getText());
//	
//		slider.setValue(value);
		
	}
	
	/**
	 * Umwandlung einer Zeichenkette zum ganzzahligen Wert
	 * @param tf
	 * @param e
	 * @return
	 */
	private int convertTextFieldValue(JTextField tf, KeyEvent e)
	{		
		
		// Position ermitteln, an der das Zeichen im TextFeld eingefügt wurde
		int charPos = tf.getCaretPosition();
		String tmpText = tf.getText().substring(0, charPos) + e.getKeyChar() + tf.getText().substring(charPos);
		
		return Integer.parseInt(tmpText);
	
	}
	
	private void openFileDialog()
	{
//		JFileChooser fc = new JFileChooser();
//		fc.setCurrentDirectory(fcFile);
//		fc.setFileFilter(new FileNameExtensionFilter("Textdokument (*.txt)", "txt"));
//		fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV-Datei (*.csv)", "csv"));
//		
//		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
//			return;
//		
//		fcFile = fc.getSelectedFile();
//		
//		//readFile(fcFile.toString());
//		
//		
//		// Auf der Methode 'readFile()' über einen nebenläufigen Prozess
//		Thread t  = new Thread(new ReadFileIntoDatabase(fcFile.toString()));
//		t.start();
	}
	
	////////////////////////////////////////////////////////////////////

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

	@Override
	public void stateChanged(ChangeEvent e) 	// Ereignis für die Schieberegler (ChangeListener)
	{
		scrollSlider();
		
	}
	
	// Erreignisse des KeyListener

	@Override
	public void keyTyped(KeyEvent e) 
	{

//		JTextField tf = null;
//		
//		if (!(e.getSource() instanceof JTextField))
//			return;
//		
//		// Steuertasten ignorieren
//		if (Character.isISOControl(e.getKeyChar()))
//			return;
//		
//		tf = (JTextField)e.getSource();
//		
//		
//		if (!Character.isDigit(e.getKeyChar()))
//		{
//			Toolkit.getDefaultToolkit().beep();
//			e.consume();
//			return;
//		}
//		
//		
//		// Zuerst die evtl. markierten Zeichen löschen,
//		// bevor die Überprüfung der Textlänge vorgenommen wird. 
//		tf.replaceSelection(null);
//		
//		if ((tf.getText().length() >= 3) && (tf.equals(txtSpannung) || (tf.getText().length() >= 4) && ((tf.equals(txtWirkWiderstand) || (tf.equals(txtBlindWiderstand))))))
//		{
//			Toolkit.getDefaultToolkit().beep();
//			e.consume();
//			return;
//		}
//		
//		if ((convertTextFieldValue(tf, e) > VOTAGE_MAX) && (tf.equals(txtSpannung) || (convertTextFieldValue(tf, e) > RESISTANCE_MAX) && (tf.equals(txtWirkWiderstand))))
//		{
//			Toolkit.getDefaultToolkit().beep();
//			e.consume();
		}

//	}

	@Override
	public void keyPressed(KeyEvent e) 
	{

		
//		if (e.getKeyCode() == KeyEvent.VK_ENTER)
//			KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();		
			
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) 
	{

//		if (e.getSource() instanceof JTextField)
//			((JTextField)e.getSource()).selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent e) 
	{

//		if (e.getSource() instanceof JTextField)
//			setSliderValue((JTextField)e.getSource());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
//		if (e.getSource() == miSchliessen)
//			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
//		else if (e.getSource() == miOeffnen)
			openFileDialog();
		
	}


}
