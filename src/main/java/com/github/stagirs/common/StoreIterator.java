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
package com.github.stagirs.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Dmitriy Malakhov
 */
public class StoreIterator<K> implements Iterator<K>{
    private ObjectMapper om = new ObjectMapper();
    private Class<K> c;
    private BufferedReader br;
    private String line;

    public StoreIterator(File file, Class<K> c) {
        this.c = c;
        try {
            this.br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            line = br.readLine();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }

    @Override
    public boolean hasNext() {
        return line != null;
    }

    @Override
    public K next() {
        try {
            String current = line;
            line = br.readLine();
            return om.readValue(current, c);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void close(){
        try {
            br.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<K> toList(){
        List<K> list = new ArrayList<>();
        while(hasNext()){
            list.add(next());
        }
        close();
        return list;
    }
}
