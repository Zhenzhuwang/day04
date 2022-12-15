import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientServer {
    public static void main(String[] args){
        int PORT = 12345;
        try{
            Socket cs = new Socket("localhost",PORT);

            //Get the I/O Streams
            OutputStream os = cs.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // dos.writeUTF("Hello world ,");
            // dos.writeUTF("from client!");
            // dos.flush();
            // System.out.println("Message sent to Server!");
            // cs.isClosed();


            //if want to be able to keep sending msges
            Scanner inputSc = new Scanner(System.in);
            String line;
            while ((line = inputSc.nextLine()) != null) {
                if (line.equalsIgnoreCase("close")){
                    System.out.println("Exit from shell");
                    dos.writeUTF("close");
                    dos.flush();
                    break;
                }

                dos.writeUTF(line);
                dos.flush();
                System.out.println("Msg sent to client " + line);
            }
            cs.close(); //closing the socket from client.
            inputSc.close();

        }catch (UnknownHostException e){
            System.out.println("Unable to reach the Host! ");

        }catch (IOException e){
            System.out.println("IO Error!");
        }
    }
}
