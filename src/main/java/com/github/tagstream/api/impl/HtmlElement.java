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
import java.util.Optional;

import com.github.tagstream.api.Element;
import com.github.tagstream.api.ElementType;

public class HtmlElement implements Element {

    private String tagName;
    private List<HtmlAttribute> attributes = Collections.emptyList();

    public HtmlElement(String tag, List<HtmlAttribute> attrList) {
        tagName = tag;
        attributes = attrList;
    }

    @Override
    public boolean hasAttributes() {
        return !attributes.isEmpty();
    }

    public void addAttribute(HtmlAttribute attribute) {
        attributes.add(attribute);
    }

    @Override
    public boolean containsAttribute(String attrName) {
        return attributes.stream().anyMatch(attr -> attr.getName().equalsIgnoreCase(attrName));
    }

    @Override
    public boolean attributeHasValue(String name) {
        return attributes.stream().anyMatch(attr -> attr.getName().equalsIgnoreCase(name) && attr.isValueAssigned());
    }

    @Override
    public String getAttributeValue(String name) {
        Optional<HtmlAttribute> attribute = attributes.stream()
                .filter(attr -> attr.getName().equalsIgnoreCase(name) && attr.isValueAssigned()).findFirst();
        if (attribute.isPresent()) {
            return attribute.get().getValue();
        }
        return null;
    }

    @Override
    public List<HtmlAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public Optional<HtmlAttribute> getAttribute(String attrName) {
        return attributes.stream().filter(attr -> attr.getName().equals(attrName)).findFirst();
    }

    public String toString() {
        return tagName;
    }

    @Override
    public ElementType getType() {
        return ElementType.TAG;
    }
}