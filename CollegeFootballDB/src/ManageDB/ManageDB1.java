package ManageDB;
/* TEXAS A&M UNIVERSITY
 * DEPARTMENT OF COMPUTER SCIENCE
 * FALL 2013
 * 
 * COURSE:	CSCSE 608 Database Project
 * NAME:	College Football Database
 * AUTHOR:	Alejandrio Vasay
 * 
 * DESCRIPTION:
 * This program is for Project #4. 
 * This is a menu-based program which allows user to display,
 * insert, and update rows in database. This is an application
 * for the College Football database. The database itself stores
 * information about college football teams, players, games,
 * performance of players in a game, and conference memberships.
 * 
 * When you run the program, you will be presented with a menu
 * so you can choose what you want to do. With the menu, you are
 * able to display the players and their personal information,
 * you are able to display the teams and their information like 
 * coaches names, you are able to display conference memberships,
 * you are able to insert new player or team, you are able to 
 * change a player in your team, and you are able to change 
 * your team's conference membership.  
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManageDB1 {

	//
	// Declare and initialize the variables to specifiy the 
	// connection parameters to the JDBC connector.
	//
	private static final String DATABASE_URL 	= "jdbc:mysql://localhost/ncaaf?"; 
    private static final String DRIVER 			= "com.mysql.jdbc.Driver"; 
    private static final String user 			= "root";
    private static final String pass 			= "MyS-Met-14";
    
    private static final String PlayersTable 	= "";
    
    //
    // These are global variables used to execute SQL queries.
    //
    private static Connection connect 			= null;
	private static Statement statement 			= null;
	private static PreparedStatement ps 		= null;
	private static ResultSet resultSet 			= null;
    
	/*
	 * This function is used for Menu Option # 4
	 * Asks the user for the name of a team. 
	 * Then, retrieves the record by which the coach of that team lost
	 * in the home game.
	 * 
	 * Shows the number of row(s) returned; displays 0 otherwise.
	 */
	public static void DisplayLostGameHeadCoach(Scanner user_input) {
		
		try {
			//
			// To display the game and headcoach of the team
			// that lost at least one home game, enter
			// the team's name.
			//
			int rowsreturned = 0;
			System.out.println();
			System.out.print("Enter Team Name:  ");
			String school = user_input.nextLine();
			
			//
			// Then, construct the string that holds the select query. The 
			// following query will joins the tables Teams and Games to
			// find the headcoach that lost a home game.
			// Where HomeTeam == user-provided team(or school)
			//
			String sql="select Games.ID as Game, SchoolName, HeadCoach from Teams join Games on(SchoolName=HomeTeam) "
					+ "where HomeTeamScore < VisitingTeamScore and HomeTeam = '" + school + "'";
			
			//
			// Execute the SQL query and extract the information needed one at a time
			// in a loop, and concatenate these data into the result string,
			// so we can print it later.
			//
			statement = connect.createStatement(); 
	        resultSet = statement.executeQuery(sql);
	        String result = "";
	        result += "Game ID" +"\t\t"+ "Team" + "\t"+ "Head Coach" + "\n";
	        while(resultSet.next()) 
	        {
	        	rowsreturned++;
	      	  Integer game 			= resultSet.getInt("Game");
	      	  String schoolname 	= resultSet.getString("SchoolName");
	      	  String coach 			= resultSet.getString("HeadCoach");
	      	  result += game.toString() +"\t"+ schoolname + "\t"+ coach + "\n";
	      	  
	        }
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println();
	        System.out.println("Result: " + rowsreturned + " row(s) returned.");
	        System.out.println(result);
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}

 	/*
 	 * This function is used in Menu Option # 1.
 	 * Retrieves all players in the league.
 	 * 
 	 */
	public static void SelectFromPlayers() {
		
		try {
			//
			// Construct the SQL statement to find all the players
			//
			int rows=0;
			String sql="select * from Players";
			statement = connect.createStatement(); 
	        
			//
			// Execute the SQL query and extract the information needed one at a time
			// in a loop, and concatenate these data into the result string,
			// so we can print it later.
			//
	        resultSet = statement.executeQuery(sql);
	        String result = "";
	        result += "SSN" +"\t\t"+ "FULLNAME" + "\t"+ "LASTNAME" + "\t" + "STATE" + "\t" + "AGE" + "\n";
	        while(resultSet.next()) 
	        {
	        	rows++;
	      	  int ssn 		= resultSet.getInt("SSN");
	      	  String fname 	= resultSet.getString("FirstName");
	      	  String lname 	= resultSet.getString("LastName");
	      	  String state 	= resultSet.getString("State");
	      	  Integer age	= resultSet.getInt("Age");
	      	  result += ssn+"\t"+ fname + "\t"+ lname + "\t" + state + "\t" + age + "\n";
	      	  
	        }
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println();
	        System.out.println("Result : " + rows + " row(s) returned.");
	        System.out.println(result);
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}
	
	/*
	 * This function is used in Menu Option # 2
	 * Retrieves all teams in the league.
	 */
	public static void SelectFromTeams() {
		
		try {
			int rows=0;
			String sql="select * from Teams";
			statement = connect.createStatement(); 
	        
			//
			// Execute the SQL query and extract the information needed one at a time
			// in a loop, and concatenate these data into the result string,
			// so we can print it later.
			//
	        resultSet = statement.executeQuery(sql);
	        String result = "";
	        result += "TEAM" +"\t\t"+ "NICKNAME" + "\t"+ "STATE" + "\t\t" + "COACH" + "\t\t" + "MASCOT" + "\n";
	        while(resultSet.next()) 
	        {
	        	rows++;
	      	  String school	= resultSet.getString("SchoolName");
	      	  String nick 	= resultSet.getString("NickName");
	      	  String state 	= resultSet.getString("State");
	      	  String coach	= resultSet.getString("HeadCoach");
	      	  String mascot	= resultSet.getString("Mascot");
	      	  result += school +"\t"+ nick + "\t"+ state + "\t" + coach + "\t" + mascot + "\n";
	      	  
	        }
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println("Result: " + rows + " row(s) returned.");
	        System.out.println(result);
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}
	
	/*
	 * This is used in Menu Option # 3
	 * Retrieves all conferences and team pair.
	 */
	public static void SelectFromMembership() {
		
		try {
			int rows=0;
			String sql="select * from ConferenceMembership";
			statement = connect.createStatement(); 
	        
			//
			// Execute the SQL query and extract the information needed one at a time
			// in a loop, and concatenate these data into the result string,
			// so we can print it later.
			//
	        resultSet = statement.executeQuery(sql);
	        String result = "";
	        result += "CONFERENCE" +"\t"+ "SCHOOLNAME" + "\n";
	        while(resultSet.next()) 
	        {
	        	rows++;
	      	  String conf	= resultSet.getString("ConferenceName");
	      	  String school 	= resultSet.getString("SchoolName");
	      	  result += conf +"\t\t"+ school + "\n";
	      	  
	        }
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println("Result: " + rows + " row(s) returned.");
	        System.out.println(result);
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}

	/*
	 * This function is used in Menu Option # 5
	 * Lets users enter a new Player in the league.
	 */
	public static void InsertPlayers(Scanner user_input) {
		try {
			//
			// Ask the user to enter all these information about
			// the player
			//
		System.out.println("\nWill insert Players \n");
		String ssn;
		System.out.print("Enter new SSN:  ");
		ssn = user_input.nextLine();
		
		String first;
		System.out.print("Enter new FirstName:  ");
		first = user_input.nextLine();
		
		String last;
		System.out.print("Enter new LastName:  ");
		last = user_input.nextLine();
		
		String addr;
		System.out.print("Enter new Address:  ");
		addr = user_input.nextLine();
		
		String city;
		System.out.print("Enter new City:  ");
		city = user_input.nextLine();
		
		String state;
		System.out.print("Enter new State:  ");
		state = user_input.nextLine();
		
		String phone;
		System.out.print("Enter new Phone:  ");
		phone = user_input.nextLine();
		
		String weight;
		System.out.print("Enter new Weight:  ");
		weight = user_input.nextLine();
		
		String height;
		System.out.print("Enter new Height:  ");
		height = user_input.nextLine();
		
		String age;
		System.out.print("Enter new Age:  ");
		age = user_input.nextLine();
		
		//
		// Construct the SQL statement that wll insert all the data
		// that the user just entered.
		// The question marks are "placeholders" that will later be
		// replaced by actual values by the preparedStatement object.
		//
		String sql = "insert into Players "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		//
		// Execute the SQL query
		// But first replace all the occurence of question marks with 
		// the following values.
		//
		ps = connect.prepareStatement(sql); 
		ps.setString(1, ssn);
		ps.setString(2, first);
		ps.setString(3, last);
		ps.setString(4, addr);
		ps.setString(5, city);
		ps.setString(6, state);
		ps.setString(7, phone);
		ps.setString(8, weight);
		ps.setString(9, height);
		ps.setString(10, age);
		
		int count = ps.executeUpdate();
		
		//
        // close the statement so it can be used later by another query
        // then print the result string
        //
		ps.close();
		System.out.println();
		System.out.println(count + " rows were inserted");
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}
	
	/*
	 * Used in Menu Option # 6
	 * Lets users add new team to the league. 
	 */
	public static void InsertTeams(Scanner user_input) {
		try {
			//
			// Ask thr user to enter all these information
			// about the team
			//
		System.out.println("\nWill insert Teams \n");
		String school;
		System.out.print("Enter School Name:  ");
		school = user_input.nextLine();
		
		String nick;
		System.out.print("Enter NickName:  ");
		nick = user_input.nextLine();
		
		String city;
		System.out.print("Enter City:  ");
		city = user_input.nextLine();
		
		String state;
		System.out.print("Enter State:  ");
		state = user_input.nextLine();
		
		String mascot;
		System.out.print("Enter Mascot:  ");
		mascot = user_input.nextLine();
		
		String stadium;
		System.out.print("Enter Stadium:  ");
		stadium = user_input.nextLine();
		
		String head;
		System.out.print("Enter HeadCoach:  ");
		head = user_input.nextLine();
		
		String defCoord;
		System.out.print("Enter Defensive Coordinator:  ");
		defCoord = user_input.nextLine();
		
		String offCoord;
		System.out.print("Enter Offensive Coordinator:  ");
		offCoord = user_input.nextLine();
				
		//
		// Construct the SQL statement that wll insert all the data
		// that the user just entered.
		// The question marks are "placeholders" that will later be
		// replaced by actual values by the preparedStatement object.
		//
		String sql = "insert into Teams "
				+ "values(?,?,?,?,?,?,?,?,?)";
		
		//
		// Execute the SQL query
		// But first replace all the occurence of question marks with 
		// the following values.
		//
		ps = connect.prepareStatement(sql); 
		ps.setString(1, school);
		ps.setString(2, nick);
		ps.setString(3, city);
		ps.setString(4, state);
		ps.setString(5, mascot);
		ps.setString(6, stadium);
		ps.setString(7, head);
		ps.setString(8, defCoord);
		ps.setString(9, offCoord);
		
		int count = ps.executeUpdate();
		
		//
        // close the statement so it can be used later by another query
        // then print the result string
        //
		ps.close();
		System.out.println();
		System.out.println(count + " rows were inserted");
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
	}
	
	/*
	 * This is a helper function for the ChangeConference() function.
	 * It changes the conference that a team belongs to, given
	 * the team name.
	 */
	public static void UpdateMembership(String school, String conf) {
		try {
			//
			// Construct the SQL statemement that we will use to update the membership
			// in ConferenceMemebrship table, given the team name and the conference name. 
			String sql = "UPDATE ConferenceMembership "; 
        	sql = sql + "SET ConferenceName = ? "; 
        	sql = sql + "WHERE SchoolName = ? ";
        	
        	//
        	//  Execute the SQL statement
        	//
        	ps = connect.prepareStatement(sql); 
    		ps.setString(1, conf);
    		ps.setString(2, school);
    		
    		int count = ps.executeUpdate();
    		
    		//
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
    		ps.close();
    		System.out.println();
    		System.out.println(count + " row(s) updated");
    		
        	
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		}
		
	}
	
	/*
	 * Used in Menu option # 8
	 * Uses Games table.
	 * Lets users enter conference league, a team name, and a 
	 * good average score. Then, compares the team's average score with the
	 * given average score. Then, it updates the conference that a team belongs
	 * to if that team's score is below the given score.
	 * 
	 * I chose this query because in reality, some conferences in college 
	 * football are considered not good, and some are considered the best
	 * in the league. If you're in the best conference in the league, then
	 * your team's score should be above average.
	 */
	public static void ChangeConference(Scanner user_input) {
		try {
			
			//
			// Ask user to enter 3 data: conference name, team name, and
			// average score. The average score is anything thatthe 
			// user can think of is a good average score in a game.
			//
			System.out.println(" ");
			System.out.println(" ");
			String conf;
			System.out.print("Enter Worst Conference in League:  ");
			conf = user_input.nextLine();
			
			String team;
			System.out.print("Enter Team not in that Conference:  ");
			team = user_input.nextLine();
			
			String good_score;
			System.out.print("Enter a Good Average Score:  ");
			good_score = user_input.nextLine();
			
			int good_scoreint = Integer.parseInt(good_score);
			System.out.println("Number: " + good_scoreint);
			
			//
			// Construct the SQL statement to find AGGREGATE AVG of 
			// all scores of team in all home games. The query is 
			// performed on the Games table.
			//
			String sql="select HomeTeam, avg(HomeTeamScore) as averageScore "
					+ "from Games where HomeTeam='" + team + "'";
			
			//
			// Execute the query on the database
			//
			statement = connect.createStatement(); 
	        
	        resultSet = statement.executeQuery(sql);
	        String result = "";
	        result += "Team" +"\t"+ "Average Score" + "\n";
	        
	        //
	        //  Extract the information that we need, most importantly
	        // the average score and put it in a temprary variable, because
	        // we will use this data for comparison.
	        //
	        Float averageScore=0.0f;
	        while(resultSet.next()) 
	        {
	      	  String hometeam	= resultSet.getString("HomeTeam");
	      	  Float average 	= resultSet.getFloat("averageScore");
	      	  result += hometeam +"\t\t"+ average + "\n";
	      	  averageScore = average;
	        }
	        
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        System.out.println(" ");
	        System.out.println("Result:");

	        statement.close();
	        
	        /*
	         * This is where we compare the team's average score with the 
	         * given average score.
	         */
	        if(averageScore < good_scoreint) {
	        	System.out.println(team + "'s average score of " + averageScore + " is not good enough!");
	        	System.out.println("Will change the conference to " + conf);
	        	
	        	//
	        	// Change the conference that the team belongs to because it is not a good team
	        	//
	        	UpdateMembership(team, conf);
	        	
	        }
	        else {
	        	
	        	//
	        	// No need to do anyting
	        	//
	        	System.out.println(team + "'s average score of " + averageScore + " is not good enough!");
	        	System.out.println("No need to change conference.");
	        }
			
	        
	        
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		} 
	}
	
	/*
	 * This is a helper function to ReplacePlayer() method.
	 * Given a SSN id of the player, find his school.
	 * JOIN PlayerInTeam with Players table.
	 */
	public static String GivenSSNGiveSchool(String ssn) {
		String hisschool=""; 
		try {
			//
	        // Find the player's school
	        //
	        String playerteamsql="select SchoolName from PlayerInTeam join Players on(Players.SSN=PlayerInTeam.SSN) where Players.SSN='" + ssn + "';";
			statement = connect.createStatement(); 
	        resultSet = statement.executeQuery(playerteamsql);
	        
	        while(resultSet.next()) 
	        {
	        	
	      	  String team 	= resultSet.getString("SchoolName");
	      	  //result += game.toString() +"\t"+ ssn + "\n";
	      	  hisschool = team;
	        }
	        
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        
	        return hisschool;
			
		}catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		} 
		return hisschool;
	}
	
	/*
	 * Used in Menu Option # 7
	 * If you don't like someone in your favorite team, this option
	 * lets you replace him with the best football player in the league.
	 * Just give me the name of the player you don't like in the team,
	 * then I will replace him with a really good player.
	 * 
	 * Performs 4 SELECT Queries and 1 UPDATE query.
	 * Uses 3 tables.
	 */
	public static void ReplacePlayer(Scanner user_input) {
		try {
			//
			// Asks user for inputs
			//
			int rows=0;
			String first;
			System.out.println("In your favorite team, who is the player you don't like? ");
			System.out.print("Enter his firstname :  ");
			first = user_input.nextLine();
			
			String last;
			System.out.print("Enter his lastname :  ");
			last = user_input.nextLine();
			
			//
			// Find the SSN of worst player
			//
			String worstsql="select * from Players where FirstName like '%" + first + "%' and LastName like '%" + last + "%';";
			statement = connect.createStatement(); 
	        resultSet = statement.executeQuery(worstsql);
	        String worstssn="";
	        while(resultSet.next()) 
	        {
	        	
	      	  String ssn 	= resultSet.getString("SSN");
	      	  //result += game.toString() +"\t"+ ssn + "\n";
	      	  worstssn = ssn;
	        }
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println("Worst Player : " + worstssn);
	        
	        //
	        // Then find his team's name
	        //
	        String worstschool = GivenSSNGiveSchool(worstssn);
			System.out.println("Worst Player's Team: " + worstschool);
			
			//
			// Look for Best Player in League
			//
			String sql="select * from OffensePerformance where  (PassingYards+RushingYards+ReceivingYards+Touchdowns) =  (select max(PassingYards+RushingYards+ReceivingYards+Touchdowns) from OffensePerformance);";
			statement = connect.createStatement(); 
	        resultSet = statement.executeQuery(sql);
	        String bestssn="";
	        while(resultSet.next()) 
	        {
	      	  String ssn 	= resultSet.getString("SSN");
	      	  //result += game.toString() +"\t"+ ssn + "\n";
	      	  bestssn = ssn;
	        }
	        //
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
	        statement.close();
	        System.out.println("Best Player : " + bestssn);
	        
	        // 
	        // Then find the best player's team's name
	        //
	        String bestschool = GivenSSNGiveSchool(bestssn);
			System.out.println("Best Player's Team: " + bestschool);
			
	        /* 
	         * Finally, Exchange Players 
	         */
			
			//
			// First Update
			//
	        String sql3 = "UPDATE PlayerInTeam "; 
        	sql3 = sql3 + "SET SchoolName = ? "; 
        	sql3 = sql3 + "WHERE SSN = ? ";        	
        	ps = connect.prepareStatement(sql3); 
    		ps.setString(1, worstschool);
    		ps.setString(2, bestssn);
    		
    		int count = ps.executeUpdate();
    		//
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
    		ps.close();
    		System.out.println();
    		System.out.println(count + " row(s) updated. " + worstschool + " now has " + bestssn);
    		
    		//
    		// Second Update
    		//
    		String sql4 = "UPDATE PlayerInTeam "; 
    		sql4 = sql4 + "SET SchoolName = ? "; 
    		sql4 = sql4 + "WHERE SSN = ? ";
        	
        	ps = connect.prepareStatement(sql4); 
    		ps.setString(1, bestschool);
    		ps.setString(2, worstssn);
    		
    		//
	        // close the statement so it can be used later by another query
	        // then print the result string
	        //
    		count = ps.executeUpdate();
    		ps.close();
    		System.out.println(count + " row(s) updated. " + bestschool + " now has " + worstssn);
    		System.out.println("Secret Exchange of Students Accomplished.");
    		System.out.println();
    		
    		
    		
		} catch (SQLException sqlException) { 
			sqlException.printStackTrace(); 
		} catch (Exception e) {
		        e.printStackTrace();
		} 
	}
	
	/*
	 * Opens connections to database. 
	 * Called in Main()
	 */
	private static void Open() {
		try {
			Class.forName(DRIVER);
			connect = DriverManager.getConnection(DATABASE_URL, user, pass);
			//System.out.println("Connection Established.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Closes all connections.
	 */
      private static void Close() {
        try {
          if (resultSet != null) {
            resultSet.close();
          }

          if (statement != null) {
            statement.close();
          }

          if (connect != null) {
            connect.close();
          }
        } catch (Exception e) {
        	e.printStackTrace();
        }
      }
      
      /*
       * OUR MAIN MENU
       */
      private static String Menu()
 	 {
 		 
 		 		 
 		// Display menu graphics
  		    System.out.println("                  COLLEGE FOOTBALL MENU                     ");
 		    System.out.println("============================================================");
 		    System.out.println("|                                                           |");
 		    System.out.println("|        1. Display All Players                             |");
		    System.out.println("|        2. Display All Teams                               |");
		    System.out.println("|        3. Display Conference Memberships of All Teams     |");
		    System.out.println("|        4. Give me a team, and I will display the game ID  |");
		    System.out.println("|           and headcoach's name for that                   |");
		    System.out.println("|           team who played at home and whose score         |");
		    System.out.println("|           is less than the visiting team's score.         |");
		    System.out.println("|        5. Insert New Player                               |");
  		    System.out.println("|        6. Insert New Team                                 |");
  		    System.out.println("|        7. Let's exchange two players in two teams.        |");
   		    System.out.println("|           What if you could replace a player in your      |");
 		    System.out.println("|           favorite team with the best player in the       |");
 		    System.out.println("|           league, and give your team's worst player to    |");
 		    System.out.println("|           to the other team(without them knowing)?        |");
 		    System.out.println("|           That is, give me a name of your least           |");
 		    System.out.println("|           favorite player in your team, and I will        |");
 		    System.out.println("|           replace it with the player from another team,   |");
 		    System.out.println("|           who has the highest total Touchdowns,Passing,   |");
 		    System.out.println("|           Rushing, and Receiving Yards in any game.       |");
  		    System.out.println("|        8. Give me the worst conference in the league.     |");
 		    System.out.println("|           Then, give me a team that is not in that        |");
 		    System.out.println("|           conference. Lastly, tell me the average score   |");
 		    System.out.println("|           that a team should have at a home game. If that |");
 		    System.out.println("|           team's average home score is less than the      |");
 		    System.out.println("|           average score you gave, then I will change its  |");
 		    System.out.println("|           conference membership to the worst conference   |");
 		    System.out.println("|           in the league.                                  |");
  		    System.out.println("|        Q. Exit                                            |");
  		    System.out.println("============================================================");

 			Scanner user_input = new Scanner(System.in);
 			
 			String selection;
 			System.out.print("         Enter option:  ");
 			selection = user_input.nextLine();
 			return selection;
 	 }
      
      
      
      /******************************************************************************
       * 
       * 				               MAIN PROGRAM
       * 
       ******************************************************************************/
	public static void main(String[] args) {
		
		try {
			
			//
			// Open database connection
			//
			Open();
			
			//
			// This is an eternal loop. Program exits out of main, when user
			// selects 'Q' in the main menu.
			//
			while(true) {
				Scanner user_input = new Scanner(System.in);
				String selection = Menu(); 
				System.out.println("Your selection: " + selection.trim());
			    
				// Menu Option 1 - Display all players
				if(selection.trim().equals("1"))
				{
					SelectFromPlayers();
				}
				
				// Menu Option 2 - Display all teams
				if(selection.trim().equals("2"))
				{
					SelectFromTeams();
				}
				
				// Menu Option 3 - Displays all conference membership
				if(selection.trim().equals("3"))
				{
					SelectFromMembership();
				}
				
				// Menu Option 4 - Display the game and head coach where his team lost a home game
				if(selection.trim().equals("4"))
				{
					DisplayLostGameHeadCoach(user_input);
				}
				
				// Menu Option 5 - Add a new player
				if(selection.trim().equals("5"))
				{
					InsertPlayers(user_input);
				}
				
				// Menu Option 6 - Add a new team
				if(selection.trim().equals("6"))
				{
					InsertTeams(user_input);
				}
				
				// Menu Option 7 - Replace a bad player with a good player
				if(selection.trim().equals("7"))
				{
					ReplacePlayer(user_input);
				}
				
				// Menu Option 8 - Change conference of a bad team
				if(selection.trim().equals("8"))
				{
					ChangeConference(user_input);
				}
				
				// Quit program
				if(selection.trim().equalsIgnoreCase("Q"))
				{
					System.out.println("\nWill now exit program. Bye!\n");
					System.exit(0);
				}		    
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			//
			// Close all database connections
			//
			Close();
		}	
	}

}

