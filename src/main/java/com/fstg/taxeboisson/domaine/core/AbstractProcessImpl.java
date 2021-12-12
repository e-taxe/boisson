package com.fstg.taxeboisson.domaine.core;

import java.text.ParseException;

public abstract class AbstractProcessImpl<T extends AbstractProcessInput> {


    public Result execute(T abstractProcessInput) throws ParseException {
        Result result = new Result();
        if (validateProcess(abstractProcessInput, result)) {
            run(abstractProcessInput, result);
        }
        return result;
    }

    public boolean validateProcess(T abstractProcessInput, Result result) throws ParseException {
        validate(abstractProcessInput, result);
        return result.hasError();
    }


    public abstract void validate(T abstractProcessInput, Result result) throws ParseException;

    public abstract void run(T abstractProcessInput, Result result) throws ParseException;

}
