package com.nicootech.commitsdemonstratorandroidapp.model;

public class Commit {
    private String mSha;
    private String mAuthor;
    private String mMessage;
    public String getmSha() {
        return mSha;
    }

    public void setmSha(String mSha) {
        this.mSha = mSha;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMeesage) {
        this.mMessage = mMeesage;
    }
}
