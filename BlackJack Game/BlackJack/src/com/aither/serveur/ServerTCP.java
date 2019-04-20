package com.aither.serveur; 
import java.net.*;
import java.util.ArrayList;
import java.io.*;
import com.aither.serveur.Participant;


public class ServerTCP extends Thread { 
	
	//Désclarations
	private Participant Participant1;
	private Socket socket;
	final static int port = 9632; //Numero du port
	private static ArrayList<PrintStream> clients = new ArrayList<PrintStream>();
	public String joueur1;
	public String joueur2;
	public String joueur3;
	public String mise;
	int compteur=0;

	public static void main(String[] args) { 
		try {
			ServerSocket socketServeur = new ServerSocket(port); //Creation d'une socket
			System.out.println("Lancement du serveur");			// Annonce du lancement

			while (true) {
				Socket socketClient = socketServeur.accept(); //Attente d'une connexion
				ServerTCP Server = new ServerTCP(socketClient); 	 
				Server.start();
			}
		} 
		catch (Exception e) {
			e.printStackTrace(); 
		}
	}


	public ServerTCP(Socket socket) { 
		this.socket = socket; 
	}

	public void run() { 
		traitements();
	}
	private void createJoueur(String name) {
		Participant1.setNom(name);
		Participant1.setMise(mise);
	}
	
	
	// Mise en place de la communication client serveur
	public void traitements() { 
		try {
			String message = "";
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			PrintStream out = new PrintStream(socket.getOutputStream());
			ServerTCP.clients.add(out);
			if (compteur==0) {
			System.out.println("Connexion en cours avec le client IP : " + socket.getInetAddress());
			System.out.println("Le client IP "+socket.getInetAddress()+" commence une partie."); }
				for (PrintStream client : clients) {
					while(true) {
					message = in.readLine();
					createJoueur(message);
					System.out.println(message);
					}
					
				}
			
		} 
		catch (Exception e) { 
			e.printStackTrace();
		} 
	}
}