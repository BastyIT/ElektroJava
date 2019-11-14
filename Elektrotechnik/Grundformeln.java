package Elektrotechnik;

public class Grundformeln 
{
	private int valVoltage, valResistance, valReactance;
	private double valCurrency, valImpedance, valActPower, valReaktPower, valComplxPower;
	private double valVoltPhase, valCurrPhase, valPhase;

	
	public Grundformeln(int valVoltage, int valResistance, int valReactance)
	{
		this.valVoltage = valVoltage;
		this.valResistance = valResistance;
		this.valReactance = valReactance;
		
		mehtCurrency(this.valVoltage, this.valResistance, this.valReactance);
	}
	
	public Grundformeln(int valVoltage, int valResistance, int valReactance, String txt)
	{
		this(valVoltage, valResistance, valReactance);
		
		this.valVoltage = valVoltage;
		this.valResistance = valResistance;
		this.valReactance = valReactance;
		

		methPower(txt);
	}
	
	private double pythagoras(int valResistance, int valReactance)
	{
		double temp1 = Math.pow(valResistance, 2);
		double temp2 = Math.pow(valReactance, 2);
		valImpedance = Math.sqrt(temp1 +temp2);
		
		return valImpedance;
	}
	
	private void mehtCurrency(int valVoltage, int valResistance, int valReactance)
	{
		valCurrency = (double)valVoltage / pythagoras(valResistance, valReactance);		
		valPhase = (Math.atan((double)valReactance / valResistance) * 180 / Math.PI);
		valCurrPhase = valVoltPhase - (valPhase);
	}
	
	public String getCurrency()
	{
		String retValue = Double.toString(Math.round(this.valCurrency * 100.0) / 100.0);
		return retValue;
	}
	
	public String getCurrPhase()
	{
		String retValue = Integer.toString((int)valCurrPhase);
		return retValue;
	}
	
	private void methPower(String txtTeil)
	{
		if (txtTeil.equals("Quelle"))
		{
			valComplxPower = valVoltage * valCurrency;
			valActPower = valComplxPower * Math.cos((valVoltPhase + valCurrPhase) * Math.PI / 180);
			valReaktPower = valComplxPower * Math.sin((valVoltPhase + valCurrPhase) * Math.PI / 180);
		}
		if (txtTeil.equals("Verbraucher"))
		{
			valComplxPower = Math.pow(valCurrency, 2) * valImpedance;
			valActPower = valComplxPower * Math.cos((valCurrPhase + valCurrPhase + valPhase) * Math.PI / 180);
			valReaktPower = valComplxPower * Math.sin((valCurrPhase + valCurrPhase + valPhase) * Math.PI / 180);
		}
	}

	
	public String getPower(String txtPower)
	{
		String retValue = null;
		
		if(txtPower.equals("WirkLeistung"))
			retValue = Double.toString((Math.round(this.valActPower * 10.0) / 10.0));
		if(txtPower.equals("BlindLeistung"))
			retValue = Double.toString((Math.round(this.valReaktPower * 10.0) / 10.0));
		if(txtPower.equals("ScheinLeistung"))
			retValue = Double.toString((Math.round(this.valComplxPower * 10.0) / 10.0));
		return retValue;
	}
	
	public double getRootMeanSquare(int value)
	{
		return value / Math.sqrt(2.0);
	}

}
