package code14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class GameClient {
	int port;
	String ip;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ArrayList<Tower> theirTowers;
	ArrayList<StrippedEnemies> theirEnemies;
	public GameClient (String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		socket = new Socket(ip, port);
	}
	
	@SuppressWarnings("unchecked")
	public void transfer(ArrayList<Tower> myTowers, ArrayList<StrippedEnemies> myEnemies) throws ClassNotFoundException, IOException {
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
		//Entities stats = (Entities) ois.readObject();
		theirTowers = (ArrayList<Tower>) ois.readObject();
		theirEnemies = (ArrayList<StrippedEnemies>) ois.readObject();
		
		oos.writeObject(myTowers);
		oos.flush();
		oos.writeObject(myEnemies);
		oos.flush();
	}
	
	public int getPort() {
		return port;
	}
	
	public String getIP() {
		return ip;
	}
	
	public ArrayList<Tower> getTowers()
	{
		return theirTowers;
	}
	
	public ArrayList<StrippedEnemies> getEnemies()
	{
		return theirEnemies;
	}
}
