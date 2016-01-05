package calendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MonthRenderer extends JLabel implements ListCellRenderer
{

	public MonthRenderer()
	{

	}

	@Override
	public Component getListCellRendererComponent(JList lista, Object elem, int indeks, boolean zazn, boolean fokus)
	{
		String napis = elem.toString();
		setText(napis);
		if (napis.contains("Niedziela")) setForeground(Color.RED);
		else
			setForeground(Color.BLACK);
		return this;
	}
}
