package Elektrotechnik;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
//import java.awt.Image;
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
//import java.io.File;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.*;
//import javax.swing.filechooser.FileNameExtensionFilter;

import myUtil.WinUtil;

public class Grundlagen extends JDialog implements WindowListener, ChangeListener, KeyListener, FocusListener, ActionListener
{
	//===========Initialisierung von Konstanten=================//
	private static final int WERT_MIN = 0;
	private static final int VOTAGE_MAX = 400;
	private static final int RESISTANCE_MAX = 1000;
	private static final int CURRENCY_MAX = 20;
	
	//==========================================================//
	
	//===========Deklarationen von Instanzen====================//
	private JLabel lbSpannung, lbWiderstand, lbStrom, lbLeistung;
//	private JLabel lbStromDichte, lbFlaeche;
//	private JLabel lbSpezWiderstnd, lbQuerschtt, lbLaenge;
//	private JLabel lbArbeit, lbZeit, lbKosten_pro_kW, lbKosten;
	
	private JTextField txtSpannung, txtWiderstand, txtStrom, txtLeistung;
//	private JTextField txtStromDichte, txtFlaeche;
//	private JTextField txtSpezWiderstnd, txtQuerschtt, txtLaenge;
//	private JTextField txtArbeit, txtZeit, txtKosten_pro_kW, txtKosten;
	
	private JSlider slSpannung, slWiderstand, slStrom, slLeistung;
//	private JSlider slFlaeche;
//	private JSlider slSpezWiderstnd, slQuerschtt, slLaenge;
//	private JSlider slKosten_pro_kW;
	
	private JMenuBar menuBar;
	private JMenu menuDatei;
	private JMenuItem miSchliessen, miOeffnen;
	
//	private File fcFile;
	
//	private JButton btInfo;
	
	
	private Hashtable<Integer, JLabel> sliderLabelTabelleVolt, sliderLabelTabelleRes, sliderLabelTabelleCurr, sliderLabelTabellePow;
	
	private Component owner;
	
	//==========================================================//
	
	
	
	
	public Grundlagen()		// Konstruktor
	{
		initializeComponents();
	}
	
