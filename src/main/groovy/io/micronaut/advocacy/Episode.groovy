package io.micronaut.advocacy;

import groovy.transform.CompileStatic

@CompileStatic
class Episode {
    String title
    String author
    String publication

    String getAuthor() {
        String[] parts = StringUtils.splitEqually(this.author, (this.author.length() / 2) as int)
        if (parts.length == 2 && parts[0] == parts[1]) {
            return parts[0]
        }
        this.author
    }
}
