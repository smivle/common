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
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Dmitriy Malakhov
 */
public class Store<T> {
    private File file;
    private ObjectMapper om = new ObjectMapper();

    public Store(File file) {
        file.delete();
        this.file = file;
    }
    
    public void save(T item){
        try {
            FileUtils.writeStringToFile(file, om.writeValueAsString(item) + "\n", "utf-8", true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
