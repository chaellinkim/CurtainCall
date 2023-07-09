package com.cc.model.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity(name = "REVIEW")
@Data
public class Review {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "REVIEW_ID")
   private long review_id;

   @Column(name = "USER_ID")
   private int user_id;

   @Column(name = "PLAY_TITLE")
   private String play_title;

//   @Nullable
//   @Column(name="REVIEW_IMGNAME")
//   private String review_imgname;
//   
//   @Nullable
//   @Column(name="REVIEW_IMGPATH")
//   private String review_imgpath;

   @Lob
   @Column(name = "REVIEW_IMG")
   private byte[] review_img;

   @Column(name = "REVIEW_COMMENT")
   private String review_comment;

   @Nullable
   @CreationTimestamp
   @Column(name = "REVIEW_DATE")
   private LocalDateTime review_date;

   @Column(name = "REVIEW_COUNT")
   private int count;

   @Transient
   private String encodedImage;

   @Transient
   private String userName;

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getEncodedImage() {
      return encodedImage;
   }

   public void setEncodedImage(String encodedImage) {
      this.encodedImage = encodedImage;
   }

   public long getReview_id() {
      return review_id;
   }

   public void setReview_id(long review_id) {
      this.review_id = review_id;
   }

   public int getUser_id() {
      return user_id;
   }

   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }

   public String getPlay_title() {
      return play_title;
   }

   public void setPlay_title(String play_title) {
      this.play_title = play_title;
   }

   public byte[] getReview_img() {
      return review_img;
   }

   public void setReview_img(byte[] review_img) {
      this.review_img = review_img;
   }

   public String getReview_comment() {
      return review_comment;
   }

   public void setReview_comment(String review_comment) {
      this.review_comment = review_comment;
   }

   public LocalDateTime getReview_date() {
      return review_date;
   }

   public void setReview_date(LocalDateTime review_date) {
      this.review_date = review_date;
   }

   public int getReview_count() {
      return count;
   }

   public void setReview_count(int review_count) {
      this.count = review_count;
   }

   @Override
   public String toString() {
      return "Review [review_id=" + review_id + ", user_id=" + user_id + ", play_title=" + play_title
            + ", review_img=" + Arrays.toString(review_img) + ", review_comment=" + review_comment
            + ", review_date=" + review_date + ", review_count=" + count + "]";
   }

}