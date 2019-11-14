package myUtil;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

// Statusbar aus der Klasse JMenuBar ableiten.
// Standardmässig wird für die MenuBar das Box-Layout verwendet,
// deshalb Änderung in BorderLayout.

public class StatusBar extends JMenuBar
{
	
	private JLabel statusText;
	
	public StatusBar()
	{
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		statusText = new JLabel();	
		
		// Der JLabel wird innerhalb des BorderLayouts dem BorderLayout.CENTER zugewiesen. 
		this.add(statusText);
		
	}
	
	public void setText(String text)
	{
		statusText.setText(text);
	}
	
	public String getText()
	{
		return statusText.getText();
	}
	
	public void setMessage(String message)
	{
		statusText.setText(" " + message);
	}
	
	public JLabel getStatusLabel()
	{
		return statusText;
	}
	
	
	public void setHorizontalAlignment(int alignment)
	{
		statusText.setHorizontalAlignment(alignment);	
	}
	
	
}
