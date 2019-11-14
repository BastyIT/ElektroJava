package myUtil;

import java.awt.event.ActionListener;

import javax.swing.*;

import Elektrotechnik.Grundlagen;
import myUtil.WinUtil.MenuItemType;

public class WinUtil 
{
	// Art des Men�eintrags
	
	public static enum MenuItemType
	{
		ITEM_PLAIN, ITEM_CHECK, ITEM_RADIO
	}
	
	
	
	
	/**
	 * <li><b><i>createMenu</i></b>
	 * <br>
	 * <br>
	 * public JMenu createMenu(JMenuBar menuBar, String menuText, String menuName, int shortKey)
	 * <br>
	 * <br>
	 * Erstellt einen Men�.
	 * <br>
	 * <br>
	 * @param menuBar
	 * - Die Men�leiste, zu dem dieses Men� geh�rt.
	 * @param menuText
	 * - Der Text des Men�s.
	 * @param menuName
	 * - Optionaler Name des Men�s oder <b>null</b>.
	 * @param shortKey
	 * - Optionales Tastaturk�rzel oder <b>0</b>.
	 * @return
	 * 	Men�.
	 */
	public static JMenu createMenu(JMenuBar menuBar, String menuText, String menuName, int shortKey)
	{
		
		JMenu menu = null;
		
		// Hinzuf�gen des Men�s
		menu = new JMenu();
		menu.setText(menuText);
		menu.setName(menuName);

		
		// Optionales Tastaturk�rzel hinzuf�gen
		if (shortKey > 0)
			menu.setMnemonic(shortKey);
		
		menuBar.add(menu);
		
		// R�ckgabe des Men�s
		return menu;
	}
	
	/**
	 * <li><b><i>createMenuItem</i></b>
	 * <br>
	 * <br>
	 * public JMenuItem createMenuItem(JMenu menu, String miName, MenuItemType miType, ActionListener actionListener,<br>&nbsp String miText, ImageIcon icon, int shortKey, String miToolTip)
	 * <br>
	 * <br>
	 * Erstellt einen Men�eintrag.
	 * <br>
	 * <br>
	 * @param menu
	 * 	- Das Men�, zu dem dieser Men�eintrag geh�rt.
	 * @param miName
	 * - Optionaler Name des Men�eintrags oder <b>nulMenul</b>.
	 * @param miType
	 * 	- Der Typ des Men�eintrags <b>MenuItemType</b>.
	 * @param grundlagen
	 *  - Der ActionListener, der verwendet werden soll, wenn der Men�eintrag ausgew�hlt wurde oder <b>null</b>.
	 * @param miText
	 * 	- Der Text des Men�eintrags.
	 * @param icon
	 * 	- Symbol, welches links vor dem Text angezeigt werden soll oder <b>null</b>.
	 * @param shortKey
	 * 	- Optionales Tastaturk�rzel oder <b>0</b>.
	 * @param miToolTip
	 * 	- Optionaler Text f�r den Tooltip oder <b>null</b>.
	 * @return
	 * 	  Men�eintrag.
	 */

	public static JMenuItem createMenuItem(JMenuItem menu, String miName,  MenuItemType miType, ActionListener actionListener, String miText, 
			                               ImageIcon miIcon, int shortKey, String miTooltip)
	{
		JMenuItem menuItem = new JMenuItem();
		
		switch(miType)
		{
		case ITEM_RADIO:
			menuItem = new JRadioButtonMenuItem();
			break;
			
		case ITEM_CHECK:
			menuItem =  new JCheckBoxMenuItem();
			break;
			
		}
		
		// Name des Menueintrags
		menuItem.setName(miName);
		
		// Men�text hinzuf�gen
		menuItem.setText(miText);

		// Optionales Image hinzuf�gen
		menuItem.setIcon(miIcon);
		
		// Optionales Tastaturk�rzel hinzuf�gen
		if (shortKey > 0)
			menuItem.setMnemonic(shortKey);
				
		// Optionalen Tooltip hinzuf�gen
		menuItem.setToolTipText(miTooltip);
		
		// Action Listener hinzuf�gen
		menuItem.addActionListener(actionListener);
		
		// Men�eintrag zum Men� hinzuf�gen
		menu.add(menuItem);
		
		
		// R�ckgabe des Men�eintrags	
		return menuItem;
	}
	
	
	
}
