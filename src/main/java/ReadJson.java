import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.commons.io.output.TeeOutputStream;

public class ReadJson {
    public ReadJson()  {
    }

    public static void main(String[] args) throws IOException {

        String first = "/Users/k.dzhigante/Downloads/new2.json";
        List<String> list = new ArrayList<String>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(contents);
            JSONArray cis = o.getJSONArray("products");

            for (int i =0; i < cis.length(); i++) {
                list.add(cis.getJSONObject(i).getString("uit_code"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);

        System.setOut(new PrintStream(teeStream));
        System.out.println(list);
        try(OutputStream fileStream = new FileOutputStream("cis.txt")) {
            buffer.writeTo(fileStream);
        }

    }

}