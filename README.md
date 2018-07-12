# TagStream
Module to provide a fast, HTML5/XML/XHTML tokenzier. TagStream takes an InputStream and separates the text into elements that define a Tag or Text data. 

## Using TagStream 
There are two classes which provides access to the processing of an HTML/XML source. 

### TagIterator
The TagIterator allows you to iterate the HTML/XML document utilizing a pull methodology. Whenever you need the next element you request it and it is parsed and presented.

### Tag
The Tag class wraps the TagIterator to provide a Stream<Element> provider.


Examples:

```java
//count the number of tags
stream.filter(elem -> elem.getType() == ElementType.START_TAG ).count();
```

```java
// find any elements that has an href attribute that doesn't point anywhere
// print out the bad links as an html list of anchors 
stream.filter(elem -> elem.getType() == ElementType.START_TAG && elem.hasAttribute("href") )
    .filter(elem -> isLinkBad(elem.getAttribute("href")))
    .map(TO_HTML)
    .forEach(System.out::println);
```

```java
//count the number of tags
HtmlSAXSupport saxEventGenerator = new HtmlSAXSupport(customHandler);
stream.forEach(saxEventGenerator);
```
TagStream works by using the W3C's HTML5 *parsing* rules to properly identify a Tag. This is a separate set of guidelines from what defines valid HTML. The TagStream parser responds with a Tag or Text element and then proceeds to the next section. It does not attempt to create a DOM tree, it doesn't perform validation of tag it found. It assumes that you know what you are doing and won't judge you.


