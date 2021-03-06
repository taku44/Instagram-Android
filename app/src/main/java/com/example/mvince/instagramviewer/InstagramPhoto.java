package com.example.mvince.instagramviewer;

/**
 * Created by mvince on 1/25/15.
 */
public class InstagramPhoto {
    // username, caption, image_url, height, likes_count
    public String username;
    public String caption;
    public String createdTime;
    public String imageUrl;
//    public String profileUrl;
    public String comment1;
    public String user1;
    public String comment2;
    public String user2;
    public String id;
    public int imageHeight;
    public int likesCount;
    public int commentsCount;

    public String getRelativeTime() {
        long ct = Long.parseLong(createdTime);
        long now = System.currentTimeMillis() / 1000;
        long elapsedSeconds = now - ct;

        if (elapsedSeconds < 60) { // less than a minute
            return String.format("%.0fsec", elapsedSeconds);
        } else if (elapsedSeconds < 3600) { // less than an hour
            return String.format("%.0fmin", Math.floor(elapsedSeconds / 60));
        } else if (elapsedSeconds < 86400) { // less than a day
            return String.format("%.0fhour", Math.floor(elapsedSeconds / 3600));
        } else if (elapsedSeconds < 31536000){  // less than a year (閏年は?とりあえず365日とする)
            return String.format("%.0fday", Math.floor(elapsedSeconds / 86400));
        } else {                        // more than a year (閏年は?とりあえず365日とする)
//            double elapsedSeconds = (double)elapsedSeconds;
            return String.format("%.0fyear", Math.floor(elapsedSeconds / 31536000));
        }
    }
}
