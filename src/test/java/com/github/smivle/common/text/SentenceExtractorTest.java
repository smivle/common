package com.github.smivle.common.text;

import com.github.stagirs.common.text.SentenceExtractor;

import java.util.Iterator;
import java.util.List;

import com.github.stagirs.common.text.TextUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author pc
 */
public class SentenceExtractorTest {
    
    @Test
    public void extractTest(){
        SentenceExtractor tp = SentenceExtractor.get();
        List<String> list = tp.extract("общая психология \nАвтореферат \nдиооѳртации М В Ломоносов");
        Iterator sent = list.iterator();
        while(sent.hasNext()){
            Object elem = sent.next();
            System.out.println(elem);
        }

        //System.out.println(TextUtils.splitWords("красныйковер",true));
        //assertEquals(list.size(), 3);
    }
}
