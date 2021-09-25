package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.Enemies.Skeleton;
import game.Enemies.Undead;
import game.VendorPackage.Vendor;
import game.bonfire.Bonfire;
import game.ground.Dirt;
import game.ground.Floor;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {
	private static Player player;
	private static GameMap gameMap;
	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Bonfire(),new Vendor(), new Cemetery());

		List<String> map = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				"...................c.....................................................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				"................c............................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#__FB___#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............................+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.............................................++");
		gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);


		player = new Player("Unkindled (Player)", '@', 100);
		world.addPlayer(player, gameMap.at(38, 12));

		// Place Yhorm the Giant/boss in the map
		gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));

		// Place a Hollow in the the map
		// FIXME: the Undead should be generated from the Cemetery
		gameMap.at(39,8).addActor(new Skeleton("Skeleton"));

		world.run();

	}

	public static Player getPlayer() {
		return player;
	}

	public static GameMap getGameMap() {
		return gameMap;
	}
}

