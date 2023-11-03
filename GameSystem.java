package gameSystem;

import java.util.Scanner;

public class GameSystem {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Methods p1 = new Methods("Player 1");
		Methods p2 = new Methods("Player 2");

		boolean endgame = false;

		while (endgame == false) {


			//ESCOLHA DE ATAQUE JOGADOR 1:
			boolean player1CanAttack = true;

			while (true) {
				int attkOptP1 = -1; // variavel para controle de repetição de opção errada
				while (attkOptP1 < 1 || attkOptP1 > 4) { // while para erro fora das opções determinadas

					System.out.println("\n" + p1.getName() + " ATTACKING PHASE" + "\n"); 
					System.out.println("Choose your strategy: " + "\n");
					System.out.println("1: Blunt Attack (Damage = " + p1.getBluntDamage() +  " | Energy cost = " + p1.getBluntCost() + ")" + "\n");
					System.out.println("2: Slash Attack (Damage = " + p1.getSlashDamage() + " | Energy cost = " + p1.getSlashCost() + ")" + "\n");
					System.out.println("3: Rest (Recovers = " + p1.getEnergyRecovery() + " of energy)" + "\n");
					System.out.println("4: Forfeit Match" + "\n");

					attkOptP1 = scanner.nextInt();

					// CHOICE ENERGY VALIDATION AND MESSAGE:
					if (attkOptP1 == 4) {
						System.out.println(p1.getName() + " has forfeit the match... " + p2.getName() + " won!" + "\n");
						endgame = true;
						break;
					}


					if (attkOptP1 == 3) {
						System.out.println(p1.getName() + " chooses to rest..." + "\n");
						if (p1.getEnergy() <= 35) {
							System.out.println("And recovers = " + p1.getEnergyRecovery() + " of energy" + "\n");
							p1.energyRecovery(p1);
							break;
						}
					}
					else {


						switch (attkOptP1) {

						case 1: 
							if (player1CanAttack) {
								if (p1.getEnergy() >= p1.getBluntCost()) {
									System.out.println("The player forwards a blunt attack..." + "\n");
									break;
								}
								else {
									System.out.println(p1.getName() + " didn't had enough energy for the attack, and lost its turn.");
									player1CanAttack = false;
									break;
								}
							}
							break;
						case 2: 
							if (player1CanAttack) {
								if (p1.getEnergy() >= p1.getSlashCost()) {
									System.out.println("The player forwards a slash attack..." + "\n");
									break;
								}

								else {	
									System.out.println(p1.getName() + " didn't had enough energy for the attack, and lost its turn.");
									player1CanAttack = false;
								}
							}
							break;
						default:
							System.out.println("Invalid option. You've lost your turn." + "\n");
						}





						//ESCOLHA DE DEFESA JOGADOR 2:
						System.out.println("\n" + p2.getName() + " DEFENSE PHASE" + "\n"); 
						System.out.println("Choose your strategy: " + "\n");
						System.out.println("1: Shield (Energy cost = " + p2.getShieldCost() + ")" + "\n");
						System.out.println("2: HP Potion (Recovers = " + p2.getHpPotion() + " of HP)" + "\n");
						System.out.println("3: Energy Potion (Recovers = " + p2.getEnergyPotion() + " of energy" + "\n");
						System.out.println("4: Rest (Recovers = " + p2.getEnergyRecovery() + " of energy)" + "\n");

						int defOptP2 = scanner.nextInt();

						switch (defOptP2) {
						case 1:
							System.out.println("The player raises his shield in defence..." + "\n");
							break;
						case 2: 
							if (p2.getHpPotionInventory() > 0) {
								System.out.println("The player chooses to grab a HP potion from his inventory." + "\n");
							}
							else {
								System.out.println("Sorry, you are out of HP potions." + "\n");
							}
							break;
						case 3:
							if (p2.getEnergyPotionInventory() > 0) { 
								System.out.println("The player chooses to grab a Energy potion from his inventory." + "\n");
								break;
							}
							else {
								System.out.println("Sorry, you are out of energy potions." + "\n");
							}
							break;
						case 4:
							System.out.println("The player chooses to rest..." + "\n");
							break;
						default:
							System.out.println("Invalid option." + "\n");
							break;
						}




						// GAMEFLOW

						if (attkOptP1 == 1 && defOptP2 == 1) { 
							System.out.println(p1.getName() + " tries a blunt attack, but " + p2.getName() + " blocks with his shield!" + "\n");
							p1.bluntAttackENE(p1);
							p2.useShield(p2);
						}

						if (attkOptP1 == 1 && defOptP2 == 2) { 
							System.out.println(p1.getName() + " tries a blunt attack and succeeds! " + "\n");
							p1.bluntAttackDMG(p2);
							p1.bluntAttackENE(p1);
							if (p2.getHpPotionInventory() > 0) {
								System.out.println("And " + p2.getName() + " uses an potion to recover HP! " + "\n");
								p2.useHpPotion(p2);
							}
						}
						if (attkOptP1 == 1 && defOptP2 == 3) { 
							System.out.println(p1.getName() + " tries a blunt attack and succeeds! " + "\n");
							p1.bluntAttackDMG(p2);
							p1.bluntAttackENE(p1);
							if (p2.getEnergyPotionInventory() > 0) {
								System.out.println("And " + p2.getName() + " uses an potion to recover energy! " + "\n");
								p2.useEnergyPotion(p2);
							}
						}
						if (attkOptP1 == 1 && defOptP2 == 4) {
							System.out.println(p1.getName() + " tries a blunt attack and succeeds!" + "\n" +
									"And " + p2.getName() + " has rested.");
							p1.bluntAttackDMG(p2);
							p1.bluntAttackENE(p1);
							p2.energyRecovery(p2);
							if (p2.getEnergy() <= 49) {
								System.out.println("And " + p2.getName() + " recovers energy from resting." + "\n");
								p2.energyRecovery(p2);

							}
							else if (p2.getEnergy() == 50) {
								System.out.println("Player rested, but his energy was already full.");
							}
						}

						if (attkOptP1 == 2 && defOptP2 == 1) {
							System.out.println(p1.getName() + " tries a slash attack, but " + p2.getName() + " blocks with his shield!" + "\n");
							p1.slashAttackENE(p1);
							p2.useShield(p2);
						}
						if (attkOptP1 == 2 && defOptP2 == 2) { 
							System.out.println(p1.getName() + " tries a slash attack and succeeds! " + "\n");
							p1.slashAttackDMG(p2);
							p1.slashAttackENE(p1);
							if (p2.getHpPotionInventory() > 0) {
								System.out.println("And " + p2.getName() + " uses an potion to recover HP! " + "\n");
								p2.useHpPotion(p2);
							}
						}
						if (attkOptP1 == 2 && defOptP2 == 3) { 
							System.out.println(p1.getName() + " tries a slash attack and succeeds! " + "\n");
							p1.slashAttackDMG(p2);
							p1.slashAttackENE(p1);
							if (p2.getEnergyPotionInventory() > 0) {
								System.out.println("And " + p2.getName() + " uses an potion to recover energy! " + "\n");
								p2.useEnergyPotion(p2);
							}
						}
						if (attkOptP1 == 2 && defOptP2 == 4) {
							p1.energyRecovery(p1);

							System.out.println(p1.getName() + " tries a slash attack and succeeds!" + "\n" +
									"And " + p2.getName() + " has rested.");
							p1.slashAttackDMG(p2);
							p1.slashAttackENE(p1);
							p2.energyRecovery(p2);

						}


						// Verificação de final de jogo após cada rodada:
						if (p2.getHp() <= 0) {
							endgame = true;

							System.out.println(p1.getName() + " has defeated " + p2.getName() + " and won the match!" + "\n");
							break;
						}
						else {

							// Stats pós rodada:
							//JOGADOR 1:
							System.out.println("STATS POST ROUND: " + "\n");
							System.out.println(p1.getName() + ":" + "\n" +
									"HP: " + p1.getHp() + "\n" +
									"Energy: " + p1.getEnergy() + "\n");

							//JOGADOR 2:
							System.out.println(
									p2.getName() + ":" + "\n" +
											"HP: " + p2.getHp() + "\n" +
											"Energy: " + p2.getEnergy() + "\n");
						}
					}
				}

				if (endgame) {
					break;
				}


				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				//ESCOLHA DE ATAQUE JOGADOR 2:
				boolean player2CanAttack = true;

				int attkOptP2 = -1; // variavel para controle de repetição de opção errada
				while (attkOptP2 < 1 || attkOptP2 > 4) { // while para erro fora das opções determinadas

					System.out.println("\n" + p2.getName() + " ATTACKING PHASE" + "\n"); 
					System.out.println("Choose your strategy: " + "\n");
					System.out.println("1: Blunt Attack (Damage = " + p2.getBluntDamage() +  " | Energy cost = " + p2.getBluntCost() + ")" + "\n");
					System.out.println("2: Slash Attack (Damage = " + p2.getSlashDamage() + " | Energy cost = " + p2.getSlashCost() + ")" + "\n");
					System.out.println("3: Rest (Recovers = " + p2.getEnergyRecovery() + " of energy)" + "\n");
					System.out.println("4: Forfeit Match" + "\n");

					attkOptP2 = scanner.nextInt();

					// CHOICE ENERGY VALIDATION AND MESSAGE:
					if (attkOptP2 == 4) {
						System.out.println(p2.getName() + " has forfeit the match... " + p1.getName() + " won!" + "\n");
						endgame = true;
						break;
					}


					if (attkOptP2 == 3) {
						System.out.println(p2.getName() + " chooses to rest..." + "\n");
						if (p2.getEnergy() <= 35) {
							System.out.println("And recovers = " + p2.getEnergyRecovery() + " of energy" + "\n");
							p2.energyRecovery(p2);
							break;
						}
					}
					else {

						switch (attkOptP2) {

						case 1: 
							if (player2CanAttack) {
								if (p2.getEnergy() >= p2.getBluntCost()) {
									System.out.println("The player forwards a blunt attack..." + "\n");
									break;
								}
								else {
									System.out.println(p2.getName() + " didn't had enough energy for the attack, and lost its turn.");
								}
							}
							break;
						case 2: 
							if (player2CanAttack) {
								if (p2.getEnergy() >= p2.getSlashCost()) {
									System.out.println("The player forwards a slash attack..." + "\n");
									break;
								}
								else {
									System.out.println(p2.getName() + " didn't had enough energy for the attack, and lost its turn.");

								}
							}
							break;
						case 4: 
							System.out.println(p2.getName() + " has forfeit the match... " + p1.getName() + " won!" + "\n");
							endgame = true;
							break;
						default:
							System.out.println("Invalid option. You've lost your turn." + "\n");
							break;
						}


						//ESCOLHA DE DEFESA JOGADOR 1:
						System.out.println("\n" + p1.getName() + " DEFENSE PHASE" + "\n"); 
						System.out.println("Choose your strategy: " + "\n");
						System.out.println("1: Shield (Energy cost = " + p1.getShieldCost() + ")" + "\n");
						System.out.println("2: HP Potion (Recovers = " + p1.getHpPotion() + " of HP)" + "\n");
						System.out.println("3: Energy Potion (Recovers = " + p1.getEnergyPotion() + " of energy)" + "\n");
						System.out.println("4: Rest (Recovers = " + p1.getEnergyRecovery() + " of energy)" + "\n");

						int defOptP1 = scanner.nextInt();

						switch (defOptP1) {
						case 1:
							System.out.println("The player raises his shield in defence..." + "\n");
							break;
						case 2: 
							if (p1.getHpPotionInventory() > 0) {
								System.out.println("The player chooses to grab a HP potion from his inventory." + "\n");
							}
							else {
								System.out.println("Sorry, you are out of HP potions." + "\n");
							}
							break;
						case 3:
							if (p1.getEnergyPotionInventory() > 0) { 
								System.out.println("The player chooses to grab a Energy potion from his inventory." + "\n");
								break;
							}
							else {
								System.out.println("Sorry, you are out of energy potions." + "\n");
							}
						case 4:
							System.out.println("The player chooses to rest..." + "\n");
							break;
						default:
							System.out.println("Invalid option. You've lost your turn." + "\n");
						}

						// GAMEFLOW

						if (attkOptP2 == 1 && defOptP1 == 1) { 
							System.out.println(p2.getName() + " tries a blunt attack, but " + p1.getName() + " blocks with his shield!" + "\n");
							p2.bluntAttackDMG(p1);
							p2.bluntAttackENE(p2);
							p1.useShield(p1);
						}

						if (attkOptP2 == 1 && defOptP1 == 2) { 
							System.out.println(p2.getName() + " tries a blunt attack and succeeds! " + "\n");
							p2.bluntAttackDMG(p1);
							p2.bluntAttackENE(p2);
							if (p1.getHpPotionInventory() > 0) {
								System.out.println("And " + p1.getName() + " uses an potion to recover HP! " + "\n");
								p1.useHpPotion(p1);
							}
						}
						if (attkOptP2 == 1 && defOptP1 == 3) { 
							System.out.println(p2.getName() + " tries a blunt attack and succeeds! " + "\n");
							p2.bluntAttackDMG(p1);
							p2.bluntAttackENE(p2);
							if (p1.getEnergyPotionInventory() > 0) {
								System.out.println("And " + p1.getName() + " uses an potion to recover energy! " + "\n");
								p1.useEnergyPotion(p1);
							}
						}
						if (attkOptP2 == 1 && defOptP1 == 4) {
							System.out.println(p2.getName() + " tries a blunt attack and succeeds!" + "\n" +
									"And " + p1.getName() + " has rested.");
							p2.bluntAttackDMG(p1);
							p2.bluntAttackENE(p2);
							p1.energyRecovery(p1);
						}

						if (attkOptP2 == 2 && defOptP1 == 1) {
							System.out.println(p2.getName() + " tries a slash attack, but " + p1.getName() + " blocks with his shield!" + "\n");
							p2.slashAttackENE(p2);
							p1.useShield(p1);
						}
						if (attkOptP2 == 2 && defOptP1 == 2) { 
							System.out.println(p2.getName() + " tries a slash attack and succeeds! " + "\n");
							p2.slashAttackDMG(p1);
							p2.slashAttackENE(p2);
							if (p1.getHpPotionInventory() > 0) {
								System.out.println("And " + p1.getName() + " uses an potion to recover HP! " + "\n");
								p1.useHpPotion(p1);
							}
						}
						if (attkOptP2 == 2 && defOptP1 == 3) {
							System.out.println(p2.getName() + " tries a slash attack and succeeds! " + "\n" +
									"And " + p1.getName() + " uses an potion to recover energy! " + "\n");
							p2.slashAttackDMG(p1);
							p2.slashAttackENE(p2);
							p1.useEnergyPotion(p1);
						}
						if (attkOptP2 == 2 && defOptP1 == 4) {
							System.out.println(p2.getName() + " tries a slash attack and succeeds!" + "\n" +
									"And " + p1.getName() + " has rested.");
							p2.slashAttackDMG(p1);
							p2.slashAttackENE(p2);
							p1.energyRecovery(p1);
						}
					}

					// Verificação de final de jogo após cada rodada:
					if (p1.getHp() <= 0) {
						endgame = true;
						System.out.println(p2.getName() + " has defeated " + p1.getName() + " and won the match!" + "\n");
						break;
					}

					// Stats pós rodada:
					//JOGADOR 1:
					System.out.println("STATS POST ROUND: " + "\n");
					System.out.println(p1.getName() + ":" + "\n" +
							"HP: " + p1.getHp() + "\n" +
							"Energy: " + p1.getEnergy() + "\n");

					//JOGADOR 2:
					System.out.println(
							p2.getName() + ":" + "\n" +
									"HP: " + p2.getHp() + "\n" +
									"Energy: " + p2.getEnergy() + "\n");


				}
				if (endgame) {
					break;
				}
			}
		}


		scanner.close();
	}
}