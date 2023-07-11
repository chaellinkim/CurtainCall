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

    //리뷰수정
    public int updateReview(String reviewid, MultipartFile reviewimg, String reviewcomment) throws IOException {
        long id = Long.parseLong(reviewid);
        int result=0;
         if(reviewimg != null && !reviewimg.isEmpty()) {
                byte[] imgdata = reviewimg.getBytes();
                result=reviewRep.updateReview(id, imgdata, reviewcomment);

        }

        return result;
    }

    public List<Review> selectBestReview(){
        return reviewRep.findTop3ByOrderByCountDesc();
    }
    
    public List<Review> searchReviews(String keyword) {
 	   return reviewRep.searchPossible(keyword);
    }
}
