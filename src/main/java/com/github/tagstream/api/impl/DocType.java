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

import java.util.Map;

import com.github.tagstream.api.AttrValue;
import com.github.tagstream.api.Element;
import com.github.tagstream.api.ElementType;

public class DocType implements Element {
    
    private String value;
    private Map<String,AttrValue> attributes;

    public DocType(String tag, Map<String,AttrValue> attrList) {
        this.value = tag;
        this.attributes = attrList;
    }
    
    @Override
    public ElementType getType() {
        return ElementType.DECLARATION;
    }

    @Override
    public boolean supportsAttributes() {
        return true;
    }

    @Override
    public Map<String, AttrValue> getAttributes() {
        return attributes;
    }

    @Override
    public String getValue() {
        return value;
    }

}
