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
package com.github.tagstream;

import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.github.tagstream.api.Element;
import com.github.tagstream.api.ElementType;
import com.github.tagstream.impl.TagParser;
import com.github.tagstream.impl.ParseException;

public class TagIterator implements Iterator<Element> {

    private Element current;

    boolean eof = false;
    private TagParser parser;

    public TagIterator(InputStream is) {
        parser = new TagParser(is);
    }

    public TagIterator(InputStream is, String encoding) {
        parser = new TagParser(is, encoding);
    }

    @Override
    public boolean hasNext() {
        if (current == null && !eof) {
            return seek();
        }
        return !eof;
    }

    @Override
    public Element next() {
        if (current == null && !eof) {
            seek();
        }
        if (current == null || eof) {
            throw new NoSuchElementException();
        }
        Element response = current;
        current = null;
        return response;
    }

    private boolean seek() {
        if (eof) {
            return false;
        }
        try {
            current = parser.element();
            eof =  (current.getType() == ElementType.EOF);
            return !eof;
        } catch (ParseException e) {
        }
        return false;
    }

}