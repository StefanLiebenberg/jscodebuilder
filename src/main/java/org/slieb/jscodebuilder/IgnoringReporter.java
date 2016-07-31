package org.slieb.jscodebuilder;

import com.google.javascript.rhino.ErrorReporter;

public class IgnoringReporter implements ErrorReporter {

    public final static IgnoringReporter INSTANCE = new IgnoringReporter();

    private IgnoringReporter() {}

    @Override
    public void warning(final String s, final String s1, final int i, final int i1) {
    }

    @Override
    public void error(final String s, final String s1, final int i, final int i1) {
    }
}
