package main;

import controler.IOnParsingControlState;
import controler.ParsingThreadExecutor;
import model.Hotel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Config;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienDQ on 1/17/16.
 */
public class MainControl implements IOnParsingControlState{
    private List<String> mListLinks;
    private List<Hotel> mListHotels;
    private List<String> mListLinksToParsing;

    public MainControl(List<String> listLinks) {
        mListLinks = listLinks;
        mListHotels = new ArrayList<>();
        mListLinksToParsing = new ArrayList<>();
    }

    public void run(){
        if (mListLinks != null && mListLinks.size() > 0){
            for (String link: mListLinks){
                List<String> links = null;
                if (link != null && link.contains(Config.BASE_DOMAIN_BOOKING)){
                    links = getListLinksFromBooking(link);
                } else if (link != null && link.contains(Config.BASE_DOMAIN_DIACHISO)){
                    links = getListLinksFromDiaChiSo(link);
                }

                if (link != null && links.size() > 0){
                    mListLinksToParsing.addAll(links);
                }
            }

            ParsingThreadExecutor executor = new ParsingThreadExecutor(mListLinksToParsing, this);
            executor.run();
        }else {
            System.out.println("========== Nothing to parsing ==========");
        }
    }

    public List<String> getListLinksFromDiaChiSo(String linkOfListAddress){
        try{
            Document doc = Jsoup.connect(linkOfListAddress).get();

            Elements elements = doc.select("h2.listing-name");
            if (elements != null){
                List<String> linksToParsing = new ArrayList<>();

                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(e.attr("href"));
                }

                return  linksToParsing;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<String> getListLinksFromBooking(String linkOfListAddress){
        try {
            Document doc = Jsoup.connect(linkOfListAddress).get();

            Elements elements = doc.select("a.hotel_name_link");

            if (elements != null) {
                List<String> linksToParsing = new ArrayList<>();

                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(Config.HTTP_ROOT + Config.BASE_DOMAIN_BOOKING + e.attr("href"));
                }

                return  linksToParsing;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onParsingStart(String linkParsing) {
        System.out.println("Start width: " + linkParsing);
    }

    @Override
    public void onParsingDoneWithAnObject(String linkParsing, Hotel hotel) {
        if (hotel != null)
            mListHotels.add(hotel);

        System.out.println("Stop width: " + linkParsing);
    }

    @Override
    public void onParsingFinish() {
        ObjectUtil.writeToFile(mListHotels);
    }
}
