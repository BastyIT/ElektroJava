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

//import javax.swing.JButton;
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

public class Wechselstrom  extends JDialog implements WindowListener, ChangeListener, KeyListener, FocusListener, ActionListener
{
	//===========Initialisierung von Konstanten=================//
	private static final int WERT_MIN = 0;
	private static final int VOTAGE_MAX = 400;
	private static final int RESISTANCE_MAX = 1000;
	//==========================================================//
	
	//===========Deklarationen von Instanzen====================//
	private JLabel lbSpannung;
	private JLabel lbWirkWiderstand, lbBlindWiderstand;
	private JLabel lbStrom, lbStromPhase;
	private JLabel lbLeistungQ, lbWirkLeistungQ, lbBlindLeistungQ;
	private JLabel lbLeistungV, lbWirkLeistungV, lbBlindLeistungV;
	
	private JTextField txtSpannung;
	private JTextField txtWirkWiderstand, txtBlindWiderstand, txtStrom, txtStromPhase;
	private JTextField txtLeistungQ, txtWirkLeistungQ, txtBlindLeistungQ;
	private JTextField txtLeistungV, txtWirkLeistungV, txtBlindLeistungV;
	
	private JSlider slSpannung, slWirkWiderstand, slBlindWiderstand;

	
	private JMenuBar menuBar;
	private JMenu menuDatei;
	private JMenuItem miSchliessen, miOeffnen;
	
//	private File fcFile;
	
	
	
	private Hashtable<Integer, JLabel> sliderLabelTabelleVolt, sliderLabelTabelleRes;
	
	private Component owner;
	
	//==========================================================//
	
	
	
	
	public Wechselstrom()		// Konstruktor
	{
		initializeComponents();
	}
	
