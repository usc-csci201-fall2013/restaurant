/*
Copyright (c) 2000-2003 Board of Trustees of Leland Stanford Jr. University,
all rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
STANFORD UNIVERSITY BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Except as contained in this notice, the name of Stanford University shall not
be used in advertising or otherwise to promote the sale, use or other dealings
in this Software without prior written authorization from Stanford University.

*/
package agent;

import java.util.*;

/**
 * Create a list of Object from a call list.
 */
public class ListUtil {
    /**
     * Don't construct.
     */
    private ListUtil() {
    }

    /**
     * Create list from arg list.
     */
    public static List<Object> list(Object... args) {
        List<Object> l = new ArrayList<Object>();
        for (int i = 0; i < args.length; i++) {
            l.add(args[i]);
        }
        return l;
    }


    /**
     * Create a list containing the elements of an array
     */
    public static List<Object> fromArray(Object array[]) {
        List<Object> l = list();
        for (int i = 0; i < array.length; i++) {
            l.add(array[i]);
        }
        return l;
    }

    /**
     * Create a list containing the elements of an iterator
     */
    public static List<Object> fromIterator(Iterator iterator) {
        List<Object> l = list();
        while (iterator.hasNext()) {
            l.add(iterator.next());
        }
        return l;
    }

    /**
     * Create a list containing the elements of a comma separated string
     */
    public static List<Object> fromCSV(String csv) {
        List<Object> res = list();
        StringTokenizer st = new StringTokenizer(csv, ",");
        while (st.hasMoreTokens()) {
            String id = (String) st.nextToken();
            res.add(id);
        }
        return res;
    }

    /**
     * Check that all elements of the list are of the specified type, and
     * return an unmodifiable copy of the list.
     *
     * @param list the list.
     * @param type the class with which all items of the list
     *             must be assignment-compatible.
     * @throws NullPointerException if the list is null or if any element
     *                              is null
     * @throws ClassCastException   if an item is not of the proper type
     */
    public static List<Object> immutableListOfType(List<Object> list, Class type) {
        return immutableListOfType(list, type, false);
    }

    /**
     * Check that all elements of the list are either of the specified type
     * or null, and
     * return an unmodifiable copy of the list.
     *
     * @param list the list.
     * @param type the class with which all items of the list
     *             must be assignment-compatible.
     * @throws NullPointerException if the list is null
     * @throws ClassCastException   if an item is not of the proper type
     */
    public static List<Object> immutableListOfTypeOrNull(List<Object> list, Class type) {
        return immutableListOfType(list, type, true);
    }

    private static List<Object> immutableListOfType(List<Object> list, Class type,
                                                    boolean nullOk) {
        List<Object> l = new ArrayList<Object>(list.size());
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object item = iter.next();
            if (item == null) {
                if (!nullOk) {
                    throw new NullPointerException("item of list is null");
                }
            } else if (!type.isInstance(item)) {
                throw new ClassCastException("item <" + item +
                        "> of list is not an instance of "
                        + type);
            }
            l.add(item);
        }
        return Collections.unmodifiableList(l);
    }

    /**
     * Return a copy of the list, in reverse order.
     *
     * @param list the List to reverse.
     * @return A new list with elements in reverse order of the original list.
     */
    public static List<Object> reverseCopy(List<Object> list) {
        List<Object> res = new ArrayList<Object>(list);
        Collections.reverse(res);
        return res;
    }
}
