/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blasterpig;

/**
 *
 * @author blackMamba
 */
public class BaseConflictException extends Exception {

    /**
     * Creates a new instance of <code>BaseConflictException</code> without detail message.
     */
    public BaseConflictException() {
    }


    /**
     * Constructs an instance of <code>BaseConflictException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BaseConflictException(String msg) {
        super(msg);
    }
}
