package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.Enemies.Skeleton;
import game.Enemies.YhormTheGiant;
import game.VendorPackage.Vendor;
import game.bonfire.Bonfire;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Valley;
import game.ground.Wall;
import game.ground.fogwall.FogWall;
import weapon.StormRuler;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {
	private static Player player;
	private static GameMap profaneCapitalMap;
	private static GameMap anorLondoMap;
	private static ArrayList<String> bonfirenames = new ArrayList<>();

	public static void main(String[] args) {

		World world = new World(new Display());
		FancyGroundFactory profaneCapitalGroundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),new Vendor(), new Cemetery(),new FogWall());
		FancyGroundFactory anorLondoGroundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),new Vendor(), new Cemetery(),new FogWall());

		List<String> profaneCapital = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..........c...................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				".........................................................c...............+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				"....c........................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#__F____#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...............c...................................++++..........",
				"+..................................................................++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............................+..+.....",
				"_..._....._._#.++......................+...................c...............+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++............===============..................++");

		List<String> anorLondo = Arrays.asList(
				".............................................===============....................",
				".....................................++.............................############",
				"...............+++........+++.........++............................###_________",
				"..............+++++.....++++++......................................_________F__",
				".............+++++++..+++++++.........c....................+++......###_________",
				"....+++.......++++++++++++++...............................++++......###########",
				"......+........+++++++++++++..................................++................",
				"................++++++++++.........++.........................+++...............",
				"..................+++++++...........++..........................+++.............",
				"....................++++...........++..........+..................+.............",
				"......................+........................+++................+++...........",
				"................+..............................++.................++............",
				"..............++.................................+++............................",
				"............++....................................++............c...............",
				".............++...................................++++..........................",
				".................................................+++............................",
				".....##################____####...................+++...........................",
				".....#.....+........#.........#......................++................c........",
				".....#...........#.......#....#.......................+.........................",
				".....#...__.#............#....#.......................+.........................",
				".....#..............#.........#.................................................",
				".....#....#...................#................c................................",
				".....###################___####.................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................");

		profaneCapitalMap = new GameMap(profaneCapitalGroundFactory, profaneCapital);
		profaneCapitalMap.at(38,11).setGround(new Bonfire("Firelink Shrine Bonfire"));
		anorLondoMap = new GameMap(anorLondoGroundFactory,anorLondo);
		anorLondoMap.at(75,3).setGround(new Bonfire("Anor Londo Bonfire"));
		world.addGameMap(profaneCapitalMap);
		world.addGameMap(anorLondoMap);

		player = new Player("Unkindled (Player)", '@', 1000000);
//		world.addPlayer(player, profaneCapitalMap.at(38,12));
		world.addPlayer(player, profaneCapitalMap.at(8,25));
//		world.addPlayer(player, anorLondoMap.at(75, 3));


		// Place Yhorm the Giant/boss in the map
//		gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
		profaneCapitalMap.at(6,25).addActor(new YhormTheGiant(6,25));
//		for (int i = 20; i < 50 ;i++){
//			for (int j = 10; j < 20; j ++)
//			anorLondoMap.at(i,j).addActor(new YhormTheGiant(i,j));
//
//		}
		profaneCapitalMap.at(7,25).addItem(new StormRuler(profaneCapitalMap));
		// Place a Hollow in the the map
		// FIXME: the Undead should be generated from the Cemetery
		profaneCapitalMap.at(39,5).addActor(new Skeleton("Skeleton",39,5));
		profaneCapitalMap.at(20,5).addActor(new Skeleton("Skeleton",20,5));
		profaneCapitalMap.at(20,15).addActor(new Skeleton("Skeleton",20,15));
		profaneCapitalMap.at(51,8).addActor(new Skeleton("Skeleton",51,8));
		profaneCapitalMap.at(41,14).addActor(new Skeleton("Skeleton",41,14));
		profaneCapitalMap.at(32,20).addActor(new Skeleton("Skeleton",32,20));
		profaneCapitalMap.at(28,23).addActor(new Skeleton("Skeleton",28,23));
		world.run();

	}

	/**
	 * getter for the player instance
	 * @return instance of the player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * getter for the map of Profane Capital
	 * @return GameMap representing the profane capital
	 */
	public static GameMap getProfaneCapital() {
		return profaneCapitalMap;
	}

	/**
	 * Getter for the map of Anor Londo
	 * @return GameMap representing Anor Londo
	 */
	public static GameMap getAnorLondoMap() {
		return anorLondoMap;
	}

	/**
	 * Getter for the name of bonfires
	 * @return name of bonfires on the map
	 */
	public static ArrayList<String> getBonfireNames() {
		return bonfirenames;
	}
}

