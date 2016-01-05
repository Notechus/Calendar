package calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

public class CalendarModel extends AbstractTableModel
{
	private String[] dni = { "Pon", "Wt", "Śr", "Czw", "Pt", "Sob", "Ndz" };
	// ilość dni w miesiącach
	int[] liczbaDni = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	String[][] calendar = new String[7][7];

	public CalendarModel(int year, int month)
	{
		for (int i = 0; i < dni.length; ++i)
			calendar[0][i] = dni[i];
		for (int i = 1; i < 7; ++i)
			for (int j = 0; j < 7; ++j)
				calendar[i][j] = " ";
		setMonth(year, month);
	}

	@Override
	public int getColumnCount()
	{
		return 7;
	}

	@Override
	public int getRowCount()
	{
		return 7;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		return calendar[row][column];
	}

	public void setValueAt(Object value, int row, int column)
	{
		calendar[row][column] = (String) value;
	}

	public void setMonth(int year, int month)
	{
		for (int i = 1; i < 7; ++i)
			for (int j = 0; j < 7; ++j)
				calendar[i][j] = " ";
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(year, month, 1);
		int offset = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
		if (offset == 1) offset = 6;
		else
		{
			--offset;
		}
		offset += 7;
		if (offset <= 7) offset += 7;
		int num = daysInMonth(year, month);
		for (int i = 0; i < num; ++i)
		{
			if (year == 1582 && month == 9 && i == 4)
			{
				i += 10;
			}
			calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
			++offset;
		}
		this.fireTableDataChanged();
	}

	public int daysInMonth(int year, int month)
	{
		int days = liczbaDni[month];
		if (month == 1 && isLeapYear(year)) ++days;
		return days;
	}

	public boolean isLeapYear(int year)
	{
		if (year <= 1582) return false;
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
}
