package util;

import com.google.gson.Gson;

import java.io.*;

/**
 * Created by TienDQ on 1/17/16.
 */
public class ObjectUtil {
    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static void writeToFile(Object object){
        File outFile = new File("hotel.json");
        if (outFile.exists()){
            outFile.delete();
        }

        try {
            FileOutputStream fileStream = new FileOutputStream(new File("hotel.json"));
            OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
            writer.write(toJson(object));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
