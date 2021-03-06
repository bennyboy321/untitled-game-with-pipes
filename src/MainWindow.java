import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** The main window of the game
*/
public class MainWindow extends JFrame
{
	/** manages the rendering of the tiles
	*/
	private TilesRenderer tiles;


	/** manages the logical state of the game
	*/
	private Game game;


	/** repaints the window based on a timer
	*/
	public static class Scheduler implements ActionListener
	{
		private Timer timer;
		private MainWindow window;

		/** initialise the scheduler
		@param setFPS the frames per second to set the scheduler to follow
		@param setWindow the window object to call every 1/FPS seconds
		*/
		public Scheduler(int setFPS, MainWindow setWindow)
		{
			window = setWindow;

			int delay = 1000 / setFPS; // in milliseconds
			timer = new Timer(delay, this);
			timer.setRepeats(true);
		}
		/** start the scheduler (main loop)
		*/
		public void start() { timer.start(); }
		/** see MainWindow.tick()
		*/
		public void actionPerformed(ActionEvent e)
		{
			window.tick();
		}
	}

	/** initialise the window
	@param setTitleString set the window's title
	*/
	public MainWindow(String setTitleString)
	{
		super();

		setTitle(setTitleString);
		setSize(640, 480);
		setResizable(false);
		setLocationRelativeTo(null); // centers the window on the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setupScene();

		setVisible(true);
	}

	/** Create, setup and add content to be rendered
	*/
	public void setupScene()
	{
		game = new Game();

		tiles = new TilesRenderer(game.tiles, 64/*tileSize*/);

		add(tiles);
	}

	
	/** Update the game state and re-draw
	*/
	public void tick()
	{
		game.tick(); // update game state

		tiles.repaint();
		Toolkit.getDefaultToolkit().sync();
	}

}

