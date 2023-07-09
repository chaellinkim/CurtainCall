package com.cc.model.service;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cc.model.entity.Play;
import com.cc.model.entity.Review;
import com.cc.model.repository.ReviewRepository;

@Service
public class ReviewService {
   
   @Autowired
   ReviewRepository reviewRep;

   public Boolean reviewInsert(int userid, String category, String content, MultipartFile img) throws IllegalStateException, IOException {
      
       Review re = new Review();
      
//       String projectPath;
       
//       if (System.getProperty("os.name").toLowerCase().contains("win")) {
//               // Windows에서 실행 중인 경우
//          projectPath = "C:/files/";
//           } else {
//               // 맥 또는 다른 운영체제에서 실행 중인 경우
//           projectPath = "/" + System.getenv("HOME") + "/";
//           }
      
        if(img != null && !img.isEmpty()) {
               byte[] imgdata = img.getBytes();
               re.setPlay_title(category);
               re.setReview_comment(content);
               re.setUser_id(userid);
               re.setReview_img(imgdata);
               
              System.out.println(re.toString());
       }
        
       reviewRep.save(re);
       
      return true;
   }
   
   public int countUpdate(String review_id) {
      
      long id = Long.parseLong(review_id);
      
      int result = reviewRep.update(id);
      System.out.println("result: " + result);
      return result;
   }
   
   public List<Review> selectOne(String play_title){
	      return reviewRep.findByPlay_title(play_title);
	   }
   public List<Review> selectBestReview(){
		return reviewRep.findTop3ByOrderByCountDesc();
	}
   
   public List<Review> searchReviews(String keyword) {
	   return reviewRep.searchPossible(keyword);
   }
   
}