package gameSystem;

public class Methods {

	private String name;
	private int hp = 100;
	private int energy = 50;
	private int energyRecovery = 10;
	private int shieldCost = 10;
	private int bluntCost = 10;
	private int bluntDamage = 25;
	private int slashCost = 35;
	private int slashDamage = 60;
	private int hpPotion = 15;
	private int energyPotion = 15;
	private int hpPotionInventory = 2;
	private int energyPotionInventory = 2;

	public Methods(String name, int hp, int energy,
			int energyRecovery, int shieldCost, int bluntCost, int bluntDamage, int slashCost, int slashDamage,
			int hpPotion, int energyPotion, int hpPotionInventory, int energyPotionInventory) {
		this.name = name;
		this.hp = hp;
		this.energy = energy;
		this.energyRecovery = energyRecovery;
		this.bluntCost = bluntCost;
		this.bluntDamage = bluntDamage;
		this.slashCost = slashCost;
		this.slashDamage = slashDamage;
		this.hpPotion = hpPotion;
		this.energyPotion = energyPotion;
		this.hpPotionInventory = hpPotionInventory;
		this.energyPotionInventory = energyPotionInventory;
	}

	public Methods (String name) {
		this.name = name;
		this.hpPotionInventory = 2; // Inicializa com 2 poções de vida
		this.energyPotionInventory = 2; // Inicializa com 2 poções de energia
	}

	public int getHpPotionInventory() {
		return hpPotionInventory;
	}
	public void setHpPotionInventory(int hpPotionInventory) {
		this.hpPotionInventory = hpPotionInventory;
	}
	public int getEnergyPotionInventory() {
		return energyPotionInventory;
	}
	public void setEnergyPotionInventory(int energyPotionInventory) {
		this.energyPotionInventory = energyPotionInventory;
	}
	public int getHpPotion() {
		return hpPotion;
	}
	public void setHpPotion(int hpPotion) {
		this.hpPotion = hpPotion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getShieldCost() {
		return shieldCost;
	}
	public void setShieldCost(int shieldCost) {
		this.shieldCost = shieldCost;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getEnergyRecovery() {
		return energyRecovery;
	}
	public void setEnergyRecovery(int energyRecovery) {
		this.energyRecovery = energyRecovery;
	}
	public int getBluntCost() {
		return bluntCost;
	}
	public void setBluntCost(int bluntCost) {
		this.bluntCost = bluntCost;
	}
	public int getBluntDamage() {
		return bluntDamage;
	}
	public void setBluntDamage(int bluntDamage) {
		this.bluntDamage = bluntDamage;
	}
	public int getSlashCost() {
		return slashCost;
	}
	public void setSlashCost(int slashCost) {
		this.slashCost = slashCost;
	}
	public int getSlashDamage() {
		return slashDamage;
	}
	public void setSlashDamage(int slashDamage) {
		this.slashDamage = slashDamage;
	}
	public int getEnergyPotion() {
		return energyPotion;
	}
	public void setEnergyPotion(int energyPotion) {
		this.energyPotion = energyPotion;
	}



	// Blunt Attack
	public void bluntAttackDMG (Methods target) {
		target.setHp(target.getHp() - bluntDamage);
	}
	public void bluntAttackENE (Methods target) {
		target.setEnergy(target.getEnergy() - bluntCost);
	}

	public void slashAttackDMG (Methods target) {
		target.setHp(target.getHp() - slashDamage);
	}
	public void slashAttackENE (Methods target) {
		target.setEnergy(target.getEnergy() - slashCost);
	}

	public void energyRecovery (Methods target) {
		target.setEnergy(target.getEnergy() + energyRecovery);
	}

	public void useShield (Methods target) {
		if (energy >= shieldCost) {
			target.setEnergy(target.getEnergy() - shieldCost);
		}
	}

	public void useHpPotion (Methods target) {
		target.setHp(target.getHp() + hpPotion);
		hpPotionInventory--; // Remove uma poção do inventário
	}


	public void useEnergyPotion (Methods target) {
		target.setEnergy(target.getEnergy() + energyPotion);
		energyPotionInventory--; // Remove uma poção do inventário
	}

	public String toString() {
		return "HP: " + hp + "\n" +
				"Energy: " + energy + "\n" +
				"Blunt cost: " + bluntCost + "\n" +
				"Slash cost: " + slashCost + "\n" +
				"Shield cost: " + shieldCost + "\n" +
				"Blunt damage: " + bluntDamage + "\n" +
				"Slash damage: " + slashDamage + "\n" +
				"Energy recovery: " + energyRecovery + "\n" +
				"Life potion: " + hpPotion + "\n" +
				"Energy potion: " + energyPotion + "\n";
	}
}