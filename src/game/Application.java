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
import game.Enemies.YhormTheGiant;
import game.VendorPackage.Vendor;
import game.bonfire.Bonfire;
import game.ground.Dirt;
import game.ground.Floor;
import weapon.StormRuler;

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
				"...................c.....................................c...............+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				"................c............................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#__FB___#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+........................................c.............++.........",
				"..............++.................................................++++++.........",
				"............+++...............c...................................++++..........",
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


		player = new Player("Unkindled (Player)", '@', 100000000);
		world.addPlayer(player, gameMap.at(6, 20));

		// Place Yhorm the Giant/boss in the map
//		gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
		gameMap.at(6,25).addActor(new YhormTheGiant());
		gameMap.at(7,25).addItem(new StormRuler());
		// Place a Hollow in the the map
		// FIXME: the Undead should be generated from the Cemetery
		gameMap.at(39,5).addActor(new Skeleton("Skeleton",39,5));
		gameMap.at(20,5).addActor(new Skeleton("Skeleton",20,5));
		gameMap.at(20,15).addActor(new Skeleton("Skeleton",20,15));
		gameMap.at(51,8).addActor(new Skeleton("Skeleton",51,8));
		gameMap.at(41,14).addActor(new Skeleton("Skeleton",41,14));
		gameMap.at(32,20).addActor(new Skeleton("Skeleton",32,20));
		gameMap.at(28,23).addActor(new Skeleton("Skeleton",28,23));
		world.run();

	}

	public static Player getPlayer() {
		return player;
	}

	public static GameMap getGameMap() {
		return gameMap;
	}
}

