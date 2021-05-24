package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.functions.Drink;
import game.dinosaurs.live.Brachiosaur;
import game.dinosaurs.live.Stegosaur;
import game.grounds.*;
import game.player.Player;
import game.player.exceptions.ModeExceptions;
import game.player.game_modes.Challenge;
import game.player.game_modes.GameMode;
import game.player.game_modes.SandBox;
import game.static_classes.Sky;
import game.vending_machine.VendingMachine;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		boolean playAnother=true;
		while(playAnother) {
			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());

			List<String> map = Arrays.asList(
					"................................................................................",
					"................................................................................",
					".....#######....................................................................",
					".....#_____#....................................................................",
					".....#_____#....................................................................",
					".....###.###....................................................................",
					"................................................................................",
					"......................................+++.......................................",
					"......................................++++......................................",
					"...................................+++++........................................",
					".....................................++++++.....................................",
					"......................................+++.......................................",
					".....................................+++........................................",
					"................................................................................",
					"............+++.................................................................",
					".............+++++..............................................................",
					"...............++........................................+++++..................",
					".............+++....................................++++++++....................",
					"............+++.......................................+++.......................",
					"................................................................................",
					".........................................................................++.....",
					"........................................................................++.++...",
					".........................................................................++++...",
					"..........................................................................++....",
					"................................................................................");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			List<String> map2 = Arrays.asList(
					"................................................................................",
					"................................................................................",
					"........................................................+++++++.................",
					"...........................................................++++.................",
					"..........................................................+++++.................",
					"................................................................................",
					"................................................................................",
					"................................................................................",
					"................................................................................",
					"...........+++++++..............................................................",
					"...........+++++++..............................................................",
					"...........+++++++..............................................................",
					"................................................................................",
					"................................................................................",
					"................................................................................",
					"................................................................................",
					".........................................................+++++..................",
					"....................................................++++++++....................",
					"......................................................+++.......................",
					"................................................................................",
					".........................................................................++.....",
					"........................................................................++.++...",
					".........................................................................++++...",
					"..........................................................................++....",
					"................................................................................");
			GameMap gameMap2 = new GameMap(groundFactory, map2);
			world.addGameMap(gameMap2);
			Sky.setRaining(false);
			Application.printMenu();
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter the number corresponding to chosen game mode : ");
			int choice = scanner.nextInt();
			GameMode gameMode = null;
			switch (choice) {
				case 1:
					gameMode = new SandBox();
					break;
				case 2:
					boolean correct = false;
					while (!correct) {
						System.out.print("Enter max number of eco points : ");
						int maxEcoPoints = scanner.nextInt();
						System.out.print("Enter max number of moves : ");
						int maxMoves = scanner.nextInt();
						try {
							gameMode = new Challenge(maxMoves, maxEcoPoints);
							correct = true;
						} catch (ModeExceptions e) {
							e.getMessage();
							System.out.println("Re-Enter");
						}
					}

			}
			Actor player = new Player("Player", '@', 100, gameMode);
			world.addPlayer(player, gameMap.at(40, 1));

			Drink drink = new Drink();

			// Place a pair of stegosaurs in the middle of the map
			gameMap.at(30, 12).addActor(new Stegosaur("Stegosaur", "male", drink));
			gameMap.at(40, 12).addActor(new Stegosaur("Stegosaur", "female", drink));
			gameMap.at(20, 12).addActor(new Brachiosaur("brachiosaur", "male", drink));
			gameMap.at(20, 16).addActor(new Brachiosaur("brachiosaur", "female", drink));
			gameMap.at(24, 15).addActor(new Brachiosaur("brachiosaur", "male", drink));
			gameMap.at(30, 15).addActor(new Brachiosaur("brachiosaur", "female", drink));
			gameMap.at(24, 12).setGround(new VendingMachine());
/*
		gameMap2.at(30, 12).addActor(new Stegosaur("Stegosaur","male"));
		gameMap2.at(40, 12).addActor(new Stegosaur("Stegosaur","female"));
		gameMap2.at(20,12).addActor(new Brachiosaur("brachiosaur","male"));
		gameMap2.at(20,16).addActor(new Brachiosaur("brachiosaur","female"));
		gameMap2.at(24,15).addActor(new Brachiosaur("brachiosaur","male"));
		gameMap2.at(30,15).addActor(new Brachiosaur("brachiosaur","female")); */
			gameMap2.at(24, 12).setGround(new VendingMachine());


			world.run();
			System.out.println("Do you want to play again(Y/N)");
			String choice2=scanner.nextLine();
			switch (choice2){
				case "Y":
					playAnother=true;
				case "N":
					playAnother=false;

			}
		}
	}

	private static void printMenu(){
		System.out.println("Choose Game Mode");
		System.out.println("1-Sandbox");
		System.out.println("2-Challenge");
	}
}
