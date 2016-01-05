package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MonthDays extends JPanel
{
	private int year;
	private int month;
	private JList poprzedni;
	private JList teraz;
	private JList nastepny;

	public MonthDays(int year, int month)
	{
		this.year = year;
		this.month = month;
		setLayout(new GridLayout(1, 3));
		if (month == 1)
		{
			poprzedni = new JList(new MonthModel(year - 1, 12));
		} else
		{
			poprzedni = new JList(new MonthModel(year, month - 1));
		}
		teraz = new JList(new MonthModel(year, month));
		if (month == 12)
		{
			nastepny = new JList(new MonthModel(year + 1, 1));
		} else
		{
			nastepny = new JList(new MonthModel(year, month + 1));
		}
		poprzedni.setBorder(new LineBorder(Color.BLACK, 5));
		poprzedni.setCellRenderer(new MonthRenderer());
		teraz.setCellRenderer(new MonthRenderer());
		nastepny.setCellRenderer(new MonthRenderer());
		teraz.setBorder(new LineBorder(Color.BLACK, 5));
		nastepny.setBorder(new LineBorder(Color.BLACK, 5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(poprzedni, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(teraz, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(nastepny, gbc);
	}

	public void setMonth(int year, int month)
	{
		this.year = year;
		this.month = month;
		if (month == 1)
		{
			poprzedni.setModel(new MonthModel(year - 1, 12));
		} else
		{
			poprzedni.setModel(new MonthModel(year, month - 1));
		}
		teraz.setModel(new MonthModel(year, month));
		if (month == 12)
		{
			nastepny.setModel(new MonthModel(year + 1, 1));
		} else
		{
			nastepny.setModel(new MonthModel(year, month + 1));
		}
	}
}
