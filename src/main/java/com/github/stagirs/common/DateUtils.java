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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal<SimpleDateFormat>(){
        @Override
        public SimpleDateFormat get() {
            SimpleDateFormat sdf = super.get();
            if(sdf == null){
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                set(sdf);
            }
            return sdf;
        }
        
    };
    
    public static String format(Date date){
        return SDF.get().format(date);
    }
    
    public static Date parse(String date){
        try {
            return SDF.get().parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
