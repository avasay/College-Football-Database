package GenerateBogus;
/* TEXAS A&M UNIVERSITY
 * DEPARTMENT OF COMPUTER SCIENCE
 * FALL 2013
 * COURSE:	CSCSE 608 Database Project
 * NAME:	College Football Database
 * AUTHOR:	Alejandrio Vasay
 * 
 * DESCRIPTION:
 * This program generates synthetic data for 11 tables. 
 * 
 * DETAILS:
 * The two most important methods of this class are createName(int) and createNumber(int).
 * Both methods return a random string, the length of which is passed as a parameter.
 * The createName(15) method will give you, for instance, "7bDN2w15yBzL0o4", while 
 * createNumber(10) method will give you a string that is all numbers, for instance, 
 * "5332118749".
 * 
 * I use the createName method to generate random strings for table columns such as
 * FirstName, LastName, SchoolName, Address, City, State, and so on. I use createNumber 
 * method to generate random number strings for columns such as SSN, Age, Weight, Height,
 * ID, Score, year, and so on.
 *  
 * This program will create 11 text files which correspond to 11 tables in the database.
 * An example of the content of the file that corresponds to ConferenceMembership table 
 * looks like this:
 * 
 * mB6CD,zo59m0jGDXpBGAV,ex,jE03cZ2jEkYh98n,1947
 * 
 * Each field is delimited by comma. Of course, each table has different number of columns. 
 * For example, the file that corresponds to Players table contains the following line, 
 * the first field is SSN, and the last fields are Weight, Height and Age:
 * 
 * 885759180,XpkKdq4dEIg5lhf,mnZZJwWrYqc30pg,IngMhtaA8DHlZIqK304C0rmP7,Pk8kMq9JOGYkmyvy2RgOVD37yYw,WKUarnnBgF,409,393,35
 * 
 * The difference in the types of column and number of columns is the reason why I created 
 * 11 methods so that each method can handle these differences.
 * 
 * I will use LOAD DATA LOCAL INFILE command to load these text files into each of the 
 * database tables.
 * 
 * Lastly, to implement the createName() and createNumber() methods, I use the java utility 
 * function Random. Basically, to generate a random string, I create an
 * array of characters with all alphabets in it, lowercase and uppercase.
 * Then I call the Random utility to generate a random number
 * that I can then use to pick an index of the array and repeat 
 * this in however many characters I want in my string. For 
 * numeric strings(or those strings that contain only numbers), 
 * the process is the same except my array contains only numbers.	
 * 
 * 
*/
import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class GenerateRandomString {
	
	// GLOBAL VARIABLES

	/*
	 * The following ArrayList are used for storing SSN, SchoolName, and 
	 * Conferences data, which, in my database, are primary keys, and 
	 * referenced by foreign keys in other table. Note: For Part 2
	 * of the project, I have foreign key constraints, as well as 
	 * trigger constraints.
	 * 
	 * To put it in other words, I can't use random synthetic data 
	 * in tables that references data in another table.
	 * 
	 */
	static ArrayList<String> ssnList = new ArrayList<String>();
	static ArrayList<String> schoolList = new ArrayList<String>();
	static ArrayList<String> confList	= new ArrayList<String>();
	
	/*
	 * Used in the for loop for generating thousands and hundreds tuples.
	 */
	private static final int FIVE_HUNDRED = 500;
	private static final int ONE_THOUSAND = 1000;
	private static final int TWO_THOUSAND = 2000;
	
	
	public static void printSSNList() {
		
		for(int i=0; i<ssnList.size(); i++) {
			System.out.println(ssnList.get(i));
		}
	}
	public static void printschoolList() {
		
		for(int i=0; i<schoolList.size(); i++) {
			System.out.println(schoolList.get(i));
		}
	}

	/*
	 *  MAIN PROGRAM
	 */
	public static void main(String[] args) {
	try {	
		
		
		
		System.out.println("Hello World!");
		System.out.println(returnRandomDate());
		
		/*
		* Create Files in local drive. These files will be
		* used later for loading the synthetic data into
		* the database using the MySQL command
		* LOAD DATA LOCAL INFILE INTO TABLE TableName.
		*/
                
		FileWriter fw = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\PlayersFile.txt", true);
		FileWriter teamFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\TeamFile.txt", true);
		FileWriter gameFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\GameFile.txt", true);
		FileWriter confFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\ConfFile.txt", true);
		FileWriter defFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\DefenseFile.txt", true);
		FileWriter offFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\OffenseFile.txt", true);
		FileWriter kickFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\KickFile.txt", true);
		FileWriter playerTeamFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\PlayerTeamFile.txt", true);
		FileWriter playerPerfFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\PlayerPerfFile.txt", true);
		FileWriter teamGameFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\TeamGameFile.txt", true);
		FileWriter confMembersFile = new FileWriter("C:\\Users\\Rio\\Documents\\608 - Database\\Github\\JavaApplication1\\data\\ConfMembersFile.txt", true);

		
		/*
		* Create Synthetic Data for "Players" Table
		*/
		writePlayersFile(fw);
		fw.close();
		
		/*
		* Create Synthetic Data for "Teams" Table
		*/
		writeTeamsFile(teamFile);
		teamFile.close();
		 
		/*
		* Create Synthetic Data for "Games" Table
		*/
		writeGamesFile(gameFile);
		gameFile.close();
		
		/*
		* Create Synthetic Data for "Conferences" Table
		*/
		writeConferencesFile(confFile);
		confFile.close();
		
		/*
		* Create Synthetic Data for "DefensePerformance" Table
		*/
		writeDefensePerformanceFile(defFile);
		defFile.close();
		
		/*
		* Create Synthetic Data for "OffensePerformance" Table
		*/
		writeOffensePerformanceFile(offFile);
		offFile.close();
		
		/*
		* Create Synthetic Data for "KickerPerformance" Table
		*/
		writeKickerPerformanceFile(kickFile);
		kickFile.close();
		
		/*
		* Create Synthetic Data for "PlayerInTeam" Table
		*/
		writePlayerInTeamFile(playerTeamFile);
		playerTeamFile.close();
		
		/*
		* Create Synthetic Data for "PlayerPerformance" Table
		*/
		writePlayerPerformanceFile(playerPerfFile);
		playerPerfFile.close();
		
		/*
		* Create Synthetic Data for "TeamInGame" Table
		*/
		writeTeamInGameFile(teamGameFile);
		teamGameFile.close();
		
		/*
		* Create Synthetic Data for "ConferenceMembership" Table
		*/
		writeConfMembersFile(confMembersFile);
		confMembersFile.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	
	/*
	 * This method generates synthetic data for SSN,
	 * FirstName, LastName, Address, City, State, Phone, Weight, 
	 * Height, and Age, which will be used for "Players" table. 
	 * It also takes a file pointer as 
	 * a parameter, and writes the data into the file. 
	 */
	public static void writePlayersFile(FileWriter fw) {
		try {	
			
			// Creating 2,000 tuples for Players table
			for(int i=0; i < TWO_THOUSAND; i++) {
				String SSN = createNumber(9);
				
				/*
				 * Store the Social Security Number(SSN) into an array list
				 * so other methods that need SSN
				 * can use them. Important for referential integrity.
				 * 
				 * It is also important that this method is called first
				 * before calling the other methods that need this array list.
				 */
				ssnList.add(SSN);
				String firstname = createName(15);
				String lastname = createName(15);
				String address = createName(25);
				String city = createName(25);
				String state = createName(2);
				String phone = createNumber(10);
				String weight = createNumber(3);
				String height = createNumber(3);
				String age = createNumber(2);
						
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(SSN + sp + firstname + sp + lastname + sp 
						+address + sp + city + sp + state + sp + phone +
						sp + weight + sp + height + sp + age + "\r\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method generates synthetic data for SchoolName, NickName,
	 * City, State, Mascot, Stadium, HeadCoach, DefensiveCoordinator, 
	 * OffensiveCoordinator, which will be used "Teams" table. 
	 * It also takes a file pointer as 
	 * a parameter, and writes the data into the file. 
	 */
	public static void writeTeamsFile(FileWriter fw) {
		try {
			
			// Creating 2,000 tuples for Teams table.
			for(int i=0; i < TWO_THOUSAND; i++) {
				String School = createName(15);
				
				/*
				 * Store the school names into an array list
				 * so other methods that need school name
				 * can use them. Important for referential integrity.
				 * 
				 *  It is also important that this method is called first
				 * before calling the other methods that need this array list.
				 * 
				 */
				schoolList.add(School);
				
				String Nick = createName(10);
				String City = createName(10);
				String State = createName(2);
				String Mascot = createName(10);
				String Stadium = createName(10);
				String Coach = createName(20);
				String Coord1 = createName(15);
				String Coord2 = createName(15);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(School + sp + Nick + sp + City + sp 
						+ State + sp + Mascot + sp+ Stadium + sp + Coach +
						sp + Coord1 + sp + Coord2 + "\r\n");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * This method generates synthetic data for Place, NickName,
	 * City, State, Mascot, Stadium, HeadCoach, DefensiveCoordinator, 
	 * OffensiveCoordinator, which will be used for "Games" table. 
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writeGamesFile(FileWriter fw) {
		try {	
			
			// Creating 500 tuples for Games table
			Random rng = new Random();
			for(int i=0; i < FIVE_HUNDRED; i++) {
				String Place = createName(15);
				/*
				 * Access the array list so we can use the stored
				 * school names for our file instead of generating
				 * a new synthetic data. This makes sure
				 * referential integrity is followed when I load
				 * these data in the database.
				 */
				String Home = schoolList.get(rng.nextInt(ONE_THOUSAND));
				String Visiting = schoolList.get(rng.nextInt(ONE_THOUSAND));
				String HomeScore = createNumber(2);
				String VisitingScore = createNumber(2);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write("DEFAULT" + sp + returnRandomDate() + sp + Place + sp 
						+ "12:30:00" + sp + Home + sp+ Visiting + sp + HomeScore +
						sp + VisitingScore + "\r\n");
			}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
	}
	
	/* 
	 * This method generates synthetic data for conference NickName, 
	 * FullName, Division, Headquarters, and year Founded, 
	 * which will be used for "Conferences" table. 
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writeConferencesFile(FileWriter fw) {
		try {	
			for(int i=0; i < 10; i++) {
			
				String Nick = createName(5);
				
				/*
				 * Store the conference names into an array list
				 * so other methods that need conference names
				 * can use them. Important for referential integrity.
				 * 
				 *  It is also important that this method is called first
				 * before calling the other methods that need this array list.
				 * 
				 */
				confList.add(Nick);
				String Full = createName(15);
				String Division = createName(2);
				String Head = createName(15);
				String Founded = returnRandomDate();
				Founded = Founded.substring(0, 4);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(Nick + sp 
						+ Full + sp + Division + sp+ Head + sp + Founded + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/* 
	 * This method generates "numeric" data for the players
	 * performance in a game, 
	 * which will be used for "DefensivePerformance" table. 
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writeDefensePerformanceFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				String GameID =createNumber(1);
				
				// GameID should not be zero.
				while(GameID == "0") {
					GameID		= createNumber(1);
				}
				
				/*
				 * Let's use the already generated SSN from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String SSN = ssnList.get(rng.nextInt(10));
				String PassAllow 	= createNumber(3);
				String RushAllow 	= createNumber(3);
				String Sacks 		= createNumber(1);
				String Intercept 	= createNumber(1);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(GameID + sp + SSN + sp + PassAllow + sp+ RushAllow + sp + 
						Sacks + sp + Intercept + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/* 
	 * This method generates "numeric" data for the players
	 * performance in a game, 
	 * which will be used for "OffensivePerformance" table. 
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writeOffensePerformanceFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				String GameID =createNumber(1);
				
				// GameID should not be zero.
				while(GameID == "0") {
					GameID		= createNumber(1);
				}
				/*
				 * Let's use the already generated SSN from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String SSN = ssnList.get(rng.nextInt(10));
				String Pass 	= createNumber(3);
				String Rush 	= createNumber(3);
				String Receive 	= createNumber(3);
				String Touch 	= createNumber(1);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(GameID + sp + SSN + sp + Pass + sp+ Rush + sp + 
						Receive + sp + Touch + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/* 
	 * This method generates "numeric" data for the players
	 * performance in a game, 
	 * which will be used for "KickerPerformance" table. 
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writeKickerPerformanceFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				String GameID =createNumber(1);
				
				// GameID should not be zero.
				while(GameID == "0") {
					GameID		= createNumber(1);
				}
				/*
				 * Let's use the already generated SSN from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String SSN = ssnList.get(rng.nextInt(10));
				String Field 	= createNumber(2);
				String Extra 	= createNumber(2);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(GameID + sp + SSN + sp + Field + sp+ Extra + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	public static void writePlayerInTeamFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				/*
				 * Let's use the already generated SSN and school name from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String SSN 		= ssnList.get(rng.nextInt(10));
				String School 	= schoolList.get(rng.nextInt(10));
				String Pos 		= createName(15);
				String Jersey 	= createNumber(2);
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(SSN + sp + School + sp + Pos + sp + Jersey + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/* 
	 * This method stores all the primary keys from DefensivePerformance,
	 * OffensivePerformance, and KickerPerformance. All the data that we need
	 * is GameID and SSN that have been previously generated.
	 * It also takes a file pointer as a parameter, and writes the 
	 * data into the file. 
	 */
	public static void writePlayerPerformanceFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				String GameID =createNumber(1);
				
				// GameID can't be zero
				while(GameID == "0") {
					GameID		= createNumber(1);
				}
				/*
				 * Let's use the already generated SSN from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String SSN = ssnList.get(rng.nextInt(10));
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(GameID + sp + SSN + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	/*
	 * This method generated uses the previously generated 
	 * school names and game ID for filling up the file.
	 * This ensures that referential integrity is followed
	 * when I insert the data in the database.
	 * These data will be used for "TeamInGame" table.
	 */
	public static void writeTeamInGameFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				String GameID =createNumber(1);
				while(GameID == "0") {
					GameID		= createNumber(1);
				}
				String School = schoolList.get(rng.nextInt(10));
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(GameID + sp + School + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/*
	 * This method uses the previously generated conference names and
	 * school names, which ensures referential integrity when I
	 * insert the data in the database.
	 * Thse data will be used in the ConferenceMembership table.
	 */
	public static void writeConfMembersFile(FileWriter fw) {
		try {	
			
			Random rng = new Random();
			for(int i=0; i < 10; i++) {
				
				/*
				 * Let's use the already generated school names and
				 * conference names from earlier.
				 * Makes sure referential integrity is followed
				 * when I load the data in the database.
				 */
				String Conf	= confList.get(rng.nextInt(10));
				String School = schoolList.get(rng.nextInt(10));
				
				char sp=',';
				/*
				 * This is the part where the program writes the data
				 * into the given file. The data are separated by
				 * comma.
				 */
				fw.write(Conf + sp + School + "\r\n");
			}
			}
				catch(IOException e) {
					e.printStackTrace();
			}
	}
	
	/*
	 * This method calls the "getRandomString" method and return a 
	 * string composed of just numbers. Used for numeric-based data
	 * like SSN, Age, Weight, Height, and so on.
	 */
	public static String createNumber(int len) {
		String chars=("0123456789");
		Random rng=new Random();
		
		String ssn= getRandomString(rng, chars, len);
		return ssn;
	}
	
	/*
	 * This method calls the "getRandomString" method and return a 
	 * string for data such as person's name, address, school name,
	 * and so on.
	 */
	public static String createName(int len) {
		
		String chars=("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
		Random rng=new Random();
		
		String name= getRandomString(rng, chars, len);
		return name;
	}
	
	/*
	 * This method is the main function that generated the random string.
	 * It accepts the Random object, the set of characters that you want your
	 * random string to come from, and the length of the string.
	 * 
	 * It returns the randomly generated string.
	 */
	public static String getRandomString(Random rng, String chars, int len) {
		char[] first = new char[len];
		for(int i=0; i<len; i++ ) {
			int anum = rng.nextInt(chars.length());
			first[i] = chars.charAt(anum);		
		}
		String second = new String(first);
		return second;
	}
	
	/*
	 * This is a small function that simply returns a "random but
	 * valid year". It basically returns a year between 1945 to 2013.
	 * The reason I'm doing this is that I have a table that only accepts 
	 * a data of type DATE; otherwise MySQL inserts all zero's in the field
	 * which we don't want.
	 */
	 public static String returnRandomDate() {
		 
		 /*
		  * Uses the java object GregorianCalendar.
		  */
		 GregorianCalendar gc = new GregorianCalendar();

	      int year = 1945 + (int)Math.round(Math.random() * (2013 - 1945));
	      gc.set(GregorianCalendar.YEAR, year);
 
	      int dayOfYear = 1 + (int)Math.round(Math.random() * (gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR) - 1));
	      gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

	      /*
	       * Concatenate the year, month and day together to form a valid looking date in the format "2013-03-01".
	       */
	      String mydate=gc.get(GregorianCalendar.YEAR) + "-" + gc.get(GregorianCalendar.MONTH) + "-" + gc.get(GregorianCalendar.DAY_OF_MONTH);
	      //System.out.println(mydate);

	      return mydate;
	 }
	 
}
