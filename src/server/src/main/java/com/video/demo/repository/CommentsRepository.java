package com.video.demo.repository;

import com.video.demo.domain.Comments;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByVideo_VideoIdOrderByCommentNo(String videoId, PageRequest pageRequest);
}
