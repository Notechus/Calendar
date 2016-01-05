package calendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableCellRenderer;

public class CalendarRenderer extends JLabel implements TableCellRenderer
{

	public CalendarRenderer()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable tablica, Object elem, boolean selected, boolean focus,
			int row, int column)
	{
		String napis = elem.toString();
		setText(napis);
		if (column == 6) setForeground(Color.RED);
		else
			setForeground(Color.BLACK);
		return this;
	}
}
