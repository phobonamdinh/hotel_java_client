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
    private IOnParsingControlState mOnParsingControlState;

    public ParsingThreadExecutor(List<String> listLink, IOnParsingControlState onParsingControlState) {
        this.mListLink = listLink;
        this.mOnParsingControlState = onParsingControlState;
        System.out.println(mListLink.toString());
    }

    public void run(){
        if (mListLink != null && mListLink.size() > 0) {
            if (mOnParsingControlState != null){
                ExecutorService executor = Executors.newFixedThreadPool(Config.MAX_NUM_OF_THREAD);

                for (String link: mListLink){
                    ParsingThread parsingThread = new ParsingThread(link, mOnParsingControlState);
                    executor.execute(parsingThread);
                }
                executor.shutdown();

                while (!executor.isTerminated()){}

                mOnParsingControlState.onParsingFinish();
            }
        } else {
            System.out.println("========== Nothing to parsing ==========");
        }
    }
}
