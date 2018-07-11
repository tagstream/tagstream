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
import java.util.Map;

import com.github.tagstream.api.Element;
import com.github.tagstream.api.ElementType;

public class EndTag implements Element {

    private String tagName;

    public EndTag(String t) {
        tagName = t;
    }

    public String getValue() {
        return tagName;
    }

    @Override
    public ElementType getType() {
        return ElementType.END_TAG;
    }

    @Override
    public boolean supportsAttributes() {
        return false;
    }

    @Override
    public Map<String, String> getAttributes() {
        return Collections.emptyMap();
    }
}