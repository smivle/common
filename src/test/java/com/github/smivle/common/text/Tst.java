package com.github.smivle.common.text;

import com.github.stagirs.common.model.doc.Point;
import com.github.stagirs.common.model.doc.Section;
import com.github.stagirs.common.text.SentenceExtractor;
import com.github.stagirs.common.text.TextUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 06.12.2016.
 */
public class Tst {
    @org.junit.Test
    public void test() throws IOException {
        //Section sec = new Section;
        StringBuilder pointText = null;
        pointText = new StringBuilder("g_{11}(\\Vec r_{\\xi\\xi}+P\\Vec r_\\xi)+g_{22}(\\Vec r_{\\eta\\eta} ");
        //s.matches("\\w*");
        //String ns = s.substring(s.lastIndexOf('{') + 1,s.lastIndexOf('}'));
        List<String> myList = new ArrayList<String>();
        myList.add(pointText.toString());
        Point pnt = new Point(myList);
        //sec.getPoints().add(pointText);
        //System.out.println(pointText);
    }

    public Point getPoint(StringBuilder pointText) {
        return new Point(SentenceExtractor.get().extract(TextUtils.charactersClutch(pointText.toString()).replaceAll("\\s+", " ")));
    }
}
