package BJ;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.aither.serveur.Participant;

public class BJack {	 
////////////////////////      Déclaration de l'ensemble des Jlabel, Jbutton, Jframe     ////////////////////////	
	
	JLabel BackGround = new JLabel("");
	JLabel card1Croupier = new JLabel(""); 			JLabel textCard1Player = new JLabel("");
	JLabel card2Croupier = new JLabel("");			JLabel textCard2Player = new JLabel("");
	JLabel card3Croupier = new JLabel("");			JLabel textCard3Player = new JLabel("");
	JLabel card4Croupier = new JLabel("");			JLabel textCard4Player = new JLabel("");
	JLabel card5Croupier = new JLabel("");			JLabel textCard5Player = new JLabel("");
	
	JLabel card1Player = new JLabel("");			JLabel textCard1Croupier = new JLabel("");
	JLabel card2Player = new JLabel("");			JLabel textCard2Croupier = new JLabel("");
	JLabel card3Player = new JLabel("");			JLabel textCard3Croupier = new JLabel("");
	JLabel card4Player = new JLabel("");			JLabel textCard4Croupier = new JLabel("");
	JLabel card5Player = new JLabel("");			JLabel textCard5Croupier = new JLabel("");
			
	private final JLabel Mise10 = new JLabel("");	private final JButton Jetons10 = new JButton("");
	private final JLabel Mise20 = new JLabel("");	private final JButton Jetons20 = new JButton("");
	private final JLabel Mise50 = new JLabel("");	private final JButton Jetons50 = new JButton("");
			
	private final JButton Hit = new JButton(""); 	private final JButton Split = new JButton("");
	private final JButton Stand = new JButton("");	private final JButton Double = new JButton("");
		
////////////////////////Déclaration de l'ensemble des Jlabel, Jbutton, Jframe     ////////////////////////	
	
	private JFrame frame;
	private Socket socket;
	private BufferedReader in;
	private PrintStream out;
	private JTextField playerName;
	private JTextField txtplayerMise;
	
	Random Rand = new Random();	
	
	private final JLabel txtScore = new JLabel      ("0");		
	private final JLabel txtScoreBanque = new JLabel("0");		
	private final JLabel txt3 = new JLabel("La banque est à ");
	private final JLabel Bienvenue = new JLabel("Bienvenue");
	
	private final JLabel betAgain = new JLabel("Bet to play again");
	private final JButton startBTN = new JButton("");
	private final JLabel fleche2 = new JLabel("");
	private final JLabel dealer = new JLabel("");
	private final JLabel fleche = new JLabel("");
	private final JLabel label = new JLabel("");
	private final JLabel panel = new JLabel("");
	private final JLabel Cave = new JLabel("");
	private final JLabel bet = new JLabel("");
	
	JLabel instructions = new JLabel("Veuillez entrer votre nom et votre mise : ");
	JLabel whoWins = new JLabel("La Banque Gagne");
	JLabel bienvenue = new JLabel("Bienvenue\n");
	JLabel whoWinsBG = new JLabel("New label");
	JLabel infoBG = new JLabel("New label");
	JLabel infoMise = new JLabel("Mise : ");
	JLabel infoName = new JLabel("Nom : ");	
	JLabel BJCard = new JLabel("");

//////////////////////////////     Déclaration de l'ensemble des variables       ////////////////////////////////////
	
	int icroupierCard1 ;
	int icroupierCard2 ;
	int icroupierCard3 ;
	int icroupierCard4 ;
	int icroupierCard5 ;
	
	int iplayerCard1 ;		
	int iplayerCard2 ;		
	int iplayerCard3 ;		
	int iplayerCard4 ;		
	int iplayerCard5 ;		
		
	int score2Player ;
	int score3Player ;
	int score4Player ;
	int score5Player ;
	
	int score1Croupier ;
	int score2Croupier ;
	int score3Croupier ;
	int score4Croupier ;
	int score5Croupier ;
	
