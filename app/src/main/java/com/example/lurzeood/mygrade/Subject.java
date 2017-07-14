package com.example.lurzeood.mygrade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lurzeood on 2017/7/11 0011.
 */

public class Subject implements Parcelable {

    private int id;
    private String studentname;
    private String subject;
    private String score;

    public Subject(int id, String studentname, String subject, String score) {
        this.id = id;
        this.studentname = studentname;
        this.subject = subject;
        this.score = score;
    }

    protected Subject(Parcel in) {
        id = in.readInt();
        studentname = in.readString();
        subject = in.readString();
        score = in.readString();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void printOut(){
        System.out.println("id:"+id+"--"+"studentname:"+studentname+"--"+"subject:"+subject+
        "--"+"score:"+score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(studentname);
        dest.writeString(subject);
        dest.writeString(score);
    }
}
