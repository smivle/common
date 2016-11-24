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

import com.github.stagirs.common.model.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author Dmitriy Malakhov
 */
public class Document{
    private String id;
    private String author;
    private Date createDate;
    private String meta;
    private List<Section> sections;

    public Document() {
    }

    public Document(String meta) {
        this.meta = meta;
        this.sections = new ArrayList<>();
    }
    
    public String getAuthor() {
        return author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
