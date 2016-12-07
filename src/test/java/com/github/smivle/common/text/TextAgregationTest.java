package com.github.smivle.common.text;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stagirs.common.model.doc.Section;
import com.github.stagirs.common.model.doc.Document;
import com.github.stagirs.common.model.doc.Point;
import com.github.stagirs.common.text.SentenceExtractor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import com.github.stagirs.common.text.TextUtils;

/**
 *
 * @author pc
 */
public class TextAgregationTest {
    //Строка начинается с большой буквы, в конце точки нет, следующая строка также с большой буквы, но потом с маленькой - первая строка тайтл
    //Строка заканчивается точкой - конец абзаца
    @Test
    public void test() throws IOException{
        File file = new File("W:/Дисcертация/docs");
        file.delete();
        for (File bookDir : new File("W:/Дисcертация/text").listFiles()) {
            
            List<String> list = new ArrayList<>();
            String meta = "";
            for (File filePart : bookDir.listFiles()) {
                if(filePart.getName().equals("1")){
                    meta = TextUtils.charactersClutch(FileUtils.readFileToString(filePart, "utf-8")).replaceAll("\\s+", " ");
                }
                list.addAll(FileUtils.readLines(filePart, "utf-8"));
            }
            Document doc = new Document(meta);
            doc.setId(bookDir.getName());
            if(list.isEmpty()){
                continue;
            }
            Section currentSection = null;
            StringBuilder pointText = null;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).isEmpty()){
                    continue;
                }
                if(pointText == null || Character.isUpperCase(list.get(i).charAt(0))){
                    //новый абзац или раздел
                    if(i + 1 < list.size() && (!list.get(i + 1).isEmpty() && Character.isUpperCase(list.get(i + 1).charAt(0)))){
                        //раздел
                        if(pointText != null && currentSection != null){
                            currentSection.getPoints().add(getPoint(pointText));
                        }
                        doc.getSections().add(currentSection = new Section(list.get(i)));
                    }else{
                        //абзац
                        if(currentSection == null){
                            doc.getSections().add(currentSection = new Section(null));
                        }
                        if(pointText != null){
                            currentSection.getPoints().add(getPoint(pointText));
                        }
                        pointText = new StringBuilder(list.get(i));
                    }
                }else{
                    pointText.append(list.get(i));
                }
            }
            if(pointText != null && currentSection != null){
                currentSection.getPoints().add(getPoint(pointText));
            }
            FileUtils.write(file, new ObjectMapper().writeValueAsString(doc) + "\n", "utf-8", true);
        }
    }
    
    public Point getPoint(StringBuilder pointText){
        return new Point(SentenceExtractor.get().extract(TextUtils.charactersClutch(pointText.toString()).replaceAll("\\s+", " ")));
    }
}
