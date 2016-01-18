package controler;

import model.Hotel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by TienDQ on 1/13/16.
 */
public abstract class ParsingControl {
    protected String mLink;

    public ParsingControl(String mLink) {
        this.mLink = mLink;
    }

    public Document loadData(){
        try {
            return Jsoup.connect(mLink).timeout(20*1000).get();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public abstract Hotel parseHotel(Document document);
}