	public void initializeComponents()
	{
//		this.setUndecorated(true);
//		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		this.setTitle("Grundbegriffe der Elektrotechnik");
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/elektrikLOGO.png")));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.addWindowListener(this);

		this.setSize(600, 400);
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
		
		lbSpannung = new JLabel("Spannung");
		lbSpannung.setBounds(20, 50, 100, 15);
		this.add(lbSpannung);

		slSpannung = new JSlider(JSlider.HORIZONTAL, WERT_MIN, VOTAGE_MAX, WERT_MIN);
		slSpannung.setBounds(120, 35, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slSpannung.setMajorTickSpacing(50); // Distanz zwischen des
											// Hauptmarkierungen der Skala
		slSpannung.setMinorTickSpacing(10); // Distanz zwischen den
											// Zwischenmarkierungen der Skala
		slSpannung.setPaintTicks(true); 	// Skala anzeigen
		slSpannung.setPaintLabels(true);

		sliderLabelTabelleVolt = new Hashtable<>();

		sliderLabelTabelleVolt.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleVolt.put(VOTAGE_MAX, new JLabel(Integer.toString(VOTAGE_MAX)));
		slSpannung.setLabelTable(sliderLabelTabelleVolt);
		slSpannung.addChangeListener(this);
		slSpannung.addFocusListener(this);
		this.add(slSpannung);

		txtSpannung = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtSpannung.setBounds(450, 50, 40, 20);
		txtSpannung.addKeyListener(this);
		txtSpannung.addFocusListener(this);
		this.add(txtSpannung);

		JLabel lbVolt = new JLabel("V");
		lbVolt.setForeground(Color.BLUE);
		lbVolt.setFont(new Font("Serif", Font.ITALIC, 19));
		lbVolt.setBounds(500, 50, 20, 15);
		this.add(lbVolt);
		// ==========================================================//

		// ===========WIDERSTAND=====================================//

		lbWiderstand = new JLabel("Widerstand");
		lbWiderstand.setBounds(20, 100, 100, 15);
		this.add(lbWiderstand);

		slWiderstand = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, RESISTANCE_MAX);
		slWiderstand.setBounds(120, 85, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slWiderstand.setMajorTickSpacing(100); 	// Distanz zwischen des
												// Hauptmarkierungen der Skala
		slWiderstand.setMinorTickSpacing(20); 	// Distanz zwischen den
												// Zwischenmarkierungen der
												// Skala
		slWiderstand.setPaintTicks(true); 	// Skala anzeigen
		slWiderstand.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slWiderstand.setLabelTable(sliderLabelTabelleRes);
		slWiderstand.addChangeListener(this);
		this.add(slWiderstand);

		txtWiderstand = new JTextField(Integer.valueOf(RESISTANCE_MAX).toString());
		txtWiderstand.setBounds(450, 100, 40, 20);
		txtWiderstand.addKeyListener(this);
		txtWiderstand.addFocusListener(this);
		this.add(txtWiderstand);

		JLabel lbOhm = new JLabel("Ohm");
		lbOhm.setForeground(Color.BLUE);
		lbOhm.setFont(new Font("Serif", Font.ITALIC, 19));
		lbOhm.setBounds(500, 100, 40, 15);
		this.add(lbOhm);

		// ==========================================================//

		Grundformeln formeln = new Grundformeln(slSpannung.getValue(), slWiderstand.getValue(), 0);

		// ===========STROMSTÄRKE====================================//

		lbStrom = new JLabel("Strom");
		lbStrom.setBounds(20, 150, 100, 15);
		this.add(lbStrom);

		slStrom = new JSlider(JSlider.HORIZONTAL, WERT_MIN, CURRENCY_MAX, WERT_MIN);
		slStrom.setBounds(120, 135, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slStrom.setMajorTickSpacing(5); // Distanz zwischen des
										// Hauptmarkierungen der Skala
		slStrom.setMinorTickSpacing(1); // Distanz zwischen den
										// Zwischenmarkierungen der Skala
		slStrom.setPaintTicks(true); 	// Skala anzeigen
		slStrom.setPaintLabels(true);

		sliderLabelTabelleCurr = new Hashtable<>();

		sliderLabelTabelleCurr.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleCurr.put(CURRENCY_MAX, new JLabel(Integer.toString(CURRENCY_MAX)));
		slStrom.setLabelTable(sliderLabelTabelleCurr);
		slStrom.addChangeListener(this);
		this.add(slStrom);

		txtStrom = new JTextField(Double.valueOf(formeln.getCurrency()).toString());
		txtStrom.setBounds(450, 150, 40, 20);
		txtStrom.setFocusable(false);
		txtWiderstand.addKeyListener(this);
		txtWiderstand.addFocusListener(this);
		this.add(txtStrom);

		JLabel lbAmper = new JLabel("A");
		lbAmper.setForeground(Color.BLUE);
		lbAmper.setFont(new Font("Serif", Font.ITALIC, 19));
		lbAmper.setBounds(500, 150, 20, 15);
		this.add(lbAmper);

		// ==========================================================//

		// ===========LEISTUNG=======================================//

		lbLeistung = new JLabel("Leistung");
		lbLeistung.setBounds(20, 200, 100, 15);
		this.add(lbLeistung);

		slLeistung = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, WERT_MIN);
		slLeistung.setBounds(120, 195, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slLeistung.setMajorTickSpacing(100); 	// Distanz zwischen des
												// Hauptmarkierungen der Skala
		slLeistung.setMinorTickSpacing(50); 	// Distanz zwischen den
												// Zwischenmarkierungen der Skala
		slLeistung.setPaintTicks(true); 		// Skala anzeigen
		slLeistung.setPaintLabels(true);

		sliderLabelTabellePow = new Hashtable<>();

		sliderLabelTabellePow.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabellePow.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slLeistung.setLabelTable(sliderLabelTabellePow);
		slLeistung.addChangeListener(this);
		this.add(slLeistung);

		txtLeistung = new JTextField(Double.valueOf(formeln.getPower("WirkLeistung")).toString());
		txtLeistung.setBounds(450, 200, 40, 20);
		txtLeistung.setFocusable(false);
		this.add(txtLeistung);

		JLabel lbWatt = new JLabel("W");
		lbWatt.setForeground(Color.BLUE);
		lbWatt.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWatt.setBounds(500, 200, 20, 15);
		this.add(lbWatt);

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
		int valSpannung = slSpannung.getValue();
		int valWiderstand =slWiderstand.getValue();
		
		txtSpannung.setText(String.valueOf(valSpannung));
		txtWiderstand.setText(String.valueOf(valWiderstand));
		txtStrom.setText(new Grundformeln(valSpannung, valWiderstand, 0).getCurrency());
		txtLeistung.setText((new Grundformeln(valSpannung, valWiderstand, 0, "Quelle").getPower("WirkLeistung")));
		
		slStrom.setValue(valSpannung / valWiderstand);
		slLeistung.setValue((int)Math.pow(valSpannung, 2) / valWiderstand);
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
		
		int value = 0;
		
		// Schieberegler wird deklariert.
		JSlider slider = null;
		
		// Umschalten auf den Schieberegler für den Wert Spannung
		if (tf == txtSpannung)
			slider = slSpannung;
		// Umschalten auf den Schieberegler für den Wert Widerstand
		else if (tf == txtWiderstand)
			slider = slWiderstand;
		// Umschalten auf den Schieberegler für den Wert Stromstärke
//		else if (tf == txtStrom)
//			slider = slStrom;
//		// Umschalten auf den Schieberegler für den Wert Leistung
//		else if (tf == txtLeistung)
//			slider = slLeistung;
		
		
		
		value = Integer.parseInt("0" + tf.getText());
	
		slider.setValue(value);
		
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

		JTextField tf = null;
		
		if (!(e.getSource() instanceof JTextField))
			return;
		
		// Steuertasten ignorieren
		if (Character.isISOControl(e.getKeyChar()))
			return;
		
		tf = (JTextField)e.getSource();
		
		
		if (!Character.isDigit(e.getKeyChar()))
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
			return;
		}
		
		
		// Zuerst die evtl. markierten Zeichen löschen,
		// bevor die Überprüfung der Textlänge vorgenommen wird. 
		tf.replaceSelection(null);
		
		if ((tf.getText().length() >= 3) && (tf.equals(txtSpannung) || (tf.getText().length() >= 4) && (tf.equals(txtWiderstand))))
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
			return;
		}
		
		if ((convertTextFieldValue(tf, e) > VOTAGE_MAX) && (tf.equals(txtSpannung) || (convertTextFieldValue(tf, e) > RESISTANCE_MAX) && (tf.equals(txtWiderstand))))
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{

		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();		
			
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) 
	{

		if (e.getSource() instanceof JTextField)
			((JTextField)e.getSource()).selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent e) 
	{

		if (e.getSource() instanceof JTextField)
			setSliderValue((JTextField)e.getSource());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == miSchliessen)
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		else if (e.getSource() == miOeffnen)
			openFileDialog();
		
	}


}
