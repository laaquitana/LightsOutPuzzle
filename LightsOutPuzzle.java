import java.awt.*;
import java.awt.event.*;
import java.awt.Color.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.imageio.ImageIO;

public class LightsOutPuzzle extends JFrame
{
	private JToggleButton[] button;
	private JButton startButton;
	private JButton skipButton;
	private JButton exitButton;
	private int[] pattern;
	private JLabel puzzleNumberLabel;
	private JLabel solvedPuzzleLabel;
	private JLabel numOfPuzzleLabel;
	private JLabel numOfSolvedPuzzleLabel;
	private int numOfPuzzle;
	private int numOfSolvedPuzzle;
	private ImageIcon onIcon;
	private ImageIcon offIcon;
	private JPanel toggleButtonPanel;
	private JPanel buttonPanel;
	private JPanel labelPanel;
	private Vector<int[]> listOfPatterns;
	
	private LightsOutPuzzle()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setTitle( "Lights Out Puzzle" );
		setMinimumSize( new Dimension(280,425) );
		setResizable( false );
		setVisible(true);
		getContentPane().setBackground(Color.white);
		
		puzzleNumberLabel = new JLabel("Puzzle Number: ");
		puzzleNumberLabel.setMaximumSize(new Dimension(130,20));
		puzzleNumberLabel.setMinimumSize(new Dimension(130,20));
		solvedPuzzleLabel = new JLabel("Solved Puzzle: ");
		numOfPuzzleLabel = new JLabel("0");
		numOfPuzzleLabel.setMaximumSize(new Dimension(130,20));
		numOfPuzzleLabel.setMinimumSize(new Dimension(130,20));
		numOfSolvedPuzzleLabel = new JLabel("0");
		
		numOfPuzzle = 0;
		numOfSolvedPuzzle = 0;
		listOfPatterns = new Vector<int[]>();
		
		onIcon = new ImageIcon("images/on.png");
		offIcon = new ImageIcon("images/off.png");
		
		startButton = new JButton("START");
		startButton.setMaximumSize(new Dimension(79,30));
		startButton.setMinimumSize(new Dimension(79,30));
		skipButton = new JButton("SKIP");
		skipButton.setMaximumSize(new Dimension(79,30));
		skipButton.setMinimumSize(new Dimension(79,30));
		skipButton.setEnabled(false);
		exitButton = new JButton("EXIT");
		exitButton.setMaximumSize(new Dimension(79,30));
		exitButton.setMinimumSize(new Dimension(79,30));
		
		pattern = new int[25];
		for( int i = 0; i < 25; i++ )
			pattern[i] = 0;
		
