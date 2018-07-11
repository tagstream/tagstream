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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import com.github.tagstream.api.Element;

/**
 * Utility class that allows you to define a flatMap process in the form of a
 * BiConsumer<Element,Process> lambda.
 * 
 * 
 *
 */
public class Process {

    private List<Element> list = new ArrayList<>();

    private Process() {
    }

    /**
     * Collects all the elements that are either being passed through or created in
     * the accept method of the consumer so that they may be passed on to the next
     * process.
     */
    public void next(Element... elements) {
        Collections.addAll(list, elements);
    }

    Function<Element, Stream<Element>> createFlatMap(BiConsumer<Element, Process> consumer, Process process) {
        return element -> {
            list.clear();
            consumer.accept(element, process);
            return list.stream();
        };
    }

    public static Function<Element, Stream<Element>> chain(BiConsumer<Element, Process> consumer) {
        Process process = new Process();
        return process.createFlatMap(consumer, process);
    }

}