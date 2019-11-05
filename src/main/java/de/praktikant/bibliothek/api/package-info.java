/**
 * ReST steht für <b>Re</b>presentational <b>S</b>tate <b>T</b>ransfer, API für <b>A</b>pplication <b>P</b>rogramming <b>I</b>nterface.<br>
 * Gemeint ist damit eine Programmierschnittstelle, die sich an den Paradigmen und dem Verhalten des World Wide Web (WWW) orientiert und einen Ansatz
 * für die Kommunikation zwischen Client und Server in Netzwerken beschreibt. Die Bezeichnung „Representational State Transfer“ soll den Übergang vom
 * aktuellen Zustand zum nächsten Zustand (state) einer Applikation verbildlichen.
 * <p>
 * Bei der <i>bibliothek-api</i> handelt es sich um die Implementierung einer ReST-API zur Verwaltung von Bibliotheksbeständen und -kunden. Als
 * Bestand können derzeit nur Bücher verwaltet werden.
 * <p>
 * Sie ist zur Ausführung auf einem Schulungs-Notebook gedacht und arbeitet mit Spring Boot.
 * <p>
 * Die <i>bibliothek-api</i> erwartet eine MySQL-Datenbank mit dem Namen <code>bibliothek_dev</code>, die einen User <code>bibliothek</code> mit dem
 * Passwort <code>developer</code> kennt und auf dem Host <code>localhost</code> läuft.<br>
 * Ist diese Voraussetzung erfüllt, kann die <i>bibliothek-api</i> durch den Befehl <strong><i>mvn spring-boot:run –Pdev</i></strong> in ihrem
 * root-Verzeichnis gestartet werden.
 * <p>
 * Das REST-Paradigma wird von der <i>bibliothek-api</i> über das zustandslose Client-Server-Protokoll HTTP realisiert. Services werden per URL/URI
 * angesprochen. Die HTTP-Methoden geben an, welche Operation ein Dienst ausführen soll.
 * <ul>
 * <li>GET - fordert Daten vom Server an
 * <li>POST - übermittelt Daten an den Server
 * <li>PUT - ändern bestehende Daten auf dem Server
 * <li>DELETE - löscht bestehende Daten auf dem Server
 * </ul>
 * Die Basis-URL zum Aufruf der ReST-Services ist in den <code>application.properties</code> definiert und lautet:
 * <strong>http://localhost:8081/bibliothek/api</strong><br>
 * Services, die Bücher betreffen, werden durch die Ergänzung <strong>/books</strong> gekennzeichnet.<br>
 * Folgende Services sind implementiert:
 * <ul>
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#getBooks() Liste aller Bücher}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#getBookById(Long) <i>ein</i> Buch aus der Datenbank lesen}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#getHistory(Long) die Ausleih-Historie eines Kunden}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#postBook(de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity)
 * ein Buch in die Datenbank einfügen}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#putBook(de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity)
 * Daten eines Buchs in der Datenbank ändern}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#lendBook(Long, Long) ein Buch als vom Kunden ausgeliehen markieren}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.BookResource#returnBook(Long) das Ende der Ausleihe eines Buches darstellen}
 * </ul>
 * Services, die Kunden betreffen, werden durch die Ergänzung <strong>/customer</strong> gekennzeichnet.<br>
 * Folgende Services sind implementiert:
 * <ul>
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.CustomerResource#getCustomer() Liste aller Kunden}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.CustomerResource#getCustomerById(Long) <i>einen</i> Kunden aus der Datenbank lesen}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.CustomerResource#getLendBooks(Long) Liste der geliehenen Bücher eines Kunden}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.CustomerResource#postCustomer(de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerEntity)
 * einen weiteren Kunden in die Datenbank aufnehmen}
 * <li>{@linkplain de.praktikant.bibliothek.api.resources.CustomerResource#putCustomer(de.praktikant.bibliothek.api.backend.hibernate.entitys.customer.CustomerEntity)
 * Daten eines Kunden verändern}
 * </ul>
 * Für die Verwendung z.B. des Services "Liste aller Bücher" in einer Client-Anwendung sind folgende Schritte notwendig:
 * <ul>
 * <li>Die Klasse {@linkplain de.praktikant.bibliothek.api.resources.response.BaseResponse BaseResponse} muss in den Client kopiert werden.
 * <li>Gemäß der Klasse {@linkplain de.praktikant.bibliothek.api.resources.response.book.BookListResponse BookListResponse} muss ein clientseitiges
 * Interface <code>BookListResponse</code> und eine implementierende Klasse <code>BookListResponseImpl</code>, die von
 * <code>BaseResponse</code> ableitet, erstellt werden. Hintergrund ist, dass die Webservices Daten im JSON-Format liefern und auch erwarten. Mit Hilfe des <code>com.fasterxml.jackson.databind.ObjectMapper</code>s
 * werden die JSON-Daten in Java-Klassen - eben die Response-Klassen - transformiert.
 * </ul>
 * Jetzt kann folgender Code zum Ermitteln aller Bücher der Bibliothek kodiert werden:
 * 
 * <pre>
 * <code>BookResponse result = new BookResponseImpl();<br>
 *  try {<br>
 *    URL url = new URL("http://localhost:8081/bibliothek/api/books");<br>
 *    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 *    conn.setRequestMethod("GET");
 *    conn.setRequestProperty("Accept", "application/json");<br>
 *    if (conn.getResponseCode() != 200) {
 *      throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
 *    }<br>
 *    result = new ObjectMapper().readValue(new InputStreamReader(conn.getInputStream()), BookResponseImpl.class);<br>
 *    conn.disconnect();<br>
 *    } catch (….) {
 *  }<br>
 *  return result;
 * </code>
 * </pre>
 * <p>
 * Der Client-Code zum Ändern eines Buches könnte wie folgt aussehen:
 * 
 * <pre>
 * <code>BookEntity entity = (BookEntity) &lt; Buchobjekt mit geänderten Attributen &gt;;

        try {

            URL url = new URL("http://localhost:8081/bibliothek/api/books");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            String jasonTrackString = mapper.writeValueAsString(entity);
            OutputStream os = conn.getOutputStream();
            os.write(jasonTrackString.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new WicketRuntimeException("Failed : HTTP error code: " + conn.getResponseCode());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
 * </code>
 * </pre>
 * 
 * Die clientseitige <code>BookEntity</code> muss dazu der {@linkplain de.praktikant.bibliothek.api.backend.hibernate.entitys.books.BookEntity
 * BookEntity} nachgebildet werden.
 */
package de.praktikant.bibliothek.api;
