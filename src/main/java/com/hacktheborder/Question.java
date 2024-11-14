package com.hacktheborder;

import java.io.Serializable;


public class Question implements Serializable {
    private final long serialVersionUID = 1L;
    private final String QUESTION_TYPE, EXPECTED_OUTPUT;
    private final String NON_EDITABLE_CODE_1, NON_EDITABLE_CODE_2;
    private final String EDITABLE_CODE;

    public Question(String nonEditableCode1, String nonEditableCode2, String editableCode, String questionType, String expectedOutput) {
        this.NON_EDITABLE_CODE_1 = nonEditableCode1;
        this.NON_EDITABLE_CODE_2 = nonEditableCode2;
        this.EDITABLE_CODE = editableCode;
        this.QUESTION_TYPE = questionType;
        this.EXPECTED_OUTPUT = expectedOutput;
    }

    public String getQuestionType() {
        return this.QUESTION_TYPE;
    }

    public String getExpectedOutput() {
        return this.EXPECTED_OUTPUT;
    }

    public String getNonEditableCode1() {
        return this.NON_EDITABLE_CODE_1;
    }

    public String getEditableCode() {
        return this.EDITABLE_CODE;
    }

    public String getNonEditableCode2() {
        return this.NON_EDITABLE_CODE_2;
    }
}
