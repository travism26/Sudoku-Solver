package travis.sudokusolver;

/*
 * @author Travis Martin
 * THIS CLASS WILL BE DEPRECATED UNTIL FURTHUR NOTICE. 
 * I will remake the GUI using something else swing is GROSS.
 */
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SudokuGUI extends JFrame implements ActionListener {
	int[][] puzzle = new int[9][9];
	private JPanel controls = new JPanel();
	private JPanel write = new JPanel();
	JTextArea canvas = new JTextArea();
	JFileChooser fc = new JFileChooser();
	private JButton solve = new JButton("Solve");
	private JButton clear = new JButton("Clear");
	private JButton Gen = new JButton("Generate");
	private JButton read = new JButton("Read");
	private JButton load = new JButton("Load");
	private JButton rules = new JButton("Rules");
	JScrollPane sp = new JScrollPane(write);
	JScrollBar bar = new JScrollBar();
	String Location = null;
	boolean justRead = false;
	boolean justSolved = false;
	boolean justGen = false;
	String Error = "NA";

	public SudokuGUI()
	{

		getContentPane().setLayout(new BorderLayout());
		solve.addActionListener(this);
		clear.addActionListener(this);
		Gen.addActionListener(this);
		read.addActionListener(this);
		load.addActionListener(this);
		rules.addActionListener(this);
		controls.add(solve);
		controls.add(clear);
		controls.add(Gen);
		controls.add(read);
		controls.add(load);
		controls.add(rules);

		JScrollPane scrollPane = new JScrollPane(canvas);

		setPreferredSize(new Dimension(6000, 6000));

		add(scrollPane, BorderLayout.CENTER);
		canvas.setBorder(BorderFactory.createTitledBorder("Puzzle Input Field"));
		canvas.setPreferredSize(new Dimension(250, 250));
		// write.add(canvas);
		// getContentPane().add("North", write);
		getContentPane().add("South", controls);

		getContentPane().setSize(400, 400);
	} // init()

	public void actionPerformed(ActionEvent e)
	{

		/*
		 * This is the read button actions!
		 */
		if (e.getSource() == read)
		{
			if (justRead == true)
			{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null,
						"you just read in a puzzle press the clear button to reset "
								+ "the puzzle solver or hit solve to solve it",
						"Warning",

						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,

						null, options, options[0]);
			} else if (justGen == true)
			{
				justRead = true;
				canvas.append("\nYour puzzle has been read in...");
			} else
			{
				String input = canvas.getText();
				justRead = true;
				puzzle = obj.readPuz(input);
				canvas.append("\n");
				print();
			}

		}
		/*
		 * Generate button actions
		 */
		else if (e.getSource() == Gen)
		{
			if (justGen == true)
			{
				puzzleReset();
			}
			// justRead = true;
			justGen = true;
			justRead = true;
			puzzle = obj.Rowgenerate();
			print();
			// Object[] options = { "OK", "CANCEL" };
			// JOptionPane.showOptionDialog(null, "Click OK to continue",
			// "Warning",

			// JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

			// null, options, options[0]);
		}
		/*
		 * Load button actions!
		 */
		else if (e.getSource() == load)
		{
			puzzleReset();
			int returnVal = fc.showOpenDialog(SudokuGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				String local = file.getAbsolutePath();
				puzzle = obj.readPuzzle(local);
				print();
				justRead = true;
			}
		}
		/*
		 * Solve button actions
		 */
		else if (e.getSource() == solve)
		{
			if (obj.isValid(puzzle) == false)
			{
				Error = obj.printPossibleError1() + " \n "
						+ obj.printPossibleError2() + " \n "
						+ obj.printPossibleError3();
				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								null,
								"The puzzle you entered is unsolvable please enter "
										+ "a new puzzle\nor revise the one you entered \n "
										+ Error, "Warning",

								JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE,

								null, options, options[0]);
			}
			if (justSolved == true)
			{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null,
						"Already solved please clear the "
								+ "puzzle\n or load a new one in :)",
						"Warning",

						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,

						null, options, options[0]);
			} else if (justRead == false)
			{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(null, "You didnt enter a puzzle",
						"Warning",

						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,

						null, options, options[0]);
			} else
			{
				obj.check(puzzle);
				canvas.append("\nThis is the solution to you puzzle\n");
				print();
				justSolved = true;
				justGen = true;
			}
		}

		/*
		 * Clear Button!
		 */
		else if (e.getSource() == clear)
		{
			puzzleReset();
		}
		/*
		 * Rules button
		 */
		else if (e.getSource() == rules)
		{
			Object[] options = { "OK" };
			JOptionPane
					.showOptionDialog(
							null,
							"Input should look like: no space needed\n102356000"
									+ "\n987600\nYou can also leave blank: \n12341\n059\nHowever, if you want"
									+ "To put spaces you can\nEx: 123 654 9 8 7",
							"Rules",

							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,

							null, options, options[0]);
		}
	} // actionPerformed()

	public static void main(String args[])
	{
		SudokuGUI gui = new SudokuGUI();
		Object[] options = { "OK" };

		gui.setSize(500, 700);
		gui.setVisible(true);
		JOptionPane.showOptionDialog(null, "Welcome to the Sudoku solver",
				"Quick Note!",

				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

				null, options, options[0]);
		gui.addWindowListener(new WindowAdapter()
		{ // Quit the application
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	/*
	 * This method will overwrite the general print method
	 */
	public void print()
	{
		for (int i = 0; i < 9; i++)
		{

			for (int j = 0; j < 9; j++)
			{
				canvas.append(" " + puzzle[i][j]);
			}
			canvas.append("\n");
		}
	}

	/*
	 * I created this as a fail safe to prevent accendital errors when reading
	 * in a puzzle this method is used to reset the puzzle variable and justUsed
	 * variable to enable the read button again.
	 */
	public void puzzleReset()
	{
		canvas.append("\nYou inputted these numbers:\n");
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				puzzle[i][j] = 0;
			}
		}
		canvas.setText("");
		justGen = false;
		justRead = false;
		justSolved = false;
	}

} // RandomDotGUI
