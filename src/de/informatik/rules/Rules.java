package de.informatik.rules;

public class Rules {

	// Booleans
	public static boolean doppleOnGO = true;
	public static boolean gettingMortageWhileInPrison = true;
	public static boolean doubleMortageIfYouHaveWholeGroup = true;
	public static boolean tradingWhilePrison = true;
	public static boolean hypothekOnHousesAndHotels = true;
	public static boolean buyHotelDirectly = true;
	public static boolean moneypool = true;

	// Houses/Hotels and Hypothek
	public static int houses = 32;
	public static int hotels = 12;
	public static double hypothekDissolveRate = 0.1;
	public static double houseDissolveRate = 0.5;
	public static double hotelDissolveRate = 0.5;

	// Prison
	public static int prisonDeposit = 50;
	public static int roundsBlockedInPrision = 3;
	public static int howManyDoubletsToGoToPrison = 3;

	// Trainstations and Companies
	public static int mortageTrainStation = 25;
	public static int mortageOneCompanyMuplicatedEyes = 4;
	public static int mortageTwoCompanyMuplicatedEyes = 10;

	// General settings
	public static int delayAfterRound = 1;
	public static int minPlayer = 2;

}
