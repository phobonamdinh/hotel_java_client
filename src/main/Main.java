package main;

import controler.ParsingThreadExecutor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.Config;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String booking = Config.HTTP_ROOT + Config.BASE_DOMAIN_BOOKING + "/searchresults.html?aid=397594;label=gog235jc-index-XX-XX-XX-unspec-vn-com-L%3Axu-O%3Aunk-B%3Aunk-N%3AXX-S%3Abo-U%3Ac;sid=e98491d12f74becf8119765f9501dfee;dcid=4;class_interval=1&csflt=%7B%7D&dest_id=-3714993&dest_type=city&dtdisc=0&group_adults=2&group_children=0&hlrd=0&hyb_red=0&inac=0&label_click=undef&nha_red=0&no_rooms=1&offset=0&offset_unavail=1&redirected_from_city=0&redirected_from_landmark=0&redirected_from_region=0&review_score_group=empty&room1=A%2CA&sb_price_type=total&score_min=0&si=ai%2Cco%2Cci%2Cre%2Cdi&src=index&ss=Ha%CC%80%20N%C3%B4%CC%A3i%2C%20H%C3%A0%20N%E1%BB%99i%2C%20Vi%C3%AA%CC%A3t%20Nam&ss_all=0&ss_raw=ha%20no&ssafas=2&ssb=empty&sshis=0&";
    public static final String diachiso = Config.HTTP_ROOT + Config.BASE_DOMAIN_DIACHISO + "/ha-noi/khach-san.html";

    public static void main(String[] args) {
        List<String> listLink = new ArrayList<>();
        listLink.add(booking);
        listLink.add(diachiso);

        run(listLink);
    }

    public static void run(List<String> listLink){
        if (listLink != null && listLink.size() > 0){
            for (String link: listLink){
                if (link != null && link.contains(Config.BASE_DOMAIN_BOOKING)){
                    runForBooking(link);
                } else if (link != null && link.contains(Config.BASE_DOMAIN_DIACHISO)){
                    runForDiaChiSo(link);
                }
            }
        }
    }

    public static void runForDiaChiSo(String linkOfListAddress){
        try{
            List<String> linksToParsing = new ArrayList<>();
            Document doc = Jsoup.connect(linkOfListAddress).get();

            Elements elements = doc.select("h2.listing-name");
            if (elements != null){
                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(e.attr("href"));
                }

                ParsingThreadExecutor executor = new ParsingThreadExecutor(linksToParsing);
                executor.run();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void runForBooking(String linkOfListAddress){
        try {
            List<String> linksToParsing = new ArrayList<>();
            Document doc = Jsoup.connect(linkOfListAddress).get();

            Elements elements = doc.select("a.hotel_name_link");

            if (elements != null) {
                for (int i = 0; i < elements.size(); i++) {
                    Element e = elements.get(i).select("a[href]").first();
                    linksToParsing.add(Config.HTTP_ROOT + e.attr("href"));
                }

                ParsingThreadExecutor executor = new ParsingThreadExecutor(linksToParsing);
                executor.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
