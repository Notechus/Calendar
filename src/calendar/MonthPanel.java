package calendar;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MonthPanel extends JPanel
{
	private JToolBar toolbar;
	private JButton nastRok;
	private JSpinner zmienRok;
	private JButton poprzRok;
	private MonthDays miesiace;
	private int year;
	private int month;

	public MonthPanel(int year, int month)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		this.year = year;
		this.month = month;
		miesiace = new MonthDays(year, month);
		setLayout(new BorderLayout());
		add(miesiace, BorderLayout.CENTER);
		toolbar = new JToolBar();
		toolbar.setLayout(new GridLayout(1, 2));
		nastRok = new JButton("Nastepny");
		nastRok.addActionListener(new IncrementButton());
		poprzRok = new JButton("Poprzedni");
		poprzRok.addActionListener(new DecrementButton());
		zmienRok = new JSpinner(new SpinnerNumberModel(month, 0, 2500, 1));
		zmienRok.addChangeListener(new SpinnerListener());
		toolbar.add(poprzRok);
		toolbar.add(zmienRok);
		toolbar.add(nastRok);
		add(toolbar, BorderLayout.SOUTH);
	}

	private class IncrementButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			++month;
			if (month > 12)
			{
				year++;
				month = 1;
			}
			CalendarWindow.setMonthTitle("Miesiąc " + month);
			CalendarWindow.setYearTitle("Rok " + year);
			miesiace.setMonth(year, month);
			zmienRok.setValue(month);
		}
	}

	private class DecrementButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			--month;
			if (month < 1)
			{
				year--;
				month = 12;
			}
			CalendarWindow.setMonthTitle("Miesiąc " + month);
			CalendarWindow.setYearTitle("Rok " + year);
			miesiace = new MonthDays(year, month);
			zmienRok.setValue(month);
			miesiace.setMonth(year, month);
		}
	}

	private class SpinnerListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			JSpinner spin = (JSpinner) e.getSource();
			month = (int) spin.getValue();
			if (month > 12)
			{
				year++;
				month = 1;
			} else if (month < 1)
			{
				year--;
				month = 12;
			}
			CalendarWindow.setMonthTitle("Miesiąc " + month);
			CalendarWindow.setYearTitle("Rok " + year);
			spin.setValue(month);
			miesiace.setMonth(year, month);
		}
	}
}
