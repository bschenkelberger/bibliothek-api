/**
 * Enthält die Implementierung der Antwortklassen der ReST-Services.
 * <p>
 * Da ReST-Services mit Daten im JSON-Format arbeiten, wir aber mit Java-Klassen programmieren wollen, müssen die JSON-Daten (mit Hilfe des
 * <code>com.fasterxml.jackson.databind.ObjectMapper</code>s) auf Response-Klassen gemapt werden. Die Attribute in den Response-Klassen müssen also
 * mit Attributen der JSON-Daten korrespondieren und ggfs. m.H. von speziellen Annotationen aus <code>com.fasterxml.jackson.annotation</code>
 * transformiert werden.
 */
package de.praktikant.bibliothek.api.resources.response;