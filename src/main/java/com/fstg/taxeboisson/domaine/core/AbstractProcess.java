package com.fstg.taxeboisson.domaine.core;

import java.text.ParseException;

public interface AbstractProcess<T extends AbstractProcessInput> {
    Result execute(T abstractProcessInput) throws ParseException;
}
