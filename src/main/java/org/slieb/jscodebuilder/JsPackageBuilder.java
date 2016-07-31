package org.slieb.jscodebuilder;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class JsPackageBuilder {

    private final File outputDirectory;

    public JsPackageBuilder(final File outputDirectory) {this.outputDirectory = outputDirectory;}

    public JsFileBuilder createFile(String filename) {
        return new JsFileBuilder(new File(outputDirectory, filename));
    }

    public void createFile(String filename, Consumer<JsFileBuilder> consumer) throws IOException {
        final JsFileBuilder fileBuilder = createFile(filename);
        consumer.accept(fileBuilder);
        fileBuilder.build();
    }
}
