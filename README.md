# TagStream
TagStream is an extremely fast, lazy validating, html5 supporting parser that can be used to produce

* lazy Iterator
* Stream<Element>
* Managed set of processors. to manipulate content 

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

```java
//count the number of tags
HtmlSAXSupport saxEventGenerator = new HtmlSAXSupport(customHandler);
stream.forEach(saxEventGenerator);
```
TagStream works by using the W3C's HTML5 *parsing* rules to properly identify a Tag. This is a separate set of guidelines from what defines valid HTML. The TagStream parser responds with a Tag or Text element and then proceeds to the next section. It does not attempt to create a DOM tree, it doesn't perform validation of tag it found. It assumes that you know what you are doing and won't judge you.

Here is a section of example HTML5 taken from the specification. 

```html
<table>
<caption>37547 TEE Electric Powered Rail Car Train Functions (Abbreviated)
<colgroup><col><col><col>
<thead>
<tr> <th>Function                              <th>Control Unit     <th>Central Station
<tbody>
<tr> <td>Headlights                            <td>✔                <td>✔
<tr> <td>Interior Lights                       <td>✔                <td>✔
<tr> <td>Electric locomotive operating sounds  <td>✔                <td>✔
<tr> <td>Engineer’s cab lighting               <td>      <td>✔
<tr> <td>Station Announcements - Swiss         <td>      <td>✔
</table>
```

