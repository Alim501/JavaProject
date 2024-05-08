import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            DBManager manager= new DBManager();
            manager.connect();
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream= new ObjectInputStream(socket.getInputStream());

            PackageData pd=null;
            while ((pd=(PackageData) inputStream.readObject())!=null ){
                if (pd.getOperation().equals("ADD")){
                    Clothes clothesFromClient= pd.getCloth();
                    manager.addCloth(clothesFromClient);
                }else if(pd.getOperation().equals("LIST")){
                    ArrayList<Clothes> infoForClient=manager.getAllClothes();
                    PackageData toClient =new PackageData(infoForClient);
                    outputStream.writeObject(toClient);
                }
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
