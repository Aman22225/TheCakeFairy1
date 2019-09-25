package com.aman.thecakefairy;

import android.widget.EditText;

public class UserFeedback {
    String Name,Mobile,Subject,Message;

    public UserFeedback() {
    }

    public UserFeedback(EditText name, EditText mobile, EditText subject, EditText message) {
    }

    public UserFeedback(String name, String mobile, String subject, String message) {
        Name = name;
        Mobile = mobile;
        Subject = subject;
        Message = message;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
