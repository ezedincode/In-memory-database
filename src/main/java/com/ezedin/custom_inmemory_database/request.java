package com.ezedin.custom_inmemory_database;

public record request (
        String typ,
        String str,
        int num,
        String bulk,
        request [] array
){
}
