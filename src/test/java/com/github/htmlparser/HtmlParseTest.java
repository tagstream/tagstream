/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.htmlparser;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.text.ParseException;
import java.util.stream.Stream;

import org.apache.jackrabbit.commons.xml.DefaultContentHandler;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import com.github.htmlparser.Html;
import com.github.htmlparser.api.Element;
import com.github.htmlparser.api.ElementType;
import com.github.htmlparser.util.HtmlSAXSupport;
import com.github.htmlparser.util.HtmlStreams;

public class HtmlParseTest {

    Stream<Element> stream;

    @Before
    public void setUp() throws ParseException, Exception {
        InputStream is = this.getClass().getResourceAsStream("/demo.html");
        stream = Html.stream(is, "UTF-8");
    }
    
    @Test
    public void docParseTagTest() throws Exception {
        long count = stream.filter(elem -> elem.getType() == ElementType.TAG ).count();
        assertEquals(902, count);
    }

    @Test
    public void docParseAllTest() throws Exception {
        long count = stream.count();
        assertEquals(2928, count);
    }
    
    @Test
    public void docParseAllTestToString() throws Exception {
        stream.map(HtmlStreams.TO_HTML).forEach(System.out::print);
    }
    
    @Test
    public void docParseSAXTest() {
        HtmlSAXSupport support = new HtmlSAXSupport(new DefaultHandler2() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                System.out.println(localName);
            }

        },new DefaultHandler2());
        stream.forEach(support);
    }
    
}