		button = new JToggleButton[25];
		for( int i = 0; i < 25; i++ )
		{
			if(pattern[i]==0)button[i] = new JToggleButton( offIcon );
			else button[i] = new JToggleButton( onIcon );
			button[i].setMaximumSize(new Dimension(50,50));
			button[i].setMinimumSize(new Dimension(50,50));
			button[i].addActionListener( new ActionListener()
			{
				public void actionPerformed( ActionEvent evt )
				{
					toggleButton( evt );
				}
			});
			button[i].setEnabled(false);
		}
		toggleButtonPanel = new JPanel();
		toggleButtonPanel.setBackground(Color.white);
		GroupLayout toggleButtonLayout = new GroupLayout(toggleButtonPanel);
		toggleButtonPanel.setLayout(toggleButtonLayout);
		toggleButtonLayout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup3 = toggleButtonLayout.createSequentialGroup();
		hGroup3.addGroup( toggleButtonLayout.createParallelGroup().addComponent(button[0]).addComponent(button[5]).addComponent(button[10]).addComponent(button[15]).addComponent(button[20]) );
		hGroup3.addGroup( toggleButtonLayout.createParallelGroup().addComponent(button[1]).addComponent(button[6]).addComponent(button[11]).addComponent(button[16]).addComponent(button[21]) );
		hGroup3.addGroup( toggleButtonLayout.createParallelGroup().addComponent(button[2]).addComponent(button[7]).addComponent(button[12]).addComponent(button[17]).addComponent(button[22]) );
		hGroup3.addGroup( toggleButtonLayout.createParallelGroup().addComponent(button[3]).addComponent(button[8]).addComponent(button[13]).addComponent(button[18]).addComponent(button[23]) );
		hGroup3.addGroup( toggleButtonLayout.createParallelGroup().addComponent(button[4]).addComponent(button[9]).addComponent(button[14]).addComponent(button[19]).addComponent(button[24]) );
		toggleButtonLayout.setHorizontalGroup(hGroup3);
		GroupLayout.SequentialGroup vGroup3 = toggleButtonLayout.createSequentialGroup();
		vGroup3.addGroup( toggleButtonLayout.createParallelGroup(Alignment.BASELINE).addComponent(button[0]).addComponent(button[1]).addComponent(button[2]).addComponent(button[3]).addComponent(button[4]) );
		vGroup3.addGroup( toggleButtonLayout.createParallelGroup(Alignment.BASELINE).addComponent(button[5]).addComponent(button[6]).addComponent(button[7]).addComponent(button[8]).addComponent(button[9]) );
		vGroup3.addGroup( toggleButtonLayout.createParallelGroup(Alignment.BASELINE).addComponent(button[10]).addComponent(button[11]).addComponent(button[12]).addComponent(button[13]).addComponent(button[14]) );
		vGroup3.addGroup( toggleButtonLayout.createParallelGroup(Alignment.BASELINE).addComponent(button[15]).addComponent(button[16]).addComponent(button[17]).addComponent(button[18]).addComponent(button[19]) );
		vGroup3.addGroup( toggleButtonLayout.createParallelGroup(Alignment.BASELINE).addComponent(button[20]).addComponent(button[21]).addComponent(button[22]).addComponent(button[23]).addComponent(button[24]) );
		toggleButtonLayout.setVerticalGroup(vGroup3);
			
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		GroupLayout buttonLayout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(buttonLayout);
		buttonLayout.setAutoCreateGaps(true);
		buttonLayout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup1 = buttonLayout.createSequentialGroup();
		hGroup1.addGroup( buttonLayout.createParallelGroup().addComponent(startButton,Alignment.LEADING) );
		hGroup1.addGroup( buttonLayout.createParallelGroup().addComponent(skipButton,Alignment.CENTER) );
		hGroup1.addGroup( buttonLayout.createParallelGroup().addComponent(exitButton,Alignment.TRAILING) );
		buttonLayout.setHorizontalGroup(hGroup1);
		GroupLayout.SequentialGroup vGroup1 = buttonLayout.createSequentialGroup();
		vGroup1.addGroup( buttonLayout.createParallelGroup().addComponent(startButton).addComponent(skipButton).addComponent(exitButton) );
		buttonLayout.setVerticalGroup(vGroup1);
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.white);
		GroupLayout labelLayout = new GroupLayout(labelPanel);
		labelPanel.setLayout(labelLayout);
		labelLayout.setAutoCreateGaps(true);
		labelLayout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup2 = labelLayout.createSequentialGroup();
		hGroup2.addGroup( labelLayout.createParallelGroup().addComponent(puzzleNumberLabel).addComponent(solvedPuzzleLabel) );
		hGroup2.addGroup( labelLayout.createParallelGroup().addComponent(numOfPuzzleLabel).addComponent(numOfSolvedPuzzleLabel) );
		labelLayout.setHorizontalGroup(hGroup2);
		GroupLayout.SequentialGroup vGroup2 = labelLayout.createSequentialGroup();
		vGroup2.addGroup( labelLayout.createParallelGroup().addComponent(puzzleNumberLabel).addComponent(numOfPuzzleLabel) );
		vGroup2.addGroup( labelLayout.createParallelGroup().addComponent(solvedPuzzleLabel).addComponent(numOfSolvedPuzzleLabel) );
		labelLayout.setVerticalGroup(vGroup2);
		
		BoxLayout mainLayout = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
		setLayout(mainLayout);
		add(toggleButtonPanel);
		add(labelPanel);
		add(buttonPanel);
		
		loadPatterns();
		
		startButton.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent evt )
			{
				startGame();
			}
		});
		
		skipButton.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent evt )
			{
				skipPuzzle();
			}
		});
		
		exitButton.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent evt )
			{
				exitGame();
			}
		});
		
		getRootPane().setDefaultButton(startButton);
	}
	
	private void startGame()
	{
		for( int i = 0; i < 25; i++ )
			button[i].setEnabled(true);
		skipButton.setEnabled(true);
		startButton.setEnabled(false);
		setPattern();
		updateToggleButtonPattern();
		numOfPuzzle++;
		numOfPuzzleLabel.setText( Integer.toString( numOfPuzzle ) );
		numOfSolvedPuzzleLabel.setText( Integer.toString( numOfSolvedPuzzle ) );
	}
	
	private void skipPuzzle()
	{
		if( numOfPuzzle < listOfPatterns.size() )
		{
			setPattern();
			updateToggleButtonPattern();
			numOfPuzzle++;
			numOfPuzzleLabel.setText( Integer.toString( numOfPuzzle ) );
			numOfSolvedPuzzleLabel.setText( Integer.toString( numOfSolvedPuzzle ) );
		}
		else 
		{
			for( int i = 0; i < 25; i++ )
				button[i].setEnabled(false);
			skipButton.setEnabled(false);
			numOfSolvedPuzzleLabel.setText( Integer.toString( numOfSolvedPuzzle ) );
			int option = JOptionPane.showConfirmDialog(null,"Game over! You solved " + Integer.toString(numOfSolvedPuzzle) +" puzzle(s).\nDo you want to restart the game?");
			numOfPuzzle = 0;
			numOfSolvedPuzzle = 0;
			switch(option)
			{
				case 0: startGame();
					break;
				case 1: System.exit(0); 
					break;
				case 2: startButton.setEnabled(true);
					break;
			}
		}
	}
	
	private void exitGame()
	{
		System.exit(0);
	}
	
	private void setPattern()
	{
		int[] temp = listOfPatterns.get(numOfPuzzle);
		for( int i = 0; i < temp.length; i++ )
			pattern[i] = temp[i];
	}
	
	private void updateToggleButtonPattern()
	{
		for( int i = 0; i < 25; i++ )
		{
			if( pattern[i] == 0 )
			{
				button[i].setIcon(offIcon);
				button[i].setSelected(false);
			}
			else
			{
				button[i].setIcon(onIcon);
				button[i].setSelected(true);
			}
		}
	}
	
	private void updateButton( JToggleButton toggleButton )
	{
		if( toggleButton.isSelected() )
		{
			toggleButton.setIcon(offIcon);
			toggleButton.setSelected(false);
		}
		else
		{
			toggleButton.setIcon(onIcon);
			toggleButton.setSelected(true);
		}
	}
	
	private void toggleButton( ActionEvent evt )
	{
		int pos = 0, num = 5, size = 25;
		JToggleButton toggleButton = (JToggleButton)evt.getSource();
		for( int i = 0; i < 25; i++ )
		{
			if( toggleButton == button[i] )
			{
				pos = i;
				break;
			}
		}
		if( button[pos].isSelected() ) toggleButton.setIcon(onIcon);
		else toggleButton.setIcon(offIcon);
		if( pos < num )
		{
			if( pos == 0 ) // upper left corner
			{
				updateButton(button[pos+1]);
				updateButton(button[pos+num]);
			}
			else if( pos == 4 ) // upper right corner
			{
				updateButton(button[pos-1]);
				updateButton(button[pos+num]);
			}
			else // upper
			{
				updateButton(button[pos-1]);
				updateButton(button[pos+1]);
				updateButton(button[pos+num]);
			}
		}
		else if( pos >= size-num )
		{
			if( pos == 20 ) // lower left corner
			{
				updateButton(button[pos+1]);
				updateButton(button[pos-num]);
			}
			else if( pos == 24 ) // lower right corner
			{
				updateButton(button[pos-1]);
				updateButton(button[pos-num]);
			}
			else // bottom
			{
				updateButton(button[pos-1]);
				updateButton(button[pos+1]);
				updateButton(button[pos-num]);
			}
		}
		else
		{
			if( pos % num == 0 ) // left
			{
				updateButton(button[pos+num]);
				updateButton(button[pos+1]);
				updateButton(button[pos-num]);
			}
			else if( pos % num == (num-1) )
			{
				updateButton(button[pos+num]);
				updateButton(button[pos-1]);
				updateButton(button[pos-num]);
			}
			else
			{
				updateButton(button[pos+num]);
				updateButton(button[pos+1]);
				updateButton(button[pos-1]);
				updateButton(button[pos-num]);
			}
		}
		if( isCleared() )
		{
			numOfSolvedPuzzle++;
			skipPuzzle();
		}
	}
	
	private boolean isCleared()
	{
		for( int i = 0; i < 25; i++ )
		{
			if( button[i].isSelected() )
				return false;
		}
		return true;
	}
	
	private void loadPatterns()
	{
		BufferedReader input = null;
		try 
		{
			input = new BufferedReader(new FileReader("pattern/pattern.txt"));
            String line;
			while ( (line = input.readLine()) != null ) 
			{
				int[] temp = new int[25];
				String[] tokens = line.split(",");
				for( int i = 0; i < tokens.length; i++ )
					temp[i] = Integer.parseInt(tokens[i]);
				listOfPatterns.add(temp);
			}
			if (input != null) 
				input.close();
		}
		catch( IOException e ) {}
	}

	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				new LightsOutPuzzle();
			}
		});
	}
}