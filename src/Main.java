import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static MainFrame frame;

    public static void connect(PackageData pd){
        try {
            Socket socket=new Socket("127.0.0.1",8888);
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream= new ObjectInputStream(socket.getInputStream());

            if(pd.getOperation().equals("ADD")){
                outputStream.writeObject(pd);
            }
            else if(pd.getOperation().equals("LIST")){

                outputStream.writeObject(pd);
                PackageData infoFromServer= (PackageData) inputStream.readObject();
                ArrayList<Clothes> arrayListFromServer=infoFromServer.getClothes();
                String s="";
                if (arrayListFromServer != null) {
                    for (int i = 0; i < arrayListFromServer.size(); i++) {
                        s+=arrayListFromServer.get(i).toString()+"\n";
                    }
                    ListClothes.text.append(s);
                }

            }
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        frame=new MainFrame();
        frame.setVisible(true);
    }
}