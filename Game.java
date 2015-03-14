import java.util.Random;
import java.awt.Point;

public class Game{

	private int players;
	private int alivePlayers;
	private static Random random;
	private int gameSpeed;
	private boolean paused;
	private int over;
	private int ticks;
	private int[] playerColors;
	private Player[] player;
	private Cherry cherry;

	public Game(int[] playerColors, int gameSpeed){

		this.gameSpeed = gameSpeed;
		this.players = playerColors.length;
		this.paused = true;
		this.over = 0;
		this.ticks = 0;
		this.playerColors = playerColors;
		this.player = new Player[this.players];
		this.cherry = new Cherry();
		this.alivePlayers = 0;

	}

	public void mainGameLoop(){

		spawnSnakes();
		spawnCherry();
		paused = false;
		random = new Random();
		while(gameNotOver()){

			checkInsideOfBound();
			checkPlayerCollision();
			checkCherryCollision();
			updatePositions();

		}

	}

	public void spawnSnakes(){

		int i = 0;
		for(Player x : player){
			x.setPlayerId(i++);
			x.setHead(new Point(48,45));
			x.setTailLength(7);
			x.setTicks(0);
			x.setDirection(0);
			x.ClearSnakeParts();
			x.setStamina(75);
			x.setBombColor(playerColors[x.getPlayerId()]);
			x.setBombOnMap(false);
			x.setHasTeleporter(false);
			x.setHasLasers(10);
			x.setHasBomb(false);	
			this.alivePlayers++;
		}

	}

	public void spawnCherry(){

		cherry.setNewLocation();
		cherry.setNewType();

	}

	public void checkInsideOfBound(){

		for(Player x : player){

			if(
				x.getHead().x < 0 ||
			   	x.getHead().y < 0 ||
			   	x.getHead().x > 799 ||
			   	x.getHead().y > 599
			){
				System.out.println("Player "+x.getPlayerId()+" out of bound");
				this.alivePlayers--;
				x.kill();
			}
		}
	}

}