	int iHit=0;
	int playerMise;
	int playerCave;
	int playerScore=0;
	int croupierScore=0;
	
	
////////////////////////////////////////      Démarrage de l'application        //////////////////////////////////	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BJack window = new BJack();
					window.frame.setVisible(true);}  
				catch (Exception e) {
					e.printStackTrace(); }}}); }
	
////////////////////////////      Mise en place de la communication client serveur    ///////////////////////////////
	public BJack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					socket = new Socket("127.0.0.1", 9632);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
					out = new PrintStream(socket.getOutputStream());
					
				} 
				catch (UnknownHostException e) {e.printStackTrace();} 
				catch (IOException e) {e.printStackTrace();}				
			}
		}).start();
		initialize();
		initialisation();
		
	}
	
///////////////////////////////      Attribution aléatoire des cartes       //////////////////////////////////
	
	private void setCards() {
		
	 iplayerCard1 = Rand.nextInt(10)+1;;	 icroupierCard1 = 10;
	 iplayerCard2 = Rand.nextInt(10)+1;		 icroupierCard2 = 7;
	 iplayerCard3 = Rand.nextInt(10)+1;		 icroupierCard3 = Rand.nextInt(10)+1;
	 iplayerCard4 = Rand.nextInt(10)+1;		 icroupierCard4 = Rand.nextInt(10)+1;
	 iplayerCard5 = Rand.nextInt(10)+1;		 icroupierCard5 = Rand.nextInt(10)+1;		
		
	 score2Player = iplayerCard1+iplayerCard2;	score1Croupier = icroupierCard1;
	 score3Player = score2Player+iplayerCard3;	score2Croupier = icroupierCard1+icroupierCard2;
	 score4Player = score3Player+iplayerCard4;	score3Croupier = score2Croupier+icroupierCard3;
	 score5Player = score4Player+iplayerCard5;	score4Croupier = score3Croupier+icroupierCard4;
	 
	}
	
//////////////////////////////     Initialisation de chaque nouvelle partie      ////////////////////////////
	
	private void initialisation() {
		
		setCards();
		playerScore=0;
		croupierScore=0;
		card1Croupier.setVisible(false);	card1Player.setVisible(false);
		card2Croupier.setVisible(false);	card2Player.setVisible(false);
		card3Croupier.setVisible(false);	card3Player.setVisible(false);
		card4Croupier.setVisible(false);	card4Player.setVisible(false);
		card5Croupier.setVisible(false);	card5Player.setVisible(false);
		BJCard.setVisible(false);
		bet.setVisible(false);	
		fleche2.setVisible(false);
		betAgain.setVisible(false);
		
		textCard1Player.setVisible(false);	textCard1Croupier.setVisible(false);
		textCard2Player.setVisible(false);	textCard2Croupier.setVisible(false);
		textCard3Player.setVisible(false);	textCard3Croupier.setVisible(false);
		textCard4Player.setVisible(false);	textCard4Croupier.setVisible(false);
		textCard5Player.setVisible(false);	textCard5Croupier.setVisible(false);
		
		textCard1Player.setText(""+iplayerCard1)	;	textCard1Croupier.setText(""+icroupierCard1);
		textCard2Player.setText(""+iplayerCard2)	;	textCard2Croupier.setText(""+icroupierCard2);
		textCard3Player.setText(""+iplayerCard3)	;	textCard3Croupier.setText(""+icroupierCard3);
		textCard4Player.setText(""+iplayerCard4)	;	textCard4Croupier.setText(""+icroupierCard4);
		textCard5Player.setText(""+iplayerCard5)	;	textCard5Croupier.setText(""+icroupierCard5);
		
		Mise10.setVisible(false);
		Mise20.setVisible(false);
		Mise50.setVisible(false);txtScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore.setVisible(false);
		txtScoreBanque.setHorizontalAlignment(SwingConstants.CENTER);
		txtScoreBanque.setVisible(false);
		txt3.setVisible(false);
		Double.setVisible(false);			Split.setVisible(false);
		whoWins.setBackground(Color.DARK_GRAY);
		whoWins.setHorizontalAlignment(SwingConstants.CENTER);
		whoWins.setVisible(false);
		panel.setVisible(false);
		Hit.setVisible(false);
		whoWinsBG.setVisible(false);
		Stand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stand();
			}
		});
		Stand.setVisible(false);
		iHit=0;
		
	}
	
