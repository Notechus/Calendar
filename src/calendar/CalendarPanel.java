package calendar;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CalendarPanel extends JPanel
{
	private CalendarMonth[] c;
	private JToolBar toolbar;
	private JButton nastRok;
	private JSpinner zmienRok;
	private JButton poprzRok;
	private JPanel kalendarz;
	private int year;

	public CalendarPanel(int year)
	{
		this.year = year;
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		setLayout(new BorderLayout());
		kalendarz = new JPanel();
		kalendarz.setLayout(new GridLayout(4, 3));
		GridBagConstraints gbc = new GridBagConstraints();
		c = new CalendarMonth[12];
		for (int i = 0; i < 12; i++)
		{
			gbc.gridx = i % 4;
			gbc.gridy = i % 3;
			c[i] = new CalendarMonth(year, i);
			kalendarz.add(c[i], gbc);
		}
		add(kalendarz, BorderLayout.CENTER);
		toolbar = new JToolBar();
		toolbar.setLayout(new GridLayout(1, 2));
		nastRok = new JButton("Nastepny");
		nastRok.addActionListener(new IncrementButton());
		poprzRok = new JButton("Poprzedni");
		poprzRok.addActionListener(new DecrementButton());
		zmienRok = new JSpinner(new SpinnerNumberModel(year, 0, 2500, 1));
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
			if (year < 2500)
			{
				year++;
				zmienRok.setValue(year);
				CalendarWindow.setYearTitle("Rok " + year);
				for (int i = 0; i < 12; i++)
				{
					c[i].setMonth(year, i);
				}
			}
		}
	}

	private class DecrementButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (year > 0)
			{
				year--;
				zmienRok.setValue(year);
				CalendarWindow.setYearTitle("Rok " + year);
				for (int i = 0; i < 12; i++)
				{
					c[i].setMonth(year, i);
				}
			}
		}
	}

	private class SpinnerListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			JSpinner spin = (JSpinner) e.getSource();
			if ((int) spin.getValue() < 2500 && (int) spin.getValue() > 0)
			{
				year = (int) spin.getValue();
				CalendarWindow.setYearTitle("Rok " + year);
				for (int i = 0; i < 12; i++)
				{
					c[i].setMonth(year, i);
				}
			}
		}
	}
}
