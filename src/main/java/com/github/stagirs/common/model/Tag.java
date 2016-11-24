/*
 * Copyright 2016 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.common.model;

import com.github.stagirs.common.text.TextUtils;
import gnu.trove.map.hash.TObjectIntHashMap;
import java.util.List;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Tag {
    private long hash;
    private String text;
    private List<String> terms;
    private String docId;
    private String clusterId;
    private double semantic = 1;

    public Tag() {
    }

    public Tag(long hash, String text, String docId) {
        this.hash = hash;
        this.text = text;
        this.docId = docId;
        this.clusterId = "";
        this.terms = TextUtils.splitWords(text, true);
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
    
    

    public long getHash() {
        return hash;
    }


    public void setHash(long hash) {
        this.hash = hash;
    }

    public double getSemantic() {
        return semantic;
    }

    public String getText() {
        return text;
    }

    public void setSemantic(double semantic) {
        this.semantic = semantic;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }
    
    public void addCluster(int id){
        if(clusterId.isEmpty()){
            clusterId += id;
        }else{
            clusterId += " " + id;
        }
    }
    
    public TObjectIntHashMap<String> termsMap(){
        TObjectIntHashMap<String> termsMap = new TObjectIntHashMap<>();
        for (String word : getTerms()) {
            termsMap.adjustOrPutValue(word, 1, 1);
        }
        return termsMap;
    }
}
