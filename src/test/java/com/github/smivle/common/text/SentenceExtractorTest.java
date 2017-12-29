package com.github.smivle.common.text;

import com.github.stagirs.common.text.SentenceExtractor;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author pc
 */
public class SentenceExtractorTest {
    
    @Test
    public void extractTest(){
        List<String> list = SentenceExtractor.extract("общая психология \nАвтореферат \nдиооѳртации М В Ломоносов");
        assertEquals(list.get(0), "общая психология \n.");
        assertEquals(list.get(1), "Автореферат \nдиооѳртации . М . В . Ломоносов");
    }
}
