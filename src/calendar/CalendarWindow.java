package calendar;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CalendarWindow extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static JTabbedPane tPane;
	private int year;
	private static int month;
	private static String monthTitle;
	private static String yearTitle;
	private CalendarPanel calendar;
	private MonthPanel monthP;

	public CalendarWindow()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Calendar");
		year = GregorianCalendar.getInstance().get(Calendar.YEAR);
		month = GregorianCalendar.getInstance().get(Calendar.MONTH) + 1;
		yearTitle = "Rok " + year;
		monthTitle = "Miesiąc " + month;
		tPane = new JTabbedPane(JTabbedPane.TOP);
		monthP = new MonthPanel(year, month);
		calendar = new CalendarPanel(year);
		tPane.add(yearTitle, calendar);
		tPane.add(monthTitle, monthP);
		add(tPane);
		setVisible(true);
	}

	public static void changeView(int index)
	{
		month = index;
		tPane.setSelectedIndex(1);
		setMonthTitle("Miesiąc " + month);
		System.out.println("clicked");
	}

	public static void setMonthTitle(String title)
	{
		monthTitle = title;
		tPane.setTitleAt(1, monthTitle);
	}

	public static void setYearTitle(String title)
	{
		yearTitle = title;
		tPane.setTitleAt(0, yearTitle);
	}
}
