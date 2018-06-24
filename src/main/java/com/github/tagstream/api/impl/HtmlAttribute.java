/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tagstream.api.impl;

import com.github.tagstream.api.ElementAttribute;

public class HtmlAttribute implements ElementAttribute {

    private String name;
    private String value;
    private boolean isQuoted;
    private char quotes;

    public HtmlAttribute(String n) {
        setName(n);
    }

    public HtmlAttribute(String name, String value) {
        setName(name);
        if (value != null) {
            setValue(value);
        }
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param value the value to set, may be null
     */
    public void setValue(String value) {
        this.value = value;
        setQuoted();
        if (isQuoted) {
            dequotValue();
        }
    }
    
    public String getValue() { 
        return value;
    }
    

    public boolean isValueAssigned() {
        return value != null;
    }
    
    private void setQuoted() {
        if (!isValueAssigned()) {
            return;
        }
        char first = value.charAt(0);
        isQuoted = (first == '\'' || first =='"') && value.charAt(value.length() - 1) == first;
        if (isQuoted) {
            quotes = first;
        }
    }
    private void dequotValue() {
        value = value.substring(1, value.length() - 1);
    }
    
    public boolean isQuoted() {
        return isQuoted;
    }
    
    public String getQuoted() {
        if (isQuoted) {
            return quotes + value + quotes;
        }
        return value;
    }
    

}