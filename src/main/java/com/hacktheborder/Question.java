package com.hacktheborder;

import java.io.Serializable;


public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private String questionType, expectedOutput;
    private final String NON_EDITABLE_CODE_1, NON_EDITABLE_CODE_2;
    private String editableCode;

    public Question(String nonEditableCode1, String nonEditableCode2, String editableCode, String questionType, String expectedOutput) {
        this.NON_EDITABLE_CODE_1 = nonEditableCode1;
        this.NON_EDITABLE_CODE_2 = nonEditableCode2;
        this.editableCode = editableCode;
        this.questionType = questionType;
        this.expectedOutput = expectedOutput;
    }

    public String getQuestionType() {
        return this.questionType;
    }

    public String getExpectedOutput() {
        return this.expectedOutput;
    }

    public String getNonEditableCode1() {
        return this.NON_EDITABLE_CODE_1;
    }

    public String getEditableCode() {
        return this.editableCode;
    }

    public String getNonEditableCode2() {
        return this.NON_EDITABLE_CODE_2;
    }
}
