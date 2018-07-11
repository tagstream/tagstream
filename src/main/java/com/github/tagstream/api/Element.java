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
package com.github.tagstream.api;

import java.util.List;
import java.util.Optional;

import com.github.tagstream.api.impl.TagAttribute;

public interface Element {
    /**
     * Retrieve the associated ElementType
     * 
     * @return the type of element
     */
    ElementType getType();

    /**
     * Must be defined as true to allow default methods to work correctly, this
     * method and the getAttributes method are used together to implement attribute
     * support in an element.
     * 
     * @return true if Element supports attributes
     */
    boolean supportsAttributes();

    
    List<TagAttribute> getAttributes();

    /**
     * Accepts a Visitor to visit
     * 
     * @param visitor
     * @return
     */
    default <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * Whether this element contains Attributes
     * 
     * @return
     */
    default boolean hasAttributes() {
        if (supportsAttributes()) {
            return !getAttributes().isEmpty();
        }
        return false;
    }

    default Optional<TagAttribute> getAttribute(String attrName) {
        return getAttributes().parallelStream().filter(attr -> attr.getName().equals(attrName)).findFirst();
    }

    default boolean containsAttribute(String attrName) {
        if (supportsAttributes()) {
            return getAttributes().parallelStream().anyMatch(attr -> attr.getName().equalsIgnoreCase(attrName));
        }
        return false;
    }

    default boolean attributeHasValue(String attrName) {
        if (supportsAttributes()) {
            return getAttributes().parallelStream()
                    .anyMatch(attr -> attr.getName().equalsIgnoreCase(attrName) && attr.isValueAssigned());
        }
        return false;
    }

    default String getAttributeValue(String name) {
        if (supportsAttributes()) {
            Optional<TagAttribute> attribute = getAttributes().parallelStream()
                    .filter(attr -> attr.getName().equalsIgnoreCase(name) && attr.isValueAssigned()).findFirst();
            if (attribute.isPresent()) {
                return attribute.get().getValue();
            }
        }
        return null;
    }

    default void setAttribute(String name, String value) {
        if (supportsAttributes()) {
            getAttributes().add(new TagAttribute(name, value));
        }
        throw new UnsupportedOperationException();
    }
}