///////////////////////////////////////        Lancement de la partie       ////////////////////////////////////////
	
	private void startTheGame() {
		txtplayerMise.setVisible(false);
		playerName.setVisible(false);
		Bienvenue.setText("Bienvenue "+playerName.getText());
		playerCave=Integer.parseInt(txtplayerMise.getText());
		Cave.setText(""+playerCave);
		infoBG.setVisible(false);
		bienvenue.setVisible(false);
		instructions.setVisible(false);
		infoName.setVisible(false);
		infoMise.setVisible(false);
		startBTN.setVisible(false);	
		fleche.setVisible(false);	
		bet.setVisible(true);	
		fleche2.setVisible(true);
		out.println(playerName.getText()+" rejoins la partie avec "+ Cave.getText()+ " euros");
		
	}
	private void choixMise (int Mise) {
		initialisation();
		setTheBet(Mise);
		start();
		out.println(playerName.getText()+" a misé "+ Mise+ " euros");
	}

	private void setTheBet(int Mise) {
		playerCave=playerCave-Mise;
		playerMise=Mise;
		Cave.setText(""+playerCave);
		
		
	}
	private void start() {
		panel.setVisible(true);
		card1Croupier.setVisible(true);
		BJCard.setVisible(true);
		card1Player.setVisible(true);
		card2Player.setVisible(true);
		textCard1Player.setVisible(true);	
		textCard2Player.setVisible(true);
		textCard1Croupier.setVisible(true);	
		Hit.setVisible(true);
		Stand.setVisible(true);
		showScore();
		if(iplayerCard1==1 & iplayerCard2==10) {
			textCard1Player.setText("A");
			txtScore.setText("21");
			BlackJack();
		}
		if(iplayerCard1==10 & iplayerCard2==1) {
			textCard2Player.setText("A");
			txtScore.setText("21");
			BlackJack();
		}
	}
	private void showScore() {
		txtScoreBanque.setText(""+score1Croupier);
		txtScore.setText(""+score2Player);
		txtScoreBanque.setVisible(true);
		txtScore.setVisible(true);
		txt3.setVisible(true);
	}
	
	private void hit() {
		if (iHit==1) {hit3();playerScore=score3Player;}
		if (iHit==2) {hit4();playerScore=score4Player;}
		if (iHit==3) {hit5();playerScore=score5Player;}
	}
	
	private void hit3() {
		card3Player.setVisible(true);
		textCard3Player.setVisible(true);
		txtScore.setText(""+score3Player);
		if (score3Player>=22) {
			looser(3);
		}
	}
	private void hit4() {
		card4Player.setVisible(true);
		textCard4Player.setVisible(true);
		txtScore.setText(""+score4Player);
		if (score4Player>=22) {
			looser(4);
		}
	}
	private void hit5() {
		card5Player.setVisible(true);
		textCard5Player.setVisible(true);
		txtScore.setText(""+score5Player);
		if (score5Player>=22) {
			looser(5);
		}
	}
	
	private void looser(int situation) {
		out.println(playerName.getText()+" a perdu. ");
		betAgain.setVisible(true);
		Hit.setVisible(false);
		Stand.setVisible(false);
		whoWins.setVisible(true);
		whoWins.setText("La Banque Gagne");
		whoWinsBG.setVisible(true);
		iHit=0;
		
	}
	
	private void stand() {
		BJCard.setVisible(false);
		card2Croupier.setVisible(true);
		textCard2Croupier.setVisible(true);
		txtScoreBanque.setText(""+score2Croupier);
		if (score2Croupier<17) {
			card3Croupier.setVisible(true);
			textCard3Croupier.setVisible(true);
			txtScoreBanque.setText(""+score3Croupier);
			croupierScore=score3Croupier;
			if (score3Croupier<17) {
				card4Croupier.setVisible(true);
				textCard4Croupier.setVisible(true);
				txtScoreBanque.setText(""+score4Croupier);
				croupierScore=score4Croupier;
				if (score4Croupier<17) {
					card5Croupier.setVisible(true);
					textCard5Croupier.setVisible(true);
					txtScoreBanque.setText(""+score5Croupier);
					croupierScore=score5Croupier;
				}
			}
		}
		
		if(playerScore>croupierScore & playerScore<22) {
			
			playerWins();
			
		}
		else if(playerScore<croupierScore & croupierScore<22  ) {
			
			bankWins();
		}
		else if(playerScore<croupierScore & croupierScore>21  ) {
			
			playerWins();
			
		}
		else {
			
			noOneWins();
		}
				
	}
