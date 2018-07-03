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
package com.github.tagstream.util;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.tagstream.api.Element;
import com.github.tagstream.api.impl.StartTag;

public class HtmlStreams {
    
    private HtmlStreams() {
    }

    public static final  Function<Element, String> TO_HTML = element ->{
        StringBuilder sb = new StringBuilder();
        switch (element.getType()) {
        case COMMENT:
            sb.append("<!--");
            sb.append(element.toString());
            sb.append("-->");
            break;
        case DECLARATION:
            sb.append("<!");
            sb.append(element.toString());
            sb.append(">");
            break;
        case END_TAG:
            sb.append("</");
            sb.append(element.toString());
            sb.append('>');
            break;
        case EOF:
            break;
        case START_TAG:
            sb.append('<');
            sb.append(element.toString());
            StartTag tag = (StartTag) element;
            if (tag.hasAttributes()) {
                sb.append(' ');
                sb.append(tag.getAttributes().stream().map(a -> {
                    StringBuilder attr1 = new StringBuilder();
                    attr1.append(a.getName());
                    if (a.isValueAssigned()) {
                        attr1.append('=');
                        if (a.isQuoted()) {
                            attr1.append(a.getQuoted());
                        } else {
                            attr1.append(a.getValue());
                        }
                    } 
                    return attr1.toString();
                }).collect(Collectors.joining(" ")));
            }
            sb.append('>');
            break;
        case TEXT:
            sb.append(element.toString());
        }
        return sb.toString();
    };
    
    
    
}
