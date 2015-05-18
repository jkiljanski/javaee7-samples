package org.javaee7.jaxws.endpoint;

import java.util.List;

import javax.jws.WebService;

/**
 * 
 * @author Fermin Gallego
 *
 */
@WebService
public interface EBookStore {
     String welcomeMessage(String name);

     List<String> findEBooks(String text);

     EBook takeBook(String title);

     void saveBook(EBook eBook);

     EBook addAppendix(EBook eBook, int appendixPages);
}
