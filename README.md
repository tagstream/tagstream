# TagStream
TagStream is an extremely fast, lazy validating, html5 supporting parser that can be used to produce

* A lazy Iterator
* An element Stream<>
* Managed set of processors

Examples:

```java
//count the number of tags
stream.filter(elem -> elem.getType() == ElementType.TAG ).count();
```

```java
// find any elements that has an href attribute that doesn't point anywhere
// print out the bad links as an html list of anchors 
stream.filter(elem -> elem.getType() == ElementType.TAG && elem.hasAttribute("href") )
    .filter(elem -> isLinkBad(elem.getAttribute("href")))
    .map(TO_HTML)
    .forEach(System.out::println);
```