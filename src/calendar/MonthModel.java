package calendar;

import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;

public class MonthModel extends AbstractListModel
{
	int[] liczbaDni = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private String[] dni = { "Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota" };
	private String[] miesiace = { "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień",
			"Wrzesień", "Październik", "Listopad", "Grudzień" };
	private String[] calendar;
	private int year;
	private int month;

	public MonthModel(int year, int month)
	{
		this.year = year;
		this.month = month;
		int size = liczbaDni[month - 1];
		if (month == 2 && isLeapYear(year)) ++size;
		calendar = new String[size];
		for (int i = 0; i < calendar.length; i++)
		{
			calendar[i] = "";
		}
		setMonth(year, month);
	}

	public void setMonth(int year, int month)
	{
		GregorianCalendar cal = new GregorianCalendar();
		for (int i = 0; i < calendar.length; i++)
		{
			cal.set(year, month - 1, i + 1);
			int day = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
			String napis = dni[day] + " " + (i + 1);
			calendar[i] = napis;
		}
		this.fireContentsChanged(this, 0, getSize() - 1);
	}

	@Override
	public Object getElementAt(int index)
	{
		return (String) (calendar[index] + " " + miesiace[month - 1]);
	}

	@Override
	public int getSize()
	{
		return calendar == null ? 0 : calendar.length;
	}

	public boolean isLeapYear(int year)
	{
		if (year <= 1582) return false;
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}
}
