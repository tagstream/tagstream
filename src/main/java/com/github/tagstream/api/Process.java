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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 
 * 
 *
 */
public interface Process extends BiConsumer<Element,Process> {

    List<Element> list = new ArrayList<>();

    /**
     * Collects all the elements that are either being passed through or created in
     * the accept method of the consumer so that they may be passed on to the next
     * process.
     */
    default void next(Element... elements) {
        Collections.addAll(list, elements);
    }

    default Function<Element, Stream<Element>> createFlatMap(Process process) {
        return element -> {
            list.clear();
            accept(element,process);
            List<Element> test = list;
            return list.stream();
        };
    }
    
    public static Function<Element, Stream<Element>> chain(Process doProcess){
        return doProcess.createFlatMap(doProcess);
    }

}
