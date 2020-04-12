package com.demo.pojo;

import java.lang.reflect.Field;

public class AnnoField {

    private Field field;

    /**
     * 是否包含@Autowired注解
     */
    private boolean haveAuto;
    /**
     * @Auto注解值
     */
    private String autoValue;
    /**
     * 是否包含@Tran注解
     */
    private boolean haveTran;
    /**
     * @Tran注解值
     */
    private boolean tranValue;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }


    public boolean isHaveAuto() {
        return haveAuto;
    }

    public void setHaveAuto(boolean haveAuto) {
        this.haveAuto = haveAuto;
    }

    public String getAutoValue() {
        return autoValue;
    }

    public void setAutoValue(String autoValue) {
        this.autoValue = autoValue;
    }

    public boolean isHaveTran() {
        return haveTran;
    }

    public void setHaveTran(boolean haveTran) {
        this.haveTran = haveTran;
    }

    public boolean isTranValue() {
        return tranValue;
    }

    public void setTranValue(boolean tranValue) {
        this.tranValue = tranValue;
    }
}
