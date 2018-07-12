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

import java.util.function.Predicate;
import java.util.regex.Pattern;


public class AttrValue implements CharSequence {

    Predicate<String> validValue = Pattern.compile("^[a-zA-Z][-_a-zA-Z0-9\\u00A0-\\u10FFFF]*$").asPredicate();

    private String value;

    public AttrValue() {
    }
    
    public AttrValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String quoteIfNeeded() {
        if (isEmpty()) {
            return null;
        }
        if (shouldBeQuoted()) {
            return getQuoted('"');
        }
        return value;
    }
    
    public String getQuoted(char paren) {
        if (isEmpty()) {
            return null;
        }
        return paren + value + paren;
    }

    public boolean shouldBeQuoted() {
        if (isEmpty()) {
            return false;
        }
        return validValue.negate().test(value);
    }
    
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public int length() {
        if (isEmpty()) {
            return 0;
        }
        return value.length();
    }

    @Override
    public char charAt(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return value.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return value.subSequence(start, end);
    }
    @Override
    public String toString() {
        return value;
    }
    
}
