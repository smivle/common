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
import java.util.List;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Section extends Item{
    protected String title;
    protected List<Point> points;

    public Section() {
    }

    public Section(String title) {
        this.title = title;
        this.points = new ArrayList<>();
    }
    
    @Override
    public String text() {
        if(points == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if(title != null){
            sb.append(title);
        }
        for (Item section : points) {
            sb.append(section);
        }
        return sb.toString();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
