package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class IllegalDBStateException extends IllegalStateException {

    private String table;

    private String problem;

    private String uuid;

    public IllegalDBStateException(final String table, final String problem, final String uuid) {
        this("The database has some troubles, please contact the administrator and give him the code: " + uuid);
        this.table = table;
        this.problem = problem;
        this.uuid = uuid;
    }

    private IllegalDBStateException(final String message) {
        super(message);
    }

    public IllegalDBStateReport getReport() {
        return new IllegalDBStateReport(this.table, this.problem, this.problem);
    }

    @AllArgsConstructor
    @Getter
    private static class IllegalDBStateReport {
        private String table;

        private String cause;

        private String uuid;
    }
}
