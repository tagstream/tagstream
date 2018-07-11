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

import java.util.Collections;
import java.util.List;

import com.github.tagstream.api.Element;
import com.github.tagstream.api.ElementType;

public class Comment implements Element {

    private String value;

    public Comment(String text) {
        this.value = text;
    }

    @Override
    public ElementType getType() {
        return ElementType.COMMENT;
    }
    
    public String toString() {
        return value;
    }

    @Override
    public boolean supportsAttributes() {
        return false;
    }

    @Override
    public List<TagAttribute> getAttributes() {
        return Collections.emptyList();
    }

}