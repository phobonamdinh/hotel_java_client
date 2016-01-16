package controler;

import util.Config;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TienDQ on 1/13/16.
 */
public class ParsingThreadExecutor {
    private List<String> mListLink;

    public ParsingThreadExecutor(List<String> listLink) {
        this.mListLink = listLink;
        System.out.println(mListLink.toString());
    }

    public void run(){
        if (mListLink != null) {
            System.out.println("========== Parsing started ==========");

            ExecutorService executor = Executors.newFixedThreadPool(Config.MAX_NUM_OF_THREAD);
            // comment
            for (String link: mListLink){
                ParsingThread parsingThread = new ParsingThread(link);
                executor.execute(parsingThread);
            }
            executor.shutdown();

            while (!executor.isTerminated()){}

            System.out.println("========== Parsing finished ==========");
        } else {
            System.out.println("========== Nothing to parsing ==========");
        }
    }
}
