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
import com.github.tagstream.util.HtmlEntityTranslator;

public class TagAttribute implements ElementAttribute {

    private String name;
    private String value;
    private boolean isQuoted;
    private char quotes;
    private boolean normalized;

    public TagAttribute(String n) {
        setName(n);
    }

    public TagAttribute(String name, String value) {
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
     * @param value
     *            the value to set, may be null
     */
    public void setValue(String value) {
        this.value = value;
        setQuoted();
        if (isQuoted) {
            dequotValue();
        }
    }

    public String getValue() {
        if (normalized) {
            return value;
        }
       //normalize();
        return value;
    }

    private boolean normalize() {
        if (value.contains("&")) {
            value = HtmlEntityTranslator.decodeHTML(value);
        }
        normalized = true;
        return true;
    }

    public boolean isValueAssigned() {
        return value != null;
    }

    private void setQuoted() {
        if (!isValueAssigned()) {
            return;
        }
        char first = value.charAt(0);
        isQuoted = (first == '\'' || first == '"') && value.charAt(value.length() - 1) == first;
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