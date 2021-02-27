package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileApi extends File {

    static BufferedWriter writer = null;
    static String PATH = "C:\\Users\\User\\Desktop\\projects\\chat_kvazi_28012021\\server\\src\\main\\java\\server\\chatHistory.txt";
    public static File file = new File(PATH);

    public FileApi() {
        super(PATH);
    }
    public static void write(String msg) {
        try
        {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write( msg);
            writer.newLine();
        }
        catch ( IOException e){}
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }

    public static List<String> read() throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        return list;
    }

    public static void cutTheFile() throws IOException {
        List<String> list = new ArrayList<>();
        list = read();
        if (list.size() <= 100){
            return;
        } else {
            for (int i = 100; i < list.size(); i++) {
                list.remove(i);
            }
        }
        for (String msg: list) {
            write(msg);
        }
    }
}