///////////////////////////////////////        Le joueur gagne       ////////////////////////////////////////
	
	private void playerWins() {
		outWin();
		betAgain.setVisible(true);
		whoWins.setText("Vous Gagnez");
		whoWins.setVisible(true);
		whoWinsBG.setVisible(true);
		playerCave=playerCave+playerMise;
		Cave.setText(""+playerCave);
		
	}
///////////////////////////////////////        La banque gagne       ////////////////////////////////////////
	
	private void bankWins() {
		outLoose();
		betAgain.setVisible(true);
		whoWins.setText("La Banque Gagne");
		whoWins.setVisible(true);
		whoWinsBG.setVisible(true);		
		
	}
///////////////////////////////////////        Egalité      ////////////////////////////////////////	
	
	private void noOneWins() {
		outEgalite();
		betAgain.setVisible(true);
		whoWins.setText("Egalité");
		whoWins.setVisible(true);
		whoWinsBG.setVisible(true);
		
	}
///////////////////////////////////////        BlackJack       ////////////////////////////////////////
	
	private void BlackJack() {
		outBJ();
		betAgain.setVisible(true);
		whoWins.setText("BlackJack");
		whoWins.setVisible(true);
		whoWinsBG.setVisible(true);
		Cave.setText(""+(playerCave*1.5));
	}

	
	private void outBJ() {
		out.println(playerName.getText()+" obtient un BlackJack !");
		
	}
	private void outLoose() {
		out.println(playerName.getText()+" a perdu. ");
		
	}
	private void outEgalite() {
		out.println(playerName.getText()+" égalise avec la banque.");
	
}	
	private void outWin() {
		out.println(playerName.getText()+" a gagné. ");
	}
