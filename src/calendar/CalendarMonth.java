package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class CalendarMonth extends JPanel
{
	private String[] miesiace = { "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień",
			"Wrzesień", "Październik", "Listopad", "Grudzień" };
	private CalendarModel c;
	private JLabel miesiac;
	private JTable tabMonth;

	public CalendarMonth(int year, int month)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		setLayout(new BorderLayout());
		miesiac = new JLabel(miesiace[month]);
		miesiac.setHorizontalAlignment(JLabel.CENTER);
		miesiac.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				super.focusGained(e);
			}
		});
		miesiac.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked()
			{
				String text = miesiac.getText();
				int index = 0;
				for (int i = 0; i < 12; i++)
				{
					if (text.equals(miesiace[i]))
					{
						index = i;
						break;
					}
				}
				CalendarWindow.changeView(index + 1);
			}
		});
		miesiac.setFocusable(true);
		add(miesiac, BorderLayout.NORTH);
		miesiac.requestFocusInWindow();
		miesiac.requestFocus();
		c = new CalendarModel(year, month);
		tabMonth = new JTable(c);
		tabMonth.setBorder(new LineBorder(Color.BLACK, 2));
		tabMonth.setDefaultRenderer(Object.class, new CalendarRenderer());
		add(tabMonth, BorderLayout.CENTER);
	}

	public void setMonth(int year, int month)
	{
		c.setMonth(year, month);
	}
}
