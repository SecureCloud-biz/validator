/*
 * Copyright (c) 2013-2014 Mozilla Foundation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package nu.validator.datatype;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;

public final class ARel extends AbstractRel {

    private static final HashSet<String> registeredValues = new HashSet<>();

    static {
        // Standard rel values for <a> and <area> from the spec
        registeredValues.add("alternate");
        registeredValues.add("author");
        registeredValues.add("bookmark");
        registeredValues.add("external");
        registeredValues.add("help");
        registeredValues.add("license");
        registeredValues.add("next");
        registeredValues.add("nofollow");
        registeredValues.add("noreferrer");
        registeredValues.add("prefetch");
        registeredValues.add("prev");
        registeredValues.add("search");
        registeredValues.add("sidebar");
        registeredValues.add("tag");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                ARel.class.getClassLoader().getResourceAsStream(
                        "nu/validator/localentities/files/a-rel-extensions")));
        // Read in registered rel values from cached copy of the registry
        try {
            String read = br.readLine();
            while (read != null) {
                registeredValues.add(read);
                read = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The singleton instance.
     */
    public static final ARel THE_INSTANCE = new ARel();

    /**
     * Private constructor
     */
    private ARel() {
        super();
    }

    @Override
    protected boolean isRegistered(CharSequence literal, String token) {
        return registeredValues.contains(token);
    }

    @Override
    public String getName() {
        return "list of link-type keywords";
    }

}
