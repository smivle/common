package com.github.smivle.common.text;

import com.github.stagirs.common.Store;
import com.github.stagirs.common.StoreIterator;
import com.github.stagirs.common.model.doc.Document;
import com.github.stagirs.common.model.doc.Point;
import com.github.stagirs.common.model.doc.Section;
import com.github.stagirs.common.text.SentenceExtractor;
import com.github.stagirs.common.text.TextUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 * Created by Vladimir on 04.12.2016.
 */
public class ParserTest {
    @Test
    public void test() throws IOException {
        String diskPath = "../docs/MAGAZINE_IZV_Vuzov/";
        String folders1 = "collection/1997/12/";
        String folders2 = "collection-udk/518/";
        String folders = folders1;
        String path = diskPath + folders;
        File dir = new File(path);

        DirTree(new File(diskPath));
        System.out.println("Done!");
    }

    public void Parse(File file) throws IOException{

        //System.out.println("Parsing" + file.getName());

        List<String> list = new ArrayList<>();
        String cp = "cp866";
        list.addAll(FileUtils.readLines(file, cp));

        String meta = "";
        String meta_start = "\\god";
        String begin = "\\begin{document}";
        Integer beginPos = 0;
        String title = "";
        String author = "";

        //Finding meta
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(meta_start)) {
                for (int j = i; j < i + 3; j++) {
                    meta += list.get(j).substring(list.get(j).lastIndexOf('\\') + 1) + ",";
                }
            }
            if(list.get(i).contains("\\author")){
                author = list.get(i).substring(list.get(i).lastIndexOf("\\it") + 4, list.get(i).lastIndexOf('}'));
            }

            if (list.get(i).contains("\\title") || list.get(i).contains("{\\bf")) {
                for (int j = i; true; j++) {
                    title += list.get(j);
                    if(list.get(j).contains("}")){
                        break;
                    }
                }
            }

            if (list.get(i).contains(begin)) {
                beginPos = i;
                break;
            }
        }

        if(title != "") {
            //Deleting Tex marking
            //System.out.println(title);
            title = title.substring(title.lastIndexOf('{') + 1, title.lastIndexOf('}'));
            title = title.replace("\\\\", " ");
        }


        Document doc = new Document(meta);
        doc.setId(file.getName());
        doc.setAuthor(author);

        Section currentSection = null;
        StringBuilder pointText = null;

        Integer formula = 0;
        Boolean needsCheck = false;

        //Parsing
        for (int i = beginPos + 2; i < list.size(); i++) {
            if (list.get(i).matches("\\s*")) {
                //needsCheck = true;
                continue;
            }
            needsCheck = false;
            if (list.get(i).contains("\\section") || list.get(i).contains("\\maketitle")) {
                needsCheck = true;
            }
            if (list.get(i).contains("\\begin{") || list.get(i).contains("\\end{")) {
                if (list.get(i).contains("\\begin{")) {
                    if (formula == 0) {
                        formula = 1;
                        if (pointText != null) {
                            currentSection.getPoints().add(getPoint(pointText));
                        }
                        pointText = new StringBuilder(list.get(i));
                    } else {
                        formula += 1;
                    }
                } else {
                    formula -= 1;
                }
            }
            if (list.get(i - 1).matches("\\s*") || (formula == 0 && list.get(i - 1).contains("\\end{")) || needsCheck) {
                if (list.get(i).contains("\\section") || list.get(i).contains("\\maketitle")) {
                    if (pointText != null && currentSection != null) {
                        currentSection.getPoints().add(getPoint(pointText));
                    }
                    if (list.get(i).contains("\\maketitle")) {
                        doc.getSections().add(currentSection = new Section(title));
                    } else {
                        String section = "";
                        for (int j = i; !list.get(j).matches("\\s*"); j++) {
                            section += list.get(j);
                        }
                        doc.getSections().add(currentSection = new Section(section.substring(list.get(i).lastIndexOf('{') + 1, section.lastIndexOf('}'))));
                    }
                    pointText = new StringBuilder(list.get(i));
                } else {
                    if (currentSection == null) {
                        doc.getSections().add(currentSection = new Section(null));
                    }
                    if (pointText != null) {
                        if (list.get(i - 1).matches("\\s*")) {
                            currentSection.getPoints().add(getPoint(pointText));
                        } else {
                            List<String> strLst = new ArrayList<String>();
                            strLst.add(pointText.toString());
                            Point pnt = new Point(strLst);
                            currentSection.getPoints().add(pnt);
                        }
                    }
                    pointText = new StringBuilder(list.get(i));
                }
            } else {
                //System.out.println(list.get(i));
                //System.out.println(pointText)   ;
                pointText.append(list.get(i));
            }
        }
        if (pointText != null && currentSection != null) {
            currentSection.getPoints().add(getPoint(pointText));
        }

        String saveDir = "../docs/json/MAGAZINE_IZV_Vuzov/";
        String json = ".json";
        File storeFile = new File(saveDir + file.getName() + json);
        //File filepath = new File(path + file.getName());
        Store store = new Store(storeFile);

        store.save(doc);


        //StoreIterator itr = new StoreIterator(filepath,doc.getClass());

        //itr.

        //System.out.println(file.getName() + " successfully parsed");
    }

    public Point getPoint(StringBuilder pointText){
        return new Point(SentenceExtractor.get().extract(TextUtils.charactersClutch(pointText.toString()).replaceAll("\\s+", " ")));
    }

    public  void DirTree(File node) throws IOException{
        if (node.isDirectory()) {
            System.out.println("Entering " + node.getPath() + " folder.");

            String[] subNote = node.list();
            for (String filename : subNote) {
                DirTree(new File(node, filename));
            }
        }
        else{
            if (node.getName().matches("\\d*-\\d*-*\\d*.(TEX|tex)")) {
                //System.out.println(node.getPath());
                Parse(node);
            }
        }
    }
}

