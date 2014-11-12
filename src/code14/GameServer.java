package code14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	int port;
	ServerSocket serverSocket;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ArrayList<Tower> theirTowers;
	ArrayList<StrippedEnemies> theirEnemies;
	public GameServer(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		System.out.println("Connected");
		oos = new ObjectOutputStream(
				socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}

	@SuppressWarnings("unchecked")
	public void transfer(ArrayList<Tower> myTowers, ArrayList<StrippedEnemies> myEnemies) throws IOException, ClassNotFoundException {
		oos = new ObjectOutputStream(
				socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
		oos.writeObject(myTowers);
		oos.flush();
		oos.writeObject(myEnemies);
		oos.flush();
		
		theirTowers = (ArrayList<Tower>) ois.readObject();
		theirEnemies = (ArrayList<StrippedEnemies>) ois.readObject();
	}
	
	public int getPort() {
		return port;
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
