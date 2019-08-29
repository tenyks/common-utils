package me.maxwell.common.utils.postgre.explain;

import java.util.function.Function;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 20:16
 */
public class JsonFieldHandler<In, Out> {

    private String  fieldName;

    private Function<String, Out> convertFun1;

    private Function<In, Out> convertFun2;

    private Function<Out, Void> setterFun;

    public JsonFieldHandler(String fieldName, Function<String, Out> convertFun1,
                            Function<In, Out> convertFun2, Function<Out, Void> setterFun) {
        this.fieldName = fieldName;
        this.convertFun1 = convertFun1;
        this.convertFun2 = convertFun2;

        this.setterFun = setterFun;
    }

    public Out implant(In value) {
        if (value == null) setterFun.apply(null);

        Out r = convertFun2.apply(value);
        setterFun.apply(r);

        return r;
    }

    public Out implant(String value) {
        if (value == null) setterFun.apply(null);

        Out r = convertFun1.apply(value);
        setterFun.apply(r);

        return r;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
