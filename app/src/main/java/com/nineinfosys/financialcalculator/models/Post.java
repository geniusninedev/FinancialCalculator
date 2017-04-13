package com.nineinfosys.financialcalculator.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String uid;
    public String author;
    public String title;
    public String body;
    public String messageTime;
    public String messageDate;
    public int likeCount = 0;
    public Map<String, Boolean> likes = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String title, String body, String messageTime, String messageDate) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.messageTime = messageTime;
        this.messageDate = messageDate;

    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("messageTime", messageTime);
        result.put("messageDate", messageDate);
        result.put("likeCount", likeCount);
        result.put("likes", likes);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
