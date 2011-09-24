package com.github.evetools.marshal;

import com.github.evetools.marshal.python.PyBase;
import com.github.evetools.marshal.python.PyDumpVisitor;
import java.io.File;

/**
 * Copyright (C)2011 by Gregor Anders All rights reserved.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the BSD license (see the file LICENSE.txt included with the
 * distribution).
 */
public final class Dump {

    /**
     * -2 exit code.
     */
    private static final int EXIT_2 = -2;

    /**
     * -3 exit code.
     */
    private static final int EXIT_3 = -3;

    /**
     * Utility class constructor: Dump should never ne called.
     */
    private Dump() {
        throw new AssertionError(
                "Utility class constructor: Dump should never ne called.");
    }

    /**
     * Dumps cachefile/bulkdata content.
     *
     * @param args
     *            file
     */
    public static void main(final String[] args) {

        if (args.length != 1) {
            System.out.println("Please provide a file.");
            System.exit(-1);
        }

        final String fileName = args[0];

        try {

            final File file = new File(fileName);

            if (!file.exists() || !file.isFile()) {
                System.out.println("No such file " + fileName + ".");
                System.exit(EXIT_2);
            }

            if (!file.canRead()) {
                System.out.println("Could not read " + fileName + ".");
                System.exit(EXIT_2);
            }

            final Reader reader = new Reader(file);
            PyBase pyBase = reader.read();

            PyDumpVisitor visitor = new PyDumpVisitor(System.out);
            pyBase.visit(visitor);

        } catch (final Exception e) {
            System.out.println("Could not decode " + fileName + ".");
            e.printStackTrace();
            System.exit(EXIT_3);
        } catch (final OutOfMemoryError e) {
            System.out.println("Could not decode [out of memory] " + fileName
                    + ".");
            // e.printStackTrace();
            System.exit(EXIT_3);
        }

        System.exit(0);
    }
}
