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
package com.github.htmlparser.util;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ext.Attributes2Impl;

import com.github.htmlparser.api.impl.HtmlAttribute;

public class SaxAttributes {
    
    public static Attributes convert(List<HtmlAttribute> attributes) {
        Attributes2Impl response = new Attributes2Impl();
        attributes.forEach(attr ->{
            response.addAttribute("", "", attr.getName(), "xsi:String", attr.getValue());
        });
        return response;
    }

}