	public void initializeComponents()
	{
//		this.setUndecorated(true);
//		this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		this.setTitle("Wechsestrom");
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/elektrikLOGO.png")));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.addWindowListener(this);

		this.setSize(600, 800);
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
		lbSpannung.setBounds(20, 50, 110, 15);
		this.add(lbSpannung);

		slSpannung = new JSlider(JSlider.HORIZONTAL, WERT_MIN, VOTAGE_MAX, WERT_MIN);
		slSpannung.setBounds(130, 35, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slSpannung.setMajorTickSpacing(50); // Distanz zwischen des
											// Hauptmarkierungen der Skala
		slSpannung.setMinorTickSpacing(10); // Distanz zwischen den
											// Zwischenmarkierungen der Skala
		slSpannung.setPaintTicks(true); // Skala anzeigen
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

		// ===========WIRKWIDERSTAND=================================//

		lbWirkWiderstand = new JLabel("Wirkwiderstand");
		lbWirkWiderstand.setBounds(20, 100, 110, 15);
		this.add(lbWirkWiderstand);

		slWirkWiderstand = new JSlider(JSlider.HORIZONTAL, WERT_MIN, RESISTANCE_MAX, RESISTANCE_MAX);
		slWirkWiderstand.setBounds(130, 85, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slWirkWiderstand.setMajorTickSpacing(100); // Distanz zwischen des
													// Hauptmarkierungen der
													// Skala
		slWirkWiderstand.setMinorTickSpacing(20); // Distanz zwischen den
													// Zwischenmarkierungen der
													// Skala
		slWirkWiderstand.setPaintTicks(true); // Skala anzeigen
		slWirkWiderstand.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN, new JLabel(Integer.toString(WERT_MIN)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX, new JLabel(Integer.toString(RESISTANCE_MAX)));
		slWirkWiderstand.setLabelTable(sliderLabelTabelleRes);
		slWirkWiderstand.addChangeListener(this);
		this.add(slWirkWiderstand);

		txtWirkWiderstand = new JTextField(Integer.valueOf(RESISTANCE_MAX).toString());
		txtWirkWiderstand.setBounds(450, 100, 40, 20);
		txtWirkWiderstand.addKeyListener(this);
		txtWirkWiderstand.addFocusListener(this);
		this.add(txtWirkWiderstand);

		JLabel lbWirkOhm = new JLabel("Ohm");
		lbWirkOhm.setForeground(Color.BLUE);
		lbWirkOhm.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWirkOhm.setBounds(500, 100, 40, 15);
		this.add(lbWirkOhm);

		// ==========================================================//

		// ===========BLINDWIDERSTAND================================//

		lbBlindWiderstand = new JLabel("Blindwiderstand");
		lbBlindWiderstand.setBounds(20, 150, 120, 15);
		this.add(lbBlindWiderstand);

		slBlindWiderstand = new JSlider(JSlider.HORIZONTAL, WERT_MIN - 500, RESISTANCE_MAX / 2, WERT_MIN);
		slBlindWiderstand.setBounds(130, 135, 300, 50);

		// Einstellungen für die Skala des Schiebereglers

		slBlindWiderstand.setMajorTickSpacing(100); // Distanz zwischen des
													// Hauptmarkierungen der
													// Skala
		slBlindWiderstand.setMinorTickSpacing(20); // Distanz zwischen den
													// Zwischenmarkierungen der
													// Skala
		slBlindWiderstand.setPaintTicks(true); // Skala anzeigen
		slBlindWiderstand.setPaintLabels(true);
		sliderLabelTabelleRes = new Hashtable<>();

		sliderLabelTabelleRes.put(WERT_MIN - 500, new JLabel(Integer.toString(WERT_MIN - 500)));
		sliderLabelTabelleRes.put(RESISTANCE_MAX / 2, new JLabel(Integer.toString(RESISTANCE_MAX / 2)));
		slBlindWiderstand.setLabelTable(sliderLabelTabelleRes);
		slBlindWiderstand.addChangeListener(this);
		this.add(slBlindWiderstand);

		txtBlindWiderstand = new JTextField(Integer.valueOf(WERT_MIN).toString());
		txtBlindWiderstand.setBounds(450, 150, 40, 20);
		txtBlindWiderstand.addKeyListener(this);
		txtBlindWiderstand.addFocusListener(this);
		this.add(txtBlindWiderstand);

		JLabel lbBlindOhm = new JLabel("Ohm");
		lbBlindOhm.setForeground(Color.BLUE);
		lbBlindOhm.setFont(new Font("Serif", Font.ITALIC, 19));
		lbBlindOhm.setBounds(500, 150, 40, 15);
		this.add(lbBlindOhm);

		// ==========================================================//

		Grundformeln formeln = new Grundformeln(slSpannung.getValue(), slWirkWiderstand.getValue(),
				slBlindWiderstand.getValue());

		// ===========STROMSTÄRKE====================================//

		lbStrom = new JLabel("Stromstärke");
		lbStrom.setBounds(20, 200, 100, 15);
		this.add(lbStrom);

		txtStrom = new JTextField(Double.valueOf(formeln.getCurrency()).toString());
		txtStrom.setBounds(130, 200, 40, 20);
		txtStrom.setFocusable(false);
		txtStrom.addKeyListener(this);
		txtStrom.addFocusListener(this);
		this.add(txtStrom);

		JLabel lbAmper = new JLabel("A");
		lbAmper.setForeground(Color.BLUE);
		lbAmper.setFont(new Font("Serif", Font.ITALIC, 19));
		lbAmper.setBounds(180, 200, 20, 15);
		this.add(lbAmper);

		lbStromPhase = new JLabel("Phasenverschiebung");
		lbStromPhase.setBounds(300, 200, 150, 15);
		this.add(lbStromPhase);

		txtStromPhase = new JTextField(formeln.getCurrPhase());
		txtStromPhase.setBounds(450, 200, 40, 20);
		txtStromPhase.setFocusable(false);
		txtStromPhase.addKeyListener(this);
		txtStromPhase.addFocusListener(this);
		this.add(txtStromPhase);

		JLabel lbGrad = new JLabel("°");
		lbGrad.setForeground(Color.BLUE);
		lbGrad.setFont(new Font("Serif", Font.ITALIC, 19));
		lbGrad.setBounds(500, 200, 20, 15);
		this.add(lbGrad);

		// ==========================================================//

		// ===========LEISTUNG=======================================//

		// QUELLE

		JLabel lbQ = new JLabel("QUELLE");
		lbQ.setFont(new Font("Serif", Font.ITALIC, 21));
		lbQ.setBounds(20, 250, 150, 20);
		this.add(lbQ);

		// Wirkleistung

		lbWirkLeistungQ = new JLabel("Wirkleistung");
		lbWirkLeistungQ.setBounds(20, 300, 100, 15);
		this.add(lbWirkLeistungQ);

		txtWirkLeistungQ = new JTextField(Double.valueOf(formeln.getPower("WirkLeistung")).toString());
		txtWirkLeistungQ.setBounds(130, 300, 40, 20);
		txtWirkLeistungQ.setFocusable(false);
		this.add(txtWirkLeistungQ);

		JLabel lbWatt1Q = new JLabel("W");
		lbWatt1Q.setForeground(Color.BLUE);
		lbWatt1Q.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWatt1Q.setBounds(180, 300, 20, 15);
		this.add(lbWatt1Q);

		// Blindleistung

		lbBlindLeistungQ = new JLabel("Blindleistung");
		lbBlindLeistungQ.setBounds(300, 300, 100, 15);
		this.add(lbBlindLeistungQ);

		txtBlindLeistungQ = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString()
						 */);
		txtBlindLeistungQ.setBounds(450, 300, 40, 20);
		txtBlindLeistungQ.setFocusable(false);
		this.add(txtBlindLeistungQ);

		JLabel lbWatt2Q = new JLabel("var");
		lbWatt2Q.setForeground(Color.BLUE);
		lbWatt2Q.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWatt2Q.setBounds(500, 300, 25, 15);
		this.add(lbWatt2Q);

		// Scheinleistung

		lbLeistungQ = new JLabel("SCHEINLEISTUNG");
		lbLeistungQ.setBounds(170, 350, 100, 15);
		this.add(lbLeistungQ);

		txtLeistungQ = new JTextField("0.0"/* Double.valueOf(formeln.getPower( "Blindleistung")).toString() */);
		txtLeistungQ.setBounds(300, 350, 40, 20);
		txtLeistungQ.setFocusable(false);
		this.add(txtLeistungQ);

		JLabel lbWattQ = new JLabel("VA");
		lbWattQ.setForeground(Color.BLUE);
		lbWattQ.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWattQ.setBounds(350, 350, 25, 15);
		this.add(lbWattQ);

		// VERBRAUCHER

		JLabel lbV = new JLabel("VERBRAUCHER");
		lbV.setFont(new Font("Serif", Font.ITALIC, 21));
		lbV.setBounds(20, 400, 150, 20);
		this.add(lbV);

		// Wirkleistung

		lbWirkLeistungV = new JLabel("Wirkleistung");
		lbWirkLeistungV.setBounds(20, 450, 100, 15);
		this.add(lbWirkLeistungV);

		txtWirkLeistungV = new JTextField(Double.valueOf(formeln.getPower("WirkLeistung")).toString());
		txtWirkLeistungV.setBounds(130, 450, 40, 20);
		txtWirkLeistungV.setFocusable(false);
		this.add(txtWirkLeistungV);

		JLabel lbWatt1V = new JLabel("W");
		lbWatt1V.setForeground(Color.BLUE);
		lbWatt1V.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWatt1V.setBounds(180, 450, 20, 15);
		this.add(lbWatt1V);

		// Blindleistung

		lbBlindLeistungV = new JLabel("Blindleistung");
		lbBlindLeistungV.setBounds(300, 450, 100, 15);
		this.add(lbBlindLeistungV);

		txtBlindLeistungV = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString() */);
		txtBlindLeistungV.setBounds(450, 450, 40, 20);
		txtBlindLeistungV.setFocusable(false);
		this.add(txtBlindLeistungV);

		JLabel lbWatt2V = new JLabel("var");
		lbWatt2V.setForeground(Color.BLUE);
		lbWatt2V.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWatt2V.setBounds(500, 450, 25, 15);
		this.add(lbWatt2V);

		// Scheinleistung

		lbLeistungV = new JLabel("SCHEINLEISTUNG");
		lbLeistungV.setBounds(170, 500, 100, 15);
		this.add(lbLeistungV);

		txtLeistungV = new JTextField("0.0"/* Double.valueOf(formeln.getPower("Blindleistung")).toString()*/);
		txtLeistungV.setBounds(300, 500, 40, 20);
		txtLeistungV.setFocusable(false);
		this.add(txtLeistungV);

		JLabel lbWattV = new JLabel("VA");
		lbWattV.setForeground(Color.BLUE);
		lbWattV.setFont(new Font("Serif", Font.ITALIC, 19));
		lbWattV.setBounds(350, 500, 25, 15);
		this.add(lbWattV);

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
		int valWiderstandRe = slWirkWiderstand.getValue();
		int valWiderstandIm = slBlindWiderstand.getValue();
		
		txtSpannung.setText(String.valueOf(valSpannung));
		txtWirkWiderstand.setText(String.valueOf(valWiderstandRe));		
		txtBlindWiderstand.setText(String.valueOf(valWiderstandIm));		
		
		txtStrom.setText(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm).getCurrency());
		txtStromPhase.setText(new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm).getCurrPhase());
		
		txtWirkLeistungQ.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("WirkLeistung")));
		txtBlindLeistungQ.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("BlindLeistung")));
		txtLeistungQ.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Quelle").getPower("ScheinLeistung")));
		txtWirkLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher").getPower("WirkLeistung")));
		txtBlindLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher").getPower("BlindLeistung")));
		txtLeistungV.setText((new Grundformeln(valSpannung, valWiderstandRe, valWiderstandIm, "Verbraucher").getPower("ScheinLeistung")));
		
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
		// Umschalten auf den Schieberegler für den Wert Wirkiderstand
		else if (tf == txtWirkWiderstand)
			slider = slWirkWiderstand;
		// Umschalten auf den Schieberegler für den Wert Blindwiderstand
		else if (tf == txtBlindWiderstand)
			slider = slBlindWiderstand;
		
		
		
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
		
		if ((tf.getText().length() >= 3) && (tf.equals(txtSpannung) || (tf.getText().length() >= 4) && ((tf.equals(txtWirkWiderstand) || (tf.equals(txtBlindWiderstand))))))
		{
			Toolkit.getDefaultToolkit().beep();
			e.consume();
			return;
		}
		
		if ((convertTextFieldValue(tf, e) > VOTAGE_MAX) && (tf.equals(txtSpannung) || (convertTextFieldValue(tf, e) > RESISTANCE_MAX) && (tf.equals(txtWirkWiderstand))))
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