///////////////////////////////////////        Mise en forme       ////////////////////////////////////////
	
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1107, 674);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVisible(false);
		lblNewLabel.setIcon(new ImageIcon(BJack.class.getResource("/Images/BJ.png")));
		lblNewLabel.setBounds(906, 342, 122, 160);
		frame.getContentPane().add(lblNewLabel);
		Cave.setForeground(new Color(255, 255, 255));
		Cave.setBounds(18, 610, 57, 36);
		
		frame.getContentPane().add(Cave);
		infoMise.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		
		infoMise.setForeground(new Color(255, 255, 255));
		infoMise.setBounds(606, 289, 87, 33);
		frame.getContentPane().add(infoMise);
		infoName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		

		infoName.setForeground(new Color(255, 255, 255));
		infoName.setBounds(314, 287, 77, 36);
		frame.getContentPane().add(infoName);
		instructions.setFont(new Font("Snell Roundhand", Font.PLAIN, 30));
		
		
		instructions.setForeground(new Color(255, 255, 255));
		instructions.setBounds(338, 165, 446, 29);
		frame.getContentPane().add(instructions);
		
		JLabel lblJoueur = new JLabel("Sebastien");
		lblJoueur.setVisible(false);
		lblJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur.setForeground(Color.WHITE);
		lblJoueur.setFont(new Font("Snell Roundhand", Font.PLAIN, 30));
		lblJoueur.setBounds(854, 313, 189, 51);
		frame.getContentPane().add(lblJoueur);
		bienvenue.setFont(new Font("Snell Roundhand", Font.PLAIN, 40));
		
		
		bienvenue.setForeground(new Color(255, 255, 51));
		bienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenue.setBounds(369, 99, 354, 41);
		frame.getContentPane().add(bienvenue);
		
		txtplayerMise = new JTextField();
		txtplayerMise.setHorizontalAlignment(SwingConstants.CENTER);
		txtplayerMise.setForeground(Color.WHITE);
		txtplayerMise.setFont(new Font("Snell Roundhand", Font.PLAIN, 25));
		txtplayerMise.setBackground(new Color(0, 0, 51));
		txtplayerMise.setBounds(683, 282, 107, 41);
		frame.getContentPane().add(txtplayerMise);
		txtplayerMise.setColumns(10);
		
		playerName = new JTextField();
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setForeground(Color.WHITE);
		playerName.setFont(new Font("Snell Roundhand", Font.ITALIC, 25));
		playerName.setBackground(new Color(0, 0, 51));
		playerName.setBounds(403, 283, 150, 41);
		frame.getContentPane().add(playerName);
		playerName.setColumns(10);
		
		
		infoBG.setIcon(new ImageIcon(BJack.class.getResource("/Images/Screen.png")));
		infoBG.setBounds(277, 60, 544, 305);
		frame.getContentPane().add(infoBG);
		betAgain.setForeground(Color.RED);
		betAgain.setFont(new Font("Snell Roundhand", Font.PLAIN, 35));
		betAgain.setBounds(44, 475, 246, 47);
		
		frame.getContentPane().add(betAgain);
		
		
		whoWins.setForeground(Color.YELLOW);
		whoWins.setFont(new Font("Snell Roundhand", Font.PLAIN, 43));
		whoWins.setBounds(398, 355, 360, 47);
		frame.getContentPane().add(whoWins);
		
		
		whoWinsBG.setIcon(new ImageIcon(BJack.class.getResource("/Images/Screen.png")));
		whoWinsBG.setBounds(418, 350, 321, 66);
		frame.getContentPane().add(whoWinsBG);
		txtScore.setForeground(Color.WHITE);
		txtScore.setFont(new Font("Snell Roundhand", Font.BOLD, 25));
		txtScore.setBounds(357, 552, 53, 41);
		
		frame.getContentPane().add(txtScore);
		panel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setIcon(new ImageIcon(BJack.class.getResource("/Images/iPads.png")));
		panel.setBounds(357, 552, 53, 41);
		
		frame.getContentPane().add(panel);
		txt3.setForeground(Color.WHITE);
		txt3.setFont(new Font("Snell Roundhand", Font.PLAIN, 22));
		txt3.setBounds(91, 35, 144, 33);
		
		frame.getContentPane().add(txt3);
		txtScoreBanque.setForeground(Color.WHITE);
		txtScoreBanque.setFont(new Font("Snell Roundhand", Font.BOLD, 25));
		txtScoreBanque.setBounds(122, 80, 63, 33);
		
		frame.getContentPane().add(txtScoreBanque);
		Bienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		Bienvenue.setForeground(Color.WHITE);
		Bienvenue.setFont(new Font("Snell Roundhand", Font.PLAIN, 22));
		Bienvenue.setBounds(418, 19, 281, 33);
		
		frame.getContentPane().add(Bienvenue);
		startBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTheGame();
			}
		});
		
		BJCard.setIcon(new ImageIcon(BJack.class.getResource("/Images/BJ.png")));
		BJCard.setBounds(452, 123, 101, 130);
		frame.getContentPane().add(BJCard);
		bet.setIcon(new ImageIcon(BJack.class.getResource("/Images/Bet.png")));
		bet.setBounds(177, 431, 113, 66);
		
		frame.getContentPane().add(bet);
		fleche2.setIcon(new ImageIcon(BJack.class.getResource("/Images/fleche2.png")));
		fleche2.setBounds(157, 475, 122, 113);
		
		frame.getContentPane().add(fleche2);
		startBTN.setIcon(new ImageIcon(BJack.class.getResource("/Images/start.png")));
		startBTN.setBounds(506, 377, 87, 66);
		
		frame.getContentPane().add(startBTN);
		fleche.setIcon(new ImageIcon(BJack.class.getResource("/Images/fleche.png")));
		fleche.setBounds(545, 449, 63, 85);
		
		frame.getContentPane().add(fleche);
		dealer.setIcon(new ImageIcon(BJack.class.getResource("/Images/dealers.png")));
		dealer.setBounds(-31, 0, 122, 160);
		
		frame.getContentPane().add(dealer);
		label.setIcon(new ImageIcon(BJack.class.getResource("/Images/iPads.png")));
		label.setBounds(67, -25, 237, 202);
		
		frame.getContentPane().add(label);
		Double.setIcon(new ImageIcon(BJack.class.getResource("/Images/Double.png")));
		Double.setBackground(Color.BLUE);
		Double.setBounds(845, 581, 80, 65);
		
		frame.getContentPane().add(Double);
		Stand.setIcon(new ImageIcon(BJack.class.getResource("/Images/Stand.png")));
		Stand.setBackground(Color.BLUE);
		Stand.setBounds(1010, 581, 80, 65);
		
		frame.getContentPane().add(Stand);
		Hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hit();
				iHit=iHit+1;
			}
		});
		Hit.setIcon(new ImageIcon(BJack.class.getResource("/Images/Hit.png")));
		Hit.setBounds(928, 581, 80, 65);
		
		frame.getContentPane().add(Hit);
		Split.setIcon(new ImageIcon(BJack.class.getResource("/Images/Split.png")));
		Split.setBackground(Color.BLUE);
		Split.setBounds(845, 581, 80, 65);
		
		frame.getContentPane().add(Split);
		Jetons10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixMise(10);
				Mise10.setVisible(true);
			}
		});
		
		Jetons10.setIcon(new ImageIcon(BJack.class.getResource("/Images/10.png")));
		Jetons10.setOpaque(false);
		Jetons10.setContentAreaFilled(false);
		Jetons10.setBorderPainted(false);	
		Jetons10.setBounds(18, 533, 70, 65);
		frame.getContentPane().add(Jetons10);
		Jetons20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixMise(20);
				Mise20.setVisible(true);
			}
		});
		
		Jetons20.setIcon(new ImageIcon(BJack.class.getResource("/Images/20.png")));
		Jetons20.setOpaque(false);
		Jetons20.setContentAreaFilled(false);
		Jetons20.setBorderPainted(false);		
		Jetons20.setBounds(87, 561, 70, 65);	
		frame.getContentPane().add(Jetons20);
		Jetons50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixMise(50);
				Mise50.setVisible(true);
			}
		});
		
		Jetons50.setIcon(new ImageIcon(BJack.class.getResource("/Images/50.png")));
		Jetons50.setOpaque(false);
		Jetons50.setContentAreaFilled(false);
		Jetons50.setBorderPainted(false);
		Jetons50.setBounds(165, 587, 70, 65);
		
		frame.getContentPane().add(Jetons50);
		Mise10.setIcon(new ImageIcon(BJack.class.getResource("/Images/10.png")));
		Mise10.setBounds(522, 348, 70, 65);
		
		frame.getContentPane().add(Mise10);
		Mise20.setIcon(new ImageIcon(BJack.class.getResource("/Images/20.png")));
		Mise20.setBounds(522, 348, 70, 65);
		
		frame.getContentPane().add(Mise20);
		Mise50.setIcon(new ImageIcon(BJack.class.getResource("/Images/50.png")));
		Mise50.setBounds(522, 348, 70, 65);
		
		frame.getContentPane().add(Mise50);
		
		
		textCard5Croupier.setForeground(Color.WHITE);
		textCard5Croupier.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard5Croupier.setBounds(678, 99, 25, 14);
		frame.getContentPane().add(textCard5Croupier);
		

		textCard4Croupier.setForeground(Color.WHITE);
		textCard4Croupier.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard4Croupier.setBounds(620, 99, 36, 14);
		frame.getContentPane().add(textCard4Croupier);
		
		
		textCard3Croupier.setForeground(Color.WHITE);
		textCard3Croupier.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 16));
		textCard3Croupier.setBounds(556, 100, 36, 14);
		frame.getContentPane().add(textCard3Croupier);
		
		
		textCard2Croupier.setForeground(Color.WHITE);
		textCard2Croupier.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard2Croupier.setBounds(498, 98, 35, 14);
		frame.getContentPane().add(textCard2Croupier);
		textCard1Croupier.setForeground(Color.WHITE);

		textCard1Croupier.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard1Croupier.setBounds(442, 98, 24, 14);
		frame.getContentPane().add(textCard1Croupier);
		textCard5Player.setForeground(Color.WHITE);

		textCard5Player.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard5Player.setBounds(679, 483, 24, 14);
		frame.getContentPane().add(textCard5Player);
		textCard4Player.setForeground(Color.WHITE);

		textCard4Player.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard4Player.setBounds(621, 481, 22, 14);
		frame.getContentPane().add(textCard4Player);
		textCard3Player.setForeground(Color.WHITE);

		textCard3Player.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard3Player.setBounds(557, 481, 27, 14);
		frame.getContentPane().add(textCard3Player);
		textCard2Player.setForeground(Color.WHITE);

		textCard2Player.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard2Player.setBounds(499, 481, 34, 14);
		frame.getContentPane().add(textCard2Player);
		textCard1Player.setForeground(Color.WHITE);
		
		textCard1Player.setFont(new Font("Andale Mono", Font.PLAIN, 16));
		textCard1Player.setBounds(443, 481, 23, 14);
		frame.getContentPane().add(textCard1Player);

		card5Player.setIcon(new ImageIcon(BJack.class.getResource("/Images/12.png")));
		card5Player.setBounds(655, 475, 87, 118);
		frame.getContentPane().add(card5Player);

		card4Player.setIcon(new ImageIcon(BJack.class.getResource("/Images/13.png")));
		card4Player.setBounds(596, 475, 87, 118);
		frame.getContentPane().add(card4Player);

		card3Player.setIcon(new ImageIcon(BJack.class.getResource("/Images/12.png")));
		card3Player.setBounds(528, 475, 87, 118);
		frame.getContentPane().add(card3Player);

		card2Player.setIcon(new ImageIcon(BJack.class.getResource("/Images/11.png")));
		card2Player.setBounds(474, 475, 87, 118);
		frame.getContentPane().add(card2Player);
		
		card1Player.setIcon(new ImageIcon(BJack.class.getResource("/Images/12.png")));
		card1Player.setBounds(422, 475, 90, 118);
		frame.getContentPane().add(card1Player);
		
		card5Croupier.setIcon(new ImageIcon(BJack.class.getResource("/Images/11.png")));
		card5Croupier.setBounds(654, 92, 88, 118);
		frame.getContentPane().add(card5Croupier);
		
		card4Croupier.setIcon(new ImageIcon(BJack.class.getResource("/Images/12.png")));
		card4Croupier.setBounds(592, 92, 80, 118);
		frame.getContentPane().add(card4Croupier);
		
		card3Croupier.setIcon(new ImageIcon(BJack.class.getResource("/Images/13.png")));
		card3Croupier.setBounds(532, 92, 79, 118);
		frame.getContentPane().add(card3Croupier);
		
		card2Croupier.setIcon(new ImageIcon(BJack.class.getResource("/Images/12.png")));
		card2Croupier.setBounds(472, 92, 83, 118);
		frame.getContentPane().add(card2Croupier);
		
		card1Croupier.setIcon(new ImageIcon(BJack.class.getResource("/Images/11.png")));
		card1Croupier.setBounds(418, 92, 83, 118);
		frame.getContentPane().add(card1Croupier);
		BackGround.setForeground(Color.WHITE);
		BackGround.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		BackGround.setIcon(new ImageIcon(BJack.class.getResource("/Images/BackGround.png")));
		BackGround.setBounds(0, 0, 1107, 652);
		frame.getContentPane().add(BackGround);
	}
}
