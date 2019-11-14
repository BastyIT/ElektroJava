package myUtil;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

import javax.swing.text.JTextComponent;

// Ableitung aus der abstrakten Klasse 'FocusTraversalPolicy'
// Implementierung der notwendigen Methoden.

public class MyFocusTraversalPolicy extends FocusTraversalPolicy
{

	
	private Vector<Component> components;
	
	
	public MyFocusTraversalPolicy(Vector<Component> components)
	{
		this.components = components;

	}
	
	@Override
	public Component getComponentAfter(Container aContainer, Component c)
	{
		
		// Endlosschleife vermeiden, wenn es keine Komponenten gibt, die den
		// Eingabefokus erhalten können: Der Container bekommt den Fokus.
		if (!containsFocusableComponents())
			return  aContainer;
		
//		int idx = components.indexOf(c);
//		if (idx >= 0 && idx < components.size() - 1)
//			return components.get(idx + 1);
//		else
//			return components.get(0);
		
		int idx = (components.indexOf(c) + 1) % components.size();
		if (isFocusable(components.get(idx)))
			return components.get(idx);
		else
			return getComponentAfter(aContainer,  components.get(idx));
			
			
	}

	@Override
	public Component getComponentBefore(Container aContainer, Component c)
	{
		
		// Endlosschleife vermeiden, wenn es keine Komponenten gibt, die den
		// Eingabefokus erhalten können: Der Container bekommt den Fokus.
		if (!containsFocusableComponents())
			return  aContainer;
				
//		int idx = components.indexOf(c) - 1;
//		if (idx < 0)
//			idx = components.size() - 1;
			
		int idx = (components.indexOf(c ) - 1 + components.size()) % components.size();
		if (isFocusable(components.get(idx)))
			return components.get(idx);
		else
			return getComponentBefore(aContainer,  components.get(idx));
		

	}

	@Override
	public Component getFirstComponent(Container aContainer)
	{
		
		if (isFocusable(components.firstElement()))
			return components.firstElement();
		else
			return getComponentAfter(aContainer,  components.firstElement());
		
		
	}

	@Override
	public Component getLastComponent(Container aContainer)
	{
		if (isFocusable(components.lastElement()))
			return components.lastElement();
		else
			return getComponentBefore(aContainer, components.lastElement());
	}

	@Override
	public Component getDefaultComponent(Container aContainer)
	{
		return getFirstComponent(aContainer); 
	}

	private boolean isFocusable(Component c)
	{
		
		if (c instanceof JTextComponent)
		{
			if (!((JTextComponent)c).isEditable())
				return false;
				
		}
			
		return c.isVisible() && c.isEnabled() && c.isFocusable();
		
		
	}
	
	private boolean containsFocusableComponents()
	{
		
		boolean retValue = false;
		
		for (Component c : components)
		{
			
			if (isFocusable(c))
			{
				retValue = true;
				break;
			}
			
		}
		
		return retValue;
		
		
	}
	
	
	// Methode zum Testen
	public void enableAllComponents(boolean b)
	{
		for (Component c : components)
			c.setEnabled(b);
	}
	
}
