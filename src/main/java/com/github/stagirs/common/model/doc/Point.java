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
package com.github.stagirs.common.model.doc;

import com.github.stagirs.common.HashUtils;
import com.github.stagirs.common.model.Tag;
import java.util.List;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Point extends Item{
    protected List<String> sentences;

    public Point() {
    }

    public Point(List<String> list) {
        this.sentences = list;
    }
    
    @Override
    public String text() {
        if(sentences == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String section : sentences) {
            sb.append(section);
        }
        return sb.toString();
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }
}
