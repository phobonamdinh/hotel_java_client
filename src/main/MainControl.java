package main;

import controler.IOnParsingControlState;
import controler.ParsingThreadExecutor;
import model.Hotel;
import model.LinkNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.AppUtil;
import util.Config;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienDQ on 1/17/16.
 */
public class MainControl implements IOnParsingControlState{
    private List<LinkNode> mListLinkNodes;
    private List<Hotel> mListHotels;
    private List<String> mListLinksToParsing;

    public MainControl(List<LinkNode> listLinkNodes) {
        mListLinkNodes = listLinkNodes;
        mListHotels = new ArrayList<>();
        mListLinksToParsing = new ArrayList<>();
    }

    public void run(){
        if (mListLinkNodes != null && mListLinkNodes.size() > 0){
            System.out.println("========== Parsing is started ==========");
            for (LinkNode linkNode: mListLinkNodes){
                if (linkNode != null && linkNode.getType() == LinkNode.TYPE_BOOKING){
                    getListLinksFromBooking(linkNode);
                } else if (linkNode != null && linkNode.getType() == LinkNode.TYPE_DIACHISO){
                    getListLinksFromDiaChiSo(linkNode);
                } else if (linkNode != null && linkNode.getType() == LinkNode.TYPE_IVIVU){
                    getListLinksFromIvivu(linkNode);
                }

                if (linkNode != null && linkNode.getCurrentLink()!= null && linkNode.getListLinksToParsing() != null){
                    mListLinksToParsing.addAll(linkNode.getListLinksToParsing());
                }
            }

            if (mListLinksToParsing != null && mListLinksToParsing.size() > 0){
                ParsingThreadExecutor executor = new ParsingThreadExecutor(mListLinksToParsing, this);
                executor.run();
            } else {
                System.out.println("=====================================");
                System.out.println("Result:");
                for (LinkNode linkNode: mListLinkNodes){
                    if (linkNode.getType() == LinkNode.TYPE_BOOKING)
                        System.out.println("- " + Config.BASE_DOMAIN_BOOKING + ": " + linkNode.getNumOfLinkIsParsed() + " pages");
                    else if (linkNode.getType() == LinkNode.TYPE_DIACHISO)
                        System.out.println("- " + Config.BASE_DOMAIN_DIACHISO + ": " + linkNode.getNumOfLinkIsParsed() + " pages");
                    else if (linkNode.getType() == LinkNode.TYPE_IVIVU)
                        System.out.println("- " + Config.BASE_DOMAIN_IVIVU + ": " + linkNode.getNumOfLinkIsParsed() + " pages");
                }
            }
        }else {
            System.out.println("========== Nothing to parsing ==========");
        }
    }

    public void getListLinksFromDiaChiSo(LinkNode linkNode){
        if (linkNode.getCurrentLink() == null) return;

        try{
            Document doc = Jsoup.connect(linkNode.getCurrentLink()).timeout(Config.CONNECT_TIMEOUT).get();

            Elements elements = doc.select("h2.listing-name");
            if (elements != null){
                List<String> linksToParsing = new ArrayList<>();

                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(e.attr("href"));
                }

                linkNode.setListLinksToParsing(linksToParsing);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getListLinksFromBooking(LinkNode linkNode){
        if (linkNode.getCurrentLink() == null) return;

        try {
            Document doc = Jsoup.connect(linkNode.getCurrentLink()).timeout(Config.CONNECT_TIMEOUT).get();

            Elements elements = doc.select("a.hotel_name_link");

            if (elements != null) {
                List<String> linksToParsing = new ArrayList<>();

                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(Config.HTTP_ROOT + Config.BASE_DOMAIN_BOOKING + e.attr("href"));
                }

                linkNode.setListLinksToParsing(linksToParsing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getListLinksFromIvivu(LinkNode linkNode){
        if (linkNode.getCurrentLink() == null) return;

        try{
            AppUtil.enableSSLSocket();
            Document doc = Jsoup.connect(linkNode.getCurrentLink()).timeout(Config.CONNECT_TIMEOUT).get();

            Elements elements = doc.select("div.place-hotel-item-name");
            if (elements != null){
                List<String> linksToParsing = new ArrayList<>();

                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add("https:" + e.attr("href"));
                }

                linkNode.setListLinksToParsing(linksToParsing);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
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
        System.out.println("========== Parsing is finished ==========");

        mListHotels.clear();
        mListLinksToParsing.clear();
        for (LinkNode linkNode : mListLinkNodes) {
            linkNode.setCurrentLink(linkNode.getNextLink());
        }

        run();
    }
}
