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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.github.tagstream.api.impl.HtmlAttribute;

public interface Element {

    /**
     * Accepts a Visitor object to then visit
     * 
     * @param visitor
     * @return
     */
    default <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * Retreive the associated ElementType
     * 
     * @return the type of element
     */
    ElementType getType();

    /**
     * Whether this element supports Attributes
     * 
     * @return
     */
    default boolean hasAttributes() {
        return false;
    }

    default List<HtmlAttribute> getAttributes() {
        return Collections.emptyList();
    }

    default Optional<HtmlAttribute> getAttribute(String attrName) {
        return Optional.empty();
    }

    default boolean containsAttribute(String attrName) {
        return false;
    }

    default boolean attributeHasValue(String attrName) {
        return false;
    }

    default String getAttributeValue(String name) {
        return null;
    }
    
    default void setAttribute(String name, String value) {
    }

}
